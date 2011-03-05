package cn.bran.japid.template;

import japidviews.cn.bran.japid.template.FooController.foo;

public class FooController extends JapidPlainController{
	public static class ModelUser{
		public String name;

		public ModelUser(String name) {
			this.name = name + "!";
		}
		
		public String what() {
			return ">" + name;
		}
	}
	
	public String a1(String p) {
		return render(p);
	}

	public String foo(String p) {
		return render(p);
	}

	public String bar(String p) {
		return render(foo.class, p);
	}
	
	public String tee(ModelUser u) {
		return render(u);
	}
	
	
}