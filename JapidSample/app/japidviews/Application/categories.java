//version: 0.9.35
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
import japidviews._tags.*;
import play.mvc.Http.*;
import controllers.*;
//
// NOTE: This file was generated from: japidviews/Application/categories.html
// Change to this file will be lost next time the template file is compiled.
//
@cn.bran.play.NoEnhance
public class categories extends cn.bran.play.JapidTemplateBase
{
	public static final String sourceTemplate = "japidviews/Application/categories.html";
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


	public categories() {
		super(null);
	}
	public categories(StringBuilder out) {
		super(out);
	}
/* based on https://github.com/branaway/Japid/issues/12
 */
	public static final String[] argNames = new String[] {/* args of the template*/"categories",  };
	public static final String[] argTypes = new String[] {/* arg types of the template*/"List<Category>",  };
	public static final Object[] argDefaults= new Object[] {null, };
	public static java.lang.reflect.Method renderMethod = getRenderMethod(japidviews.Application.categories.class);

	{
		setRenderMethod(renderMethod);
		setArgNames(argNames);
		setArgTypes(argTypes);
		setArgDefaults(argDefaults);
		setSourceTemplate(sourceTemplate);
	}
////// end of named args stuff

	private List<Category> categories; // line 1, japidviews/Application/categories.html
	public cn.bran.japid.template.RenderResult render(List<Category> categories) {
		this.categories = categories;
		long __t = -1;
		try {super.layout();} catch (RuntimeException __e) { super.handleException(__e);} // line 1, japidviews/Application/categories.html
		return new cn.bran.japid.template.RenderResultPartial(getHeaders(), getOut(), __t, actionRunners, sourceTemplate);
	}

	public static cn.bran.japid.template.RenderResult apply(List<Category> categories) {
		return new categories().render(categories);
	}

	@Override protected void doLayout() {
		beginDoLayout(sourceTemplate);
//------
p(" ");// line 1, categories.html
 if(asBoolean(categories)) {// line 2, categories.html
		p("     <ul>\n" + 
"       ");// line 2, categories.html
		for(Category cat: categories) {// line 4, categories.html
		p("	       <li>\n" + 
"	           <a href=\"\">");// line 4, categories.html
		p(cat.name);// line 6, categories.html
		p("</a>\n" + 
"	           ");// line 6, categories.html
		final categories _this0 = new categories(getOut()); _this0.setActionRunners(getActionRunners()).setOut(getOut()); _this0.render(cat.subCategories); // line 7, categories.html// line 7, categories.html
		p("	       </li>\n" + 
"       ");// line 7, categories.html
		}// line 9, categories.html
		p("     </ul>\n" + 
" ");// line 9, categories.html
		}// line 11, categories.html
		p(" \n" + 
" ");// line 11, categories.html
		
		endDoLayout(sourceTemplate);
	}

}