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
// NOTE: This file was generated from: japidviews/Application/ifs.html
// Change to this file will be lost next time the template file is compiled.
//
@cn.bran.play.NoEnhance
public class ifs extends cn.bran.japid.template.JapidTemplateBase
{	public static final String sourceTemplate = "japidviews/Application/ifs.html";
{
	headers.put("Content-Type", "text/html; charset=utf-8");
}
	public ifs() {
		super(null);
	}
	public ifs(StringBuilder out) {
		super(out);
	}
	private String str;
	private Collection col;
	private boolean b;
	private Object[] a1;
	private int[] a2;
	private int i;
	private String s2;
	public cn.bran.japid.template.RenderResult render(String str, Collection col, boolean b, Object[] a1, int[] a2, int i, String s2) {
		this.str = str;
		this.col = col;
		this.b = b;
		this.a1 = a1;
		this.a2 = a2;
		this.i = i;
		this.s2 = s2;
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
;// line 1
p("\n" + 
"<p>\n" + 
"<pre>\n");// line 1
p("\n" + 
"    `if expr {\n" + 
"        xxx\n" + 
"    `} else {\n" + 
"        yyy\n" + 
"    `}\n");// line 5
p("</pre>\n" + 
"</p>\n" + 
"<p>\n" + 
"    is equals to\n" + 
"</p>\n" + 
"<p>\n" + 
"<pre>\n");// line 11
p("\n" + 
"    `if(cn.bran.play.WebUtils.asBoolean(expr)){\n" + 
"        xxx\n" + 
"    `} else {\n" + 
"        yyy\n" + 
"    `}\n");// line 19
p("<pre>\n" + 
"\n" + 
"<p/>\n");// line 25
if(asBoolean(str)) {// line 29
p("    Got ");// line 29
p(str);// line 30
p("\n");// line 30
} else {// line 31
p("    str is empty\n");// line 31
}// line 33
p("<p/>\n" + 
"\n");// line 33
if(asBoolean(col)) {// line 36
p("    Got data from col: ");// line 36
p(col);// line 37
p("\n");// line 37
} else {// line 38
p("    col is empty\n");// line 38
}// line 40
p("\n" + 
"<p/>\n");// line 40
if(asBoolean(b)) {// line 43
p("    right\n");// line 43
} else {// line 45
p("    wrong\n");// line 45
}// line 47
p("\n" + 
"<p/>\n");// line 47
if(asBoolean(a1)) {// line 50
p("    got a1: ");// line 50
p(a1);// line 51
p("\n");// line 51
} else {// line 52
p("    a1 is empty\n");// line 52
}// line 54
p("<p/>\n");// line 54
if(asBoolean(a2)) {// line 56
p("    got a2: ");// line 56
p(a2);// line 57
p("\n");// line 57
} else {// line 58
p("    a2 is empty\n");// line 58
}// line 60
p("<p/>\n");// line 60
if(asBoolean(i)) {// line 62
p("    got i: ");// line 62
p(i);// line 63
p("\n");// line 63
} else {// line 64
p("    i == 0\n");// line 64
}// line 66
p("<p/>\n");// line 66
if(asBoolean(s2)) {// line 68
p("    got s2: ");// line 68
p(s2);// line 69
p("\n");// line 69
} else {// line 70
p("    s2 is empty\n");// line 70
}// line 72
p("\n" + 
"\n" + 
"\n" + 
"\n" + 
"\n");// line 72

	}

}