package cn.bran.play;

import java.io.File;
import java.util.HashSet;
import java.util.Set;

import org.apache.tools.ant.DirectoryScanner;

import edu.emory.mathcs.backport.java.util.Arrays;

public class DirUtil {
	public static Set<File> findOrphanJava(File src, File target) {
		if (target == null)
			target = src;
		String[] allSrc = getAllFiles(src, new String[] {"**/*.java", "**/*.html"});
		Set<String> javas = new HashSet<String>();
		Set<String> htmls = new HashSet<String>();
		
		for (String s : allSrc) {
			if (s.endsWith(".java")) {
				javas.add(s);
			}
			else if (s.endsWith(".html")) {
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

//	public static Set<String> mapFileSet(String[] fnames, String extension) {
//		Set<String> fset = new HashSet<String>();
//		for (String f : fnames) {
//			int dot = f.lastIndexOf(".");
//			if (dot > 0) {
//				String root = f.substring(0, dot);
//				fset.add(root + "." + extension);
//			}
//		}
//		return fset;
//	}

	public static String[] getAllFiles(File dir, String[] patterns) {
		DirectoryScanner ds = new DirectoryScanner();
		ds.setBasedir(dir);
//		ds.setExcludes(new String[] {JapidPlugin.JAVATAGS + "/**"});
		if (patterns == null)
			ds.setIncludes(null); // all
		else
			ds.setIncludes(patterns); // all
		ds.scan();
		String[] includeFiles = ds.getIncludedFiles();
		return includeFiles;
	}
}
