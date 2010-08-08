package cn.bran.japid.util;

//import static org.junit.Assert.*;
//
//import org.junit.Test;

public class StackTraceUtils {

	public static String getCaller() {
		int depth = 3;
		return getCaller(depth);
	}

	public static String getCaller2() {
		int depth = 4;
		return getCaller(depth);
	}
	
	public static String getCaller3() {
		int depth = 5;
		return getCaller(depth);
	}
	
	/**
	 * @param depth
	 * @return
	 */
	public static String getCaller(int depth) {
		final StackTraceElement[] ste = new Throwable().getStackTrace();
		StackTraceElement st = ste[depth];
		return st.getClassName() + "." + st.getMethodName();
	}
	
	public static String getCurrentMethodFullName() {
		return getCaller(2);
	}

//	@Test
//	public void testCaller() {
//		String caller = getCaller(1);
//		Class<StackTraceUtils> class1 = StackTraceUtils.class;
//		String methodFullName = class1.getName() + ".testCaller";
//		assertEquals (methodFullName, caller);
//		caller = getCurrentMethodFullName();
//		assertEquals (methodFullName, caller);
//	}
}
