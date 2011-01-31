package cn.bran.japid.compiler;

import static org.junit.Assert.*;

import java.util.List;

import japa.parser.ParseException;
import japa.parser.ast.CompilationUnit;
import japa.parser.ast.body.Parameter;

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
	
	@Test
	public void testHasMathod() throws ParseException {
		String src = "class A { private static void foo(int i, B b); }";
		CompilationUnit cu = JavaSyntaxTool.parse(src);
		assertTrue(JavaSyntaxTool.hasMethod(cu, "foo", "static private", "void", " int , B "));
	}

	@Test
	public void testaddParamNamesPlaceHolder() {
		String src = "int  String Obj";
		String pama = JavaSyntaxTool.addParamNamesPlaceHolder(src);
		List<Parameter> parseParams = JavaSyntaxTool.parseParams(pama);
		assertEquals(3, parseParams.size());
		assertEquals("int", parseParams.get(0).getType().toString());
		assertEquals("String", parseParams.get(1).getType().toString());
		assertEquals("Obj", parseParams.get(2).getType().toString());

		src = "int,  String Obj";
		pama = JavaSyntaxTool.addParamNamesPlaceHolder(src);
		parseParams = JavaSyntaxTool.parseParams(pama);
		assertEquals(3, parseParams.size());
		assertEquals("int", parseParams.get(0).getType().toString());
		assertEquals("String", parseParams.get(1).getType().toString());
		assertEquals("Obj", parseParams.get(2).getType().toString());
	}
}
