package templates;
import java.util.*;
import java.io.*;
import cn.bran.japid.tags.Each;
import japidviews._layouts.*;
import japidviews._tags.*;
import static cn.bran.play.PlayTemplateVarsAdapter.*;
// NOTE: This file was generated from: templates/Msg.html
// Change to this file will be lost next time the template file is compiled.
@cn.bran.play.NoEnhance
public class Msg extends cn.bran.japid.template.JapidTemplateBase{
	public static final String sourceTemplate = "templates/Msg.html";
	public static final String contentType = "text/html";
static String static_0 = "login: "
;
static String static_1 = "\n" + 
"customer name: "
;
static String static_2 = ""
;
	public Msg(StringBuilder out) {
		super(out);
	}
	public cn.bran.japid.template.RenderResult render() {
		long t = -1;
		super.layout();
		return new cn.bran.japid.template.RenderResult(this.contentType, getOut(), t);
	}
	@Override protected void doLayout() {
p(static_0);// line 1
;p(getMessage("login.name"));// line 1
p(static_1);// line 1
;p(getMessage("cus.name", "冉冉"));// line 2
p(static_2);// line 2

	}
}
