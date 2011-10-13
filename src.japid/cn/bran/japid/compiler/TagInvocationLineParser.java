package cn.bran.japid.compiler;

import japa.parser.ast.body.Parameter;

import java.util.List;

import cn.bran.japid.util.StringUtils;

/**
 * parse #{tag (t, v) | U u }
 * 
 * @author Bing Ran<bing_ran@hotmail.com>
 * 
 */
public class TagInvocationLineParser {

	/**
	 * TODO: use stricter syntax checker. The callback border symbol is not
	 * strictly picked up based on Java grammar, etc.
	 * 
	 * @param line
	 * @return
	 */
	public Tag parse(String line) {
		// String original = line;
		Tag tag = new Tag();

		// get tag name
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
					throw new RuntimeException(
							"invalid character in the tag invocation line "
									+ line + ": [" + c + "]");
				}
			}
		}
		if (tag.tagName == null) {
			// there was no end char in the line. so the whole line is the tag
			// name
			tag.tagName = line.replace('/', '.');
			return tag;
		}

		if (tag.tagName.equals("def")) {
			tag = new Tag.TagDef();
			if (!line.endsWith(")"))
				line += "()";
			JavaSyntaxTool.isValidMethDecl(line);
			tag.args = line;
			return tag;
		} else if (tag.tagName.equals("set")) {
			tag = new Tag.TagSet();
			tag.args = line;
			return tag;
		} else if (tag.tagName.equals("get")) {
			tag.args = line;
			return tag;
		}

		// let's parse the closure params
		try {
			List<String> args = JavaSyntaxTool.parseArgs(line);
			tag.args = StringUtils.join(args, ",");
			// // might miss a wrong calling syntax: a, b|c, where the user
			// might intend a, b | String c
			// // let's check that
			// int vertline = line.lastIndexOf('|');
			// if (vertline < 0) {
			// tag.args = line;
			// }
			// else {
			// String closureArgs = line.substring(vertline + 1).trim();
			// List<Parameter> params = JavaSyntaxTool.parseParams(closureArgs);
			// }

			// valid already, there is no callback body
			parseNamedArgs(tag);
		} catch (RuntimeException e) {
			// ok let's check for callback

			int vertline = line.lastIndexOf('|');
			if (vertline >= 0) {
				String closureArgs = line.substring(vertline + 1).trim();
				// test syntax
				JavaSyntaxTool.parseParams(closureArgs);
				tag.callbackArgs = closureArgs;
				tag.hasBody = true;
				line = line.substring(0, vertline).trim();

				if (line.length() == 0)
					return tag;
				else {
					// parse args.
					char firstC = line.charAt(0);
					char lastC = line.charAt(line.length() - 1);
					if ('(' == firstC) {
						if (')' != lastC) {
							throw new RuntimeException(
									"The tag argument part is not valid: parenthesis is not paired.");
						} else {
							tag.args = line.substring(1, line.length() - 1);
						}
					} else {
						tag.args = line;
					}

					parseNamedArgs(tag);
				}
			} else
				throw new RuntimeException("tag args not valid: " + line);

		}

		return tag;
	}

	private void parseNamedArgs(Tag tag) {
		// check for grammar and named arguments
		List<NamedArg> args = JavaSyntaxTool.parseNamedArgs(tag.args);
		if (args.size() > 0) {
			tag.namedArgs = args;
			// let reformat the args to named("a1", a), named("a2", 12), etc
			String ar = "";
			for (NamedArg na : args) {
				ar += na.toNamed() + ", ";
			}
			if (ar.endsWith(", ")) {
				ar = ar.substring(0, ar.length() - 2);
			}
			tag.args = ar;
		}
	}
}
