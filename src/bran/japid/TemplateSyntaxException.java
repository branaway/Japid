package bran.japid;

public class TemplateSyntaxException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	String fileName;
	String hotspot;
	int line;
	
	public TemplateSyntaxException(String msg, String fileName, String hotspot, int line) {
		super(msg);
		this.fileName = fileName;
		this.hotspot = hotspot;
		this.line = line;
	}
	
}
