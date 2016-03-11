/**
 * 
 */
package cn.bran.play.routing;

import cn.bran.play.JapidController;
import cn.bran.play.routing.HttpMethod.GET;
import cn.bran.play.routing.HttpMethod.POST;


@AutoPath
public class AutoPathSample extends JapidController {
	
	@GET
	public static void foo(String a, int b) {
		renderJapid(a, b);
	}
	
	public static void bar(String hi, int there) {
		renderText("bar");
	}
	
	// effectively -> GET|POST  /t3.App.bb/{a}/{b}.html
	@GET
	@POST
	@EndWith // ".html" by default
	public static void bb(int aa, String bb) {
		renderJapid(aa, bb);
	}

}
