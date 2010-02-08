package japidviews._layouts;
import java.util.*;
import java.io.*;
import cn.bran.japid.tags.Each;
import japidviews._layouts.*;
import japidviews._tags.*;
import static cn.bran.play.PlayTemplateVarsAdapter.*;
// NOTE: This file was generated from: japidviews/_layouts/SetLayout.html
// Change to this file will be lost next time the template file is compiled.
@cn.bran.play.NoEnhance
public abstract class SetLayout extends cn.bran.japid.template.JapidTemplateBase{
	public static final String sourceTemplate = "japidviews/_layouts/SetLayout.html";
	public static final String contentType = "text/html";
static String static_0 = ""
;
static String static_1 = "\n" + 
"";
static String static_2 = "\n" + 
"";
	public SetLayout(StringBuilder out) {
		super(out);
	}
	@Override public void layout() {		p(static_0);// line 1
	title();// line 1
p(static_1);// line 1
	footer();// line 2
p(static_2);// line 2
	}	protected abstract void title();
	protected abstract void footer();
	protected abstract void doLayout();
}