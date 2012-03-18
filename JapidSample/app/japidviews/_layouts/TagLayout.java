package japidviews._layouts;
import java.util.*;
import java.io.*;
import cn.bran.japid.tags.Each;
import static play.templates.JavaExtensions.*;
import static cn.bran.play.JapidPlayAdapter.*;
import static play.data.validation.Validation.*;
import japidviews._layouts.*;
import play.i18n.Messages;
import play.data.validation.Validation;
import play.mvc.Scope.*;
import models.*;
import play.data.validation.Error;
import play.i18n.Lang;
import japidviews._tags.*;
import play.mvc.Http.*;
import controllers.*;
import japidviews._javatags.*;
//
// NOTE: This file was generated from: japidviews/_layouts/TagLayout.html
// Change to this file will be lost next time the template file is compiled.
//
@cn.bran.play.NoEnhance
public abstract class TagLayout extends cn.bran.play.JapidTemplateBase
{
	public static final String sourceTemplate = "japidviews/_layouts/TagLayout.html";
	{
		putHeader("Content-Type", "text/html; charset=utf-8");
		setContentType("text/html; charset=utf-8");
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


	public TagLayout() {
		super(null);
	}
	public TagLayout(StringBuilder out) {
		super(out);
	}
	@Override public void layout() {
		beginDoLayout(sourceTemplate);		p("标签布局\n");// line 1
		p("\n");// line 2
		final dummyTag _dummyTag0 = new dummyTag(getOut()); _dummyTag0.setActionRunners(getActionRunners()).setOut(getOut()); _dummyTag0.render("me"); // line 3// line 3
		p("<div>\n" + 
"\n");// line 3
		p("\n" + 
"    \n");// line 6
		doLayout();// line 8
		p("\n" + 
"</div>\n" + 
"\n" + 
"\n");// line 8
				endDoLayout(sourceTemplate);	}

	protected abstract void doLayout();
}