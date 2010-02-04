package bran.japid;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.List;

/**
 * compile html based template to java files
 * 
 *  The facade to all the compiler suite and configurations.
 * 
 * @author bran
 *
 */
public class BranTemplateTranslator {

	private static final String HTML = ".html";
	private String sourceFolder;
	private String targetFolder;

//	private MessageProvider messageProvider;
//	private UrlMapper urlMapper;


	public BranTemplateTranslator(String sourceFolder, String targetFolder) {
		super();
		this.sourceFolder = sourceFolder;
//		this.messageProvider = messageProvider;
//		this.urlMapper = urlMapper;
		this.targetFolder = targetFolder;
//		
//		BranTemplateBase.messageProvider = messageProvider;
//		BranTemplateBase.urlMapper = urlMapper;
	}

	public void addImportLine(String importLine) {
		AbstractTemplateClassMetaData.addImportLine(importLine);
	}

	public void addImportStatic(Class<?> class1) {
		AbstractTemplateClassMetaData.addImportStatic(class1);
	}

	
	/**
	 * 
	 * @param fileName the relative path of the template file from the source folder
	 * @return
	 * @throws Exception
	 */
	public File generate(String fileName) throws Exception {
		String realSrcFile = sourceFolder == null? fileName : sourceFolder + "/" + fileName;
		String src = readFileAsString(realSrcFile);
		BranTemplate temp = new BranTemplate(fileName, src);
		AbstractCompiler c = null;
		if (src.indexOf("#{doLayout") >= 0 || src.indexOf("#{get") >= 0) {
			c = new BranLayoutCompiler();
		}
		else {
			// regular template and tag are the same thing
			c = new BranTemplateCompiler();
		}
		c.compile(temp);
		String jsrc = temp.javaSource;
		
		String fileNameRoot = fileName;
		if (fileName.endsWith(HTML)) {
			fileNameRoot = fileName.substring(0, fileName.indexOf(HTML));
		}
		
		String target = targetFolder == null? sourceFolder : targetFolder;
		String realTargetFile = target == null? fileNameRoot + ".java" : target + "/" + fileNameRoot + ".java";
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

	// this method is entirely safe
	private String readFileAsString(String filePath) throws Exception {
		byte[] buffer = new byte[(int) new File(filePath).length()];
		BufferedInputStream f = new BufferedInputStream(new FileInputStream(filePath));
		f.read(buffer);
		return new String(buffer, "UTF-8");

	}

//	// this method is entirely safe
//	private File writeStringToFile(String src) throws Exception {
//		File f = new File(targetFolder);
////		if (f.exists()) {
////			f.createNewFile();
////		}
//		BufferedOutputStream bf = new BufferedOutputStream(new FileOutputStream(f));
//		bf.write(src.getBytes("UTF-8"));
//		bf.close();
//		return f;
//			
//	}
//	
//	public void setTargetFolder(String target) {
//		this.targetFolder = target;
//	}
//	
//	public void setSourceFolder(String string) {
//		this.sourceFolder = string;
//	}

//	/**
//	 * compile a template to java in srcFolder/fileName. 
//	 * 
//	 * @param srcFolder the source folder name and won't become part of the java package
//	 * @param fileName can have a relative path and a file name. The path part becomes  the package name
//	 * @return
//	 * @throws Exception
//	 */
//	public File generate(String srcFolder, String fileName) throws Exception {
//		this.setSourceFolder(srcFolder);
//		return this.generate(fileName);
//	}
//
//	public File generate(File srcDir, File file) throws Exception {
//		String relativeFile = getRelativePath(file, srcDir);
//		String srcRelative = getRelativePath(srcDir, new File("."));
//		
//	    return generate(srcRelative, relativeFile);//	}

	/**
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
			srcRelative  = srcRelative.substring(File.separator.length());
		}
		return srcRelative;
	}

	public File generate(File file) throws Exception {
		String rela = getRelativePath(file, new File("."));
		return generate(rela);
	}

	/**
	 * add class level annotation
	 * @param anno
	 */
	public void addAnnotation(Class<? extends Annotation> anno) {
		AbstractTemplateClassMetaData.addAnnotation(anno);
//		typeAnnotations.add(anno);
	}

//	List<Class<? extends Annotation>> typeAnnotations =  new ArrayList<Class<? extends Annotation>>();
}
