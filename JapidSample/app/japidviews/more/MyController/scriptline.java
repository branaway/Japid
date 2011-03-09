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
// NOTE: This file was generated from: japidviews/more/MyController/scriptline.html
// Change to this file will be lost next time the template file is compiled.
//
@cn.bran.play.NoEnhance
public class scriptline extends scriptlineLayout
{	public static final String sourceTemplate = "japidviews/more/MyController/scriptline.html";
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
final Tag2 _Tag21 = new Tag2(getOut());
{ _Tag21.setActionRunners(getActionRunners()); }

final Tag2 _Tag22 = new Tag2(getOut());
{ _Tag22.setActionRunners(getActionRunners()); }

// -- end of the tag objects

	public scriptline() {
		super(null);
	}
	public scriptline(StringBuilder out) {
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
p("\n" + 
"hello ");// line 2
_Tag21.setOut(getOut()); _Tag21.render("123");
// line 4
p(" a  ");// line 4
_Tag22.setOut(getOut()); _Tag22.render("456");
// line 4
p("!\n" + 
"this is how to print a single back quote: ");// line 4
p('`');// line 5
p(" or `");// line 5

	}

	@Override protected void meta() {
		p("holy meta");;
	}
}