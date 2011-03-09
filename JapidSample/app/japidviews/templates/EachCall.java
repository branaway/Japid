package japidviews.templates;
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
// NOTE: This file was generated from: japidviews/templates/EachCall.html
// Change to this file will be lost next time the template file is compiled.
//
@cn.bran.play.NoEnhance
public class EachCall extends cn.bran.japid.template.JapidTemplateBase
{	public static final String sourceTemplate = "japidviews/templates/EachCall.html";
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

final SampleTag _SampleTag2 = new SampleTag(getOut());
{ _SampleTag2.setActionRunners(getActionRunners()); }

final SampleTag _SampleTag4 = new SampleTag(getOut());
{ _SampleTag4.setActionRunners(getActionRunners()); }

final Each _Each3 = new Each(getOut());
{ _Each3.setActionRunners(getActionRunners()); }

// -- end of the tag objects

	public EachCall() {
		super(null);
	}
	public EachCall(StringBuilder out) {
		super(out);
	}
	private List<String> posts;
	public cn.bran.japid.template.RenderResult render(List<String> posts) {
		this.posts = posts;
		long t = -1;
		super.layout();
		return new cn.bran.japid.template.RenderResult(this.headers, getOut(), t);
	}
	@Override protected void doLayout() {
//------
;// line 1
p("\n" + 
"<p>\n" + 
"The \"each/Each\" command is a for loop on steroid, with lots of loop information. \n" + 
"</p>\n" + 
"\n" + 
"<p> \n" + 
"The instance variable is defined after the | line, the same way as any tag render-back\n" + 
"</p>\n" + 
"\n");// line 1
_Each0.setOut(getOut()); _Each0.render(posts, new Each.DoBody<String>(){
public void render(final String p, final int _size, final int _index, final boolean _isOdd, final String _parity, final boolean _isFirst, final boolean _isLast) {
// line 11
p("    <p>index: ");// line 11
p(_index);// line 12
p(", parity: ");// line 12
p(_parity);// line 12
p(", is odd? ");// line 12
p(_isOdd);// line 12
p(", is first? ");// line 12
p(_isFirst);// line 12
p(", is last? ");// line 12
p(_isLast);// line 12
p(", total size: ");// line 12
p(_size);// line 12
p(" </p>\n" + 
"    call a tag:  ");// line 12
_SampleTag1.setOut(getOut()); _SampleTag1.render(p);
// line 13

}
}
);
// line 11
p("\n");// line 14
_SampleTag2.setOut(getOut()); _SampleTag2.render("end");
// line 16
p("\n" + 
"<p> now we have an enhanced for loop (the \"open for loop\") that also makes all the loop properties available</p>\n" + 
"\n");// line 16
_Each3.setOut(getOut()); _Each3.render(posts, new Each.DoBody<String>(){
public void render(final String p, final int _size, final int _index, final boolean _isOdd, final String _parity, final boolean _isFirst, final boolean _isLast) {
// line 20
p("    <p>index: ");// line 20
p(_index);// line 21
p(", parity: ");// line 21
p(_parity);// line 21
p(", is odd? ");// line 21
p(_isOdd);// line 21
p(", is first? ");// line 21
p(_isFirst);// line 21
p(", is last? ");// line 21
p(_isLast);// line 21
p(", total size: ");// line 21
p(_size);// line 21
p(" </p>\n" + 
"    call a tag:  ");// line 21
_SampleTag4.setOut(getOut()); _SampleTag4.render(p);
// line 22

}
}
);
// line 20

	}

}