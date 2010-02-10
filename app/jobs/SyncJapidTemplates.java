package jobs;

import java.io.File;

import org.apache.tools.ant.Project;
import org.apache.tools.ant.Target;

import com.jamonapi.Monitor;
import com.jamonapi.MonitorFactory;

import play.Play;
import play.Play.Mode;
import play.data.validation.Validation;
import play.exceptions.JavaExecutionException;
import play.exceptions.PlayException;
import play.jobs.Every;
import play.jobs.Job;
import play.templates.JavaExtensions;
import cn.bran.japid.ant.TranslateTemplateTask;
import cn.bran.play.JapidPlugin;
import cn.bran.play.NoEnhance;
import cn.bran.play.JapidPlayAdapter;

/**
 * for some reason it's hard to delete an html template file. Seems the ant
 * locks the html files for some time.
 * 
 * @author Bing Ran<bing_ran@hotmail.com>
 * 
 */
@Every("4s")
public class SyncJapidTemplates extends Job<String> {
//	private static TranslateTemplateTask t = new TranslateTemplateTask();
//	static {
//		Project proj = new Project();
//		t.setProject(proj);
//		proj.init();
//
//		t.setSrcdir(new File("app"));
//		t.setIncludes(JapidPlugin.JAPIDVIEWS_ROOT + "/**/*.html");
//		t.importStatic(PlayTemplateVarsAdapter.class);
//		t.importStatic(Validation.class);
//		t.importStatic(JavaExtensions.class);
//		t.addAnnotation(NoEnhance.class);
//		t.addImport(JapidPlugin.JAPIDVIEWS_ROOT + "._layouts.*");
//		t.addImport(JapidPlugin.JAPIDVIEWS_ROOT + "._tags.*");
//		t.addImport("models.*");
//
//		// t.add(new ModifiedSelector());
//		t.setTaskType("foo");
//		t.setTaskName("foo");
//		t.setOwningTarget(new Target());
//	}

	@Override
	public void doJob() throws Exception {
//		if (Play.mode == Mode.DEV) {
//			// System.out.println("SyncJapidTemplates: sync templates to java in "
//			// + JapidPlugin.JAPIDVIEWS_ROOT);
//			synchronized (SyncJapidTemplates.class) {
//				t.execute();
//			}
//			File[] changedFiles = t.getChangedFiles();
//			if (changedFiles.length > 0) {
//				for (File f : changedFiles) {
//					System.out.println("SyncJapidTemplates: updated: " + f.getName());
//				}
//			}
//		}
	}

	@Override
	public void run() {
		call();
	}

	/**
	 * override the super's version and not to do anything with other plugins
	 * and system change detection
	 */
	public String call() {
		Monitor monitor = null;
		try {
			String result = null;
			try {
				lastException = null;
				lastRun = System.currentTimeMillis();
				monitor = MonitorFactory.start(getClass().getName() + ".doJob()");
				result = doJobWithResult();
				monitor.stop();
				monitor = null;
				wasError = false;
			} catch (PlayException e) {
				throw e;
			} catch (Exception e) {
				StackTraceElement element = PlayException.getInterestingStrackTraceElement(e);
				if (element != null) {
					throw new JavaExecutionException(Play.classes.getApplicationClass(element.getClassName()), element.getLineNumber(), e);
				}
				throw e;
			}
			return result;
		} catch (Throwable e) {
			onException(e);
		} finally {
			if (monitor != null) {
				monitor.stop();
			}
			// _finally();
		}
		return null;
	}
}
