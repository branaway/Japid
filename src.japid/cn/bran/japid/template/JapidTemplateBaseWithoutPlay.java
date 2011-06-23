/**
 * Copyright 2010 Bing Ran<bing_ran@hotmail.com> 
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not 
 * use this file except in compliance with the License. You may obtain a copy 
 * of the License at http://www.apache.org/licenses/LICENSE-2.0.
 * 
 * Unless required by applicable law or agreed to in writing, software 
 * distributed under the License is distributed on an "AS IS" BASIS, 
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. 
 * See the License for the specific language governing permissions and 
 * limitations under the License. 
 */
package cn.bran.japid.template;

import java.io.IOException;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import cn.bran.japid.compiler.NamedArg;
import cn.bran.japid.compiler.NamedArgRuntime;
import cn.bran.japid.util.HTMLUtils;
import cn.bran.japid.util.WebUtils;

/**
 * a java based template suing StringBuilder as the content buffer, no play
 * dependency.
 * 
 * @author bran
 * 
 */
public abstract class JapidTemplateBaseWithoutPlay implements Serializable {

	private StringBuilder out;
	private Map<String, String> headers = new TreeMap<String, String>();
	{
		headers.put("Content-Type", "text/html; charset=utf-8");
	}

	public void setOut(StringBuilder out) {
		this.out = out;
	}

	protected StringBuilder getOut() {
		return out;
	}

	// public JapidTemplateBase() {
	//
	// };

	protected void putHeader(String k, String v) {
		headers.put(k, v);
	}
	
	protected Map<String, String> getHeaders(){
		return this.headers;
	}

	public JapidTemplateBaseWithoutPlay(StringBuilder out2) {
		this.out = out2 == null ? new StringBuilder(4000) : out2;
	}

	// don't use it since it will lead to new instance of stringencoder
	// Charset UTF8 = Charset.forName("UTF-8");

	final protected void p(String s) {
		writeString(s);
	}

	final protected void pln(String s) {
		writeString(s);
		out.append('\n');
	}

	/**
	 * @param s
	 * @throws IOException
	 * @throws UnsupportedEncodingException
	 */
	final private void writeString(String s) {
		// ByteBuffer bb = StringUtils.encodeUTF8(s);
		// out.write(bb.array(), 0, bb.position());
		// ok my code is slower in large trunk of data
		if (s != null && !s.isEmpty())
			out.append(s);
	}

	// final protected void pln(byte[] ba) {
	// try {
	// out.write(ba);
	// out.write('\n');
	// } catch (IOException e) {
	// throw new RuntimeException(e);
	// }
	// }

	final protected void p(Object s) {
		if (s != null) {
			writeString(s.toString());
			// out.append(s);
		}
	}

	final protected void pln(Object s) {
		if (s != null)
			writeString(s.toString());
		pln();
	}

	final protected void pln() {
		out.append('\n');
	}

	/**
	 * The template pattern to implement the template/layout relationship.
	 * Clients call a template's render(), which store params in fields and
	 * calls in super class's layout, which does the whole page layout and calls
	 * back child's doLayout to get the child content.
	 */
	protected void layout() {
		doLayout();
	}

	protected abstract void doLayout();

