package cn.bran.japid.util;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import cn.bran.japid.template.JapidTemplateBase;
import cn.bran.japid.template.RenderResult;

public class ControllerUtilsTest {

	@Test
	public void testRender() {
		RenderResult render;
		
		render = ControllerUtils.render(new Foo(null), "hi", new Integer(12));
		assertNotNull(render);

		render = ControllerUtils.render(new Foo(null), "hi", 12);
		assertNotNull(render);

		render = ControllerUtils.render(new Foo(null), "hi", null);
		assertNotNull(render);
		
		try {
			render = ControllerUtils.render(new Foo2(null), 12);
		} catch (Exception e) {
			System.out.println(e);
		}

		render = ControllerUtils.render(new Foo2(null), new ArrayList<String>());
		assertNotNull(render);

		testSingleNull();

		testEmptyArgs();
		
	}

	/**
	 * 
	 */
	@Test
	public void testSingleNull() {
		RenderResult render;
		// if cast to Object, the null is treated as an argument.
		// Otherwise the varargs is set to null. 
		render = ControllerUtils.render(new Foo2(null), (Object)null);
		assertNotNull(render);
	}

	/**
	 * 
	 */
	@Test
	public void testEmptyArgs() {
		RenderResult render;
		render = ControllerUtils.render(new Bar(null));
		assertNotNull(render);
	}

	static class Foo extends JapidTemplateBase {

		public Foo(StringBuilder out) {
			super(out);
		}

		@Override
		protected void doLayout() {
		}
		
		public RenderResult render(String str, Integer i) {
			return new RenderResult();
		}
		
	}

	static class Foo2 extends Foo{
		public Foo2(StringBuilder out) {
			super(out);
		}

		public RenderResult render(List<String> lists) {
			return new RenderResult();
		}
		
	}

	static class Bar extends Foo{
		public Bar(StringBuilder out) {
			super(out);
		}
		
		public RenderResult render() {
			return new RenderResult();
		}
		
	}
}
