package cn.bran.japid.compiler;

import static org.junit.Assert.*;

import org.junit.Test;

import cn.bran.japid.compiler.JapidAbstractCompiler.Tag;

public class TagInvocationLineParserTest {
	TagInvocationLineParser p = new TagInvocationLineParser();

	@Test
	public void testParseSimplest() {
		String src = "tag";
		Tag t = p.parse(src);
		assertEquals("tag", t.tagName);
		assertEquals("", t.args);
	}

	@Test
	public void testParseSimple() {
		String src = "tag a, b";
		Tag t = p.parse(src);
		assertEquals("tag", t.tagName);
		assertEquals("a, b", t.args);
	}

	@Test
	public void testClosureParamError() {
		String src = "tag a, b | c";
		try {
			Tag t = p.parse(src);
		} catch (Exception e) {
			return;
		}
		fail("shoudl have reported syntax error");
	}

	@Test
	public void testParseSimple2() {
		String src = "tag(a, b)";
		Tag t = p.parse(src);
		assertEquals("tag", t.tagName);
		assertEquals("a, b", t.args);
	}

	@Test
	public void testParseWithClosure() {
		String src = "tag(a, b) | String c";
		Tag t = p.parse(src);
		assertEquals("tag", t.tagName);
		assertEquals("a, b", t.args);
		assertEquals("String c", t.bodyArgsString);
	}

	@Test
	public void testNoArgWithClosure() {
		String src = "tag | String c";
		Tag t = p.parse(src);
		assertEquals("tag", t.tagName);
		assertEquals("", t.args);
		assertEquals("String c", t.bodyArgsString);
	}

	@Test
	public void testNoArgWithClosure2() {
		String src = "tag()| String c";
		Tag t = p.parse(src);
		assertEquals("tag", t.tagName);
		assertEquals("", t.args);
		assertEquals("String c", t.bodyArgsString);
	}

	@Test
	public void testWithNoClosure2() {
		String src = "tag(a, b)";
		Tag t = p.parse(src);
		assertEquals("tag", t.tagName);
		assertEquals("a, b", t.args);
		assertEquals("", t.bodyArgsString);
	}

	@Test
	public void testStringLiteral() {
		String src = "get 'title'";
		Tag t = p.parse(src);
		assertEquals("get", t.tagName);
		assertEquals("'title'", t.args);
		assertEquals("", t.bodyArgsString);
	}

}
