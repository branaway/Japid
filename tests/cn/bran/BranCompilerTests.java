package cn.bran;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;

import org.junit.Test;

import cn.bran.japid.compiler.JapidAbstractCompiler;
import cn.bran.japid.compiler.JapidLayoutCompiler;
import cn.bran.japid.compiler.JapidTemplateCompiler;
import cn.bran.japid.template.JapidTemplate;

/**
 * have tests for all three type compilers.
 * 
 * How do I verify the integrity the generated source files?
 * 
 * @author bran
 *
 */
public class BranCompilerTests {

	@Test
	public void testCompileLayout() throws IOException {
		String src = readFile("JapidSample/app/japidviews/_layouts/Layout.html");
		JapidTemplate bt = new JapidTemplate("tag/Layout.html", src);
		JapidAbstractCompiler cp = new JapidLayoutCompiler();
		cp.compile(bt);
		System.out.println(bt.javaSource);
	}

	@Test
	public void testAnotherLayout() throws IOException {
		String src = readFile("JapidSample/app/japidviews/_layouts/TagLayout.html");
		JapidTemplate bt = new JapidTemplate("tag/TagLayout.html", src);
		JapidAbstractCompiler cp = new JapidLayoutCompiler();
		cp.compile(bt);
		System.out.println(bt.javaSource);
	}
	
	@Test
	public void testCompileTemplate() throws IOException {
		String src = readFile("JapidSample/app/japidviews/templates/AllPost.html");

		JapidTemplate bt = new JapidTemplate("AllPost.html", src);
		JapidAbstractCompiler cp = new JapidTemplateCompiler();
		cp.compile(bt);
		System.out.println(bt.javaSource);
	}

	@Test
	public void testCompileDisplay() throws IOException {
		String src = readFile("tempgen/tag/Display.html");

		JapidTemplate bt = new JapidTemplate("tag/Display.html", src);
		JapidAbstractCompiler cp = new JapidTemplateCompiler();
		cp.compile(bt);
		System.out.println(bt.javaSource);
	}

	@Test
	public void testCompileTag2() throws IOException {
		String src = readFile("JapidSample/app/japidviews/_tags/Tag2.html");

		JapidTemplate bt = new JapidTemplate("tag/Tag2.html", src);
		JapidAbstractCompiler cp = new JapidTemplateCompiler ();
		cp.compile(bt);
		System.out.println(bt.javaSource);
	}

	@Test
	public void testActionNotation() throws IOException {
		String src = readFile("tempgen/japidviews/templates/Actions.html");
		
		JapidTemplate bt = new JapidTemplate("templates/Actions.html", src);
		JapidAbstractCompiler cp = new JapidTemplateCompiler ();
		cp.compile(bt);
		System.out.println(bt.javaSource);
	}
	
	@Test
	public void testOpenBrace() throws IOException {
		String srcFile = "tests/openBrace.html";
		String src = readFile(srcFile);
		
		JapidTemplate bt = new JapidTemplate(srcFile, src);
		JapidAbstractCompiler cp = new JapidTemplateCompiler ();
		cp.compile(bt);
		System.out.println(bt.javaSource);
	}
	
	@Test
	public void testTagCalls() throws IOException {
		String srcFile = "tests/tagCalls.html";
		String src = readFile(srcFile);
		
		JapidTemplate bt = new JapidTemplate("tagCalls.html", src);
		JapidAbstractCompiler cp = new JapidTemplateCompiler ();
		cp.compile(bt);
		String code = bt.javaSource;
		System.out.println(code);
		assertTrue(code.contains("_tag0.setActionRunners(getActionRunners());"));
		assertTrue(code.contains("_tag0.render(a);"));
		assertTrue(code.contains("_my_tag1.setActionRunners(getActionRunners());"));
		assertTrue(code.contains("_my_tag1.render(a);"));
		assertTrue(code.contains("_my_tag2.setActionRunners(getActionRunners());"));
		assertTrue(code.contains("_my_tag2.render(a, _my_tag2DoBody);"));
		assertTrue(code.contains("private tag _tag0 = new tag(getOut());"));
		assertTrue(code.contains("private my.tag _my_tag1 = new my.tag(getOut());"));
		assertTrue(code.contains("private my.tag _my_tag2 = new my.tag(getOut());"));
		assertTrue(code.contains("class my_tag2DoBody implements my.tag.DoBody< String>{"));
		assertTrue(code.contains("private my_tag2DoBody _my_tag2DoBody = new my_tag2DoBody();"));
	}
	
	@Test
	public void testSimpleInvoke() throws IOException {
		String srcFile = "tests/simpleInvoke.html";
		String src = readFile(srcFile);
		
		JapidTemplate bt = new JapidTemplate("simpleInvoke.html", src);
		JapidAbstractCompiler cp = new JapidTemplateCompiler ();
		cp.compile(bt);
		String code = bt.javaSource;
		System.out.println(code);
		assertTrue(code.contains("actionRunners.put(getOut().length(), new cn.bran.play.CacheablePlayActionRunner(\"\", \"MyController.action\", s)"));
		assertTrue(code.contains("MyController.action(s);"));
		assertTrue(code.contains("actionRunners.put(getOut().length(), new cn.bran.play.CacheablePlayActionRunner(\"\", \"MyController.action\", s + \"2\""));
		assertTrue(code.contains("MyController.action(s + \"2\");"));
		
	}

	@Test
	public void testOldInvoke() throws IOException {
		String srcFile = "JapidSample/app/japidviews/Application/authorPanel2.html";
		String src = readFile(srcFile);
		
		JapidTemplate bt = new JapidTemplate("simpleInvoke.html", src);
		JapidAbstractCompiler cp = new JapidTemplateCompiler ();
		cp.compile(bt);
		String code = bt.javaSource;
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
