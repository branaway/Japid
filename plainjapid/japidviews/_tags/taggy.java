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
	public taggy() {
		super(null);
	}
	public taggy(StringBuilder out) {
		super(out);
	}
/* based on https://github.com/branaway/Japid/issues/12
 */
public static final String[] argNames = new String[] {/* args of the template*/"a",  };
public static final String[] argTypes = new String[] {/* arg types of the template*/"String",  };
public static final Object[] argDefaults= new Object[] {null, };
public static java.lang.reflect.Method renderMethod = getRenderMethod(japidviews._tags.taggy.class);
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
		 if (t != -1) System.out.println("[taggy] rendering time: " + t);
		return getOut().toString();
	}
	@Override protected void doLayout() {

// -- set up the tag objects
final Each _Each1 = new Each(getOut());

final taddy _taddy0 = new taddy(getOut());

// -- end of the tag objects

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

StringBuilder oriBuffer;
@Override
public void setBuffer(StringBuilder sb) {
	oriBuffer = getOut();
	setOut(sb);
}

@Override
public void resetBuffer() {
	setOut(oriBuffer);
}

}
);
// line 4

}

StringBuilder oriBuffer;
@Override
public void setBuffer(StringBuilder sb) {
	oriBuffer = getOut();
	setOut(sb);
}

@Override
public void resetBuffer() {
	setOut(oriBuffer);
}

}
);
// line 3

	}

}