package japidviews._layouts;
import java.util.*;
import java.io.*;
import cn.bran.japid.tags.Each;
import japidviews._layouts.*;
import japidviews._tags.*;
import static cn.bran.play.JapidPlayAdapter.*;
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