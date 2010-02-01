package bran.japid;

/**
 * mappping action calling notation to URL
 * 
 * @author bran
 *
 */
public interface UrlMapper {
	/**
	 * map an action to a relative url
	 * Note: the method internally need to be multi-threaded safe
	 * @param action
	 * @return
	 */
	String lookup(String action, Object[] args);

	/**
	 * map an action to an absolute url
	 * Note: the method internally need to be multi-threaded safe
	 * 
	 * @param action
	 * @return
	 */
	String lookupAbs(String action, Object[] args);

}
