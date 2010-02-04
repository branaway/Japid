package templates;
import java.util.*;
import java.io.*;
import tag.*;
import bran.japid.tags.Each;
import bran.Post;
import layout.*;
import static bran.WebUtils.*;
import static japidplay.PlayTemplateVarsAdapter.*;
// This file was generated from: templates/Posts.html
// Change to this file will be lost next time the template file is compiled.
@bran.NoEnhance
public class Posts extends bran.japid.BranTemplateBase{
	public static final String sourceTemplate = "templates/Posts.html";
static byte[] static_0 = getBytes(""
);
static byte[] static_1 = getBytes("\n" + 
"");
static byte[] static_2 = getBytes("\n" + 
"\n" + 
"");
static byte[] static_3 = getBytes(" \n" + 
"	- title: "
);
static byte[] static_4 = getBytes("\n" + 
"	- date: "
);
static byte[] static_5 = getBytes("\n" + 
"	- author "
);
static byte[] static_6 = getBytes(" "
);
static byte[] static_7 = getBytes("\n" + 
"	the real title: 你好\n" + 
"");
static byte[] static_8 = getBytes("\n" + 
"");
	public Posts(OutputStream out) {
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
