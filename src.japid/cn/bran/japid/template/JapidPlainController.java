package cn.bran.japid.template;

import java.io.File;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

import cn.bran.japid.compiler.JapidRender;
import cn.bran.japid.util.RenderInvokerUtils;

/**
 * a helper class. for hiding the template API from user eyes. not really needed
 * since the template invocation API is simple enough.
 * 
 * @author Bing Ran<bing_ran@hotmail.com>
 */
public class JapidPlainController {
	private static final char DOT = '.';
	private static final String HTML = ".html";
	private static final String JAPIDVIEWS_ROOT = "japidviews";

	/**
	 * render an array of objects to a template defined by a Template class.
	 * 
	 * @param <T>
	 *            a sub-class type of JapidTemplateBase
	 * @param c
	 *            a sub-class of JapidTemplateBase
	 * @param args
	 *            arguments
	 */
	public static <T extends JapidTemplateBaseWithoutPlay> String render(Class<T> c, Object... args) {
		if (JapidRender.isDevMode())
			return renderJapidWith(c.getName(), args);
		else
			try {
				RenderResult rr = invokeRender(c, args);
				return rr.getContent().toString();
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
	}

	/**
	 * @param <T>
	 * @param c
	 * @param args
	 * @return
	 * @throws NoSuchMethodException
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 */
	private static <T extends JapidTemplateBaseWithoutPlay> RenderResult invokeRender(Class<T> c, Object... args) {
		int modifiers = c.getModifiers();
		if (Modifier.isAbstract(modifiers)) {
			throw new RuntimeException("Cannot init the template class since it's an abstract class: " + c.getName());
		}
		try {
			Constructor<T> ctor = c.getConstructor(StringBuilder.class);
			StringBuilder sb = new StringBuilder(8000);
			T t = ctor.newInstance(sb);
			RenderResult rr = RenderInvokerUtils.render(t, args);
			return rr;
		} catch (NoSuchMethodException e) {
			throw new RuntimeException("Could not match the arguments with the template args.");
		} catch (InstantiationException e) {
			// e.printStackTrace();
			throw new RuntimeException("Could not instantiate the template object. Abstract?");
		} catch (Exception e) {
			if (e instanceof RuntimeException)
				throw (RuntimeException) e;
			else
				throw new RuntimeException("Could not invoke the template object: " + e);
			// throw new RuntimeException(e);
		}
	}

	/**
	 * pickup the Japid renderer in the conventional location and render it.
	 * Positional match is used to assign values to parameters
	 * 
	 * @param objects
	 */
	protected static String render(Object... objects) {
		String action = template();
		return renderJapidWith(action, objects);
	}

	public static String renderJapidWith(String template, Object... args) {
		RenderResult rr = getRenderResultWith(template, args);
		return rr.getContent().toString();
	}

	protected static String template() {
		// the super.template() class uses current request object to determine
		// the caller and method to find the matching template
		// this won't work if the current method is called from another action.
		// let's fall back to use the stack trace to deduce the template.
		// String caller2 = StackTraceUtils.getCaller2();

		final StackTraceElement[] stes = new Throwable().getStackTrace();

		for (StackTraceElement st : stes) {
			String controller = st.getClassName();
			String action = st.getMethodName();
			Class<?> controllerClass;
			try {
				controllerClass = JapidPlainController.class.getClassLoader().loadClass(controller);
				if (controllerClass != null) {
					Class<?> superclass = controllerClass.getSuperclass();
					if (superclass == JapidPlainController.class) {
						String expr = controller + "." + action;
						return expr;
					}
				}
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return null;
	}

	/**
	 * copies from the same method in the Java class. Removed the public
	 * requirement for easier chaining.
	 * 
	 * @param name
	 * @param clazz
	 * @return
	 */
	public static Method findActionMethod(String name, Class clazz) {
		while (!clazz.getName().equals("java.lang.Object")) {
			for (Method m : clazz.getDeclaredMethods()) {
				if (m.getName().equalsIgnoreCase(name) /*
														 * &&
														 * Modifier.isPublic(m
														 * .getModifiers())
														 */) {
					return m;
				}
			}
			clazz = clazz.getSuperclass();
		}
		return null;
	}

	/**
	 * render parameters to the prescribed template and return the RenderResult
	 * 
	 * @param template
	 *            relative path from japidviews folder. if empty, use implicit
	 *            naming pattern to match the template
	 * @param args
	 */
	public static RenderResult getRenderResultWith(String template, Object... args) {
		//
		// if (TemplateClassLoader.isDevMode())
		// Thread.currentThread().setContextClassLoader(TemplateClassLoader.getCrlr());
		//
		if (template == null || template.length() == 0) {
			template = template();
		}

		if (template.endsWith(HTML)) {
			template = template.substring(0, template.length() - HTML.length());
		}

		// String action = StackTraceUtils.getCaller(); // too tricky to use
		// stacktrace to track the caller action name
		// something like controllers.japid.SampleController.testFindAction

		// if (template.startsWith("@")) {
		// // a template in the current directory
		// template = request.controller + "/" + template.substring(1);
		// }

		// map to default japid view
		// if (template.startsWith("controllers.")) {
		// template = template.substring(template.indexOf(DOT) + 1);
		// }

		String templateClassName = template.startsWith(JAPIDVIEWS_ROOT) ?
					template :
					JAPIDVIEWS_ROOT + File.separator + template;

		templateClassName = templateClassName.replace('/', DOT).replace('\\', DOT);

		Class<? extends JapidTemplateBaseWithoutPlay> tClass = null;

		if (JapidRender.isDevMode())
			tClass = JapidRender.getClass(templateClassName);
		else
			try {
				tClass = (Class<? extends JapidTemplateBaseWithoutPlay>)
						JapidPlainController.class.getClassLoader().loadClass(templateClassName);
			} catch (ClassNotFoundException e) {
				throw new RuntimeException(e);
			}

		if (tClass == null) {
			String templateFileName = templateClassName.replace(DOT, '/') + HTML;
			throw new RuntimeException("Could not find a Japid template with the name of: " + templateFileName);
		} else {
			RenderResult rr;
			// render(tClass, args);
			rr = invokeRender(tClass, args);
			return (rr);
		}
	}

}
