package cn.bran.japid.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.text.CharacterIterator;
import java.text.StringCharacterIterator;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class DirUtil {
	public static Set<File> findOrphanJava(File src, File target) {
		if (target == null)
			target = src;
		String[] allSrc = getAllFileNames(src, new String[] { ".java", ".html", ".txt", ".json", ".xml" });
		Set<String> javas = new HashSet<String>();
		Set<String> srcFiles = new HashSet<String>();

		for (String s : allSrc) {
			if (s.endsWith(".java")) {
				javas.add(s);
			} else /*if (s.endsWith(".html"))*/ {
				srcFiles.add(mapSrcToJava(s));
			}
		}

		javas.removeAll(srcFiles);
		Set<File> re = new HashSet<File>();
		for (String j : javas) {
			re.add(new File(j));
		}
		return re;
	}

	
	/**
	 * 
	 */
	public static String[] getAllFileNames(File dir, String[] exts) {
		List<String> files = new ArrayList<String>();
		getAllFileNames("", dir, files, exts);
		String[] ret = new String[files.size()];
		return files.toArray(ret);
	}
	
	public static String[] getAllTemplateHtmlFiles(File dir) {
		List<String> files = new ArrayList<String>();
		getAllFileNames("", dir, files, new String[] {".html"});
		// should filter out bad named files
		String[] ret = new String[files.size()];
		return files.toArray(ret);
	}
	
	

	/**
	 * collect all files with one of the extensions from the directory. 
	 * @param dir
	 * @param exts
	 * @param fs
	 * @return the files match. Note the files path starts with the source dir.
	 */
	public static Set<File> getAllFiles(File dir, String[] exts, Set<File> fs) {
		Set<File> scanFiles = scanFiles(dir, exts, fs);
		return scanFiles;
	}

	/**
	 * @param dir
	 * @param exts
	 * @param fs
	 * @return
	 */
	private static Set<File> scanFiles(File dir, String[] exts, Set<File> fs) {
		File[] flist = dir.listFiles();
		for (File f : flist) {
			if (f.isDirectory())
				getAllFiles(f, exts, fs);
			else {
				if (match(f, exts))
					fs.add(f);
			}
		}
		return fs;
	}
	
	private static void getAllFileNames(String leadingPath, File dir, List<String> files, String[] exts) {
		File[] flist = dir.listFiles();
		if (flist == null)
			throw new RuntimeException("directory exists? " +  dir.getPath());
		for (File f : flist) {
			if (f.isDirectory())
				getAllFileNames(leadingPath + f.getName() + File.separatorChar, f, files, exts);
			else {
				if (match(f, exts))
					files.add(leadingPath + f.getName());
			}
		}
	}

	static boolean match(File f, String[] exts) {
		for (String ext : exts) {
			if (f.getName().endsWith(ext))
				if (fileNameIsValidClassName(f))
					return true;
		}
		return false;
	}

	public static List<File> findChangedSrcFiles(File srcDir) {
//		if (target == null)
//			target = src;
//		String srcPath = src.getPath();
		Set<File> allSrc  = new HashSet<File>();
		allSrc = getAllFiles(srcDir, new String[] { ".java", ".html", ".txt", ".json", ".xml", ".js" }, allSrc);
		Map<String, Long> javas = new HashMap<String, Long>();
		Map<String, Long> srcFiles = new HashMap<String, Long>();
		

		for (File f : allSrc) {
			String path = f.getPath();
			long modi = f.lastModified();
//			System.out.println("file: " + path + ":" + modi);
			if (path.endsWith(".java")) {
				javas.put(path, modi);
			} else  {
				// validate file name to filter out dubious files such as temporary files
				if (fileNameIsValidClassName(f))
					srcFiles.put(path, modi);
			}
		}

		List<File> rs = new ArrayList<File>();
		
		for (String src : srcFiles.keySet()) {
			String javak = mapSrcToJava(src);
//			System.out.println("mapped key: " + javak);
			Long t = javas.get(javak);
			if (t == null) {
//				System.out.println("new file: " + src);
				rs.add(new File(src));
			}
			else {
				Long srcStamp = srcFiles.get(src);
//				System.out.println("src stamp:" + srcStamp);
//				System.out.println("java stamp:" + t);
				if (srcStamp.compareTo(t) > 0) {
					rs.add(new File(src));
				}
			}
		}
		return rs;
	}

	/**
	 * @author Bing Ran (bing.ran@hotmail.com)
	 * @param f
	 * @return
	 */
	private static boolean fileNameIsValidClassName(File f) {
		String fname = f.getName();
		if (fname.startsWith("."))
			return false;
		fname = fname.substring(0, fname.lastIndexOf(".")).replace('.', '_');
		return isClassname(fname);
	}

	/**
	 * map template source file name to the generated java file name
	 * @param k
	 * @return
	 */
	public static String mapSrcToJava(String k) {
		if (k.endsWith(".txt")) {
			return getRoot(k) + "_txt" + ".java";
		}
		else if (k.endsWith(".xml")) {
			return getRoot(k) + "_xml" + ".java";
		}
		else if (k.endsWith(".json")) {
			return getRoot(k) + "_json" + ".java";
		}
		else if (k.endsWith(".css")) {
			return getRoot(k) + "_css" + ".java";
		}
		else if (k.endsWith(".js")) {
			return getRoot(k) + "_js" + ".java";
		}
		else { // including html
			return getRoot(k) + ".java";
		}
	}
	
	/**
	 * 
	 * map java source file name to the template file name
	 * @param k
	 * @return
	 */
	public static String mapJavaToSrc(String k) {
		if (k.endsWith(".java"))
			k = k.substring(0, k.lastIndexOf(".java"));
		
		if (k.endsWith("_txt")) {
			return k.substring(0, k.lastIndexOf("_txt")) + ".txt";
		}
		else if (k.endsWith("_xml")) {
			return k.substring(0, k.lastIndexOf("_xml")) + ".xml";
		}
		else if (k.endsWith("_json")) {
			return k.substring(0, k.lastIndexOf("_json")) + ".json";
		}
		else if (k.endsWith("_css")) {
			return k.substring(0, k.lastIndexOf("_css")) + ".css";
		}
		else if (k.endsWith("_js")) {
			return k.substring(0, k.lastIndexOf("_js")) + ".js";
		}
		else { // including html
			return  k + ".html";
		}
	}
 
	private static String getRoot(String k) {
		int indexOf = k.lastIndexOf(".");
		if (indexOf > 0) {
			return k.substring(0, indexOf);
		}
		return k;
	}

	public static void writeStringToFile(File file, String content) throws IOException {
		Writer fw = new OutputStreamWriter(new FileOutputStream(file), "UTF-8");
		BufferedWriter bw = new BufferedWriter(fw);
		bw.append(content);
		bw.close();
	}

	public static void touch(File newer) throws IOException {
		writeStringToFile(newer, "");
	}

	public static boolean containsTemplateFiles(String root, String dirName) {
		String sep = File.separator;
		String japidViews = root + sep + JAPIDVIEWS_ROOT + sep;
		String dir = japidViews + dirName;
		return containsTemplatesInDir(dir);
	}

	public static boolean containsTemplatesInDir(String dirName) {
		File dir = new File(dirName);
		
		if (dir.exists()) {
			String[] temps = getAllFileNames(dir, new String[]{".html", ".js", ".txt", ".css", ".xml", ".json"});
			if (temps.length > 0) 
				return true;
			else
				return false;
		}
		else
			return false;
	}

	public static boolean hasTags(String root) {
		String dirName = DirUtil.TAGSDIR;
		return containsTemplateFiles(root, dirName);
	}

	public static boolean hasJavaTags(String root) {
		String dirName = DirUtil.JAVATAGS;
		return containsTemplateFiles(root, dirName);
	}

	public static boolean hasLayouts(String root) {
		String dirName = DirUtil.LAYOUTDIR;
		return containsTemplateFiles(root, dirName);
	}
	
	 public static boolean isClassname( String classname ) {
	      if (classname == null || classname.length() ==0) return false;

          CharacterIterator iter = new StringCharacterIterator(classname);
          // Check first character (there should at least be one character for each part) ...
          char c = iter.first();
          if (c == CharacterIterator.DONE) return false;
          if (!Character.isJavaIdentifierStart(c) && !Character.isIdentifierIgnorable(c)) return false;
          c = iter.next();
          // Check the remaining characters, if there are any ...
          while (c != CharacterIterator.DONE) {
              if (!Character.isJavaIdentifierPart(c) && !Character.isIdentifierIgnorable(c)) return false;
              c = iter.next();
          }
	      return true;
	  }

	public static final String JAVATAGS = "_javatags";
	public static final String LAYOUTDIR = "_layouts";
	public static final String TAGSDIR = "_tags";
	public static final String JAPIDVIEWS_ROOT = "japidviews";
	public static List<String> scanJavaTags(String root) {
		String sep = File.separator;
		String japidViews = root + sep + JAPIDVIEWS_ROOT + sep;
		File javatags = new File(japidViews + JAVATAGS);
		if (!javatags.exists()) {
			boolean mkdirs = javatags.mkdirs();
			assert mkdirs == true;
			JapidFlags.log("created: " + japidViews + JAVATAGS);
		}
	
		File[] javafiles = javatags.listFiles(new FilenameFilter() {
			@Override
			public boolean accept(File dir, String name) {
				if (name.endsWith(".java"))
					return true;
				return false;
			}
		});
		
		List<String> files = new ArrayList<String>();
		for (File f : javafiles) {
			String fname = f.getName();
			files.add(JAPIDVIEWS_ROOT + "." + JAVATAGS + "." + fname.substring(0, fname.lastIndexOf(".java")));
		}
		return files;
	}

	/**
	 * @author Bing Ran (bing.ran@hotmail.com)
	 * @param f the java file
	 * @return the original template file
	 */
	public static File mapJavatoSrc(File f) {
		File parent = f.getParentFile();
		String fname = mapJavaToSrc(f.getName());
		return new File(parent, fname);
	}
	
	/**
	 * 
	 * @author Bing Ran (bing.ran@hotmail.com)
	 * @param sourceCode
	 * @param lineNum 1-based line number in Java file
	 * @return 1-based line number in the original source template
	 */
	public static int mapJavaLineToSrcLine(String sourceCode, int lineNum) {
		String[] codeLines = sourceCode.split("\n");
		String line = codeLines[lineNum - 1];
	
		int lineMarker = line.lastIndexOf("// line ");
		if (lineMarker < 1) {
			return 0;
		}
		int oriLineNumber = Integer.parseInt(line.substring(lineMarker + 8)
				.trim());
		return oriLineNumber;
	}

}
