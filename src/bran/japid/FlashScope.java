package bran.japid;

public interface FlashScope {
	public static final String SUCCESS = "success"; 
	public static final String ERROR = "error"; 

	public abstract boolean hasSuccess();

	public abstract Object success();

	public abstract void putSuccess(Object message);

	public abstract boolean hasError();

	public abstract Object error();

	public abstract void putError(Object error);

	public abstract Object get(String key);

	public abstract void put(String key, Object val);

}