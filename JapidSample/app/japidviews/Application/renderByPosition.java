package japidviews.Application;
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
import models.japidsample.Author2;
import models.japidsample.Author;
import static play.templates.JavaExtensions.*;
import static cn.bran.play.JapidPlayAdapter.*;
import static play.data.validation.Validation.*;
import static cn.bran.play.WebUtils.*;
// NOTE: This file was generated from: japidviews/Application/renderByPosition.html
// Change to this file will be lost next time the template file is compiled.
@cn.bran.play.NoEnhance
public class renderByPosition extends cn.bran.japid.template.JapidTemplateBase{
	public static final String sourceTemplate = "japidviews/Application/renderByPosition.html";
static private final String static_0 = ""
;
static private final String static_1 = ""
;
static private final String static_2 = ""
;
static private final String static_3 = "\n" + 
"got: "
;
static private final String static_4 = "\n" + 
"got: "
;
static private final String static_5 = "\n" + 
"got: "
;
static private final String static_6 = ", "
;
static private final String static_7 = ", "
;
static private final String static_8 = "\n" + 
"\n" + 
"";
	public renderByPosition() {
		super(null);
	}
	public renderByPosition(StringBuilder out) {
		super(out);
	}
	String ss;
	int ii;
	Author au1;
	Author au2;
	Author2 au22;
	public cn.bran.japid.template.RenderResult render(String ss, int ii, Author au1, Author au2, Author2 au22) {
		this.ss = ss;
		this.ii = ii;
		this.au1 = au1;
		this.au2 = au2;
		this.au22 = au22;
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
p(static_3);// line 3
p(ss);// line 5
p(static_4);// line 5
p(ii);// line 6
p(static_5);// line 6
p(au1.name);// line 7
p(static_6);// line 7
p(au2.name);// line 7
p(static_7);// line 7
p(au22.who);// line 7
p(static_8);// line 7

	}
}
