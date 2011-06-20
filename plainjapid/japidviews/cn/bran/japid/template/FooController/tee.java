package japidviews.cn.bran.japid.template.FooController;
import java.util.*;
import java.io.*;
import cn.bran.japid.tags.Each;
import japidviews ._tags.*;
import japidviews ._layouts.*;
//
// NOTE: This file was generated from: japidviews/cn/bran/japid/template/FooController/tee.html
// Change to this file will be lost next time the template file is compiled.
//
public class tee extends cn.bran.japid.template.JapidTemplateBaseWithoutPlay
{	public static final String sourceTemplate = "japidviews/cn/bran/japid/template/FooController/tee.html";
	public tee() {
		super(null);
	}
	public tee(StringBuilder out) {
		super(out);
	}
/* based on https://github.com/branaway/Japid/issues/12
 */
public static final String[] argNames = new String[] {/* args of the template*/"u",  };
public static final String[] argTypes = new String[] {/* arg types of the template*/"cn.bran.japid.template.FooController.ModelUser",  };
public static final Object[] argDefaults= new Object[] {null, };
public static java.lang.reflect.Method renderMethod = getRenderMethod(japidviews.cn.bran.japid.template.FooController.tee.class);
{
	setRenderMethod(renderMethod);
	setArgNames(argNames);
	setArgTypes(argTypes);
	setArgDefaults(argDefaults);
}
////// end of named args stuff

	private cn.bran.japid.template.FooController.ModelUser u;
	public String render(cn.bran.japid.template.FooController.ModelUser u) {
		this.u = u;
		long t = -1;
		super.layout();
		 if (t != -1) System.out.println("[tee] rendering time: " + t);
		return getOut().toString();
	}
	@Override protected void doLayout() {
//------
;// line 1
p("\n" + 
"Hi: ");// line 1
p(u.what());// line 3
p("\n");// line 3

	}

}