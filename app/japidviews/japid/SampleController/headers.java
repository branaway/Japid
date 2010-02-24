package japidviews.japid.SampleController;
import java.util.*;
import java.io.*;
import cn.bran.japid.tags.Each;
import japidviews._layouts.*;
import japidviews._tags.*;
import static cn.bran.play.JapidPlayAdapter.*;
// NOTE: This file was generated from: japidviews/japid/SampleController/headers.html
// Change to this file will be lost next time the template file is compiled.
@cn.bran.play.NoEnhance
public class headers extends superheaders{
	public static final String sourceTemplate = "japidviews/japid/SampleController/headers.html";
{
	headers.put("Date", "Tue, 23 Feb 2010 13:42:34 GMT");
	headers.put("Expires", "Tue, 23 Feb 2010 13:47:34 GMT");
	headers.put("Last-Modified", "Tue, 23 Feb 2010 13:40:01 GMT");
	headers.put("Server", "nginx/0.8.26");
	headers.put("Cache-Control", "max-age=600");
}
static private final String static_0 = ""
;
static private final String static_1 = ""
;
static private final String static_2 = ""
;
static private final String static_3 = ""
;
static private final String static_4 = ""
;
static private final String static_5 = ""
;
static private final String static_6 = "\n" + 
"<p>\n" + 
"\"setHeader\" is for adding a http response header to the response. One cannot use \"\" to surround the value part\n" + 
"</p>\n" + 
"\n" + 
"<p>\n" + 
"The header name and the value are separated by white spaces (space or tab)\n" + 
"</p>\n" + 
"\n" + 
"<p>\n" + 
"Notes: If a response includes both an Expires header and a max-age directive, the max-age directive overrides the Expires header, even if the Expires header is more restrictive.\n" + 
"</p>\n" + 
"\n" + 
"\n" + 
"";
	public headers() {
		super(null);
	}
	public headers(StringBuilder out) {
		super(out);
	}
	public cn.bran.japid.template.RenderResult render() {
		long t = -1;
		super.layout();
		return new cn.bran.japid.template.RenderResult(this.headers, getOut(), t);
	}
	@Override protected void doLayout() {
p(static_0);// line 1
p(static_1);// line 1
p(static_2);// line 2
p(static_3);// line 3
p(static_4);// line 4
p(static_5);// line 5
p(static_6);// line 6

	}
}
