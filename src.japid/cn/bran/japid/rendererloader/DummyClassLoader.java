package cn.bran.japid.rendererloader;

public class DummyClassLoader extends ClassLoader{
	public DummyClassLoader() {
		super(DummyClassLoader.class.getClassLoader());
	}

	@Override
	protected synchronized Class<?> loadClass(String name, boolean resolve) throws ClassNotFoundException {
		System.out.println("loadclass resolve: " + name + "|" + resolve);
		return super.loadClass(name, resolve);
	}

	@Override
	public Class<?> loadClass(String name) throws ClassNotFoundException {
		System.out.println("loadclass: " + name);
		return super.loadClass(name);
	}

	@Override
	protected Class<?> findClass(String name) throws ClassNotFoundException {
		System.out.println("findclass: " + name);
		return super.findClass(name);
	}

	
}
