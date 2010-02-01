package bran.japid;

public class JapidCompilationException extends RuntimeException {
	BranTemplate template;
	int startLine;
	
	public JapidCompilationException(BranTemplate template, int startLine, String string) {
		super(string);
		this.template = template;
		this.startLine = startLine;
	}

}
