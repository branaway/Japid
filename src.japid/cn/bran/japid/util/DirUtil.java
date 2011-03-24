package cn.bran.japid.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
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
				return true;
		}
		return false;
	}

	public static List<File> findChangedSrcFiles(File srcDir) {
//		if (target == null)
//			target = src;
//		String srcPath = src.getPath();
		Set<File> allSrc  = new HashSet<File>();
		allSrc = getAllFiles(srcDir, new String[] { ".java", ".html", ".txt", ".json", ".xml" }, allSrc);
		Map<String, Long> javas = new HashMap<String, Long>();
		Map<String, Long> srcFiles = new HashMap<String, Long>();
		

		for (File s : allSrc) {
			String path = s.getPath();
			long modi = s.lastModified();
//			System.out.println("file: " + path + ":" + modi);
			if (path.endsWith(".java")) {
				javas.put(path, modi);
			} else /*if (path.endsWith(".html"))*/ {
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
}
