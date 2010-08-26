package japidviews.templates;
import java.util.*;
import java.io.*;
import cn.bran.japid.tags.Each;
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
import	 	models.japidsample.Post;
import static play.templates.JavaExtensions.*;
import static cn.bran.play.JapidPlayAdapter.*;
import static play.data.validation.Validation.*;
import static cn.bran.play.WebUtils.*;
// NOTE: This file was generated from: japidviews/templates/AllPost.html
// Change to this file will be lost next time the template file is compiled.
@cn.bran.play.NoEnhance
public class AllPost extends Layout{
	public static final String sourceTemplate = "japidviews/templates/AllPost.html";
static private final String static_0 = ""
;
static private final String static_1 = "\n" + 
"\n" + 
"";
static private final String static_2 = "\n" + 
"\n" + 
"";
static private final String static_3 = " \n" + 
"	    "
;
static private final String static_4 = "\n" + 
"			"
;
static private final String static_5 = " The real title is: "
;
static private final String static_6 = ";\n" + 
"	    "
;
static private final String static_7 = "\n" + 
"";
static private final String static_8 = "\n" + 
"\n" + 
"";
static private final String static_9 = "\n" + 
"\n" + 
"<p>cool</p>"
;
	public AllPost() {
		super(null);
	}
	public AllPost(StringBuilder out) {
		super(out);
	}
	String blogTitle;
	List <Post> allPost;
	public cn.bran.japid.template.RenderResult render(String blogTitle, List <Post> allPost) {
		this.blogTitle = blogTitle;
		this.allPost = allPost;
		long t = -1;
		t = System.currentTimeMillis();
		super.layout();
		t = System.currentTimeMillis() - t;
		System.out.println("[AllPost] rendering time: " + t);
		return new cn.bran.japid.template.RenderResultPartial(this.headers, getOut(), t, actionRunners);
	}
	@Override protected void doLayout() {

		play.mvc.Http.Request request = play.mvc.Http.Request.current(); assert request != null;
		play.mvc.Http.Response response = play.mvc.Http.Response.current(); assert response != null;
		play.mvc.Scope.Flash flash = play.mvc.Scope.Flash.current();assert flash != null;
		play.mvc.Scope.Session session = play.mvc.Scope.Session.current();assert session != null;
		play.mvc.Scope.RenderArgs renderArgs = play.mvc.Scope.RenderArgs.current(); assert renderArgs != null;
		play.mvc.Scope.Params params = play.mvc.Scope.Params.current();assert params != null;
		play.data.validation.Validation validation = play.data.validation.Validation.current();assert validation!= null;
		cn.bran.play.FieldErrors errors = new cn.bran.play.FieldErrors(validation.errors());assert errors != null;
		play.Play _play = new play.Play(); assert _play != null;
p(static_0);// line 1
// line 1
p(static_1);// line 6
// line 8
p(static_2);// line 8
for (Post p: allPost) {// line 10
p(static_3);// line 10
_Display1.setActionRunners(getActionRunners());
_Display1.render(p, "home", _Display1DoBody);
// line 11
p(static_7);// line 13
}// line 14
p(static_8);// line 14
_Tag22.setActionRunners(getActionRunners());
_Tag22.render(blogTitle);
// line 16
p(static_9);// line 16

	}
	@Override protected void title() {
		p("Home");;
	}
	private Display _Display1 = new Display(getOut());
class Display1DoBody implements Display.DoBody< String>{
	public void render(String title) {
		// line 11
p(static_4);// line 11
p(static_5);// line 12
p(title);// line 12
p(static_6);// line 12

	}
}
	private Display1DoBody _Display1DoBody = new Display1DoBody();

	private Tag2 _Tag22 = new Tag2(getOut());
}
