package cn.bran.japid.util;

public class JapidFlags {
	static enum LogLevel{
		debug(0), info(1), warn(2), error(3);
		LogLevel(int i) {
			this.level = i;
		}
		
		int level;

		/**
		 * @author Bing Ran (bing.ran@gmail.com)
		 * @param debug2
		 * @return
		 */
		public boolean noLowerThan(LogLevel debug2) {
			return level >= debug2.level;
		}
		
	}
	
	static LogLevel logLevel = LogLevel.warn;
	
	public static boolean verbose = true;

	/**
	 * @author Bing Ran (bing.ran@hotmail.com)
	 * @param string
	 */
	public static void log(String string) {
		if (verbose)
			out(string);
	}

	private static void out(String string) {
		if (!string.startsWith("[Japid"))
			string = "[Japid]" + " " + string.trim();
		System.out.println(string);
	}

	/**
	 * @author Bing Ran (bing.ran@gmail.com)
	 * @param string
	 */
	public static void debug(String string) {
		if (LogLevel.debug.noLowerThan(logLevel)) {
			out("[debug] " + string);
		}
	}

	public static void warn(String string) {
		if (LogLevel.warn.noLowerThan(logLevel)) {
			out("[warn] " + string);
		}
	}

	public static void setLogLevel(LogLevel l) {
		out("set log level to " + l);
		logLevel = l;
	}

	/**
	 * @author Bing Ran (bing.ran@gmail.com)
	 * @param string
	 */
	public static void info(String string) {
		if (LogLevel.info.noLowerThan(logLevel)) {
			out("[info] " + string);
		}
	}
	
	public static void error(String string) {
		if (LogLevel.error.noLowerThan(logLevel)) {
			out("[error] " + string);
		}
	}

	public static void setLogLevelDebug() {
		setLogLevel(LogLevel.debug);
	}

	public static void setLogLevelInfo() {
		setLogLevel(LogLevel.info);
	}
	
	public static void setLogLevelWarn() {
		setLogLevel(LogLevel.warn);
	}
	
	public static void setLogLevelError() {
		setLogLevel(LogLevel.error);
	}
	
	
}
