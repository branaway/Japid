package cn.bran.japid.compiler;

import java.util.List;


/**
 * parse #{tag (t, v) | U u }
 * 
 * @author Bing Ran<bing_ran@hotmail.com>
 * 
 */
public class TagInvocationLineParser {

	public Tag parse(String line) {
		String original = line;
		Tag tag = new Tag();
		for (int i = 0; i < line.length(); i++) {
			char c = line.charAt(i);
			if (Character.isJavaIdentifierPart(c) || c == '.' || c == '/') {
				continue;
			} else {
				if (Character.isWhitespace(c) || c == '(' || c == '|') {
					tag.tagName = line.substring(0, i).replace('/', '.');
					if (tag.tagName.startsWith(".."))
						tag.tagName = tag.tagName.substring(1);
					line = line.substring(i).trim();
					break;
				} else {
					throw new RuntimeException(" tag invocation syntax error: " + c);
				}
			}
		}
		if (tag.tagName == null) {
			// there was no end char in the line. so the whole line is the tag
			// name
			tag.tagName = line.replace('/', '.');
			return tag;
		}

		// let's parse the closure params
		int vertline = line.lastIndexOf('|');
		if (vertline >= 0) {
			String closureArgs = line.substring(vertline + 1).trim();
			// test syntax 
			JavaSyntaxTool.parseParams(closureArgs);
			tag.callbackArgs = closureArgs;
			tag.hasBody = true;
			line = line.substring(0, vertline).trim();
		}

		if (line.length() == 0)
			return tag;

		// now args.
		char firstC = line.charAt(0);
		char lastC = line.charAt(line.length() - 1);
		if ('(' == firstC) {
			if (')' != lastC) {
				throw new RuntimeException("The tag argument part is not valid");
			} else {
				tag.args = line.substring(1, line.length() - 1);
			}
		} else {
			tag.args = line;
		}

		return tag;
	}
}
