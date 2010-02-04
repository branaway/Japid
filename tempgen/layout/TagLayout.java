package layout;
import java.util.*;
import java.io.*;
import tag.*;
import bran.japid.tags.Each;
import static japidplay.PlayTemplateVarsAdapter.*;
// This file was generated from: layout/TagLayout.html
// Change to this file will be lost next time the template file is compiled.
@bran.NoEnhance
public abstract class TagLayout extends bran.japid.BranTemplateBase{
	public static final String sourceTemplate = "layout/TagLayout.html";
static byte[] static_0 = getBytes("标签布局\n" + 
"<div>\n" + 
"");
static byte[] static_1 = getBytes("\n" + 
"</div>\n" + 
"\n" + 
"\n" + 
"");
	public TagLayout(OutputStream out) {
		super(out);
	}
	@Override public void layout() {		p(static_0);// line 1
	doLayout();// line 3
p(static_1);// line 3
	}	protected abstract void doLayout();
}