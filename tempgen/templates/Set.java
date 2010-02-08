package templates;
import java.util.*;
import java.io.*;
import cn.bran.japid.tags.Each;
import japidviews._layouts.*;
import japidviews._tags.*;
import static cn.bran.play.PlayTemplateVarsAdapter.*;
// NOTE: This file was generated from: templates/Set.html
// Change to this file will be lost next time the template file is compiled.
@cn.bran.play.NoEnhance
public class Set extends japidviews._layouts.SetLayout{
	public static final String sourceTemplate = "templates/Set.html";
	public static final String contentType = "text/html";
static String static_0 = ""
;
static String static_1 = "\n" + 
"";
static String static_2 = "\n" + 
"\n" + 
"\n" + 
"";
static String static_3 = "\n" + 
"";
static String static_4 = "great footer"
;
static String static_5 = "\n" + 
"";
	public Set(StringBuilder out) {
		super(out);
	}
	String a;
	public cn.bran.japid.template.RenderResult render(
String a
) {
		this.a = a;
		long t = -1;
		super.layout();
		return new cn.bran.japid.template.RenderResult(this.contentType, getOut(), t);
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
