package controllers;


import japidviews.Application.authorPanel;
import japidviews.Application.composite;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import models.japidsample.Author;
import models.japidsample.Author2;
import models.japidsample.Post;
import notifiers.TestEmailer;
import cn.bran.japid.template.RenderResult;
import cn.bran.play.CacheableRunner;
import cn.bran.play.JapidController;
import cn.bran.play.JapidResult;
/**
 *  a sample controller that demos Japid features
 *  
 * @author Bing Ran<bing_ran@hotmail.com>
 *
 */
public class Application extends JapidController {
	public static void index() {
		renderJapid(); // use the default index.html in the japidviews/SampleController directory
	}
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
	
	public static void hello() {
		renderText("hello，Japid!");
	}
	
	/**
	 * this method shows how to render arguments to a japid template by naming and positional convention with the 
	 * renderJapid().
	 * 
	 */
	public static void renderByPosition() {
		String s = "hello，renderByPosition！";
		int i = 100;
		Author a = new Author();
		a.name = "author1";

		Author2 a2 = new Author2();
		a2.name = "author2";
		
		renderJapid(s, i, a, a2, a2);
	}
	
	public static void renderByPositionEmpty() {
		renderJapid();
	}
	
	/**
	 * demo how to composite a page with independent segments with the #{invoke } tag
	 */
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
	
	public static void reverseLookup0() {
		renderJapid();
	}

	public static void reverseLookup1(String[] args) {
		renderText("OK");
	}
	
	/**
	 * test the japid emailer
	 */
	
	public static void email() {
		Post p = new Post();
		p.title = "我自己";
		TestEmailer.emailme(p);
		renderText("mail sent");
	}
	
	public static void callTag() {
		renderJapidWith("templates/callPicka");
	}
	
	public static void postList() {
		String title = "my Blog";
		List<Post> posts = createPosts();
		renderJapidWith("templates/AllPost", title, posts);
	}
	/**
	 * @return
	 */
	private static List<Post> createPosts() {
		List<Post> posts = new ArrayList<Post>();
		Author a = new Author();
		a.name = "冉兵";
		a.birthDate = new Date();
		a.gender = 'M';
		Post p = new Post();
		p.author = a;
		p.content = "long time ago...";
		p.postedAt = new Date();
		p.title = "post 1";
		posts.add(p);
		p = new Post();
		p.author = a;
		p.content = "way too long time ago...";
		p.postedAt = new Date();
		p.title = "post 2";
		posts.add(p);
		return posts;
	}
	
	public static void each() {
		List<String> list = Arrays.asList("as1", "as2", "as3", "as4", "as5", "as6");
		renderJapidWith("templates/EachCall", list);
	}
	
	/**
	 * test using primitive with renderText
	 * @param i
	 */
	public static void echo(int i) {
		renderText(i * 2);
	}
	
	public static void invokeInLoop() {
		renderJapidWith("templates/invokeInLoop", createPosts());
	}
	
	public static void echoPost(Post p) {
		renderText(p);
	}
}
