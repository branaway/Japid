package layout;
import java.util.*;
import java.io.*;
import tag.*;
// This file was generated from: layout/Layout.html
// Change to this file will be lost next time the template file is compiled.
public abstract class Layout extends bran.japid.BranTemplateBase{
	public static final String sourceTemplate = "layout/Layout.html";
static byte[] static_0 = getBytes("I'm the layout.\n" + 
"<p>\n" + 
"");
static byte[] static_1 = getBytes(";\n" + 
"</p>\n" + 
"<div>\n" + 
"");
static byte[] static_2 = getBytes("\n" + 
"</div>\n" + 
"\n" + 
"\n" + 
"");
	public Layout(OutputStream out) {
		super(out);
	}
	@Override public void layout() {		p(static_0);// line 1
	title();// line 3
p(static_1);// line 3
	doLayout();// line 6
p(static_2);// line 6
	}	protected abstract void title();
	protected abstract void doLayout();
}