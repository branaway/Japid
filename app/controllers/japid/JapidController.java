package controllers.japid;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

import cn.bran.japid.template.JapidTemplateBase;
import cn.bran.japid.template.RenderResult;
import cn.bran.play.JapidResult;

import play.mvc.Controller;

/**
 * a helper class. for hiding the template API from user eyes. 
 * not really needed since the template invocation API is simeple enough.
 * 
 * @author Bing Ran<bing_ran@hotmail.com>
 */
public class JapidController extends Controller {
	public static <T extends JapidTemplateBase> void render(Class<T> c, Object... args) {
		Class<?>[] paramTypes = new Class<?>[args.length];
		for (int i=0; i < args.length; i++) {
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
}
