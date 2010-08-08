package japidviews._notifiers.TestEmailer;
import java.util.*;
import java.io.*;
import cn.bran.japid.tags.Each;
import japidviews._layouts.*;
import static  japidviews._javatags.JapidWebUtil.*;
import play.data.validation.Validation;
import play.mvc.Scope.*;
import models.*;
import play.data.validation.Error;
import japidviews._tags.*;
import controllers.*;
import japidviews._javatags.*;
import static play.templates.JavaExtensions.*;
import static cn.bran.play.JapidPlayAdapter.*;
import static play.data.validation.Validation.*;
import static cn.bran.play.WebUtils.*;
// NOTE: This file was generated from: japidviews/_notifiers/TestEmailer/emailme.html
// Change to this file will be lost next time the template file is compiled.
@cn.bran.play.NoEnhance
public class emailme extends cn.bran.japid.template.JapidTemplateBase{
	public static final String sourceTemplate = "japidviews/_notifiers/TestEmailer/emailme.html";
static private final String static_0 = ""
;
static private final String static_1 = "\n" + 
"Hello "
;
static private final String static_2 = "!"
;
	public emailme() {
		super(null);
	}
	public emailme(StringBuilder out) {
		super(out);
	}
	models.japidsample.Post post;
	public cn.bran.japid.template.RenderResult render(models.japidsample.Post post) {
		this.post = post;
		long t = -1;
		super.layout();
		return new cn.bran.japid.template.RenderResultPartial(this.headers, getOut(), t, actionRunners);
	}
	@Override protected void doLayout() {
p(static_0);// line 1
p(static_1);// line 1
p(post.title);// line 3
p(static_2);// line 3

	}
}
