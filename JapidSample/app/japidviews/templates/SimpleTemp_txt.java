package japidviews.templates;
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
// NOTE: This file was generated from: japidviews/templates/SimpleTemp.txt
// Change to this file will be lost next time the template file is compiled.
//
@cn.bran.play.NoEnhance
public class SimpleTemp_txt extends cn.bran.play.JapidTemplateBase
{	public static final String sourceTemplate = "japidviews/templates/SimpleTemp.txt";
{
putHeader("Content-Type", "text/plain; charset=utf-8");
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


	public SimpleTemp_txt() {
		super(null);
	}
	public SimpleTemp_txt(StringBuilder out) {
		super(out);
	}
/* based on https://github.com/branaway/Japid/issues/12
 */
public static final String[] argNames = new String[] {/* args of the template*/"blogTitle",  };
public static final String[] argTypes = new String[] {/* arg types of the template*/"String",  };
public static final Object[] argDefaults= new Object[] {null, };
public static java.lang.reflect.Method renderMethod = getRenderMethod(japidviews.templates.SimpleTemp_txt.class);

{
	setRenderMethod(renderMethod);
	setArgNames(argNames);
	setArgTypes(argTypes);
	setArgDefaults(argDefaults);
	setSourceTemplate(sourceTemplate);

}
////// end of named args stuff

	private String blogTitle; // line 1
	public cn.bran.japid.template.RenderResult render(String blogTitle) {
		this.blogTitle = blogTitle;
		long t = -1;
		try {super.layout();} catch (RuntimeException e) { super.handleException(e);} // line 1
		return new cn.bran.japid.template.RenderResultPartial(getHeaders(), getOut(), t, actionRunners);
	}
	@Override protected void doLayout() {
//------
;// line 1
		p("\n" + 
"blog title:  ");// line 1
		p(blogTitle);// line 3
		p("\n");// line 3
		
	}

}