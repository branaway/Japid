package bran.japid;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class SimpleMessageProvider implements MessageProvider {

	Map<String, String> messages  = new ConcurrentHashMap<String, String>() {
		private static final long serialVersionUID = 1L;
	{
			put("login.name", "请输入你的姓名");
			put("cus.name", "用户名是 %s");
	}};
	
	@Override
	public String getMessage(String msgName, Object... args) {
		String template = messages.get(msgName);
		if (template != null) {
			return String.format(template, args);
		}
		else {
			return "?";
		}
	}

}
