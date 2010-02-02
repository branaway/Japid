package templates;
import java.util.*;
import java.io.*;
import tag.*;
import bran.japid.tags.Each;
import bran.Post;
import layout.*;
import static bran.WebUtils.*;
// This file was generated from: templates/SimpleTemp.html
// Change to this file will be lost next time the template file is compiled.
public class SimpleTemp extends bran.japid.BranTemplateBase{
	public static final String sourceTemplate = "templates/SimpleTemp.html";
static byte[] static_0 = getBytes(""
);
static byte[] static_1 = getBytes("\n" + 
"\n" + 
"<p>cool "
);
static byte[] static_2 = getBytes("</p>"
);
	public SimpleTemp(OutputStream out) {
		super(out);
	}
	String blogTitle;
	public void render(
  String blogTitle
) {
		this.blogTitle = blogTitle;
		super.layout();
	}
	@Override protected void doLayout() {
p(static_0);// line 1
p(static_1);// line 3
p(blogTitle);// line 5
p(static_2);// line 5

	}
}
