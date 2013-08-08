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
import japidviews._tags.*;
import play.i18n.Lang;
import play.mvc.Http.*;
import controllers.*;
//
// NOTE: This file was generated from: japidviews/_tags/Display.html
// Change to this file will be lost next time the template file is compiled.
//
@cn.bran.play.NoEnhance
public class Display extends TagLayout
{
	public static final String sourceTemplate = "japidviews/_tags/Display.html";
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


	public Display() {
		super(null);
	}
	public Display(StringBuilder out) {
		super(out);
	}
/* based on https://github.com/branaway/Japid/issues/12
 */
	public static final String[] argNames = new String[] {/* args of the template*/"post", "as",  };
	public static final String[] argTypes = new String[] {/* arg types of the template*/"models.japidsample.Post", "String",  };
	public static final Object[] argDefaults= new Object[] {null,null, };
	public static java.lang.reflect.Method renderMethod = getRenderMethod(japidviews._tags.Display.class);

	{
		setRenderMethod(renderMethod);
		setArgNames(argNames);
		setArgTypes(argTypes);
		setArgDefaults(argDefaults);
		setSourceTemplate(sourceTemplate);
	}
////// end of named args stuff

	{ setHasDoBody(); }
	private models.japidsample.Post post; // line 2, japidviews/_tags/Display.html
	private String as; // line 2, japidviews/_tags/Display.html
public cn.bran.japid.template.RenderResult render(DoBody body, cn.bran.japid.compiler.NamedArgRuntime... named) {
    Object[] args = buildArgs(named, body);
    try {return runRenderer(args);} catch(RuntimeException e) {handleException(e); throw e;} // line 2, japidviews/_tags/Display.html
}

	private DoBody body;
public static interface DoBody<A> {
		void render(A a);
		void setBuffer(StringBuilder sb);
		void resetBuffer();
}
<A> String renderBody(A a) {
		StringBuilder sb = new StringBuilder();
		if (body != null){
			body.setBuffer(sb);
			body.render( a);
			body.resetBuffer();
		}
		return sb.toString();
	}
	public cn.bran.japid.template.RenderResult render(models.japidsample.Post post,String as, DoBody body) {
		this.body = body;
		this.post = post;
		this.as = as;
		long __t = -1;
		try {super.layout();} catch (RuntimeException e) { super.handleException(e);} // line 2, japidviews/_tags/Display.html
		return new cn.bran.japid.template.RenderResultPartial(getHeaders(), getOut(), __t, actionRunners, sourceTemplate);
	}
	public cn.bran.japid.template.RenderResult render(models.japidsample.Post post,String as) {
		this.post = post;
		this.as = as;
		long __t = -1;
		try {super.layout();} catch (RuntimeException e) { super.handleException(e);} // line 2, japidviews/_tags/Display.html
		return new cn.bran.japid.template.RenderResultPartial(getHeaders(), getOut(), __t, actionRunners, sourceTemplate);
	}

	public static cn.bran.japid.template.RenderResult apply(models.japidsample.Post post,String as) {
		return new Display().render(post, as);
	}

	@Override protected void doLayout() {
		beginDoLayout(sourceTemplate);
//------
;// line 1, Display.html
		;// line 1, Display.html
		p("<div class=\"divvy\">\n" + 
"	<p>title: ");// line 4, Display.html
		p(post.getTitle());// line 6, Display.html
		p("</p>\n" + 
"	<p>at: ");// line 6, Display.html
		p(format(post.getPostedAt(), ("yy-MMM-dd")));// line 7, Display.html
		p("</p>\n" + 
"	<p>by: ");// line 7, Display.html
		p(post.getAuthor().name);// line 8, Display.html
		p(", ");// line 8, Display.html
		p(post.getAuthor().gender);// line 8, Display.html
		p("</p>\n" + 
"	<p class=\"try again using a simple syntax\">\n" + 
"        ");// line 8, Display.html
		p("\n" + 
"        ");// line 10, Display.html
		p("\n" + 
"	   ");// line 11, Display.html
		if (body != null){ body.setBuffer(getOut()); body.render(post.getTitle() + "!"); body.resetBuffer();}// line 12, Display.html
		p("	</p>\n" + 
"</div>");// line 12, Display.html
		
		endDoLayout(sourceTemplate);
	}

}