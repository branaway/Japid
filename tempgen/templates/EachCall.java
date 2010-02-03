package templates;
import java.util.*;
import java.io.*;
import tag.*;
import bran.japid.tags.Each;
import bran.Post;
import layout.*;
import static bran.WebUtils.*;
// This file was generated from: templates/EachCall.html
// Change to this file will be lost next time the template file is compiled.
public class EachCall extends bran.japid.BranTemplateBase{
	public static final String sourceTemplate = "templates/EachCall.html";
static byte[] static_0 = getBytes(""
);
static byte[] static_1 = getBytes("\n" + 
"\n" + 
"the old bad way:\n" + 
"");
static byte[] static_2 = getBytes("\n" + 
"	"
);
static byte[] static_3 = getBytes("<p>"
);
static byte[] static_4 = getBytes("</p>\n" + 
"");
static byte[] static_5 = getBytes("\n" + 
"\n" + 
"the closure form:\n" + 
"");
static byte[] static_6 = getBytes("\n" + 
"	<p>"
);
static byte[] static_7 = getBytes(" ) "
);
static byte[] static_8 = getBytes("</p>\n" + 
"");
static byte[] static_9 = getBytes("\n" + 
"");
	public EachCall(OutputStream out) {
		super(out);
	}
	List<String> posts;
	public void render(
List<String> posts
) {
		this.posts = posts;
		super.layout();
	}
	@Override protected void doLayout() {
p(static_0);// line 1
p(static_1);// line 3
_Each1.render(posts, _Each1DoBody);
// line 6
p(static_5);// line 8
_Each2.render(posts, _Each2DoBody);
// line 11
p(static_9);// line 13

	}
	private Each _Each1 = new Each(getOut());
class Each1DoBody implements Each.DoBody< String>{
	public void render(String p, int _index, boolean _isOdd, boolean _isFirst, boolean _isLast) {
		// line 6
p(static_2);// line 6
p(static_3);// line 7
p(p);// line 7
p(static_4);// line 7

	}
}
	private Each1DoBody _Each1DoBody = new Each1DoBody();

	private Each _Each2 = new Each(getOut());
class Each2DoBody implements Each.DoBody< String>{
	public void render(String p, int _index, boolean _isOdd, boolean _isFirst, boolean _isLast) {
		// line 11
p(static_6);// line 11
p(_index);// line 12
p(static_7);// line 12
p(p);// line 12
p(static_8);// line 12

	}
}
	private Each2DoBody _Each2DoBody = new Each2DoBody();

}
