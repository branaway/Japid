package japidviews._tags;
import java.util.*;
import java.io.*;
import cn.bran.japid.tags.Each;
import static cn.bran.play.WebUtils.*;
import static play.templates.JavaExtensions.*;
import static cn.bran.play.JapidPlayAdapter.*;
import static play.data.validation.Validation.*;
import static cn.bran.play.WebUtils.*;
import japidviews._layouts.*;
import static  japidviews._javatags.JapidWebUtil.*;
import play.data.validation.Validation;
import play.mvc.Scope.*;
import models.*;
import play.data.validation.Error;
import japidviews._tags.*;
import controllers.*;
import play.mvc.Http.*;
import japidviews._javatags.*;
//
// NOTE: This file was generated from: japidviews/_tags/Display.html
// Change to this file will be lost next time the template file is compiled.
//
@cn.bran.play.NoEnhance
public class Display extends TagLayout
{	public static final String sourceTemplate = "japidviews/_tags/Display.html";
{
	headers.put("Content-Type", "text/html; charset=utf-8");
}
	public Display() {
		super(null);
	}
	public Display(StringBuilder out) {
		super(out);
	}
	private models.japidsample.Post post;
	private String as;
	private DoBody body;
	public static interface DoBody<A> {
		 void render(A a);
	}
	public cn.bran.japid.template.RenderResult render(models.japidsample.Post post,	String as, DoBody body) {
		this.body = body;
		this.post = post;
		this.as = as;
		long t = -1;
		super.layout();
		return new cn.bran.japid.template.RenderResult(this.headers, getOut(), t);
	}
	@Override protected void doLayout() {

// - add implicit variables 

		final Request request = Request.current(); assert request != null;

		final Response response = Response.current(); assert response != null;

		final Flash flash = Flash.current();assert flash != null;

		final Session session = Session.current();assert session != null;

		final RenderArgs renderArgs = RenderArgs.current(); assert renderArgs != null;

		final Params params = Params.current();assert params != null;

		final Validation validation = Validation.current();assert validation!= null;

		final cn.bran.play.FieldErrors errors = new cn.bran.play.FieldErrors(validation);assert errors != null;

		final play.Play _play = new play.Play(); assert _play != null;

// - end of implicit variables 


//------
;// line 1
p("\n" + 
"\n" + 
"<div class=\"divvy\">\n" + 
"	<p>title: ");// line 3
p(post.getTitle());// line 7
p("</p>\n" + 
"	<p>at: ");// line 7
p(fastformat(post.getPostedAt(), ("yy-MMM-dd")));// line 8
p("</p>\n" + 
"	<p>by: ");// line 8
p(post.getAuthor().name);// line 9
p(", ");// line 9
p(post.getAuthor().gender);// line 9
p("</p>\n" + 
"	<p class=\"try again using a simple syntax\">\n" + 
"        ");// line 9
p("\n" + 
"        ");// line 11
p("\n" + 
"	   ");// line 12
if (body != null)
	body.render(post.getTitle() + "!");
p("	</p>\n" + 
"</div>");// line 13

	}

}