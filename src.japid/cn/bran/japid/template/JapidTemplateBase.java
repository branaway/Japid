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
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Method;
import java.nio.charset.Charset;
import java.util.TreeMap;
import java.util.Map;
import java.util.TreeMap;

/**
 * a java based template suing StringBuilder as the content buffer
 * 
 * @author bran
 * 
 */
public abstract class JapidTemplateBase {
//	private static final String UTF_8 = "UTF-8";

	StringBuilder out;
	protected Map<String, String> headers = new TreeMap<String, String>();
	{
		headers.put("Content-Type", "text/html; charset=utf-8");
	}

	/**
	 * to keep track of all the action invocations by #{invoke} tag
	 */
	protected TreeMap<Integer, cn.bran.japid.template.ActionRunner> actionRunners = new TreeMap<Integer, cn.bran.japid.template.ActionRunner>();

	public TreeMap<Integer, cn.bran.japid.template.ActionRunner> getActionRunners() {
		return actionRunners;
	}

	public void setActionRunners(TreeMap<Integer, cn.bran.japid.template.ActionRunner> actionRunners) {
		this.actionRunners = actionRunners;
	}

	public void setOut(StringBuilder out) {
		this.out = out;
	}

	protected StringBuilder getOut() {
		return out;
	}
	
//	public JapidTemplateBase() {
//		
//	};

	public JapidTemplateBase(StringBuilder out2) {
		this.out = out2 == null? new StringBuilder(4000) : out2;
	}

	// don't use it since it will lead to new instance of stringencoder
	Charset UTF8 = Charset.forName("UTF-8");

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
	 * The template pattern to implement the template/layout relationship. Clients call a template's 
	 * render(), which store params in fields and calls in super class's layout, which does the whole page
	 * layout and calls back child's doLayout to get the child content.
	 */
	protected void layout() {
		doLayout();
	}

	protected abstract void doLayout();

	// protected abstract void doLayout();
	//
	// /**
	// * not used for now. can be used to invoke a template dynamically
	// * @param post
	// * @param body
	// */
	// protected static void doBody(BranTemplateBase body, Object... args) {
	// // must use reflection now
	// Class<? extends BranTemplateBase> bodyClass = body.getClass();
	// Method[] methods = bodyClass.getMethods();
	// List<Method> renders = new ArrayList<Method>();
	// for (Method m : methods) {
	// if (m.getName().equals("render")) {
	// renders.add(m);
	// }
	// }
	// if (renders.size() == 0) {
	// throw new
	// RuntimeException("the template class does not have a render method: " +
	// bodyClass.getName());
	// }
	// else if (renders.size() > 1 ) {
	// throw new
	// RuntimeException("the template class has more than one render method: " +
	// bodyClass.getName());
	// }
	// else {
	// Method render = renders.get(0);
	// try {
	// render.invoke(body, args);
	// } catch (Exception e) {
	// throw new RuntimeException("error run the render method on: " +
	// bodyClass.getName());
	// }
	// }
	// }

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
	 * @param methodName
	 * @return
	 */
	protected String get(String methodName, String defaultVal)  {
		try {
			Method method = this.getClass().getMethod(methodName, (Class[])null);
			String invoke = (String)method.invoke(this, (Object[])null);
			return invoke;
		} catch (Exception e) {
			return defaultVal;
		}
	}

	/**
	 * reflect this object for a method of the name
	 * @param methodName
	 * @return
	 */
	protected String get(String methodName)  {
		try {
			Method method = this.getClass().getMethod(methodName, (Class[])null);
			String invoke = (String)method.invoke(this, (Object[])null);
			return invoke;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
