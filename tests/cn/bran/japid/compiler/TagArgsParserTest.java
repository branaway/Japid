package cn.bran.japid.compiler;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;

import cn.bran.japid.compiler.TagArgsParser.Token;


public class TagArgsParserTest {
	@Test
	public void testParamsTokens() {
		String src = "controllers.action(\"a\", 12),p1 + p2.foo(), \"12s\",123";
		TagArgsParser p = new TagArgsParser(src);
		List<String> params = p.split();
		for (String pa : params) {
			System.out.println("[" + pa + "]");
		}
		assertEquals("controllers.action(\"a\", 12)", params.get(0));
		assertEquals("p1 + p2.foo()", params.get(1));
		assertEquals("\"12s\"", params.get(2));
		assertEquals("123", params.get(3));
	}
}
