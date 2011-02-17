package controllers.more;
import java.util.Date;

import play.cache.CacheFor;
import cn.bran.play.JapidController;

public class Portlets extends JapidController {
	@CacheFor("20s")
	public static void index() {
		renderJapid("a", "b");
	}

	public static void panel1(String a) {
		System.out.println("panel1 called");
		renderJapid(a);
	}

	public static void panel2(String b) {
		System.out.println("panel2 called");
		renderJapid(b);
	}

	@CacheFor("5s")
	public static void panel3(String whatever) {
		System.out.println("panel3 called");
		renderText("<div>" + new Date() + "</div>");
	}
}
