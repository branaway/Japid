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
// NOTE: This file was generated from: japidviews/templates/EachCall.html
// Change to this file will be lost next time the template file is compiled.
//

@cn.bran.play.NoEnhance
public class EachCall extends cn.bran.japid.template.JapidTemplateBase {
	public static final String sourceTemplate = "japidviews/templates/EachCall.html";
	{
		headers.put("Content-Type", "text/html; charset=utf-8");
	}

	public EachCall() {
		super(null);
	}

	public EachCall(StringBuilder out) {
		super(out);
	}

	private List<String> posts;

	public cn.bran.japid.template.RenderResult render(List<String> posts) {
		this.posts = posts;
		long t = -1;
		super.layout();
		return new cn.bran.japid.template.RenderResult(this.headers, getOut(),
				t);
	}

	@Override
	protected void doLayout() {

		// -- set up the tag objects
		final SampleTag _SampleTag1 = new SampleTag(getOut());
		_SampleTag1.setActionRunners(getActionRunners());

		final Each _Each0 = new Each(getOut());
		_Each0.setActionRunners(getActionRunners());

		final SampleTag _SampleTag2 = new SampleTag(getOut());
		_SampleTag2.setActionRunners(getActionRunners());

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
		p("<p>\n"
				+
				"The \"each/Each\" command is a for loop on steroid, with lots of loop information. \n"
				+
				"</p>\n"
				+
				"\n"
				+
				"<p> \n"
				+
				"The instance variable is defined after the | line, the same way as any tag render-back\n"
				+
				"</p>\n" +
				"\n");// line 1
		_Each0.render(posts, new Each.DoBody<String>() {
			public void render(final String p, final int _size,
					final int _index, final boolean _isOdd,
					final String _parity, final boolean _isFirst,
					final boolean _isLast) {
				p("    <p>index: ");// line 11
				p(_index);// line 12
				p(", parity: ");// line 12
				p(_parity);// line 12
				p(", is odd? ");// line 12
				p(_isOdd);// line 12
				p(", is first? ");// line 12
				p(_isFirst);// line 12
				p(", is last? ");// line 12
				p(_isLast);// line 12
				p(", total size: ");// line 12
				p(_size);// line 12
				p(" </p>\n" +
						"    call a tag:  ");// line 12
				_SampleTag1.render(p);

			}
		}
				);
		_SampleTag2.render("end");

	}

}
