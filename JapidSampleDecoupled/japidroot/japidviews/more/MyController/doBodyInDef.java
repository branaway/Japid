package japidviews.more.MyController;
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
// NOTE: This file was generated from: japidviews/more/MyController/doBodyInDef.html
// Change to this file will be lost next time the template file is compiled.
//
@cn.bran.play.NoEnhance
public class doBodyInDef extends cn.bran.play.JapidTemplateBase
{
	public static final String sourceTemplate = "japidviews/more/MyController/doBodyInDef.html";
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


	public doBodyInDef() {
		super(null);
	}
	public doBodyInDef(StringBuilder out) {
		super(out);
	}
/* based on https://github.com/branaway/Japid/issues/12
 */
	public static final String[] argNames = new String[] {/* args of the template*/ };
	public static final String[] argTypes = new String[] {/* arg types of the template*/ };
	public static final Object[] argDefaults= new Object[] { };
	public static java.lang.reflect.Method renderMethod = getRenderMethod(japidviews.more.MyController.doBodyInDef.class);

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
		try {super.layout();} catch (RuntimeException e) { super.handleException(e);} // line 0, japidviews/more/MyController/doBodyInDef.html
		return new cn.bran.japid.template.RenderResultPartial(getHeaders(), getOut(), __t, actionRunners, sourceTemplate);
	}

	public static cn.bran.japid.template.RenderResult apply() {
		return new doBodyInDef().render();
	}

	@Override protected void doLayout() {
		beginDoLayout(sourceTemplate);
//------
p("\n");// line 1, doBodyInDef.html
		final doBodyInDefTag _doBodyInDefTag0 = new doBodyInDefTag(getOut()); _doBodyInDefTag0.setActionRunners(getActionRunners()).setOut(getOut()); _doBodyInDefTag0.render(// line 2, doBodyInDef.html
new doBodyInDefTag.DoBody<String, Integer>(){ // line 2, doBodyInDef.html
public void render(final String c, final Integer i) { // line 2, doBodyInDef.html
// line 2, doBodyInDef.html
		p("  my body plus ");// line 2, doBodyInDef.html
		p(c);// line 3, doBodyInDef.html
		p(", ");// line 3, doBodyInDef.html
		p(i);// line 3, doBodyInDef.html
		;// line 3, doBodyInDef.html
		
}

StringBuilder oriBuffer;
@Override
public void setBuffer(StringBuilder sb) {
	oriBuffer = getOut();
	setOut(sb);
}

@Override
public void resetBuffer() {
	setOut(oriBuffer);
}

}
);// line 2, doBodyInDef.html
		
		endDoLayout(sourceTemplate);
	}

}