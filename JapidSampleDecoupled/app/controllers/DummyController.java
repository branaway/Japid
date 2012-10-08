package controllers;

import cn.bran.play.JapidController2;



public class DummyController extends JapidController2{

	/**
	 * curl -H "Accept: text/javascript" localhost:9000/DummyController/content
	 * curl -H "Accept: text/xml" localhost:9000/DummyController/content
	 * @author Bing Ran (bing.ran@hotmail.com)
	 */
	public static void content() {
		System.out.println("format detected: " + request.format);	
		renderJapid();
	}
//	
//	public static void contentXml() {
//		request.format = "xml";
//		renderJapid();
//	}
//	
//	public static void contentJs() {
//		request.format = "javascript";
//		renderJapid();
//	}
	
}
