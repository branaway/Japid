package japidviews.templates;
import java.util.*;
import java.io.*;
import cn.bran.japid.tags.Each;
import static play.templates.JavaExtensions.*;
import static cn.bran.play.JapidPlayAdapter.*;
import static play.data.validation.Validation.*;
import static cn.bran.play.WebUtils.*;
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
//
// NOTE: This file was generated from: japidviews/templates/log.html
// Change to this file will be lost next time the template file is compiled.
//
@cn.bran.play.NoEnhance
public class log extends cn.bran.japid.template.JapidTemplateBase
{	public static final String sourceTemplate = "japidviews/templates/log.html";
{
	headers.put("Content-Type", "text/html; charset=utf-8");
}
	public log() {
		super(null);
	}
	public log(StringBuilder out) {
		super(out);
	}
	public cn.bran.japid.template.RenderResult render() {
		long t = -1;
		super.layout();
		return new cn.bran.japid.template.RenderResult(this.headers, getOut(), t);
	}
	@Override protected void doLayout() {

// - add implicit variables 

		final Request request = Request.current(); assert request != null;

		final Response response = Response.current(); assert response != null;

		final Flash flash = Flash.current();assert flash != null;

		final Session session = Session.current();assert session != null;

		final RenderArgs renderArgs = RenderArgs.current(); assert renderArgs != null;

		final Params params = Params.current();assert params != null;

		final Validation validation = Validation.current();assert validation!= null;

		final cn.bran.play.FieldErrors errors = new cn.bran.play.FieldErrors(validation);assert errors != null;

		final play.Play _play = new play.Play(); assert _play != null;

// - end of implicit variables 


//------
p("\n" + 
"log directives are used to print a line of information to the console. \n" + 
"It can take an argument of String\n" + 
"</p>\n");// line 1
System.out.println("japidviews/templates/log.html(line 5): " + "");
p("</p>\n" + 
"\n" + 
"hello world!\n" + 
"</p>\n" + 
"\n");// line 5
 String a = "a";// line 11
 int i = 10;// line 12
p("now with argument\n");// line 12
System.out.println("japidviews/templates/log.html(line 14): " + a + i);
p("\n" + 
"</p>\n" + 
"now with a message literal\n");// line 14
System.out.println("japidviews/templates/log.html(line 18): " + "a message ");
p("</p>");// line 18

	}

}