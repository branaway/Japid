package controllers.more;

import play.mvc.*;

import cn.bran.play.JapidController;
import cn.bran.play.JapidController2;
import cn.bran.play.routing.AutoPath;

@AutoPath
public class ContentNegotiation extends JapidController {

    public static void index() {
        renderJapid();
    }

    public static void xml() {
    	renderJapid();
    }
    
    public static void json() {
    	renderJapid();
    }
    
    public static void xmld() {
    	renderXml("<a><b>hello</b></a>");
    }
    
}
