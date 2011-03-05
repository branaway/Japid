package japidviews.cn.bran.japid.template.FooController;
import java.util.*;
import java.io.*;
import cn.bran.japid.tags.Each;
import japidviews ._tags.*;
import japidviews ._layouts.*;
//
// NOTE: This file was generated from: japidviews/cn/bran/japid/template/FooController/a1.html
// Change to this file will be lost next time the template file is compiled.
//
public class a1 extends main
{	public static final String sourceTemplate = "japidviews/cn/bran/japid/template/FooController/a1.html";

// -- set up the tag objects
final taggy _taggy1 = new taggy(getOut());

// -- end of the tag objects

	public a1() {
		super(null);
	}
	public a1(StringBuilder out) {
		super(out);
	}
	private String a;
	public cn.bran.japid.template.RenderResult render(String a) {
		this.a = a;
		long t = -1;
		super.layout(a + "1");
		return new cn.bran.japid.template.RenderResult(this.headers, getOut(), t);
	}
	@Override protected void doLayout() {
//------
;// line 1
p("\n" + 
"->a1: ");// line 3
_taggy1.setOut(getOut()); _taggy1.render(a + "1");
// line 5

	}

	@Override protected void title() {
		p("my view");;
	}
}