package bran.japid;

public interface MessageProvider {
	String getMessage(String msgName, Object... args);
}
