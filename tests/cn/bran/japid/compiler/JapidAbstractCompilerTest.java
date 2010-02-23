package cn.bran.japid.compiler;

import org.junit.Test;

public class JapidAbstractCompilerTest {
	@Test public void testActionRunnerline() {
		String action = "my.action(param), \"12s\", a, 123";
		String line = JapidAbstractCompiler.createActionRunner(action);
		System.out.println(line);
	}
}
