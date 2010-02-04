package templates;
import java.util.*;
import java.io.*;
import tag.*;
import bran.japid.tags.Each;
import bran.Post;
import layout.*;
import static bran.WebUtils.*;
import static japidplay.PlayTemplateVarsAdapter.*;
// This file was generated from: templates/JavaScript.html
// Change to this file will be lost next time the template file is compiled.
@bran.NoEnhance
public class JavaScript extends bran.japid.BranTemplateBase{
	public static final String sourceTemplate = "templates/JavaScript.html";
static byte[] static_0 = getBytes(""
);
static byte[] static_1 = getBytes("\n" + 
"\n" + 
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
"</script>\n" + 
"");
static byte[] static_2 = getBytes("\n" + 
"</body>\n" + 
"</html>"
);
	public JavaScript(OutputStream out) {
		super(out);
	}
	String hello;
	public void render(String hello) {
		this.hello = hello;
		super.layout();
	}
	@Override protected void doLayout() {
p(static_0);// line 1
p(static_1);// line 1
p(hello);// line 24
p(static_2);// line 24

	}
}
