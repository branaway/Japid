package cn.bran.japid.util;

import java.io.File;
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
		String[] allSrc = getAllFileNames(src, new String[] { ".java", ".html" });
		Set<String> javas = new HashSet<String>();
		Set<String> htmls = new HashSet<String>();

		for (String s : allSrc) {
			if (s.endsWith(".java")) {
				javas.add(s);
			} else if (s.endsWith(".html")) {
				htmls.add(s.substring(0, s.lastIndexOf('.')) + ".java");
			}
		}

		javas.removeAll(htmls);
		Set<File> re = new HashSet<File>();
		for (String j : javas) {
			re.add(new File(j));
		}
		return re;
	}

	// public static Set<String> mapFileSet(String[] fnames, String extension) {
	// Set<String> fset = new HashSet<String>();
	// for (String f : fnames) {
	// int dot = f.lastIndexOf(".");
	// if (dot > 0) {
	// String root = f.substring(0, dot);
	// fset.add(root + "." + extension);
	// }
	// }
	// return fset;
	// }

//	public static String[] getAllFilesAnt(File dir, String[] patterns) {
//		DirectoryScanner ds = new DirectoryScanner();
//		ds.setBasedir(dir);
//		// ds.setExcludes(new String[] {JapidPlugin.JAVATAGS + "/**"});
//		if (patterns == null)
//			ds.setIncludes(null); // all
//		else
//			ds.setIncludes(patterns); // all
//		ds.scan();
//		String[] includeFiles = ds.getIncludedFiles();
//		return includeFiles;
//	}

	/**
	 * 
	 */
	public static String[] getAllFileNames(File dir, String[] exts) {
		List<String> files = new ArrayList<String>();
		getAllFileNames("", dir, files, exts);
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

	public static List<File> findChangedHtmlFiles(File src) {
//		if (target == null)
//			target = src;
//		String srcPath = src.getPath();
		Set<File> allSrc  = new HashSet<File>();
		allSrc = getAllFiles(src, new String[] { ".java", ".html" }, allSrc);
		Map<String, Long> javas = new HashMap<String, Long>();
		Map<String, Long> htmls = new HashMap<String, Long>();
		

		for (File s : allSrc) {
			String path = s.getPath();
			if (path.endsWith(".java")) {
				// keep the root only
				javas.put(path.substring(0, path.length() - 5), s.lastModified());
			} else if (path.endsWith(".html")) {
				// keep the root only
				htmls.put(path.substring(0, path.length() - 5), s.lastModified());
			}
		}

		List<File> rs = new ArrayList<File>();
		
		for (String k : htmls.keySet()) {
			Long t = javas.get(k);
			if (t == null) {
				rs.add(new File(k + ".html"));
			}
			else {
				if (htmls.get(k).compareTo(t) > 0) {
					rs.add(new File(k + ".html"));
				}
			}
		}
		return rs;
	}
}
