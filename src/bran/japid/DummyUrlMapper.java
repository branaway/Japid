package bran.japid;

public class DummyUrlMapper implements UrlMapper{
	
	/**
	 * must be multi-threaded safe
	 */
	@Override
	public String lookup(String action, Object[] args) {
		if (args == null) {
			return action;
		}
		return "dummyaction?id=1";
	}

	/**
	 * must be multi-threaded safe
	 * 
	 */
	@Override
	public String lookupAbs(String action, Object[] args) {
		if (args == null) {
			return "/" + action;
		}
		return "http://dummp/action?id=1";
	}

}
