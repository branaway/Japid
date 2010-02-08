package templates;
import java.util.*;
import java.io.*;
import cn.bran.japid.tags.Each;
import japidviews._layouts.*;
import japidviews._tags.*;
import	 	cn.bran.Post;
import static cn.bran.play.PlayTemplateVarsAdapter.*;
// NOTE: This file was generated from: templates/AllPost.html
// Change to this file will be lost next time the template file is compiled.
@cn.bran.play.NoEnhance
public class AllPost extends Layout{
	public static final String sourceTemplate = "templates/AllPost.html";
	public static final String contentType = "text/html";
static String static_0 = ""
;
static String static_1 = "\n" + 
"\n" + 
"";
static String static_2 = "\n" + 
"\n" + 
"";
static String static_3 = " \n" + 
"	    "
;
static String static_4 = "\n" + 
"			"
;
static String static_5 = " The real title is: "
;
static String static_6 = ";\n" + 
"	    "
;
static String static_7 = "\n" + 
"";
static String static_8 = "\n" + 
"\n" + 
"";
static String static_9 = "\n" + 
"\n" + 
"<p>cool</p>"
;
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
		return new cn.bran.japid.template.RenderResult(this.contentType, getOut(), t);
	}
	@Override protected void doLayout() {
p(static_0);// line 1
// line 1
p(static_1);// line 6
// line 8
p(static_2);// line 8
for (Post p: allPost) {// line 10
p(static_3);// line 10
_Display3.render(p, "home", _Display3DoBody);
// line 11
p(static_7);// line 13
}// line 14
p(static_8);// line 14
_Tag25.render(blogTitle);
// line 16
p(static_9);// line 16

	}
	@Override protected void title() {
		p("Home");;
	}
	private Display _Display3 = new Display(getOut());
class Display3DoBody implements Display.DoBody< String>{
	public void render(String title) {
		// line 11
p(static_4);// line 11
p(static_5);// line 12
p(title);// line 12
p(static_6);// line 12

	}
}
	private Display3DoBody _Display3DoBody = new Display3DoBody();

	private Tag2 _Tag25 = new Tag2(getOut());
}
