package japidviews.Application;
import java.util.*;
import java.io.*;
import cn.bran.japid.tags.Each;
import static play.templates.JavaExtensions.*;
import static cn.bran.japid.util.WebUtils.*;
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
// NOTE: This file was generated from: japidviews/Application/index.html
// Change to this file will be lost next time the template file is compiled.
//
@cn.bran.play.NoEnhance
public class index extends main
{	public static final String sourceTemplate = "japidviews/Application/index.html";
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


	public index() {
		super(null);
	}
	public index(StringBuilder out) {
		super(out);
	}
	private Date now;
	public cn.bran.japid.template.RenderResult render(Date now) {
		this.now = now;
		long t = -1;
		super.layout();
		return new cn.bran.japid.template.RenderResult(this.headers, getOut(), t);
	}
	@Override protected void doLayout() {
//------
;// line 1
p("\n" + 
"\n" + 
"<p id=\"time\">\n" + 
"    <span>");// line 3
p(format(now, "EEEE',' MMMM dd',' yyyy"));// line 7
p("</span>\n" + 
"    ");// line 7
p(format(now, "hh'h' MM'mn' ss's'"));// line 8
p("\n" + 
"</p>\n" + 
"\n" + 
"<script type=\"text/javascript\" charset=\"utf-8\">\n" + 
"    setInterval(function() {\n" + 
"        $('section').load('");// line 8
p(lookup("index", new Object[]{}));// line 13
p(" #time')\n" + 
"    }, 1000)\n" + 
"</script>");// line 13

	}

	@Override protected void title() {
		p("Home");;
	}
}