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
// NOTE: This file was generated from: japidviews/Application/listAll.html
// Change to this file will be lost next time the template file is compiled.
//
public class listAll extends main
{
	public static final String sourceTemplate = "japidviews/Application/listAll.html";
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


	public listAll() {
		super(null);
	}
	public listAll(StringBuilder out) {
		super(out);
	}
/* based on https://github.com/branaway/Japid/issues/12
 */
	public static final String[] argNames = new String[] {/* args of the template*/"contacts",  };
	public static final String[] argTypes = new String[] {/* arg types of the template*/"List<Contact>",  };
	public static final Object[] argDefaults= new Object[] {null, };
	public static java.lang.reflect.Method renderMethod = getRenderMethod(japidviews.Application.listAll.class);

	{
		setRenderMethod(renderMethod);
		setArgNames(argNames);
		setArgTypes(argTypes);
		setArgDefaults(argDefaults);
		setSourceTemplate(sourceTemplate);
	}
////// end of named args stuff

	private List<Contact> contacts; // line 1
	public cn.bran.japid.template.RenderResult render(List<Contact> contacts) {
		this.contacts = contacts;
		long t = -1;
		try {super.layout();} catch (RuntimeException e) { super.handleException(e);} // line 1
		return new cn.bran.japid.template.RenderResultPartial(getHeaders(), getOut(), t, actionRunners, sourceTemplate);
	}
	@Override protected void doLayout() {
		beginDoLayout(sourceTemplate);
//------
;// line 1
		p("\n");// line 1
p("\n" + 
"<table>\n" + 
"    <thead>\n" + 
"        <tr>\n" + 
"            <th class=\"name\">The Name</th>\n" + 
"            <th class=\"firstname\">First name</th>\n" + 
"            <th class=\"birthdate\">Birth date</th>\n" + 
"            <th class=\"email\">Email</th>\n" + 
"            <th class=\"edit\"></th>\n" + 
"        </tr>\n" + 
"    </thead>\n" + 
"    <tbody>\n");// line 3
		p("\n" + 
"        ");// line 17
		for (Contact contact : contacts) {// line 18
		p("	        <tr class=\"contact\" contactId=\"");// line 18
		p(contact.id);// line 19
		p("\" draggable=\"true\">\n" + 
"	            <td id=\"name-");// line 19
		p(contact.id);// line 20
		p("\">");// line 20
		p(contact.name);// line 20
		p("</td>\n" + 
"	            <td id=\"firstname-");// line 20
		p(contact.id);// line 21
		p("\">");// line 21
		p(contact.firstname);// line 21
		p("</td>\n" + 
"	            <td id=\"birthdate-");// line 21
		p(contact.id);// line 22
		p("\">");// line 22
		try { Object o = format(contact.birthdate, "yyyy-MM-dd") ; if (o.toString().length() ==0) { ; } else { p(o); } } catch (NullPointerException npe) { ; }// line 22
		p("</td>\n" + 
"	            <td id=\"email-");// line 22
		p(contact.id);// line 23
		p("\">");// line 23
		p(contact.email);// line 23
		p("</td>\n" + 
"	            <td><a href=\"");// line 23
		p(lookup("form", contact.id));// line 24
		p("\">&gt;</a></td>\n" + 
"	        </tr>\n" + 
"        ");// line 24
		}// line 26
		p("        <tr>\n" + 
"            <form action=\"");// line 26
		p(lookup("save", new Object[]{}));// line 28
		p("\" method=\"POST\">\n" + 
"            	");// line 28
		p(authenticityToken());// line 29
		p("\n" + 
"	            <td><input type=\"text\" name=\"contact.name\"></td>\n" + 
"	            <td><input type=\"text\" name=\"contact.firstname\"></td>\n" + 
"	            <td><input type=\"text\" name=\"contact.birthdate\"></td>\n" + 
"	            <td><input type=\"text\" name=\"contact.email\"></td>\n" + 
"	            <td><input type=\"submit\" value=\"+\"></td>\n" + 
"            </form>\n" + 
"        </tr>\n" + 
"    </tbody>\n" + 
"</table>\n" + 
"\n" + 
"<script type=\"text/javascript\" charset=\"utf-8\">\n" + 
"    // In place edition\n" + 
"    $(\".contact td\").editInPlace({\n" + 
"        bg_over: 'transparent',\n" + 
"        callback: function(el, n, o) {\n" + 
"            var m = /([a-z]+)-(\\d+)/.exec(el), data = {}\n" + 
"            data['contact.id'] = m[2]\n" + 
"            data['contact.' + m[1]] = n\n" + 
"            \n" + 
"            // Save result\n" + 
"            $.ajax({\n" + 
"                url: '");// line 29
		p(lookup("save", new Object[]{}));// line 51
		p("',\n" + 
"                type: 'POST',\n" + 
"                data: data,\n" + 
"                success: function() {$('#' + el).html(n)},\n" + 
"                error: function() {$('#' + el).html(o)}\n" + 
"            })\n" + 
"            \n" + 
"            return true\n" + 
"        }\n" + 
"    })\n" + 
"\n" + 
"    	\n" + 
"    ");// line 51
		p("\n" + 
"    // Drag & Drop\n" + 
"    var dragIcon = document.createElement('img')\n" + 
"    dragIcon.src = '");// line 64
		p(lookupStatic("public/images/avatar.png"));// line 67
		p("'  \n" + 
"    var action = ");// line 67
		p(jsAction("form", ":id"));// line 68
		p("\n" + 
"    var cancel = function cancel(e) {e.preventDefault()}\n" + 
"    \n" + 
"    $('#new')\n" + 
"        .bind('dragover', cancel)\n" + 
"        .bind('dragenter', cancel)\n" + 
"        .bind('drop', function(e) {\n" + 
"            document.location = action({id: e.originalEvent.dataTransfer.getData('contactId')})            \n" + 
"        })\n" + 
"      \n" + 
"    $('[draggable]').bind('dragstart', function(e) {\n" + 
"        e.originalEvent.dataTransfer.setData('contactId', $(this).attr('contactId'));\n" + 
"        e.originalEvent.dataTransfer.setDragImage(dragIcon, 0, -10);\n" + 
"    })\n" + 
"    \n" + 
"</script>\n");// line 68
		
		endDoLayout(sourceTemplate);
	}

	@Override protected void title() {
		p("List of all");;
	}
}