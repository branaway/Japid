package cn.bran.japid.compiler;

import static org.junit.Assert.*;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import japa.parser.ParseException;
import japa.parser.ast.CompilationUnit;
import japa.parser.ast.body.Parameter;
import japa.parser.ast.body.VariableDeclarator;
import japa.parser.ast.expr.AnnotationExpr;
import japa.parser.ast.expr.VariableDeclarationExpr;
import japa.parser.ast.type.Type;
import japa.parser.ast.visitor.VoidVisitorAdapter;

import org.junit.Test;

public class JavaSyntaxToolTest {
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

		src = "\"String\".length * 12";
		finals = JavaSyntaxTool.matchLongestPossibleExpr(src);
		assertEquals("\"String\".length * 12", finals);

		src = "12.0";
		finals = JavaSyntaxTool.matchLongestPossibleExpr(src);
		assertEquals("12.0", finals);

		src = "12. a";
		finals = JavaSyntaxTool.matchLongestPossibleExpr(src);
		assertEquals("12.", finals);

		src = "a + b().";
		finals = JavaSyntaxTool.matchLongestPossibleExpr(src);
		assertEquals("a + b()", finals);

		src = "a + b!";
		finals = JavaSyntaxTool.matchLongestPossibleExpr(src);
		assertEquals("a + b", finals);

		src = " ";
		finals = JavaSyntaxTool.matchLongestPossibleExpr(src);
		assertEquals("", finals);

		src = "a;";
		finals = JavaSyntaxTool.matchLongestPossibleExpr(src);
		assertEquals("a", finals);

