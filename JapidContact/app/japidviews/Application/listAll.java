package japidviews.Application;
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
// NOTE: This file was generated from: japidviews/Application/listAll.html
// Change to this file will be lost next time the template file is compiled.
//
@cn.bran.play.NoEnhance
public class listAll extends main
{	public static final String sourceTemplate = "japidviews/Application/listAll.html";
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


	public listAll() {
		super(null);
	}
	public listAll(StringBuilder out) {
		super(out);
	}
	private List<Contact> contacts;
	public cn.bran.japid.template.RenderResult render(List<Contact> contacts) {
		this.contacts = contacts;
		long t = -1;
		super.layout();
		return new cn.bran.japid.template.RenderResult(this.headers, getOut(), t);
	}
	@Override protected void doLayout() {
//------
;// line 1
p("\n" + 
"<table>\n" + 
"    <thead>\n" + 
"        <tr>\n" + 
"            <th class=\"name\">Name</th>\n" + 
"            <th class=\"firstname\">First name</th>\n" + 
"            <th class=\"birthdate\">Birth date</th>\n" + 
"            <th class=\"email\">Email</th>\n" + 
"            <th class=\"edit\"></th>\n" + 
"        </tr>\n" + 
"    </thead>\n" + 
"    <tbody>\n" + 
"        ");// line 3
for (Contact contact : contacts) {// line 16
p("	        <tr class=\"contact\" contactId=\"");// line 16
p(contact.id);// line 17
p("\" draggable=\"true\">\n" + 
"	            <td id=\"name-");// line 17
p(contact.id);// line 18
p("\">");// line 18
p(contact.name);// line 18
p("</td>\n" + 
"	            <td id=\"firstname-");// line 18
p(contact.id);// line 19
p("\">");// line 19
p(contact.firstname);// line 19
p("</td>\n" + 
"	            <td id=\"birthdate-");// line 19
p(contact.id);// line 20
p("\">");// line 20
try { Object o = format(contact.birthdate, "yyyy-MM-dd") ; if (o.toString().length() ==0) { ; } else { p(o); } } catch (NullPointerException npe) { ; }// line 20
p("</td>\n" + 
"	            <td id=\"email-");// line 20
p(contact.id);// line 21
p("\">");// line 21
p(contact.email);// line 21
p("</td>\n" + 
"	            <td><a href=\"");// line 21
p(lookup("form", contact.id));// line 22
p("\">&gt;</a></td>\n" + 
"	        </tr>\n" + 
"        ");// line 22
}// line 24
p("        <tr>\n" + 
"            <form action=\"");// line 24
p(lookup("save", new Object[]{}));// line 26
p("\" method=\"POST\">\n" + 
"            	");// line 26
p(authenticityToken());// line 27
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
"                url: '");// line 27
p(lookup("save", new Object[]{}));// line 49
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
"    ");// line 49
p("\n" + 
"    // Drag & Drop\n" + 
"    var dragIcon = document.createElement('img')\n" + 
"    dragIcon.src = '");// line 62
p(lookupStatic("public/images/avatar.png"));// line 65
p("'  \n" + 
"    var action = ");// line 65
p(jsAction("form", ":id"));// line 66
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
"</script>");// line 66

	}

	@Override protected void title() {
		p("List of all");;
	}
}