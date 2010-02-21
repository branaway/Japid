package cn.bran.japid.compiler;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import org.junit.Test;

import cn.bran.japid.compiler.JapidAbstractCompiler;
import cn.bran.japid.compiler.JapidTemplateCompiler;
import cn.bran.japid.template.JapidTemplate;


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
		
		JapidTemplate bt = new JapidTemplate("tag/AllPost.html", src);
		JapidAbstractCompiler cp = new JapidTemplateCompiler();
		cp.compile(bt);
		System.out.println(bt.javaSource);
	}
}
