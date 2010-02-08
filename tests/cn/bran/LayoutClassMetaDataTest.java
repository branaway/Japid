package cn.bran;

import org.junit.Test;

import cn.bran.japid.classmeta.LayoutClassMetaData;


public class LayoutClassMetaDataTest {
	@Test
	public void testPrintout() {
		LayoutClassMetaData icm = new LayoutClassMetaData();
		icm.className = "Layout";
		icm.packageName = "tag";
		icm.body = "p(\"something\");";
		icm.get("title");
		icm.get("footer");
		
		System.out.println(icm.toString());
	}
}
