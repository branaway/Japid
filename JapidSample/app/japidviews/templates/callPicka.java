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


	public callPicka() {
		super(null);
	}
	public callPicka(StringBuilder out) {
		super(out);
	}
	public cn.bran.japid.template.RenderResult render() {
		long t = -1;
		super.layout();
		return new cn.bran.japid.template.RenderResultPartial(this.headers, getOut(), t, actionRunners);
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
((aTag)(new aTag(getOut()).setActionRunners(getActionRunners()))).render(strings);
// line 12
p("\n" + 
"note: the picka tag is defined in the japidviews/_tags directory\n" + 
"\n");// line 12
((picka)(new picka(getOut())).setActionRunners(getActionRunners())).render("a", "b" + "c", new picka.DoBody<String>(){
public void render(final String r) {
// line 16
p("    the tag chosed: ");// line 16
p(r);// line 17
p("\n" + 
"    <p>and we can can call a tag recurive?</p>\n" + 
"    ");// line 17
((SampleTag)(new SampleTag(getOut()).setActionRunners(getActionRunners()))).render(r);
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
((japidviews.templates.aTag)(new japidviews.templates.aTag(getOut()).setActionRunners(getActionRunners()))).render(strings);
// line 27
p("\n" + 
"<p>You can use \".\" instead of \"/\" in the path:</p>\n" + 
"\n");// line 27
((japidviews.templates.aTag)(new japidviews.templates.aTag(getOut()).setActionRunners(getActionRunners()))).render(strings);
// line 31
;// line 31

	}

}