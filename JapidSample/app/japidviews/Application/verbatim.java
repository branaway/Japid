package japidviews.Application;
import java.util.*;
import java.io.*;
import cn.bran.japid.tags.Each;
import static play.templates.JavaExtensions.*;
import static cn.bran.play.JapidPlayAdapter.*;
import static play.data.validation.Validation.*;
import japidviews._layouts.*;
import play.i18n.Messages;
import models.*;
import play.i18n.Lang;
import play.mvc.Http.*;
import play.data.validation.Validation;
import play.mvc.Scope.*;
import play.data.validation.Error;
import japidviews._tags.*;
import controllers.*;
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


	public verbatim() {
		super(null);
	}
	public verbatim(StringBuilder out) {
		super(out);
	}
/* based on https://github.com/branaway/Japid/issues/12
 */
public static final String[] argNames = new String[] {/* args of the template*/ };
public static final String[] argTypes = new String[] {/* arg types of the template*/ };
public static java.lang.reflect.Method renderMethod = getRenderMethod(japidviews.Application.verbatim.class);
{
	setRenderMethod(renderMethod);
	setArgNames(argNames);
	setArgTypes(argTypes);
}
////// end of named args stuff

	public cn.bran.japid.template.RenderResult render() {
		long t = -1;
		super.layout();
		return new cn.bran.japid.template.RenderResultPartial(this.headers, getOut(), t, actionRunners);
	}
	@Override protected void doLayout() {

// -- set up the tag objects
final Each _Each0 = new Each(getOut());
{ _Each0.setActionRunners(getActionRunners()); }

// -- end of the tag objects

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
_Each0.render(ss, new Each.DoBody<String>(){
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