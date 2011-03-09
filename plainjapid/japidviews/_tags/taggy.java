package japidviews._tags;
import java.util.*;
import java.io.*;
import cn.bran.japid.tags.Each;
import japidviews ._tags.*;
import japidviews ._layouts.*;
//
// NOTE: This file was generated from: japidviews/_tags/taggy.html
// Change to this file will be lost next time the template file is compiled.
//
public class taggy extends cn.bran.japid.template.JapidTemplateBaseWithoutPlay
{	public static final String sourceTemplate = "japidviews/_tags/taggy.html";

// -- set up the tag objects
final Each _Each1 = new Each(getOut());

final taddy _taddy0 = new taddy(getOut());

// -- end of the tag objects

	public taggy() {
		super(null);
	}
	public taggy(StringBuilder out) {
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
p("[");// line 1
p(a);// line 2
p("]-->\n");// line 2
_taddy0.setOut(getOut()); _taddy0.render(new taddy.DoBody<String[]>(){
public void render(final String[] ss) {
// line 3
    _Each1.setOut(getOut()); _Each1.render(ss, new Each.DoBody<String>(){
public void render(final String s, final int _size, final int _index, final boolean _isOdd, final String _parity, final boolean _isFirst, final boolean _isLast) {
// line 4
p("    -> ");// line 4
p(s);// line 5
p("\n" + 
"    ");// line 5

}
}
);
// line 4

}
}
);
// line 3

	}

}