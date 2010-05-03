package japidviews.templates;
import java.util.*;
import java.io.*;
import cn.bran.japid.tags.Each;
import japidviews._layouts.*;
import static  japidviews._javatags.JapidWebUtil.*;
import play.data.validation.Validation;
import play.mvc.Scope.*;
import models.*;
import play.data.validation.Error;
import japidviews._tags.*;
import controllers.*;
import japidviews._javatags.*;
import static play.templates.JavaExtensions.*;
import static cn.bran.play.JapidPlayAdapter.*;
import static play.data.validation.Validation.*;
import static cn.bran.play.WebUtils.*;
// NOTE: This file was generated from: japidviews/templates/def.html
// Change to this file will be lost next time the template file is compiled.
@cn.bran.play.NoEnhance
public class def extends defLayout{
	public static final String sourceTemplate = "japidviews/templates/def.html";
static private final String static_0 = ""
;
static private final String static_1 = ""
;
static private final String static_2 = "\n" + 
"";
static private final String static_3 = "\n" + 
"\n" + 
"";
static private final String static_4 = "\n" + 
"	hello "
;
static private final String static_5 = "!\n" + 
"";
static private final String static_6 = "\n" + 
"\n" + 
"";
static private final String static_7 = "\n" + 
"	hi "
;
static private final String static_8 = "!\n" + 
"";
static private final String static_9 = "\n" + 
"\n" + 
"";
static private final String static_10 = "\n" + 
"\n" + 
"";
static private final String static_11 = "\n" + 
"";
	public def() {
		super(null);
	}
	public def(StringBuilder out) {
		super(out);
	}
	String s;
	public cn.bran.japid.template.RenderResult render(String s) {
		this.s = s;
		long t = -1;
		super.layout();
		return new cn.bran.japid.template.RenderResultPartial(this.headers, getOut(), t, actionRunners);
	}
	@Override protected void doLayout() {
p(static_0);// line 1
p(static_1);// line 1
p(static_2);// line 2
p(static_3);// line 4
// line 6
p(static_6);// line 8
// line 10
p(static_9);// line 12
_dummyTag2.setActionRunners(getActionRunners());
_dummyTag2.render(get("bar"));
// line 14
p(static_10);// line 14
p(get("foo"));// line 16
p(static_11);// line 16

	}
	private dummyTag _dummyTag2 = new dummyTag(getOut());
public String foo() {
StringBuilder sb = new StringBuilder();
StringBuilder ori = getOut();
this.setOut(sb);
// line 6
p(static_4);// line 6
p(s);// line 7
p(static_5);// line 7

this.setOut(ori);
return sb.toString();
}
public String bar() {
StringBuilder sb = new StringBuilder();
StringBuilder ori = getOut();
this.setOut(sb);
// line 10
p(static_7);// line 10
p(s);// line 11
p(static_8);// line 11

this.setOut(ori);
return sb.toString();
}
}
