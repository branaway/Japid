package templates;
import java.util.*;
import java.io.*;
import cn.bran.japid.tags.Each;
import japidviews._layouts.*;
import japidviews._tags.*;
import static cn.bran.play.PlayTemplateVarsAdapter.*;
// NOTE: This file was generated from: templates/EachCall.html
// Change to this file will be lost next time the template file is compiled.
@cn.bran.play.NoEnhance
public class EachCall extends cn.bran.japid.template.JapidTemplateBase{
	public static final String sourceTemplate = "templates/EachCall.html";
	public static final String contentType = "text/html";
static String static_0 = ""
;
static String static_1 = "\n" + 
"\n" + 
"the old bad way:\n" + 
"";
static String static_2 = "\n" + 
"	"
;
static String static_3 = "<p>"
;
static String static_4 = "</p>\n" + 
"";
static String static_5 = "\n" + 
"\n" + 
"the closure form:\n" + 
"";
static String static_6 = "\n" + 
"	<p>"
;
static String static_7 = " ) "
;
static String static_8 = "</p>\n" + 
"";
static String static_9 = "\n" + 
"";
	public EachCall(StringBuilder out) {
		super(out);
	}
	List<String> posts;
	public cn.bran.japid.template.RenderResult render(
List<String> posts
) {
		this.posts = posts;
		long t = -1;
		super.layout();
		return new cn.bran.japid.template.RenderResult(this.contentType, getOut(), t);
	}
	@Override protected void doLayout() {
p(static_0);// line 1
p(static_1);// line 3
_Each1.render(posts, _Each1DoBody);
// line 6
p(static_5);// line 8
_Each3.render(posts, _Each3DoBody);
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

	private Each _Each3 = new Each(getOut());
class Each3DoBody implements Each.DoBody< String>{
	public void render(String p, int _index, boolean _isOdd, boolean _isFirst, boolean _isLast) {
		// line 11
p(static_6);// line 11
p(_index);// line 12
p(static_7);// line 12
p(p);// line 12
p(static_8);// line 12

	}
}
	private Each3DoBody _Each3DoBody = new Each3DoBody();

}
