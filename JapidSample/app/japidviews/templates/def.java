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
// NOTE: This file was generated from: japidviews/templates/def.html
// Change to this file will be lost next time the template file is compiled.
//

@cn.bran.play.NoEnhance
public class def extends defLayout {
	public static final String sourceTemplate = "japidviews/templates/def.html";
	{
		headers.put("Content-Type", "text/html; charset=utf-8");
	}

	public def() {
		super(null);
	}

	public def(StringBuilder out) {
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

		// -- set up the tag objects
		final dummyTag _dummyTag2 = new dummyTag(getOut());
		_dummyTag2.setActionRunners(getActionRunners());

		// -- end of the tag objects

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
		p("\n");// line 4
		p("\n");// line 9
		p("\n");// line 14
		_dummyTag2.render(get("bar"));
		p("\n");// line 16
		p(get("foo"));// line 18

	}

	public String foo() {
		StringBuilder sb = new StringBuilder();
		StringBuilder ori = getOut();
		this.setOut(sb);
		p("	");// line 6
		String s = "hi";// line 7
		p("	hello ");// line 7
		p(s);// line 8
		p("!\n");// line 8

		this.setOut(ori);
		return sb.toString();
	}

	public String bar() {
		StringBuilder sb = new StringBuilder();
		StringBuilder ori = getOut();
		this.setOut(sb);
		p("	");// line 11
		String s = "hi2";// line 12
		p("	hi ");// line 12
		p(s);// line 13
		p("!\n");// line 13

		this.setOut(ori);
		return sb.toString();
	}
}
