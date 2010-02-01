package bran;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import org.junit.Test;

import bran.japid.AbstractCompiler;
import bran.japid.BranLayoutCompiler;
import bran.japid.BranTemplate;


/**
 * 
 * @author bran
 * @deprecated now is on the {@code BranCompilerTests}
 */
public class BranLayoutCompilerTest {

	@Test
	public void testHop() throws IOException {
		FileInputStream fis = new FileInputStream("tempgen/tag/Layout.html");
		InputStreamReader isr = new InputStreamReader(fis, "UTF-8");
		BufferedReader br = new BufferedReader(isr);
		String src = "";
		for(String line = br.readLine(); line != null; line = br.readLine()) {
			src += line + "\n";
		}
		
		BranTemplate bt = new BranTemplate("tag/Layout.html", src);
		AbstractCompiler cp = new BranLayoutCompiler();
		cp.compile(bt);
		System.out.println(bt.javaSource);
	}
}
