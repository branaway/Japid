package bran;

import java.util.Date;

import org.apache.commons.lang.time.FastDateFormat;

public class WebUtils {
    public static String format(Date date, String pattern) {
    	return FastDateFormat.getInstance(pattern).format(date);
    }
}
