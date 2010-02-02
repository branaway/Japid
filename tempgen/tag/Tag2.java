package tag;
import java.util.*;
import java.io.*;
import tag.*;
import bran.japid.tags.Each;
import layout.*;
import static bran.WebUtils.*;
// This file was generated from: tag/Tag2.html
// Change to this file will be lost next time the template file is compiled.
public class Tag2 extends bran.japid.BranTemplateBase{
	public static final String sourceTemplate = "tag/Tag2.html";
static byte[] static_0 = getBytes(""
);
static byte[] static_1 = getBytes("\n" + 
"\n" + 
"<span>"
);
static byte[] static_2 = getBytes("</span>"
);
	public Tag2(OutputStream out) {
		super(out);
	}
	String msg;
	public void render(
	String msg
) {
		this.msg = msg;
		super.layout();
	}
	@Override protected void doLayout() {
p(static_0);// line 1
p(static_1);// line 3
p(msg);// line 5
p(static_2);// line 5

	}
}
