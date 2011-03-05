package cn.bran.japid.template;

import static org.junit.Assert.*;

import org.junit.Test;

import cn.bran.japid.compiler.JapidRender;
import cn.bran.japid.template.FooController.ModelUser;

public class JapidPlainControllerTest {
	@Test
	public void testA1() throws InterruptedException {
		String p = "worldy ";
		JapidRender.setTemplateRoot("plainjapid");
//		JapidRender.setProdMode();
		JapidRender.setDevMode();
		JapidRender.setRefreshInterval(1);
		
		int i = 0;
		while (i++ < 50) {
			
			try {
				long t = System.currentTimeMillis();
				String a1 = new FooController().a1(p);
				System.out.println(a1);
				System.out.println("==== took: " + (System.currentTimeMillis()  - t ));
	
				t = System.currentTimeMillis();
//				a1 = new FooController().foo(p);
				a1 = new FooController().bar(p);
				System.out.println(a1);
				
				long x = System.currentTimeMillis() - t;
				System.out.println("--------> took time(ms): " + x);
				
				a1 = new FooController().tee(new ModelUser("me and you"));
				System.out.println(a1);
			} catch (Exception e) {
				e.printStackTrace();
//				System.out.println(e);
			}
			Thread.sleep(3000);
		}		
	}
}
