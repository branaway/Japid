package cn.bran.play;

import java.io.File;
import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.beanutils.MethodUtils;

import play.Play;
import play.cache.Cache;
import play.mvc.Controller;
import play.mvc.results.RenderTemplate;
import cn.bran.japid.template.ActionRunner;
import cn.bran.japid.template.JapidTemplateBase;
import cn.bran.japid.template.RenderResult;
import cn.bran.japid.util.StackTraceUtils;

import com.google.gson.Gson;

/**
 * a helper class. for hiding the template API from user eyes. not really needed
 * since the template invocation API is simple enough.
 * 
 * @author Bing Ran<bing_ran@hotmail.com>
 */
public class JapidController extends Controller {
	private static final char DOT = '.';
	private static final String HTML = ".html";

	/**
	 * render an array of objects to a template defined by a Template class.
	 * 
	 * @param <T> a sub-class type of JapidTemplateBase
	 * @param c a sub-class of JapidTemplateBase
	 * @param args arguments
	 */
	public static <T extends JapidTemplateBase> void render(Class<T> c, Object... args) {
		try {
			String methodName = "render";
			Constructor<T> ctor = c.getConstructor(StringBuilder.class);
			StringBuilder sb = new StringBuilder(8000);
			T t = ctor.newInstance(sb);
			RenderResult rr = (RenderResult) MethodUtils.invokeMethod(t, methodName, args);
			throw new JapidResult(rr);
		} catch (Exception e) {
			if (e instanceof JapidResult)
				throw (JapidResult) e;
//			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	/**
	 * just hide the result throwing
	 * 
	 * @param rr
	 */
	protected static void render(RenderResult rr) {
		throw new JapidResult(rr);
	}

	/**
	 * pickup the Japid renderer in the conventional location render it. 
	 * Positional match is used to assign values to parameters
	 * 
	 * @param objects
	 */
	protected static void renderJapid(Object... objects) {
		String action = template();
		renderJapidWith(action, objects);
	}

	/**
	 * render parameters to the prescribed template
	 * 
	 * @param template
	 * @param args
	 */
	public static void renderJapidWith(String template, Object... args) {
		if (template.endsWith(HTML)) {
			template = template.substring(0, template.length() - HTML.length());
		}
		
//		String action = StackTraceUtils.getCaller(); // too tricky to use stacktrace to track the caller action name
		// something like controllers.japid.SampleController.testFindAction
		
		if (template.startsWith("controllers.")) {
			template = template.substring(template.indexOf(DOT) + 1);
		}
		// map to default japid view
		String templateClassName = JapidPlugin.JAPIDVIEWS_ROOT + File.separator + template;
		
		templateClassName = templateClassName.replace('/', DOT).replace('\\', DOT);
		Class tClass = Play.classloader.getClassIgnoreCase(templateClassName);
		if (tClass == null) {
			String templateFileName = templateClassName.replace(DOT, '/') + HTML;
			throw new RuntimeException("Could not find a Japid template with the name of: " + templateFileName);
		} else if (JapidTemplateBase.class.isAssignableFrom(tClass)) {
			render(tClass, args);
		} else {
			throw new RuntimeException("The found class is not a Japid template class: " + templateClassName);
		}
	}
	
//	protected static String caller() {
//		String action = StackTraceUtils.getCaller();
//		return action;
//	}
	/**
	 * cache a Japid RenderResult associated with an action call with specific
	 * arguments
	 * 
	 * To use this method and the getFromCache() effectively requires the each
	 * of the argument has a toString() that uniquely identify itself. Otherwise
	 * the user needs to provider its own kek building routine
	 * 
	 * mind the cost associated with this
	 * 
	 * @param rr
	 *            the render result to cache
	 * @param ttl
	 *            the expiration spec as in Play's Cache.set(), e.g., 1s, 2mn,
	 *            3h, etc
	 * @param objs
	 *            the original arguments
	 */
	protected static void cache(RenderResult rr, String ttl, Object... objs) {
		String caller = buildKey(null, objs);
		Cache.set(caller, rr, ttl);
	}

	/**
	 * use a defined keybase to build the key and cache the RenderResult by that
	 * key
	 * 
	 * @param rr
	 * @param ttl
	 * @param keyBase
	 * @param objs
	 */
	protected static void cache(RenderResult rr, String ttl, String keyBase, Object... objs) {
		String caller = buildKey(keyBase, objs);
		Cache.set(caller, rr, ttl);
	}

	/**
	 * mind the cost associated with this and the key building issues, as stated
	 * in the cache() method
	 * 
	 * @param objs
	 * @return
	 */

	protected static RenderResult getFromCache(Object... objs) {
		// the key building with caller info and the arguments
		if (RenderResultCache.shouldIgnoreCache())
			return null;
		String caller = buildKey(null, objs);
		Object object = Cache.get(caller);
		if (object instanceof RenderResult) {
			return (RenderResult) object;
		} else {
			return null;
		}
	}

	/**
	 * 
	 * @param keyBase
	 *            usually the fully qualified method name of the controller
	 *            action
	 * @param objs
	 * @return
	 */
	protected static RenderResult getFromCache(String keyBase, Object... objs) {
		// the key building with caller info and the arguments
		if (RenderResultCache.shouldIgnoreCache())
			return null;
		String caller = buildKey(keyBase, objs);
		Object object = Cache.get(caller);
		if (object instanceof RenderResult) {
			return (RenderResult) object;
		} else {
			return null;
		}
	}

	/**
	 * @param objs
	 * @return
	 */
	private static String buildKey(String base, Object... objs) {
		// the getCaller thing is relatively expensive, as it might take
		// hundreds of us to complete.

		String caller = base;
		if (base == null)
			caller = StackTraceUtils.getCaller2(); // tricky and expensive
		for (Object o : objs) {
			caller += "-" + String.valueOf(o);
		}
		return caller;
	}

	/**
	 * run a piece of rendering code with cache check and refilling
	 * 
	 * @param runner
	 * @param ttl
	 * @param objects
	 * 
	 * @deprecated use CacheableRunner directly in actions
	 */
	protected static void runWithCache(ActionRunner runner, String ttl, Object... objects) {
		if (ttl == null || ttl.trim().length() == 0)
			throw new RuntimeException("Cache expiration time must be defined.");

		ttl = ttl.trim();
		if (Character.isDigit(ttl.charAt(ttl.length() - 1))) {
			// assuming second
			ttl += "s";
		}

		String base = StackTraceUtils.getCaller();
		RenderResult rr = getFromCache(base, objects);
		if (rr == null) {
			rr = runner.run();
			cache(rr, ttl, base, objects);
		}
		// System.out.println("render show took ms: " + rr.getRenderTime());
		throw new JapidResult(rr);
	}

	/**
	 * 
	 * @param runner
	 * @param ttl
	 * @deprecated use CacheableRunner directly in actions
	 */
	protected static void runWithCache(ActionRunner runner, String ttl) {
		runWithCache(runner, ttl, new Object[] {});
	}

	/**
	 * run action wrapped in a CacheableRunner and throws a JapidResult to the
	 * downstream of the pipeline
	 * 
	 * @param r
	 */
	protected static void render(CacheableRunner r) {
		RenderResult rr = r.run();
		throw new JapidResult(rr);
	}

	/**
	 * set a flag to instruct the cache runner to bypass cache checking for
	 * reading but still cache the result
	 */
	public static void ignoreCache() {
		RenderResultCache.setIgnoreCache(true);
	}

	/**
	 * set a flag to instruct the cache runner to bypass cache checking for
	 * reading but still cache the result, for the current response and next
	 * request
	 */
	public static void ignoreCacheNowAndNext() {
		RenderResultCache.setIgnoreCacheInCurrentAndNextReq(true);
	}
	
	/**
	 * this will set a flag so calling another action won't trigger a redirect
	 */
	protected static void dontRedirect() {
		play.classloading.enhancers.ControllersEnhancer.ControllerInstrumentation.initActionCall();
	}
	
	/**
	 * render a text in a RenderResult so it can work with invoke tag in templates.
	 * @param s
	 */
	protected static void renderText(String s){
		Map<String, String> headers = new HashMap<String, String>();
		headers.put("Content-Type", "text/plain; charset=utf-8");
		render(new RenderResult(headers, new StringBuilder(s), -1L));
	}
	
	protected static void renderText(Object o){
		String str = o == null? "" : o.toString();
		renderText(str);
	}
	
	protected static void renderText(int o){
		renderText(new Integer(o));
	}

	protected static void renderText(long o){
		renderText(new Long(o));
	}

	protected static void renderText(float o){
		renderText(new Float(o));
	}
	
	protected static void renderText(double o){
		renderText(new Double(o));
	}

	protected static void renderText(boolean o){
		renderText(new Boolean(o));
	}

	protected static void renderText(char o){
		renderText(new String(new char[] {o}));
	}
	
	protected static void renderJson(Object o){
		 String json = new Gson().toJson(o);
		 Map<String, String> headers = new HashMap<String, String>();
		 headers.put("Content-Type", "application/json; charset=utf-8");
		 render(new RenderResult(headers, new StringBuilder(json), -1L));
	}
	
	/**
	 *  run another action wrapped in a runnable run() and intercept the Result
	 * 
	 *  one should wrap the call to another action like this:
	 *  new Runnable () {
	 *    public void run() { AnotherController.action();}
	 *  }
	 * @param runnable
	 */
	protected static String getResultFromAction(Runnable runnable ) {
		dontRedirect();
		try {
			runnable.run();
			System.out.println("JapidController.getResultFromAction() warning: the runnable did not generate a result.");
			return "";
		} catch (JapidResult e) {
			return e.content;
			// TODO: handle exception
		} catch (RenderTemplate rt) {
			return rt.getContent();
		} catch (Exception ex) {
			ex.printStackTrace();
			return "";
		}
	}

	/**
	 *  run another action wrapped in a runnable run() and intercept the Result, ignoring the cache
	 * 
	 *  one should wrap the call to another action like this:
	 *  new Runnable () {
	 *    public void run() { AnotherController.action();}
	 *  }
	 * @param runnable
	 */
	protected static String getFreshResultFromAction(Runnable runnable ) {
		ignoreCache();
		return getResultFromAction(runnable);
	}
	
	
}
