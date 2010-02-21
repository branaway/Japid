package cn.bran.play;

import java.io.File;
import java.util.Set;

import play.Play;
import play.PlayPlugin;
import play.exceptions.UnexpectedException;
import play.templates.Template;

/**
 *
 * 
 * @author Bing Ran<bing_ran@hotmail.com>
 * 
 */
public class JapidPlugin extends PlayPlugin {
	@Override
	public void beforeDetectingChanges() {
		File[] changed = JapidCommands.reloadChanged();
		if (changed.length > 0) {
			for (File f : changed) {
//				System.out.println("pre-detect changed: " + f.getName());
			}
		}

		boolean hasRealOrphan = JapidCommands.rmOrphanJava();
		if (hasRealOrphan)
		{
			// a little messy here. clean the cache in case bad files are delete
			// remove all the existing ApplicationClass will reload verything. 
			// ideally we just need to remove the orphan. But the internal cache
			// is not visible. Need API change to do that.
			Play.classes.clear();
			throw new RuntimeException("found orphan template Java artifacts. reload to be safe.");
		}	
	}


	// // VirtualFile appRoot = VirtualFile.open(Play.applicationPath);
	// TranslateTemplateTask t = new TranslateTemplateTask();
	// {
	// Project proj = new Project();
	// t.setProject(proj);
	// proj.init();
	//
	// t.setSrcdir(new File("app"));
	// t.setIncludes(JAPIDVIEWS_ROOT + "/**/*.html");
	// t.importStatic(JapidPlayAdapter.class);
	// t.importStatic(Validation.class);
	// t.importStatic(JavaExtensions.class);
	// t.addAnnotation(NoEnhance.class);
	// t.addImport(JAPIDVIEWS_ROOT + "._layouts.*");
	// t.addImport(JAPIDVIEWS_ROOT + "._tags.*");
	// t.addImport("models.*");
	//
	// // t.add(new ModifiedSelector());
	// t.setTaskType("foo");
	// t.setTaskName("foo");
	// t.setOwningTarget(new Target());
	// }

	public static final String JAPIDVIEWS_ROOT = "japidviews";
	public static final String JAVATAGS = "_javatags";
	public static final String LAYOUTDIR = "_layouts";
	public static final String TAGSDIR = "_tags";

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
