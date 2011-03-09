package cn.bran;

import static org.junit.Assert.assertTrue;
import japa.parser.ParseException;
import japa.parser.ast.CompilationUnit;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Modifier;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;

import org.junit.Test;

import cn.bran.japid.compiler.JapidAbstractCompiler;
import cn.bran.japid.compiler.JapidLayoutCompiler;
import cn.bran.japid.compiler.JapidTemplateCompiler;
import cn.bran.japid.compiler.JavaSyntaxTool;
import cn.bran.japid.template.JapidTemplate;

/**
 * have tests for all three type compilers.
 * 
 * How do I verify the integrity the generated source files?
 * 
 * @author bran
 *
 */
public class CompilerTests {

	@Test
	public void testOpenFor() throws IOException {
		String src = readFile("JapidSample/app/japidviews/_layouts/Layout.html");
		JapidTemplate bt = new JapidTemplate("tag/Layout.html", src);
		JapidAbstractCompiler cp = new JapidLayoutCompiler();
		cp.compile(bt);
		System.out.println(bt.javaSource);
		assertTrue("invalid java code", JavaSyntaxTool.isValid(bt.javaSource));
	}

	@Test
	public void testCompileLayout() throws IOException {
		String src = readFile("JapidSample/app/japidviews/_layouts/Layout.html");
		JapidTemplate bt = new JapidTemplate("tag/Layout.html", src);
		JapidAbstractCompiler cp = new JapidLayoutCompiler();
		cp.compile(bt);
		System.out.println(bt.javaSource);
		assertTrue("invalid java code", JavaSyntaxTool.isValid(bt.javaSource));
	}
	
	@Test
	public void testIfCommand() throws IOException {
		String src = readFile("JapidSample/app/japidviews/Application/ifs.html");
		JapidTemplate bt = new JapidTemplate("Application/ifs.html", src);
		JapidAbstractCompiler cp = new JapidLayoutCompiler();
		cp.compile(bt);
		String javaSource = bt.javaSource;
		System.out.println(javaSource);
		assertTrue(javaSource.contains("if(!asBoolean(ss))"));
		assertTrue(javaSource.contains("else if(!asBoolean(ss))"));
		assertTrue("invalid java code", JavaSyntaxTool.isValid(javaSource));
	}
	
	@Test
	public void testOpenIfCommand() throws IOException {
		String src = readFile("JapidSample/app/japidviews/Application/ifs2.html");
		JapidTemplate bt = new JapidTemplate("Application/ifs2.html", src);
		JapidAbstractCompiler cp = new JapidTemplateCompiler();
		cp.compile(bt);
		System.out.println(bt.javaSource);
		assertTrue("invalid java code", JavaSyntaxTool.isValid(bt.javaSource));
	}
	
	@Test
	public void testLayoutWithArgs() throws IOException {
		String src = readFile("JapidSample/app/japidviews/more/Perf/perfmain.html");
		JapidTemplate bt = new JapidTemplate("more/Perf/perfmain.html", src);
		JapidAbstractCompiler cp = new JapidLayoutCompiler();
		cp.compile(bt);
		System.out.println(bt.javaSource);
		assertTrue("invalid java code", JavaSyntaxTool.isValid(bt.javaSource));
	}
	
	@Test
	public void testExtendsLayoutWithArgs() throws IOException {
		String src = readFile("JapidSample/app/japidviews/more/Perf/perf.html");
		JapidTemplate bt = new JapidTemplate("more/Perf/perf.html", src);
		JapidAbstractCompiler cp = new JapidTemplateCompiler();
		cp.compile(bt);
		System.out.println(bt.javaSource);
		assertTrue("invalid java code", JavaSyntaxTool.isValid(bt.javaSource));
	}
	
