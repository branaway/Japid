package controllers.japid;

import japidviews.Application.composite;

import java.util.Date;

import models.japidsample.Author;
import models.japidsample.Post;

import org.junit.Test;

import cn.bran.japid.template.RenderResult;

public class CompositeTests {
	@Test public void testCompositeTemplates() {
		final Author a = new Author() {{
			name = "JFK";
			birthDate = new Date();
			gender = 'M';
			
		}};
		
		Post p = new Post() {{
			author = a;
			title = "Post title";
			content = "nice conent";
			postedAt = new Date();
		}};
		
		RenderResult render = new composite().render(p);
		System.out.println(render.getContent().toString());
		
	}
}
