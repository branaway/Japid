package japidviews.Application;
import java.util.*;
import java.io.*;
import cn.bran.japid.tags.Each;
import models.japidsample.*;
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
// NOTE: This file was generated from: japidviews/Application/renderByPosition.html
// Change to this file will be lost next time the template file is compiled.
//
@cn.bran.play.NoEnhance
public class renderByPosition extends cn.bran.play.JapidTemplateBase
{
	public static final String sourceTemplate = "japidviews/Application/renderByPosition.html";
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


	public renderByPosition() {
		super(null);
	}
	public renderByPosition(StringBuilder out) {
		super(out);
	}
/* based on https://github.com/branaway/Japid/issues/12
 */
	public static final String[] argNames = new String[] {/* args of the template*/"ss", "ii", "au1", "au2", "au22",  };
	public static final String[] argTypes = new String[] {/* arg types of the template*/"String", "int", "Author", "Author", "Author2",  };
	public static final Object[] argDefaults= new Object[] {null,null,null,null,null, };
	public static java.lang.reflect.Method renderMethod = getRenderMethod(japidviews.Application.renderByPosition.class);

	{
		setRenderMethod(renderMethod);
		setArgNames(argNames);
		setArgTypes(argTypes);
		setArgDefaults(argDefaults);
		setSourceTemplate(sourceTemplate);
	}
////// end of named args stuff

	private String ss; // line 2
	private int ii; // line 2
	private Author au1; // line 2
	private Author au2; // line 2
	private Author2 au22; // line 2
	public cn.bran.japid.template.RenderResult render(String ss,int ii,Author au1,Author au2,Author2 au22) {
		this.ss = ss;
		this.ii = ii;
		this.au1 = au1;
		this.au2 = au2;
		this.au22 = au22;
		long t = -1;
		try {super.layout();} catch (RuntimeException e) { super.handleException(e);} // line 2
		return new cn.bran.japid.template.RenderResultPartial(getHeaders(), getOut(), t, actionRunners, sourceTemplate);
	}
	@Override protected void doLayout() {
		beginDoLayout(sourceTemplate);
//------
;// line 1
		;// line 1
		p("\n" + 
"\n" + 
"got: ");// line 2
		p(ss);// line 4
		p("\n" + 
"got: ");// line 4
		p(ii);// line 5
		p("\n" + 
"got: ");// line 5
		p(au1.name);// line 6
		p(", ");// line 6
		p(au2.name);// line 6
		p(", ");// line 6
		p(au22.who);// line 6
		p("\n" + 
"\n" + 
"<p>Lets call a tag by name:</p>\n" + 
"\n");// line 6
		final tagPrimitives _tagPrimitives0 = new tagPrimitives(getOut()); _tagPrimitives0.setActionRunners(getActionRunners()).setOut(getOut()); _tagPrimitives0.render(named("s", "hello"), named("b", true), named("f", 1.2f), named("d", 3.6)); // line 10// line 10
		
		endDoLayout(sourceTemplate);
	}

}