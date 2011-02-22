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
// NOTE: This file was generated from: japidviews/Application/ifs2.html
// Change to this file will be lost next time the template file is compiled.
//
@cn.bran.play.NoEnhance
public class ifs2 extends cn.bran.japid.template.JapidTemplateBase
{	public static final String sourceTemplate = "japidviews/Application/ifs2.html";
{
	headers.put("Content-Type", "text/html; charset=utf-8");
}
	public ifs2() {
		super(null);
	}
	public ifs2(StringBuilder out) {
		super(out);
	}
	private int i;
	private String[] ss;
	public cn.bran.japid.template.RenderResult render(int i, String[] ss) {
		this.i = i;
		this.ss = ss;
		long t = -1;
		super.layout();
		return new cn.bran.japid.template.RenderResult(this.headers, getOut(), t);
	}
	@Override protected void doLayout() {

// -- set up the tag objects
final SampleTag _SampleTag1 = new SampleTag(getOut());
_SampleTag1.setActionRunners(getActionRunners());

final Each _Each0 = new Each(getOut());
_Each0.setActionRunners(getActionRunners());

final SampleTag _SampleTag3 = new SampleTag(getOut());
_SampleTag3.setActionRunners(getActionRunners());

final Each _Each2 = new Each(getOut());
_Each2.setActionRunners(getActionRunners());

// -- end of the tag objects


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
"OK, the minimalism if-else statement, no parenthesis, no braces, like command \n" + 
"<p>\n" + 
"\n" + 
"<pre>\n" + 
"    `if expr\n" + 
"        xxx\n" + 
"    `else if expr\n" + 
"        yyy\n" + 
"    `else\n" + 
"        zzz\n" + 
"    `\n" + 
"</pre>\n" + 
"<p>\n" + 
"    is equals to\n" + 
"</p>\n" + 
"<p>\n" + 
"<pre>\n" + 
"    `if(asBoolean(expr)){\n" + 
"        xxx\n" + 
"    `} else if(asBoolean(expr)){\n" + 
"        yyy\n" + 
"    `} else {\n" + 
"        zzz\n" + 
"    `}\n" + 
"</pre>\n" + 
"\n" + 
"<p/>\n");// line 1
if(asBoolean(ss)) {// line 30
p("    well got ss\n" + 
"    ");// line 30
_Each0.render(ss, new Each.DoBody<String>(){
public void render(final String s, final int _size, final int _index, final boolean _isOdd, final String _parity, final boolean _isFirst, final boolean _isLast) {
// line 32
p("        call a tag\n" + 
"        ");// line 32
_SampleTag1.render(s);
// line 34
    
}
}
);
// line 32
} else if(asBoolean(ss)) {// line 36
p("    finally got ");// line 36
p(ss);// line 37
p("\n" + 
"    ");// line 37
_Each2.render(ss, new Each.DoBody<String>(){
public void render(final String s, final int _size, final int _index, final boolean _isOdd, final String _parity, final boolean _isFirst, final boolean _isLast) {
// line 38
p("        call a tag\n" + 
"        ");// line 38
_SampleTag3.render(s);
// line 40
    
}
}
);
// line 38
} else {// line 42
    if(asBoolean("assd")) {// line 43
p("        a true\n" + 
"    ");// line 43
} else {// line 45
p("        a false\n" + 
"    ");// line 45
}// line 47
p("    ss is empty\n");// line 47
}// line 49
;// line 49

	}

}