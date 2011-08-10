package cn.bran.japid.compiler;

import static org.junit.Assert.*;

import java.util.List;

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
//		System.out.println(def);
		
		p = pama.get(1);
		def = JavaSyntaxTool.getDefault(p);
		assertEquals("foo() + \"aa\"", def);

		p = pama.get(2);
		def = JavaSyntaxTool.getDefault(p);
		assertNull(def);

		p = pama.get(3);
		def = JavaSyntaxTool.getDefault(p);
		assertEquals("\"aa\"", def);
		
//		System.out.println(def);
	}
	
	
	@Test
	public void testParseParams2() {
		String src = "@default(\"html\") String dataType";
		List<Parameter> pama = JavaSyntaxTool.parseParams(src);
//		System.out.println(def);
	}
	
	@Test
	public void testBoxPrimitiveTypesInParams(){
		String src = "String a, int b, long[] c, long d";
		String res = JavaSyntaxTool.boxPrimitiveTypesInParams(src);
		assertEquals("String a, Integer b, long[] c, Long d", res);
	}
}
