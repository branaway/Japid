package japidviews.templates;
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
// NOTE: This file was generated from: japidviews/templates/log.html
// Change to this file will be lost next time the template file is compiled.
@cn.bran.play.NoEnhance
public class log extends cn.bran.japid.template.JapidTemplateBase{
	public static final String sourceTemplate = "japidviews/templates/log.html";
static private final String static_0 = "\n" + 
"log directives are used to print a line of information to the console. \n" + 
"It can take an argument of String\n" + 
"\n" + 
"";
static private final String static_1 = "\n" + 
"hello world!\n" + 
"\n" + 
"";
static private final String static_2 = ""
;
static private final String static_3 = "now with argument\n" + 
"";
static private final String static_4 = "\n" + 
"now with a message literal\n" + 
"";
	public log() {
		super(null);
	}
	public log(StringBuilder out) {
		super(out);
	}
	public cn.bran.japid.template.RenderResult render() {
		long t = -1;
		super.layout();
		return new cn.bran.japid.template.RenderResultPartial(this.headers, getOut(), t, actionRunners);
	}
	@Override protected void doLayout() {
p(static_0);// line 1
System.out.println("japidviews/templates/log.html(line 5): " + "");
p(static_1);// line 5
String a = "a";// line 9
p(static_2);// line 9
int i = 10;// line 10
p(static_3);// line 10
System.out.println("japidviews/templates/log.html(line 12): " + a + i);
p(static_4);// line 12
System.out.println("japidviews/templates/log.html(line 15): " + "a message ");

	}
}
