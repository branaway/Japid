package japidviews.templates;

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
// NOTE: This file was generated from: japidviews/templates/dumpPost.html
// Change to this file will be lost next time the template file is compiled.
//

@cn.bran.play.NoEnhance
public class dumpPost extends cn.bran.japid.template.JapidTemplateBase {
	public static final String sourceTemplate = "japidviews/templates/dumpPost.html";
	{
		headers.put("Content-Type", "text/html; charset=utf-8");
	}

	public dumpPost() {
		super(null);
	}

	public dumpPost(StringBuilder out) {
		super(out);
	}

	private String f1;
	private String f2;
	private String body;

	public cn.bran.japid.template.RenderResult render(String f1, String f2,
			String body) {
		this.f1 = f1;
		this.f2 = f2;
		this.body = body;
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
		p("\n");// line 1
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
				"<form method=\"POST\" action=\"/Application/dumpPost\">\n" +
				"	<input type=\"text\" width=\"30\" name=\"f1\" value=\"");// line 3
		try {
			p(f1);
		} catch (NullPointerException npe) {
		}// line 13
		p("\"/>\n" +
				"	<input type=\"text\" width=\"30\" name=\"f2\" value=\"");// line 13
		try {
			p(f2);
		} catch (NullPointerException npe) {
		}// line 14
		p("\"/>\n" +
				"	<input type=\"text\" width=\"50\" name=\"body\" value=\"");// line 14
		try {
			p(body);
		} catch (NullPointerException npe) {
		}// line 15
		p("\"/>\n" +
				"	<input type=\"submit\"/>\n" +
				"</form>\n" +
				"\n" +
				"</body>\n" +
				"</html>\n");// line 15

	}

}
