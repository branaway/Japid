package japidviews.templates;
import java.util.*;
import java.io.*;
import cn.bran.japid.tags.Each;
import static play.templates.JavaExtensions.*;
import static cn.bran.play.JapidPlayAdapter.*;
import static play.data.validation.Validation.*;
import japidviews._layouts.*;
import play.i18n.Messages;
import play.data.validation.Validation;
import play.mvc.Scope.*;
import models.*;
import play.data.validation.Error;
import play.i18n.Lang;
import japidviews._tags.*;
import play.mvc.Http.*;
import controllers.*;
import japidviews._javatags.*;
//
// NOTE: This file was generated from: japidviews/templates/EachCall.html
// Change to this file will be lost next time the template file is compiled.
//
@cn.bran.play.NoEnhance
public class EachCall extends cn.bran.play.JapidTemplateBase
{
	public static final String sourceTemplate = "japidviews/templates/EachCall.html";
	{
		putHeader("Content-Type", "text/html; charset=utf-8");
		setContentType("text/html; charset=utf-8");
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


	public EachCall() {
		super(null);
	}
	public EachCall(StringBuilder out) {
		super(out);
	}
/* based on https://github.com/branaway/Japid/issues/12
 */
	public static final String[] argNames = new String[] {/* args of the template*/"posts",  };
	public static final String[] argTypes = new String[] {/* arg types of the template*/"List<String>",  };
	public static final Object[] argDefaults= new Object[] {null, };
	public static java.lang.reflect.Method renderMethod = getRenderMethod(japidviews.templates.EachCall.class);

	{
		setRenderMethod(renderMethod);
		setArgNames(argNames);
		setArgTypes(argTypes);
		setArgDefaults(argDefaults);
		setSourceTemplate(sourceTemplate);
	}
////// end of named args stuff

	private List<String> posts; // line 1
	public cn.bran.japid.template.RenderResult render(List<String> posts) {
		this.posts = posts;
		long t = -1;
		try {super.layout();} catch (RuntimeException e) { super.handleException(e);} // line 1
		return new cn.bran.japid.template.RenderResultPartial(getHeaders(), getOut(), t, actionRunners, sourceTemplate);
	}
	@Override protected void doLayout() {
		beginDoLayout(sourceTemplate);
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
		final Each _Each0 = new Each(getOut()); _Each0.setOut(getOut()); _Each0.render(// line 11
posts, new Each.DoBody<String>(){ // line 11
public void render(final String p, final int _size, final int _index, final boolean _isOdd, final String _parity, final boolean _isFirst, final boolean _isLast) { // line 11
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
		final SampleTag _SampleTag1 = new SampleTag(getOut()); _SampleTag1.setActionRunners(getActionRunners()).setOut(getOut()); _SampleTag1.render(p); // line 13// line 13

}

StringBuilder oriBuffer;
@Override
public void setBuffer(StringBuilder sb) {
	oriBuffer = getOut();
	setOut(sb);
}

@Override
public void resetBuffer() {
	setOut(oriBuffer);
}

}
);// line 11
		p("\n");// line 14
		final SampleTag _SampleTag2 = new SampleTag(getOut()); _SampleTag2.setActionRunners(getActionRunners()).setOut(getOut()); _SampleTag2.render("end"); // line 16// line 16
		p("\n" + 
"<p> now we have an enhanced for loop (the \"open for loop\") that also makes all the loop properties available</p>\n" + 
"\n");// line 16
		 int k = 1;// line 20
final Each _Each3 = new Each(getOut()); _Each3.setOut(getOut()); _Each3.render(// line 21
posts, new Each.DoBody<String>(){ // line 21
public void render(final String p, final int _size, final int _index, final boolean _isOdd, final String _parity, final boolean _isFirst, final boolean _isLast) { // line 21
// line 21
		p("    <p>index: ");// line 21
		p(_index);// line 22
		p(", parity: ");// line 22
		p(_parity);// line 22
		p(", is odd? ");// line 22
		p(_isOdd);// line 22
		p(", is first? ");// line 22
		p(_isFirst);// line 22
		p(", is last? ");// line 22
		p(_isLast);// line 22
		p(", total size: ");// line 22
		p(_size);// line 22
		p(" </p>\n" + 
"    call a tag:  ");// line 22
		final SampleTag _SampleTag4 = new SampleTag(getOut()); _SampleTag4.setActionRunners(getActionRunners()).setOut(getOut()); _SampleTag4.render(p); // line 23// line 23

}

StringBuilder oriBuffer;
@Override
public void setBuffer(StringBuilder sb) {
	oriBuffer = getOut();
	setOut(sb);
}

@Override
public void resetBuffer() {
	setOut(oriBuffer);
}

}
);// line 21
		p("\n" + 
"\n");// line 24
		int[] ints = {1, 2,3};// line 27
final Each _Each5 = new Each(getOut()); _Each5.setOut(getOut()); _Each5.render(// line 28
ints, new Each.DoBody<Integer>(){ // line 28
public void render(final Integer i, final int _size, final int _index, final boolean _isOdd, final String _parity, final boolean _isFirst, final boolean _isLast) { // line 28
// line 28
		p("    --> ");// line 28
		p(escape(i));// line 29
		p("\n");// line 29
		
}

StringBuilder oriBuffer;
@Override
public void setBuffer(StringBuilder sb) {
	oriBuffer = getOut();
	setOut(sb);
}

@Override
public void resetBuffer() {
	setOut(oriBuffer);
}

}
);// line 28
		;// line 30
		
		endDoLayout(sourceTemplate);
	}

}