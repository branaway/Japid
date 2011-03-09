package japidviews.Application;
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
// NOTE: This file was generated from: japidviews/Application/ifs.html
// Change to this file will be lost next time the template file is compiled.
//
@cn.bran.play.NoEnhance
public class ifs extends cn.bran.japid.template.JapidTemplateBase
{	public static final String sourceTemplate = "japidviews/Application/ifs.html";
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
//------
;// line 1
p("\n" + 
"<p>\n" + 
"<pre>\n");// line 1
p("\n" + 
"    `if expr {\n" + 
"        xxx\n" + 
"    `} else if expr {\n" + 
"        yyy\n" + 
"    `} else {\n" + 
"        zzz\n" + 
"    `}\n");// line 5
p("</pre>\n" + 
"</p>\n" + 
"<p>\n" + 
"    is equals to\n" + 
"</p>\n" + 
"<p>\n" + 
"<pre>\n");// line 13
p("\n" + 
"    `if(cn.bran.play.WebUtils.asBoolean(expr)){\n" + 
"        xxx\n" + 
"    `} else if(cn.bran.play.WebUtils.asBoolean(expr)){\n" + 
"        yyy\n" + 
"    `} else {\n" + 
"        zzz\n" + 
"    `}\n");// line 21
p("<pre>\n" + 
"\n" + 
"<p/>\n");// line 29
if(asBoolean(str)) {// line 33
p("    Got ");// line 33
p(str);// line 34
p("\n");// line 34
} else if(asBoolean(str )) {// line 35
p("    finally got ");// line 35
p(str);// line 36
p("\n");// line 36
} else {// line 37
p("    str is empty\n");// line 37
}// line 39
p("<p/>\n" + 
"\n");// line 39
if(asBoolean(col)) {// line 42
p("    Got data from col: ");// line 42
p(col);// line 43
p("\n");// line 43
} else {// line 44
p("    col is empty\n");// line 44
}// line 46
p("\n" + 
"<p/>\n");// line 46
if(asBoolean(b)) {// line 49
p("    right\n");// line 49
} else {// line 51
p("    wrong\n");// line 51
}// line 53
p("\n" + 
"<p/>\n");// line 53
if(asBoolean(a1)) {// line 56
p("    got a1: ");// line 56
p(a1);// line 57
p("\n");// line 57
} else {// line 58
p("    a1 is empty\n");// line 58
}// line 60
p("<p/>\n");// line 60
if(asBoolean(a2)) {// line 62
p("    got a2: ");// line 62
p(a2);// line 63
p("\n");// line 63
} else {// line 64
p("    a2 is empty\n");// line 64
}// line 66
p("<p/>\n");// line 66
if(asBoolean(i)) {// line 68
p("    got i: ");// line 68
p(i);// line 69
p("\n");// line 69
} else {// line 70
p("    i == 0\n");// line 70
}// line 72
p("<p/>\n");// line 72
if(asBoolean(s2)) {// line 74
p("    got s2: ");// line 74
p(s2);// line 75
p("\n");// line 75
} else {// line 76
p("    s2 is empty\n");// line 76
}// line 78
p("\n" + 
"<p>try the negation</p>\n");// line 78
String ss = str;// line 81
if(!asBoolean(ss)) {// line 82
p("    ss is empty\n");// line 82
} else if(!asBoolean(ss)) {// line 84
p("    again...\n");// line 84
} else {// line 86
p("    ss has something\n");// line 86
}// line 88
p("\n" + 
"\n" + 
"\n" + 
"\n");// line 88

	}

}