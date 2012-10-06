package japidviews.templates;
import java.util.*;
import java.io.*;
import cn.bran.japid.tags.Each;
import static play.templates.JavaExtensions.*;
import static cn.bran.play.JapidPlayAdapter.*;
import static play.data.validation.Validation.*;
import japidviews._layouts.*;
import play.i18n.Messages;
import play.data.validation.Validation;
import play.mvc.Scope.*;
import models.*;
import play.data.validation.Error;
import play.i18n.Lang;
import japidviews._tags.*;
import play.mvc.Http.*;
import controllers.*;
//
// NOTE: This file was generated from: japidviews/templates/dumpPost.html
// Change to this file will be lost next time the template file is compiled.
//
public class dumpPost extends cn.bran.play.JapidTemplateBase
{
	public static final String sourceTemplate = "japidviews/templates/dumpPost.html";
	{
		putHeader("Content-Type", "text/html; charset=utf-8");
		setContentType("text/html; charset=utf-8");
	}

// - add implicit fields with Play

	final play.mvc.Http.Request request = play.mvc.Http.Request.current(); 
	final play.mvc.Http.Response response = play.mvc.Http.Response.current(); 
	final play.mvc.Scope.Session session = play.mvc.Scope.Session.current();
	final play.mvc.Scope.RenderArgs renderArgs = play.mvc.Scope.RenderArgs.current();
	final play.mvc.Scope.Params params = play.mvc.Scope.Params.current();
	final play.data.validation.Validation validation = play.data.validation.Validation.current();
	final cn.bran.play.FieldErrors errors = new cn.bran.play.FieldErrors(validation);
	final play.Play _play = new play.Play(); 

// - end of implicit fields with Play 


	public dumpPost() {
		super(null);
	}
	public dumpPost(StringBuilder out) {
		super(out);
	}
/* based on https://github.com/branaway/Japid/issues/12
 */
	public static final String[] argNames = new String[] {/* args of the template*/"f1", "f2", "body",  };
	public static final String[] argTypes = new String[] {/* arg types of the template*/"String", "String", "String",  };
	public static final Object[] argDefaults= new Object[] {null,null,null, };
	public static java.lang.reflect.Method renderMethod = getRenderMethod(japidviews.templates.dumpPost.class);

	{
		setRenderMethod(renderMethod);
		setArgNames(argNames);
		setArgTypes(argTypes);
		setArgDefaults(argDefaults);
		setSourceTemplate(sourceTemplate);
	}
////// end of named args stuff

	private String f1; // line 2
	private String f2; // line 2
	private String body; // line 2
	public cn.bran.japid.template.RenderResult render(String f1,String f2,String body) {
		this.f1 = f1;
		this.f2 = f2;
		this.body = body;
		long t = -1;
		try {super.layout();} catch (RuntimeException e) { super.handleException(e);} // line 2
		return new cn.bran.japid.template.RenderResultPartial(getHeaders(), getOut(), t, actionRunners, sourceTemplate);
	}
	@Override protected void doLayout() {
		beginDoLayout(sourceTemplate);
//------
p("\n");// line 1
p("\n" + 
"<!DOCTYPE html PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\" \"http://www.w3.org/TR/html4/loose.dtd\">\n" + 
"<html>\n" + 
"<head>\n" + 
"<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\n" + 
"<title>Insert title here</title>\n" + 
"</head>\n" + 
"<body>\n" + 
"<form method=\"POST\" action=\"/Application/dumpPost\">\n" + 
"	<input type=\"text\" width=\"30\" name=\"f1\" value=\"");// line 3
		try { p(f1); } catch (NullPointerException npe) {}// line 13
		p("\"/>\n" + 
"	<input type=\"text\" width=\"30\" name=\"f2\" value=\"");// line 13
		try { p(f2); } catch (NullPointerException npe) {}// line 14
		p("\"/>\n" + 
"	<input type=\"text\" width=\"50\" name=\"body\" value=\"");// line 14
		try { p(body); } catch (NullPointerException npe) {}// line 15
		p("\"/>\n" + 
"	<input type=\"submit\"/>\n" + 
"</form>\n" + 
"\n" + 
"</body>\n" + 
"</html>");// line 15
		
		endDoLayout(sourceTemplate);
	}

}