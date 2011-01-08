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
// NOTE: This file was generated from: japidviews/templates/ImplicitObjects.html
// Change to this file will be lost next time the template file is compiled.
@cn.bran.play.NoEnhance
public class ImplicitObjects extends cn.bran.japid.template.JapidTemplateBase{
	public static final String sourceTemplate = "japidviews/templates/ImplicitObjects.html";
	public ImplicitObjects() {
		super(null);
	}
	public ImplicitObjects(StringBuilder out) {
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
		cn.bran.play.FieldErrors errors = new cn.bran.play.FieldErrors(validation);assert errors != null;
		play.Play _play = new play.Play(); assert _play != null;
p("\n" + 
"\n" + 
"<p>request: ");// line 1
p(request);// line 3
p("</p>\n" + 
"<p>response: ");// line 3
p(response);// line 4
p("</p>\n" + 
"<p>flash: ");// line 4
p(flash);// line 5
p("</p>\n" + 
"<p>errors: ");// line 5
p(errors);// line 6
p("</p>\n" + 
"<p>session: ");// line 6
p(session);// line 7
p("</p>\n" + 
"<p>renderArgs: ");// line 7
p(renderArgs);// line 8
p("</p>\n" + 
"<p>params: ");// line 8
p(params);// line 9
p("</p>\n" + 
"<p>validation: ");// line 9
p(validation);// line 10
p("</p>\n" + 
"<p>play: ");// line 10
p(_play);// line 11
p("</p>\n");// line 11

	}
}
