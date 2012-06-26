package japidviews.cn.bran.japid.template.FooController;
import java.util.*;
import java.io.*;
import cn.bran.japid.tags.Each;
import japidviews._layouts.*;
import japidviews._tags.*;
//
// NOTE: This file was generated from: japidviews/cn/bran/japid/template/FooController/a1.html
// Change to this file will be lost next time the template file is compiled.
//
public class a1 extends main
{
	public static final String sourceTemplate = "japidviews/cn/bran/japid/template/FooController/a1.html";
	public a1() {
		super(null);
	}
	public a1(StringBuilder out) {
		super(out);
	}
/* based on https://github.com/branaway/Japid/issues/12
 */
	public static final String[] argNames = new String[] {/* args of the template*/"a",  };
	public static final String[] argTypes = new String[] {/* arg types of the template*/"String",  };
	public static final Object[] argDefaults= new Object[] {null, };
	public static java.lang.reflect.Method renderMethod = getRenderMethod(japidviews.cn.bran.japid.template.FooController.a1.class);

	{
		setRenderMethod(renderMethod);
		setArgNames(argNames);
		setArgTypes(argTypes);
		setArgDefaults(argDefaults);
		setSourceTemplate(sourceTemplate);
	}
////// end of named args stuff

	private String a; // line 1
	public String render(String a) {
		this.a = a;
		long t = -1;
		try {super.layout(a + "1");} catch (RuntimeException e) { super.handleException(e);} // line 1
		 if (t != -1) System.out.println("[a1] rendering time: " + t);
		return getOut().toString();
	}
	@Override protected void doLayout() {
		beginDoLayout(sourceTemplate);
//------
;// line 1
p("\n");// line 3
		p(foo(a));// line 5
		p("\n" + 
"\n");// line 5
		// line 7
		;// line 9
		
		endDoLayout(sourceTemplate);
	}

	@Override protected void title() {
		p("my view");;
	}
public String foo(String ar) {
StringBuilder sb = new StringBuilder();
StringBuilder ori = getOut();
this.setOut(sb);
// line 7
		p("  -> a1-: ");// line 7
		final taggy _taggy2 = new taggy(getOut()); _taggy2.setOut(getOut()); _taggy2.render(ar); // line 8// line 8

this.setOut(ori);
	return sb.toString();
}
}