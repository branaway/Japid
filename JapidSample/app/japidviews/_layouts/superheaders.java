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
// NOTE: This file was generated from: japidviews/_layouts/superheaders.html
// Change to this file will be lost next time the template file is compiled.
@cn.bran.play.NoEnhance
public abstract class superheaders extends cn.bran.japid.template.JapidTemplateBase{
	public static final String sourceTemplate = "japidviews/_layouts/superheaders.html";
{
	headers.put("Content-Type", "text/html; charset=utf-8");
	headers.put("Cache-Control", "max-age=300");
}
static private final String static_0 = ""
;
static private final String static_1 = ""
;
static private final String static_2 = "\n" + 
"";
static private final String static_3 = ""
;
	public superheaders() {
		super(null);
	}
	public superheaders(StringBuilder out) {
		super(out);
	}
	@Override public void layout() {		p(static_0);// line 1
p(static_1);// line 1
p(static_2);// line 2
	doLayout();// line 4
p(static_3);// line 4
	}	protected abstract void doLayout();
}