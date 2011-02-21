package japidviews.more.Portlets;
import java.util.*;
import java.io.*;
import cn.bran.japid.tags.Each;
import cn.bran.japid.template.ActionRunner;
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
// NOTE: This file was generated from: japidviews/more/Portlets/index.html
// Change to this file will be lost next time the template file is compiled.
//
@cn.bran.play.NoEnhance
public class index extends cn.bran.japid.template.JapidTemplateBase
{	public static final String sourceTemplate = "japidviews/more/Portlets/index.html";
{
	headers.put("Content-Type", "text/html; charset=utf-8");
}
	public index() {
		super(null);
	}
	public index(StringBuilder out) {
		super(out);
	}
	private String a;
	private String b;
	public cn.bran.japid.template.RenderResult render(String a, String b) {
		this.a = a;
		this.b = b;
		long t = -1;
		super.layout();
		return new cn.bran.japid.template.RenderResultPartial(this.headers, getOut(), t, actionRunners);
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
p("\n" + 
"<p>The outer most content is cached for 20 seconds, using the CacheFor annotation. ");// line 1
p(new Date());// line 4
p("</p>\n" + 
"\n" + 
"<div>\n" + 
"	<p>this part is never cached.</p>\n" + 
"	");// line 4
		actionRunners.put(getOut().length(), new cn.bran.play.CacheablePlayActionRunner("", controllers.more.Portlets.class, "panel1", a) {
			@Override
			public void runPlayAction() throws cn.bran.play.JapidResult {
				controllers.more.Portlets.panel1(a); //
			}
		});

p("</div>\n" + 
"\n" + 
"<div>\n" + 
"    <p>this part is cached for 10 seconds. Note the timeout spec with invoke overrides CacheFor annotation. </p>\n" + 
"    ");// line 8
		actionRunners.put(getOut().length(), new cn.bran.play.CacheablePlayActionRunner("10s", controllers.more.Portlets.class, "panel2", b) {
			@Override
			public void runPlayAction() throws cn.bran.play.JapidResult {
				controllers.more.Portlets.panel2(b); //
			}
		});

p("</div>\n" + 
"\n" + 
"<div>\n" + 
"    <p>this part is cached for 4 seconds, \n" + 
"    specified with CacheFor annotation in the controller. </p>\n" + 
"    ");// line 13
		actionRunners.put(getOut().length(), new cn.bran.play.CacheablePlayActionRunner("", controllers.more.Portlets.class, "panel3", a + b) {
			@Override
			public void runPlayAction() throws cn.bran.play.JapidResult {
				controllers.more.Portlets.panel3(a + b); //
			}
		});

p("</div>\n");// line 19

	}

}