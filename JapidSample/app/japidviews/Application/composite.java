package japidviews.Application;
import java.util.*;
import java.io.*;
import cn.bran.japid.tags.Each;
import cn.bran.japid.template.ActionRunner;
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
import controllers.Application;
import static play.templates.JavaExtensions.*;
import static cn.bran.play.JapidPlayAdapter.*;
import static play.data.validation.Validation.*;
import static cn.bran.play.WebUtils.*;
// NOTE: This file was generated from: japidviews/Application/composite.html
// Change to this file will be lost next time the template file is compiled.
@cn.bran.play.NoEnhance
public class composite extends lcomposite2{
	public static final String sourceTemplate = "japidviews/Application/composite.html";
static private final String static_0 = ""
;
static private final String static_1 = ""
;
static private final String static_2 = ""
;
static private final String static_3 = "\n" + 
"\n" + 
"\n" + 
"<p>This action won't be cached, unless the action has CacheFor annotation.</p>\n" + 
"<div>"
;
static private final String static_4 = "</div>\n" + 
"\n" + 
"<div>this one has full cache control</div>\n" + 
"<div>"
;
static private final String static_5 = "</div>\n" + 
"\n" + 
"<div>This one invokes an action with two params. Note the twoPrams() result is cached since the action carries CacheFor annotation.</div>\n" + 
"<div>"
;
static private final String static_6 = "</div>\n" + 
"\n" + 
"<p>Let's invoke a tag which invokes an action</p>\n" + 
"";
static private final String static_7 = ""
;
	public composite() {
		super(null);
	}
	public composite(StringBuilder out) {
		super(out);
	}
	models.japidsample.Post post;
	public cn.bran.japid.template.RenderResult render(models.japidsample.Post post) {
		this.post = post;
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
		cn.bran.play.FieldErrors errors = new cn.bran.play.FieldErrors(validation.errors());assert errors != null;
		play.Play _play = new play.Play(); assert _play != null;
p(static_0);// line 1
p(static_1);// line 1
p(static_2);// line 2
p(static_3);// line 3
		actionRunners.put(getOut().length(), new cn.bran.play.CacheablePlayActionRunner("", "Application.authorPanel", post.getAuthor()) {
			@Override
			public void runPlayAction() throws cn.bran.play.JapidResult {
				Application.authorPanel(post.getAuthor()); //
			}
		});

// line 8
p(static_4);// line 8
		actionRunners.put(getOut().length(), new cn.bran.play.CacheablePlayActionRunner("10s", "Application.authorPanel", post.getAuthor()) {
			@Override
			public void runPlayAction() throws cn.bran.play.JapidResult {
				Application.authorPanel(post.getAuthor()); //
			}
		});

// line 11
p(static_5);// line 11
		actionRunners.put(getOut().length(), new cn.bran.play.CacheablePlayActionRunner("", "Application.twoParams", "hello", 10) {
			@Override
			public void runPlayAction() throws cn.bran.play.JapidResult {
				Application.twoParams("hello", 10); //
			}
		});

// line 14
p(static_6);// line 14
_invokeInTag3.setActionRunners(getActionRunners());
_invokeInTag3.render();
// line 17
p(static_7);// line 17

	}
	private invokeInTag _invokeInTag3 = new invokeInTag(getOut());
}
