package japidviews.templates;
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
import japidviews._javatags.*;
import models.japidsample.*;
import static play.templates.JavaExtensions.*;
import static cn.bran.play.JapidPlayAdapter.*;
import static play.data.validation.Validation.*;
import static cn.bran.play.WebUtils.*;
// NOTE: This file was generated from: japidviews/templates/invokeInLoop.html
// Change to this file will be lost next time the template file is compiled.
@cn.bran.play.NoEnhance
public class invokeInLoop extends cn.bran.japid.template.JapidTemplateBase{
	public static final String sourceTemplate = "japidviews/templates/invokeInLoop.html";
static private final String static_0 = ""
;
static private final String static_1 = ""
;
static private final String static_2 = "\n" + 
"";
static private final String static_3 = "\n" + 
"\n" + 
"";
static private final String static_4 = "	"
;
static private final String static_5 = "	"
;
static private final String static_6 = "\n" + 
"";
static private final String static_7 = "<p/>\n" + 
"";
static private final String static_8 = "\n" + 
"\n" + 
"";
static private final String static_9 = "	"
;
static private final String static_10 = "\n" + 
"";
	public invokeInLoop() {
		super(null);
	}
	public invokeInLoop(StringBuilder out) {
		super(out);
	}
	List<Post> posts;
	public cn.bran.japid.template.RenderResult render(List<Post> posts) {
		this.posts = posts;
		long t = -1;
		super.layout();
		return new cn.bran.japid.template.RenderResultPartial(this.headers, getOut(), t, actionRunners);
	}
	@Override protected void doLayout() {
p(static_0);// line 1
p(static_1);// line 1
p(static_2);// line 2
p(static_3);// line 7
for (int i = 0; i < 3;i++) {// line 9
p(static_4);// line 9
final int j = i;// line 10
p(static_5);// line 10
		actionRunners.put(getOut().length(), new cn.bran.japid.template.ActionRunner() {
			@Override
			public cn.bran.japid.template.RenderResult run() {
				try {
					play.classloading.enhancers.ControllersEnhancer.ControllerInstrumentation.initActionCall();
					Application.echo(j);
				} catch (cn.bran.play.JapidResult jr) {
					return jr.getRenderResult();
				}
				throw new RuntimeException("No render result from running: Application.echo(j)");
			}
		});
// line 11
p(static_6);// line 11
}// line 12
p(static_7);// line 12
p(static_8);// line 14
for (final Post p : posts) {// line 16
p(static_9);// line 16
		actionRunners.put(getOut().length(), new cn.bran.japid.template.ActionRunner() {
			@Override
			public cn.bran.japid.template.RenderResult run() {
				try {
					play.classloading.enhancers.ControllersEnhancer.ControllerInstrumentation.initActionCall();
					Application.echoPost(p);
				} catch (cn.bran.play.JapidResult jr) {
					return jr.getRenderResult();
				}
				throw new RuntimeException("No render result from running: Application.echoPost(p)");
			}
		});
// line 17
p(static_10);// line 17
}// line 18

	}
}
