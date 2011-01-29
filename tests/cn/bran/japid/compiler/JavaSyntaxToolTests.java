package cn.bran.japid.compiler;

import static org.junit.Assert.*;

import org.junit.Test;

public class JavaSyntaxToolTests {
	@Test
	public void testAddFinalToAllParams() {
		String src = "String a, final int b, MyObject[] c";
		String finals = JavaSyntaxTool.addFinalToAllParams(src);
		assertEquals("final String a, final int b, final MyObject[] c", finals);
	}

	@Test
	public void testMatchLongestPossibleExpr() {
		String src = "a + b(\"s\".length()) c";
		String finals = JavaSyntaxTool.matchLongestPossibleExpr(src);
		assertEquals("a + b(\"s\".length())", finals);

		src = "a + b()c";
		finals = JavaSyntaxTool.matchLongestPossibleExpr(src);
		assertEquals("a + b()", finals);

		src = "a  ba";
		finals = JavaSyntaxTool.matchLongestPossibleExpr(src);
		assertEquals("a", finals);

		src = "a | 2()";
		finals = JavaSyntaxTool.matchLongestPossibleExpr(src);
		assertEquals("a | 2", finals);

		src = "a b c a b c a b c a b c a b c a b c a b c a b c a b c a b c a b c " +
				"a b c a b c a b c a b c a b c a b c a b c a b c a b c a b c a b c " +
				"a b c a b c a b c a b c a b c a b c a b c a b c a b c a b c a b c " +
				"a b c a b c a b c a b c a b c a b c a b c a b c a b c a b c a b c " +
				"a b c a b c a b c a b c a b c a b c a b c a b c a b c a b c a b c " +
				"a b c a b c a b c a b c a b c a b c a b c a b c a b c a b c a b c " +
				"a b c a b c a b c a b c a b c a b c a b c a b c a b c a b c a b c ";
		finals = JavaSyntaxTool.matchLongestPossibleExpr(src);
		assertEquals("a", finals);
	}
}
