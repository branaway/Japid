package japidviews.Application;
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
// NOTE: This file was generated from: japidviews/Application/search.html
// Change to this file will be lost next time the template file is compiled.
//
@cn.bran.play.NoEnhance
public class search extends cn.bran.play.JapidTemplateBase
{
	public static final String sourceTemplate = "japidviews/Application/search.html";
	{
		putHeader("Content-Type", "text/html; charset=utf-8");
		setContentType("text/html; charset=utf-8");
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


	public search() {
		super(null);
	}
	public search(StringBuilder out) {
		super(out);
	}
/* based on https://github.com/branaway/Japid/issues/12
 */
	public static final String[] argNames = new String[] {/* args of the template*/"sp",  };
	public static final String[] argTypes = new String[] {/* arg types of the template*/"SearchParams",  };
	public static final Object[] argDefaults= new Object[] {null, };
	public static java.lang.reflect.Method renderMethod = getRenderMethod(japidviews.Application.search.class);

	{
		setRenderMethod(renderMethod);
		setArgNames(argNames);
		setArgTypes(argTypes);
		setArgDefaults(argDefaults);
		setSourceTemplate(sourceTemplate);
	}
////// end of named args stuff

	private SearchParams sp; // line 1
	public cn.bran.japid.template.RenderResult render(SearchParams sp) {
		this.sp = sp;
		long t = -1;
		try {super.layout();} catch (RuntimeException e) { super.handleException(e);} // line 1
		return new cn.bran.japid.template.RenderResultPartial(getHeaders(), getOut(), t, actionRunners, sourceTemplate);
	}
	@Override protected void doLayout() {
		beginDoLayout(sourceTemplate);
//------
;// line 1
		p("\n" + 
"\n");// line 1
		String nomode = "no mode";// line 3
		p("keys: ");// line 3
		try { Object o = sp.keywords ; if (o.toString().length() ==0) { p("没有 keywords"); } else { p(o); } } catch (NullPointerException npe) { p("没有 keywords"); }// line 5
		p(", mode: ");// line 5
		try { Object o = sp.mode ; if (o.toString().length() ==0) { p(nomode); } else { p(o); } } catch (NullPointerException npe) { p(nomode); }// line 5
		p("\n" + 
"true/false: ");// line 5
		p(true?"class=\"someclass\"":"");// line 7
		;// line 7
		
		endDoLayout(sourceTemplate);
	}

}