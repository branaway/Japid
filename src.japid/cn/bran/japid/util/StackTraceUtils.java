package cn.bran.japid.util;

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

}
