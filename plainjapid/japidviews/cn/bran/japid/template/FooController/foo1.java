package japidviews.cn.bran.japid.template.FooController;
import java.util.*;
import java.io.*;
import cn.bran.japid.tags.Each;
import japidviews ._tags.*;
import japidviews ._layouts.*;
//
// NOTE: This file was generated from: japidviews/cn/bran/japid/template/FooController/foo1.html
// Change to this file will be lost next time the template file is compiled.
//
public class foo1 extends main
{	public static final String sourceTemplate = "japidviews/cn/bran/japid/template/FooController/foo1.html";

// -- set up the tag objects
final taggy _taggy1 = new taggy(getOut());

// -- end of the tag objects

	public foo1() {
		super(null);
	}
	public foo1(StringBuilder out) {
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
p("foo12: ");// line 3
_taggy1.setOut(getOut()); _taggy1.render(a + "1");
// line 4
;// line 4

	}

	@Override protected void title() {
		p("my view");;
	}
}