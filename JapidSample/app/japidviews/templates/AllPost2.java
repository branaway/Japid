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
// NOTE: This file was generated from: japidviews/templates/AllPost2.html
// Change to this file will be lost next time the template file is compiled.
//
@cn.bran.play.NoEnhance
public class AllPost2 extends Layout
{	public static final String sourceTemplate = "japidviews/templates/AllPost2.html";
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



// -- set up the tag objects
final Display _Display1 = new Display(getOut());
{ _Display1.setActionRunners(getActionRunners()); }

final Tag2 _Tag22 = new Tag2(getOut());
{ _Tag22.setActionRunners(getActionRunners()); }

// -- end of the tag objects

	public AllPost2() {
		super(null);
	}
	public AllPost2(StringBuilder out) {
		super(out);
	}
	private String blogTitle;
	private List<Post> allPost;
	public cn.bran.japid.template.RenderResult render(String blogTitle, List <Post> allPost) {
		this.blogTitle = blogTitle;
		this.allPost = allPost;
		long t = -1;
		super.layout();
		return new cn.bran.japid.template.RenderResult(this.headers, getOut(), t);
	}
	@Override protected void doLayout() {
//------
;// line 1
p("\n");// line 3
p("\n" + 
"\n");// line 5
if (allPost.size() > 0 ) {// line 8
p("	<p></p>\n" + 
"	");// line 8
for (Post p: allPost) {// line 10
p("	    ");// line 10
_Display1.setOut(getOut()); _Display1.render(p, "home", new Display.DoBody<String>(){
public void render(final String title) {
// line 11
p("			<p>The real title is: ");// line 11
p(title);// line 12
p(";</p>\n" + 
"	    ");// line 12

}
}
);
// line 11
p("	");// line 13
}// line 14
} else {// line 15
p("	<p>There is no post at this moment</p>\n");// line 15
}// line 17
p("\n");// line 17
_Tag22.setOut(getOut()); _Tag22.render(blogTitle);
// line 19
p("\n" + 
"<p>end of it</p>");// line 19

	}

	@Override protected void title() {
		p("Home");;
	}
}