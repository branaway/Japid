/**
 * 
 */
package bran.japid;

/**
 * specifically for callable templates
 * 
 * tags does not extends (why not?) some decisions: flow control is not treated
 * as tags, assume ~{ if (xxx) { }~ ~{}}~ #{tags} with body will be compiled to
 * inner classes /anonymous class#{tags sdfs df /} don't create inner class
 * 
 * -- extends can take parnt.html or a java class direcly.
 */

public class BranTemplateCompiler extends AbstractCompiler {
	private static final String DO_BODY = "doBody";
	// StringBuilder mainRenderBodySource = new StringBuilder();
	TemplateClassMetaData cmd = new TemplateClassMetaData();

	@Override
	protected void templateArgs() {
		Tag currentTag = this.tagsStack.peek();
		String args = parser.getToken();
		currentTag.bodyArgsString = args;
		// if (this.currentInnerClassName == null)
		// // the args are for the whole template
		// this.cmd.renderArgs = args;
		// else
		// this.currentInnerClassArgs = args;
	}

	@Override
	protected void startTag() {
		tagIndex++;
		String tagText = parser.getToken().trim().replaceAll(NEW_LINE, SPACE);
		String tagName = "";
		String tagArgs = "";
		boolean hasBody = !parser.checkNext().endsWith("/");
		if (tagText.indexOf(SPACE) > 0) {
			tagName = tagText.substring(0, tagText.indexOf(SPACE));
			tagArgs = tagText.substring(tagText.indexOf(SPACE) + 1).trim();
			// bran: no names argument
			// if (!tagArgs.matches("^[a-zA-Z0-9]+\\s*:.*$")) {
			// tagArgs = "arg:" + tagArgs;
			// }
			tagArgs = tagArgs.replaceAll("[:]\\s*[@]", ":actionBridge.");
			tagArgs = tagArgs.replaceAll("(\\s)[@]", "$1actionBridge.");
		} else {
			tagName = tagText;
			// tagArgs = ":";
		}

		Tag tag = new Tag();
		tag.tagName = tagName;
		tag.startLine = parser.getLine();
		tag.hasBody = hasBody;
		tag.args = tagArgs;

		if ("extends".equals(tagName)) {
			String layoutName = tagArgs;
			layoutName = layoutName.replace("'", "");
			if (layoutName.endsWith(HTML)) {
				layoutName = layoutName.substring(0, layoutName.indexOf(HTML));
			}
			if (layoutName.startsWith("/")) {
				layoutName = layoutName.substring(1);
			}
			this.cmd.superClass = layoutName.replace('/', '.');
			tagsStack.push(tag);
		} else if (tag.tagName.equals(DO_BODY)) {
			cmd.doBody(tagArgs);
			println("if (body != null)");
			println("\tbody.render(" + tagArgs + ");");
			// print to the root space before move one stack up
			tagsStack.push(tag);
		} else if ("set".equals(tagName)) {
			// only support value as tag content as opposed to as attribut:
			// #{set key}value#{/}
			// wait until end of tag
			tagsStack.push(tag);
		} else {
			// invoke a tag
			// String tagClassName = tagName;
			// String bodyInnerClassName = tagClassName.replace('.', '_') +
			// tagIndex + "_Body";
			tag.tagName = tagName;
			if (hasBody) {
				// old way: create a new instance for each call
				// println("new " + tagClassName + "(getOut()).render(" +
				// tagArgs + ", new " + tagName + tagIndex + "DoBody());");
				// use a field to call a tag for better performance in case of
				// loop
				// TODO: handle tags with prefix: #{my.tag}
				println("_" + tagName + tagIndex + ".render(" + tagArgs + ", _" + tagName + tagIndex + "DoBody);");
			} else {
				// println("new " + tagClassName + "(getOut()).render(" +
				// tagArgs + ", null);");
				println("_" + tagName + tagIndex + ".render(" + tagArgs + ");");
			}
			tagsStack.push(tag);
		}
		// XXX other tags
		markLine(parser.getLine());
		println();
		skipLineBreak = true;

	}

	@Override
	protected void endTag() {
		String tagName = parser.getToken().trim();
		if (tagsStack.isEmpty()) {
			throw new JapidCompilationException(template, currentLine, "#{/" + tagName + "} is not opened.");
		}
		BranTemplateCompiler.Tag tag = tagsStack.pop();
		String lastInStack = tag.tagName;
		if (tagName.equals("")) {
			tagName = lastInStack;
		}
		if (!lastInStack.equals(tagName)) {
			throw new JapidCompilationException(template, tag.startLine, "#{" + tag.tagName + "} is not closed.");
		}

		if ("set".equals(tagName)) {
			// only support value as tag content as opposed to as attribut:
			// #{set key}value#{/}
			String key = tag.args;
			this.cmd.addSetTag(key, tag.bodyBuffer.toString());
		} else if (tagName.equals(DO_BODY)) {
		} else if ("extends".equals(tagName)) {
		} else {
			// // regular tag invocation
			// // the inferface name to create an inner class:
			// TagName_html.DoBody

			if (tag.hasBody) {
				this.cmd.addCallTagBodyInnerClass(tag.tagName, tagIndex, tag.bodyArgsString, tag.bodyBuffer.toString());
			} else {
				this.cmd.addCallTagBodyInnerClass(tag.tagName, tagIndex, null, null);
			}

		}
		markLine(tag.startLine);
		println();
		tagIndex--;
		skipLineBreak = true;
	} // Writer

	/**
	 * @param tag
	 */
	@Override
	protected void postParsing(Tag tag) {
		this.cmd.renderArgs = tag.bodyArgsString;
	}

	@Override
	protected AbstractTemplateClassMetaData getTemplateClassMetaData() {
		return cmd;
	}

}