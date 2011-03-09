package japidviews.more.MyController;
import java.util.*;
import java.io.*;
import cn.bran.japid.tags.Each;
import japidviews.more.MyController._tags2.*;
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
// NOTE: This file was generated from: japidviews/more/MyController/quickview.html
// Change to this file will be lost next time the template file is compiled.
//
@cn.bran.play.NoEnhance
public class quickview extends japidviews.more.MyController._layouts.simLayout
{	public static final String sourceTemplate = "japidviews/more/MyController/quickview.html";
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
final japidviews.more.MyController._tags.taggy _japidviews_more_MyController__tags_taggy0 = new japidviews.more.MyController._tags.taggy(getOut());
{ _japidviews_more_MyController__tags_taggy0.setActionRunners(getActionRunners()); }

final taggy2 _taggy21 = new taggy2(getOut());
{ _taggy21.setActionRunners(getActionRunners()); }

// -- end of the tag objects

	public quickview() {
		super(null);
	}
	public quickview(StringBuilder out) {
		super(out);
	}
	public cn.bran.japid.template.RenderResult render() {
		long t = -1;
		super.layout();
		return new cn.bran.japid.template.RenderResult(this.headers, getOut(), t);
	}
	@Override protected void doLayout() {
//------
;// line 1
p("<p>\n" + 
"hello there\n" + 
"</p>\n" + 
"<p>\n");// line 2
String s = "quick" ;// line 8
;// line 8
_japidviews_more_MyController__tags_taggy0.setOut(getOut()); _japidviews_more_MyController__tags_taggy0.render(s);
// line 9
p("</p> \n" + 
"<p>\n");// line 9
_taggy21.setOut(getOut()); _taggy21.render(s);
// line 12
p("</p>");// line 12

	}

}