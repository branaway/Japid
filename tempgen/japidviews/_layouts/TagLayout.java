package japidviews._layouts;
import java.util.*;
import java.io.*;
import cn.bran.japid.tags.Each;
import japidviews._layouts.*;
import japidviews._tags.*;
import static cn.bran.play.JapidPlayAdapter.*;
// NOTE: This file was generated from: japidviews/_layouts/TagLayout.html
// Change to this file will be lost next time the template file is compiled.
@cn.bran.play.NoEnhance
public abstract class TagLayout extends cn.bran.japid.template.JapidTemplateBase{
	public static final String sourceTemplate = "japidviews/_layouts/TagLayout.html";
	public static final String contentType = "text/html";
static private final String static_0 = "标签布局\n" + 
"<div>\n" + 
"";
static private final String static_1 = "\n" + 
"</div>\n" + 
"\n" + 
"\n" + 
"";
	public TagLayout() {
		super(null);
	}
	public TagLayout(StringBuilder out) {
		super(out);
	}
	@Override public void layout() {		p(static_0);// line 1
	doLayout();// line 3
p(static_1);// line 3
	}	protected abstract void doLayout();
}