package japidviews.templates;
import java.util.*;
import java.io.*;
import cn.bran.japid.tags.Each;
import japidviews._layouts.*;
import models.*;
import japidviews._tags.*;
import controllers.*;
import japidviews._javatags.*;
//
// NOTE: This file was generated from: japidviews/templates/noplay.html
// Change to this file will be lost next time the template file is compiled.
//
@cn.bran.play.NoEnhance
public class noplay extends cn.bran.japid.template.JapidTemplateBaseWithoutPlay
{	public static final String sourceTemplate = "japidviews/templates/noplay.html";
	public noplay() {
		super(null);
	}
	public noplay(StringBuilder out) {
		super(out);
	}
/* based on https://github.com/branaway/Japid/issues/12
 */
public static final String[] argNames = new String[] {/* args of the template*/"s",  };
public static final String[] argTypes = new String[] {/* arg types of the template*/"String",  };
public static final Object[] argDefaults= new Object[] {null, };
public static java.lang.reflect.Method renderMethod = getRenderMethod(japidviews.templates.noplay.class);

{
	setRenderMethod(renderMethod);
	setArgNames(argNames);
	setArgTypes(argTypes);
	setArgDefaults(argDefaults);
	setSourceTemplate(sourceTemplate);

}
////// end of named args stuff

	private String s;
	public String render(String s) {
		this.s = s;
		long t = -1;
		try {super.layout();} catch (RuntimeException e) { super.handleException(e);}
		 if (t != -1) System.out.println("[noplay] rendering time: " + t);
		return getOut().toString();
	}
	@Override protected void doLayout() {
//------
;// line 1
p("\n" + 
"hello ");// line 2
		final japidviews._tags.Tag2 _japidviews__tags_Tag20 = new japidviews._tags.Tag2(getOut()); _japidviews__tags_Tag20.setOut(getOut()); _japidviews__tags_Tag20.render(named("msg", s));// line 4
		p(" !!!!\n" + 
"\n");// line 4
		
	}

}