package japidviews.cn.bran.japid.template.FooControllerBare;
import java.util.*;
import java.io.*;
import cn.bran.japid.tags.Each;
import japidviews ._tags.*;
import japidviews ._layouts.*;
//
// NOTE: This file was generated from: japidviews/cn/bran/japid/template/FooControllerBare/a1.html
// Change to this file will be lost next time the template file is compiled.
//
public class a1 extends cn.bran.japid.template.JapidTemplateBaseWithoutPlay
{	public static final String sourceTemplate = "japidviews/cn/bran/japid/template/FooControllerBare/a1.html";
	public a1() {
		super(null);
	}
	public a1(StringBuilder out) {
		super(out);
	}
	private String a;
	public String render(String a) {
		this.a = a;
		long t = -1;
		super.layout();
		return getOut().toString();
	}
	@Override protected void doLayout() {
//------
;// line 1
p(">");// line 1
p(a);// line 2

	}

}