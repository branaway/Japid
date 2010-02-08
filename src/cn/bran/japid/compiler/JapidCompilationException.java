package cn.bran.japid.compiler;

import cn.bran.japid.template.JapidTemplate;

public class JapidCompilationException extends RuntimeException {
	JapidTemplate template;
	int startLine;
	
	public JapidCompilationException(JapidTemplate template, int startLine, String string) {
		super(string);
		this.template = template;
		this.startLine = startLine;
	}

}
