package japidviews._layouts;
import java.util.*;
import java.io.*;
import cn.bran.japid.tags.Each;
import japidviews ._tags.*;
import japidviews ._layouts.*;
//
// NOTE: This file was generated from: japidviews/_layouts/main.html
// Change to this file will be lost next time the template file is compiled.
//
public abstract class main extends cn.bran.japid.template.JapidTemplateBaseWithoutPlay
{	public static final String sourceTemplate = "japidviews/_layouts/main.html";
	public main() {
		super(null);
	}
	public main(StringBuilder out) {
		super(out);
	}
	private String x;
	 public void layout(String x) {
		this.x = x;
		;// line 1
p("<head>");// line 1
	title();p(" - ");// line 2
p(x);// line 2
p("</head>\n" + 
"<body>");// line 2
	doLayout();
p("</body>\n");// line 3
	}
	 protected void title() {};

	protected abstract void doLayout();
}