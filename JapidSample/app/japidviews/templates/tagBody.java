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
// NOTE: This file was generated from: japidviews/templates/tagBody.html
// Change to this file will be lost next time the template file is compiled.
//
@cn.bran.play.NoEnhance
public class tagBody extends cn.bran.play.JapidTemplateBase
{
	public static final String sourceTemplate = "japidviews/templates/tagBody.html";
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


	public tagBody() {
		super(null);
	}
	public tagBody(StringBuilder out) {
		super(out);
	}
/* based on https://github.com/branaway/Japid/issues/12
 */
	public static final String[] argNames = new String[] {/* args of the template*/ };
	public static final String[] argTypes = new String[] {/* arg types of the template*/ };
	public static final Object[] argDefaults= new Object[] { };
	public static java.lang.reflect.Method renderMethod = getRenderMethod(japidviews.templates.tagBody.class);

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
		try {super.layout();} catch (RuntimeException __e) { super.handleException(__e);} // line 0, japidviews/templates/tagBody.html
		return new cn.bran.japid.template.RenderResultPartial(getHeaders(), getOut(), __t, actionRunners, sourceTemplate);
	}

	public static cn.bran.japid.template.RenderResult apply() {
		return new tagBody().render();
	}

	@Override protected void doLayout() {
		beginDoLayout(sourceTemplate);
//------
p("\n" + 
"Note: the last | is the separator for the tag arguments and the call back parameters. \n" + 
"It must present even if the parameters are empty.\n" + 
"<p/>\n" + 
"before\n" + 
"\n" + 
"<p/>\n");// line 1, tagBody.html
		final fooTag _fooTag0 = new fooTag(getOut()); _fooTag0.setActionRunners(getActionRunners()).setOut(getOut()); _fooTag0.render(// line 8, tagBody.html
"hi", new fooTag.DoBody(){ // line 8, tagBody.html
public void render() { // line 8, tagBody.html
// line 8, tagBody.html
    final String echo = "secret";// line 9, tagBody.html
		p("\n" + 
"    well ");// line 9, tagBody.html
		p(echo);// line 11, tagBody.html
		p("\n" + 
"\n" + 
"	");// line 11, tagBody.html
		final anotherTag _anotherTag1 = new anotherTag(getOut()); _anotherTag1.setActionRunners(getActionRunners()).setOut(getOut()); _anotherTag1.render(// line 13, tagBody.html
echo, new anotherTag.DoBody<String>(){ // line 13, tagBody.html
public void render(final String what) { // line 13, tagBody.html
// line 13, tagBody.html
		p("	    got ");// line 13, tagBody.html
		p(what);// line 14, tagBody.html
		p(" and ");// line 14, tagBody.html
		p(echo);// line 14, tagBody.html
		p("\n" + 
"		");// line 14, tagBody.html
		final moreTag _moreTag2 = new moreTag(getOut()); _moreTag2.setActionRunners(getActionRunners()).setOut(getOut()); _moreTag2.render(// line 15, tagBody.html
echo, new moreTag.DoBody<String>(){ // line 15, tagBody.html
public void render(final String more) { // line 15, tagBody.html
// line 15, tagBody.html
		p("		   got ");// line 15, tagBody.html
		p(what);// line 16, tagBody.html
		p(" and ");// line 16, tagBody.html
		p(echo);// line 16, tagBody.html
		p(" and ");// line 16, tagBody.html
		p(more);// line 16, tagBody.html
		p("\n" + 
"		");// line 16, tagBody.html
		
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
);// line 15, tagBody.html
	
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
);// line 13, tagBody.html

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
);// line 8, tagBody.html
		p("<p/>\n" + 
"after");// line 19, tagBody.html
		
		endDoLayout(sourceTemplate);
	}

}