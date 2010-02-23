package japidviews.japid.SampleController;
import java.util.*;
import java.io.*;
import cn.bran.japid.tags.Each;
import cn.bran.japid.template.ActionRunner;
import japidviews._layouts.*;
import japidviews._tags.*;
import controllers.japid.SampleController;
import static cn.bran.play.JapidPlayAdapter.*;
// NOTE: This file was generated from: japidviews/japid/SampleController/composite.html
// Change to this file will be lost next time the template file is compiled.
@cn.bran.play.NoEnhance
public class composite extends cn.bran.japid.template.JapidTemplateBase{
	public static final String sourceTemplate = "japidviews/japid/SampleController/composite.html";
	public static final String contentType = "text/html";
	LinkedHashMap<Integer, cn.bran.japid.template.ActionRunner> actionRunners = new LinkedHashMap<Integer, cn.bran.japid.template.ActionRunner>();
static private final String static_0 = ""
;
static private final String static_1 = ""
;
static private final String static_2 = "\n" + 
"<p>This is the comosite content header</p>\n" + 
"<div>\n" + 
"	"
;
static private final String static_3 = "\n" + 
"</div>\n" + 
"<div>this one has full cache control</div>\n" + 
"<div>\n" + 
"	"
;
static private final String static_4 = " \n" + 
"</div>\n" + 
"<div>this one has cache control using the default signature. Note the key usually must present if the action takes params</div>\n" + 
"<div>\n" + 
"	"
;
static private final String static_5 = " \n" + 
"</div>\n" + 
"<p>This is the comosite content footer</p>"
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
		return new cn.bran.japid.template.RenderResultPartial(this.contentType, getOut(), t, actionRunners);
	}
	@Override protected void doLayout() {
p(static_0);// line 1
p(static_1);// line 1
p(static_2);// line 2
		actionRunners.put(getOut().length(), new cn.bran.japid.template.ActionRunner() {
			@Override
			public cn.bran.japid.template.RenderResult run() {
				try {
					play.classloading.enhancers.ControllersEnhancer.ControllerInstrumentation.initActionCall();
					SampleController.authorPanel(post.getAuthor());
				} catch (cn.bran.play.JapidResult jr) {
					return jr.getRenderResult();
				}
				throw new RuntimeException("No render result from running: SampleController.authorPanel(post.getAuthor())");
			}
		});
// line 6
p(static_3);// line 6
		actionRunners.put(getOut().length(), new cn.bran.play.CacheablePlayActionRunner("10s", "SampleController.authorPanel", post.getAuthor()) {
			@Override
			public void runPlayAction() throws cn.bran.play.JapidResult {
				SampleController.authorPanel(post.getAuthor()); //
			}
		});

// line 10
p(static_4);// line 10
		actionRunners.put(getOut().length(), new cn.bran.play.CacheablePlayActionRunner("10s", "SampleController.authorPanel", "") {
			@Override
			public void runPlayAction() throws cn.bran.play.JapidResult {
				SampleController.authorPanel(post.getAuthor()); //
			}
		});

// line 14
p(static_5);// line 14

	}
}
