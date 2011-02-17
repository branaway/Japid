package japidviews._tags;

import java.util.*;
import java.io.*;
import cn.bran.japid.tags.Each;
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
// NOTE: This file was generated from: japidviews/_tags/moreTag.html
// Change to this file will be lost next time the template file is compiled.
//

@cn.bran.play.NoEnhance
public class moreTag extends cn.bran.japid.template.JapidTemplateBase {
	public static final String sourceTemplate = "japidviews/_tags/moreTag.html";
	{
		headers.put("Content-Type", "text/html; charset=utf-8");
	}

	public moreTag() {
		super(null);
	}

	public moreTag(StringBuilder out) {
		super(out);
	}

	private String s;
	private DoBody body;

	public static interface DoBody<A> {
		void render(A a);
	}

	public cn.bran.japid.template.RenderResult render(String s, DoBody body) {
		this.body = body;
		this.s = s;
		long t = -1;
		super.layout();
		return new cn.bran.japid.template.RenderResult(this.headers, getOut(),
				t);
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
		p("<p/>\n" +
				"ok\n" +
				"\n" +
				"<p/>\n" +
				"\n");// line 1
		if (body != null)
			body.render(s);
		p("<p/>\n" +
				"mmm\n");// line 8

	}

}
