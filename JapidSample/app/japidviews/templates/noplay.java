package japidviews.templates;
import java.util.*;
import java.io.*;
import cn.bran.japid.tags.Each;
import japidviews._layouts.*;
import models.*;
import japidviews._tags.*;
import controllers.*;
import japidviews._javatags.*;
//
// NOTE: This file was generated from: japidviews/templates/noplay.html
// Change to this file will be lost next time the template file is compiled.
//
@cn.bran.play.NoEnhance
public class noplay extends cn.bran.japid.template.JapidTemplateBaseWithoutPlay
{	public static final String sourceTemplate = "japidviews/templates/noplay.html";

// -- set up the tag objects
final japidviews._tags.Tag2 _japidviews__tags_Tag20 = new japidviews._tags.Tag2(getOut());

// -- end of the tag objects

	public noplay() {
		super(null);
	}
	public noplay(StringBuilder out) {
		super(out);
	}
	private String s;
	public String render(String s) {
		this.s = s;
		long t = -1;
		super.layout();
		return getOut().toString();
	}
	@Override protected void doLayout() {
//------
;// line 1
p("\n" + 
"hello ");// line 2
_japidviews__tags_Tag20.setOut(getOut()); _japidviews__tags_Tag20.render(s);
// line 4
p(" !\n" + 
"\n");// line 4

	}

}