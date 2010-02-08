package cn.bran;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;

import org.junit.Test;

import cn.bran.japid.compiler.JapidTemplateTransformer;



public class BranTemplateTranslatorTest {
	@Test
	public void testGen() throws Exception {
		JapidTemplateTransformer gen = new JapidTemplateTransformer("tempgen", null);
//		gen.addImportStatic(JavaExtensions.class);
		gen.addImportLine("import java.math.*;");
//		gen.setSourceFolder("tempgen");
//		gen.setTargetFolder("tempgen");
		File f = gen.generate("templates/AllPost.html");
		assertTrue(f.exists());
		f = gen.generate("tag/Tag2.html");
		assertTrue(f.exists());
		f = gen.generate("layout/Layout.html");
		assertTrue(f.exists());
		f = gen.generate("tag/Display.html");
		assertTrue(f.exists());
		f = gen.generate("layout/TagLayout.html");
		assertTrue(f.exists());

	}
	
	/**
	 * take a template file and translate it to Java in the same directoty as the template.
	 * 
	 * @throws Exception
	 */
	@Test
	public void testSimpleGenInSitu() throws Exception {
		JapidTemplateTransformer gen = new JapidTemplateTransformer("tempgen", null);
//		gen.addImportStatic(JavaExtensions.class);
//		File f = gen.generate("tempgen/tag/SimpleTemp.html");
		File f = gen.generate("templates/SimpleTemp.html");
		assertTrue(f.exists());
	}
	
	@Test public void testFilePathConversion() throws IOException {
		String ch = "child";
		File f = new File(ch);
		File fc = new File(".");
		String rela = JapidTemplateTransformer.getRelativePath(f, fc);
		assertEquals(ch, rela);
	}
}
