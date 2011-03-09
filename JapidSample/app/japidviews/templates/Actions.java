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
// NOTE: This file was generated from: japidviews/templates/Actions.html
// Change to this file will be lost next time the template file is compiled.
//
@cn.bran.play.NoEnhance
public class Actions extends cn.bran.japid.template.JapidTemplateBase
{	public static final String sourceTemplate = "japidviews/templates/Actions.html";
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


	public Actions() {
		super(null);
	}
	public Actions(StringBuilder out) {
		super(out);
	}
	private models.japidsample.Post post;
	public cn.bran.japid.template.RenderResult render(models.japidsample.Post post) {
		this.post = post;
		long t = -1;
		super.layout();
		return new cn.bran.japid.template.RenderResult(this.headers, getOut(), t);
	}
	@Override protected void doLayout() {
//------
;// line 1
p("\n" + 
"\n" + 
"<form url=\"");// line 1
p(lookup("showAll", new Object[]{}));// line 4
p("\"></form>\n" + 
"<form url=\"");// line 4
p(lookup("Clients.showAccounts", post.title, post.title));// line 5
p("\"></form>\n" + 
"<form url=\"");// line 5
p(lookupAbs("Clients.showAccounts", post.title.substring(1, 2)));// line 6
p("\"></form>\n" + 
"<form url='");// line 6
p(lookupAbs("Clients.showAccounts", new String[]{"aa", "bb"}));// line 7
p("'></form>\n" + 
"<form url=\"");// line 7
p(lookupStatic("/public/stylesheets/main.css"));// line 8
p("\"></form>\n");// line 8

	}

}