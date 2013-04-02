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
//
// NOTE: This file was generated from: japidviews/_tags/paramWithDefaults2.html
// Change to this file will be lost next time the template file is compiled.
//
@cn.bran.play.NoEnhance
public class paramWithDefaults2 extends cn.bran.play.JapidTemplateBase
{
	public static final String sourceTemplate = "japidviews/_tags/paramWithDefaults2.html";
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
		setSourceTemplate(sourceTemplate);
	}
////// end of named args stuff

	private String name; // line 1
	private String url; // line 1
	private String type; // line 1
	private String data; // line 1
	private String reRender; // line 1
	private String dataType; // line 1
	private String beforeSend; // line 1
	private String success; // line 1
	private String jsData; // line 1
	private Boolean cache; // line 1
	private String event; // line 1
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
		long __t = -1;
		try {super.layout();} catch (RuntimeException e) { super.handleException(e);} // line 1
		return new cn.bran.japid.template.RenderResultPartial(getHeaders(), getOut(), __t, actionRunners, sourceTemplate);
	}

	public static cn.bran.japid.template.RenderResult apply(String name,String url,String type,String data,String reRender,String dataType,String beforeSend,String success,String jsData,Boolean cache,String event) {
		return new paramWithDefaults2().render(name, url, type, data, reRender, dataType, beforeSend, success, jsData, cache, event);
	}

	@Override protected void doLayout() {
		beginDoLayout(sourceTemplate);
//------
;// line 1
		p("   \n" + 
"oh well...\n" + 
"\n");// line 4
		 String fancyname = "fancy: $[name]";// line 8
		p("\n" + 
"The fancy name is ");// line 8
		p(fancyname);// line 10
		;// line 10
		
		endDoLayout(sourceTemplate);
	}

}