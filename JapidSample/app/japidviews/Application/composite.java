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
import play.data.validation.Validation;
import play.mvc.Scope.*;
import models.*;
import play.data.validation.Error;
import play.i18n.Lang;
import japidviews._tags.*;
import play.mvc.Http.*;
import controllers.*;
//
// NOTE: This file was generated from: japidviews/Application/composite.html
// Change to this file will be lost next time the template file is compiled.
//
@cn.bran.play.NoEnhance
public class composite extends lcomposite2
{
	public static final String sourceTemplate = "japidviews/Application/composite.html";
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


	public composite() {
		super(null);
	}
	public composite(StringBuilder out) {
		super(out);
	}
/* based on https://github.com/branaway/Japid/issues/12
 */
	public static final String[] argNames = new String[] {/* args of the template*/"post",  };
	public static final String[] argTypes = new String[] {/* arg types of the template*/"models.japidsample.Post",  };
	public static final Object[] argDefaults= new Object[] {null, };
	public static java.lang.reflect.Method renderMethod = getRenderMethod(japidviews.Application.composite.class);

	{
		setRenderMethod(renderMethod);
		setArgNames(argNames);
		setArgTypes(argTypes);
		setArgDefaults(argDefaults);
		setSourceTemplate(sourceTemplate);
	}
////// end of named args stuff

	private models.japidsample.Post post; // line 2
	public cn.bran.japid.template.RenderResult render(models.japidsample.Post post) {
		this.post = post;
		long __t = -1;
		try {super.layout();} catch (RuntimeException e) { super.handleException(e);} // line 2
		return new cn.bran.japid.template.RenderResultPartial(getHeaders(), getOut(), __t, actionRunners, sourceTemplate);
	}

	public static cn.bran.japid.template.RenderResult apply(models.japidsample.Post post) {
		return new composite().render(post);
	}

	@Override protected void doLayout() {
		beginDoLayout(sourceTemplate);
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
				Application.authorPanel(post.getAuthor()); // line 8
			}
		}); p("\n");// line 8
		p("</div>\n" + 
"<div>Another one in sub package: ");// line 8
				actionRunners.put(getOut().length(), new cn.bran.play.CacheablePlayActionRunner("", SubController.class, "foo", post.getAuthor().name) {
			@Override
			public void runPlayAction() throws cn.bran.play.JapidResult {
				SubController.foo(post.getAuthor().name); // line 9
			}
		}); p("\n");// line 9
		p("</div>\n" + 
"\n" + 
"<div>this one has full cache control</div>\n" + 
"<div>");// line 9
				actionRunners.put(getOut().length(), new cn.bran.play.CacheablePlayActionRunner("10s", Application.class, "authorPanel", post.getAuthor()) {
			@Override
			public void runPlayAction() throws cn.bran.play.JapidResult {
				Application.authorPanel(post.getAuthor()); // line 12
			}
		}); p("\n");// line 12
		p("</div>\n" + 
"\n" + 
"<div>This one invokes an action with two params. Note the twoPrams() result is cached since the action carries CacheFor annotation.</div>\n" + 
"<div>");// line 12
				actionRunners.put(getOut().length(), new cn.bran.play.CacheablePlayActionRunner("", Application.class, "twoParams", "hello", 10) {
			@Override
			public void runPlayAction() throws cn.bran.play.JapidResult {
				Application.twoParams("hello", 10); // line 15
			}
		}); p("\n");// line 15
		p("</div>\n" + 
"\n" + 
"<p>Let's invoke a tag which invokes an action</p>\n" + 
"\n");// line 15
		final invokeInTag _invokeInTag4 = new invokeInTag(getOut()); _invokeInTag4.setActionRunners(getActionRunners()).setOut(getOut()); _invokeInTag4.render(); // line 19// line 19
		p("\n" + 
"<p>let's invoke an action that renders a template that contains another invoke: ");// line 19
				actionRunners.put(getOut().length(), new cn.bran.play.CacheablePlayActionRunner("", Application.class, "authorPanel2", post.getAuthor()) {
			@Override
			public void runPlayAction() throws cn.bran.play.JapidResult {
				Application.authorPanel2(post.getAuthor()); // line 21
			}
		}); p("\n");// line 21
		p("</p>\n");// line 21
		
		endDoLayout(sourceTemplate);
	}

}