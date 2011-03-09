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
// NOTE: This file was generated from: japidviews/Application/ifs2.html
// Change to this file will be lost next time the template file is compiled.
//
@cn.bran.play.NoEnhance
public class ifs2 extends cn.bran.japid.template.JapidTemplateBase
{	public static final String sourceTemplate = "japidviews/Application/ifs2.html";
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



// -- set up the tag objects
final SampleTag _SampleTag1 = new SampleTag(getOut());
{ _SampleTag1.setActionRunners(getActionRunners()); }

final Each _Each0 = new Each(getOut());
{ _Each0.setActionRunners(getActionRunners()); }

final SampleTag _SampleTag3 = new SampleTag(getOut());
{ _SampleTag3.setActionRunners(getActionRunners()); }

final Each _Each2 = new Each(getOut());
{ _Each2.setActionRunners(getActionRunners()); }

// -- end of the tag objects

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
_Each0.setOut(getOut()); _Each0.render(ss, new Each.DoBody<String>(){
public void render(final String s, final int _size, final int _index, final boolean _isOdd, final String _parity, final boolean _isFirst, final boolean _isLast) {
// line 32
p("        call a tag\n" + 
"        ");// line 32
_SampleTag1.setOut(getOut()); _SampleTag1.render(s);
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
_Each2.setOut(getOut()); _Each2.render(ss, new Each.DoBody<String>(){
public void render(final String s, final int _size, final int _index, final boolean _isOdd, final String _parity, final boolean _isFirst, final boolean _isLast) {
// line 38
p("        call a tag\n" + 
"        ");// line 38
_SampleTag3.setOut(getOut()); _SampleTag3.render(s);
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
p("\n");// line 49

	}

}