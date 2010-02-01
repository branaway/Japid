package bran.japid;

import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Method;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * a java based template
 * 
 * @author bran
 * 
 */
public abstract class BranTemplateBase {
	private static final String UTF_8 = "UTF-8";
	protected Map<String, Object> flash;
	protected String lang;
//	public PrintWriter getOut() {
//		return out;
//	}
//
//	public void setOut(PrintWriter out) {
//		this.out = out;
//	}

//	private PrintWriter out;
	private OutputStream out;
	protected OutputStream getOut() {
		return out;
	}
//
//
//	public void setOut(OutputStream out) {
//		this.out = out;
//	}

	// bran: another alternative is to use static import in generated java source
	public static MessageProvider messageProvider;
	public static UrlMapper urlMapper;

	
	public BranTemplateBase(OutputStream out2) {
		this.out = out2;
		flash = new HashMap<String, Object>();
	}
//
//	private BranTemplateBase() {
//	}
	

	// call this if run in PlayContainer
	public void runtimeInit() {
		// scope = Scope.
	}

	// don't use it since it will lead to new instance of stringencoder
	Charset UTF8 = Charset.forName("UTF-8");


	final protected void p(byte[] ba) {
		try {
			out.write(ba);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
	
	final protected void p(String s) {
		try {
			writeString(s);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
	
	final protected void pln(String s) {
		try {
			writeString(s);
			out.write('\n');
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}


	/**
	 * @param s
	 * @throws IOException
	 * @throws UnsupportedEncodingException
	 */
	private void writeString(String s) throws IOException, UnsupportedEncodingException {
//		ByteBuffer bb = StringUtils.encodeUTF8(s);
//		out.write(bb.array(), 0, bb.position());
		// ok my code is slower in large trunk of data
		out.write(s.getBytes("UTF-8"));
	}
	
	final protected void pln(byte[] ba) {
		try {
			out.write(ba);
			out.write('\n');
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
	
	final protected void p(Object... ss) {
		for (Object s : ss) {
			if (s != null) {
				writeObject(s);
//				out.append(s);
			}
		}
	}
	/**
	 * @param s
	 */
	private void writeObject(Object s) {
		try {
			if (s instanceof byte[]) {
				out.write((byte[])s);
			}
			else {
				writeString(s.toString());
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	final protected void pln(Object... ss) {
		for (Object s : ss) {
			if (s != null)
				writeObject(s);
		}
		pln();
	}

	final protected void pln() {
		try {
			out.write('\n');
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
	
	protected void layout() {
		doLayout();
	}
	protected abstract void doLayout();
	
//	final private boolean asBoolean(Object o) {
//		boolean r = false;
//
//		if (o instanceof Boolean) {
//			r = (Boolean) o;
//		} else if (o instanceof Integer) {
//			Integer n = (Integer) o;
//			r = n != 0 ? true : false;
//		}
//		// etc...
//		// should really consult DefaultGroovyMethods.java for all the coercion
//		// methods
//		else {
//			r = o != null ? true : false;
//		}
//
//		return r;
//	}

//	protected abstract void doLayout();

	/**
	 * not used for now. can be used to invoke a template dynamically 
	 * @param post
	 * @param body
	 */
//	protected static void doBody(BranTemplateBase body, Object... args) {
//		// must use reflection now
//		Class<? extends BranTemplateBase> bodyClass = body.getClass();
//		Method[] methods = bodyClass.getMethods();
//		List<Method> renders = new ArrayList<Method>();
//		for (Method m :  methods) {
//			if (m.getName().equals("render")) {
//				renders.add(m);
//			}
//		}
//		if (renders.size() == 0) {
//			throw new RuntimeException("the template class does not have a render method: " + bodyClass.getName());
//		}
//		else if (renders.size() > 1 ) {
//			throw new RuntimeException("the template class has more than one render method: " + bodyClass.getName());
//		}
//		else {
//			Method render = renders.get(0);
//			try {
//				render.invoke(body, args);
//			} catch (Exception e) {
//				throw new RuntimeException("error run the render method on: " + bodyClass.getName());
//			}
//		}
//	}

	protected static String getMessage(String msgName, Object... params) {
		return messageProvider.getMessage(msgName, params);
	}
//	
	static protected byte[] getBytes(String src) {
		if (src == null || src.length() == 0)
			return new byte[] {};
		
		try {
			return src.getBytes("UTF-8");
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * map a action to a url
	 * 
	 * @param action the controller.action part of the whole string
	 * @param args: the argument list, each thereof is a result of an expression evaluation
	 * 		meaning the name of the param is not kept
	 * @return
	 */
	protected static String lookup(String action, Object... args) {
		return urlMapper.lookup(action, args);
	}

	/**
	 * map an action to an absolute url
	 * 
	 * @param action the controller.action part of the whole string
	 * @param args: the argument list, each thereof is a result of an expression evaluation
	 * 		meaning the name of the param is not kept
	 * 
	 * @param action
	 * @return
	 */
	protected static String lookupAbs(String action, Object... args) {
		return urlMapper.lookupAbs(action, args);
	}
	
}
