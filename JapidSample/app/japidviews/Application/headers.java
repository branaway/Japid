package japidviews.Application;
import java.util.*;
import java.io.*;
import cn.bran.japid.tags.Each;
import static play.templates.JavaExtensions.*;
import static cn.bran.play.JapidPlayAdapter.*;
import static play.data.validation.Validation.*;
import static cn.bran.play.WebUtils.*;
import japidviews._layouts.*;
import static  japidviews._javatags.JapidWebUtil.*;
import play.data.validation.Validation;
import play.mvc.Scope.*;
import models.*;
import play.data.validation.Error;
import japidviews._tags.*;
import controllers.*;
import play.mvc.Http.*;
import japidviews._javatags.*;
//
// NOTE: This file was generated from: japidviews/Application/headers.html
// Change to this file will be lost next time the template file is compiled.
//
@cn.bran.play.NoEnhance
public class headers extends superheaders
{	public static final String sourceTemplate = "japidviews/Application/headers.html";
{
	headers.put("Date", "Tue, 23 Feb 2010 13:42:34 GMT");
	headers.put("Expires", "Tue, 23 Feb 2010 13:47:34 GMT");
	headers.put("Last-Modified", "Tue, 23 Feb 2010 13:40:01 GMT");
	headers.put("Content-Type", "text/html; charset=utf-8");
	headers.put("Server", "nginx/0.8.26");
	headers.put("Cache-Control", "max-age=600");
}
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

// - add implicit variables 

		final Request request = Request.current(); assert request != null;

		final Response response = Response.current(); assert response != null;

		final Flash flash = Flash.current();assert flash != null;

		final Session session = Session.current();assert session != null;

		final RenderArgs renderArgs = RenderArgs.current(); assert renderArgs != null;

		final Params params = Params.current();assert params != null;

		final Validation validation = Validation.current();assert validation!= null;

		final cn.bran.play.FieldErrors errors = new cn.bran.play.FieldErrors(validation);assert errors != null;

		final play.Play _play = new play.Play(); assert _play != null;

// - end of implicit variables 


//------
;// line 1
p("\n" + 
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
"\n");// line 6

	}

}