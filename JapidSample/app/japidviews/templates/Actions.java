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
// NOTE: This file was generated from: japidviews/templates/Actions.html
// Change to this file will be lost next time the template file is compiled.
//
@cn.bran.play.NoEnhance
public class Actions extends cn.bran.play.JapidTemplateBase
{
	public static final String sourceTemplate = "japidviews/templates/Actions.html";
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


	public Actions() {
		super(null);
	}
	public Actions(StringBuilder out) {
		super(out);
	}
/* based on https://github.com/branaway/Japid/issues/12
 */
	public static final String[] argNames = new String[] {/* args of the template*/"post",  };
	public static final String[] argTypes = new String[] {/* arg types of the template*/"models.japidsample.Post",  };
	public static final Object[] argDefaults= new Object[] {null, };
	public static java.lang.reflect.Method renderMethod = getRenderMethod(japidviews.templates.Actions.class);

	{
		setRenderMethod(renderMethod);
		setArgNames(argNames);
		setArgTypes(argTypes);
		setArgDefaults(argDefaults);
		setSourceTemplate(sourceTemplate);
	}
////// end of named args stuff

	private models.japidsample.Post post; // line 1, japidviews/templates/Actions.html
	public cn.bran.japid.template.RenderResult render(models.japidsample.Post post) {
		this.post = post;
		long __t = -1;
		try {super.layout();} catch (RuntimeException __e) { super.handleException(__e);} // line 1, japidviews/templates/Actions.html
		return new cn.bran.japid.template.RenderResultPartial(getHeaders(), getOut(), __t, actionRunners, sourceTemplate);
	}

	public static cn.bran.japid.template.RenderResult apply(models.japidsample.Post post) {
		return new Actions().render(post);
	}

	@Override protected void doLayout() {
		beginDoLayout(sourceTemplate);
//------
;// line 1, Actions.html
		p("\n" + 
"\n" + 
"<form url=\"");// line 1, Actions.html
		p(lookup("showAll", new Object[]{}));// line 4, Actions.html
		p("\"></form>\n" + 
"<form url=\"");// line 4, Actions.html
		p(lookup("Clients.showAccounts", post.title, post.title));// line 5, Actions.html
		p("\"></form>\n" + 
"<form url=\"");// line 5, Actions.html
		p(lookupAbs("Clients.showAccounts", post.title.substring(1, 2)));// line 6, Actions.html
		p("\"></form>\n" + 
"<form url='");// line 6, Actions.html
		p(lookupAbs("Clients.showAccounts", new String[]{"aa", "bb"}));// line 7, Actions.html
		p("'></form>\n" + 
"<form url=\"");// line 7, Actions.html
		p(lookupStatic("/public/stylesheets/main.css"));// line 8, Actions.html
		p("\"></form>\n");// line 8, Actions.html
		
		endDoLayout(sourceTemplate);
	}

}