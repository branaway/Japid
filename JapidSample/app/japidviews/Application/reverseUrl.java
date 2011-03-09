package japidviews.Application;
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
// NOTE: This file was generated from: japidviews/Application/reverseUrl.html
// Change to this file will be lost next time the template file is compiled.
//
@cn.bran.play.NoEnhance
public class reverseUrl extends cn.bran.japid.template.JapidTemplateBase
{	public static final String sourceTemplate = "japidviews/Application/reverseUrl.html";
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


	public reverseUrl() {
		super(null);
	}
	public reverseUrl(StringBuilder out) {
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
String name = "bran";// line 1
p("<a href=\"");// line 1
p(lookup("validate", name));// line 2
p("\">simple reverse lookup</a>\n" + 
"<p/>\n" + 
"<a href=\"");// line 2
p(lookupAbs("validate", name, 12));// line 4
p("\">simple reverse lookup with absolute url</a>\n" + 
"<p/>\n" + 
"<form action=\"");// line 4
p(lookup("validate", new Object[]{}));// line 6
p("\">\n" + 
"    <input type=\"hidden\" name=\"name\" value=\"bran\"/>\n" + 
"    <input type=\"hidden\" name=\"age\" value=\"12\"/>\n" + 
"    <input type=\"submit\"/>\n" + 
"</form>\n" + 
"<p/>\n" + 
"<a href=\"");// line 6
p(lookup("more.MyController.echo", name));// line 12
p("\">simple reverse lookup</a>\n" + 
"<p/>\n" + 
"\n" + 
"\n");// line 12

	}

}