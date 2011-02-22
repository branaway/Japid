package cn.bran.japid.compiler;

import static org.junit.Assert.*;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.Test;

public class JapidAbstractCompilerTest {
	@Test public void testActionRunnerline() {
		String action = "my.action(param), \"12s\", a, 123";
		String line = JapidAbstractCompiler.createActionRunner(action);
		System.out.println(line);
	}
	
	@Test public void testIfPattern() {
		String ifPattern = JapidAbstractCompiler.OPEN_IF_PATTERN1;
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
		assertTrue("} else if 3 + 2 ".matches(elseifPattern));
		assertFalse("} else if (expr) {".matches(elseifPattern));
		assertFalse("} else {".matches(elseifPattern));
	}

	@Test public void testOpenElseIfPattern() {
		String elseifPattern = JapidAbstractCompiler.OPEN_ELSE_IF_PATTERN_STRING;
		assertTrue(" else if 3 + 2 ".matches(elseifPattern));
		assertTrue("else  if expr.goo()".matches(elseifPattern));
		assertFalse("} else if expr".matches(elseifPattern));
		assertFalse("else if (asd)".matches(elseifPattern));
	}
	
	@Test public void testOpentForPattern() {
		String forPattern = JapidAbstractCompiler.OPEN_FOR_PATTERN_STRING;
		Pattern p = Pattern.compile(forPattern);
		assertTrue("for String a:aaa ".matches(forPattern));
		assertFalse(" for String a : aaa".matches(forPattern));
		assertTrue("for String a : foo()".matches(forPattern));
		String string = "for String a : new foo.bar(){}";
		assertTrue(string.matches(forPattern));
		Matcher matcher = p.matcher(string);
		assertTrue(matcher.matches());
		assertEquals("String a" , matcher.group(1).trim());
		assertEquals("new foo.bar(){}" , matcher.group(2));

		assertFalse("for String a:aaa {".matches(forPattern));
		assertFalse("for(String a: aaa)  ".matches(forPattern));
		assertFalse("for(String a: aaa) { ".matches(forPattern));
	}
}
