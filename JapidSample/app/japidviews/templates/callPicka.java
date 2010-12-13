package japidviews.templates;
import java.util.*;
import java.io.*;
import cn.bran.japid.tags.Each;
import japidviews._layouts.*;
import static  japidviews._javatags.JapidWebUtil.*;
import play.data.validation.Validation;
import play.mvc.Scope.*;
import models.*;
import play.data.validation.Error;
import japidviews._tags.*;
import controllers.*;
import play.mvc.Http.*;
import japidviews._javatags.*;
import static play.templates.JavaExtensions.*;
import static cn.bran.play.JapidPlayAdapter.*;
import static play.data.validation.Validation.*;
import static cn.bran.play.WebUtils.*;
// NOTE: This file was generated from: japidviews/templates/callPicka.html
// Change to this file will be lost next time the template file is compiled.
@cn.bran.play.NoEnhance
public class callPicka extends cn.bran.japid.template.JapidTemplateBase{
	public static final String sourceTemplate = "japidviews/templates/callPicka.html";
static private final String static_0 = "before...\n" + 
"\n" + 
"note: the picka tag is defined in the japidviews/_tags directory\n" + 
"\n";
static private final String static_1 = "\n" + 
"    the tag chosed: ";
static private final String static_2 = "\n";
static private final String static_3 = "\n" + 
"after...\n" + 
"\n" + 
"Another simple tag aTag, which locates in the same directory as this template:\n" + 
"\n" + 
"first define something in a Java code block. \n" + 
"\n";
static private final String static_4 = "\n" + 
"<p>now call a simple tag</p>\n" + 
"\n";
static private final String static_5 = "\n" + 
"\n" + 
"<p>Or using the full path of the tag</p>\n" + 
"\n";
static private final String static_6 = "\n" + 
"\n" + 
"<p>You can use \".\" instead of \"/\" in the path:</p>\n" + 
"\n";
static private final String static_7 = "";
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

		play.mvc.Http.Request request = play.mvc.Http.Request.current(); assert request != null;
		play.mvc.Http.Response response = play.mvc.Http.Response.current(); assert response != null;
		play.mvc.Scope.Flash flash = play.mvc.Scope.Flash.current();assert flash != null;
		play.mvc.Scope.Session session = play.mvc.Scope.Session.current();assert session != null;
		play.mvc.Scope.RenderArgs renderArgs = play.mvc.Scope.RenderArgs.current(); assert renderArgs != null;
		play.mvc.Scope.Params params = play.mvc.Scope.Params.current();assert params != null;
		play.data.validation.Validation validation = play.data.validation.Validation.current();assert validation!= null;
		cn.bran.play.FieldErrors errors = new cn.bran.play.FieldErrors(validation);assert errors != null;
		play.Play _play = new play.Play(); assert _play != null;
p(static_0);// line 1
_picka0.setActionRunners(getActionRunners());
_picka0.render("a", "b" + "c", _picka0DoBody);
// line 5
p(static_3);// line 7
List<String> strings = new ArrayList<String>(){{add("you");add("me");add("them");}};// line 15
p(static_4);// line 15
_aTag1.setActionRunners(getActionRunners());
_aTag1.render(strings);
// line 19
p(static_5);// line 19
_japidviews_templates_aTag2.setActionRunners(getActionRunners());
_japidviews_templates_aTag2.render(strings);
// line 23
p(static_6);// line 23
_japidviews_templates_aTag3.setActionRunners(getActionRunners());
_japidviews_templates_aTag3.render(strings);
// line 27
p(static_7);// line 27

	}
	private picka _picka0 = new picka(getOut());
class picka0DoBody implements picka.DoBody< String>{
	public void render(String r) {
		// line 5
p(static_1);// line 5
p(r);// line 6
p(static_2);// line 6

	}
}
	private picka0DoBody _picka0DoBody = new picka0DoBody();

	private aTag _aTag1 = new aTag(getOut());
	private japidviews.templates.aTag _japidviews_templates_aTag2 = new japidviews.templates.aTag(getOut());
	private japidviews.templates.aTag _japidviews_templates_aTag3 = new japidviews.templates.aTag(getOut());
}
