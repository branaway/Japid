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
// NOTE: This file was generated from: japidviews/Application/verbatim.html
// Change to this file will be lost next time the template file is compiled.
//
@cn.bran.play.NoEnhance
public class verbatim extends cn.bran.japid.template.JapidTemplateBase
{	public static final String sourceTemplate = "japidviews/Application/verbatim.html";
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
final Each _Each0 = new Each(getOut());
{ _Each0.setActionRunners(getActionRunners()); }

// -- end of the tag objects

	public verbatim() {
		super(null);
	}
	public verbatim(StringBuilder out) {
		super(out);
	}
	public cn.bran.japid.template.RenderResult render() {
		long t = -1;
		super.layout();
		return new cn.bran.japid.template.RenderResult(this.headers, getOut(), t);
	}
	@Override protected void doLayout() {
//------
p("\n" + 
"<p>\n" + 
"you should be able to see all Japid command un-interpreted.    	\n" + 
"</p>\n" + 
"\n");// line 1
p("\n" + 
"\n" + 
"	`args models.japidsample.Author a\n" + 
"	\n" + 
"	<p>author name: $a.name</p>\n" + 
"	<p>his birthdate: $a.birthDate</p>\n" + 
"	<p>and his is a '${a.getGender()}'</p>\n" + 
"	    `tag SampleTag \"end\"\n" + 
"    \n");// line 6
p("\n" + 
"<p>got it?</p>\n" + 
"\n");// line 15
String[] ss = new String[]{"a", "b"};// line 18
_Each0.setOut(getOut()); _Each0.render(ss, new Each.DoBody<String>(){
public void render(final String s, final int _size, final int _index, final boolean _isOdd, final String _parity, final boolean _isFirst, final boolean _isLast) {
// line 19
p("    <p>loop: ");// line 19
p(s);// line 20
p("</p>\n" + 
"    ");// line 20
p("\n" + 
"    <p>please use ` to start command and $s to get the value</p>\n" + 
"    ");// line 21
;// line 23

}
}
);
// line 19

	}

}