package japidviews.templates;
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
// NOTE: This file was generated from: japidviews/templates/callPicka.html
// Change to this file will be lost next time the template file is compiled.
@cn.bran.play.NoEnhance
public class callPicka extends cn.bran.japid.template.JapidTemplateBase{
	public static final String sourceTemplate = "japidviews/templates/callPicka.html";
static private final String static_0 = "before...\n" + 
"";
static private final String static_1 = "\n" + 
"    the tag chosed: "
;
static private final String static_2 = "\n" + 
"";
static private final String static_3 = "\n" + 
"after...\n" + 
"";
	public callPicka() {
		super(null);
	}
	public callPicka(StringBuilder out) {
		super(out);
	}
	public cn.bran.japid.template.RenderResult render() {
		long t = -1;
		super.layout();
		return new cn.bran.japid.template.RenderResultPartial(this.headers, getOut(), t, actionRunners);
	}
	@Override protected void doLayout() {
p(static_0);// line 1
_picka0.setActionRunners(getActionRunners());
_picka0.render("a", "b" + "c", _picka0DoBody);
// line 2
p(static_3);// line 4

	}
	private picka _picka0 = new picka(getOut());
class picka0DoBody implements picka.DoBody< String>{
	public void render(String r) {
		// line 2
p(static_1);// line 2
p(r);// line 3
p(static_2);// line 3

	}
}
	private picka0DoBody _picka0DoBody = new picka0DoBody();

}
