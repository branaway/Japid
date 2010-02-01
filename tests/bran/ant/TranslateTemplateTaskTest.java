package bran.ant;

import java.io.File;

import org.apache.tools.ant.Project;
import org.apache.tools.ant.Target;
import org.apache.tools.ant.taskdefs.Delete;
import org.apache.tools.ant.types.FileSet;
import org.junit.Test;

public class TranslateTemplateTaskTest {

	private static final String TEMP_ROOT = "tempgen";

	@Test
	public void updateJavaFilesFromTemps() {
		TranslateTemplateTask t = new TranslateTemplateTask();

		Project proj = new Project();
		t.setProject(proj);
		proj.init();
		t.setTaskType("japid");
		t.setTaskName("japid");
		t.setOwningTarget(new Target());
		t.setSrcdir(new File(TEMP_ROOT));
//		t.add(new ModifiedSelector());
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
