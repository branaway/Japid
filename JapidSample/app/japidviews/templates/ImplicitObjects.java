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
import japidviews._javatags.*;
//
// NOTE: This file was generated from: japidviews/templates/ImplicitObjects.html
// Change to this file will be lost next time the template file is compiled.
//
@cn.bran.play.NoEnhance
public class ImplicitObjects extends cn.bran.play.JapidTemplateBase
{	public static final String sourceTemplate = "japidviews/templates/ImplicitObjects.html";
{
putHeader("Content-Type", "text/html; charset=utf-8");
}

// - add implicit fields with Play

	final Request request = Request.current(); 
	final Response response = Response.current(); 
	final Session session = Session.current();
	final RenderArgs renderArgs = RenderArgs.current();
	final Params params = Params.current();
	final Validation validation = Validation.current();
	final cn.bran.play.FieldErrors errors = new cn.bran.play.FieldErrors(validation);
	final play.Play _play = new play.Play(); 

// - end of implicit fields with Play 


	public ImplicitObjects() {
		super(null);
	}
	public ImplicitObjects(StringBuilder out) {
		super(out);
	}
/* based on https://github.com/branaway/Japid/issues/12
 */
public static final String[] argNames = new String[] {/* args of the template*/ };
public static final String[] argTypes = new String[] {/* arg types of the template*/ };
public static final Object[] argDefaults= new Object[] { };
public static java.lang.reflect.Method renderMethod = getRenderMethod(japidviews.templates.ImplicitObjects.class);

{
	setRenderMethod(renderMethod);
	setArgNames(argNames);
	setArgTypes(argTypes);
	setArgDefaults(argDefaults);
	setSourceTemplate(sourceTemplate);

}
////// end of named args stuff

	public cn.bran.japid.template.RenderResult render() {
		long t = -1;
		try {super.layout();} catch (RuntimeException e) { super.handleException(e);} 
		return new cn.bran.japid.template.RenderResultPartial(getHeaders(), getOut(), t, actionRunners);
	}
	@Override protected void doLayout() {
//------
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
		p("</p>");// line 11
		
	}

}