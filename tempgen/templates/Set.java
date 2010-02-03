package templates;
import java.util.*;
import java.io.*;
import tag.*;
import bran.japid.tags.Each;
import bran.Post;
import layout.*;
import static bran.WebUtils.*;
// This file was generated from: templates/Set.html
// Change to this file will be lost next time the template file is compiled.
public class Set extends layout.SetLayout{
	public static final String sourceTemplate = "templates/Set.html";
static byte[] static_0 = getBytes(""
);
static byte[] static_1 = getBytes("\n" + 
"\n" + 
"\n" + 
"");
static byte[] static_2 = getBytes("\n" + 
"");
static byte[] static_3 = getBytes("great footer"
);
static byte[] static_4 = getBytes("\n" + 
"");
	public Set(OutputStream out) {
		super(out);
	}
	public void render() {
		super.layout();
	}
	@Override protected void doLayout() {
p(static_0);// line 1
// line 1
p(static_1);// line 3
// line 6
p(static_2);// line 6
// line 7
p(static_4);// line 7

	}
	@Override protected void footer() {
		// line 7
p(static_3);// line 7
;
	}
	@Override protected void title() {
		p("Home");;
	}
}
