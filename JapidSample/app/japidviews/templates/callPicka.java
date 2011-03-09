package japidviews.templates;
import java.util.*;
import java.io.*;
import cn.bran.japid.tags.Each;
import static play.templates.JavaExtensions.*;
import static cn.bran.play.JapidPlayAdapter.*;
import static play.data.validation.Validation.*;
import japidviews._layouts.*;
import play.i18n.Messages;
import static  japidviews._javatags.JapidWebUtil.*;
import play.data.validation.Validation;
import play.mvc.Scope.*;
import models.*;
import play.data.validation.Error;
import play.i18n.Lang;
import japidviews._tags.*;
import controllers.*;
import play.mvc.Http.*;
import japidviews._javatags.*;
//
// NOTE: This file was generated from: japidviews/templates/callPicka.html
// Change to this file will be lost next time the template file is compiled.
//
@cn.bran.play.NoEnhance
public class callPicka extends cn.bran.japid.template.JapidTemplateBase
{	public static final String sourceTemplate = "japidviews/templates/callPicka.html";
{
	headers.put("Content-Type", "text/html; charset=utf-8");
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



// -- set up the tag objects
final aTag _aTag0 = new aTag(getOut());
{ _aTag0.setActionRunners(getActionRunners()); }

final SampleTag _SampleTag2 = new SampleTag(getOut());
{ _SampleTag2.setActionRunners(getActionRunners()); }

final picka _picka1 = new picka(getOut());
{ _picka1.setActionRunners(getActionRunners()); }

final japidviews.templates.aTag _japidviews_templates_aTag3 = new japidviews.templates.aTag(getOut());
{ _japidviews_templates_aTag3.setActionRunners(getActionRunners()); }

final japidviews.templates.aTag _japidviews_templates_aTag4 = new japidviews.templates.aTag(getOut());
{ _japidviews_templates_aTag4.setActionRunners(getActionRunners()); }

// -- end of the tag objects

	public callPicka() {
		super(null);
	}
	public callPicka(StringBuilder out) {
		super(out);
	}
	public cn.bran.japid.template.RenderResult render() {
		long t = -1;
		super.layout();
		return new cn.bran.japid.template.RenderResult(this.headers, getOut(), t);
	}
	@Override protected void doLayout() {
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
}
);
// line 16
p("</p>\n" + 
"\n" + 
"<p>\n" + 
"Or using the full path of the tag\n" + 
"</p>\n" + 
"\n");// line 20
_japidviews_templates_aTag3.setOut(getOut()); _japidviews_templates_aTag3.render(strings);
// line 27
p("\n" + 
"<p>You can use \".\" instead of \"/\" in the path:</p>\n" + 
"\n");// line 27
_japidviews_templates_aTag4.setOut(getOut()); _japidviews_templates_aTag4.render(strings);
// line 31
;// line 31

	}

}