package bran.play;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import play.classloading.enhancers.LocalvariablesNamesEnhancer.LocalVariablesNamesTracer;
import play.exceptions.ActionNotFoundException;
import play.exceptions.NoRouteFoundException;
import play.exceptions.PlayException;
import play.exceptions.UnexpectedException;
import play.mvc.ActionInvoker;
import play.mvc.Router;
import play.mvc.Http.Request;
import play.mvc.Router.ActionDefinition;
import bran.japid.UrlMapper;

/**
 * the logic is modeled after the the Play!'s {@code Template.ActionBridge.}
 * 
 * 
 * @author bran
 * 
 */
public class RouteAdapter implements UrlMapper {
//	String controllerName;

	/**
	 * 
	 * @param controllerName
	 *            the current controller's name. If null is specified, the Request.current().controller will be used
	 */
	public RouteAdapter() {
		super();
//		this.controllerName = controllerName;
	}

	/**
	 * Some sample form of action: CRUD.index(), blank(), attachment(_id, _name)
	 * this one has method call inside of controller param list
	 * 
	 * @{Application.show(post.previous().id)
	 * 
	 *                                        this is a super long one: *
	 * 
	 * @{list( ).remove('page').add('search', params.search).add('order',
	 *         (_caller.order == 'DESC' ? 'ASC' : 'DESC'))} in the table.html in
	 *         
	 *         the CRUD module
	 * 
	 *         this one has property access in the param list:
	 * @{show(_caller.object.id)
	 * 
	 * @param actionString
	 *            the leasing part of the whole expression, e.g.
	 *            Application.show in @{Application.show(post.previous().id)}.
	 *            it can also bear the form of a simple function call, such as:
	 *            show
	 * @param params
	 *            the runtime value of the parameters used to call the action,
	 *            e.g. 123 from evaluating "post.previous().id"
	 */
	@Override
	public String lookup(String actionString, Object[] params) {
		// lookup the target method param names to build a name value map to
		// reverse-lookup in the router
		String controllerName = Request.current().controller;

		try {
			// forms: Controller.action, action, package.Controller.action
			String action = actionString;
			if (actionString.indexOf(".") > 0) {
				// fell spec with controller name
			}
			else {
				action = controllerName + "." + actionString;
			}
			if (action.endsWith(".call")) {
				action = action.substring(0, action.length() - 5);
			}
			try {
				Map<String, Object> paramMap = new HashMap<String, Object>();
				Method actionMethod = (Method) ActionInvoker.getActionMethod(action)[1];
				Class<?> actionClass = actionMethod.getDeclaringClass();
				Integer methodHash = LocalVariablesNamesTracer.computeMethodHash(actionMethod.getParameterTypes());
				String synthFieldForMethod = "$" + actionMethod.getName() + methodHash;
				String[] targetParamNames = (String[]) actionClass.getDeclaredField(synthFieldForMethod).get(null);
				// too many parameters versus action, possibly a developer
				// error. we must warn him.
				if (targetParamNames.length < params.length) {
					throw new NoRouteFoundException(action, null);
				}
				for (int i = 0; i < params.length; i++) {
					String key = i < targetParamNames.length ? targetParamNames[i] : "";
					String value = params[i] == null ? null : params[i].toString();
					paramMap.put(key, value);
				}
				ActionDefinition reverse = Router.reverse(action, paramMap);
				return reverse != null ? reverse.toString() : "";
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

	@Override
	public String lookupAbs(String action, Object[] args) {
		// TODO Auto-generated method stub
		return null;
	}

}
