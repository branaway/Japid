package japidviews.Application;
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
// NOTE: This file was generated from: japidviews/Application/reverseLookup0.html
// Change to this file will be lost next time the template file is compiled.
@cn.bran.play.NoEnhance
public class reverseLookup0 extends cn.bran.japid.template.JapidTemplateBase{
	public static final String sourceTemplate = "japidviews/Application/reverseLookup0.html";
static private final String static_0 = "<!DOCTYPE html PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\" \"http://www.w3.org/TR/html4/loose.dtd\">\n" + 
"<html>\n" + 
"<head>\n" + 
"<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\n" + 
"<title>Insert title here</title>\n" + 
"</head>\n" + 
"<body>\n" + 
"<h1>actions</h1>\n" + 
"<p><a href='"
;
static private final String static_1 = "'>Action notation </a></p>\n" + 
"<p><a href='/japid.SampleController/reverseLookup1?agrs=order0&args=order2'>action reverse lookup cannot handle arrays or collections...</a></p>\n" + 
"</body>\n" + 
"</html>"
;
	public reverseLookup0() {
		super(null);
	}
	public reverseLookup0(StringBuilder out) {
		super(out);
	}
	public cn.bran.japid.template.RenderResult render() {
		long t = -1;
		super.layout();
		return new cn.bran.japid.template.RenderResultPartial(this.headers, getOut(), t, actionRunners);
	}
	@Override protected void doLayout() {
p(static_0);// line 1
p(lookup("japid.SampleController.reverseLookup0", new Object[]{}));// line 9
p(static_1);// line 9

	}
}
