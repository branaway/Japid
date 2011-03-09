package cn.bran.japid.rendererloader;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.Modifier;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import cn.bran.japid.template.JapidRenderer;
import cn.bran.japid.template.JapidTemplateBaseWithoutPlay;
import cn.bran.japid.template.RenderResult;
import cn.bran.japid.util.RenderInvokerUtils;

/**
 * The template class loader that detects changes and recompile on the fly. 
 * 
 * 1. whenever changes detected, clear the global class cache. 
 * 2. Only redefine the class to load, which will lead to define all the dependencies. All the dependencies must be defined by the same
 * class classloader or InvalidAccessException.
 * 3. The main program will call the loadClass once for each of the classes defined in one classloader.  
 * 
 * 
 * @author Bing Ran<bing_ran@hotmail.com>
 * 
 */
public class TemplateClassLoader extends ClassLoader {
	// the per classloader class cache
	private Map<String, Class<?>> localClasses = new ConcurrentHashMap<String, Class<?>>();

	public TemplateClassLoader() {
		super(TemplateClassLoader.class.getClassLoader());
	}

	@Override
	public Class<?> loadClass(String name) throws ClassNotFoundException {
		if (!name.startsWith(JapidRenderer.JAPIDVIEWS))
			return super.loadClass(name);
		String oid = "[TemplateClassLoader@" + Integer.toHexString(hashCode()) + "]";

		Class<?> cla = localClasses.get(name);
		if (cla != null) {
			System.out.println(oid + " loaded from local cache : " + name);
			return cla;
		}
			
		// System.out.println("[TemplateClassLoader] loading: " + name);
		RendererClass rc = JapidRenderer.classes.get(name);

		byte[] bytecode = rc.bytecode;

		if (bytecode == null) {
			throw new RuntimeException(oid + " could not find the bytecode for: " + name);
		}

		// the defineClass method will load the classes of the dependency
		// classes.
		Class<? extends JapidTemplateBaseWithoutPlay> cl =
					(Class<? extends JapidTemplateBaseWithoutPlay>) defineClass(name, bytecode, 0, bytecode.length);
		rc.setClz(cl);
		localClasses.put(name, cl);
		rc.lastUpdated = 1;// System.currentTimeMillis();
		System.out.println(oid + " reloaded from bytecode: " + name);
		return cl;

	}

	/**
	 * Search for the byte code of the given class.
	 */
	protected byte[] getClassDefinition(String name) {
		name = name.replace(".", "/") + ".class";
		InputStream is = getResourceAsStream(name);
		if (is == null) {
			return null;
		}
		try {
			ByteArrayOutputStream os = new ByteArrayOutputStream();
			byte[] buffer = new byte[8192];
			int count;
			while ((count = is.read(buffer, 0, buffer.length)) > 0) {
				os.write(buffer, 0, count);
			}
			return os.toByteArray();
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			try {
				is.close();
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}
	}
}