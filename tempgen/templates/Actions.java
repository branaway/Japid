package templates;
import java.util.*;
import java.io.*;
import cn.bran.japid.tags.Each;
import japidviews._layouts.*;
import japidviews._tags.*;
import static cn.bran.play.JapidPlayAdapter.*;
// NOTE: This file was generated from: templates/Actions.html
// Change to this file will be lost next time the template file is compiled.
@cn.bran.play.NoEnhance
public class Actions extends cn.bran.japid.template.JapidTemplateBase{
	public static final String sourceTemplate = "templates/Actions.html";
	public static final String contentType = "text/html";
static private final String static_0 = ""
;
static private final String static_1 = "\n" + 
"\n" + 
"\n" + 
"<form url=\""
;
static private final String static_2 = "\"></form>\n" + 
"<form url=\""
;
static private final String static_3 = "\"></form>\n" + 
"<form url=\""
;
static private final String static_4 = "\"></form>\n" + 
"<form url=\""
;
static private final String static_5 = "\"></form>\n" + 
"";
	public Actions() {
		super(null);
	}
	public Actions(StringBuilder out) {
		super(out);
	}
	models.japidsample.Post post;
	public cn.bran.japid.template.RenderResult render(models.japidsample.Post post) {
		this.post = post;
		long t = -1;
		super.layout();
		return new cn.bran.japid.template.RenderResult(this.contentType, getOut(), t);
	}
	@Override protected void doLayout() {
p(static_0);// line 1
// line 1
p(static_1);// line 3
p(lookup("showAll", new Object[]{}));// line 6
p(static_2);// line 6
p(lookup("Clients.showAccounts", post.title, post.title));// line 7
p(static_3);// line 7
p(lookupAbs("Clients.showAccounts", post.title.substring(1, 2)));// line 8
p(static_4);// line 8
p(lookupStatic("/public/stylesheets/main.css"));// line 9
p(static_5);// line 9

	}
}
