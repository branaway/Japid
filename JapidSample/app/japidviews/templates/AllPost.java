package japidviews.templates;
import java.util.*;
import java.io.*;
import cn.bran.japid.tags.Each;
import	 	models.japidsample.Post;
import static play.templates.JavaExtensions.*;
import static cn.bran.play.JapidPlayAdapter.*;
import static play.data.validation.Validation.*;
import japidviews._layouts.*;
import play.i18n.Messages;
import static  japidviews._javatags.JapidWebUtil.*;
import play.data.validation.Validation;
import play.mvc.Scope.*;
import models.*;
import play.data.validation.Error;
import play.i18n.Lang;
import japidviews._tags.*;
import controllers.*;
import play.mvc.Http.*;
import japidviews._javatags.*;
//
// NOTE: This file was generated from: japidviews/templates/AllPost.html
// Change to this file will be lost next time the template file is compiled.
//
@cn.bran.play.NoEnhance
public class AllPost extends Layout
{	public static final String sourceTemplate = "japidviews/templates/AllPost.html";
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


	public AllPost() {
		super(null);
	}
	public AllPost(StringBuilder out) {
		super(out);
	}
	private String blogTitle;
	private List<Post> allPost;
	public cn.bran.japid.template.RenderResult render(
    String blogTitle, 
    List <Post> allPost
) {
		this.blogTitle = blogTitle;
		this.allPost = allPost;
		long t = -1;
		 t = System.nanoTime();
		super.layout();
     	String l = "" + (System.nanoTime() - t) / 100000;
		int len = l.length();
		l = l.substring(0, len - 1) + "." +  l.substring(len - 1);

		System.out.println("[AllPost] rendering time(ms): " + l);
		return new cn.bran.japid.template.RenderResultPartial(this.headers, getOut(), t, actionRunners);
	}
	@Override protected void doLayout() {
//------
;// line 1
;// line 2
p("\n");// line 6
p("\n");// line 7
p("\n");// line 9
for (Post p: allPost) { // line 11
p("        ");// line 11
p("\n" + 
"\n" + 
"	    ");// line 12
((Display)(new Display(getOut())).setActionRunners(getActionRunners())).render(p, "home2", new Display.DoBody<String>(){
public void render(final String title) {
// line 14
p("		   The real title is: ");// line 14
p(title);// line 15
p(";\n" + 
"	    ");// line 15

}
}
);
// line 14
}// line 17
p("\n");// line 17
((Tag2)(new Tag2(getOut()).setActionRunners(getActionRunners()))).render(blogTitle);
// line 19
p("\n" + 
"<p>cool</p>");// line 19

	}

	@Override protected void title() {
		p("Home");;
	}
}