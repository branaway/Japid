package cn.bran.play;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import play.Play;
import play.data.validation.Validation;
import play.templates.JavaExtensions;
import play.vfs.VirtualFile;
import cn.bran.japid.compiler.JapidCompilationException;
import cn.bran.japid.compiler.TranslateTemplateTask;
import cn.bran.japid.util.DirUtil;

public class JapidCommands {
	private static final String APP = "app";

	/**
	 * TODO: perhaps I can parse the depended modules and apply the commands to them as well.
	 * 
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {
		String arg0 = args[0];

		String applicationPath = System.getProperty("user.dir");
		if (args.length > 1) {
			applicationPath = args[1];
		}
		Play.applicationPath = new File(applicationPath);
		if ("gen".equals(arg0)) {
			gen(APP);
		} else if ("regen".equals(arg0)) {
			regen(APP);
		} else if ("clean".equals(arg0)) {
			delAllGeneratedJava(APP + File.separatorChar + DirUtil.JAPIDVIEWS_ROOT);
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
		String japidViews = root + sep + DirUtil.JAPIDVIEWS_ROOT + sep;
		File javatags = new File(Play.applicationPath, japidViews + DirUtil.JAVATAGS);
		if (!javatags.exists()) {
			boolean mkdirs = javatags.mkdirs();
			assert mkdirs == true;
			log("created: " + javatags.getPath());
		}

//		File webutil = new File(javatags, "JapidWebUtil.java");
//		if (!webutil.exists()) {
//			DirUtil.writeStringToFile(webutil, JapidWebUtil);
//			log("created JapidWebUtil.java.");
//		}
		// add the place-holder for utility class for use in templates

		File layouts = new File(Play.applicationPath, japidViews + DirUtil.LAYOUTDIR);
		if (!layouts.exists()) {
			boolean mkdirs = layouts.mkdirs();
			assert mkdirs == true;
			log("created: " + layouts.getPath());
		}

		File tags = new File(Play.applicationPath, japidViews + DirUtil.TAGSDIR);
		if (!tags.exists()) {
			boolean mkdirs = tags.mkdirs();
			assert mkdirs == true;
			log("created: " + tags.getPath());
		}
		
		// email notifiers
		File notifiers = new File(Play.applicationPath, japidViews + "_notifiers");
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
			
			String controllerPath = root + sep + "controllers";
			if (new File(controllerPath).exists()) {
				File[] controllers = getAllJavaFilesInDir(controllerPath);
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
			}
		} catch (Exception e) {
			log(e.toString());
		}

//		log("JapidCommands:  check default template packages for email notifiers.");
		try {
			String notifiersDir = root + sep + "notifiers";
			File notifiersDirFile = new File(Play.applicationPath, notifiersDir);
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
	
	public static void regen() throws IOException {
		regen(APP);
	}

	public static void regen(String root) throws IOException {
		// TODO Auto-generated method stub
		String pathname = root + File.separatorChar + DirUtil.JAPIDVIEWS_ROOT;
		delAllGeneratedJava(pathname);
		gen(root);
	}

	public static void delAllGeneratedJava(String pathname) {
		String[] javas = DirUtil.getAllFileNames(new File(Play.applicationPath, pathname), new String[] { "java" });

		for (String j : javas) {
			if (!j.contains(DirUtil.JAVATAGS)) {
				log("removed: " + j);
				boolean delete = new File(Play.applicationPath, pathname + File.separatorChar + j).delete();
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
		t.setInclude(new File(rootDir, DirUtil.JAPIDVIEWS_ROOT));
		t.clearImports();
		t.importStatic(JapidPlayAdapter.class);
		t.importStatic(Validation.class);
		t.importStatic(JavaExtensions.class);
		t.addAnnotation(NoEnhance.class);
		if (DirUtil.hasLayouts(root))
			t.addImport(DirUtil.JAPIDVIEWS_ROOT + "._layouts.*");
		if (DirUtil.hasJavaTags(root))
			t.addImport(DirUtil.JAPIDVIEWS_ROOT + "._javatags.*");
		if (DirUtil.hasTags(root))
			t.addImport(DirUtil.JAPIDVIEWS_ROOT + "._tags.*");
		t.addImport("models.*");
		t.addImport("controllers.*");
		t.addImport(play.mvc.Scope.class.getName() + ".*");
		t.addImport(play.i18n.Messages.class);
		t.addImport(play.i18n.Lang.class);
		t.addImport(play.mvc.Http.class.getName() + ".*");
		t.addImport(Validation.class.getName());
		t.addImport(play.data.validation.Error.class.getName());
//		t.addImport("static  japidviews._javatags.JapidWebUtil.*");
		List<String> javatags = scanJavaTags(root);
		for (String f : javatags) {
			t.addImport("static " + f + ".*");
		}
		try {
			t.execute();
		} catch (JapidCompilationException e) {
//			 remove the .class file from previous successful compilation if any
//			String templateName = e.getTemplateName();
//			String javaSrc = DirUtil.mapSrcToJava(templateName);
//			// remove the java file
//			String javaSrcPath = APP + File.separator + javaSrc;
//			if (new File(javaSrcPath).delete()){
//				System.out.println("[Japid] deleted: " + javaSrcPath);
//			}
//			String className = javaSrc.substring(0, javaSrc.length() - 5).replace('/', '.').replace('\\', '.');
//			// remove the class file
//			Play.classes.remove(className);
			throw e;
		}
		List<File> changedFiles = t.getChangedFiles();
		return changedFiles;
	}

	public static List<String> scanJavaTags(String root) {
		String sep = File.separator;
		String japidViews = root + sep + DirUtil.JAPIDVIEWS_ROOT + sep;
		File javatags = new File(japidViews + DirUtil.JAVATAGS);
		if (!javatags.exists()) {
			boolean mkdirs = javatags.mkdirs();
			assert mkdirs == true;
			log("created: " + javatags.getPath());
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
			files.add(DirUtil.JAPIDVIEWS_ROOT + "." + DirUtil.JAVATAGS + "." + fname.substring(0, fname.lastIndexOf(".java")));
		}
		return files;
	}
	/**
	 * get all the java files in a dir with the "java" removed
	 * 
	 * @return
	 */
	public static File[] getAllJavaFilesInDir(String root) {
		// from source files only
		String[] allFiles = DirUtil.getAllFileNames(new File(Play.applicationPath, root), new String[] { ".java" });
		File[] fs = new File[allFiles.length];
		int i = 0;
		for (String f : allFiles) {
			String path = f.replace(".java", "");
			fs[i++] = new File(path);
		}
		return fs;
	}

