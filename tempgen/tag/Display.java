package tag;
import java.util.*;
import java.io.*;
import tag.*;
import bran.japid.tags.Each;
import layout.*;
import static bran.WebUtils.*;
import static japidplay.PlayTemplateVarsAdapter.*;
// This file was generated from: tag/Display.html
// Change to this file will be lost next time the template file is compiled.
@bran.NoEnhance
public class Display extends TagLayout{
	public static final String sourceTemplate = "tag/Display.html";
static byte[] static_0 = getBytes(""
);
static byte[] static_1 = getBytes("\n" + 
"\n" + 
"");
static byte[] static_2 = getBytes("\n" + 
"\n" + 
"\n" + 
"\n" + 
"<div class=\"divvy\">\n" + 
"	<p>title: "
);
static byte[] static_3 = getBytes("</p>\n" + 
"	<p>at: "
);
static byte[] static_4 = getBytes("</p>\n" + 
"	<p>by: "
);
static byte[] static_5 = getBytes(", "
);
static byte[] static_6 = getBytes("</p>\n" + 
"	"
);
static byte[] static_7 = getBytes("\n" + 
"</div>"
);
	public Display(OutputStream out) {
		super(out);
	}
	bran.Post post;
	String as;
	DoBody body;
	public static interface DoBody<A> {
		 void render(A a);
	}
	public void render(
	bran.Post post, 
	String as
, DoBody body) {
		this.body = body;
		this.post = post;
		this.as = as;
		super.layout();
	}
	@Override protected void doLayout() {
p(static_0);// line 1
// line 1
p(static_1);// line 5
p(static_2);// line 10
p(post.getTitle());// line 15
p(static_3);// line 15
p(format(post.getPostedAt(), ("yy-MMM-dd")));// line 16
p(static_4);// line 16
p(post.getAuthor().name);// line 17
p(static_5);// line 17
p(post.getAuthor().gender);// line 17
p(static_6);// line 17
if (body != null)
	body.render(post.getTitle());
// line 18
p(static_7);// line 18

	}
}
