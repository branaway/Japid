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
import play.mvc.Http.*;
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
static private final String static_1 = "\n" + 
"\n" + 
"";
static private final String static_2 = "\n" + 
"\n" + 
"";
static private final String static_3 = "\n" + 
"	"
;
static private final String static_4 = "	hello "
;
static private final String static_5 = "!\n" + 
"";
static private final String static_6 = "\n" + 
"\n" + 
"";
static private final String static_7 = "\n" + 
"	"
;
static private final String static_8 = "	hi "
;
static private final String static_9 = "!\n" + 
"";
static private final String static_10 = "\n" + 
"\n" + 
"";
static private final String static_11 = "\n" + 
"\n" + 
"";
static private final String static_12 = "\n" + 
"";
	public def() {
		super(null);
	}
	public def(StringBuilder out) {
		super(out);
	}
	public cn.bran.japid.template.RenderResult render() {
		long t = -1;
		super.layout();
		return new cn.bran.japid.template.RenderResultPartial(this.headers, getOut(), t, actionRunners);
	}
	@Override protected void doLayout() {

		play.mvc.Http.Request request = play.mvc.Http.Request.current(); assert request != null;
		play.mvc.Http.Response response = play.mvc.Http.Response.current(); assert response != null;
		play.mvc.Scope.Flash flash = play.mvc.Scope.Flash.current();assert flash != null;
		play.mvc.Scope.Session session = play.mvc.Scope.Session.current();assert session != null;
		play.mvc.Scope.RenderArgs renderArgs = play.mvc.Scope.RenderArgs.current(); assert renderArgs != null;
		play.mvc.Scope.Params params = play.mvc.Scope.Params.current();assert params != null;
		play.data.validation.Validation validation = play.data.validation.Validation.current();assert validation!= null;
		cn.bran.play.FieldErrors errors = new cn.bran.play.FieldErrors(validation.errors());assert errors != null;
		play.Play _play = new play.Play(); assert _play != null;
p(static_0);// line 1
p(static_1);// line 1
p(static_2);// line 4
// line 6
p(static_6);// line 9
// line 11
p(static_10);// line 14
_dummyTag2.setActionRunners(getActionRunners());
_dummyTag2.render(get("bar"));
// line 16
p(static_11);// line 16
p(get("foo"));// line 18
p(static_12);// line 18

	}
	private dummyTag _dummyTag2 = new dummyTag(getOut());
public String foo() {
StringBuilder sb = new StringBuilder();
StringBuilder ori = getOut();
this.setOut(sb);
// line 6
p(static_3);// line 6
String s = "hi";// line 7
p(static_4);// line 7
p(s);// line 8
p(static_5);// line 8

this.setOut(ori);
return sb.toString();
}
public String bar() {
StringBuilder sb = new StringBuilder();
StringBuilder ori = getOut();
this.setOut(sb);
// line 11
p(static_7);// line 11
String s = "hi2";// line 12
p(static_8);// line 12
p(s);// line 13
p(static_9);// line 13

this.setOut(ori);
return sb.toString();
}
}
