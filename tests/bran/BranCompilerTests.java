package bran;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;

import org.junit.Test;

import bran.japid.AbstractCompiler;
import bran.japid.BranLayoutCompiler;
import bran.japid.BranTemplate;
import bran.japid.BranTemplateCompiler;
/**
 * have tests for all three type compilers
 * @author bran
 *
 */
public class BranCompilerTests {

	@Test
	public void testCompileLayout() throws IOException {
		String src = readFile("tempgen/tag/Layout.html");
		BranTemplate bt = new BranTemplate("tag/Layout.html", src);
		AbstractCompiler cp = new BranLayoutCompiler();
		cp.compile(bt);
		System.out.println(bt.javaSource);
	}

	@Test
	public void testAnotherLayout() throws IOException {
		String src = readFile("tempgen/tag/TagLayout.html");
		BranTemplate bt = new BranTemplate("tag/TagLayout.html", src);
		AbstractCompiler cp = new BranLayoutCompiler();
		cp.compile(bt);
		System.out.println(bt.javaSource);
	}
	
	@Test
	public void testCompileTemplate() throws IOException {
		String src = readFile("tempgen/tag/AllPost.html");

		BranTemplate bt = new BranTemplate("tag/AllPost.html", src);
		BranTemplateCompiler cp = new BranTemplateCompiler();
		cp.compile(bt);
		System.out.println(bt.javaSource);
	}

	@Test
	public void testCompileDisplay() throws IOException {
		String src = readFile("tempgen/tag/Display.html");

		BranTemplate bt = new BranTemplate("tag/Display.html", src);
		BranTemplateCompiler cp = new BranTemplateCompiler();
		cp.compile(bt);
		System.out.println(bt.javaSource);
	}

	@Test
	public void testCompileTag2() throws IOException {
		String src = readFile("tempgen/tag/Tag2.html");

		BranTemplate bt = new BranTemplate("tag/Tag2.html", src);
		BranTemplateCompiler cp = new BranTemplateCompiler ();
		cp.compile(bt);
		System.out.println(bt.javaSource);
	}

	@Test
	public void testActionNotation() throws IOException {
		String src = readFile("tempgen/templates/Actions.html");
		
		BranTemplate bt = new BranTemplate("templates/Actions.html", src);
		BranTemplateCompiler cp = new BranTemplateCompiler ();
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
