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
// NOTE: This file was generated from: japidviews/templates/callPicka.html
// Change to this file will be lost next time the template file is compiled.
//
@cn.bran.play.NoEnhance
public class callPicka extends cn.bran.japid.template.JapidTemplateBase
{	public static final String sourceTemplate = "japidviews/templates/callPicka.html";
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
}
////// end of named args stuff

	public cn.bran.japid.template.RenderResult render() {
		long t = -1;
		super.layout();
		return new cn.bran.japid.template.RenderResultPartial(getHeaders(), getOut(), t, actionRunners);
	}
	@Override protected void doLayout() {

// -- set up the tag objects
final aTag _aTag0 = new aTag(getOut());
{ _aTag0.setActionRunners(getActionRunners()); }

final SampleTag _SampleTag2 = new SampleTag(getOut());
{ _SampleTag2.setActionRunners(getActionRunners()); }

final picka _picka1 = new picka(getOut());
{ _picka1.setActionRunners(getActionRunners()); }

final picka _picka3 = new picka(getOut());
{ _picka3.setActionRunners(getActionRunners()); }

final picka _picka4 = new picka(getOut());
{ _picka4.setActionRunners(getActionRunners()); }

final japidviews.templates.aTag _japidviews_templates_aTag5 = new japidviews.templates.aTag(getOut());
{ _japidviews_templates_aTag5.setActionRunners(getActionRunners()); }

final japidviews.templates.aTag _japidviews_templates_aTag6 = new japidviews.templates.aTag(getOut());
{ _japidviews_templates_aTag6.setActionRunners(getActionRunners()); }

// -- end of the tag objects

//------
p("<p>before...<p>\n" + 
"<p>\n" + 
"<p>call a simple tag</p>\n" + 
"\n" + 
"Another simple tag aTag, which locates in the same directory as this template:\n" + 
"\n" + 
"first define something in a Java code block. \n" + 
"\n");// line 1
 List<String> strings = new ArrayList<String>(){{add("you");add("me");add("them");}};// line 10
p("\n");// line 10
_aTag0.setOut(getOut()); _aTag0.render(strings);
// line 12
p("\n" + 
"note: the picka tag is defined in the japidviews/_tags directory\n" + 
"\n");// line 12
_picka1.setOut(getOut()); _picka1.render("a", "b" + "c", new picka.DoBody<String>(){
public void render(final String r) {
// line 16
p("    the tag chosed: ");// line 16
p(r);// line 17
p("\n" + 
"    <p>and we can can call a tag recurive?</p>\n" + 
"    ");// line 17
_SampleTag2.setOut(getOut()); _SampleTag2.render(r);
// line 19

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
);
// line 16
p("</p>\n" + 
"\n" + 
"<p>\n" + 
"we can call without the body part:\n" + 
"\n");// line 20
_picka3.setOut(getOut()); _picka3.render(named("a", "aa"), named("b", "bb"));
// line 26
p("\n" + 
"or \n");// line 26
_picka4.setOut(getOut()); _picka4.render("cc", "dd");
// line 29
p("\n" + 
"</p>\n" + 
"<p>\n" + 
"Or using the full path of the tag\n" + 
"</p>\n" + 
"\n");// line 29
_japidviews_templates_aTag5.setOut(getOut()); _japidviews_templates_aTag5.render(strings);
// line 36
p("\n" + 
"<p>You can use \".\" instead of \"/\" in the path:</p>\n" + 
"\n");// line 36
_japidviews_templates_aTag6.setOut(getOut()); _japidviews_templates_aTag6.render(strings);
// line 40
;// line 40

	}

}