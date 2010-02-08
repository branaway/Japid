package cn.bran.japid.util;

import java.util.HashMap;
import java.util.Map;


public class FlashScopeGeneric implements FlashScope {
	Map<String, Object> store = new HashMap<String, Object>();
	/* (non-Javadoc)
	 * @see bran.japid.Flash#hasSuccess()
	 */
	public boolean hasSuccess() {
		return store.containsKey(SUCCESS);
	}

	/* (non-Javadoc)
	 * @see bran.japid.Flash#getSuccess()
	 */
	public Object success() {
		return store.get(SUCCESS);
	}

	/* (non-Javadoc)
	 * @see bran.japid.Flash#putSuccess(java.lang.Object)
	 */
	public void putSuccess(Object message) {
		store.put(SUCCESS, message);
	}

	/* (non-Javadoc)
	 * @see bran.japid.Flash#hasError()
	 */
	public boolean hasError() {
		return store.containsKey(ERROR);
	}
	
	/* (non-Javadoc)
	 * @see bran.japid.Flash#getError()
	 */
	public Object error() {
		return store.get(ERROR);
	}

	/* (non-Javadoc)
	 * @see bran.japid.Flash#putError(java.lang.Object)
	 */
	public void putError(Object error) {
		store.put(ERROR, error);
	}
	
	/* (non-Javadoc)
	 * @see bran.japid.Flash#get(java.lang.String)
	 */
	public Object get(String key) {
		return store.get(key);
	}
	/* (non-Javadoc)
	 * @see bran.japid.Flash#put(java.lang.String, java.lang.Object)
	 */
	public void put(String key, Object val) {
		store.put(key, val);
	}
}
