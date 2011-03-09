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
// NOTE: This file was generated from: japidviews/Application/search.html
// Change to this file will be lost next time the template file is compiled.
//
@cn.bran.play.NoEnhance
public class search extends cn.bran.japid.template.JapidTemplateBase
{	public static final String sourceTemplate = "japidviews/Application/search.html";
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


	public search() {
		super(null);
	}
	public search(StringBuilder out) {
		super(out);
	}
	private SearchParams sp;
	public cn.bran.japid.template.RenderResult render(SearchParams sp) {
		this.sp = sp;
		long t = -1;
		super.layout();
		return new cn.bran.japid.template.RenderResult(this.headers, getOut(), t);
	}
	@Override protected void doLayout() {
//------
;// line 1
p(" keys: ");// line 1
try { Object o = sp.keywords; if (o.toString().length() ==0) { p("没有 keywords"); } else { p(o); } } catch (NullPointerException npe) { p("没有 keywords"); }// line 3
p(", mode: ");// line 3
try { Object o = sp.mode; if (o.toString().length() ==0) { p("no mode"); } else { p(o); } } catch (NullPointerException npe) { p("no mode"); }// line 3
;// line 3

	}

}