package cn.bran.japid.template;

import static org.junit.Assert.assertEquals;

import java.io.IOException;

import org.junit.Test;

import cn.bran.japid.compiler.OpMode;

public class JapidRenderTest {

	@Test
	public void testGen() {
		JapidRenderer.init(null, "plainjapid", 1);
		JapidRenderer.gen();
	}

	@Test
	public void testReGen() throws IOException {
		JapidRenderer.init(null, "plainjapid", 1);
		JapidRenderer.regen();
	}
//
//	@Test
//	public void testAview() {
//		JapidRenderer.init(OpMode.dev, "plainjapid", 1);
//		String render = new JapidRenderer().render(aview.class, "world");
//		System.out.println(render);
//	}

	@Test
	public void testSmartBindingWithRender() {
		final String UNI = "universe";
		JapidRenderer.init(OpMode.prod, "plainjapid", 1);
		String r = new FooControllerBare().a1(UNI);
		System.out.println(r);;
		assertEquals(">" + UNI, r);
	}

	@Test
	public void testRenderSuperMethod() {
		final String UNI = "universe";
		JapidRenderer.init(OpMode.dev, "plainjapid", 1);
		String r = new FooController().a1(UNI);
		System.out.println(r);;
//		assertEquals(">" + UNI, r);
	}

	@Test
	public void testExplicit() {
		final String UNI = "universe";
		JapidRenderer.init(OpMode.prod, "plainjapid", 1);
		String r = new FooControllerBare().a2(UNI);
		System.out.println(r);;
		assertEquals(">" + UNI, r);
	}
}
