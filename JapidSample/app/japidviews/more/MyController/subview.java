package japidviews.more.MyController;

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
// NOTE: This file was generated from: japidviews/more/MyController/subview.html
// Change to this file will be lost next time the template file is compiled.
//

@cn.bran.play.NoEnhance
public class subview extends superview {
	public static final String sourceTemplate = "japidviews/more/MyController/subview.html";
	{
		headers.put("Content-Type", "text/html; charset=utf-8");
	}

	public subview() {
		super(null);
	}

	public subview(StringBuilder out) {
		super(out);
	}

	String s;

	public cn.bran.japid.template.RenderResult render(String s) {
		this.s = s;
		long t = -1;
		super.layout();
		return new cn.bran.japid.template.RenderResultPartial(this.headers,
				getOut(), t, actionRunners);
	}

	@Override
	protected void doLayout() {

		// -- set up the tag objects
		final japidviews.more.MyController._tags.taggy _japidviews_more_MyController__tags_taggy2 = new japidviews.more.MyController._tags.taggy(
				getOut());
		_japidviews_more_MyController__tags_taggy2
				.setActionRunners(getActionRunners());

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
		p("\n" +
				"\n" +
				"hello ");// line 5
		p(s);// line 8
		p("\n");// line 8
		_japidviews_more_MyController__tags_taggy2.render(s);
		p(" \n");// line 10

	}

	@Override
	protected void title() {
		p("my title from subview");
		;
	}

	@Override
	protected void side() {
		p("my side bar");
		;
	}
}