	@Test
	public void testAnotherLayout() throws IOException, ParseException {
		String src = readFile("JapidSample/app/japidviews/_layouts/TagLayout.html");
		JapidTemplate bt = new JapidTemplate("japidviews/_layouts/TagLayout.html", src);
		JapidAbstractCompiler cp = new JapidLayoutCompiler();
		cp.compile(bt);
		String srccode = bt.javaSource;
		System.out.println(srccode);
		CompilationUnit cu = JavaSyntaxTool.parse(srccode);
		assertTrue(srccode.contains("package japidviews._layouts;"));
		assertTrue(srccode.contains("public abstract class TagLayout extends cn.bran.japid.template.JapidTemplateBase"));
		assertTrue(srccode.contains("protected abstract void doLayout();"));
		assertTrue(srccode.contains("@Override public void layout()"));
		
	}
	
	@Test
	public void testNoPlayCommand() throws IOException, ParseException {
		String src = readFile("JapidSample/app/japidviews/templates/noplay.html");
		JapidTemplate bt = new JapidTemplate("japidviews/templates/noplay.html", src);
		JapidAbstractCompiler cp = new JapidLayoutCompiler();
		cp.compile(bt);
		String srccode = bt.javaSource;
		System.out.println(srccode);
		CompilationUnit cu = JavaSyntaxTool.parse(srccode);
	}
	
	@Test
	public void testSubLayout() throws IOException {
		String src = readFile("JapidSample/app/japidviews/_layouts/SubLayout.html");
		JapidTemplate bt = new JapidTemplate("japidviews/_layouts/SubLayout.html", src);
		JapidAbstractCompiler cp = new JapidLayoutCompiler();
		cp.compile(bt);
		String srccode = bt.javaSource;
		System.out.println(srccode);
		assertTrue("invalid java code", JavaSyntaxTool.isValid(bt.javaSource));
		assertTrue(srccode.contains("package japidviews._layouts;"));
		assertTrue(srccode.contains("public abstract class SubLayout extends Layout"));
		assertTrue(srccode.contains("protected abstract void doLayout();"));
		assertTrue(srccode.contains("@Override public void layout()"));
		
	}
	
	@Test
	public void testTemplateWithCallbackTagCalls() throws IOException, ParseException {
		String src = readFile("JapidSample/app/japidviews/templates/AllPost.html");

		JapidTemplate bt = new JapidTemplate("japidviews/templates/AllPost.html", src);
		JapidAbstractCompiler cp = new JapidTemplateCompiler();
		cp.compile(bt);
//		System.out.println(bt.javaSource);
		CompilationUnit cu = JavaSyntaxTool.parse(bt.javaSource);
		System.out.println(cu);
//		assertTrue("invalid java code", JavaSyntaxValidator.isValid(bt.javaSource));
		
	}

	@Test
	public void testCompileTagWithDoubleDispatch() throws IOException, ParseException {
		String src = readFile("japidSample/app/japidviews/_tags/Display.html");
		JapidTemplate bt = new JapidTemplate("tags/Display.html", src);
		JapidAbstractCompiler cp = new JapidTemplateCompiler();
		cp.compile(bt);
		String srcCode = bt.javaSource;
		System.out.println(srcCode);
		
		CompilationUnit cu = JavaSyntaxTool.parse(srcCode);
		assertTrue(srcCode.contains("package tags;"));
		assertTrue(srcCode.contains("public class Display extends TagLayout"));
		assertTrue(srcCode.contains("public cn.bran.japid.template.RenderResult render(models.japidsample.Post post,	String as, DoBody body) {"));
		assertTrue(srcCode.contains("@Override protected void doLayout() {"));
		assertTrue("doBody is not presenting", srcCode.contains("body.render(post.getTitle() + \"!\");"));
		assertTrue(srcCode.contains("public static interface DoBody<A>"));
	}

	@Test
	public void testCompileTag2() throws IOException {
		String src = readFile("JapidSample/app/japidviews/_tags/Tag2.html");
		JapidTemplate bt = new JapidTemplate("tag/Tag2.html", src);
		JapidAbstractCompiler cp = new JapidTemplateCompiler ();
		cp.compile(bt);
		System.out.println(bt.javaSource);
		assertTrue("invalid java code", JavaSyntaxTool.isValid(bt.javaSource));
	}

