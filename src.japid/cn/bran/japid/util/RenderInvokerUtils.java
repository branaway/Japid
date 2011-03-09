package cn.bran.japid.util;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import cn.bran.japid.template.JapidTemplateBase;
import cn.bran.japid.template.JapidTemplateBaseWithoutPlay;
import cn.bran.japid.template.RenderResult;

public class RenderInvokerUtils {
	private static final String RENDER_METHOD = "render";

	public static <T extends JapidTemplateBaseWithoutPlay> Object render(T t, Object... args) {
		String className = t.getClass().getName();
		Method[] methods = t.getClass().getDeclaredMethods();
		if (args == null) {
			// treat it as a single null argument
			args = new Object[] {null};
		}
		
		for(Method m : methods) {
			if (m.getName().equals(RENDER_METHOD)) {
				// 
				Class<?>[] types = m.getParameterTypes();
				if(types.length != args.length) {
					throw new RuntimeException("The number of arguments does not match the template's parameters: " + className);
				}
				else {
					try {
						Object invoke = m.invoke(t, args);
						return invoke;
					} 
					catch (IllegalArgumentException e) {
						throw new RuntimeException("Template argument type mismatch: " + className);
					} 
					catch (InvocationTargetException e) {
						Throwable te = e.getTargetException();
						te.printStackTrace();
						throw new RuntimeException("error in running the renderer: " + te.getMessage());
					} 
					catch (Exception e) {
						e.printStackTrace();
						throw new RuntimeException(e);
					}
				}
			}
		}
		throw new RuntimeException("Could not find a render() in the template: " + className);
		
	}
}
