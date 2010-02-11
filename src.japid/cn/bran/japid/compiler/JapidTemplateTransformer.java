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
package cn.bran.japid.compiler;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.annotation.Annotation;

import cn.bran.japid.classmeta.AbstractTemplateClassMetaData;
import cn.bran.japid.template.JapidTemplate;

/**
 * compile html based template to java files
 * 
 * The facade to all the compiler suite and configurations.
 * 
 * @author Bing Ran<bing_ran@hotmail.com>
 * 
 */
public class JapidTemplateTransformer {

	private static final String HTML = ".html";
	private String sourceFolder;
	private String targetFolder;

	// private MessageProvider messageProvider;
	// private UrlMapper urlMapper;

	/**
	 * @param sourceFolder
	 *            the folder containing the template source tree. It's the same
	 *            concept as in eclipse. The root package for Java artifacts are
	 *            the direct children of this
	 * @param targetFolder
	 *            the "source folder", in Eclipse term, that the generated Java
	 *            artifacts will be placed. If it is null, the Java files will
	 *            be placed in the sourceFolder.
	 */
	public JapidTemplateTransformer(String sourceFolder, String targetFolder) {
		super();

		this.sourceFolder = sourceFolder;
		// this.messageProvider = messageProvider;
		// this.urlMapper = urlMapper;
		this.targetFolder = targetFolder;
		//		
		// BranTemplateBase.messageProvider = messageProvider;
		// BranTemplateBase.urlMapper = urlMapper;
	}

	/**
	 * 
	 * @param importLine
	 *            add an import to all the files generated. For examples:
	 *            "my.package.*", "my.package.MyClass"
	 */
	public void addImportLine(String importLine) {
		AbstractTemplateClassMetaData.addImportLineGlobal(importLine);
	}

	/**
	 * effectively as in Java: "import static my.Tools.*;" if Tools.class is the
	 * parameter.
	 * 
	 * @param class1
	 */
	public void addImportStatic(Class<?> class1) {
		AbstractTemplateClassMetaData.addImportStatic(class1);
	}

	/**
	 * 
	 * @param fileName
	 *            the relative path of the template file from the source folder,
	 *            e.g., "my/path/mytemplate.html", which maps to
	 *            my.path.mytemplate.java
	 * @return
	 * @throws Exception
	 */
	public File generate(String fileName) throws Exception {
		String realSrcFile = sourceFolder == null ? fileName : sourceFolder + "/" + fileName;
		String src = readFileAsString(realSrcFile);
		JapidTemplate temp = new JapidTemplate(fileName, src);
		JapidAbstractCompiler c = null;
		if (src.indexOf("#{doLayout") >= 0 || src.indexOf("#{get") >= 0) {
			c = new JapidLayoutCompiler();
		} else {
			// regular template and tag are the same thing
			c = new JapidTemplateCompiler();
		}
		c.compile(temp);
		String jsrc = temp.javaSource;

		String fileNameRoot = fileName;
		if (fileName.endsWith(HTML)) {
			fileNameRoot = fileName.substring(0, fileName.indexOf(HTML));
		}

		String target = targetFolder == null ? sourceFolder : targetFolder;
		String realTargetFile = target == null ? fileNameRoot + ".java" : target + "/" + fileNameRoot + ".java";
		File f = new File(realTargetFile);
		if (!f.exists()) {
			String parent = f.getParent();
			new File(parent).mkdirs();
		}
		BufferedOutputStream bf = new BufferedOutputStream(new FileOutputStream(f));
		bf.write(jsrc.getBytes("UTF-8"));
		bf.close();
		return f;

	}

	// this method is entirely safe ???
	private String readFileAsString(String filePath) throws Exception {
		// let're remove dependency on commons IO
//		return IOUtils.toString(new FileInputStream(filePath), "UTF-8");
		byte[] buffer = new byte[(int) new File(filePath).length()];
		BufferedInputStream f = new BufferedInputStream(new FileInputStream(filePath));
		// not sure if this is always safe assume it'll read all bytes in
		f.read(buffer);
		f.close();
		return new String(buffer, "UTF-8");

	}

	/**
	 * a utility method. Should be somewhere else.
	 * 
	 * @param srcDir
	 * @param cf
	 * @throws IOException
	 */
	public static String getRelativePath(File child, File parent) throws IOException {
		String curPath = parent.getCanonicalPath();
		String childPath = child.getCanonicalPath();
		assert (childPath.startsWith(curPath));
		String srcRelative = childPath.substring(curPath.length());
		if (srcRelative.startsWith(File.separator)) {
			srcRelative = srcRelative.substring(File.separator.length());
		}
		return srcRelative;
	}

	/**
	 * transform a source template to Java
	 * 
	 * @param file
	 *            the source file that must be a decendant of the source folder.
	 * @return
	 * @throws Exception
	 */
	public File generate(File file) throws Exception {
		String rela = getRelativePath(file, new File("."));
		return generate(rela);
	}

	/**
	 * add class level annotation for whatever purpose
	 * 
	 * @param anno
	 */
	public void addAnnotation(Class<? extends Annotation> anno) {
		AbstractTemplateClassMetaData.addAnnotation(anno);
		// typeAnnotations.add(anno);
	}

	// List<Class<? extends Annotation>> typeAnnotations = new ArrayList<Class<?
	// extends Annotation>>();
}
