package layout;
import java.util.*;
import java.io.*;
import tag.*;
import bran.japid.tags.Each;
// This file was generated from: layout/SetLayout.html
// Change to this file will be lost next time the template file is compiled.
public abstract class SetLayout extends bran.japid.BranTemplateBase{
	public static final String sourceTemplate = "layout/SetLayout.html";
static byte[] static_0 = getBytes(""
);
static byte[] static_1 = getBytes("\n" + 
"");
static byte[] static_2 = getBytes("\n" + 
"");
	public SetLayout(OutputStream out) {
		super(out);
	}
	@Override public void layout() {		p(static_0);// line 1
	title();// line 1
p(static_1);// line 1
	footer();// line 2
p(static_2);// line 2
	}	protected abstract void title();
	protected abstract void footer();
	protected abstract void doLayout();
}