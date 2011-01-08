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
import play.mvc.Http.*;
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

		play.mvc.Http.Request request = play.mvc.Http.Request.current(); assert request != null;
		play.mvc.Http.Response response = play.mvc.Http.Response.current(); assert response != null;
		play.mvc.Scope.Flash flash = play.mvc.Scope.Flash.current();assert flash != null;
		play.mvc.Scope.Session session = play.mvc.Scope.Session.current();assert session != null;
		play.mvc.Scope.RenderArgs renderArgs = play.mvc.Scope.RenderArgs.current(); assert renderArgs != null;
		play.mvc.Scope.Params params = play.mvc.Scope.Params.current();assert params != null;
		play.data.validation.Validation validation = play.data.validation.Validation.current();assert validation!= null;
		cn.bran.play.FieldErrors errors = new cn.bran.play.FieldErrors(validation);assert errors != null;
		play.Play _play = new play.Play(); assert _play != null;
;// line 1
p("the closure form:\n");// line 1
_Each0.setActionRunners(getActionRunners());
_Each0.render(posts, _Each0DoBody);
// line 4
;// line 6

	}
	private Each _Each0 = new Each(getOut());
class Each0DoBody implements Each.DoBody< String>{
	public void render(String p, int _index, boolean _isOdd, String _parity, boolean _isFirst, boolean _isLast) {
		// line 4
p("	<p>");// line 4
p(_index);// line 5
p(" :: ");// line 5
p(p);// line 5
p(" || ");// line 5
p(_parity);// line 5
p(", ^ ");// line 5
p(_isOdd);// line 5
p(", @ ");// line 5
p(_isFirst);// line 5
p(", # ");// line 5
p(_isLast);// line 5
p("</p>\n");// line 5

	}
}
	private Each0DoBody _Each0DoBody = new Each0DoBody();

}
