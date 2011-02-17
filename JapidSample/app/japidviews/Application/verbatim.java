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
// NOTE: This file was generated from: japidviews/Application/verbatim.html
// Change to this file will be lost next time the template file is compiled.
//

@cn.bran.play.NoEnhance
public class verbatim extends cn.bran.japid.template.JapidTemplateBase {
	public static final String sourceTemplate = "japidviews/Application/verbatim.html";
	{
		headers.put("Content-Type", "text/html; charset=utf-8");
	}

	public verbatim() {
		super(null);
	}

	public verbatim(StringBuilder out) {
		super(out);
	}

	public cn.bran.japid.template.RenderResult render() {
		long t = -1;
		super.layout();
		return new cn.bran.japid.template.RenderResult(this.headers, getOut(),
				t);
	}

	@Override
	protected void doLayout() {

		// -- set up the tag objects
		final Each _Each0 = new Each(getOut());
		_Each0.setActionRunners(getActionRunners());

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
				"<p>\n"
				+
				"you should be able to see all Japid command un-interpreted.    	\n"
				+
				"</p>\n" +
				"\n");// line 1
		p("\n" +
				"\n" +
				"	`args models.japidsample.Author a\n" +
				"	\n" +
				"	<p>author name: $a.name</p>\n" +
				"	<p>his birthdate: $a.birthDate</p>\n" +
				"	<p>and his is a '${a.getGender()}'</p>\n" +
				"	    `tag SampleTag \"end\"\n" +
				"    \n");// line 6
		p("\n" +
				"<p>got it?</p>\n" +
				"\n");// line 15
		String[] ss = new String[] { "a", "b" };// line 18
		_Each0.render(ss, new Each.DoBody<String>() {
			public void render(final String s, final int _size,
					final int _index, final boolean _isOdd,
					final String _parity, final boolean _isFirst,
					final boolean _isLast) {
				p("    <p>loop: ");// line 19
				p(s);// line 20
				p("</p>\n" +
						"    ");// line 20
				p("    <p>please use ` to start command and $s to get the value</p>\n"
						+
						"    ");// line 21

			}
		}
				);

	}

}
