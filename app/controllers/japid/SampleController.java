package controllers.japid;

import japidviews.japid.SampleController.authorPanel;
import japidviews.japid.SampleController.composite;

import java.util.Date;

import cn.bran.Author;
import cn.bran.Post;
import cn.bran.japid.template.ActionRunner;
import cn.bran.japid.template.RenderResult;
import cn.bran.play.JapidController;
import cn.bran.play.JapidResult;

public class SampleController extends JapidController {
	public static void authorPanel(final Author a) {
		throw new JapidResult(new authorPanel().render(a));
		
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
