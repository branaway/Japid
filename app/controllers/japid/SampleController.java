package controllers.japid;

import japidviews.japid.SampleController.authorPanel;
import japidviews.japid.SampleController.composite;

import java.util.Date;

import models.japidsample.Author;
import models.japidsample.Post;
import cn.bran.japid.template.RenderResult;
import cn.bran.play.CacheableRunner;
import cn.bran.play.JapidController;
import cn.bran.play.JapidResult;

public class SampleController extends JapidController {
	public static void authorPanel(final Author a) {
		//  the straight-thru way of rendering
		//		throw new JapidResult(new authorPanel().render(a));
		
		String key = "authorpanel:" + a;
		CacheableRunner r = new CacheableRunner("10s", key) {
			@Override
			protected RenderResult render() {
				return new authorPanel().render(a);
			}
		};
		
		throw new JapidResult(r.run());
//	or 		render(r);
	}
	
	public static void foo() {
		StringBuilder sb = new StringBuilder();
		sb.append("--------------foo() action invoked:Hello foo!");
		RenderResult rr = new RenderResult(null, sb, 0);
		
		throw new JapidResult(rr);
		
//		runWithCache(new ActionRunner() {
//			@Override
//			public RenderResult run() {
//				return new authorPanel().render(a);
//			}
//		}, "10s", a);
	}
	
	public static void composite() {
		Post post = new Post();
		post.title = "test post";
		post.postedAt = new Date();
		post.content = "this is perfect piece of content~!";
		
		Author a = new Author();
		a.name = "me";
		a.birthDate = new Date();
		a.gender = 'm';
		
		post.setAuthor(a);
		
		RenderResult render = new composite().render(post);
		// the render can be cached
		throw new JapidResult(render);
	}
	
	public static void main(String[] args) {
		try {
			composite();
		} catch (JapidResult e) {
			System.out.println(e.content);
		}
	}
}
