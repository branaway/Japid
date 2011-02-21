package japidviews.more.Portlets;
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
// NOTE: This file was generated from: japidviews/more/Portlets/panel1.html
// Change to this file will be lost next time the template file is compiled.
//
@cn.bran.play.NoEnhance
public class panel1 extends cn.bran.japid.template.JapidTemplateBase
{	public static final String sourceTemplate = "japidviews/more/Portlets/panel1.html";
{
	headers.put("Content-Type", "text/html; charset=utf-8");
}
	public panel1() {
		super(null);
	}
	public panel1(StringBuilder out) {
		super(out);
	}
	private String s;
	public cn.bran.japid.template.RenderResult render(String s) {
		this.s = s;
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
;// line 1
p("<p>panel-1 ");// line 1
p(s);// line 3
p(" : ");// line 3
p(new Date());// line 3
p("</p>\n");// line 3

	}

}