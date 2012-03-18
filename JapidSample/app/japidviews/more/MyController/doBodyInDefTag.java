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
import play.i18n.Lang;
import japidviews._tags.*;
import play.mvc.Http.*;
import controllers.*;
import japidviews._javatags.*;
//
// NOTE: This file was generated from: japidviews/more/MyController/doBodyInDefTag.html
// Change to this file will be lost next time the template file is compiled.
//
@cn.bran.play.NoEnhance
public class doBodyInDefTag extends cn.bran.play.JapidTemplateBase
{
	public static final String sourceTemplate = "japidviews/more/MyController/doBodyInDefTag.html";
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


	public doBodyInDefTag() {
		super(null);
	}
	public doBodyInDefTag(StringBuilder out) {
		super(out);
	}
/* based on https://github.com/branaway/Japid/issues/12
 */
	public static final String[] argNames = new String[] {/* args of the template*/ };
	public static final String[] argTypes = new String[] {/* arg types of the template*/ };
	public static final Object[] argDefaults= new Object[] { };
	public static java.lang.reflect.Method renderMethod = getRenderMethod(japidviews.more.MyController.doBodyInDefTag.class);

	{
		setRenderMethod(renderMethod);
		setArgNames(argNames);
		setArgTypes(argTypes);
		setArgDefaults(argDefaults);
		setSourceTemplate(sourceTemplate);
	}
////// end of named args stuff

	{ setHasDoBody(); }
public cn.bran.japid.template.RenderResult render(DoBody body, cn.bran.japid.compiler.NamedArgRuntime... named) {
    Object[] args = buildArgs(named, body);
    try {return runRenderer(args);} catch(RuntimeException e) {handleException(e); throw e;} 
}

	DoBody body;
public static interface DoBody<A,B> {
		void render(A a,B b);
		void setBuffer(StringBuilder sb);
		void resetBuffer();
}
<A,B> String renderBody(A a,B b) {
		StringBuilder sb = new StringBuilder();
		if (body != null){
			body.setBuffer(sb);
			body.render( a, b);
			body.resetBuffer();
		}
		return sb.toString();
	}
	public cn.bran.japid.template.RenderResult render(DoBody body) {
		this.body = body;
		long t = -1;
		try {super.layout();} catch (RuntimeException e) { super.handleException(e);} 
		return new cn.bran.japid.template.RenderResultPartial(getHeaders(), getOut(), t, actionRunners, sourceTemplate);
	}
	public cn.bran.japid.template.RenderResult render() {
		long t = -1;
		try {super.layout();} catch (RuntimeException e) { super.handleException(e);} 
		return new cn.bran.japid.template.RenderResultPartial(getHeaders(), getOut(), t, actionRunners, sourceTemplate);
	}
	@Override protected void doLayout() {
		beginDoLayout(sourceTemplate);
//------
p("outside: ");// line 1
		if (body != null){ body.setBuffer(getOut()); body.render("taggy", 1); body.resetBuffer();}// line 1
		p("ok, try to get the content as method call: \n" + 
"   ");// line 1
		p(renderBody("taddy", 3));// line 4
		p("\n");// line 4
		// line 6
		p("call the def\n" + 
"\n");// line 8
		p(foo());// line 11
		p("\n");// line 11
		final fooTag _fooTag1 = new fooTag(getOut()); _fooTag1.setActionRunners(getActionRunners()).setOut(getOut()); _fooTag1.render(// line 13
new fooTag.DoBody(){ // line 13
public void render() { // line 13
// line 13
		p("  -> called footag:  ");// line 13
		if (body != null){ body.setBuffer(getOut()); body.render("kaddy", 13); body.resetBuffer();}// line 14

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
);// line 13
		;// line 15
		
		endDoLayout(sourceTemplate);
	}

public String foo() {
StringBuilder sb = new StringBuilder();
StringBuilder ori = getOut();
this.setOut(sb);
TreeMap<Integer, cn.bran.japid.template.ActionRunner> parentActionRunners = actionRunners;
actionRunners = new TreeMap<Integer, cn.bran.japid.template.ActionRunner>();
// line 6
		p("	hello ");// line 6
		if (body != null){ body.setBuffer(getOut()); body.render("saddy", 2); body.resetBuffer();}// line 7

this.setOut(ori);
if (actionRunners.size() > 0) {
	StringBuilder sb2 = new StringBuilder();
	int segStart = 0;
	for (Map.Entry<Integer, cn.bran.japid.template.ActionRunner> arEntry : actionRunners.entrySet()) {
		int pos = arEntry.getKey();
		sb2.append(sb.substring(segStart, pos));
		segStart = pos;
		cn.bran.japid.template.ActionRunner a = arEntry.getValue();
		sb2.append(a.run().getContent().toString());
	}
	sb2.append(sb.substring(segStart));
	actionRunners = parentActionRunners;
	return sb2.toString();
} else {
	actionRunners = parentActionRunners;
	return sb.toString();
}
}
}