package cn.bran.japid.template;

import groovy.lang.Closure;

import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import play.templates.Template.ExecutableTemplate.RawData;

/**
 * a java based template
 * 
 * @author bran
 * 
 */
public abstract class JapidTemplateBase {
	private static final String UTF_8 = "UTF-8";

	StringBuilder out;

	public void setOut(StringBuilder out) {
		this.out = out;
	}

	protected StringBuilder getOut() {
		return out;
	}
	
	public JapidTemplateBase() {};

	public JapidTemplateBase(StringBuilder out2) {
		this.out = out2 == null? new StringBuilder(8000) : out2;
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
	private void writeString(String s) {
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
}
