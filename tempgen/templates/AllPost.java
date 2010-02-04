package templates;
import java.util.*;
import java.io.*;
import tag.*;
import bran.japid.tags.Each;
import bran.Post;
import layout.*;
import static bran.WebUtils.*;
import static japidplay.PlayTemplateVarsAdapter.*;
// This file was generated from: templates/AllPost.html
// Change to this file will be lost next time the template file is compiled.
@bran.NoEnhance
public class AllPost extends layout.Layout{
	public static final String sourceTemplate = "templates/AllPost.html";
static byte[] static_0 = getBytes(""
);
static byte[] static_1 = getBytes("\n" + 
"");
static byte[] static_2 = getBytes("\n" + 
"\n" + 
"");
static byte[] static_3 = getBytes("\n" + 
"\n" + 
"");
static byte[] static_4 = getBytes(" \n" + 
"	    "
);
static byte[] static_5 = getBytes("\n" + 
"			"
);
static byte[] static_6 = getBytes(" The real title is: "
);
static byte[] static_7 = getBytes(";\n" + 
"	    "
);
static byte[] static_8 = getBytes("\n" + 
"");
static byte[] static_9 = getBytes("\n" + 
"\n" + 
"");
static byte[] static_10 = getBytes("\n" + 
"\n" + 
"<p>cool</p>"
);
	public AllPost(OutputStream out) {
		super(out);
	}
	String blogTitle;
	List <Post> allPost;
	public void render(
  String blogTitle, 
  List <Post> allPost
) {
		this.blogTitle = blogTitle;
		this.allPost = allPost;
		super.layout();
	}
	@Override protected void doLayout() {
p(static_0);// line 1
// line 1
p(static_1);// line 4
p(static_2);// line 8
// line 10
p(static_3);// line 10
for (Post p: allPost) {// line 12
p(static_4);// line 12
_Display3.render(p, "home", _Display3DoBody);
// line 13
p(static_8);// line 15
}// line 16
p(static_9);// line 16
_Tag25.render(blogTitle);
// line 18
p(static_10);// line 18

	}
	@Override protected void title() {
		p("Home");;
	}
	private Display _Display3 = new Display(getOut());
class Display3DoBody implements Display.DoBody< String>{
	public void render(String title) {
		// line 13
p(static_5);// line 13
p(static_6);// line 14
p(title);// line 14
p(static_7);// line 14

	}
}
	private Display3DoBody _Display3DoBody = new Display3DoBody();

	private Tag2 _Tag25 = new Tag2(getOut());
}
