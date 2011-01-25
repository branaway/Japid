package japidviews.Application;

import java.util.*;
import java.io.*;
import cn.bran.japid.tags.Each;
import cn.bran.japid.template.ActionRunner;
import controllers.more.*;
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
// NOTE: This file was generated from: japidviews/Application/composite.html
// Change to this file will be lost next time the template file is compiled.
//

@cn.bran.play.NoEnhance
public class composite extends lcomposite2 {
	public static final String sourceTemplate = "japidviews/Application/composite.html";
	{
		headers.put("Content-Type", "text/html; charset=utf-8");
	}

	public composite() {
		super(null);
	}

	public composite(StringBuilder out) {
		super(out);
	}

	models.japidsample.Post post;

	public cn.bran.japid.template.RenderResult render(
			models.japidsample.Post post) {
		this.post = post;
		long t = -1;
		super.layout();
		return new cn.bran.japid.template.RenderResultPartial(this.headers,
				getOut(), t, actionRunners);
	}

	@Override
	protected void doLayout() {

		// -- set up the tag objects
		final invokeInTag _invokeInTag4 = new invokeInTag(getOut());
		_invokeInTag4.setActionRunners(getActionRunners());

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
		p("\n"
				+
				"\n"
				+
				"<p>This action won't be cached, unless the action has CacheFor annotation.</p>\n"
				+
				"<div>");// line 3
		actionRunners.put(getOut().length(),
				new cn.bran.play.CacheablePlayActionRunner("",
						"Application.authorPanel", post.getAuthor()) {
					@Override
					public void runPlayAction() throws cn.bran.play.JapidResult {
						super.checkActionCacheFor(Application.class,
								"authorPanel");
						Application.authorPanel(post.getAuthor()); //
					}
				});

		p("</div>\n" +
				"<div>Another one in sub package: ");// line 8
		actionRunners.put(getOut().length(),
				new cn.bran.play.CacheablePlayActionRunner("",
						"SubController.foo", post.getAuthor().name) {
					@Override
					public void runPlayAction() throws cn.bran.play.JapidResult {
						super.checkActionCacheFor(SubController.class, "foo");
						SubController.foo(post.getAuthor().name); //
					}
				});

		p("</div>\n" +
				"\n" +
				"<div>this one has full cache control</div>\n" +
				"<div>");// line 9
		actionRunners.put(getOut().length(),
				new cn.bran.play.CacheablePlayActionRunner("10s",
						"Application.authorPanel", post.getAuthor()) {
					@Override
					public void runPlayAction() throws cn.bran.play.JapidResult {
						super.checkActionCacheFor(Application.class,
								"authorPanel");
						Application.authorPanel(post.getAuthor()); //
					}
				});

		p("</div>\n"
				+
				"\n"
				+
				"<div>This one invokes an action with two params. Note the twoPrams() result is cached since the action carries CacheFor annotation.</div>\n"
				+
				"<div>");// line 12
		actionRunners.put(getOut().length(),
				new cn.bran.play.CacheablePlayActionRunner("",
						"Application.twoParams", "hello", 10) {
					@Override
					public void runPlayAction() throws cn.bran.play.JapidResult {
						super.checkActionCacheFor(Application.class,
								"twoParams");
						Application.twoParams("hello", 10); //
					}
				});

		p("</div>\n" +
				"\n" +
				"<p>Let's invoke a tag which invokes an action</p>\n");// line 15
		_invokeInTag4.render();
		p("\n"
				+
				"<p>let's invoke an action that renders a template that contains another invoke: ");// line 18
		actionRunners.put(getOut().length(),
				new cn.bran.play.CacheablePlayActionRunner("",
						"Application.authorPanel2", post.getAuthor()) {
					@Override
					public void runPlayAction() throws cn.bran.play.JapidResult {
						super.checkActionCacheFor(Application.class,
								"authorPanel2");
						Application.authorPanel2(post.getAuthor()); //
					}
				});

		p("</p>\n");// line 20

	}

}
