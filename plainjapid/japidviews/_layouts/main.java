package japidviews._layouts;
import java.util.*;
import java.io.*;
import cn.bran.japid.tags.Each;
import japidviews._layouts.*;
import japidviews._tags.*;
//
// NOTE: This file was generated from: japidviews/_layouts/main.html
// Change to this file will be lost next time the template file is compiled.
//
public abstract class main extends cn.bran.play.JapidTemplateBase
{
	public static final String sourceTemplate = "japidviews/_layouts/main.html";
	{
		putHeader("Content-Type", "text/html; charset=utf-8");
		setContentType("text/html; charset=utf-8");
	}

// - add implicit fields with Play

	final play.mvc.Http.Request request = play.mvc.Http.Request.current(); 
	final play.mvc.Http.Response response = play.mvc.Http.Response.current(); 
	final play.mvc.Scope.Session session = play.mvc.Scope.Session.current();
	final play.mvc.Scope.RenderArgs renderArgs = play.mvc.Scope.RenderArgs.current();
	final play.mvc.Scope.Params params = play.mvc.Scope.Params.current();
	final play.data.validation.Validation validation = play.data.validation.Validation.current();
	final cn.bran.play.FieldErrors errors = new cn.bran.play.FieldErrors(validation);
	final play.Play _play = new play.Play(); 

// - end of implicit fields with Play 


	public main() {
		super(null);
	}
	public main(StringBuilder out) {
		super(out);
	}
	private String x; // line 1
	 public void layout(String x) {
		this.x = x;
		beginDoLayout(sourceTemplate);		;// line 1
		p("<head>");// line 1
		title();p(" - ");// line 2
		p(x);// line 2
		p("</head>\n" + 
"<body>");// line 2
		doLayout();// line 3
		p("</body>\n");// line 3
				endDoLayout(sourceTemplate);	}
	 protected void title() {};

	protected abstract void doLayout();
}