	/**
	 * delete orphaned java artifacts from the japidviews directory of the current app and all the depended modules
	 * 
	 * @return
	 */
	public static boolean rmOrphanJava() {
		boolean hasOrphan = false;
		hasOrphan = removeOrphanedJavaFrom(APP);
		
		Collection<VirtualFile> modules = Play.modules.values();
		for (VirtualFile module: modules) {
			try {
				VirtualFile root = module.child(APP);
				VirtualFile japidViewDir = root.child(DirUtil.JAPIDVIEWS_ROOT);
				File japidFile = japidViewDir.getRealFile();
				if (japidFile.exists()) {
					String absoluteRootPath = root.getRealFile().getAbsolutePath();
					if( removeOrphanedJavaFrom(absoluteRootPath))
						hasOrphan = true;
				}
			}
			catch(Throwable t) {
				
			}
			
		}
		return hasOrphan;
	}

	private static boolean removeOrphanedJavaFrom(String root) {
		boolean hasRealOrphan = false;
		try {
			String pathname = root + File.separator + DirUtil.JAPIDVIEWS_ROOT;
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
				if (path.contains(DirUtil.JAVATAGS)) {

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

	/**
	 * check the current app and dependencies for changed templates
	 * @return
	 */
	public static List<File> reloadChanged() {
		List<File> reloadChanged = reloadChanged(APP);
		Collection<VirtualFile> modules = Play.modules.values();
		for (VirtualFile module: modules) {
			try {
				VirtualFile root = module.child(APP);
				VirtualFile japidViewDir = root.child(DirUtil.JAPIDVIEWS_ROOT);
				File japidFile = japidViewDir.getRealFile();
				if (japidFile.exists()) {
					String absoluteRootPath = root.getRealFile().getAbsolutePath();
					reloadChanged.addAll(reloadChanged(absoluteRootPath));
				}
			}
			catch(Throwable t) {
				
			}
			
		}
		return reloadChanged;
	}
	
	private static void log(String m) {
		System.out.println("[JapidCommands]: " + m);
	}
}
