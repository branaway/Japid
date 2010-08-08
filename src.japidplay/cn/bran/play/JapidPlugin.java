package cn.bran.play;

import java.io.File;
import java.lang.reflect.Method;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import play.Play;
import play.PlayPlugin;
import play.exceptions.UnexpectedException;
import play.mvc.Http.Header;
import play.mvc.Http.Request;
import play.mvc.Scope.Flash;
import play.mvc.results.Result;

/**
 * 
 * 
 * @author Bing Ran<bing_ran@hotmail.com>
 * 
 */
public class JapidPlugin extends PlayPlugin {
	private static final String RENDER_JAPID_WITH = "/renderJapidWith";
	private static final String NO_CACHE = "no-cache";
	private static AtomicLong lastTimeChecked = new AtomicLong(0);
	
	
	/**
	 * pre-compile the templates so PROD mode would work
	 */
	@Override
	public void onLoad() {
		beforeDetectingChanges();
	}

	@Override
	public void beforeDetectingChanges() {
		// have a delay in change detection.
		if (System.currentTimeMillis() - lastTimeChecked.get() < 1000 )
			return;
		List<File> changed = JapidCommands.reloadChanged();
		if (changed.size() > 0) {
			for (File f : changed) {
				// System.out.println("pre-detect changed: " + f.getName());
			}
		}

		boolean hasRealOrphan = JapidCommands.rmOrphanJava();
		lastTimeChecked.set(System.currentTimeMillis());

		if (hasRealOrphan) {
			// a little messy here. clean the cache in case bad files are delete
			// remove all the existing ApplicationClass will reload verything.
			// ideally we just need to remove the orphan. But the internal cache
			// is not visible. Need API change to do that.
			Play.classes.clear();
			throw new RuntimeException("found orphan template Java artifacts. reload to be safe.");
		}
	}
	


	// // VirtualFile appRoot = VirtualFile.open(Play.applicationPath);
	// TranslateTemplateTask t = new TranslateTemplateTask();
	// {
	// Project proj = new Project();
	// t.setProject(proj);
	// proj.init();
	//
	// t.setSrcdir(new File("app"));
	// t.setIncludes(JAPIDVIEWS_ROOT + "/**/*.html");
	// t.importStatic(JapidPlayAdapter.class);
	// t.importStatic(Validation.class);
	// t.importStatic(JavaExtensions.class);
	// t.addAnnotation(NoEnhance.class);
	// t.addImport(JAPIDVIEWS_ROOT + "._layouts.*");
	// t.addImport(JAPIDVIEWS_ROOT + "._tags.*");
	// t.addImport("models.*");
	//
	// // t.add(new ModifiedSelector());
	// t.setTaskType("foo");
	// t.setTaskName("foo");
	// t.setOwningTarget(new Target());
	// }

	public static final String JAPIDVIEWS_ROOT = "japidviews";
	public static final String JAVATAGS = "_javatags";
	public static final String LAYOUTDIR = "_layouts";
	public static final String TAGSDIR = "_tags";

	@Override
	public void onApplicationStop() {
		try {
			Japid.shutdown();
		} catch (Exception e) {
			throw new UnexpectedException(e);
		}
	}

	/**
	 * just before an action is invoked. See {@code ActionInvoker}
	 */
	@Override
	public void beforeActionInvocation(Method actionMethod) {
		String property = Play.configuration.getProperty("japid.dump.request");
		Request current = Request.current();
		if ("yes".equals(property) || "true".equals(property)) {
			System.out.println("---->>" + current.method + " : " + current.url + " ["+ current.action + "]");
//			System.out.println("request.controller:" + current.controller);
		}
		
		String string = Flash.current().get(RenderResultCache.READ_THRU_FLASH);
		if (string != null) {
			RenderResultCache.setIgnoreCache(true);
		} else {
			// cache-control in lower case, lowercase for some reason
			Header header = Request.current().headers.get("cache-control"); 
			if (header != null) {
				List<String> list = header.values;
				if (list.contains(NO_CACHE)) {
					RenderResultCache.setIgnoreCache(true);
				}
			} else {
				header = Request.current().headers.get("pragma");
				if (header != null) {
					List<String> list = header.values;
					if (list.contains(NO_CACHE)) {
						RenderResultCache.setIgnoreCache(true);
					}
				} else {
					// just in case
					RenderResultCache.setIgnoreCacheInCurrentAndNextReq(false);
				}
			}
		}
	}

	/**
	 * this takes place before the flash and session save in the ActionInvoker
	 */
	@Override
	public void onActionInvocationResult(Result result) {
		Flash fl = Flash.current();
		if (RenderResultCache.shouldIgnoreCacheInCurrentAndNextReq()) {
			fl.put(RenderResultCache.READ_THRU_FLASH, "yes");
		} else {
			fl.remove(RenderResultCache.READ_THRU_FLASH);
			fl.discard(RenderResultCache.READ_THRU_FLASH);
		}

		// always reset the flag since the thread may be reused for another
		// request processing
		RenderResultCache.setIgnoreCacheInCurrentAndNextReq(false);
	}

	/**
	 * right after an action is invoked. See {@code ActionInvoker} Currently
	 * this code has no effect to the flash due to a bug in ActionInvoker where
	 * Flash save its state to cookie.
	 */
	@Override
	public void afterActionInvocation() {
	}

	@Override
	public void detectChange() {
	}

	/**
	 * this thing happens very early in the cycle. Neither Session nore Flash is
	 * restored yet.
	 */
	@Override
	public void beforeInvocation() {
	}

	@Override
	public void afterApplicationStart() {
		Japid.startup();
	}

	@Override
	public void onApplicationStart() {
		// TODO Auto-generated method stub
		super.onApplicationStart();
	}

	@Override
	public void onEvent(String message, Object context) {

	}

	/**
	 * intercept a special url that renders a Japid template without going thru a controller.
	 * 
	 * The url format: (anything)/renderJapidWith/(template path from japidview, not included)
	 * e.g.
	 * 
	 * http://localhost:9000/renderJapidWith/templates/callPicka
	 * 
	 * will render the template "templates/callPicka.html" in the japidview package in the app dir.
	 * 
	 * TODO: add parameter support.
	 */
	@Override
	public void routeRequest(Request request) {
		String path = request.path;
//		System.out.println("request path:" + path);
		Matcher matcher = renderJapidWithPattern.matcher(path);
		if (matcher.matches()) {
			String template = matcher.group(1);
			JapidController.renderJapidWith(template);
		}
	}
	
	private static Pattern renderJapidWithPattern = Pattern.compile(".*" + RENDER_JAPID_WITH + "/(.+)");
	
}
