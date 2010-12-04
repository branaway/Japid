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
// NOTE: This file was generated from: japidviews/templates/openBrace.html
// Change to this file will be lost next time the template file is compiled.
@cn.bran.play.NoEnhance
public class openBrace extends cn.bran.japid.template.JapidTemplateBase{
	public static final String sourceTemplate = "japidviews/templates/openBrace.html";
static private final String static_0 = "<!DOCTYPE html PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\" \"http://www.w3.org/TR/html4/loose.dtd\">\n" + 
"<html>\n" + 
"<head>\n" + 
"<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\n" + 
"<title>Insert title here</title>\n" + 
"</head>\n" + 
"<body>\n" + 
"<p>hello</p>\n";
static private final String static_1 = "        <p>in while ";
static private final String static_2 = "</p>\n" + 
"    ";
static private final String static_3 = "    <p>good2</p>\n";
static private final String static_4 = "\n" + 
"  ";
static private final String static_5 = "    <p>good 3</p>\n" + 
"  ";
static private final String static_6 = "\n" + 
"</body>\n" + 
"</html>";
	public openBrace() {
		super(null);
	}
	public openBrace(StringBuilder out) {
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
p(static_0);// line 1
int i = 3;// line 9
if(true)// line 10
{// line 11
    while (i-- > 0)// line 12
    {// line 13
p(static_1);// line 13
p(i);// line 14
p(static_2);// line 14
}// line 15
p(static_3);// line 15
}// line 17
p(static_4);// line 17
if (true) {// line 19
p(static_5);// line 19
}// line 21
p(static_6);// line 21

	}
}
