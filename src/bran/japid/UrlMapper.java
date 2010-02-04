package bran.japid;

/**
 * mappping action calling notation to URL
 * 
 * @author bran
 * 
 */
public interface UrlMapper {

	/**
	 * map an action to a relative url Note: the method internally need to be
	 * multi-threaded safe
	 * 
	 * @param action
	 *            name of static resource or action name, depending the args
	 *            param
	 * @param args
	 *            null indicates this is a static resource lookup, otherwise
	 *            it's an action lookup
	 * @return
	 */
	String lookup(String action, Object[] args);

	/**
	 * map an action to an absolute url Note: the method internally need to be
	 * multi-threaded safe
	 * 
	 * @param action
	 * @return
	 */
	String lookupAbs(String action, Object[] args);

	/**
	 * lookup statics
	 * 
	 * @param resource
	 * @return
	 */
	String lookupStatic(String resource);
	String lookupStaticAbs(String resource);
	

}
