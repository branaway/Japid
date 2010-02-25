package templates;
import java.util.*;
import java.io.*;
import cn.bran.japid.tags.Each;
import japidviews._layouts.*;
import japidviews._tags.*;
import static cn.bran.play.JapidPlayAdapter.*;
// NOTE: This file was generated from: templates/Set.html
// Change to this file will be lost next time the template file is compiled.
@cn.bran.play.NoEnhance
public class Set extends japidviews._layouts.SetLayout{
	public static final String sourceTemplate = "templates/Set.html";
static private final String static_0 = ""
;
static private final String static_1 = ""
;
static private final String static_2 = "\n" + 
"\n" + 
"";
static private final String static_3 = "\n" + 
"";
static private final String static_4 = "great footer"
;
static private final String static_5 = "\n" + 
"";
	public Set() {
		super(null);
	}
	public Set(StringBuilder out) {
		super(out);
	}
	String a;
	public cn.bran.japid.template.RenderResult render(String a) {
		this.a = a;
		long t = -1;
		super.layout();
		return new cn.bran.japid.template.RenderResult(this.headers, getOut(), t);
	}
	@Override protected void doLayout() {
p(static_0);// line 1
p(static_1);// line 1
p(static_2);// line 2
// line 5
p(static_3);// line 5
// line 6
p(static_5);// line 6

	}
	@Override protected void footer() {
		// line 6
p(static_4);// line 6
;
	}
	@Override protected void title() {
		p("Home" + a);;
	}
}
