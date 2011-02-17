package cn.bran.play;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Method;
import java.net.URLDecoder;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
import java.util.regex.Pattern;

import play.Play;
import play.Play.Mode;
import play.PlayPlugin;
import play.exceptions.UnexpectedException;
import play.mvc.Http.Header;
import play.mvc.Http.Request;
import play.mvc.Http.Response;
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
	// can be used to cache a plugin scoped valules
	private static Map<String, Object> japidCache = new ConcurrentHashMap<String, Object>();
	/**
	 * pre-compile the templates so PROD mode would work
	 */
	@Override
	public void onLoad() {
		System.out.println("JapidPlugin.onload()");
		beforeDetectingChanges();
		getDumpRequest();
	}
	
	public static Map<String, Object> getCache() {
		return japidCache;
	}

	/**
	 * 
	 */
	private void getDumpRequest() {
		String property = Play.configuration.getProperty("japid.dump.request");
		this.dumpRequest = property;
	}

	@Override
	public void beforeDetectingChanges() {
		// have a delay in change detection.
		if (System.currentTimeMillis() - lastTimeChecked.get() < 1000)
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
			// remove all the existing ApplicationClass will reload everything.
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
		if (property != null && property.length() > 0) {
			if (!"false".equals(property) && !"no".equals(property)) {
				if ("yes".equals(property) || "true".equals(property)) {
					System.out.println("--- action ->: " + Request.current().action);
				} else if (Request.current().url.matches(property)) {
					System.out.println("--- action ->: " + Request.current().action);
				}
			}
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
		
//		// shortcut 
//		String path = Request.current().path;
//		// System.out.println("request path:" + path);
//		Matcher matcher = renderJapidWithPattern.matcher(path);
//		if (matcher.matches()) {
//			String template = matcher.group(1);
//			JapidController.renderJapidWith(template);
//		}
	}

	String dumpRequest = null;

	@Override
	public boolean rawInvocation(Request req, Response response) throws Exception {
		Mode mode = Play.mode;
		if (mode == Mode.DEV) {
			String path = req.path;
			if (path.endsWith("_japidgen")) {
				try {
					beforeDetectingChanges();
				} catch (Exception e) {
				}
				response.out.write("OK".getBytes());
				return true;
			} else if (path.endsWith("_japidregen")) {
				try {
					JapidCommands.regen();
				} catch (Exception e) {
				}
				response.out.write("OK".getBytes());
				return true;
			}
		}

		if (dumpRequest != null && dumpRequest.length() > 0) {
			if ("yes".equals(dumpRequest) || "true".equals(dumpRequest)) {
				System.out.println("---->>" + req.method + " : " + req.url + " [" + req.action + "]");
				// System.out.println("request.controller:" +
				// current.controller);
			} else if ("false".equals(dumpRequest) || "no".equals(dumpRequest)) {
				// do nothing
			} else if (req.url.matches(dumpRequest)) {
				String querystring = req.querystring;
				if (querystring != null && querystring.length() > 0)
					querystring = "?" + querystring;
				else
					querystring = "";
				System.out.println("---->>" + req.method + " : " + req.url + querystring);
				String contentType = req.contentType;
				if (contentType == null || contentType.length() == 0) {
					contentType = "";
				}

				for (String k : req.headers.keySet()) {
					Header h = req.headers.get(k);
					System.out.println("... " + h.name + ":" + URLDecoder.decode(h.value(), "utf-8"));
				}
				// cookie is already in the headers
				// for (String ck : req.cookies.keySet()) {
				// Cookie c = req.cookies.get(ck);
				// System.out.println("... cookie --> " + c.name + ":" +
				// c.value);
				// }
				if ("POST".equals(req.method)) {
					if (contentType.contains("application/x-www-form-urlencoded")) {
						dumpReqBody(req, true);
					} else if (contentType.startsWith("text")) {
						dumpReqBody(req, false);
					} else if (contentType.contains("multipart/form-data")) {
						// cannot dump it, since it may contain binary
					} else if (contentType.contains("xml")) {
						dumpReqBody(req, false);
					} else if (contentType.contains("javascript")) {
						dumpReqBody(req, false);
					}
				}
			}

		}

		return false;

	}

	/**
	 * @param req
	 * @throws IOException
	 * @throws UnsupportedEncodingException
	 */
	private void dumpReqBody(Request req, boolean urlDecode) throws IOException, UnsupportedEncodingException {
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		InputStream bodystream = req.body;
		int r = bodystream.read();
		while (r > -1) {
			bos.write(r);
			r = bodystream.read();
		}

		bodystream.close();

		String body = bos.toString("UTF-8");
		if (urlDecode)
			body = URLDecoder.decode(body, "UTF-8");
		System.out.println("... body -> " + body);
		req.body = new ByteArrayInputStream(bos.toByteArray());
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
		getDumpRequest();
	}

	@Override
	public void onApplicationStart() {
		System.out.println("JapidPlugin: clean japidCache");
		japidCache.clear();
	}

	@Override
	public void onEvent(String message, Object context) {

	}

	/**
	 * intercept a special url that renders a Japid template without going thru
	 * a controller.
	 * 
	 * The url format: (anything)/renderJapidWith/(template path from japidview,
	 * not included) e.g.
	 * 
	 * http://localhost:9000/renderJapidWith/templates/callPicka
	 * 
	 * will render the template "templates/callPicka.html" in the japidview
	 * package in the app dir.
	 * 
	 * TODO: add parameter support.
	 */
	@Override
	public void routeRequest(Request request) {

	}

	private static Pattern renderJapidWithPattern = Pattern.compile(".*" + RENDER_JAPID_WITH + "/(.+)");

}
