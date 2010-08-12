package japidviews.Application;
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
// NOTE: This file was generated from: japidviews/Application/index.html
// Change to this file will be lost next time the template file is compiled.
@cn.bran.play.NoEnhance
public class index extends cn.bran.japid.template.JapidTemplateBase{
	public static final String sourceTemplate = "japidviews/Application/index.html";
static private final String static_0 = "<h3>There are a few samples in the japid.Application that demonstrate Japid features. More will be added.</h3>\n" + 
"\n" + 
"<ul>\n" + 
"	<li><a href=\""
;
static private final String static_1 = "\">Hello Japid, using an overridden version of renderText()</a></li>\n" + 
"	<li><a href=\"application/callTag\">using tags in a template</a></li>\n" + 
"	<li><a href=\"renderJapidWith/templates/def.html\"><em>def</em> tag: define a method that return a string that can be invoked from super template. Compare this to the <b>set</b> tag</a></li>\n" + 
"	<li><a href=\"application/composite\">demo how to composite a page with independent segments with the <b>invoke</b> tag</a></li>\n" + 
"	<li><a href=\"application/renderByPosition\">implicit template binding with <b>JapidController.renderJapid()</b> </a></li>\n" + 
"	<li><a href=\"application/postList\">a template with template and tags etc...</a></li>\n" + 
"	<li><a href=\"renderJapidWith/templates/log.html\">use the log macro in a template. watch the output in the console</a></li>\n" + 
"	<li><a href=\"renderJapidWith/templates/Msg.html\">use predefined messages</a></li>\n" + 
"	<li><a href=\"application/each\">the *Each* tag</a></li>\n" + 
"	<li><a href=\"application/invokeInLoop\">using invoke tag with local variables requires final attribute</a></li>\n" + 
"</ul>\n" + 
"";
	public index() {
		super(null);
	}
	public index(StringBuilder out) {
		super(out);
	}
	public cn.bran.japid.template.RenderResult render() {
		long t = -1;
		super.layout();
		return new cn.bran.japid.template.RenderResultPartial(this.headers, getOut(), t, actionRunners);
	}
	@Override protected void doLayout() {
p(static_0);// line 1
p(lookup("hello", new Object[]{}));// line 4
p(static_1);// line 4

	}
}
