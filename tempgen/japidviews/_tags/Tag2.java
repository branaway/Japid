package japidviews._tags;
import java.util.*;
import java.io.*;
import cn.bran.japid.tags.Each;
import japidviews._layouts.*;
import japidviews._tags.*;
import static cn.bran.play.PlayTemplateVarsAdapter.*;
// NOTE: This file was generated from: japidviews/_tags/Tag2.html
// Change to this file will be lost next time the template file is compiled.
@cn.bran.play.NoEnhance
public class Tag2 extends cn.bran.japid.template.JapidTemplateBase{
	public static final String sourceTemplate = "japidviews/_tags/Tag2.html";
	public static final String contentType = "text/html";
static String static_0 = ""
;
static String static_1 = "\n" + 
"\n" + 
"<span>"
;
static String static_2 = "</span>"
;
	public Tag2(StringBuilder out) {
		super(out);
	}
	String msg;
	public cn.bran.japid.template.RenderResult render(
	String msg
) {
		this.msg = msg;
		long t = -1;
		super.layout();
		return new cn.bran.japid.template.RenderResult(this.contentType, getOut(), t);
	}
	@Override protected void doLayout() {
p(static_0);// line 1
p(static_1);// line 3
p(msg);// line 5
p(static_2);// line 5

	}
}
