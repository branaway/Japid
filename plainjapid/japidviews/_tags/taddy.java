package japidviews._tags;
import java.util.*;
import java.io.*;
import cn.bran.japid.tags.Each;
import japidviews ._tags.*;
import japidviews ._layouts.*;
//
// NOTE: This file was generated from: japidviews/_tags/taddy.html
// Change to this file will be lost next time the template file is compiled.
//
public class taddy extends cn.bran.japid.template.JapidTemplateBaseWithoutPlay
{	public static final String sourceTemplate = "japidviews/_tags/taddy.html";
	public taddy() {
		super(null);
	}
	public taddy(StringBuilder out) {
		super(out);
	}
/* based on https://github.com/branaway/Japid/issues/12
 */
public static final String[] argNames = new String[] {/* args of the template*/ };
public static final String[] argTypes = new String[] {/* arg types of the template*/ };
public static final Object[] argDefaults= new Object[] { };
public static java.lang.reflect.Method renderMethod = getRenderMethod(japidviews._tags.taddy.class);

{
	setRenderMethod(renderMethod);
	setArgNames(argNames);
	setArgTypes(argTypes);
	setArgDefaults(argDefaults);
	setSourceTemplate(sourceTemplate);

}
////// end of named args stuff

	{ setHasDoBody(); }
public cn.bran.japid.template.RenderResult render(DoBody body, cn.bran.japid.compiler.NamedArgRuntime... named) {
    Object[] args = buildArgs(named, body);
    try {return runRenderer(args);} catch(RuntimeException e) {handleException(e); throw e;} 
}

	DoBody body;
public static interface DoBody<A> {
		void render(A a);
		void setBuffer(StringBuilder sb);
		void resetBuffer();
}
<A> String renderBody(A a) {
		StringBuilder sb = new StringBuilder();
		if (body != null){
			body.setBuffer(sb);
			body.render( a);
			body.resetBuffer();
		}
		return sb.toString();
	}
	public String render(DoBody body) {
		this.body = body;
		long t = -1;
		try {super.layout();} catch (RuntimeException e) { super.handleException(e);} 
		 if (t != -1) System.out.println("[taddy] rendering time: " + t);
		return getOut().toString();
	}
	public String render() {
		long t = -1;
		try {super.layout();} catch (RuntimeException e) { super.handleException(e);} 
		 if (t != -1) System.out.println("[taddy] rendering time: " + t);
		return getOut().toString();
	}
	@Override protected void doLayout() {
//------
p("teddy bear\n" + 
"\n");// line 1
		String[] ss = new String[]{"a", "add", "cd"};// line 3
		p("\n");// line 3
		if (body != null){ body.setBuffer(getOut()); body.render(ss); body.resetBuffer();}// line 5
		
	}

}