package bran;

import org.junit.Test;

import bran.japid.AbstractTemplateClassMetaData;
import bran.japid.TemplateClassMetaData;

public class TemplateClassMetaDataTest {

	@Test
	public void testToString() {
		TemplateClassMetaData m = new TemplateClassMetaData();
		m.packageName = "tag";
		m.className = "Child_html";
		m.superClass = "Layout_html";
		m.renderArgs = "String blogTitle, Post frontPost";
		m.addSetTag("title", "pln(\"the title  is \"); p(blogTitle);");
		m.addSetTag("footer", "\tpln(\"My Footer\")");
		m.addCallTagBodyInnerClass("Display", 1, "String title, String hi", "p(\"The real title is: \"); p(title);");
		m.body = "pln();\n\t if (frontPost != null) { new tag.Display().render(frontPost, \"home\", new Display1()); } \n pln(\"<p>cool</p>	  \");";
		System.out.println(m.toString());
	}
	
	@Test
	public void testImports () {
		AbstractTemplateClassMetaData meta = new AbstractTemplateClassMetaData() {{
			addImportLine("models");
		}};
		
		meta.printHeaders();
		System.out.println(meta.sb.toString());
		
	}

}
