package cn.bran.japid.ant;


import java.io.File;

import org.apache.tools.ant.Project;
import org.apache.tools.ant.Target;
import org.apache.tools.ant.taskdefs.Delete;
import org.apache.tools.ant.types.FileSet;
import org.junit.Test;

import cn.bran.play.NoEnhance;
import cn.bran.play.JapidPlayAdapter;


public class TranslateTemplateTaskTest {

	private static final String TEMP_ROOT = "tempgen";
	private static final String JAPIDVIEWS_ROOT = "japidviews";
	@Test
	public void updateJavaFilesFromTemps() {
		TranslateTemplateTask t = new TranslateTemplateTask();

		Project proj = new Project();
		t.setProject(proj);
		// use streaming API or stringbuffer API, 10% slower
//		t.setUseStreaming(true);
		t.importStatic(JapidPlayAdapter.class);
		t.setSrcdir(new File(TEMP_ROOT));
		t.addAnnotation(NoEnhance.class);
		
		t.setTaskType("japid");
		t.setTaskName("japid");
		t.addImport(JAPIDVIEWS_ROOT + "._layouts.*");
		t.addImport(JAPIDVIEWS_ROOT + "._tags.*");

		t.setOwningTarget(new Target());
//		t.add(new ModifiedSelector());
		proj.init();
		t.execute();
//		System.out.println("1");
	}

	@Test
	public void delAllGeneratedJava() {
		Delete t = new Delete();
		FileSet fs = new FileSet();
		fs.setDir(new File(TEMP_ROOT));
		fs.setIncludes("**/*.java");
		t.addFileset(fs);
		
		Project proj = new Project();
		t.setProject(proj);
		proj.init();
		t.setTaskType("deljava");
		t.setTaskName("deljava");
		t.setOwningTarget(new Target());
		t.execute();
	}
	
	@Test
	public void regenerateAll() {
		delAllGeneratedJava();
		updateJavaFilesFromTemps();
	}
}
	
