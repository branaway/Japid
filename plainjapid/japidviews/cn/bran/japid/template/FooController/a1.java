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
final taggy _taggy2 = new taggy(getOut());

// -- end of the tag objects

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
		super.layout(a + "1");
		 if (t != -1) System.out.println("[a1] rendering time: " + t);
		return getOut().toString();
	}
	@Override protected void doLayout() {
//------
;// line 1
p("\n");// line 3
p(foo(a));// line 5
p("\n" + 
"\n");// line 5
// line 7
;// line 9

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
_taggy2.setOut(getOut()); _taggy2.render(ar);
// line 8

this.setOut(ori);
return sb.toString();
}
}