package cn.bran.play.rendererloader;

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

import cn.bran.japid.rendererloader.RendererClass;
import cn.bran.japid.rendererloader.TemplateClassLoader;
import cn.bran.japid.template.JapidRenderer;
import cn.bran.japid.template.JapidTemplateBaseWithoutPlay;
import cn.bran.japid.template.RenderResult;
import cn.bran.japid.util.JapidFlags;
import cn.bran.japid.util.RenderInvokerUtils;
import cn.bran.play.JapidPlayRenderer;

/**
 * The template class loader that detects changes and recompile on the fly and use
 * play's class loader to load classes referenced in the templates, such as the model classes, 
 * controllers, etc. 
 * 
 * 1. whenever changes detected, clear the global class cache. 
 * 2. Only redefine the class to load, which will lead to define all the dependencies. All the dependencies must be defined by the same
 * class classloader or InvalidAccessException.
 * 3. The main program will call the loadClass once for each of the classes defined in one classloader.  
 * 
 * 
 * @author Bing Ran<bing.ran@gmail.com>
 * 
 */
public class TemplateClassLoaderWithPlay extends TemplateClassLoader {


	public TemplateClassLoaderWithPlay() {
		super(TemplateClassLoaderWithPlay.class.getClassLoader());
	}

	@Override
	protected ClassLoader getParentClassLoader() {
		return play.Play.classloader;
	}
	
	@Override
	protected RendererClass getJapidRendererClassWrapper(String name) {
		return JapidPlayRenderer.japidClasses.get(name);
	}

}