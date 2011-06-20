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
// NOTE: This file was generated from: japidviews/_tags/paramWithDefaults2.html
// Change to this file will be lost next time the template file is compiled.
//
@cn.bran.play.NoEnhance
public class paramWithDefaults2 extends cn.bran.japid.template.JapidTemplateBase
{	public static final String sourceTemplate = "japidviews/_tags/paramWithDefaults2.html";
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


	public paramWithDefaults2() {
		super(null);
	}
	public paramWithDefaults2(StringBuilder out) {
		super(out);
	}
/* based on https://github.com/branaway/Japid/issues/12
 */
public static final String[] argNames = new String[] {/* args of the template*/"name", "url", "type", "data", "reRender", "dataType", "beforeSend", "success", "jsData", "cache", "event",  };
public static final String[] argTypes = new String[] {/* arg types of the template*/"String", "String", "String", "String", "String", "String", "String", "String", "String", "Boolean", "String",  };
public static final Object[] argDefaults= new Object[] {null,null,null,null,null,"html",null,null,null,null,null, };
public static java.lang.reflect.Method renderMethod = getRenderMethod(japidviews._tags.paramWithDefaults2.class);
{
	setRenderMethod(renderMethod);
	setArgNames(argNames);
	setArgTypes(argTypes);
	setArgDefaults(argDefaults);
}
////// end of named args stuff

	private String name;
	private String url;
	private String type;
	private String data;
	private String reRender;
	private String dataType;
	private String beforeSend;
	private String success;
	private String jsData;
	private Boolean cache;
	private String event;
	public cn.bran.japid.template.RenderResult render(String name,String url,String type,String data,String reRender,String dataType,String beforeSend,String success,String jsData,Boolean cache,String event) {
		this.name = name;
		this.url = url;
		this.type = type;
		this.data = data;
		this.reRender = reRender;
		this.dataType = dataType;
		this.beforeSend = beforeSend;
		this.success = success;
		this.jsData = jsData;
		this.cache = cache;
		this.event = event;
		long t = -1;
		super.layout();
		return new cn.bran.japid.template.RenderResultPartial(this.headers, getOut(), t, actionRunners);
	}
	@Override protected void doLayout() {
//------
;// line 1
p("\n" + 
"   \n" + 
"oh well...\n" + 
"\n");// line 4
 String fancyname = "fancy: "+name+"";// line 8
p("\n" + 
"The fancy name is ");// line 8
p(fancyname);// line 10

	}

}