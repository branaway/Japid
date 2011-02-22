package japidviews.Application;
import java.util.*;
import java.io.*;
import cn.bran.japid.tags.Each;
import static play.templates.JavaExtensions.*;
import static cn.bran.play.JapidPlayAdapter.*;
import static play.data.validation.Validation.*;
import static cn.bran.play.WebUtils.*;
import japidviews._layouts.*;
import static  japidviews._javatags.JapidWebUtil.*;
import play.data.validation.Validation;
import play.mvc.Scope.*;
import models.*;
import play.data.validation.Error;
import japidviews._tags.*;
import controllers.*;
import play.mvc.Http.*;
import japidviews._javatags.*;
//
// NOTE: This file was generated from: japidviews/Application/index.html
// Change to this file will be lost next time the template file is compiled.
//
@cn.bran.play.NoEnhance
public class index extends cn.bran.japid.template.JapidTemplateBase
{	public static final String sourceTemplate = "japidviews/Application/index.html";
{
	headers.put("Content-Type", "text/html; charset=utf-8");
}
	public index() {
		super(null);
	}
	public index(StringBuilder out) {
		super(out);
	}
	public cn.bran.japid.template.RenderResult render() {
		long t = -1;
		super.layout();
		return new cn.bran.japid.template.RenderResult(this.headers, getOut(), t);
	}
	@Override protected void doLayout() {

// - add implicit variables 

		final Request request = Request.current(); assert request != null;

		final Response response = Response.current(); assert response != null;

		final Flash flash = Flash.current();assert flash != null;

		final Session session = Session.current();assert session != null;

		final RenderArgs renderArgs = RenderArgs.current(); assert renderArgs != null;

		final Params params = Params.current();assert params != null;

		final Validation validation = Validation.current();assert validation!= null;

		final cn.bran.play.FieldErrors errors = new cn.bran.play.FieldErrors(validation);assert errors != null;

		final play.Play _play = new play.Play(); assert _play != null;

// - end of implicit variables 


//------
p("<h2>Some Samples that demonstrate Japid features.</h2>\n" + 
"\n" + 
"<p>Please follow the controller actions and render paths for the\n" + 
"source code.</p>\n" + 
"\n" + 
"<ul>\n" + 
"	<li><a href=\"");// line 1
p(lookup("hello", new Object[]{}));// line 7
p("\">Hello Japid, using an overridden\n" + 
"	version of renderText()</a></li>\n" + 
"	<li><a href=\"application/callTag\">using tags in a template</a></li>\n" + 
"	<li><a href=\"renderJapidWith/templates/def.html\"><em>def</em>\n" + 
"	tag: define a method that return a string that can be invoked from\n" + 
"	super template. Compare this to the <b>set</b> tag</a></li>\n");// line 7
p("\n" + 
"	<li><a href=\"more.Portlets/index\">demo how to composite a\n" + 
"	page with independent segments with the <b>invoke</b> tag</a></li>\n" + 
"\n" + 
"	<li><a href=\"application/renderByPosition\">implicit template\n" + 
"	binding with <b>JapidController.renderJapid()</b> </a></li>\n" + 
"	<li><a href=\"application/postList\">a template with template\n" + 
"	and tags etc...</a></li>\n" + 
"	<li><a href=\"renderJapidWith/templates/log.html\">use the log\n" + 
"	macro in a template. watch the output in the console</a></li>\n" + 
"	<li><a href=\"renderJapidWith/templates/Msg.html\">use\n" + 
"	predefined messages</a></li>\n" + 
"	<li><a href=\"application/postlist\">looping and tag calling</a></li>\n" + 
"	<li><a href=\"application/each\">the *Each* tag</a></li>\n" + 
"	<li><a href=\"application/ifs2\">open if statement: minimalistic if-else exposing complete looping properties</a>\n" + 
"	   <p>example: </p>\n" + 
"	   <pre>\n" + 
"		    `if expr\n" + 
"		        xxx\n" + 
"		    `else if expr\n" + 
"		        yyy\n" + 
"		    `else\n" + 
"		        zzz\n" + 
"		    `\n" + 
"	   </pre>\n" + 
"	</li>\n" + 
"	<li><a href=\"application/invokeInLoop\">using invoke tag with\n" + 
"	local variables requires final attribute</a></li>\n" + 
"	<li><a href=\"renderJapidWith/templates/suppressNull.html\">suppressNull,\n" + 
"	a directive to allow safe navigation in expression, default off</a></li>\n" + 
"	<li><a href=\"application/dumpPost\">show how to dump a post\n" + 
"	detail with japid.dump.request spec in the application.conf. see\n" + 
"	console for output.</a></li>\n" + 
"	<li><a href=\"application/cacheWithRenderJapid?a=hi\">cache with\n" + 
"	renderJapid</a></li>\n" + 
"	<li><a href=\"application/testCacheFor\">using the CacheFor\n" + 
"	annotation</a></li>\n" + 
"	<li><a href=\"application/in\">action forwarding with\n" + 
"	dontRedirect() from JapidController</a></li>\n" + 
"	<li><a href=\"renderJapidWith/templates/openBrace.html\"> use\n" + 
"	`{ in if and while </a></li>\n" + 
"	<li><a href=\"more.ContentNegotiation/index\"> content\n" + 
"	negotiation.</a> Use tools like CURL to test it: <pre>curl -i -H \"Accept: application/json\" http://127.0.0.1:9000/more.ContentNegotiation/index</pre>\n" + 
"	<p>Content negotiation works with renderJapid(), which does\n" + 
"	implicit parameter binding and template picking.</p>\n" + 
"	<p>For more doc: see <a\n" + 
"		href=\"http://www.playframework.org/documentation/1.1/routes#content-negotiation\">Play\n" + 
"	Content Negotiation</a></p>\n" + 
"	</li>\n" + 
"	<li><a href=\"more.MyController/quickview\"> Use relative path\n" + 
"	in layout spec and tags</a>: prefix the layout name or the tag name with a\n" + 
"	dot \".\" to let the compiler prefix the path with the current package.\n" + 
"	This saves using the full and long class qualifications.</li>\n" + 
"</ul>\n" + 
"\n");// line 17

	}

}