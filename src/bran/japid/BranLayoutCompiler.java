/**
 * 
 */
package bran.japid;

/**
 * specifically for layouts
 * 
 * supprt #{get xxx}, #{doLayout /} will allow param for doLayout later. 
 * 
 */

public class BranLayoutCompiler extends AbstractCompiler {

	// StringBuilder mainRenderBodySource = new StringBuilder();
	LayoutClassMetaData cmd = new LayoutClassMetaData();
	
	@Override
	protected void templateArgs() {
		Tag currentTag = this.tagsStack.peek();
		if ("root".equals(currentTag.tagName)) {
			throw new RuntimeException("Layouts don't take script level parameters!");
		}
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
			// TODO
//			tagArgs = tagArgs.replaceAll("[:]\\s*[@]", ":actionBridge.");
//			tagArgs = tagArgs.replaceAll("(\\s)[@]", "$1actionBridge.");
		} else {
			tagName = tagText;
			// tagArgs = ":";
		}
		BranLayoutCompiler.Tag tag = new Tag();
		tag.tagName = tagName;
		tag.startLine = parser.getLine();
		tag.hasBody = hasBody;
//		#{tag tagArg | String closureArg...}
		int vertiLine = tagArgs.lastIndexOf('|');
		String closureParamList = "";
		if (vertiLine > 0) {
			tag.args = tagArgs.substring(0, vertiLine).trim();
			closureParamList = tagArgs.substring(vertiLine + 1).trim();
			tag.bodyArgsString  = closureParamList;
		}
		else {
			tag.args = tagArgs;
		}
		
		if ("get".equals(tagName)) {
			if (hasBody) {
				throw new RuntimeException("get tag cannot have a body. not closed?");
			}
			String var = tagArgs;
			var = var.replace("'","");
			var = var.replace("\"","");
			this.cmd.get(var);
			print("\t" + var + "();");
		} else if ("doLayout".equals(tagName)) {
			print("\tdoLayout();");
		}else {
			// String tagClassName = "tags." + tagName + "_html";

			// // collect tab invocation body and embed an innner class
			// tag.innerClassName = tagName + tagIndex + "_Body";
			// print("new " + tagClassName + "().render(" + tagArgs + ", new " +
			// tag.innerClassName + "());");

			// build something like this: new Display_html().render(frontPost,
			// "home", new Display1_Body());
//			String tagClassName = "tag." + tagName;
			// String bodyInnerClassName = tagClassName.replace('.', '_') +
			// tagIndex + "_Body";
			tag.tagName = tagName;
			if (hasBody) {
//				println("new " + tagClassName + "(getOut()).render(" + tagArgs + ", new " + tagName + tagIndex + "DoBody());");
				println("_" + tagName + tagIndex + ".render(" + tag.args + ", _" + tagName + tagIndex + "DoBody);");
			} else {
				println("_" + tagName + tagIndex + ".render(" + tag.args + ", null);");
			}
			
		}
		tagsStack.push(tag);
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
		BranLayoutCompiler.Tag tag = tagsStack.pop();
		String lastInStack = tag.tagName;
		if (tagName.equals("")) {
			tagName = lastInStack;
		}
		if (!lastInStack.equals(tagName)) {
			throw new JapidCompilationException(template, tag.startLine, "#{" + tag.tagName + "} is not closed.");
		}

		if ("get".equals(tagName)) {
//			// only support value as tag content as opposed to as attribut:
//			// #{set key}value#{/}
//			String key = tag.args;
//			this.cmd.addSetTag(key, tag.bodyBuffer.toString());
		} else {
			// // regular tag invocation
			// // the inferface name to create an inner class:
			// TagName_html.DoBody

			if (tag.hasBody) {
				this.cmd.addCallTagBodyInnerClass(tag.tagName, tagIndex, tag.bodyArgsString, tag.bodyBuffer.toString());
			}
			else if (!"doLayout".equals(tagName)){
				this.cmd.addCallTagBodyInnerClass(tag.tagName, tagIndex, null, null);
			}

		}
		markLine(tag.startLine);
		println();
//		tagIndex--;
		skipLineBreak = true;
	} // Writer

	@Override
	protected AbstractTemplateClassMetaData getTemplateClassMetaData() {
		return cmd;
	}

	@Override
	protected void postParsing(Tag tag) {
		// nothing to add
		
	}

}