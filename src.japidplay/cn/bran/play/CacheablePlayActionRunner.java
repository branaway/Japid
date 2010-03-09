package cn.bran.play;

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

	public CacheablePlayActionRunner(String ttl, Object... args) {
		super(ttl, args);
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
		} catch (cn.bran.play.JapidResult jr) {
			return jr.getRenderResult();
		}
	}

}
