package japidviews.Application;
import java.util.*;
import java.io.*;
import cn.bran.japid.tags.Each;
import cn.bran.japid.template.ActionRunner;
import controllers.more.*;
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
// NOTE: This file was generated from: japidviews/Application/composite.html
// Change to this file will be lost next time the template file is compiled.
//
@cn.bran.play.NoEnhance
public class composite extends lcomposite2
{	public static final String sourceTemplate = "japidviews/Application/composite.html";
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
final invokeInTag _invokeInTag4 = new invokeInTag(getOut());
{ _invokeInTag4.setActionRunners(getActionRunners()); }

// -- end of the tag objects

	public composite() {
		super(null);
	}
	public composite(StringBuilder out) {
		super(out);
	}
	private models.japidsample.Post post;
	public cn.bran.japid.template.RenderResult render(models.japidsample.Post post) {
		this.post = post;
		long t = -1;
		super.layout();
		return new cn.bran.japid.template.RenderResultPartial(this.headers, getOut(), t, actionRunners);
	}
	@Override protected void doLayout() {
//------
;// line 1
p("\n" + 
"\n" + 
"\n" + 
"<p>This action won't be cached, unless the action has CacheFor annotation.</p>\n" + 
"<div>");// line 3
		actionRunners.put(getOut().length(), new cn.bran.play.CacheablePlayActionRunner("", Application.class, "authorPanel", post.getAuthor()) {
			@Override
			public void runPlayAction() throws cn.bran.play.JapidResult {
				Application.authorPanel(post.getAuthor()); //
			}
		});

// line 8
p("</div>\n" + 
"<div>Another one in sub package: ");// line 8
		actionRunners.put(getOut().length(), new cn.bran.play.CacheablePlayActionRunner("", SubController.class, "foo", post.getAuthor().name) {
			@Override
			public void runPlayAction() throws cn.bran.play.JapidResult {
				SubController.foo(post.getAuthor().name); //
			}
		});

// line 9
p("</div>\n" + 
"\n" + 
"<div>this one has full cache control</div>\n" + 
"<div>");// line 9
		actionRunners.put(getOut().length(), new cn.bran.play.CacheablePlayActionRunner("10s", Application.class, "authorPanel", post.getAuthor()) {
			@Override
			public void runPlayAction() throws cn.bran.play.JapidResult {
				Application.authorPanel(post.getAuthor()); //
			}
		});

// line 12
p("</div>\n" + 
"\n" + 
"<div>This one invokes an action with two params. Note the twoPrams() result is cached since the action carries CacheFor annotation.</div>\n" + 
"<div>");// line 12
		actionRunners.put(getOut().length(), new cn.bran.play.CacheablePlayActionRunner("", Application.class, "twoParams", "hello", 10) {
			@Override
			public void runPlayAction() throws cn.bran.play.JapidResult {
				Application.twoParams("hello", 10); //
			}
		});

// line 15
p("</div>\n" + 
"\n" + 
"<p>Let's invoke a tag which invokes an action</p>\n" + 
"\n");// line 15
_invokeInTag4.setOut(getOut()); _invokeInTag4.render();
// line 19
p("\n" + 
"<p>let's invoke an action that renders a template that contains another invoke: ");// line 19
		actionRunners.put(getOut().length(), new cn.bran.play.CacheablePlayActionRunner("", Application.class, "authorPanel2", post.getAuthor()) {
			@Override
			public void runPlayAction() throws cn.bran.play.JapidResult {
				Application.authorPanel2(post.getAuthor()); //
			}
		});

// line 21
p("</p>\n");// line 21

	}

}