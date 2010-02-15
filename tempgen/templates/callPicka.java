package templates;
import java.util.*;
import java.io.*;
import cn.bran.japid.tags.Each;
import japidviews._layouts.*;
import japidviews._tags.*;
import static cn.bran.play.JapidPlayAdapter.*;
// NOTE: This file was generated from: templates/callPicka.html
// Change to this file will be lost next time the template file is compiled.
@cn.bran.play.NoEnhance
public class callPicka extends cn.bran.japid.template.JapidTemplateBase{
	public static final String sourceTemplate = "templates/callPicka.html";
	public static final String contentType = "text/html";
static private final String static_0 = ""
;
static private final String static_1 = "the tag chosed:"
;
	public callPicka() {
		super(null);
	}
	public callPicka(StringBuilder out) {
		super(out);
	}
	public cn.bran.japid.template.RenderResult render() {
		long t = -1;
		super.layout();
		return new cn.bran.japid.template.RenderResult(this.contentType, getOut(), t);
	}
	@Override protected void doLayout() {
p(static_0);// line 1
// line 1
_picka0.render("a", "b", _picka0DoBody);


	}
	private picka _picka0 = new picka(getOut());
class picka0DoBody implements picka.DoBody< String>{
	public void render(String r) {
		
p(static_1);
p(r);

	}
}
	private picka0DoBody _picka0DoBody = new picka0DoBody();

}