	static protected byte[] getBytes(String src) {
		if (src == null || src.length() == 0)
			return new byte[] {};

		try {
			return src.getBytes("UTF-8");
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public String toString() {
		return this.out.toString();
	}

	/**
	 * reflect this object for a method of the name
	 * 
	 * @param methodName
	 * @return
	 */
	protected String get(String methodName, String defaultVal) {
		try {
			Method method = this.getClass().getMethod(methodName, (Class[]) null);
			String invoke = (String) method.invoke(this, (Object[]) null);
			return invoke;
		} catch (Exception e) {
			return defaultVal;
		}
	}

	/**
	 * reflect this object for a method of the name
	 * 
	 * @param methodName
	 * @return
	 */
	protected String get(String methodName) {
		try {
			Method method = this.getClass().getMethod(methodName, (Class[]) null);
			String invoke = (String) method.invoke(this, (Object[]) null);
			return invoke;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	protected boolean asBoolean(Object o) {
		return WebUtils.asBoolean(o);
	}

	/**
	 * escape the string representation of the object to make it HTML safe.
	 * 
	 * @param o
	 * @return
	 */
	public static String escape(Object o) {
		if (o == null)
			return null;
		return HTMLUtils.htmlEscape(o.toString());
	}

	/**
	 * @param currentClass
	 */
	public static Method getRenderMethod(Class<? extends JapidTemplateBaseWithoutPlay> currentClass) {
		java.lang.reflect.Method[] methods = currentClass.getDeclaredMethods();

		for (java.lang.reflect.Method m : methods) {
			if (m.getName().equals("render")) {
				Class<?>[] parameterTypes = m.getParameterTypes();
				if (parameterTypes.length == 1) {
					Class<?> t = parameterTypes[0];
					if (t != NamedArgRuntime[].class) {
						return m;
					}
				} else {
					boolean hasNamedArg = false;
					for (Class<?> c : parameterTypes) {
						if (c == NamedArgRuntime.class || c == NamedArgRuntime[].class) {
							hasNamedArg = true;
							break;
						}
					}
					if (!hasNamedArg)
						return m;
				}
			}
		}
		throw new RuntimeException("no render method found for the template: " + currentClass.getCanonicalName());
	}

	/*
	 * based on https://github.com/branaway/Japid/issues/12 This static mapping
	 * will be later user in method renderModel to construct an proper Object[]
	 * arraywhich is needed to invoke the method render(Object... args) over
	 * reflection.
	 */

	public java.lang.reflect.Method renderMethodInstance;

	protected void setRenderMethod(Method renderMethod) {
		// System.out.println("-> setrender name: " + renderMethod);
		renderMethodInstance = renderMethod;
	}

	public String[] argNamesInstance = null;

	protected void setArgNames(String[] argNames) {
		// System.out.println("-> set args names: " + argNames);
		this.argNamesInstance = argNames;
	}

	public String[] argTypesInstance = null;

	protected void setArgTypes(String[] argTypes) {
		// System.out.println("-> set args names: " + argNames);
		this.argTypesInstance = argTypes;
	}

	public Object[] argDefaultsInstance = null;

	protected void setArgDefaults(Object[] argDefaults) {
		// System.out.println("-> set args names: " + argNames);
		this.argDefaultsInstance = argDefaults;
	}

	// public cn.bran.japid.template.RenderResult
	// renderModel(cn.bran.japid.template.JapidModelMap model) {
	// // a static utils method of JapidModelMap to build up an Object[] array.
	// Nulls are used where the args are omitted.
	// Object[] args = model.buildArgs(argNamesInstance);
	// try {
	// return (cn.bran.japid.template.RenderResult )
	// renderMethodInstance.invoke(this, args);
	// } catch (IllegalArgumentException e) {
	// throw new RuntimeException(e);
	// } catch (IllegalAccessException e) {
	// throw new RuntimeException(e);
	// } catch (InvocationTargetException e) {
	// Throwable t = e.getTargetException();
	// throw new RuntimeException(t);
	// }
	// }
	//
	protected static NamedArgRuntime named(String name, Object val) {
		return new NamedArgRuntime(name, val);
	}

	public cn.bran.japid.template.RenderResult render(NamedArgRuntime... named) {
		// a static utils method of JapidModelMap to build up an Object[] array.
		// Nulls are used where the args are omitted.
		Object[] args = buildArgs(named);
		return runRenderer(args);
	}

	/**
	 * @param args
	 * @return
	 */
	protected cn.bran.japid.template.RenderResult runRenderer(Object[] args) {
		try {
			return (cn.bran.japid.template.RenderResult) renderMethodInstance.invoke(this, args);
		} catch (IllegalArgumentException e) {
			throw new RuntimeException(e);
		} catch (IllegalAccessException e) {
			throw new RuntimeException(e);
		} catch (InvocationTargetException e) {
			Throwable t = e.getTargetException();
			throw new RuntimeException(t);
		}
	}

	/**
	 * build
	 * 
	 * @param argNames
	 * @param namedArgs
	 * @return
	 */
	public Object[] buildArgs(NamedArgRuntime[] namedArgs) {
		Map<String, Object> map = new HashMap<String, Object>();
		for (NamedArgRuntime na : namedArgs) {
			map.put(na.name, na.val);
		}

		Object[] ret = new Object[argNamesInstance.length];

		for (int i = 0; i < argNamesInstance.length; i++) {
			String name = argNamesInstance[i];
			if (map.containsKey(name)) {
				ret[i] = map.remove(name);
			} else {
				// any default set?
				Object defa = this.argDefaultsInstance[i];
				if (defa != null)
					ret[i] = defa;
				else {
					// set default value for primitives and Strings, or null for
					// complex object
					String type = argTypesInstance[i];
					Object defaultVal = getDefaultValForType(type);
					ret[i] = defaultVal;
				}
			}
		}
		if (map.size() > 0) {
			Set<String> keys = map.keySet();
			String ks = "";
			for (String k : keys) {
				ks += k + " ";
			}
			String vs = "[";
			for (String n : argNamesInstance) {
				vs += n + " ";
			}
			vs += "]";
			throw new RuntimeException("One or more argument names are not valid: " + ks + ". Valid argument names are: " + vs);
		}
		return ret;
	}

	protected Object[] buildArgs(NamedArgRuntime[] named, Object body) {
		Object[] obsNoBody = buildArgs(named);
		int len = obsNoBody.length;
		Object[] ret = new Object[len + 1];
		System.arraycopy(obsNoBody, 0, ret, 0, len);
		ret[len] = body;
		return ret;
	}

	private Object getDefaultValForType(String type) {
		if (type.equals("String"))
			return "";
		else if (/* type.equals("Boolean") || */type.equals("boolean"))
			return false;
		else if (type.equals("char") /* || type.equals("Character") */)
			return (char) 0;
		else if (type.equals("byte") /* || type.equals("Byte") */)
			return (byte) 0;
		else if (type.equals("short") /* || type.equals("Short") */)
			return (short) 0;
		else if (type.equals("int") /* || type.equals("Integer") */)
			return 0;
		else if (type.equals("float") /* || type.equals("Float") */)
			return 0f;
		else if (type.equals("long") /* || type.equals("Long") */)
			return 0L;
		else if (type.equals("double") /* || type.equals("Double") */)
			return 0d;

		return null;
	}
}
