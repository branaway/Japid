package templates;
import java.util.*;
import java.io.*;
import tag.*;
import bran.japid.tags.Each;
import bran.Post;
import layout.*;
import static bran.WebUtils.*;
import static japidplay.PlayTemplateVarsAdapter.*;
// This file was generated from: templates/Set.html
// Change to this file will be lost next time the template file is compiled.
@bran.NoEnhance
public class Set extends layout.SetLayout{
	public static final String sourceTemplate = "templates/Set.html";
static byte[] static_0 = getBytes(""
);
static byte[] static_1 = getBytes("\n" + 
"");
static byte[] static_2 = getBytes("\n" + 
"\n" + 
"\n" + 
"");
static byte[] static_3 = getBytes("\n" + 
"");
static byte[] static_4 = getBytes("great footer"
);
static byte[] static_5 = getBytes("\n" + 
"");
	public Set(OutputStream out) {
		super(out);
	}
	String a;
	public void render(
String a
) {
		this.a = a;
		super.layout();
	}
	@Override protected void doLayout() {
p(static_0);// line 1
// line 1
// line 3
p(static_1);// line 4
p(static_2);// line 7
// line 10
p(static_3);// line 10
// line 11
p(static_5);// line 11

	}
	@Override protected void footer() {
		// line 11
p(static_4);// line 11
;
	}
	@Override protected void title() {
		p("Home" + a);;
	}
}
