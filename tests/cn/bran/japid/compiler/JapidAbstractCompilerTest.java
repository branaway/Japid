package cn.bran.japid.compiler;

import static org.junit.Assert.*;

import org.junit.Test;

public class JapidAbstractCompilerTest {
	@Test public void testActionRunnerline() {
		String action = "my.action(param), \"12s\", a, 123";
		String line = JapidAbstractCompiler.createActionRunner(action);
		System.out.println(line);
	}
	
	@Test public void testIfPattern() {
		String ifPattern = JapidAbstractCompiler.GROOVY_IF_PATTERN;
		assertTrue("if 3 + 2 {".matches(ifPattern));
		assertTrue("if foo(\"hello\") {".matches(ifPattern));
		assertTrue("if  myCollection".matches(ifPattern));
		assertFalse("if (expr)".matches(ifPattern));
		assertFalse("if(expr){".matches(ifPattern));
	}

	@Test public void testElseIfPattern() {
		String elseifPattern = JapidAbstractCompiler.ELSE_IF_PATTERN_STRING;
		assertTrue("} else if 3 + 2 {".matches(elseifPattern));
		assertTrue("}  else  if expr.goo()  {".matches(elseifPattern));
		assertFalse("} else if 3 + 2 ".matches(elseifPattern));
		assertFalse("} else if (expr) {".matches(elseifPattern));
		assertFalse("} else {".matches(elseifPattern));
	}
}
