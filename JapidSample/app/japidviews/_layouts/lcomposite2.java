package japidviews._layouts;
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
import static play.templates.JavaExtensions.*;
import static cn.bran.play.JapidPlayAdapter.*;
import static play.data.validation.Validation.*;
import static cn.bran.play.WebUtils.*;
// NOTE: This file was generated from: japidviews/_layouts/lcomposite2.html
// Change to this file will be lost next time the template file is compiled.
@cn.bran.play.NoEnhance
public abstract class lcomposite2 extends cn.bran.japid.template.JapidTemplateBase{
	public static final String sourceTemplate = "japidviews/_layouts/lcomposite2.html";
static private final String static_0 = "<p>beginning: lcomposite</p>\n" + 
"";
static private final String static_1 = "\n" + 
"\n" + 
"";
static private final String static_2 = "\n" + 
"\n" + 
"<p>back to layout</p>\n" + 
"";
static private final String static_3 = "\n" + 
"<p>back to layout again</p>\n" + 
"\n" + 
"<p>end of lcomposite</p>\n" + 
"";
	public lcomposite2() {
		super(null);
	}
	public lcomposite2(StringBuilder out) {
		super(out);
	}
	@Override public void layout() {		p(static_0);// line 1
		actionRunners.put(getOut().length(), new cn.bran.japid.template.ActionRunner() {
			@Override
			public cn.bran.japid.template.RenderResult run() {
				try {
					play.classloading.enhancers.ControllersEnhancer.ControllerInstrumentation.initActionCall();
					controllers.Application.foo();
				} catch (cn.bran.play.JapidResult jr) {
					return jr.getRenderResult();
				}
				throw new RuntimeException("No render result from running: controllers.Application.foo()");
			}
		});
// line 2
p(static_1);// line 2
	doLayout();// line 4
p(static_2);// line 4
		actionRunners.put(getOut().length(), new cn.bran.japid.template.ActionRunner() {
			@Override
			public cn.bran.japid.template.RenderResult run() {
				try {
					play.classloading.enhancers.ControllersEnhancer.ControllerInstrumentation.initActionCall();
					controllers.Application.foo();
				} catch (cn.bran.play.JapidResult jr) {
					return jr.getRenderResult();
				}
				throw new RuntimeException("No render result from running: controllers.Application.foo()");
			}
		});
// line 7
p(static_3);// line 7
	}	protected abstract void doLayout();
}