package japidviews.more.MyController;
import java.util.*;
import java.io.*;
import cn.bran.japid.tags.Each;
import static play.templates.JavaExtensions.*;
import static cn.bran.play.JapidPlayAdapter.*;
import static play.data.validation.Validation.*;
import japidviews._layouts.*;
import play.i18n.Messages;
import static  japidviews._javatags.JapidWebUtil.*;
import play.data.validation.Validation;
import play.mvc.Scope.*;
import models.*;
import play.data.validation.Error;
import play.i18n.Lang;
import japidviews._tags.*;
import controllers.*;
import play.mvc.Http.*;
import japidviews._javatags.*;
//
// NOTE: This file was generated from: japidviews/more/MyController/subview.html
// Change to this file will be lost next time the template file is compiled.
//
@cn.bran.play.NoEnhance
public class subview extends superview
{	public static final String sourceTemplate = "japidviews/more/MyController/subview.html";
{
	headers.put("Content-Type", "text/html; charset=utf-8");
}

// - add implicit fields with Play

	final Request request = Request.current(); 
	final Response response = Response.current(); 
	final Session session = Session.current();
	final RenderArgs renderArgs = RenderArgs.current();
	final Params params = Params.current();
	final Validation validation = Validation.current();
	final cn.bran.play.FieldErrors errors = new cn.bran.play.FieldErrors(validation);
	final play.Play _play = new play.Play(); 

// - end of implicit fields with Play 



// -- set up the tag objects
final japidviews.more.MyController._tags.taggy _japidviews_more_MyController__tags_taggy2 = new japidviews.more.MyController._tags.taggy(getOut());
{ _japidviews_more_MyController__tags_taggy2.setActionRunners(getActionRunners()); }

// -- end of the tag objects

	public subview() {
		super(null);
	}
	public subview(StringBuilder out) {
		super(out);
	}
	private String s;
	public cn.bran.japid.template.RenderResult render(String s) {
		this.s = s;
		long t = -1;
		super.layout();
		return new cn.bran.japid.template.RenderResult(this.headers, getOut(), t);
	}
	@Override protected void doLayout() {
//------
;// line 1
;// line 2
// line 4
;// line 4
// line 5
p("\n" + 
"\n" + 
"hello ");// line 5
p(s);// line 8
p("\n");// line 8
_japidviews_more_MyController__tags_taggy2.setOut(getOut()); _japidviews_more_MyController__tags_taggy2.render(s);
// line 10
p(" ");// line 10

	}

	@Override protected void title() {
		p("my title from subview");;
	}
	@Override protected void side() {
		p("my side bar");;
	}
}