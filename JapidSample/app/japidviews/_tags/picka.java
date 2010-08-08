package japidviews._tags;
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
import static play.templates.JavaExtensions.*;
import static cn.bran.play.JapidPlayAdapter.*;
import static play.data.validation.Validation.*;
import static cn.bran.play.WebUtils.*;
// NOTE: This file was generated from: japidviews/_tags/picka.html
// Change to this file will be lost next time the template file is compiled.
@cn.bran.play.NoEnhance
public class picka extends cn.bran.japid.template.JapidTemplateBase{
	public static final String sourceTemplate = "japidviews/_tags/picka.html";
static private final String static_0 = ""
;
static private final String static_1 = "<p>\n" + 
"some text \n" + 
"</p>\n" + 
"<p>\n" + 
"";
static private final String static_2 = " \n" + 
"</p>\n" + 
"<p>\n" + 
"more text \n" + 
"</p>\n" + 
" "
;
	public picka() {
		super(null);
	}
	public picka(StringBuilder out) {
		super(out);
	}
	String a;
	String b;
	DoBody body;
	public static interface DoBody<A> {
		 void render(A a);
	}
	public cn.bran.japid.template.RenderResult render(String a, String b, DoBody body) {
		this.body = body;
		this.a = a;
		this.b = b;
		long t = -1;
		super.layout();
		return new cn.bran.japid.template.RenderResultPartial(this.headers, getOut(), t, actionRunners);
	}
	@Override protected void doLayout() {
p(static_0);// line 1
p(static_1);// line 1
if (body != null)
	body.render(b);
// line 6
p(static_2);// line 6

	}
}
