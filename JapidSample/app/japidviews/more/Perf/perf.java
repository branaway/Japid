package japidviews.more.Perf;
import java.util.*;
import java.io.*;
import cn.bran.japid.tags.Each;
import static play.templates.JavaExtensions.*;
import static cn.bran.play.JapidPlayAdapter.*;
import static play.data.validation.Validation.*;
import japidviews._layouts.*;
import play.i18n.Messages;
import static  japidviews._javatags.JapidWebUtil.*;
import play.data.validation.Validation;
import play.mvc.Scope.*;
import models.*;
import play.data.validation.Error;
import play.i18n.Lang;
import japidviews._tags.*;
import controllers.*;
import play.mvc.Http.*;
import japidviews._javatags.*;
//
// NOTE: This file was generated from: japidviews/more/Perf/perf.html
// Change to this file will be lost next time the template file is compiled.
//
@cn.bran.play.NoEnhance
public class perf extends perfmain
{	public static final String sourceTemplate = "japidviews/more/Perf/perf.html";
{
	headers.put("Content-Type", "text/html; charset=utf-8");
}

// - add implicit fields with Play

	final Request request = Request.current(); 
	final Response response = Response.current(); 
	final Session session = Session.current();
	final RenderArgs renderArgs = RenderArgs.current();
	final Params params = Params.current();
	final Validation validation = Validation.current();
	final cn.bran.play.FieldErrors errors = new cn.bran.play.FieldErrors(validation);
	final play.Play _play = new play.Play(); 

// - end of implicit fields with Play 


	public perf() {
		super(null);
	}
	public perf(StringBuilder out) {
		super(out);
	}
	private String title;
	private DataModel.User user;
	private ArrayList<DataModel.Entry> entries;
	public cn.bran.japid.template.RenderResult render(String title, DataModel.User user, ArrayList<DataModel.Entry> entries) {
		this.title = title;
		this.user = user;
		this.entries = entries;
		long t = -1;
		super.layout(user);
		return new cn.bran.japid.template.RenderResult(this.headers, getOut(), t);
	}
	@Override protected void doLayout() {
//------
;// line 1
p("\n");// line 1
p("\n");// line 3
p("\n");// line 5
p("\n");// line 7
p("\n");// line 9
if (entries.size() > 0 ) {// line 11
p("	");// line 11
for (DataModel.Entry entry : entries) {// line 12
p("		<div id=\"entries\">\n" + 
"		    Entry Id: ");// line 12
p(entry.getEntryId());// line 14
p(", Date: ");// line 14
p(entry.getEntryDate());// line 14
p("\n" + 
"		    <h2>");// line 14
p(entry.getTitle());// line 15
p("</h2>\n" + 
"		    <div>Submitted By: ");// line 15
p(entry.getOwner().getUserName());// line 16
p(" - ");// line 16
p(entry.getOwner().getEmail());// line 16
p("</div>\n" + 
"		    <div>");// line 16
p(entry.getBody());// line 17
p("</div>\n" + 
"		    ");// line 17
if(entry.getComments().size() > 0) {// line 18
                for (DataModel.Comment comment: entry.getComments()) {// line 19
p("				    <div>\n" + 
"				        <div>Comment by: ");// line 19
p(comment.getOwner().getUserName());// line 21
p("</div>\n" + 
"				        <div>");// line 21
p(comment.getCommentText());// line 22
p("</div>\n" + 
"				    </div>\n" + 
"                ");// line 22
}// line 24
            } else {// line 25
p("		      <div>No comments yet</div>\n" + 
"		    ");// line 25
}// line 27
p("		</div>\n" + 
"	");// line 27
}// line 29
} else {// line 30
p("	<div>\n" + 
"	    <div>No Entries Found</div>\n" + 
"	</div>\n");// line 30
}// line 34

	}

	@Override protected void title() {
		p(title);;
	}
}