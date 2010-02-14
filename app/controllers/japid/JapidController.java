package controllers.japid;

import java.io.File;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

import cn.bran.japid.template.JapidTemplateBase;
import cn.bran.japid.template.RenderResult;
import cn.bran.play.JapidPlugin;
import cn.bran.play.JapidResult;

import play.Play;
import play.mvc.Controller;
import play.mvc.Http;

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
	 * pickup the japid renderer in the preset location
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
		}
		else if (JapidTemplateBase.class.isAssignableFrom(tClass)){
			render(tClass, objects);
		}
		else {
			throw new RuntimeException("The found class is not a Japid template class: " + templateClassName);
		}
	}
}
