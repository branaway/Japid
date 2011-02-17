package japidviews.templates;

import java.util.*;
import java.io.*;
import cn.bran.japid.tags.Each;
//
// NOTE: This file was generated from: japidviews/templates/noplay.html
// Change to this file will be lost next time the template file is compiled.
//

@cn.bran.play.NoEnhance
public class noplay extends cn.bran.japid.template.JapidTemplateBaseWithoutPlay {
	public static final String sourceTemplate = "japidviews/templates/noplay.html";
	{
		headers.put("Content-Type", "text/html; charset=utf-8");
	}

	public noplay() {
		super(null);
	}

	public noplay(StringBuilder out) {
		super(out);
	}

	private String s;

	public cn.bran.japid.template.RenderResult render(String s) {
		this.s = s;
		long t = -1;
		super.layout();
		return new cn.bran.japid.template.RenderResult(this.headers, getOut(),
				t);
	}

	@Override
	protected void doLayout() {

		// -- set up the tag objects
		final japidviews._tags.Tag2 _japidviews__tags_Tag20 = new japidviews._tags.Tag2(
				getOut());

		// -- end of the tag objects

		//------
		p("hello ");// line 2
		_japidviews__tags_Tag20.render(s);
		p(" !\n" +
				"\n");// line 4

	}

}
