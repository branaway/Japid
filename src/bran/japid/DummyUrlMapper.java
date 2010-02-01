package bran.japid;

public class DummyUrlMapper implements UrlMapper{
	
	/**
	 * must be multi-threaded safe
	 */
	@Override
	public String lookup(String action, Object[] ags) {
		return "action?id=1";
	}

	/**
	 * must be multi-threaded safe
	 * 
	 */
	@Override
	public String lookupAbs(String action, Object[] args) {
		return "http://dummp/action?id=1";
	}

}
