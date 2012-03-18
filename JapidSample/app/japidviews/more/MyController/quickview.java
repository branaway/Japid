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
// NOTE: This file was generated from: japidviews/more/MyController/quickview.html
// Change to this file will be lost next time the template file is compiled.
//
@cn.bran.play.NoEnhance
public class quickview extends japidviews.more.MyController._layouts.simLayout
{
	public static final String sourceTemplate = "japidviews/more/MyController/quickview.html";
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


	public quickview() {
		super(null);
	}
	public quickview(StringBuilder out) {
		super(out);
	}
/* based on https://github.com/branaway/Japid/issues/12
 */
	public static final String[] argNames = new String[] {/* args of the template*/ };
	public static final String[] argTypes = new String[] {/* arg types of the template*/ };
	public static final Object[] argDefaults= new Object[] { };
	public static java.lang.reflect.Method renderMethod = getRenderMethod(japidviews.more.MyController.quickview.class);

	{
		setRenderMethod(renderMethod);
		setArgNames(argNames);
		setArgTypes(argTypes);
		setArgDefaults(argDefaults);
		setSourceTemplate(sourceTemplate);
	}
////// end of named args stuff

	public cn.bran.japid.template.RenderResult render() {
		long t = -1;
		try {super.layout();} catch (RuntimeException e) { super.handleException(e);} 
		return new cn.bran.japid.template.RenderResultPartial(getHeaders(), getOut(), t, actionRunners, sourceTemplate);
	}
	@Override protected void doLayout() {
		beginDoLayout(sourceTemplate);
//------
;// line 1
p("<p>\n" + 
"hello there\n" + 
"</p>\n" + 
"<p>\n");// line 2
		String s = "quick" ;// line 8
		;// line 8
		final japidviews.more.MyController._tags.taggy _japidviews_more_MyController__tags_taggy0 = new japidviews.more.MyController._tags.taggy(getOut()); _japidviews_more_MyController__tags_taggy0.setActionRunners(getActionRunners()).setOut(getOut()); _japidviews_more_MyController__tags_taggy0.render(s); // line 9// line 9
		p("</p> \n" + 
"<p>\n");// line 9
		final taggy2 _taggy21 = new taggy2(getOut()); _taggy21.setActionRunners(getActionRunners()).setOut(getOut()); _taggy21.render(s); // line 12// line 12
		p("</p>");// line 12
		
		endDoLayout(sourceTemplate);
	}

}