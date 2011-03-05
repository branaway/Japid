package cn.bran.japid.rendererloader;

import org.junit.Test;

public class DummyClassLoaderTest {
	

	static class Foo {
		Bar b;
	}
	
	static class Bar {}
	
	@Test
	public void testLoading() throws ClassNotFoundException, InstantiationException, IllegalAccessException {
		DummyClassLoader cl = new DummyClassLoader();
		Thread.currentThread().setContextClassLoader(cl);
		Class<?> lc = cl.loadClass(Foo.class.getName());
		Foo f = (Foo) lc.newInstance();
	}
}
