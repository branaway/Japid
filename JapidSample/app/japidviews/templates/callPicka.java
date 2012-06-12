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
// NOTE: This file was generated from: japidviews/templates/callPicka.html
// Change to this file will be lost next time the template file is compiled.
//
@cn.bran.play.NoEnhance
public class callPicka extends cn.bran.play.JapidTemplateBase
{
	public static final String sourceTemplate = "japidviews/templates/callPicka.html";
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


	public callPicka() {
		super(null);
	}
	public callPicka(StringBuilder out) {
		super(out);
	}
/* based on https://github.com/branaway/Japid/issues/12
 */
	public static final String[] argNames = new String[] {/* args of the template*/ };
	public static final String[] argTypes = new String[] {/* arg types of the template*/ };
	public static final Object[] argDefaults= new Object[] { };
	public static java.lang.reflect.Method renderMethod = getRenderMethod(japidviews.templates.callPicka.class);

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
		return new cn.bran.japid.template.RenderResultPartial(getHeaders(), getOut(), t, actionRunners, sourceTemplate);
	}
	@Override protected void doLayout() {
		beginDoLayout(sourceTemplate);
//------
p("<p>beginning...<p>\n" + 
"<p>\n" + 
"<p>call a simple tag</p>\n" + 
"\n" + 
"Another simple tag aTag, which locates in the same directory as this template:\n" + 
"\n" + 
"first define something in a Java code block. \n" + 
"\n");// line 1
		p("\n" + 
"\n");// line 11
		 List<String> strings = new ArrayList<String>(){{add("you");add("me");add("everyone");}};// line 13
		p("\n");// line 13
		final aTag _aTag0 = new aTag(getOut()); _aTag0.setActionRunners(getActionRunners()).setOut(getOut()); _aTag0.render(strings); // line 15// line 15
		p("\n" + 
"note: the picka tag is defined in the japidviews/_tags directory\n" + 
"\n");// line 15
		final picka _picka1 = new picka(getOut()); _picka1.setActionRunners(getActionRunners()).setOut(getOut()); _picka1.render(// line 19
"a", "b" + "c", new picka.DoBody<String>(){ // line 19
public void render(final String rr) { // line 19
// line 19
		p("    the tag chosed: ");// line 19
		p(rr);// line 20
		p("\n" + 
"    <p>and we can can call a tag recursively?</p>\n" + 
"    ");// line 20
		final SampleTag _SampleTag2 = new SampleTag(getOut()); _SampleTag2.setActionRunners(getActionRunners()).setOut(getOut()); _SampleTag2.render(rr); // line 22// line 22

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
);// line 19
		p("</p>\n" + 
"\n" + 
"<p>\n" + 
"we can call without the body part:\n" + 
"\n");// line 23
		final picka _picka3 = new picka(getOut()); _picka3.setActionRunners(getActionRunners()).setOut(getOut()); _picka3.render(named("a", "aa"), named("b", "bb")); // line 29// line 29
		p("\n" + 
"or \n");// line 29
		final picka _picka4 = new picka(getOut()); _picka4.setActionRunners(getActionRunners()).setOut(getOut()); _picka4.render("cc","dd"); // line 32// line 32
		p("\n" + 
"</p>\n" + 
"<p>\n" + 
"Or using the full path of the tag\n" + 
"</p>\n" + 
"\n");// line 32
		final japidviews.templates.aTag _japidviews_templates_aTag5 = new japidviews.templates.aTag(getOut()); _japidviews_templates_aTag5.setActionRunners(getActionRunners()).setOut(getOut()); _japidviews_templates_aTag5.render(strings); // line 39// line 39
		p("\n" + 
"<p>You can use \".\" instead of \"/\" on the path:</p>\n" + 
"\n");// line 39
		final japidviews.templates.aTag _japidviews_templates_aTag6 = new japidviews.templates.aTag(getOut()); _japidviews_templates_aTag6.setActionRunners(getActionRunners()).setOut(getOut()); _japidviews_templates_aTag6.render(strings); // line 43// line 43
		;// line 43
		
		endDoLayout(sourceTemplate);
	}

}