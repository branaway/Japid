package cn.bran.japid.compiler;

import static org.junit.Assert.*;
import japidviews.myapp.aview;

import org.junit.Test;

public class JapidRenderTest {
	@Test
	public void testGen() {
		JapidRender.setTemplateRoot("plainjapid");
		JapidRender.gen();
	}

	@Test
	public void testAview() {
		String render = new JapidRender().render(aview.class, "world");
		System.out.println(render);
	}
}
