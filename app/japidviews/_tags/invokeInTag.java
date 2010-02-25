package japidviews._tags;
import java.util.*;
import java.io.*;
import cn.bran.japid.tags.Each;
import cn.bran.japid.template.ActionRunner;
import japidviews._layouts.*;
import japidviews._tags.*;
import static cn.bran.play.JapidPlayAdapter.*;
// NOTE: This file was generated from: japidviews/_tags/invokeInTag.html
// Change to this file will be lost next time the template file is compiled.
@cn.bran.play.NoEnhance
public class invokeInTag extends cn.bran.japid.template.JapidTemplateBase{
	public static final String sourceTemplate = "japidviews/_tags/invokeInTag.html";
static private final String static_0 = "<p>beginning: invocation in tag</p>\n" + 
"";
static private final String static_1 = "\n" + 
"<p>end of invoke in tag</p>\n" + 
"";
	public invokeInTag() {
		super(null);
	}
	public invokeInTag(StringBuilder out) {
		super(out);
	}
	public cn.bran.japid.template.RenderResult render() {
		long t = -1;
		super.layout();
		return new cn.bran.japid.template.RenderResultPartial(this.headers, getOut(), t, actionRunners);
	}
	@Override protected void doLayout() {
p(static_0);// line 1
		actionRunners.put(getOut().length(), new cn.bran.japid.template.ActionRunner() {
			@Override
			public cn.bran.japid.template.RenderResult run() {
				try {
					play.classloading.enhancers.ControllersEnhancer.ControllerInstrumentation.initActionCall();
					controllers.japid.SampleController.foo();
				} catch (cn.bran.play.JapidResult jr) {
					return jr.getRenderResult();
				}
				throw new RuntimeException("No render result from running: controllers.japid.SampleController.foo()");
			}
		});
// line 2
p(static_1);// line 2

	}
}
