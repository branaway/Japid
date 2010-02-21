package cn.bran.japid.util;

import static org.junit.Assert.*;

import org.junit.Test;

public class StackTraceUtilsTest {
	@Test public void testCallerInfo() {
		String r = foo();
		assertEquals(StackTraceUtilsTest.class.getName() + ".foo", r );
		r = bar2();
		String thisCaller = StackTraceUtilsTest.class.getName() + ".testCallerInfo";
		assertEquals(thisCaller, r );
		assertEquals(thisCaller, foo1() );
	}
	
	private String foo() {
		return bar();
	}

	private String bar() {
		return StackTraceUtils.getCaller();
	}

	private String bar2() {
		return StackTraceUtils.getCaller(2);
	}
	
	private String foo1() {
		return foo2();
	}

	private String foo2() {
		return StackTraceUtils.getCaller2();
	}
	
	/**
	 * it shows that each call is about 44us.
	 */
	@Test public void testPerformance() {
		char c =' ';
		
		// warm up
		for (int i = 0; i < 10; i++) {
			String m = StackTraceUtils.getCaller2();
			c = m.charAt(0);
		}

		long t = System.currentTimeMillis();
		int runs = 1000;
		for (int i = 0; i < runs; i++) {
			String m = StackTraceUtils.getCaller2();
			c = m.charAt(0);
		}
		System.out.println("runs " + runs + " took/ms: " + (System.currentTimeMillis() - t));
	}
}
