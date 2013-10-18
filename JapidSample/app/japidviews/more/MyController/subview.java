package japidviews.more.MyController;
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
//
// NOTE: This file was generated from: japidviews/more/MyController/subview.html
// Change to this file will be lost next time the template file is compiled.
//
@cn.bran.play.NoEnhance
public class subview extends superview
{
	public static final String sourceTemplate = "japidviews/more/MyController/subview.html";
	{
		putHeader("Content-Type", "text/html; charset=utf-8");
		setContentType("text/html; charset=utf-8");
	}

// - add implicit fields with Play

	final play.mvc.Http.Request request = play.mvc.Http.Request.current(); 
	final play.mvc.Http.Response response = play.mvc.Http.Response.current(); 
	final play.mvc.Scope.Session session = play.mvc.Scope.Session.current();
	final play.mvc.Scope.RenderArgs renderArgs = play.mvc.Scope.RenderArgs.current();
	final play.mvc.Scope.Params params = play.mvc.Scope.Params.current();
	final play.data.validation.Validation validation = play.data.validation.Validation.current();
	final cn.bran.play.FieldErrors errors = new cn.bran.play.FieldErrors(validation);
	final play.Play _play = new play.Play(); 

// - end of implicit fields with Play 


	public subview() {
		super(null);
	}
	public subview(StringBuilder out) {
		super(out);
	}
/* based on https://github.com/branaway/Japid/issues/12
 */
	public static final String[] argNames = new String[] {/* args of the template*/"s",  };
	public static final String[] argTypes = new String[] {/* arg types of the template*/"String",  };
	public static final Object[] argDefaults= new Object[] {null, };
	public static java.lang.reflect.Method renderMethod = getRenderMethod(japidviews.more.MyController.subview.class);

	{
		setRenderMethod(renderMethod);
		setArgNames(argNames);
		setArgTypes(argTypes);
		setArgDefaults(argDefaults);
		setSourceTemplate(sourceTemplate);
	}
////// end of named args stuff

	private String s; // line 2, japidviews/more/MyController/subview.html
	public cn.bran.japid.template.RenderResult render(String s) {
		this.s = s;
		long __t = -1;
		try {super.layout();} catch (RuntimeException __e) { super.handleException(__e);} // line 2, japidviews/more/MyController/subview.html
		return new cn.bran.japid.template.RenderResultPartial(getHeaders(), getOut(), __t, actionRunners, sourceTemplate);
	}

	public static cn.bran.japid.template.RenderResult apply(String s) {
		return new subview().render(s);
	}

	@Override protected void doLayout() {
		beginDoLayout(sourceTemplate);
//------
;// line 1, subview.html
;// line 2, subview.html
		// line 4, subview.html
		;// line 4, subview.html
		// line 5, subview.html
		p("\n" + 
"\n" + 
"hello ");// line 5, subview.html
		p(s);// line 8, subview.html
		p("\n");// line 8, subview.html
		final japidviews.more.MyController._tags.taggy _japidviews_more_MyController__tags_taggy2 = new japidviews.more.MyController._tags.taggy(getOut()); _japidviews_more_MyController__tags_taggy2.setActionRunners(getActionRunners()).setOut(getOut()); _japidviews_more_MyController__tags_taggy2.render(s); // line 10, subview.html// line 10, subview.html
		p(" ");// line 10, subview.html
		
		endDoLayout(sourceTemplate);
	}

	@Override protected void title() {
		p("my title from subview");;
	}
	@Override protected void side() {
		p("my side bar");;
	}
}