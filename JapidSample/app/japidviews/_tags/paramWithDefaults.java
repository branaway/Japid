package japidviews._tags;
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
// NOTE: This file was generated from: japidviews/_tags/paramWithDefaults.html
// Change to this file will be lost next time the template file is compiled.
//
@cn.bran.play.NoEnhance
public class paramWithDefaults extends cn.bran.japid.template.JapidTemplateBase
{	public static final String sourceTemplate = "japidviews/_tags/paramWithDefaults.html";
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


	public paramWithDefaults() {
		super(null);
	}
	public paramWithDefaults(StringBuilder out) {
		super(out);
	}
/* based on https://github.com/branaway/Japid/issues/12
 */
public static final String[] argNames = new String[] {/* args of the template*/"msg", "m2", "age",  };
public static final String[] argTypes = new String[] {/* arg types of the template*/"String", "String", "Integer",  };
public static final Object[] argDefaults= new Object[] {"message 1 default value",new String("m2message"),20, };
public static java.lang.reflect.Method renderMethod = getRenderMethod(japidviews._tags.paramWithDefaults.class);
{
	setRenderMethod(renderMethod);
	setArgNames(argNames);
	setArgTypes(argTypes);
	setArgDefaults(argDefaults);
}
////// end of named args stuff

	private String msg;
	private String m2;
	private Integer age;
	public cn.bran.japid.template.RenderResult render(String msg,String m2,Integer age) {
		this.msg = msg;
		this.m2 = m2;
		this.age = age;
		long t = -1;
		super.layout();
		return new cn.bran.japid.template.RenderResultPartial(this.headers, getOut(), t, actionRunners);
	}
	@Override protected void doLayout() {
//------
;// line 1
p("\n" + 
"<span>");// line 5
p(msg);// line 6
p("</span>\n" + 
"<span>");// line 6
p(m2);// line 7
p("</span>\n" + 
"<span>");// line 7
p(age);// line 8
p("</span>\n" + 
"\n");// line 8

	}

}