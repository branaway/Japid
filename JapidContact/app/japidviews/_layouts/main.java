package japidviews._layouts;
import java.util.*;
import java.io.*;
import cn.bran.japid.tags.Each;
import static play.templates.JavaExtensions.*;
import static cn.bran.play.JapidPlayAdapter.*;
import static play.data.validation.Validation.*;
import japidviews._layouts.*;
import play.i18n.Messages;
import static  japidviews._javatags.JapidWebUtil.*;
import play.data.validation.Validation;
import play.mvc.Scope.*;
import models.*;
import play.data.validation.Error;
import play.i18n.Lang;
import japidviews._tags.*;
import controllers.*;
import play.mvc.Http.*;
import japidviews._javatags.*;
//
// NOTE: This file was generated from: japidviews/_layouts/main.html
// Change to this file will be lost next time the template file is compiled.
//
@cn.bran.play.NoEnhance
public abstract class main extends cn.bran.japid.template.JapidTemplateBase
{	public static final String sourceTemplate = "japidviews/_layouts/main.html";
{
	headers.put("Content-Type", "text/html; charset=utf-8");
}

// - add implicit fields with Play

	final Request request = Request.current(); 
	final Response response = Response.current(); 
	final Session session = Session.current();
	final RenderArgs renderArgs = RenderArgs.current();
	final Params params = Params.current();
	final Validation validation = Validation.current();
	final cn.bran.play.FieldErrors errors = new cn.bran.play.FieldErrors(validation);
	final play.Play _play = new play.Play(); 

// - end of implicit fields with Play 


	public main() {
		super(null);
	}
	public main(StringBuilder out) {
		super(out);
	}
	@Override public void layout() {
		p("<!DOCTYPE html>\n" + 
"<html>\n" + 
"    <head>\n" + 
"    	<title>JapidContact, by zenexity/Bing Ran  ★ ");// line 1
	title();p(" </title>\n" + 
"    	<meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\"/>\n" + 
"        <link rel=\"stylesheet\" type=\"text/css\" media=\"screen\" href=\"");// line 4
p(lookupStatic("public/stylesheets/style.css"));// line 6
p("\" />\n" + 
"        <link rel=\"stylesheet\" type=\"text/css\" media=\"screen\" href=\"");// line 6
p(lookupStatic("public/stylesheets/south-street/jquery-ui-1.7.2.custom.css"));// line 7
p("\" />\n" + 
"    	<script src=\"");// line 7
p(lookupStatic("public/javascripts/jquery-1.4.min.js"));// line 8
p("\" type=\"text/javascript\" charset=\"utf-8\"></script>\n" + 
"    	<script src=\"");// line 8
p(lookupStatic("public/javascripts/jquery-ui-1.7.2.custom.min.js"));// line 9
p("\" type=\"text/javascript\" charset=\"utf-8\"></script>\n" + 
"    	<script src=\"");// line 9
p(lookupStatic("public/javascripts/jquery.editinplace.packed.js"));// line 10
p("\" type=\"text/javascript\" charset=\"utf-8\"></script>\n" + 
"    </head>\n" + 
"	<body>\n" + 
"	    <div id=\"zencontact\">\n" + 
"    		<header>\n" + 
"    			<img src=\"");// line 10
p(lookupStatic("public/images/logo.png"));// line 15
p("\" alt=\"logo\" id=\"logo\" />\n" + 
"    			<h1>Japid Contact <span>by zenexity & Bing Ran</span></h1>\n" + 
"    		</header>\n" + 
"    		<nav>\n" + 
"    			<a id=\"home\" href=\"");// line 15
p(lookup("index", new Object[]{}));// line 19
p("\" class=\"");// line 19
p(selected(".*index"));// line 19
p("\">Home</a>\n" + 
"    			<a id=\"list\" href=\"");// line 19
p(lookup("list", new Object[]{}));// line 20
p("\" class=\"");// line 20
p(selected(".*list"));// line 20
p("\">List</a>\n" + 
"    			<a id=\"new\" href=\"");// line 20
p(lookup("form", new Object[]{}));// line 21
p("\" class=\"");// line 21
p(selected(".*form|.*save"));// line 21
p("\">New</a>\n" + 
"    		</nav>\n" + 
"    		<section>\n" + 
"    		    ");// line 21
	doLayout();
p("    		</section>\n" + 
"    		<footer>\n" + 
"    			<a href=\"http://www.w3.org/TR/html5/\">html5</a> - \n" + 
"    			<a href=\"http://www.w3.org/TR/css3-roadmap/\">css3</a> - \n" + 
"    			<a href=\"http://www.playframework.org/\">playframework with Japid</a> \n" + 
"    		</footer>\n" + 
"		</div>\n" + 
"	</body>\n" + 
"</html>\n" + 
"\n");// line 24
p("\n");// line 35
// line 37
;// line 39
	}
	 protected void title() {};

	protected abstract void doLayout();
public String selected(String pattern) {
StringBuilder sb = new StringBuilder();
StringBuilder ori = getOut();
this.setOut(sb);
// line 37
p("");// line 37
p(request.action.matches(pattern) ? "selected" : "");// line 38
p("");// line 38

this.setOut(ori);
return sb.toString();
}
}