package cn.bran;

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
		String src = readFile("tempgen/japidviews/_layouts/Layout.html");
		JapidTemplate bt = new JapidTemplate("tag/Layout.html", src);
		JapidAbstractCompiler cp = new JapidLayoutCompiler();
		cp.compile(bt);
		System.out.println(bt.javaSource);
	}

	@Test
	public void testAnotherLayout() throws IOException {
		String src = readFile("tempgen/japidviews/_layouts/TagLayout.html");
		JapidTemplate bt = new JapidTemplate("tag/TagLayout.html", src);
		JapidAbstractCompiler cp = new JapidLayoutCompiler();
		cp.compile(bt);
		System.out.println(bt.javaSource);
	}
	
	@Test
	public void testCompileTemplate() throws IOException {
		String src = readFile("tempgen/tag/AllPost.html");

		JapidTemplate bt = new JapidTemplate("tag/AllPost.html", src);
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
		String src = readFile("tempgen/tag/Tag2.html");

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
