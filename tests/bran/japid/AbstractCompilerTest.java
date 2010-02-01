package bran.japid;

import static org.junit.Assert.*;

import org.junit.Test;

public class AbstractCompilerTest {

	@Test
	public void testComposeValidMultiLines() {
		String src = "~(\n" + 
		"	bran.Post post, \n" + 
		"	String as\n" + 
		")\n" + 
		"\n";
		
		String result = AbstractCompiler.composeValidMultiLines(src);
		System.out.println(result);
	}

}
