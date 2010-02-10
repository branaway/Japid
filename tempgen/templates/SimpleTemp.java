package templates;
import java.util.*;
import java.io.*;
import cn.bran.japid.tags.Each;
import japidviews._layouts.*;
import japidviews._tags.*;
import static cn.bran.play.JapidPlayAdapter.*;
// NOTE: This file was generated from: templates/SimpleTemp.html
// Change to this file will be lost next time the template file is compiled.
@cn.bran.play.NoEnhance
public class SimpleTemp extends cn.bran.japid.template.JapidTemplateBase{
	public static final String sourceTemplate = "templates/SimpleTemp.html";
	public static final String contentType = "text/html";
static private final String static_0 = ""
;
static private final String static_1 = "\n" + 
"\n" + 
"<p>cool "
;
static private final String static_2 = "</p>"
;
	public SimpleTemp() {
		super(null);
	}
	public SimpleTemp(StringBuilder out) {
		super(out);
	}
	String blogTitle;
	public cn.bran.japid.template.RenderResult render(
  String blogTitle
) {
		this.blogTitle = blogTitle;
		long t = -1;
		super.layout();
		return new cn.bran.japid.template.RenderResult(this.contentType, getOut(), t);
	}
	@Override protected void doLayout() {
p(static_0);// line 1
p(static_1);// line 3
p(blogTitle);// line 5
p(static_2);// line 5

	}
}
