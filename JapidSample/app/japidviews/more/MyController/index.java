package japidviews.more.MyController;
import java.util.*;
import java.io.*;
import cn.bran.japid.tags.Each;
import static play.templates.JavaExtensions.*;
import static cn.bran.play.JapidPlayAdapter.*;
import static play.data.validation.Validation.*;
import static cn.bran.play.WebUtils.*;
import japidviews._layouts.*;
import static  japidviews._javatags.JapidWebUtil.*;
import play.data.validation.Validation;
import play.mvc.Scope.*;
import models.*;
import play.data.validation.Error;
import japidviews._tags.*;
import controllers.*;
import play.mvc.Http.*;
import japidviews._javatags.*;
//
// NOTE: This file was generated from: japidviews/more/MyController/index.html
// Change to this file will be lost next time the template file is compiled.
//
@cn.bran.play.NoEnhance
public class index extends SampleLayout
{	public static final String sourceTemplate = "japidviews/more/MyController/index.html";
{
	headers.put("Content-Type", "text/html; charset=utf-8");
}
	public index() {
		super(null);
	}
	public index(StringBuilder out) {
		super(out);
	}
	private String s;
	private int i;
	public cn.bran.japid.template.RenderResult render(String s, int i) {
		this.s = s;
		this.i = i;
		long t = -1;
		super.layout();
		return new cn.bran.japid.template.RenderResultPartial(this.headers, getOut(), t, actionRunners);
	}
	@Override protected void doLayout() {

// -- set up the tag objects
final SampleTag _SampleTag1 = new SampleTag(getOut());
_SampleTag1.setActionRunners(getActionRunners());

// -- end of the tag objects


// - add implicit variables 

		final Request request = Request.current(); assert request != null;

		final Response response = Response.current(); assert response != null;

		final Flash flash = Flash.current();assert flash != null;

		final Session session = Session.current();assert session != null;

		final RenderArgs renderArgs = RenderArgs.current(); assert renderArgs != null;

		final Params params = Params.current();assert params != null;

		final Validation validation = Validation.current();assert validation!= null;

		final cn.bran.play.FieldErrors errors = new cn.bran.play.FieldErrors(validation);assert errors != null;

		final play.Play _play = new play.Play(); assert _play != null;

// - end of implicit variables 


//------
;// line 1
;// line 1
;// line 3
// line 5
p("\n" + 
"hello ");// line 5
p(s);// line 7
p(", ");// line 7
p(i);// line 7
p(".\n" + 
"Here goes your Japid template content.\n" + 
"call a tag: \n");// line 7
_SampleTag1.render("world");
// line 10
;// line 10

	}

	@Override protected void title() {
		p("index");;
	}
}