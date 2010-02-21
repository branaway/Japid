package cn.bran.play;

import java.io.File;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

import play.Play;
import play.cache.Cache;
import play.mvc.Controller;
import play.mvc.Http;
import cn.bran.japid.template.ActionRunner;
import cn.bran.japid.template.JapidTemplateBase;
import cn.bran.japid.template.RenderResult;
import cn.bran.japid.util.StackTraceUtils;

/**
 * a helper class. for hiding the template API from user eyes. not really needed
 * since the template invocation API is simeple enough.
 * 
 * @author Bing Ran<bing_ran@hotmail.com>
 */
public class JapidController extends Controller {
	/**
	 * use reflection to do the rendering
	 * 
	 * @param <T>
	 * @param c
	 * @param args
	 */
	protected static <T extends JapidTemplateBase> void render(Class<T> c, Object... args) {
		Class<?>[] paramTypes = new Class<?>[args.length];
		for (int i = 0; i < args.length; i++) {
			paramTypes[i] = args[i].getClass();
		}
		try {
			Constructor<T> ctor = c.getConstructor(StringBuilder.class);
			Method renderMeth = c.getMethod("render", paramTypes);
			// TODO: consider caching the method so reflection cost is
			// eliminated
			StringBuilder sb = new StringBuilder(8000);
			T t = ctor.newInstance(sb);
			RenderResult rr = (RenderResult) renderMeth.invoke(t, args);

			throw new JapidResult(rr);
		} catch (Exception e) {
			e.printStackTrace();
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
	 * pickup the japid renderer in the conventional location render it
	 * 
	 * @param objects
	 */
	protected static void renderJapid(Object... objects) {
		// get full action: e.g., mypackage.Application.index
		String action = Http.Request.current().action.replace(".", "/");

		// map to default japid view
		String templateClassName = JapidPlugin.JAPIDVIEWS_ROOT + File.separator + action;

		Class tClass = Play.classloader.getClassIgnoreCase(templateClassName.replace('/', '.').replace('\\', '.'));
		if (tClass == null) {
			throw new RuntimeException("There is no default Japid renderer by the name of: " + templateClassName);
		} else if (JapidTemplateBase.class.isAssignableFrom(tClass)) {
			render(tClass, objects);
		} else {
			throw new RuntimeException("The found class is not a Japid template class: " + templateClassName);
		}
	}

	/**
	 * cache a Japid RenderResult associated with an action call with specific
	 * arguments
	 * 
	 * To use this method and the getFromCache() effectively requires the each
	 * of the argument has a toString() that uniquely identify itself. Otherwise
	 * the user needs to provider its own kek building routine
	 * 
	 * mind the cost asscoiated with this
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
	 * use a defined keybase to build the key and cache the RenderResult by that key
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
	 * mind the cost asscoiated with this and the key building issues, as stated
	 * in the cache() method
	 * 
	 * @param objs
	 * @return
	 */

	protected static RenderResult getFromCache(Object... objs) {
		// the key building with caller info and the arguments
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
			caller = StackTraceUtils.getCaller2();
		for (Object o : objs) {
			caller += "-" + String.valueOf(o);
		}
		return caller;
	}
	
	protected static void runWithCache(ActionRunner runner, String ttl, Object...objects ) {
		String base = StackTraceUtils.getCaller();
		RenderResult rr = getFromCache(base, objects);
		if (rr == null) {
			RenderResult run = runner.run();
			cache(rr, ttl, base, objects);
		}
		// System.out.println("render show took ms: " + rr.getRenderTime());
		throw new JapidResult(rr);
	}
}
