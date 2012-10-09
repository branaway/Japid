package japidviews.Application;
import java.util.*;
import java.io.*;
import cn.bran.japid.tags.Each;
import static play.templates.JavaExtensions.*;
import static cn.bran.play.JapidPlayAdapter.*;
import static play.data.validation.Validation.*;
import japidviews._layouts.*;
import play.i18n.Messages;
import play.data.validation.Validation;
import play.mvc.Scope.*;
import models.*;
import play.data.validation.Error;
import play.i18n.Lang;
import play.mvc.Http.*;
import controllers.*;
//
// NOTE: This file was generated from: japidviews/Application/form.html
// Change to this file will be lost next time the template file is compiled.
//
public class form extends main
{
	public static final String sourceTemplate = "japidviews/Application/form.html";
	{
		putHeader("Content-Type", "text/html; charset=utf-8");
		setContentType("text/html; charset=utf-8");
	}

// - add implicit fields with Play

	final play.mvc.Http.Request request = play.mvc.Http.Request.current(); 
	final play.mvc.Http.Response response = play.mvc.Http.Response.current(); 
	final play.mvc.Scope.Session session = play.mvc.Scope.Session.current();
	final play.mvc.Scope.RenderArgs renderArgs = play.mvc.Scope.RenderArgs.current();
	final play.mvc.Scope.Params params = play.mvc.Scope.Params.current();
	final play.data.validation.Validation validation = play.data.validation.Validation.current();
	final cn.bran.play.FieldErrors errors = new cn.bran.play.FieldErrors(validation);
	final play.Play _play = new play.Play(); 

// - end of implicit fields with Play 


	public form() {
		super(null);
	}
	public form(StringBuilder out) {
		super(out);
	}
/* based on https://github.com/branaway/Japid/issues/12
 */
	public static final String[] argNames = new String[] {/* args of the template*/"contact",  };
	public static final String[] argTypes = new String[] {/* arg types of the template*/"Contact",  };
	public static final Object[] argDefaults= new Object[] {null, };
	public static java.lang.reflect.Method renderMethod = getRenderMethod(japidviews.Application.form.class);

	{
		setRenderMethod(renderMethod);
		setArgNames(argNames);
		setArgTypes(argTypes);
		setArgDefaults(argDefaults);
		setSourceTemplate(sourceTemplate);
	}
////// end of named args stuff

	private Contact contact; // line 1
	public cn.bran.japid.template.RenderResult render(Contact contact) {
		this.contact = contact;
		long t = -1;
		try {super.layout();} catch (RuntimeException e) { super.handleException(e);} // line 1
		return new cn.bran.japid.template.RenderResultPartial(getHeaders(), getOut(), t, actionRunners, sourceTemplate);
	}
	@Override protected void doLayout() {
		beginDoLayout(sourceTemplate);
//------
;// line 1
p("<form action=\"");// line 4
		p(lookup("save", new Object[]{}));// line 6
		p("\" method=\"POST\">\n" + 
"    ");// line 6
		try { p(authenticityToken()); } catch (NullPointerException npe) {}// line 7
		p("    <input type=\"hidden\" name=\"contact.id\" value=\"");// line 7
		try { p(contact.id); } catch (NullPointerException npe) {}// line 8
		p("\">\n" + 
"    \n" + 
"    <p class=\"field\">\n" + 
"        <label for=\"name\">Name:</label>\n" + 
"        <input type=\"text\" id=\"name\" name=\"contact.name\" value=\"");// line 8
		try { p(contact.name); } catch (NullPointerException npe) {}// line 12
		p("\">\n" + 
"        <span class=\"error\">");// line 12
		try { p(error("contact.name")); } catch (NullPointerException npe) {}// line 13
		p("</span>\n" + 
"    </p>\n" + 
"\n" + 
"    <p class=\"field\">\n" + 
"        <label for=\"firstname\">First name:</label>\n" + 
"        <input type=\"text\" id=\"firstname\" name=\"contact.firstname\" value=\"");// line 13
		try { p(contact.firstname); } catch (NullPointerException npe) {}// line 18
		p("\">\n" + 
"        <span class=\"error\">");// line 18
		try { p(error("contact.firstname")); } catch (NullPointerException npe) {}// line 19
		p("</span>\n" + 
"    </p>\n" + 
"\n" + 
"    <p class=\"field\">\n" + 
"        <label for=\"birthdate\">Birth date:</label>\n" + 
"        <input type=\"text\" id=\"birthdate\" name=\"contact.birthdate\" value=\"");// line 19
		try { p(format(contact.birthdate, "yyyy-MM-dd")); } catch (NullPointerException npe) {}// line 24
		p("\">\n" + 
"        <span class=\"error\">");// line 24
		try { p(error("contact.birthdate")); } catch (NullPointerException npe) {}// line 25
		p("</span>\n" + 
"    </p>\n" + 
"\n" + 
"    <p class=\"field\">\n" + 
"        <label for=\"email\">Email:</label>\n" + 
"        <input type=\"text\" id=\"email\" name=\"contact.email\" value=\"");// line 25
		try { p(contact.email); } catch (NullPointerException npe) {}// line 30
		p("\">\n" + 
"        <span class=\"error\">");// line 30
		try { p(error("contact.email")); } catch (NullPointerException npe) {}// line 31
		p("</span>\n" + 
"    </p>\n" + 
"\n" + 
"    <p class=\"buttons\">\n" + 
"        <a href=\"");// line 31
		p(lookup("list", new Object[]{}));// line 35
		p("\">Cancel</a> or <input type=\"submit\" value=\"Save this contact\" id=\"saveContact\">\n" + 
"    </p>\n" + 
"    \n" + 
"    <script type=\"text/javascript\" charset=\"utf-8\">\n" + 
"        $(\"#birthdate\").datepicker({dateFormat:'yy-mm-dd', showAnim:'fadeIn'})\n" + 
"    </script>\n" + 
"</form>\n");// line 35
		
		endDoLayout(sourceTemplate);
	}

	@Override protected void title() {
		p("Form");;
	}
}