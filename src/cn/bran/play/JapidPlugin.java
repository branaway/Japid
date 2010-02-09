package cn.bran.play;

import java.io.File;

import org.apache.tools.ant.Project;
import org.apache.tools.ant.Target;

import play.Play;
import play.PlayPlugin;
import play.Play.Mode;
import play.data.validation.Validation;
import play.exceptions.UnexpectedException;
import play.templates.JavaExtensions;
import play.templates.Template;
import cn.bran.japid.ant.TranslateTemplateTask;

/**
 * this plugin is not in effect until Play! provides a hook before its
 * classloader detect file changes
 * 
 * @author Bing Ran<bing_ran@hotmail.com>
 * 
 */
public class JapidPlugin extends PlayPlugin {
	// VirtualFile appRoot = VirtualFile.open(Play.applicationPath);
	TranslateTemplateTask t = new TranslateTemplateTask();
	{
		Project proj = new Project();
		t.setProject(proj);
		proj.init();

		t.setSrcdir(new File("app"));
		t.setIncludes(JAPIDVIEWS_ROOT + "/**/*.html");
		t.importStatic(PlayTemplateVarsAdapter.class);
		t.importStatic(Validation.class);
		t.importStatic(JavaExtensions.class);
		t.addAnnotation(NoEnhance.class);
		t.addImport(JAPIDVIEWS_ROOT + "._layouts.*");
		t.addImport(JAPIDVIEWS_ROOT + "._tags.*");
		t.addImport("models.*");

		// t.add(new ModifiedSelector());
		t.setTaskType("foo");
		t.setTaskName("foo");
		t.setOwningTarget(new Target());
	}

	public static final String JAPIDVIEWS_ROOT = "japidviews";

	@Override
	public void onApplicationStop() {
		try {
			Japid.shutdown();
		} catch (Exception e) {
			throw new UnexpectedException(e);
		}
	}

	@Override
	public void detectChange() {
		// if (Play.mode == Mode.DEV) {
		// System.out.println("detecting template change and compile changes to java in "
		// + JAPIDVIEWS_ROOT);
		// t.execute();
		// }
	}

	@Override
	public void beforeInvocation() {
		// cannot do this. Rather I should get the controller name from the
		// thread local
		// the urlMapper is shared by threads!
		// BranTemplateBase.urlMapper = new
		// RouteAdapter(Request.current().controller);
	}

	@Override
	public void afterApplicationStart() {
		Japid.startup();
	}

	@Override
	public void onApplicationStart() {
		// TODO Auto-generated method stub
		super.onApplicationStart();
	}

	@Override
	public void onTemplateCompilation(Template template) {
		// TODO Auto-generated method stub
		super.onTemplateCompilation(template);
	}

	@Override
	public void onEvent(String message, Object context) {

	}
}
