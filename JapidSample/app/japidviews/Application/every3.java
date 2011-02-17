package japidviews.Application;

import java.util.*;
import java.io.*;
import cn.bran.japid.tags.Each;
import cn.bran.japid.template.ActionRunner;
import static play.templates.JavaExtensions.*;
import static cn.bran.play.JapidPlayAdapter.*;
import static play.data.validation.Validation.*;
import static cn.bran.play.WebUtils.*;
import japidviews._layouts.*;
import static japidviews._javatags.JapidWebUtil.*;
import play.data.validation.Validation;
import play.mvc.Scope.*;
import models.*;
import play.data.validation.Error;
import japidviews._tags.*;
import controllers.*;
import play.mvc.Http.*;
import japidviews._javatags.*;
//
// NOTE: This file was generated from: japidviews/Application/every3.html
// Change to this file will be lost next time the template file is compiled.
//

@cn.bran.play.NoEnhance
public class every3 extends cn.bran.japid.template.JapidTemplateBase {
	public static final String sourceTemplate = "japidviews/Application/every3.html";
	{
		headers.put("Content-Type", "text/html; charset=utf-8");
	}

	public every3() {
		super(null);
	}

	public every3(StringBuilder out) {
		super(out);
	}

	private String a;

	public cn.bran.japid.template.RenderResult render(String a) {
		this.a = a;
		long t = -1;
		super.layout();
		return new cn.bran.japid.template.RenderResultPartial(this.headers,
				getOut(), t, actionRunners);
	}

	@Override
	protected void doLayout() {

		// - add implicit variables 

		final Request request = Request.current();
		assert request != null;

		final Response response = Response.current();
		assert response != null;

		final Flash flash = Flash.current();
		assert flash != null;

		final Session session = Session.current();
		assert session != null;

		final RenderArgs renderArgs = RenderArgs.current();
		assert renderArgs != null;

		final Params params = Params.current();
		assert params != null;

		final Validation validation = Validation.current();
		assert validation != null;

		final cn.bran.play.FieldErrors errors = new cn.bran.play.FieldErrors(
				validation);
		assert errors != null;

		final play.Play _play = new play.Play();
		assert _play != null;

		// - end of implicit variables 

		//------
		p("every3: now is ");// line 1
		p(a);// line 3
		p(", ");// line 3
		actionRunners.put(getOut().length(),
				new cn.bran.play.CacheablePlayActionRunner("",
						Application.class, "seconds", "") {
					@Override
					public void runPlayAction() throws cn.bran.play.JapidResult {
						Application.seconds(); //
					}
				});

	}

}