	@Test
	public void testActionNotation() throws IOException {
		String src = readFile("JapidSample/app/japidviews/templates/Actions.html");
	
		JapidTemplate bt = new JapidTemplate("japidviews/templates/Actions.html", src);
		JapidAbstractCompiler cp = new JapidTemplateCompiler ();
		cp.compile(bt);
		System.out.println(bt.javaSource);
		assertTrue("invalid java code", JavaSyntaxTool.isValid(bt.javaSource));
	}
	
	@Test
	public void testOpenBrace() throws IOException {
		String srcFile = "tests/openBrace.html";
		String src = readFile(srcFile);
		
		JapidTemplate bt = new JapidTemplate(srcFile, src);
		JapidAbstractCompiler cp = new JapidTemplateCompiler ();
		cp.compile(bt);
		System.out.println(bt.javaSource);
		assertTrue("invalid java code", JavaSyntaxTool.isValid(bt.javaSource));
	}
	
	@Test
	public void testTagCalls() throws IOException {
		String srcFile = "tests/tagCalls.html";
		String src = readFile(srcFile);
		
		JapidTemplate bt = new JapidTemplate("tagCalls.html", src);
		JapidAbstractCompiler cp = new JapidTemplateCompiler ();
		cp.compile(bt);
		String code = bt.javaSource;
		assertTrue("invalid java code", JavaSyntaxTool.isValid(bt.javaSource));
		System.out.println(code);
		assertTrue(code.contains("_tag0.setActionRunners(getActionRunners());"));
		assertTrue(code.contains("_tag0.render(a);"));
		assertTrue(code.contains("_my_tag1.setActionRunners(getActionRunners());"));
		assertTrue(code.contains("_my_tag1.render(a);"));
		assertTrue(code.contains("_my_tag2.setActionRunners(getActionRunners());"));
		assertTrue(code.contains("_my_tag2.render(a, new my.tag.DoBody<String>(){"));
		assertTrue(code.contains("final tag _tag0 = new tag(getOut());"));
		assertTrue(code.contains("final my.tag _my_tag1 = new my.tag(getOut());"));
		assertTrue(code.contains("final my.tag _my_tag2 = new my.tag(getOut());"));
	}
	
	@Test
	public void testTagline() throws IOException, ParseException {
		String srcFile = "tests/tagline.html";
		String src = readFile(srcFile);
		
		JapidTemplate bt = new JapidTemplate("tagline.html", src);
		JapidAbstractCompiler cp = new JapidTemplateCompiler ();
		cp.compile(bt);
		CompilationUnit cu = JavaSyntaxTool.parse(bt.javaSource);
		System.out.println(cu);
		assertTrue(bt.javaSource.contains("_my_tag0.render(a);"));
		assertTrue(bt.javaSource.contains("_my_tag1.render(a);"));
		assertTrue(bt.javaSource.contains("_your_tag4.render(a + 123);"));
	}

	@Test
	public void testLog() throws IOException, ParseException {
		String srcFile = "JapidSample/app/japidviews/templates/log.html";
		String src = readFile(srcFile);
		
		JapidTemplate bt = new JapidTemplate("japidviews/templates/Actions.html", src);
		JapidAbstractCompiler cp = new JapidTemplateCompiler ();
		cp.compile(bt);
		CompilationUnit cu = JavaSyntaxTool.parse(bt.javaSource);
		System.out.println(cu);
	}

	@Test
	public void testVerbatim() throws IOException, ParseException {
		String srcFile = "JapidSample/app/japidviews/Application/verbatim.html";
		String src = readFile(srcFile);
		
		JapidTemplate bt = new JapidTemplate("japidviews/Application/verbatim.html", src);
		JapidAbstractCompiler cp = new JapidTemplateCompiler ();
		cp.compile(bt);
		CompilationUnit cu = JavaSyntaxTool.parse(bt.javaSource);
		System.out.println(cu);
	}

