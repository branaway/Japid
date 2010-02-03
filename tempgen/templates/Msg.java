package templates;

import java.util.*;
import java.io.*;
import tag.*;
import bran.japid.tags.Each;
import bran.Post;
import layout.*;
import static bran.WebUtils.*;

// This file was generated from: templates/Msg.html
// Change to this file will be lost next time the template file is compiled.
public class Msg extends bran.japid.BranTemplateBase {
	public static final String sourceTemplate = "templates/Msg.html";
	static byte[] static_0 = getBytes("login: ");
	static byte[] static_1 = getBytes("\n" + "customer name: ");
	static byte[] static_2 = getBytes("");

	public Msg(OutputStream out) {
		super(out);
	}

	public void render() {
		super.layout();
	}

	@Override
	protected void doLayout() {
		p(static_0);// line 1
		;
		p(getMessage("login.name"));// line 1
		p(static_1);// line 1
		;
		p(getMessage("cus.name", "冉冉"));// line 2
		p(static_2);// line 2

	}
}
