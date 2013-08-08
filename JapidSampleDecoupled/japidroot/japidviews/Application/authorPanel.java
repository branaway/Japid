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
import japidviews._tags.*;
import play.i18n.Lang;
import play.mvc.Http.*;
import controllers.*;
//
// NOTE: This file was generated from: japidviews/Application/authorPanel.html
// Change to this file will be lost next time the template file is compiled.
//
@cn.bran.play.NoEnhance
public class authorPanel extends cn.bran.play.JapidTemplateBase
{
	public static final String sourceTemplate = "japidviews/Application/authorPanel.html";
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


	public authorPanel() {
		super(null);
	}
	public authorPanel(StringBuilder out) {
		super(out);
	}
/* based on https://github.com/branaway/Japid/issues/12
 */
	public static final String[] argNames = new String[] {/* args of the template*/"a",  };
	public static final String[] argTypes = new String[] {/* arg types of the template*/"models.japidsample.Author",  };
	public static final Object[] argDefaults= new Object[] {null, };
	public static java.lang.reflect.Method renderMethod = getRenderMethod(japidviews.Application.authorPanel.class);

	{
		setRenderMethod(renderMethod);
		setArgNames(argNames);
		setArgTypes(argTypes);
		setArgDefaults(argDefaults);
		setSourceTemplate(sourceTemplate);
	}
////// end of named args stuff

	private models.japidsample.Author a; // line 1, japidviews/Application/authorPanel.html
	public cn.bran.japid.template.RenderResult render(models.japidsample.Author a) {
		this.a = a;
		long __t = -1;
		try {super.layout();} catch (RuntimeException e) { super.handleException(e);} // line 1, japidviews/Application/authorPanel.html
		return new cn.bran.japid.template.RenderResultPartial(getHeaders(), getOut(), __t, actionRunners, sourceTemplate);
	}

	public static cn.bran.japid.template.RenderResult apply(models.japidsample.Author a) {
		return new authorPanel().render(a);
	}

	@Override protected void doLayout() {
		beginDoLayout(sourceTemplate);
//------
;// line 1, authorPanel.html
		p("\n" + 
"<p>author name: ");// line 1, authorPanel.html
		p(a.name);// line 3, authorPanel.html
		p("</p>\n" + 
"<p>his birthdate: ");// line 3, authorPanel.html
		p(a.birthDate);// line 4, authorPanel.html
		p("</p>\n" + 
"<p>and his is a '");// line 4, authorPanel.html
		p(a.getGender());// line 5, authorPanel.html
		p("'</p>\n" + 
"    ");// line 5, authorPanel.html
		final SampleTag _SampleTag0 = new SampleTag(getOut()); _SampleTag0.setActionRunners(getActionRunners()).setOut(getOut()); _SampleTag0.render("end"); // line 6, authorPanel.html// line 6, authorPanel.html
		p("    ");// line 6, authorPanel.html
		
		endDoLayout(sourceTemplate);
	}

}