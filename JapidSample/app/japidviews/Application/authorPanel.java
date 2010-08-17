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
import static play.templates.JavaExtensions.*;
import static cn.bran.play.JapidPlayAdapter.*;
import static play.data.validation.Validation.*;
import static cn.bran.play.WebUtils.*;
// NOTE: This file was generated from: japidviews/Application/authorPanel.html
// Change to this file will be lost next time the template file is compiled.
@cn.bran.play.NoEnhance
public class authorPanel extends cn.bran.japid.template.JapidTemplateBase{
	public static final String sourceTemplate = "japidviews/Application/authorPanel.html";
static private final String static_0 = ""
;
static private final String static_1 = "\n" + 
"<p>author name: "
;
static private final String static_2 = "</p>\n" + 
"<p>his birthdate: "
;
static private final String static_3 = "</p>\n" + 
"<p>and his is a '"
;
static private final String static_4 = "'</p>\n" + 
"";
	public authorPanel() {
		super(null);
	}
	public authorPanel(StringBuilder out) {
		super(out);
	}
	models.japidsample.Author a;
	public cn.bran.japid.template.RenderResult render(models.japidsample.Author a) {
		this.a = a;
		long t = -1;
		super.layout();
		return new cn.bran.japid.template.RenderResultPartial(this.headers, getOut(), t, actionRunners);
	}
	@Override protected void doLayout() {
		play.mvc.Http.Request request = play.mvc.Http.Request.current();
		play.mvc.Http.Response response = play.mvc.Http.Response.current();
		play.mvc.Scope.Flash flash = play.mvc.Scope.Flash.current();
		play.mvc.Scope.Session session = play.mvc.Scope.Session.current();
		play.mvc.Scope.RenderArgs renderArgs = play.mvc.Scope.RenderArgs.current();
		play.mvc.Scope.Params params = play.mvc.Scope.Params.current();
		play.data.validation.Validation validation = play.data.validation.Validation.current();
		cn.bran.play.FieldErrors errors = new cn.bran.play.FieldErrors(validation.errors());
p(static_0);// line 1
p(static_1);// line 1
p(a.name);// line 3
p(static_2);// line 3
p(a.birthDate);// line 4
p(static_3);// line 4
p(a.getGender());// line 5
p(static_4);// line 5

	}
}
