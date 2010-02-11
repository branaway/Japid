package cn.bran.play;

import java.io.File;

import org.apache.tools.ant.Project;
import org.apache.tools.ant.Target;
import org.apache.tools.ant.taskdefs.Delete;
import org.apache.tools.ant.types.FileSet;

import play.data.validation.Validation;
import play.templates.JavaExtensions;
import cn.bran.japid.ant.TranslateTemplateTask;

public class JapidCommands {
	private static final String APP = "app";

	public static void main(String[] args) {
		if ("gen".equals(args[0])) {
			gen();
		}
		else if ("regen".equals(args[0])) {
			regen();
		}
		else if ("clean".equals(args[0])) {
			delAllGeneratedJava();
		}
	}

	private static void regen() {
		// TODO Auto-generated method stub
		delAllGeneratedJava();
		gen();
	}
	
	private static void delAllGeneratedJava() {
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
	private static void gen() {
		File[] changedFiles = reloadChanged();
		if (changedFiles.length > 0) {
			for (File f : changedFiles) {
				System.out.println("updated: " + f.getName().replace("html", "java"));
			}
		}
		else {
			System.out.println("No java files need to be updated.");
		}
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
		t.addImport(JapidPlugin.JAPIDVIEWS_ROOT + "._tags.*");
		t.addImport("models.*");

		// t.add(new ModifiedSelector());
		t.setTaskType("foo");
		t.setTaskName("foo");
		t.setOwningTarget(new Target());
		t.execute();
		File[] changedFiles = t.getChangedFiles();
		return changedFiles;
	}
}
