package cn.bran.play;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import play.classloading.enhancers.LocalvariablesNamesEnhancer.LocalVariablesNamesTracer;
import play.data.binding.Unbinder;
import play.exceptions.ActionNotFoundException;
import play.exceptions.NoRouteFoundException;
import play.exceptions.PlayException;
import play.exceptions.UnexpectedException;
import play.mvc.ActionInvoker;
import play.mvc.Router;
import play.mvc.Http.Request;
import play.mvc.Router.ActionDefinition;

/**
 * this file is copied from the the the following code is copied from the
 * play.templates
 * .GroovyTemplate.ExecutableTemplate.ActionBridge.invokeMethod(...); version 1.2 trunk. 
 * 
 * since the class is not public.
 * 
 * Please check this with the original source code to find any updates to bring over.
 * 
 * @author Bing Ran<bing_ran@hotmail.com>
 * 
 */
public class ActionBridge {

	boolean absolute = false;

	public ActionBridge(boolean absolute) {
		this.absolute = absolute;
	}

	public Object _abs() {
		this.absolute = true;
		return this;
	}

	/**
	 * this is really to to the reverse url lookup
	 * 
	 * @param actionString
	 * @param param
	 * @return
	 */
	public ActionDefinition invokeMethod(String actionString, Object param) {
		try {

			String controllerName = Request.current().controller;
			// forms: Controller.action, action, package.Controller.action
			String action = actionString;
//			String methodName = actionString;
			if (actionString.indexOf(".") > 0) {
				int lastIndexOf = actionString.lastIndexOf('.');
//				methodName = actionString.substring(lastIndexOf + 1);
				controllerName = actionString.substring(0, lastIndexOf);
				// fell spec with controller name
			} else {
				action = controllerName + "." + actionString;
			}
			
			try {
				Map<String, Object> r = new HashMap<String, Object>();
				Method actionMethod = (Method) ActionInvoker.getActionMethod(action)[1];
				String[] names = (String[]) actionMethod
						.getDeclaringClass()
						.getDeclaredField(
								"$" + actionMethod.getName()
										+ LocalVariablesNamesTracer.computeMethodHash(actionMethod.getParameterTypes())).get(null);
				if (param instanceof Object[]) {
					// too many parameters versus action, possibly a developer
					// error. we must warn him.
					if (names.length < ((Object[]) param).length) {
						throw new NoRouteFoundException(action, null);
					}
					for (int i = 0; i < ((Object[]) param).length; i++) {
						if (((Object[]) param)[i] instanceof Router.ActionDefinition && ((Object[]) param)[i] != null) {
							Unbinder.unBind(r, ((Object[]) param)[i].toString(), i < names.length ? names[i] : "");
						} else if (isSimpleParam(actionMethod.getParameterTypes()[i])) {
							if (((Object[]) param)[i] != null) {
								Unbinder.unBind(r, ((Object[]) param)[i].toString(), i < names.length ? names[i] : "");
							}
						} else {
							Unbinder.unBind(r, ((Object[]) param)[i], i < names.length ? names[i] : "");
						}
					}
				}
				Router.ActionDefinition def = Router.reverse(action, r);
				if (absolute) {
					def.absolute();
				}

				// if (template.template.name.endsWith(".html") ||
				// template.template.name.endsWith(".xml")) {
				def.url = def.url.replace("&", "&amp;");
				// }
				return def;
			} catch (ActionNotFoundException e) {
				throw new NoRouteFoundException(action, null);
			}
		} catch (Exception e) {
			if (e instanceof PlayException) {
				throw (PlayException) e;
			}
			throw new UnexpectedException(e);
		}
	}

	static boolean isSimpleParam(Class type) {
		return Number.class.isAssignableFrom(type) || type.equals(String.class) || type.isPrimitive();
	}
}