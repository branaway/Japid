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
import models.japidsample.Post;
import static play.templates.JavaExtensions.*;
import static cn.bran.play.JapidPlayAdapter.*;
import static play.data.validation.Validation.*;
import static cn.bran.play.WebUtils.*;
// NOTE: This file was generated from: japidviews/templates/Posts.html
// Change to this file will be lost next time the template file is compiled.
@cn.bran.play.NoEnhance
public class Posts extends cn.bran.japid.template.JapidTemplateBase{
	public static final String sourceTemplate = "japidviews/templates/Posts.html";
static private final String static_0 = ""
;
static private final String static_1 = "\n" + 
"";
static private final String static_2 = "\n" + 
"\n" + 
"";
static private final String static_3 = " \n" + 
"	- title: "
;
static private final String static_4 = "\n" + 
"	- date: "
;
static private final String static_5 = "\n" + 
"	- author "
;
static private final String static_6 = " "
;
static private final String static_7 = "\n" + 
"	the real title: 你好\n" + 
"";
static private final String static_8 = "\n" + 
"";
	public Posts() {
		super(null);
	}
	public Posts(StringBuilder out) {
		super(out);
	}
	String blogTitle;
	List <Post> allPost;
	public cn.bran.japid.template.RenderResult render(
  String blogTitle, 
  List <Post> allPost
) {
		this.blogTitle = blogTitle;
		this.allPost = allPost;
		long t = -1;
		super.layout();
		return new cn.bran.japid.template.RenderResultPartial(this.headers, getOut(), t, actionRunners);
	}
	@Override protected void doLayout() {
p(static_0);// line 1
// line 1
p(static_1);// line 3
p(static_2);// line 7
for (Post post: allPost) {// line 9
p(static_3);// line 9
p(post.title);// line 10
p(static_4);// line 10
p(post.postedAt);// line 11
p(static_5);// line 11
p(post.author.name);// line 12
p(static_6);// line 12
p(post.author.gender);// line 12
p(static_7);// line 12
}// line 14
p(static_8);// line 14

	}
}
