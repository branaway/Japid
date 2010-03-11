package cn.bran.play;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

import org.apache.commons.io.FileUtils;

import play.data.validation.Validation;
import play.templates.JavaExtensions;
import cn.bran.japid.compiler.TranslateTemplateTask;
import cn.bran.japid.util.DirUtil;

public class JapidCommands {
	private static final String APP = "app";

	private static final String JapidWebUtil = 
			"package japidviews._javatags;\n" + 
			"\n" + 
			"/**\n" + 
			" * a well-know place to add all the static method you want to use in your\n" + 
			" * templates.\n" + 
			" * \n" + 
			" * All the public static methods will be automatically \"import static \" to the\n" + 
			" * generated Java classes by the Japid compiler.\n" + 
			" * \n" + 
			" */\n" + 
			"public class JapidWebUtil {\n" + 
			"	public static String hi() {\n" + 
			"		return \"Hi\";\n" + 
			"	}\n" + 
			"	// your utility methods...\n" + 
			"	\n" + 
			"}\n" + 
			"";
	public static void main(String[] args) throws IOException {
		if ("gen".equals(args[0])) {
			gen(APP);
		} else if ("regen".equals(args[0])) {
			regen(APP);
		} else if ("clean".equals(args[0])) {
			delAllGeneratedJava(APP + File.separatorChar + JapidPlugin.JAPIDVIEWS_ROOT);
		} else if ("mkdir".equals(args[0])) {
			mkdir(APP);
		}
	}

	/**
	 * create the basic layout: app/japidviews/_javatags app/japidviews/_layouts
	 * app/japidviews/_tags
	 * 
	 * then create a dir for each controller. //TODO
	 * @throws IOException 
	 * 
	 */
	public static List<File> mkdir(String root) throws IOException {

		String sep = File.separator;
		String japidViews = root + sep + JapidPlugin.JAPIDVIEWS_ROOT + sep;
		File javatags = new File(japidViews + JapidPlugin.JAVATAGS);
		boolean mkdirs = javatags.mkdirs();
		assert mkdirs == true;
		System.out.println("created: " + javatags.getPath());
		File webutil = new File(javatags, "JapidWebUtil.java");
		if (!webutil.exists()) {
			FileUtils.writeStringToFile(webutil, JapidWebUtil, "UTF-8");
			System.out.println("created JapidWebUtil.java.");
		}
		// add the placeholder for utility class for use in templates
		
		
		File layouts = new File(japidViews + JapidPlugin.LAYOUTDIR);
		mkdirs = layouts.mkdirs();
		assert mkdirs == true;
		System.out.println("created: " + layouts.getPath());

		File tags = new File(japidViews + JapidPlugin.TAGSDIR);
		mkdirs = tags.mkdirs();
		assert mkdirs == true;
		System.out.println("created: " + tags.getPath());

		File[] dirs = new File[] { javatags, layouts, tags };
		List<File> res = new ArrayList<File>();
		res.addAll(Arrays.asList(dirs));

		// create dirs for controllers

		System.out.println("create default packages for controllers.");
		try {
			File[] controllers = getAllControllers(root + sep +  "controllers");
			for (File f : controllers) {
				String cp = japidViews + f.getPath();
				File ff = new File(cp);
				mkdirs = ff.mkdirs();
				assert mkdirs == true;

				res.add(ff);
				System.out.println("created: " + cp);
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		return res;

	}

	public static void regen(String root) throws IOException {
		// TODO Auto-generated method stub
		String pathname = root + File.separatorChar + JapidPlugin.JAPIDVIEWS_ROOT;
		mkdir(root);
		delAllGeneratedJava(pathname);
		gen(root);
	}

	public static void delAllGeneratedJava(String pathname) {
		String[] javas = DirUtil.getAllFileNames(new File(pathname), new String[] {"java"});

		for (String j : javas) {
			if (!j.contains(JapidPlugin.JAVATAGS)) {
				System.out.println("removed: " + j);
				boolean delete = new File(pathname + File.separatorChar + j).delete();
				if (!delete)
					throw new RuntimeException("file was not deleted: "+ j);
			}
		}
//		System.out.println("removed: all none java tag java files in " + JapidPlugin.JAPIDVIEWS_ROOT);
	}

	/**
	 * update the java files from the html files, for the changed only
	 */
	public static void gen(String packageRoot) {
		List<File> changedFiles = reloadChanged(packageRoot);
		if (changedFiles.size() > 0) {
			for (File f : changedFiles) {
				System.out.println("updated: " + f.getName().replace("html", "java"));
			}
		} else {
			System.out.println("No java files need to be updated.");
		}
		
		rmOrphanJava();
	}

	/**
	 * @param root the package root "/"
	 * @return
	 */
	public static List<File> reloadChanged(String root) {
		TranslateTemplateTask t = new TranslateTemplateTask();

		File rootDir = new File(root);
		t.setPackageRoot(rootDir);
		t.setInclude(new File(rootDir, JapidPlugin.JAPIDVIEWS_ROOT));
		t.importStatic(JapidPlayAdapter.class);
		t.importStatic(Validation.class);
		t.importStatic(JavaExtensions.class);
		t.importStatic(WebUtils.class);
		t.addAnnotation(NoEnhance.class);
		t.addImport(JapidPlugin.JAPIDVIEWS_ROOT + "._layouts.*");
		t.addImport(JapidPlugin.JAPIDVIEWS_ROOT + "._javatags.*");
		t.addImport(JapidPlugin.JAPIDVIEWS_ROOT + "._tags.*");
		t.addImport(play.mvc.Scope.class.getName() + ".*");
		t.addImport("models.*");
		t.addImport("controllers.*");
		t.addImport("static  japidviews._javatags.JapidWebUtil.*");
		t.execute();
		List<File> changedFiles = t.getChangedFiles();
		return changedFiles;
	}

	/**
	 * create package structures for all controllers
	 * 
	 * @return
	 */
	public static File[] getAllControllers(String root) {
		// from source fils only
		String[] allFiles = DirUtil.getAllFileNames(new File(root), new String[] { ".java" });
		File[] fs = new File[allFiles.length];
		int i = 0;
		for (String f : allFiles) {
			String path = f.replace(".java", "");
			fs[i++] = new File(path);
		}
		return fs;
	}

	/**
	 * delete orphaned java artifacts from the japidviews directory
	 * 
	 * @return
	 */
	public static boolean rmOrphanJava() {

		boolean hasRealOrphan = false;
		try {
			String pathname = "app" + File.separator + JapidPlugin.JAPIDVIEWS_ROOT;
			File src = new File(pathname);
			if (!src.exists()) {
				System.out.println("Could not find required Japid package structure: " + pathname);
				System.out.println("Please use \"play japid:mkdir\" command to create the Japid view structure.");
				return hasRealOrphan;
			}

			Set<File> oj = DirUtil.findOrphanJava(src, null);
			for (File j : oj) {
				String path = j.getPath();
				System.out.println("found: " + path);
				if (path.contains(JapidPlugin.JAVATAGS)) {
					
					// java tags, don't touch
				} else {
					hasRealOrphan = true;
					String realfile = pathname + File.separator + path;
					File file = new File(realfile);
					boolean r = file.delete();
					if (r)
						System.out.println("JapidPlugin: deleted orphan " + realfile);
					else
						System.out.println("JapidPlugin: failed to delete: " + realfile);
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return hasRealOrphan;
	}

	public static List<File> reloadChanged() {
		return reloadChanged(APP);
	}
}
