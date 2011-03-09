package japidviews.templates;
import java.util.*;
import java.io.*;
import cn.bran.japid.tags.Each;
import static play.templates.JavaExtensions.*;
import static cn.bran.play.JapidPlayAdapter.*;
import static play.data.validation.Validation.*;
import japidviews._layouts.*;
import play.i18n.Messages;
import static  japidviews._javatags.JapidWebUtil.*;
import play.data.validation.Validation;
import play.mvc.Scope.*;
import models.*;
import play.data.validation.Error;
import play.i18n.Lang;
import japidviews._tags.*;
import controllers.*;
import play.mvc.Http.*;
import japidviews._javatags.*;
//
// NOTE: This file was generated from: japidviews/templates/JavaScript.html
// Change to this file will be lost next time the template file is compiled.
//
@cn.bran.play.NoEnhance
public class JavaScript extends cn.bran.japid.template.JapidTemplateBase
{	public static final String sourceTemplate = "japidviews/templates/JavaScript.html";
{
	headers.put("Content-Type", "text/html; charset=utf-8");
}

// - add implicit fields with Play

	final Request request = Request.current(); 
	final Response response = Response.current(); 
	final Session session = Session.current();
	final RenderArgs renderArgs = RenderArgs.current();
	final Params params = Params.current();
	final Validation validation = Validation.current();
	final cn.bran.play.FieldErrors errors = new cn.bran.play.FieldErrors(validation);
	final play.Play _play = new play.Play(); 

// - end of implicit fields with Play 


	public JavaScript() {
		super(null);
	}
	public JavaScript(StringBuilder out) {
		super(out);
	}
	private String hello;
	public cn.bran.japid.template.RenderResult render(String hello) {
		this.hello = hello;
		long t = -1;
		super.layout();
		return new cn.bran.japid.template.RenderResult(this.headers, getOut(), t);
	}
	@Override protected void doLayout() {
//------
;// line 1
p("\n" + 
"<!DOCTYPE html PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\" \"http://www.w3.org/TR/html4/loose.dtd\">\n" + 
"<html>\n" + 
"<head>\n" + 
"<meta http-equiv=\"Content-Type\" content=\"text/html; charset=GB18030\">\n" + 
"<title>Insert title here</title>\n" + 
"</head>\n" + 
"<body>\n" + 
"<script type=\"text/javascript\" charset=\"utf-8\">\n" + 
"    $(function() {         \n" + 
"        // Expose the form \n" + 
"        $('form').click(function() { \n" + 
"            $('form').expose({api: true}).load('\\n\\t'); \n" + 
"        }); \n" + 
"        \n" + 
"        // If there is an error, focus to form\n" + 
"        if($('form .error').size()) {\n" + 
"            $('form').expose({api: true, loadSpeed: 0}).load(); \n" + 
"            $('form input').get(0).focus();\n" + 
"        }\n" + 
"    });\n" + 
"</script>\n");// line 1
p(hello);// line 24
p("\n" + 
"</body>\n" + 
"</html>");// line 24

	}

}