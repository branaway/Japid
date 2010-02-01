package bran;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import org.junit.Test;

import bran.japid.BranTemplate;
import bran.japid.BranTemplateCompiler;

/**
 * 
 * @author bran
 * @deprecated test is in {@code BranCompilerTests}
 */
public class BranTemplateCompilerTests {
	@Test
	public void testComp() throws IOException {
		FileInputStream fis = new FileInputStream("tempgen/tag/AllPost.html");
		InputStreamReader isr = new InputStreamReader(fis, "UTF-8");
		BufferedReader br = new BufferedReader(isr);
		String src = "";
		for(String line = br.readLine(); line != null; line = br.readLine()) {
			src += line + "\n";
		}
		
		BranTemplate bt = new BranTemplate("tag/AllPost.html", src);
		BranTemplateCompiler cp = new BranTemplateCompiler();
		cp.compile(bt);
		System.out.println(bt.javaSource);
	}
}
