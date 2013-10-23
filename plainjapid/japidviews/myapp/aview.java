//version: 0.9.35
package japidviews.myapp;
import java.util.*;
import java.io.*;
import cn.bran.japid.tags.Each;
import japidviews._layouts.*;
import japidviews._tags.*;
//
// NOTE: This file was generated from: japidviews/myapp/aview.html
// Change to this file will be lost next time the template file is compiled.
//
public class aview extends main
{
	public static final String sourceTemplate = "japidviews/myapp/aview.html";
	public aview() {
		super(null);
	}
	public aview(StringBuilder out) {
		super(out);
	}
/* based on https://github.com/branaway/Japid/issues/12
 */
	public static final String[] argNames = new String[] {/* args of the template*/"a",  };
	public static final String[] argTypes = new String[] {/* arg types of the template*/"String",  };
	public static final Object[] argDefaults= new Object[] {null, };
	public static java.lang.reflect.Method renderMethod = getRenderMethod(japidviews.myapp.aview.class);

	{
		setRenderMethod(renderMethod);
		setArgNames(argNames);
		setArgTypes(argTypes);
		setArgDefaults(argDefaults);
		setSourceTemplate(sourceTemplate);
	}
////// end of named args stuff

	private String a; // line 1, japidviews/myapp/aview.html
	public String render(String a) {
		this.a = a;
		long __t = -1;
		try {super.layout();} catch (RuntimeException __e) { super.handleException(__e);} // line 1, japidviews/myapp/aview.html
		 if (__t != -1) System.out.println("[aview] rendering time: " + __t);
		return getOut().toString();
	}

	public static String apply(String a) {
		return new aview().render(a);
	}

	@Override protected void doLayout() {
		beginDoLayout(sourceTemplate);
//------
;// line 1, aview.html
		;// line 1, aview.html
p("\n" + 
"escaped: ");// line 3, aview.html
		p(escape(a));// line 5, aview.html
		p("\n" + 
"nice view: ");// line 5, aview.html
		final taggy _taggy1 = new taggy(getOut()); _taggy1.setOut(getOut()); _taggy1.render(a + "1"); // line 6, aview.html// line 6, aview.html
		;// line 6, aview.html
		
		endDoLayout(sourceTemplate);
	}

	@Override protected void title() {
		p("my view");;
	}
}