	@Test
	public void testTagBlock() throws IOException {
		String srcFile = "JapidSample/app/japidviews/templates/tagBody.html";
		String src = readFile(srcFile);
		
		JapidTemplate bt = new JapidTemplate("japidviews/templates/tagBody.html", src);
		JapidAbstractCompiler cp = new JapidTemplateCompiler ();
		cp.compile(bt);
		System.out.println(bt.javaSource);
		assertTrue("invalid java code", JavaSyntaxTool.isValid(bt.javaSource));
		assertTrue(bt.javaSource.contains("_fooTag0.render(\"hi\", new fooTag.DoBody(){"));
		assertTrue(bt.javaSource.contains("_anotherTag1.render(echo, new anotherTag.DoBody<String>(){"));
	}

	@Test
	public void testEachDirective() throws IOException {
		String srcFile = "tests/eachTag.html";
		String src = readFile(srcFile);
		
		JapidTemplate bt = new JapidTemplate("eachTag.html", src);
		JapidAbstractCompiler cp = new JapidTemplateCompiler ();
		cp.compile(bt);
		System.out.println(bt.javaSource);
		assertTrue("invalid java code", JavaSyntaxTool.isValid(bt.javaSource));
		assertTrue(bt.javaSource.contains("final Each _Each0 = new Each(getOut());"));
		assertTrue(bt.javaSource.contains("_Each0.setActionRunners(getActionRunners());"));
		assertTrue(bt.javaSource.contains("_Each0.render(sa, new Each.DoBody<String>(){"));
		assertTrue(bt.javaSource.contains("public void render(final String a, final int _size, final int _index, final boolean _isOdd, final String _parity, final boolean _isFirst, final boolean _isLast) {"));
	}
	
	@Test
	public void testSetDirective() throws IOException {
		String srcFile = "tests/setTag.html";
		String src = readFile(srcFile);
		
		JapidTemplate bt = new JapidTemplate("tests/setTag.html", src);
		JapidAbstractCompiler cp = new JapidTemplateCompiler ();
		cp.compile(bt);
		System.out.println(bt.javaSource);
		assertTrue("invalid java code", JavaSyntaxTool.isValid(bt.javaSource));
		assertTrue(bt.javaSource.contains("@Override protected void message() {"));
		assertTrue(bt.javaSource.contains("@Override protected void title() {"));
	}
	
	@Test
	public void testGetDirective() throws IOException, ParseException {
		String srcFile = "tests/getTag.html";
		String src = readFile(srcFile);
		
		JapidTemplate bt = new JapidTemplate("tests/getTag.html", src);
		JapidAbstractCompiler cp = new JapidLayoutCompiler();
		cp.compile(bt);
		System.out.println(bt.javaSource);
//		assertTrue("invalid java code", JavaSyntaxTool.isValid(bt.javaSource));
		CompilationUnit cu = JavaSyntaxTool.parse(bt.javaSource);
		assertTrue("method is not declared", JavaSyntaxTool.hasMethod(cu, "title", Modifier.PROTECTED, "void", ""));
		assertTrue("method is not declared", JavaSyntaxTool.hasMethod(cu, "footer", Modifier.PROTECTED, "void", ""));
		assertTrue("method is not declared", JavaSyntaxTool.hasMethod(cu, "doLayout", Modifier.PROTECTED | Modifier.ABSTRACT, "void", ""));
		assertTrue("method is never called", JavaSyntaxTool.hasMethodInvocatioin(cu, "title"));
		assertTrue("method is never called", JavaSyntaxTool.hasMethodInvocatioin(cu, "footer"));
		
//		assertTrue(bt.javaSource.contains("@Override protected void message() {"));
//		assertTrue(bt.javaSource.contains("@Override protected void title() {"));
	}
	
