package cn.bran.japid.util;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import cn.bran.japid.compiler.NamedArg;
import cn.bran.japid.compiler.NamedArgRuntime;
import cn.bran.japid.template.JapidTemplateBaseWithoutPlay;

public class RenderInvokerUtils {
	// private static final String RENDER_METHOD = "render";

	public static <T extends JapidTemplateBaseWithoutPlay> Object render(T t, Object... args) throws IllegalArgumentException, IllegalAccessException, InvocationTargetException {
		if (args == null) {
			// treat it as a single null argument
			args = new Object[] { null };
		}
	
		Method m = t.renderMethodInstance;
		if (m == null) {
			throw new RuntimeException("The render method cache is not initialized for: " + t.getClass().getName() + ". Please run 'play japid:regen' to fresh the generated Java files.");
		}
			Object invoke = m.invoke(t, args);
			return invoke;
//		} catch (IllegalArgumentException e) {
//			throw new RuntimeException("Template argument type mismatch: ", e);
//		} catch (InvocationTargetException e) {
//			Throwable te = e.getTargetException();
//			Throwable cause = te.getCause();
//			if (cause != null)
//				throw new RuntimeException("error in running the renderer: ", cause);
//			else 
//				throw new RuntimeException("error in running the renderer: ", te);
//			// te.printStackTrace();
//		} catch (Exception e) {
//			e.printStackTrace();
//			throw new RuntimeException(e);
//		}
	}

	public static <T extends JapidTemplateBaseWithoutPlay> Object renderWithNamedArgs(T t, NamedArgRuntime... args) throws IllegalArgumentException, IllegalAccessException, InvocationTargetException {
		if (args == null) {
			// treat it as a single null argument
			args = new NamedArgRuntime[] { null };
		}
		return render(t, t.buildArgs(args));
	}
}
