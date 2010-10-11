package japidviews.Application;
import java.util.*;
import java.io.*;
import cn.bran.japid.tags.Each;
import cn.bran.japid.template.ActionRunner;
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
// NOTE: This file was generated from: japidviews/Application/testCacheFor.html
// Change to this file will be lost next time the template file is compiled.
@cn.bran.play.NoEnhance
public class testCacheFor extends cn.bran.japid.template.JapidTemplateBase{
	public static final String sourceTemplate = "japidviews/Application/testCacheFor.html";
static private final String static_0 = ""
;
static private final String static_1 = "\n" + 
"<html>\n" + 
"\n" + 
"<body>\n" + 
"<p>heheh</p>\n" + 
"\n" + 
"	<p>hello "
;
static private final String static_2 = ", "
;
static private final String static_3 = ",</p> \n" + 
"	<p>directly, now seconds is "
;
static private final String static_4 = "</p>\n" + 
"\n" + 
"</body>\n" + 
"</html>"
;
	public testCacheFor() {
		super(null);
	}
	public testCacheFor(StringBuilder out) {
		super(out);
	}
	String a;
	public cn.bran.japid.template.RenderResult render(String a) {
		this.a = a;
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
p(a);// line 8
p(static_2);// line 8
		actionRunners.put(getOut().length(), new cn.bran.play.CacheablePlayActionRunner("", "Application.every3", "") {
			@Override
			public void runPlayAction() throws cn.bran.play.JapidResult {
				Application.every3(); //
			}
		});

// line 8
p(static_3);// line 8
		actionRunners.put(getOut().length(), new cn.bran.play.CacheablePlayActionRunner("", "Application.seconds", "") {
			@Override
			public void runPlayAction() throws cn.bran.play.JapidResult {
				Application.seconds(); //
			}
		});

// line 9
p(static_4);// line 9

	}
}
