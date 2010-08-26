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
// NOTE: This file was generated from: japidviews/templates/suppressNull.html
// Change to this file will be lost next time the template file is compiled.
@cn.bran.play.NoEnhance
public class suppressNull extends cn.bran.japid.template.JapidTemplateBase{
	public static final String sourceTemplate = "japidviews/templates/suppressNull.html";
static private final String static_0 = ""
;
static private final String static_1 = "\n" + 
"";
static private final String static_2 = "\n" + 
"";
static private final String static_3 = "safe to do a.something: "
;
static private final String static_4 = "\n" + 
"\n" + 
"";
static private final String static_5 = "safe to do a.something too: "
;
static private final String static_6 = "\n" + 
"";
	public suppressNull() {
		super(null);
	}
	public suppressNull(StringBuilder out) {
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
p(static_2);// line 2
String a = "a";// line 4
p(static_3);// line 4
try { p(a.length()); } catch (NullPointerException npe) {}// line 5
p(static_4);// line 5
a = null;// line 7
p(static_5);// line 7
try { p(a.length()); } catch (NullPointerException npe) {}// line 8
p(static_6);// line 8

	}
}
