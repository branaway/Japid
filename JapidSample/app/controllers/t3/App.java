package controllers.t3;

import japidviews.Application.authorPanel;
import japidviews._javatags.JapidWebUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import controllers.more.BaseController;

import play.cache.CacheFor;
import play.mvc.Before;

import models.SearchParams;
import models.japidsample.Author;
import models.japidsample.Author2;
import models.japidsample.Post;
import notifiers.TestEmailer;
import cn.bran.japid.template.RenderResult;
import cn.bran.play.CacheableRunner;
import cn.bran.play.JapidController;
import cn.bran.play.JapidResult;
public class App extends JapidController {
	public static void foo() {
		renderText("hi foo  ");
	}
}
