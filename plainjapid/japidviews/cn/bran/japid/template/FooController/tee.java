package japidviews.cn.bran.japid.template.FooController;
import java.util.*;
import java.io.*;
import cn.bran.japid.tags.Each;
import japidviews ._tags.*;
import japidviews ._layouts.*;
//
// NOTE: This file was generated from: japidviews/cn/bran/japid/template/FooController/tee.html
// Change to this file will be lost next time the template file is compiled.
//
public class tee extends cn.bran.japid.template.JapidTemplateBaseWithoutPlay
{	public static final String sourceTemplate = "japidviews/cn/bran/japid/template/FooController/tee.html";
	public tee() {
		super(null);
	}
	public tee(StringBuilder out) {
		super(out);
	}
	private cn.bran.japid.template.FooController.ModelUser u;
	public String render(cn.bran.japid.template.FooController.ModelUser u) {
		this.u = u;
		long t = -1;
		super.layout();
		return getOut().toString();
	}
	@Override protected void doLayout() {
//------
;// line 1
p("\n" + 
"Hi: ");// line 1
p(u.what());// line 3
p("\n");// line 3

	}

}