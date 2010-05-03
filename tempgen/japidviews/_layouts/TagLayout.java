package japidviews._layouts;
import java.util.*;
import java.io.*;
import cn.bran.japid.tags.Each;
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
// NOTE: This file was generated from: japidviews/_layouts/TagLayout.html
// Change to this file will be lost next time the template file is compiled.
@cn.bran.play.NoEnhance
public abstract class TagLayout extends cn.bran.japid.template.JapidTemplateBase{
	public static final String sourceTemplate = "japidviews/_layouts/TagLayout.html";
static private final String static_0 = "标签布局\n" + 
"";
static private final String static_1 = "\n" + 
"";
static private final String static_2 = "\n" + 
"<div>\n" + 
"";
static private final String static_3 = "\n" + 
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
p(static_1);// line 2
_dummyTag0.setActionRunners(getActionRunners());
_dummyTag0.render("me");
// line 3
p(static_2);// line 3
	doLayout();// line 5
p(static_3);// line 5
	}	protected abstract void doLayout();
	private dummyTag _dummyTag0 = new dummyTag(getOut());
}