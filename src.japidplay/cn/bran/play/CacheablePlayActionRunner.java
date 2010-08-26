package cn.bran.play;

import java.lang.reflect.Method;

import play.cache.CacheFor;
import play.mvc.ActionInvoker;
import cn.bran.japid.template.RenderResult;

/**
 * The class defines a cached adapter to invoke a Play controller action.
 * 
 * This class can be used in template, indirectly by Japid engine.
 * 
 * @author Bing Ran<bing_ran@hotmail.com>
 * 
 */
public abstract class CacheablePlayActionRunner extends CacheableRunner {
	String controllerActionString;
	
	public CacheablePlayActionRunner(String ttl, Object... args) {
		super(ttl, args);
		if (args != null && args.length > 0) {
			// the frist arg is the methodInvocation string, like myController.myMethod
			controllerActionString = (String) args[0];
		}
	}

	public CacheablePlayActionRunner(String ttl) {
		super(ttl);
	}
	
	/**
	 * the action invocation to Play controller action, which must throw a
	 * JapidResult
	 * 
	 * @throws JapidResult
	 */
	protected abstract void runPlayAction() throws JapidResult;

	@Override
	protected RenderResult render() {
		// XXX can cache the actionMethod lookup, using Cache will probably work, since it's cleared up every time system detects changes
		Object[] actionMethod = ActionInvoker.getActionMethod(controllerActionString);
//		Class<?> controllerClass = (Class<?>) actionMethod[0];
		Method meth = (Method) actionMethod[1];
		CacheFor cacheFor = meth.getAnnotation(CacheFor.class);
		String key = super.keyString;
		if (cacheFor != null) {
			Object v = play.cache.Cache.get(key);
			if (v != null) {
				if (v instanceof JapidResult) {
					return ((JapidResult)v).getRenderResult();
				}
				else if (v instanceof CachedRenderResult) {
					return ((CachedRenderResult)v).rr;
				}
				else {
					System.out.println("got something from the cache but not sure what it is: " + v.getClass().getName());
				}
			}
		}
		
		try {
			play.classloading.enhancers.ControllersEnhancer.ControllerInstrumentation.initActionCall();
			runPlayAction();
			throw new RuntimeException("No render result from running play action. Probably the action was not using Japid templates.");
		} catch (JapidResult jr) {
			if (cacheFor != null) {
				play.cache.Cache.set(key, jr, cacheFor.value());
			}
			return jr.getRenderResult();
		}
	}

}
