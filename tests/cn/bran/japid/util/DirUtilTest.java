package cn.bran.japid.util;

import static org.junit.Assert.*;

import org.junit.Test;

public class DirUtilTest {

	private static final String root = ".\\japidviews\\_tags\\taggy";

	@Test
	public void testMapSrcToJava() {
		String f = root + ".html";
		String j = DirUtil.mapSrcToJava(f);
		assertEquals(root + ".java", j);
	}

}
