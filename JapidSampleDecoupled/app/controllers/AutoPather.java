package controllers;

import cn.bran.play.JapidController;
import cn.bran.play.routing.AutoPath;


@AutoPath
public class AutoPather extends JapidController{

	public static void foo(String a, int b) {
		renderJapid(a, b);
	}
	
}
