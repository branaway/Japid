package japidviews.myapp;
import java.util.*;
import java.io.*;
import cn.bran.japid.tags.Each;
import japidviews ._tags.*;
import japidviews ._layouts.*;
//
// NOTE: This file was generated from: japidviews/myapp/aview.html
// Change to this file will be lost next time the template file is compiled.
//
public class aview extends main
{	public static final String sourceTemplate = "japidviews/myapp/aview.html";
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
}
////// end of named args stuff

	private String a;
	public String render(String a) {
		this.a = a;
		long t = -1;
		super.layout();
		 if (t != -1) System.out.println("[aview] rendering time: " + t);
		return getOut().toString();
	}
	@Override protected void doLayout() {

// -- set up the tag objects
final taggy _taggy1 = new taggy(getOut());

// -- end of the tag objects

//------
;// line 1
p("\n");// line 1
p("\n" + 
"escaped: ");// line 3
p(escape(a));// line 5
p("\n" + 
"nice view: ");// line 5
_taggy1.render(a + "1");
// line 6
;// line 6

	}

	@Override protected void title() {
		p("my view");;
	}
}