package japidviews.templates;
import java.util.*;
import java.io.*;
import cn.bran.japid.tags.Each;
import japidviews._layouts.*;
import static  japidviews._javatags.JapidWebUtil.*;
import play.data.validation.Validation;
import play.mvc.Scope.*;
import models.*;
import play.data.validation.Error;
import japidviews._tags.*;
import controllers.*;
import japidviews._javatags.*;
import static play.templates.JavaExtensions.*;
import static cn.bran.play.JapidPlayAdapter.*;
import static play.data.validation.Validation.*;
import static cn.bran.play.WebUtils.*;
// NOTE: This file was generated from: japidviews/templates/EachCall.html
// Change to this file will be lost next time the template file is compiled.
@cn.bran.play.NoEnhance
public class EachCall extends cn.bran.japid.template.JapidTemplateBase{
	public static final String sourceTemplate = "japidviews/templates/EachCall.html";
static private final String static_0 = ""
;
static private final String static_1 = "\n" + 
"the closure form:\n" + 
"";
static private final String static_2 = "\n" + 
"	<p>"
;
static private final String static_3 = " :: "
;
static private final String static_4 = " || "
;
static private final String static_5 = ", ^ "
;
static private final String static_6 = ", @ "
;
static private final String static_7 = ", # "
;
static private final String static_8 = "</p>\n" + 
"";
static private final String static_9 = "\n" + 
"";
	public EachCall() {
		super(null);
	}
	public EachCall(StringBuilder out) {
		super(out);
	}
	List<String> posts;
	public cn.bran.japid.template.RenderResult render(List<String> posts) {
		this.posts = posts;
		long t = -1;
		super.layout();
		return new cn.bran.japid.template.RenderResultPartial(this.headers, getOut(), t, actionRunners);
	}
	@Override protected void doLayout() {
p(static_0);// line 1
p(static_1);// line 1
_Each0.setActionRunners(getActionRunners());
_Each0.render(posts, _Each0DoBody);
// line 4
p(static_9);// line 6

	}
	private Each _Each0 = new Each(getOut());
class Each0DoBody implements Each.DoBody< String>{
	public void render(String p, int _index, boolean _isOdd, String _parity, boolean _isFirst, boolean _isLast) {
		// line 4
p(static_2);// line 4
p(_index);// line 5
p(static_3);// line 5
p(p);// line 5
p(static_4);// line 5
p(_parity);// line 5
p(static_5);// line 5
p(_isOdd);// line 5
p(static_6);// line 5
p(_isFirst);// line 5
p(static_7);// line 5
p(_isLast);// line 5
p(static_8);// line 5

	}
}
	private Each0DoBody _Each0DoBody = new Each0DoBody();

}
