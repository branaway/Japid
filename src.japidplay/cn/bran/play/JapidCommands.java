package cn.bran.play;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

import org.apache.tools.ant.Project;
import org.apache.tools.ant.Target;
import org.apache.tools.ant.taskdefs.Delete;
import org.apache.tools.ant.taskdefs.Mkdir;
import org.apache.tools.ant.types.FileSet;

import play.data.validation.Validation;
import play.templates.JavaExtensions;
import cn.bran.japid.ant.TranslateTemplateTask;

public class JapidCommands {
	private static final String APP = "app";

	public static void main(String[] args) {
		if ("gen".equals(args[0])) {
			gen();
		} else if ("regen".equals(args[0])) {
			regen();
		} else if ("clean".equals(args[0])) {
			delAllGeneratedJava();
		} else if ("mkdir".equals(args[0])) {
			mkdir(".");
		}
	}

	/**
	 * create the basic layout: app/japidviews/_javatags app/japidviews/_layouts
	 * app/japidviews/_tags
	 * 
	 * then create a dir for each controller. //TODO
	 * 
	 */
	public static List<File> mkdir(String root) {
		Mkdir t = new Mkdir();
		Project proj = new Project();
		t.setProject(proj);
		proj.init();

		String sep = File.separator;
		String japidViews = root + sep + APP + sep + JapidPlugin.JAPIDVIEWS_ROOT + sep;
		File javatags = new File(japidViews + JapidPlugin.JAVATAGS);
		t.setDir(javatags);
		t.execute();
		System.out.println("created: " + javatags.getPath());
		File layouts = new File(japidViews + JapidPlugin.LAYOUTDIR);
		t.setDir(layouts);
		t.execute();
		System.out.println("created: " + layouts.getPath());
		File tags = new File(japidViews + JapidPlugin.TAGSDIR);
		t.setDir(tags);
		t.execute();
		System.out.println("created: " + tags.getPath());
		File[] dirs = new File[] { javatags, layouts, tags };
		List<File> res = new ArrayList<File>();
		res.addAll(Arrays.asList(dirs));

		// create dirs for controllers

		System.out.println("create default packages for controllers.");
		File[] controllers = getAllControllers(root + sep + APP + sep + "controllers");
		for (File f : controllers) {
			String cp = japidViews + f.getPath();
			File ff = new File(cp);
			t.setDir(ff);
			t.execute();
			res.add(ff);
			System.out.println("created: " + cp);
		}

		return res;

	}

	public static void regen() {
		// TODO Auto-generated method stub
		delAllGeneratedJava();
		gen();
	}

	public static void delAllGeneratedJava() {
		Delete t = new Delete();
		FileSet fs = new FileSet();
		fs.setDir(new File(APP));
		fs.setIncludes(JapidPlugin.JAPIDVIEWS_ROOT + "/**/*.java");
		fs.setExcludes(JapidPlugin.JAPIDVIEWS_ROOT + "/" + JapidPlugin.JAVATAGS + "/**");
		t.addFileset(fs);

		Project proj = new Project();
		t.setProject(proj);
		proj.init();
		t.setTaskType("deljava");
		t.setTaskName("deljava");
		t.setOwningTarget(new Target());
		t.execute();
		System.out.println("removed: all java files in " + JapidPlugin.JAPIDVIEWS_ROOT);
	}

	/**
	 * update the java files from the html files, for the changed only
	 */
	public static void gen() {
		File[] changedFiles = reloadChanged();
		if (changedFiles.length > 0) {
			for (File f : changedFiles) {
				System.out.println("updated: " + f.getName().replace("html", "java"));
			}
		} else {
			System.out.println("No java files need to be updated.");
		}
		
		rmOrphanJava();
	}

	/**
	 * @return
	 */
	public static File[] reloadChanged() {
		TranslateTemplateTask t = new TranslateTemplateTask();
		Project proj = new Project();
		t.setProject(proj);
		proj.init();

		t.setSrcdir(new File(APP));
		t.setIncludes(JapidPlugin.JAPIDVIEWS_ROOT + "/**/*.html");
		t.importStatic(JapidPlayAdapter.class);
		t.importStatic(Validation.class);
		t.importStatic(JavaExtensions.class);
		t.addAnnotation(NoEnhance.class);
		t.addImport(JapidPlugin.JAPIDVIEWS_ROOT + "._layouts.*");
		t.addImport(JapidPlugin.JAPIDVIEWS_ROOT + "._javatags.*");
		t.addImport(JapidPlugin.JAPIDVIEWS_ROOT + "._tags.*");
		t.addImport("models.*");
		t.addImport("controllers.*");

		// t.add(new ModifiedSelector());
		t.setTaskType("foo");
		t.setTaskName("foo");
		t.setOwningTarget(new Target());
		t.execute();
		File[] changedFiles = t.getChangedFiles();
		return changedFiles;
	}

	/**
	 * create package structures for all controllers
	 * 
	 * @return
	 */
	public static File[] getAllControllers(String root) {
		// from source fils only
		String[] allFiles = DirUtil.getAllFiles(new File(root), new String[] { "**/*.java" });
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
				if (j.getName().contains(JapidPlugin.JAVATAGS)) {
					// java tags, don't touch
				} else {
					hasRealOrphan = true;
					String realfile = pathname + File.separator + j.getPath();
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
}