		src = "a b c a b c a b c a b c a b c a b c a b c a b c a b c a b c a b c "
				+ "a b c a b c a b c a b c a b c a b c a b c a b c a b c a b c a b c "
				+ "a b c a b c a b c a b c a b c a b c a b c a b c a b c a b c a b c "
				+ "a b c a b c a b c a b c a b c a b c a b c a b c a b c a b c a b c "
				+ "a b c a b c a b c a b c a b c a b c a b c a b c a b c a b c a b c "
				+ "a b c a b c a b c a b c a b c a b c a b c a b c a b c a b c a b c "
				+ "a b c a b c a b c a b c a b c a b c a b c a b c a b c a b c a b c ";
		finals = JavaSyntaxTool.matchLongestPossibleExpr(src);
		assertEquals("a", finals);
	}

	@Test
	public void testWeirdExpr() {
		String ex = "\"hello\".hi(foo(var+ \"sd\"))";
		String src = ex + "etc... ~a=='a'";
		String finals = JavaSyntaxTool.matchLongestPossibleExpr(src);
		assertEquals(ex, finals);

	}

	@Test
	public void testHasMathod() throws ParseException {
		String src = "class A { private static void foo(int i, B b); }";
		CompilationUnit cu = JavaSyntaxTool.parse(src);
		assertTrue(JavaSyntaxTool.hasMethod(cu, "foo", "static private",
				"void", " int , B "));
	}

	@Test
	public void testaddParamNamesPlaceHolder() {
		String src = "int  String Object";
		String pama = JavaSyntaxTool.addParamNamesPlaceHolder(src);
		List<Parameter> parseParams = JavaSyntaxTool.parseParams(pama);
		assertEquals(3, parseParams.size());
		assertEquals("int", parseParams.get(0).getType().toString());
		assertEquals("String", parseParams.get(1).getType().toString());
		assertEquals("Object", parseParams.get(2).getType().toString());

		src = "int,  String Object";
		pama = JavaSyntaxTool.addParamNamesPlaceHolder(src);
		parseParams = JavaSyntaxTool.parseParams(pama);
		assertEquals(3, parseParams.size());
		assertEquals("int", parseParams.get(0).getType().toString());
		assertEquals("String", parseParams.get(1).getType().toString());
		assertEquals("Object", parseParams.get(2).getType().toString());
	}

	@Test
	public void testParseParams() {
		String src = "@ Default (3 ) int i,  @Default(foo()+ \"aa\")String s, String m, @Default(\"aa\")String ss";
		List<Parameter> pama = JavaSyntaxTool.parseParams(src);
		Parameter p = pama.get(0);
		String def = JavaSyntaxTool.getDefault(p);
		assertEquals("3", def);
		// System.out.println(def);

		p = pama.get(1);
		def = JavaSyntaxTool.getDefault(p);
		assertEquals("foo() + \"aa\"", def);

		p = pama.get(2);
		def = JavaSyntaxTool.getDefault(p);
		assertNull(def);

		p = pama.get(3);
		def = JavaSyntaxTool.getDefault(p);
		assertEquals("\"aa\"", def);

		// System.out.println(def);
	}

	@Test
	public void testParseParams2() {
		String src = "@default(\"html\") String dataType";
		List<Parameter> pama = JavaSyntaxTool.parseParams(src);
		// System.out.println(def);
	}

	@Test
	public void testBoxPrimitiveTypesInParams() {
		String src = "String a, int b, long[] c, long d";
		String res = JavaSyntaxTool.boxPrimitiveTypesInParams(src);
		assertEquals("String a, Integer b, long[] c, Long d", res);
	}

	/**
	 * doBody a, b -> c
	 */
	@Test
	public void testAsClausePattern() {
		Pattern m = JavaSyntaxTool.AS_PATTERN;

		String s1 = "a, 'sfsdf', 123 -> var";
		Matcher matcher = m.matcher(s1);
		assertTrue(matcher.matches());
		// matcher.find();
		assertEquals("a, 'sfsdf', 123 ", matcher.group(1));
		assertEquals("var", matcher.group(2));

		s1 = "a, 'sfsdf', asr - 1";
		matcher = m.matcher(s1);
		assertTrue(!matcher.matches());
	}

	@Test
	public void testAsClauseExtraction() {
		String s = "a, 1 -> c";
		String[] r = JavaSyntaxTool.breakArgParts(s);
		assertEquals(2, r.length);
		assertEquals("a, 1 ", r[0]);
		assertEquals("c", r[1]);

		s = "a, 1";
		r = JavaSyntaxTool.breakArgParts(s);
		assertEquals(1, r.length);
		assertEquals("a, 1", r[0]);

		s = "-> c";
		r = JavaSyntaxTool.breakArgParts(s);
		assertEquals(2, r.length);
		assertEquals("", r[0]);
		assertEquals("c", r[1]);

	}

	@Test
	public void testParsingSimpleArgs() {
		String src = "a, 1, \"hello\", foo()";
		List<String> args = JavaSyntaxTool.parseArgs(src);
		assertEquals(4, args.size());
	}

	@Test
	public void testParsingArgsWithAssignment() {
		String src = "a, b = foo(), 123";
		List<String> args = JavaSyntaxTool.parseArgs(src);
		assertEquals(3, args.size());
	}

	@Test
	public void testParsingInvalidArgs() {
		String src = "a, foo(";
		try {
			List<String> args = JavaSyntaxTool.parseArgs(src);
			fail("should not be here");
		} catch (RuntimeException e) {
			System.out.println(e);
		}
	}

	@Test
	public void testParsingNamedArgsNone() {
		String src = "a, b";
		List<NamedArg> args = JavaSyntaxTool.parseNamedArgs(src);
		assertEquals(0, args.size());
	}

	@Test
	public void testParsingNamedArgsAll() {
		String src = "a = 1, b = foo()";
		List<NamedArg> args = JavaSyntaxTool.parseNamedArgs(src);
		assertEquals(2, args.size());
	}

	@Test
	public void testParsingNamedArgsMixed() {
		String src = "a, b = foo()";
		try {
			List<NamedArg> args = JavaSyntaxTool.parseNamedArgs(src);
			fail("should have caught mixed cases");
		} catch (RuntimeException e) {
			System.out.println(e);
		}

	}
	
	@Test
	public void testParsingNamedArgsInvalid() {
		String src = "a, b = ";
		try {
			List<NamedArg> args = JavaSyntaxTool.parseNamedArgs(src);
			fail("should have caught bad syntax");
		} catch (RuntimeException e) {
			System.out.println(e);
		}
		
	}
	
	
	@Test
	public void testValidMethDecl() {
		String src = "foo(int i, String s)";
		JavaSyntaxTool.isValidMeth(src);
		
	}
	
	
	
	@Test
	public void testInvalidMethDecl() {
		String src = "foo(i,  s)";
		try {
			JavaSyntaxTool.isValidMeth(src);
			fail("should have thrown exception");
		} catch (RuntimeException e) {
			System.out.println(e);
		}
		
	}
	
	
}
