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
// NOTE: This file was generated from: japidviews/_tags/tagPrimitives.html
// Change to this file will be lost next time the template file is compiled.
//
@cn.bran.play.NoEnhance
public class tagPrimitives extends cn.bran.play.JapidTemplateBase
{
	public static final String sourceTemplate = "japidviews/_tags/tagPrimitives.html";
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


	public tagPrimitives() {
		super(null);
	}
	public tagPrimitives(StringBuilder out) {
		super(out);
	}
/* based on https://github.com/branaway/Japid/issues/12
 */
	public static final String[] argNames = new String[] {/* args of the template*/"s", "i", "ii", "d", "dd", "b", "bb", "map", "f",  };
	public static final String[] argTypes = new String[] {/* arg types of the template*/"String", "int", "Integer", "double", "Double", "boolean", "Boolean", "Map<Object, String>", "float",  };
	public static final Object[] argDefaults= new Object[] {null,null,null,null,null,null,null,null,null, };
	public static java.lang.reflect.Method renderMethod = getRenderMethod(japidviews._tags.tagPrimitives.class);

	{
		setRenderMethod(renderMethod);
		setArgNames(argNames);
		setArgTypes(argTypes);
		setArgDefaults(argDefaults);
		setSourceTemplate(sourceTemplate);
	}
////// end of named args stuff

	private String s; // line 1
	private int i; // line 1
	private Integer ii; // line 1
	private double d; // line 1
	private Double dd; // line 1
	private boolean b; // line 1
	private Boolean bb; // line 1
	private Map<Object, String> map; // line 1
	private float f; // line 1
	public cn.bran.japid.template.RenderResult render(String s,int i,Integer ii,double d,Double dd,boolean b,Boolean bb,Map<Object, String> map,float f) {
		this.s = s;
		this.i = i;
		this.ii = ii;
		this.d = d;
		this.dd = dd;
		this.b = b;
		this.bb = bb;
		this.map = map;
		this.f = f;
		long t = -1;
		try {super.layout();} catch (RuntimeException e) { super.handleException(e);} // line 1
		return new cn.bran.japid.template.RenderResultPartial(getHeaders(), getOut(), t, actionRunners, sourceTemplate);
	}
	@Override protected void doLayout() {
		beginDoLayout(sourceTemplate);
//------
;// line 1
		p("\n" + 
"\n" + 
"<div>");// line 12
		p(s);// line 14
		p("</div>\n" + 
"<div>");// line 14
		p(i);// line 15
		p(", ");// line 15
		p(ii);// line 15
		p("</div>\n" + 
"<div>");// line 15
		p(d);// line 16
		p(", ");// line 16
		p(dd);// line 16
		p("</div>\n" + 
"<div>");// line 16
		p(b);// line 17
		p(", ");// line 17
		p(bb);// line 17
		p("</div>\n" + 
"<div>");// line 17
		p(map);// line 18
		p("</div>\n" + 
"<div>");// line 18
		p(f);// line 19
		p("</div>\n" + 
"\n");// line 19
		
		endDoLayout(sourceTemplate);
	}

}