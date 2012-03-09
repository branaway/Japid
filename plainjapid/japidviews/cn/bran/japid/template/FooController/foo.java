package japidviews.cn.bran.japid.template.FooController;
import java.util.*;
import java.io.*;
import cn.bran.japid.tags.Each;
import japidviews ._tags.*;
import japidviews ._layouts.*;
//
// NOTE: This file was generated from: japidviews/cn/bran/japid/template/FooController/foo.html
// Change to this file will be lost next time the template file is compiled.
//
public class foo extends main
{	public static final String sourceTemplate = "japidviews/cn/bran/japid/template/FooController/foo.html";
	public foo() {
		super(null);
	}
	public foo(StringBuilder out) {
		super(out);
	}
/* based on https://github.com/branaway/Japid/issues/12
 */
public static final String[] argNames = new String[] {/* args of the template*/"a",  };
public static final String[] argTypes = new String[] {/* arg types of the template*/"String",  };
public static final Object[] argDefaults= new Object[] {null, };
public static java.lang.reflect.Method renderMethod = getRenderMethod(japidviews.cn.bran.japid.template.FooController.foo.class);

{
	setRenderMethod(renderMethod);
	setArgNames(argNames);
	setArgTypes(argTypes);
	setArgDefaults(argDefaults);
	setSourceTemplate(sourceTemplate);

}
////// end of named args stuff

	private String a;
	public String render(String a) {
		this.a = a;
		long t = -1;
		try {super.layout();} catch (RuntimeException e) { super.handleException(e);}
		 if (t != -1) System.out.println("[foo] rendering time: " + t);
		return getOut().toString();
	}
	@Override protected void doLayout() {
//------
;// line 1
p("foo: ");// line 3
		final taggy _taggy1 = new taggy(getOut()); _taggy1.setOut(getOut()); _taggy1.render(a + "1");// line 4
		;// line 4
		
	}

	@Override protected void title() {
		p("my view");;
	}
}