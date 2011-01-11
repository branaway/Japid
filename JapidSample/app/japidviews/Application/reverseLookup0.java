package japidviews.Application;

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
// NOTE: This file was generated from: japidviews/Application/reverseLookup0.html
// Change to this file will be lost next time the template file is compiled.

@cn.bran.play.NoEnhance
public class reverseLookup0 extends cn.bran.japid.template.JapidTemplateBase {
	public static final String sourceTemplate = "japidviews/Application/reverseLookup0.html";

	public reverseLookup0() {
		super(null);
	}

	public reverseLookup0(StringBuilder out) {
		super(out);
	}

	public cn.bran.japid.template.RenderResult render() {
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
		p("<!DOCTYPE html PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\" \"http://www.w3.org/TR/html4/loose.dtd\">\n"
				+
				"<html>\n"
				+
				"<head>\n"
				+
				"<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\n"
				+
				"<title>Insert title here</title>\n" +
				"</head>\n" +
				"<body>\n" +
				"<h1>actions</h1>\n" +
				"<p><a href='");// line 1
		p(lookup("japid.SampleController.reverseLookup0", new Object[] {}));// line 9
		p("'>Action notation </a></p>\n"
				+
				"<p><a href='/japid.SampleController/reverseLookup1?agrs=order0&args=order2'>action reverse lookup cannot handle arrays or collections...</a></p>\n"
				+
				"</body>\n" +
				"</html>\n");// line 9

	}
}
