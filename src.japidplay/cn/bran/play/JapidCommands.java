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

	private static final String JapidWebUtil = "package japidviews._javatags;\n" + "\n" + "/**\n"
			+ " * a well-know place to add all the static method you want to use in your\n" + " * templates.\n" + " * \n"
			+ " * All the public static methods will be automatically \"import static \" to the\n"
			+ " * generated Java classes by the Japid compiler.\n" + " * \n" + " */\n" + "public class JapidWebUtil {\n"
			+ "	public static String hi() {\n" + "		return \"Hi\";\n" + "	}\n" + "	// your utility methods...\n" + "	\n" + "}\n" + "";

	public static void main(String[] args) throws IOException {
		String arg0 = args[0];

		if ("gen".equals(arg0)) {
			gen(APP);
		} else if ("regen".equals(arg0)) {
			regen(APP);
		} else if ("clean".equals(arg0)) {
			delAllGeneratedJava(APP + File.separatorChar + JapidPlugin.JAPIDVIEWS_ROOT);
		} else if ("mkdir".equals(arg0)) {
			mkdir(APP);
		} else {
			log("not known: " + arg0);
		}
	}

	/**
	 * create the basic layout: app/japidviews/_javatags app/japidviews/_layouts
	 * app/japidviews/_tags
	 * 
	 * then create a dir for each controller. //TODO
	 * 
	 * @throws IOException
	 * 
	 */
	public static List<File> mkdir(String root) throws IOException {
		String sep = File.separator;
		String japidViews = root + sep + JapidPlugin.JAPIDVIEWS_ROOT + sep;
		File javatags = new File(japidViews + JapidPlugin.JAVATAGS);
		if (!javatags.exists()) {
			boolean mkdirs = javatags.mkdirs();
			assert mkdirs == true;
			log("created: " + javatags.getPath());
		}

		File webutil = new File(javatags, "JapidWebUtil.java");
		if (!webutil.exists()) {
			FileUtils.writeStringToFile(webutil, JapidWebUtil, "UTF-8");
			log("created JapidWebUtil.java.");
		}
		// add the place-holder for utility class for use in templates

		File layouts = new File(japidViews + JapidPlugin.LAYOUTDIR);
		if (!layouts.exists()) {
			boolean mkdirs = layouts.mkdirs();
			assert mkdirs == true;
			log("created: " + layouts.getPath());
		}

		File tags = new File(japidViews + JapidPlugin.TAGSDIR);
		if (!tags.exists()) {
			boolean mkdirs = tags.mkdirs();
			assert mkdirs == true;
			log("created: " + tags.getPath());
		}
		
		// email notifiers
		File notifiers = new File(japidViews + "_notifiers");
		if (!notifiers.exists()) {
			boolean mkdirs = notifiers.mkdirs();
			assert mkdirs == true;
			log("created: " + notifiers.getPath());
		}
		
		
		File[] dirs = new File[] { javatags, layouts, tags };
		List<File> res = new ArrayList<File>();
		res.addAll(Arrays.asList(dirs));

		// create dirs for controllers

//		log("JapidCommands: check default template packages for controllers.");
		try {
			File[] controllers = getAllJavaFilesInDir(root + sep + "controllers");
			for (File f : controllers) {
				String cp = japidViews + f.getPath();
				File ff = new File(cp);
				if (!ff.exists()) {
					boolean mkdirs = ff.mkdirs();
					assert mkdirs == true;
					res.add(ff);
					log("created: " + cp);
				}
			}
		} catch (Exception e) {
			log(e.toString());
		}

//		log("JapidCommands:  check default template packages for email notifiers.");
		try {
			String notifiersDir = root + sep + "notifiers";
			File notifiersDirFile = new File(notifiersDir);
			if (!notifiersDirFile.exists()) {
				if (notifiersDirFile.mkdir()) {
					log("created the email notifiers directory. ");
				}
				else {
					log("email notifiers directory did not exist and could not be created for unknow reason. ");
				}
			}
			
			File[] controllers = getAllJavaFilesInDir(notifiersDir);
			for (File f : controllers) {
				// note: we keep the notifiers dir to differentiate those from the controller
				// however this means we cannot have a controller with package like "controllers.notifiers"
				// so we now use "_notifiers"
				String cp = japidViews + "_notifiers" + sep + f.getPath();
				File ff = new File(cp);
				if (!ff.exists()) {
					boolean mkdirs = ff.mkdirs();
					assert mkdirs == true;
					res.add(ff);
					log("created: " + cp);
				}
			}
		} catch (Exception e) {
			log(e.toString());
		}
		return res;

	}

	public static void regen(String root) throws IOException {
		// TODO Auto-generated method stub
		String pathname = root + File.separatorChar + JapidPlugin.JAPIDVIEWS_ROOT;
		delAllGeneratedJava(pathname);
		gen(root);
	}

	public static void delAllGeneratedJava(String pathname) {
		String[] javas = DirUtil.getAllFileNames(new File(pathname), new String[] { "java" });

		for (String j : javas) {
			if (!j.contains(JapidPlugin.JAVATAGS)) {
				log("removed: " + j);
				boolean delete = new File(pathname + File.separatorChar + j).delete();
				if (!delete)
					throw new RuntimeException("file was not deleted: " + j);
			}
		}
		// log("removed: all none java tag java files in " +
		// JapidPlugin.JAPIDVIEWS_ROOT);
	}

	/**
	 * update the java files from the html files, for the changed only
	 * @throws IOException 
	 */
	public static void gen(String packageRoot) throws IOException {
//		mkdir(packageRoot);
		// moved to reloadChanged
		List<File> changedFiles = reloadChanged(packageRoot);
		if (changedFiles.size() > 0) {
			for (File f : changedFiles) {
				log("updated: " + f.getName().replace("html", "java"));
			}
		} else {
			log("No java files need to be updated.");
		}

		rmOrphanJava();
	}

	/**
	 * @param root
	 *            the package root "/"
	 * @return
	 */
	public static List<File> reloadChanged(String root) {
		try {
			mkdir(root);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		
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
		t.addImport(play.mvc.Http.class.getName() + ".*");
		t.addImport(Validation.class.getName());
		t.addImport(play.data.validation.Error.class.getName());
		t.addImport("models.*");
		t.addImport("controllers.*");
		t.addImport("static  japidviews._javatags.JapidWebUtil.*");
		t.execute();
		List<File> changedFiles = t.getChangedFiles();
		return changedFiles;
	}

	/**
	 * get all the java files in a dir with the "java" removed
	 * 
	 * @return
	 */
	public static File[] getAllJavaFilesInDir(String root) {
		// from source files only
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
				log("Could not find required Japid package structure: " + pathname);
				log("Please use \"play japid:mkdir\" command to create the Japid view structure.");
				return hasRealOrphan;
			}

			Set<File> oj = DirUtil.findOrphanJava(src, null);
			for (File j : oj) {
				String path = j.getPath();
				// log("found: " + path);
				if (path.contains(JapidPlugin.JAVATAGS)) {

					// java tags, don't touch
				} else {
					hasRealOrphan = true;
					String realfile = pathname + File.separator + path;
					File file = new File(realfile);
					boolean r = file.delete();
					if (r)
						log("deleted orphan " + realfile);
					else
						log("failed to delete: " + realfile);
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
	
	private static void log(String m) {
		System.out.println("[JapidCommands]: " + m);
	}
}
