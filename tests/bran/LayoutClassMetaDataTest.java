package bran;

import org.junit.Test;

import bran.japid.LayoutClassMetaData;

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
