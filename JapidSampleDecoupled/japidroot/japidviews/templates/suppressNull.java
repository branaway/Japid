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
import japidviews._tags.*;
import play.i18n.Lang;
import play.mvc.Http.*;
import controllers.*;
//
// NOTE: This file was generated from: japidviews/templates/suppressNull.html
// Change to this file will be lost next time the template file is compiled.
//
@cn.bran.play.NoEnhance
public class suppressNull extends cn.bran.play.JapidTemplateBase
{
	public static final String sourceTemplate = "japidviews/templates/suppressNull.html";
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


	public suppressNull() {
		super(null);
	}
	public suppressNull(StringBuilder out) {
		super(out);
	}
/* based on https://github.com/branaway/Japid/issues/12
 */
	public static final String[] argNames = new String[] {/* args of the template*/ };
	public static final String[] argTypes = new String[] {/* arg types of the template*/ };
	public static final Object[] argDefaults= new Object[] { };
	public static java.lang.reflect.Method renderMethod = getRenderMethod(japidviews.templates.suppressNull.class);

	{
		setRenderMethod(renderMethod);
		setArgNames(argNames);
		setArgTypes(argTypes);
		setArgDefaults(argDefaults);
		setSourceTemplate(sourceTemplate);
	}
////// end of named args stuff

	public cn.bran.japid.template.RenderResult render() {
		long __t = -1;
		try {super.layout();} catch (RuntimeException e) { super.handleException(e);} // line 0, japidviews/templates/suppressNull.html
		return new cn.bran.japid.template.RenderResultPartial(getHeaders(), getOut(), __t, actionRunners, sourceTemplate);
	}

	public static cn.bran.japid.template.RenderResult apply() {
		return new suppressNull().render();
	}

	@Override protected void doLayout() {
		beginDoLayout(sourceTemplate);
//------
;// line 1, suppressNull.html
		p("\n" + 
"\n");// line 1, suppressNull.html

String a = "a";// line 5, suppressNull.html
		p("<p>\n" + 
"safe to do a.something: ");// line 5, suppressNull.html
		try { p(a.length()); } catch (NullPointerException npe) {}// line 7, suppressNull.html
		p("\n" + 
"</p>\n" + 
"\n");// line 7, suppressNull.html
		 a = null;// line 10, suppressNull.html
		p("<p>\n" + 
"safe to do a.something too: ");// line 10, suppressNull.html
		try { p(a.length()); } catch (NullPointerException npe) {}// line 12, suppressNull.html
		p("\n" + 
"</p>\n");// line 12, suppressNull.html
		
		endDoLayout(sourceTemplate);
	}

}