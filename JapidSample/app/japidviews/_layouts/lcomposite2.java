package japidviews._layouts;
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
// NOTE: This file was generated from: japidviews/_layouts/lcomposite2.html
// Change to this file will be lost next time the template file is compiled.
//
@cn.bran.play.NoEnhance
public abstract class lcomposite2 extends cn.bran.japid.template.JapidTemplateBase
{	public static final String sourceTemplate = "japidviews/_layouts/lcomposite2.html";
{
	headers.put("Content-Type", "text/html; charset=utf-8");
}
	public lcomposite2() {
		super(null);
	}
	public lcomposite2(StringBuilder out) {
		super(out);
	}
	@Override public void layout() {
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


		p("<p>beginning: lcomposite</p>\n" + 
"\n" + 
"    ");// line 1
p("\n" + 
"\n");// line 3
		actionRunners.put(getOut().length(), new cn.bran.play.CacheablePlayActionRunner("", "controllers.Application.foo", "") {
			@Override
			public void runPlayAction() throws cn.bran.play.JapidResult {
				super.checkActionCacheFor(controllers.Application.class, "foo");
				controllers.Application.foo(); //
			}
		});

p("\n");// line 5
	doLayout();
p("\n" + 
"<p>back to layout</p>\n" + 
"\n" + 
"\n");// line 7
		actionRunners.put(getOut().length(), new cn.bran.play.CacheablePlayActionRunner("", "controllers.Application.foo", "") {
			@Override
			public void runPlayAction() throws cn.bran.play.JapidResult {
				super.checkActionCacheFor(controllers.Application.class, "foo");
				controllers.Application.foo(); //
			}
		});

p("\n" + 
"<p>back to layout again</p>\n" + 
"\n" + 
"<p>end of lcomposite</p>\n");// line 12
	}

	protected abstract void doLayout();
}