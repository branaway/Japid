package japidviews.templates;
import java.util.*;
import java.io.*;
import cn.bran.japid.tags.Each;
import	 	models.japidsample.Post;
import static play.templates.JavaExtensions.*;
import static cn.bran.play.JapidPlayAdapter.*;
import static play.data.validation.Validation.*;
import japidviews._layouts.*;
import play.i18n.Messages;
import play.data.validation.Validation;
import play.mvc.Scope.*;
import models.*;
import play.data.validation.Error;
import japidviews._tags.*;
import play.i18n.Lang;
import play.mvc.Http.*;
import controllers.*;
//
// NOTE: This file was generated from: japidviews/templates/AllPost.html
// Change to this file will be lost next time the template file is compiled.
//
@cn.bran.play.NoEnhance
public class AllPost extends Layout
{
	public static final String sourceTemplate = "japidviews/templates/AllPost.html";
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


	public AllPost() {
		super(null);
	}
	public AllPost(StringBuilder out) {
		super(out);
	}
/* based on https://github.com/branaway/Japid/issues/12
 */
	public static final String[] argNames = new String[] {/* args of the template*/"blogTitle", "allPost",  };
	public static final String[] argTypes = new String[] {/* arg types of the template*/"String", "List<Post>",  };
	public static final Object[] argDefaults= new Object[] {null,null, };
	public static java.lang.reflect.Method renderMethod = getRenderMethod(japidviews.templates.AllPost.class);

	{
		setRenderMethod(renderMethod);
		setArgNames(argNames);
		setArgTypes(argTypes);
		setArgDefaults(argDefaults);
		setSourceTemplate(sourceTemplate);
	}
////// end of named args stuff

	private String blogTitle; // line 3, japidviews/templates/AllPost.html
	private List<Post> allPost; // line 3, japidviews/templates/AllPost.html
	public cn.bran.japid.template.RenderResult render(String blogTitle,List<Post> allPost) {
		this.blogTitle = blogTitle;
		this.allPost = allPost;
		long __t = -1;
		 __t = System.nanoTime();
		try {super.layout();} catch (RuntimeException e) { super.handleException(e);} // line 3, japidviews/templates/AllPost.html
     	String __l = "" + (System.nanoTime() - __t) / 100000;
		int __len = __l.length();
		__l = __l.substring(0, __len - 1) + "." +  __l.substring(__len - 1);

		System.out.println("[AllPost] rendering time(ms): " + __l);
		return new cn.bran.japid.template.RenderResultPartial(getHeaders(), getOut(), __t, actionRunners, sourceTemplate);
	}

	public static cn.bran.japid.template.RenderResult apply(String blogTitle,List<Post> allPost) {
		return new AllPost().render(blogTitle, allPost);
	}

	@Override protected void doLayout() {
		beginDoLayout(sourceTemplate);
//------
;// line 1, AllPost.html
;// line 2, AllPost.html
		;// line 7, AllPost.html

final Each _Each1 = new Each(getOut()); _Each1.setOut(getOut()); _Each1.render(// line 12, AllPost.html
allPost, new Each.DoBody<Post>(){ // line 12, AllPost.html
public void render(final Post p, final int _size, final int _index, final boolean _isOdd, final String _parity, final boolean _isFirst, final boolean _isLast) { // line 12, AllPost.html
// line 12, AllPost.html
		p("\n" + 
"        ");// line 12, AllPost.html
		p("\n" + 
"\n" + 
"	    ");// line 16, AllPost.html
		final Display _Display2 = new Display(getOut()); _Display2.setActionRunners(getActionRunners()).setOut(getOut()); _Display2.render( // line 18, AllPost.html
new Display.DoBody<String>(){ // line 18, AllPost.html
public void render(final String title) { // line 18, AllPost.html
// line 18, AllPost.html
		p("		   The real title is: ");// line 18, AllPost.html
		p(title);// line 19, AllPost.html
		p("\n" + 
"	    ");// line 19, AllPost.html
		
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
, named("post", p), named("as", "home222"));// line 18, AllPost.html

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
);// line 12, AllPost.html

final Tag2 _Tag23 = new Tag2(getOut()); _Tag23.setActionRunners(getActionRunners()).setOut(getOut()); _Tag23.render(named("msg", blogTitle), named("age", 100)); // line 23, AllPost.html// line 23, AllPost.html
		p("\n" + 
"<p>cool cool!</p>");// line 23, AllPost.html
		
		endDoLayout(sourceTemplate);
	}

	@Override protected void title() {
		p( "Home of " + blogTitle);;
	}
}