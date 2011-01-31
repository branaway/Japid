package cn.bran.japid.compiler;

import static org.junit.Assert.*;

import java.util.regex.Matcher;

import org.junit.Test;

public class JapidTemplateCompilerTest {
	@Test
	public void testSetPattern() {
		Matcher matcher = JapidTemplateCompiler.setPattern.matcher("title \"my great title\"");
		assertTrue(matcher.matches());
		assertEquals("title", matcher.group(1));
		assertEquals("\"my great title\"", matcher.group(2));
		
	}
}
