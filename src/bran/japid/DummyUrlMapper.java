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

	@Override
	public String lookupStatic(String resource) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String lookupStaticAbs(String resource) {
		// TODO Auto-generated method stub
		return null;
	}

}
