package japidviews.Application;
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
// NOTE: This file was generated from: japidviews/Application/flashout.html
// Change to this file will be lost next time the template file is compiled.
//
@cn.bran.play.NoEnhance
public class flashout extends cn.bran.japid.template.JapidTemplateBase
{	public static final String sourceTemplate = "japidviews/Application/flashout.html";
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


	public flashout() {
		super(null);
	}
	public flashout(StringBuilder out) {
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
p("\n");// line 1
if(asBoolean(flash.hasError())) {// line 3
p("    ");// line 3
p(flash.error());// line 4
p("\n");// line 4
} else if(asBoolean(flash.hasSuccess())) {// line 5
p("    ");// line 5
p(flash.success());// line 6
p("\n");// line 6
} else {// line 7
    if(asBoolean(flash.contains("msg"))) {// line 8
p("        the message: ");// line 8
p(flash("msg"));// line 9
p("\n" + 
"    ");// line 9
} else {// line 10
p("        oh well\n" + 
"    ");// line 10
}// line 12
}// line 13

	}

}