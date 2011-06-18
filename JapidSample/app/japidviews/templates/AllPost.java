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
/* based on https://github.com/branaway/Japid/issues/12
 */
public static final String[] argNames = new String[] {/* args of the template*/"blogTitle", "allPost",  };
public static final String[] argTypes = new String[] {/* arg types of the template*/"String", "List<Post>",  };
public static java.lang.reflect.Method renderMethod = getRenderMethod(japidviews.templates.AllPost.class);
{
	setRenderMethod(renderMethod);
	setArgNames(argNames);
	setArgTypes(argTypes);
}
////// end of named args stuff

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

// -- set up the tag objects
final Display _Display1 = new Display(getOut());
{ _Display1.setActionRunners(getActionRunners()); }

final Tag2 _Tag22 = new Tag2(getOut());
{ _Tag22.setActionRunners(getActionRunners()); }

// -- end of the tag objects

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
_Display1.render(new Display.DoBody<String>(){
public void render(final String title) {
// line 14
p("		   The real title iiiis: ");// line 14
p(title);// line 15
p(";\n" + 
"	    ");// line 15

}
}
, named("post", p), named("as", "home2"));
// line 14
}// line 17
p("\n");// line 17
_Tag22.render(named("msg", blogTitle), named("m2", "msg2"), named("age", 10000));
// line 19
p("\n" + 
"<p>cool</p>");// line 19

	}

	@Override protected void title() {
		p("Home");;
	}
}