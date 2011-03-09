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
// NOTE: This file was generated from: japidviews/templates/def.html
// Change to this file will be lost next time the template file is compiled.
//
@cn.bran.play.NoEnhance
public class def extends defLayout
{	public static final String sourceTemplate = "japidviews/templates/def.html";
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
final dummyTag _dummyTag1 = new dummyTag(getOut());
{ _dummyTag1.setActionRunners(getActionRunners()); }

final dummyTag _dummyTag4 = new dummyTag(getOut());
{ _dummyTag4.setActionRunners(getActionRunners()); }

// -- end of the tag objects

	public def() {
		super(null);
	}
	public def(StringBuilder out) {
		super(out);
	}
	public cn.bran.japid.template.RenderResult render() {
		long t = -1;
		super.layout();
		return new cn.bran.japid.template.RenderResult(this.headers, getOut(), t);
	}
	@Override protected void doLayout() {
//------
;// line 1
p("\n" + 
"<p>check 1</p>\n");// line 1
p("\n" + 
"\n" + 
"\n");// line 4
// line 7
p("\n" + 
"<p>check 2</p>\n");// line 12
// line 15
p("<p>check 3</p>\n" + 
"\n");// line 18
p("\n" + 
"\n");// line 21
// line 23
p("\n" + 
"\n" + 
"<p>check 4</p>\n");// line 26
_dummyTag4.setOut(getOut()); _dummyTag4.render(get("bar"));
// line 29
p("\n" + 
"\n");// line 29
p("\n" + 
"<p>check 5</p>\n" + 
"\n");// line 31
p(foo());// line 34
p("\n" + 
"\n" + 
"<p>check 6</p>");// line 34

	}

public String foo2(String p) {
StringBuilder sb = new StringBuilder();
StringBuilder ori = getOut();
this.setOut(sb);
// line 7
p("	<p>hi ");// line 7
p(p);// line 8
p("!,</p> \n" + 
"	<p>from ");// line 8
p(request.action);// line 9
p("</p>\n" + 
"	<p>OK you can call a tag:</p>\n" + 
"	");// line 9
_dummyTag1.setOut(getOut()); _dummyTag1.render(p);
// line 11

this.setOut(ori);
return sb.toString();
}
public String foo() {
StringBuilder sb = new StringBuilder();
StringBuilder ori = getOut();
this.setOut(sb);
// line 15
p("	");// line 15
String s = "hi there";// line 16
p("	<p>foo hello ");// line 16
p(foo2(s));// line 17
p("</p>\n");// line 17

this.setOut(ori);
return sb.toString();
}
public String bar() {
StringBuilder sb = new StringBuilder();
StringBuilder ori = getOut();
this.setOut(sb);
// line 23
p("\n" + 
"	");// line 23
String s = "hi2";// line 24
p("	<p>bar hi ");// line 24
p(s);// line 25
p("!</p>\n");// line 25

this.setOut(ori);
return sb.toString();
}
}