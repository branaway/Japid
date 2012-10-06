package cn.bran.japid.util;

import java.io.File;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

import play.exceptions.TemplateExecutionException;
import play.mvc.Http.Request;

import cn.bran.japid.compiler.NamedArg;
import cn.bran.japid.compiler.NamedArgRuntime;
import cn.bran.japid.template.JapidTemplateBaseWithoutPlay;
import cn.bran.japid.template.RenderResult;
import cn.bran.play.JapidController2;

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
	public static <T extends JapidTemplateBaseWithoutPlay> RenderResult invokeRender(
			Class<T> c, Object... args) {
		int modifiers = c.getModifiers();
		if (Modifier.isAbstract(modifiers)) {
			throw new RuntimeException(
					"Cannot init the template class since it's an abstract class: "
							+ c.getName());
		}
		try {
			// String methodName = "render";
			Constructor<T> ctor = c.getConstructor(StringBuilder.class);
			StringBuilder sb = new StringBuilder(8000);
			T t = ctor.newInstance(sb);
			RenderResult rr = (RenderResult) render(t, args);
			// RenderResult rr = (RenderResult) MethodUtils.invokeMethod(t,
			// methodName, args);
			return rr;
		} catch (NoSuchMethodException e) {
			throw new RuntimeException(
					"Could not match the arguments with the template args.");
		} catch (InstantiationException e) {
			// e.printStackTrace();
			throw new RuntimeException(
					"Could not instantiate the template object. Abstract?");
		} catch (InvocationTargetException e) {
			// e.printStackTrace();
			Throwable te = e.getTargetException();
			if (te instanceof TemplateExecutionException)
				throw (TemplateExecutionException) te;
			Throwable cause = te.getCause();
			if (cause != null)
				if (cause instanceof RuntimeException)
					throw (RuntimeException) cause;
				else
					throw new RuntimeException(
							"error in running the renderer: "
									+ cause.getMessage(), cause);
			else if (te instanceof RuntimeException)
				throw (RuntimeException) te;
			else
				throw new RuntimeException("error in running the renderer: "
						+ te.getMessage(), te);
		} catch (Exception e) {
			if (e instanceof RuntimeException)
				throw (RuntimeException) e;
			else
				throw new RuntimeException(
						"Could not invoke the template object: ", e);
			// throw new RuntimeException(e);
		}
	}

	public static <T extends JapidTemplateBaseWithoutPlay> RenderResult invokeNamedArgsRender(
			Class<T> c, NamedArgRuntime[] args) {
		int modifiers = c.getModifiers();
		if (Modifier.isAbstract(modifiers)) {
			throw new RuntimeException(
					"Cannot init the template class since it's an abstract class: "
							+ c.getName());
		}
		try {
			// String methodName = "render";
			Constructor<T> ctor = c.getConstructor(StringBuilder.class);
			StringBuilder sb = new StringBuilder(8000);
			JapidTemplateBaseWithoutPlay t = ctor.newInstance(sb);
			RenderResult rr = (RenderResult) renderWithNamedArgs(t, args);
			// RenderResult rr = (RenderResult) MethodUtils.invokeMethod(t,
			// methodName, args);
			return rr;
		} catch (NoSuchMethodException e) {
			throw new RuntimeException(
					"Could not match the arguments with the template args.");
		} catch (InstantiationException e) {
			// e.printStackTrace();
			throw new RuntimeException(
					"Could not instantiate the template object. Abstract?");
		} catch (InvocationTargetException e) {
			// e.printStackTrace();
			Throwable e1 = e.getTargetException();
			throw new RuntimeException(
					"Could not invoke the template object:  ", e1);
		} catch (Exception e) {
			if (e instanceof RuntimeException)
				throw (RuntimeException) e;
			else
				throw new RuntimeException(
						"Could not invoke the template object: ", e);
			// throw new RuntimeException(e);
		}
	}

	/**
	 * @param template
	 * @return
	 */
	public static String getTemapletClassName(String template) {
		//
		if (template == null || template.length() == 0) {
			template = JapidController2.template();
		}
	
		if (template.endsWith(JapidController2.HTML)) {
			template = template.substring(0, template.length() - JapidController2.HTML.length());
		}
	
		// String action = StackTraceUtils.getCaller(); // too tricky to use
		// stacktrace to track the caller action name
		// something like controllers.japid.SampleController.testFindAction
	
		if (template.startsWith("@")) {
			// a template in the current directory
			template = Request.current().controller + "/"
					+ template.substring(1);
		}
	
		// map to default japid view
		if (template.startsWith("controllers.")) {
			template = template.substring(template.indexOf(JapidController2.DOT) + 1);
		}
		String templateClassName = template
				.startsWith(DirUtil.JAPIDVIEWS_ROOT) ? template
				: DirUtil.JAPIDVIEWS_ROOT + File.separator + template;
	
		templateClassName = templateClassName.replace('/', JapidController2.DOT).replace('\\',
				JapidController2.DOT);
		return templateClassName;
	}
}
