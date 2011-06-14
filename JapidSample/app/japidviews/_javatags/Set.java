package japidviews._javatags;
import java.util.*;
import java.io.*;
import java.lang.reflect.InvocationTargetException;

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
// a model code for named stuff
// NOTE: This file was generated from: japidviews/templates/Set.html
// Change to this file will be lost next time the template file is compiled.
//
@cn.bran.play.NoEnhance
public class Set extends japidviews._layouts.SetLayout
{	public static final String sourceTemplate = "japidviews/templates/Set.html";
{
	headers.put("Content-Type", "text/html; charset=utf-8");
}

/*
 * based on https://github.com/branaway/Japid/issues/12
 * This static mapping will be later user in method renderModel to construct an proper Object[] array
 *which is needed to invoke the method render(Object... args) over reflection.
 */
public static java.lang.reflect.Method renderMethod;
public static final String[] argNames = new String[] {/* args of the template*/};
static {
    java.lang.reflect.Method[] methods =  /*place the current template class here*/japidviews.templates.Set.class.getDeclaredMethods();

    for(java.lang.reflect.Method m : methods) {
    if (m.getName().equals("render")) {
            renderMethod = m;
            break;
        }
    }
}

public cn.bran.japid.template.RenderResult renderModel(cn.bran.japid.template.JapidModelMap model) {
    // a static utils method of JapidModelMap to build up an Object[] array. Nulls are used where the args are omitted.
    Object[] args = model.buildArgs(argNames);
    try {
		return (cn.bran.japid.template.RenderResult ) renderMethod.invoke(this, args);
	} catch (IllegalArgumentException e) {
		throw new RuntimeException(e);
	} catch (IllegalAccessException e) {
		throw new RuntimeException(e);
	} catch (InvocationTargetException e) {
		Throwable t = e.getTargetException();
		throw new RuntimeException(t);
	}
}
////// end of named args stuff

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


	public Set() {
		super(null);
	}
	public Set(StringBuilder out) {
		super(out);
	}
	private String a;
	public cn.bran.japid.template.RenderResult render(String a) {
		this.a = a;
		long t = -1;
		super.layout();
		return new cn.bran.japid.template.RenderResultPartial(this.headers, getOut(), t, actionRunners);
	}
	@Override protected void doLayout() {
//------
;// line 1
p("\n");// line 2
p("\n" + 
"\n");// line 8
p("\n" + 
"\n");// line 10
p("\n");// line 12
// line 14
;// line 16

	}

	@Override protected void footer() {
final dummyTag _dummyTag2 = new dummyTag(getOut());
{ _dummyTag2.setActionRunners(getActionRunners()); }

		// line 14
p("    great footer. Call a tag: ");// line 14
_dummyTag2.render("me");
// line 15
;
	}
	@Override protected void title() {
		p( "Home away" + a);;
	}
}