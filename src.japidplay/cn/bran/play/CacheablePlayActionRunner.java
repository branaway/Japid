package cn.bran.play;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

import play.cache.CacheFor;
import play.mvc.ActionInvoker;
import play.mvc.After;
import play.mvc.Before;
import play.mvc.Finally;
import cn.bran.japid.template.RenderResult;

/**
 * The class defines a cached adapter to invoke a Play controller action. A new instance
 * must be used for each action invocation.
 * 
 * This class can be used in template, indirectly by Japid engine.
 * 
 * @author Bing Ran<bing_ran@hotmail.com>
 * 
 */
public abstract class CacheablePlayActionRunner extends CacheableRunner {
	String controllerActionString;
//	private CacheFor cacheFor;
	private boolean gotFromCacheForCache;
	private String cacheForVal;
	
	public CacheablePlayActionRunner(String ttl, Object... args) {
		super(ttl, args);
		if (args != null && args.length > 0) {
			// the first argument is the methodInvocation string, like myController.myMethod
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
		try {
			play.classloading.enhancers.ControllersEnhancer.ControllerInstrumentation.initActionCall();
			runPlayAction();
			throw new RuntimeException("No render result from running play action. Probably the action was not using Japid templates.");
		} catch (JapidResult jr) {
			if (cacheForVal != null && cacheForVal.length() > 0 && !gotFromCacheForCache) {
				play.cache.Cache.set(keyString, jr, cacheForVal);
			}
			return jr.getRenderResult();
		}
	}

	/**
	 * check the cache for the action. The cache should have been caused by the CacheFor annotation
	 * 
	 * @param class1
	 * @param actionName
	 */
	protected void checkActionCacheFor(Class<? extends JapidController> class1, String actionName) {
		// TODO: should cache the CacheFor object
		String className = class1.getName();
		String cacheForKey = className + "_" + actionName;
		cacheForVal = (String) JapidPlugin.getCache().get(cacheForKey);
		if (cacheForVal == null) {
			// the cache has not been filled up yet.
			Method[] mths = class1.getDeclaredMethods();
			for (Method m : mths) {
				if (m.getName().equalsIgnoreCase(actionName) && Modifier.isPublic(m.getModifiers())) {
					if (!m.isAnnotationPresent(Before.class) && !m.isAnnotationPresent(After.class) && !m.isAnnotationPresent(Finally.class)) {
						CacheFor cacheFor = m.getAnnotation(CacheFor.class);
						if (cacheFor == null) {
							cacheForVal = "";
						}
						else {
							cacheForVal = cacheFor.value();
						}
						JapidPlugin.getCache().put(cacheForKey, cacheForVal);
					}
				}
			}
		}
		
		if (cacheForVal != null && cacheForVal.length() > 0) {
			String key = super.keyString;
			Object v = play.cache.Cache.get(key);
			if (v != null) {
				if (v instanceof JapidResult) {
					this.gotFromCacheForCache = true;
					throw ((JapidResult) v);
				} else if (v instanceof CachedRenderResult) {
					this.gotFromCacheForCache = true;
					throw new JapidResult(((CachedRenderResult) v).rr);
				} else {
//								throw new RuntimeException("got something from the cache but not sure what it is: "
//										+ v.getClass().getName());
				}
			}
			return;
		}				
	}

}
