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
public class composite extends lcomposite{
	public static final String sourceTemplate = "japidviews/japid/SampleController/composite.html";
static private final String static_0 = ""
;
static private final String static_1 = ""
;
static private final String static_2 = ""
;
static private final String static_3 = "\n" + 
"<p>This is the comosite content header</p>\n" + 
"<div>"
;
static private final String static_4 = "</div>\n" + 
"\n" + 
"<div>this one has full cache control</div>\n" + 
"<div>"
;
static private final String static_5 = "</div>\n" + 
"\n" + 
"<div>this one has cache control using the default signature. Note the key usually must present if the action takes params</div>\n" + 
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
p(static_0);// line 1
p(static_1);// line 1
p(static_2);// line 2
p(static_3);// line 3
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
p(static_4);// line 6
		actionRunners.put(getOut().length(), new cn.bran.play.CacheablePlayActionRunner("10s", "SampleController.authorPanel", post.getAuthor()) {
			@Override
			public void runPlayAction() throws cn.bran.play.JapidResult {
				SampleController.authorPanel(post.getAuthor()); //
			}
		});

// line 9
p(static_5);// line 9
		actionRunners.put(getOut().length(), new cn.bran.play.CacheablePlayActionRunner("10s", "SampleController.authorPanel", "") {
			@Override
			public void runPlayAction() throws cn.bran.play.JapidResult {
				SampleController.authorPanel(post.getAuthor()); //
			}
		});

// line 12
p(static_6);// line 12
_invokeInTag3.setActionRunners(getActionRunners());
_invokeInTag3.render();
// line 15
p(static_7);// line 15

	}
	private invokeInTag _invokeInTag3 = new invokeInTag(getOut());
}
