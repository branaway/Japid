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
import japidviews._javatags.*;
import	 	models.japidsample.Post;
import static play.templates.JavaExtensions.*;
import static cn.bran.play.JapidPlayAdapter.*;
import static play.data.validation.Validation.*;
import static cn.bran.play.WebUtils.*;
// NOTE: This file was generated from: japidviews/templates/AllPost2.html
// Change to this file will be lost next time the template file is compiled.
@cn.bran.play.NoEnhance
public class AllPost2 extends Layout{
	public static final String sourceTemplate = "japidviews/templates/AllPost2.html";
static private final String static_0 = ""
;
static private final String static_1 = ""
;
static private final String static_2 = ""
;
static private final String static_3 = "\n" + 
"";
static private final String static_4 = "\n" + 
"\n" + 
"\n" + 
"";
static private final String static_5 = "	<p></p>\n" + 
"	"
;
static private final String static_6 = "	    "
;
static private final String static_7 = "\n" + 
"			<p>The real title is: "
;
static private final String static_8 = ";</p>\n" + 
"	    "
;
static private final String static_9 = "\n" + 
"	"
;
static private final String static_10 = ""
;
static private final String static_11 = "	<p>There is no post at this moment</p>\n" + 
"";
static private final String static_12 = "\n" + 
"";
static private final String static_13 = "\n" + 
"\n" + 
"<p>end of it</p>"
;
	public AllPost2() {
		super(null);
	}
	public AllPost2(StringBuilder out) {
		super(out);
	}
	String blogTitle;
	List <Post> allPost;
	public cn.bran.japid.template.RenderResult render(String blogTitle, List <Post> allPost) {
		this.blogTitle = blogTitle;
		this.allPost = allPost;
		long t = -1;
		super.layout();
		return new cn.bran.japid.template.RenderResultPartial(this.headers, getOut(), t, actionRunners);
	}
	@Override protected void doLayout() {
p(static_0);// line 1
p(static_1);// line 1
p(static_2);// line 2
p(static_3);// line 3
// line 5
p(static_4);// line 5
if (allPost.size() > 0 ) {// line 8
p(static_5);// line 8
for (Post p: allPost) {// line 10
p(static_6);// line 10
_Display1.setActionRunners(getActionRunners());
_Display1.render(p, "home", _Display1DoBody);
// line 11
p(static_9);// line 13
}// line 14
p(static_10);// line 14
} else {// line 15
p(static_11);// line 15
}// line 17
p(static_12);// line 17
_Tag22.setActionRunners(getActionRunners());
_Tag22.render(blogTitle);
// line 19
p(static_13);// line 19

	}
	@Override protected void title() {
		p("Home");;
	}
	private Display _Display1 = new Display(getOut());
class Display1DoBody implements Display.DoBody< String>{
	public void render(String title) {
		// line 11
p(static_7);// line 11
p(title);// line 12
p(static_8);// line 12

	}
}
	private Display1DoBody _Display1DoBody = new Display1DoBody();

	private Tag2 _Tag22 = new Tag2(getOut());
}
