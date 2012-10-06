package cn.bran.japid.util;

public class JapidFlags {

	public static boolean verbose = true;

	/**
	 * @author Bing Ran (bing.ran@hotmail.com)
	 * @param string
	 */
	public static void log(String string) {
		if (verbose)
			System.out.println(string);
	}

}
