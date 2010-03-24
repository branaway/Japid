package cn.bran.play;

import java.util.Collection;
import java.util.Date;

import org.apache.commons.lang.time.FastDateFormat;

public class WebUtils {
    public static String fastformat(Date date, String pattern) {
    	return FastDateFormat.getInstance(pattern).format(date);
    }
    

	// Note, the {@code JavaExtensions} class in Play! has lots of formatting methods
	public static String numOf(Collection c) {
		if (c == null)
			return "no";
		else
			return String.valueOf(c.size());
	}
	
	/**
	 * 
	 * @param o
	 * @return
	 */
	public static boolean asBoolean(Object o) {
		if (o == null)
			return false;
		
		boolean r = false;

		if (o instanceof Boolean) {
			r = (Boolean) o;
		} else if (o instanceof Integer) {
			Integer n = (Integer) o;
			r = n != 0 ? true : false;
		}
		else if (o instanceof Collection){
			Collection col = ((Collection)o);
			if (col.size() > 0)
				return true;
			else
				return false;
		}
		else if (o instanceof String) {
			return ((String)o).trim().length() > 0;
		}
		else if (o instanceof Object[]) {
			return ((Object[])o).length > 0;
		}
		else {
			// TODO more
			r = o != null ? true : false;
		}

		return r;
	}


}
