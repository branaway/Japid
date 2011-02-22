package cn.bran.japid.compiler;

import java.util.ArrayList;
import java.util.List;

public class Tag {
	static final String ROOT_TAGNAME = "_root";
	public String tagName;
	public int startLine;
	public boolean hasBody;
	// bran: put everything in the args tag in it
	public String callbackArgs = null;
	// public StringBuffer bodyBuffer = new StringBuffer(2000);
	// each line contains a line of text in the body of a tag scope.
	List<String> bodyTextList = new ArrayList<String>();
	{
		bodyTextList.add("");
	}
	public String innerClassName;
	public String args = "";
	public int tagIndex;

	public String getBodyText() {
		if (!hasBody)
			return null;
		StringBuffer sb = new StringBuffer(2000);
		for (String s : bodyTextList) {
			sb.append(s).append('\n');
		}
		String string = sb.toString();
		return string.substring(0, string.length() - 1);
	}

	public String getTagVarName() {
		return tagName.replace('.', '_').replace('/', '_');
	}

	public String getBodyVar() {
		return "_" + getTagVarName() + tagIndex + "DoBody";
	}

	public boolean isRoot() {
		return tagName.equals(ROOT_TAGNAME);
	}
	
	public static class TagIf extends Tag {
		public TagIf(String expr, int line) {
			super();
			args = expr;
			tagName = "if";
			super.startLine = line;
			hasBody = true;
		}

	}

	// the "else if" might just be an if
//	public static class TagElseIf extends Tag {
//		public TagElseIf(String expr) {
//			super();
//			args = expr;
//			tagName = "else if";
//		}
//	}
}