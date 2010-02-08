package cn.bran;

import org.junit.Test;

import cn.bran.japid.classmeta.InnerClassMeta;



public class InnerClassMetaTest {
	@Test
	public void testPrintout() {
		InnerClassMeta icm = new InnerClassMeta(
				"Display", 
				2,
				"String title, String hi", 
				"p (\"The real title is: \"); pln(title);"
				);
		System.out.println(icm.toString());
	}
}
