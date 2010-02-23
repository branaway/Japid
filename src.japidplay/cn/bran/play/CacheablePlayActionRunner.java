package cn.bran.play;

import play.cache.Cache;
import cn.bran.japid.template.ActionRunner;
import cn.bran.japid.template.RenderResult;

/**
 * The class defines a cached adapter to invoke a Play controller action.
 * 
 * @author Bing Ran<bing_ran@hotmail.com>
 * 
 */
public abstract class CacheablePlayActionRunner extends ActionRunner {
	Object[] key = null;
	String ttl = null;

	public CacheablePlayActionRunner(String ttl, Object... args) {
		super();
		this.key = args;
		this.ttl = ttl;
	}

	@Override
	public RenderResult run() {
		if (key != null && key.length > 0 && ttl != null && ttl.length() > 0) {
			String keyString = buildKey();
			RenderResult rr = (RenderResult) Cache.get(keyString);
			if (rr != null) {
				return rr;
			} else {
				try {
					play.classloading.enhancers.ControllersEnhancer.ControllerInstrumentation.initActionCall();
					runPlayAction();
				} catch (cn.bran.play.JapidResult jr) {
					rr = jr.getRenderResult();
					Cache.set(keyString, rr, ttl);
					return rr;
				}
			}
		} else {
			try {
				play.classloading.enhancers.ControllersEnhancer.ControllerInstrumentation.initActionCall();
				runPlayAction();
			} catch (cn.bran.play.JapidResult jr) {
				RenderResult rr = jr.getRenderResult();
				return rr;
			}
		}
		//
		throw new RuntimeException("No render result from running play action. Probably the action was not using Japid templates.");
	}

	/**
	 * @return
	 */
	private String buildKey() {
		String keyString = "";
		for (Object k : key) {
			keyString += ":" + String.valueOf(k);
		}
		return keyString;
	}

	/**
	 * the action invocation to Play controller action, which must throw a
	 * JapidResult
	 * 
	 * @throws JapidResult
	 */
	public abstract void runPlayAction() throws JapidResult;

}