	@Test
	public void testDefDirective() throws IOException, ParseException {
		String srcFile = "JapidSample/app/japidviews/templates/def.html";
		String src = readFile(srcFile);
		
		JapidTemplate bt = new JapidTemplate("japidviews/templates/def.html", src);
		JapidAbstractCompiler cp = new JapidLayoutCompiler();
		cp.compile(bt);
//		System.out.println(bt.javaSource);
//		assertTrue("invalid java code", JavaSyntaxTool.isValid(bt.javaSource));
		CompilationUnit cu = JavaSyntaxTool.parse(bt.javaSource);
//		System.out.println(cu);
		assertTrue("method is not declared", JavaSyntaxTool.hasMethod(cu, "foo", "public", "String", null));
		assertTrue("method is not declared", JavaSyntaxTool.hasMethod(cu, "foo2", "public", "String", "String"));
		assertTrue("method is not declared", JavaSyntaxTool.hasMethod(cu, "bar", "public", "String", null));
//		assertTrue("method is never called", JavaSyntaxTool.hasMethodInvocatioin(cu, "title"));
//		assertTrue("method is never called", JavaSyntaxTool.hasMethodInvocatioin(cu, "footer"));
		
//		assertTrue(bt.javaSource.contains("@Override protected void message() {"));
//		assertTrue(bt.javaSource.contains("@Override protected void title() {"));
	}
	
	@Test
	public void testSimpleInvoke() throws IOException {
		String srcFile = "tests/simpleInvoke.html";
		String src = readFile(srcFile);
		
		JapidTemplate bt = new JapidTemplate("simpleInvoke.html", src);
		JapidAbstractCompiler cp = new JapidTemplateCompiler ();
		cp.compile(bt);
		String code = bt.javaSource;
		assertTrue("invalid java code", JavaSyntaxTool.isValid(bt.javaSource));
		System.out.println(code);
		assertTrue(code.contains("actionRunners.put(getOut().length(), new cn.bran.play.CacheablePlayActionRunner(\"\", MyController.class, \"action\", s + \"2\") {"));
		assertTrue(code.contains("MyController.action(s);"));
		assertTrue(code.contains("return new cn.bran.japid.template.RenderResultPartial(this.headers, getOut(), t, actionRunners);"));
		assertTrue(code.contains("MyController.action(s + \"2\");"));
		
	}

	@Test
	public void testScriptlineLayout() throws IOException {
		String srcFile = "JapidSample/app/japidviews/more/MyController/scriptlineLayout.html";
		String src = readFile(srcFile);
		
		JapidTemplate bt = new JapidTemplate("scriptlineLayout.html", src);
		JapidAbstractCompiler cp = new JapidLayoutCompiler();
		cp.compile(bt);
		String code = bt.javaSource;
		System.out.println(code);
		assertTrue("invalid java code", JavaSyntaxTool.isValid(bt.javaSource));
			
	}

	@Test
	public void testOldInvoke() throws IOException {
		String srcFile = "JapidSample/app/japidviews/Application/authorPanel2.html";
		String src = readFile(srcFile);
		
		JapidTemplate bt = new JapidTemplate("simpleInvoke.html", src);
		JapidAbstractCompiler cp = new JapidTemplateCompiler ();
		cp.compile(bt);
		String code = bt.javaSource;
		assertTrue("invalid java code", JavaSyntaxTool.isValid(bt.javaSource));
		System.out.println(code);
	}
	
	private static String readFile(String path) throws IOException {
		FileInputStream stream = new FileInputStream(new File(path));
		try {
			FileChannel fc = stream.getChannel();
			MappedByteBuffer bb = fc.map(FileChannel.MapMode.READ_ONLY, 0, fc.size());
			/* Instead of using default, pass in a decoder. */
			return Charset.forName("UTF-8").decode(bb).toString();
		} finally {
			stream.close();
		}
	}
	

}
