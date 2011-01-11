package japidviews.templates;

import java.util.*;
import java.io.*;
import cn.bran.japid.tags.Each;
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
import static play.templates.JavaExtensions.*;
import static cn.bran.play.JapidPlayAdapter.*;
import static play.data.validation.Validation.*;
import static cn.bran.play.WebUtils.*;
//
// NOTE: This file was generated from: japidviews/templates/SimpleTemp.html
// Change to this file will be lost next time the template file is compiled.

@cn.bran.play.NoEnhance
public class SimpleTemp extends cn.bran.japid.template.JapidTemplateBase {
	public static final String sourceTemplate = "japidviews/templates/SimpleTemp.html";

	public SimpleTemp() {
		super(null);
	}

	public SimpleTemp(StringBuilder out) {
		super(out);
	}

	String blogTitle;

	public cn.bran.japid.template.RenderResult render(
			String blogTitle
			) {
		this.blogTitle = blogTitle;
		long t = -1;
		super.layout();
		return new cn.bran.japid.template.RenderResultPartial(this.headers,
				getOut(), t, actionRunners);
	}

	@Override
	protected void doLayout() {
		//// -- set up the tag objects
		//// -- end of the tag objects

		////// - add implicit variables 

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

		////// - end of implicit variables 

		//------
		p("\n" +
				"\n" +
				"<p>cool ");// line 3
		p(blogTitle);// line 5
		p("</p>\n");// line 5

	}
}
