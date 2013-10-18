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

	final play.mvc.Http.Request request = play.mvc.Http.Request.current(); 
	final play.mvc.Http.Response response = play.mvc.Http.Response.current(); 
	final play.mvc.Scope.Session session = play.mvc.Scope.Session.current();
	final play.mvc.Scope.RenderArgs renderArgs = play.mvc.Scope.RenderArgs.current();
	final play.mvc.Scope.Params params = play.mvc.Scope.Params.current();
	final play.data.validation.Validation validation = play.data.validation.Validation.current();
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

	private List<String> posts; // line 1, japidviews/templates/EachCall.html
	public cn.bran.japid.template.RenderResult render(List<String> posts) {
		this.posts = posts;
		long __t = -1;
		try {super.layout();} catch (RuntimeException __e) { super.handleException(__e);} // line 1, japidviews/templates/EachCall.html
		return new cn.bran.japid.template.RenderResultPartial(getHeaders(), getOut(), __t, actionRunners, sourceTemplate);
	}

	public static cn.bran.japid.template.RenderResult apply(List<String> posts) {
		return new EachCall().render(posts);
	}

	@Override protected void doLayout() {
		beginDoLayout(sourceTemplate);
//------
;// line 1, EachCall.html
		p("\n" + 
"<p>\n" + 
"The \"each/Each\" command is a for loop on steroid, with lots of loop information. \n" + 
"</p>\n" + 
"\n" + 
"<p> \n" + 
"The instance variable is defined after the | line, the same way as any tag render-back\n" + 
"</p>\n" + 
"\n");// line 1, EachCall.html
		final Each _Each0 = new Each(getOut()); _Each0.setOut(getOut()); _Each0.render(// line 11, EachCall.html
posts, new Each.DoBody<String>(){ // line 11, EachCall.html
public void render(final String p, final int _size, final int _index, final boolean _isOdd, final String _parity, final boolean _isFirst, final boolean _isLast) { // line 11, EachCall.html
// line 11, EachCall.html
		p("    <p>index: ");// line 11, EachCall.html
		p(_index);// line 12, EachCall.html
		p(", parity: ");// line 12, EachCall.html
		p(_parity);// line 12, EachCall.html
		p(", is odd? ");// line 12, EachCall.html
		p(_isOdd);// line 12, EachCall.html
		p(", is first? ");// line 12, EachCall.html
		p(_isFirst);// line 12, EachCall.html
		p(", is last? ");// line 12, EachCall.html
		p(_isLast);// line 12, EachCall.html
		p(", total size: ");// line 12, EachCall.html
		p(_size);// line 12, EachCall.html
		p(" </p>\n" + 
"    call a tag:  ");// line 12, EachCall.html
		final SampleTag _SampleTag1 = new SampleTag(getOut()); _SampleTag1.setActionRunners(getActionRunners()).setOut(getOut()); _SampleTag1.render(p); // line 13, EachCall.html// line 13, EachCall.html

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
);// line 11, EachCall.html

final SampleTag _SampleTag2 = new SampleTag(getOut()); _SampleTag2.setActionRunners(getActionRunners()).setOut(getOut()); _SampleTag2.render("end"); // line 16, EachCall.html// line 16, EachCall.html
		p("\n" + 
"<p> now we have an enhanced for loop (the \"open for loop\") that also makes all the loop properties available</p>\n" + 
"\n");// line 16, EachCall.html
		 int k = 1;// line 20, EachCall.html
final Each _Each3 = new Each(getOut()); _Each3.setOut(getOut()); _Each3.render(// line 21, EachCall.html
posts, new Each.DoBody<String>(){ // line 21, EachCall.html
public void render(final String p, final int _size, final int _index, final boolean _isOdd, final String _parity, final boolean _isFirst, final boolean _isLast) { // line 21, EachCall.html
// line 21, EachCall.html
		p("    <p>index: ");// line 21, EachCall.html
		p(_index);// line 22, EachCall.html
		p(", parity: ");// line 22, EachCall.html
		p(_parity);// line 22, EachCall.html
		p(", is odd? ");// line 22, EachCall.html
		p(_isOdd);// line 22, EachCall.html
		p(", is first? ");// line 22, EachCall.html
		p(_isFirst);// line 22, EachCall.html
		p(", is last? ");// line 22, EachCall.html
		p(_isLast);// line 22, EachCall.html
		p(", total size: ");// line 22, EachCall.html
		p(_size);// line 22, EachCall.html
		p(" </p>\n" + 
"    call a tag:  ");// line 22, EachCall.html
		final SampleTag _SampleTag4 = new SampleTag(getOut()); _SampleTag4.setActionRunners(getActionRunners()).setOut(getOut()); _SampleTag4.render(p); // line 23, EachCall.html// line 23, EachCall.html

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
);// line 21, EachCall.html
		p("\n" + 
"\n");// line 24, EachCall.html
		int[] ints = {1, 2, 3, 4};// line 27, EachCall.html
final Each _Each5 = new Each(getOut()); _Each5.setOut(getOut()); _Each5.render(// line 28, EachCall.html
ints, new Each.DoBody<Integer>(){ // line 28, EachCall.html
public void render(final Integer i, final int _size, final int _index, final boolean _isOdd, final String _parity, final boolean _isFirst, final boolean _isLast) { // line 28, EachCall.html
// line 28, EachCall.html
		p("    --> ");// line 28, EachCall.html
		p(escape(i));// line 29, EachCall.html
		p("\n" + 
"    ");// line 29, EachCall.html
		if (i == 2) {// line 30, EachCall.html
        return; // this will work as continue// line 31, EachCall.html
    } else {// line 32, EachCall.html
		p("         :--\n" + 
"    ");// line 32, EachCall.html
		}// line 34, EachCall.html

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
);// line 28, EachCall.html
		;// line 35, EachCall.html
		
		endDoLayout(sourceTemplate);
	}

}