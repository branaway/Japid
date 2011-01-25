package japidviews.Application;

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
// NOTE: This file was generated from: japidviews/Application/cacheWithRenderJapid.html
// Change to this file will be lost next time the template file is compiled.
//

@cn.bran.play.NoEnhance
public class cacheWithRenderJapid extends
		cn.bran.japid.template.JapidTemplateBase {
	public static final String sourceTemplate = "japidviews/Application/cacheWithRenderJapid.html";
	{
		headers.put("Content-Type", "text/html; charset=utf-8");
	}

	public cacheWithRenderJapid() {
		super(null);
	}

	public cacheWithRenderJapid(StringBuilder out) {
		super(out);
	}

	String a;

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
				"hello ");// line 1
		p(a);// line 10
		p("</body>\n" +
				"</html>\n");// line 10

	}

}
