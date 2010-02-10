/**
 * Copyright 2010 Bing Ran<bing_ran@hotmail.com> 
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not 
 * use this file except in compliance with the License. You may obtain a copy 
 * of the License at http://www.apache.org/licenses/LICENSE-2.0.
 * 
 * Unless required by applicable law or agreed to in writing, software 
 * distributed under the License is distributed on an "AS IS" BASIS, 
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. 
 * See the License for the specific language governing permissions and 
 * limitations under the License. 
 */
package cn.bran.japid.compiler;

import cn.bran.japid.classmeta.AbstractTemplateClassMetaData;
import cn.bran.japid.classmeta.TemplateClassMetaData;

/**
 * specifically for callable templates
 * 
 * tags does not extends (why not?) some decisions: flow control is not treated
 * as tags, assume ~{ if (xxx) { }~ ~{}}~ #{tags} with body will be compiled to
 * inner classes /anonymous class#{tags sdfs df /} don't create inner class
 * 
 * -- extends can take parnt.html or a java class direcly.
 * @author Bing Ran<bing_ran@hotmail.com>
 * @author Play! framework original authors
 */

public class JapidTemplateCompiler extends JapidAbstractCompiler {
	private static final String DO_BODY = "doBody";
	// StringBuilder mainRenderBodySource = new StringBuilder();
	TemplateClassMetaData cmd = new TemplateClassMetaData();

	@Override
	protected void startTag() {
		tagIndex++;
		String tagText = parser.getToken().trim().replaceAll(NEW_LINE, SPACE);
		String tagName = "";
		String tagArgs = "";
		boolean hasBody = !parser.checkNext().endsWith("/");
		if (tagText.indexOf(SPACE) > 0) {
			tagName = tagText.substring(0, tagText.indexOf(SPACE));
			tagArgs = tagText.substring(tagText.indexOf(SPACE) + 1).trim().replace('\'', '"');
			// bran: no named argument
			// if (!tagArgs.matches("^[a-zA-Z0-9]+\\s*:.*$")) {
			// tagArgs = "arg:" + tagArgs;
			// }

			// TODO handle the action reverse lookup
			// tagArgs = tagArgs.replaceAll("[:]\\s*[@]", ":actionBridge.");
			// tagArgs = tagArgs.replaceAll("(\\s)[@]", "$1actionBridge.");
		} else {
			tagName = tagText;
			// tagArgs = ":";
		}

		Tag tag = new Tag();
		tag.tagName = tagName;
		tag.startLine = parser.getLine();
		tag.hasBody = hasBody;
		tag.tagIndex = tagIndex++;

		// #{tag tagArg | String closureArg...}
		int vertiLine = tagArgs.lastIndexOf('|');
		String closureParamList = "";
		if (vertiLine > 0) {
			tag.args = tagArgs.substring(0, vertiLine).trim();
			closureParamList = tagArgs.substring(vertiLine + 1).trim();
			tag.bodyArgsString = closureParamList;
		} else {
			tag.args = tagArgs;
		}

		if ("extends".equals(tagName)) {
			String layoutName = tagArgs;
			layoutName = layoutName.replace("'", "");
			layoutName = layoutName.replace("\"", "");
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
			if (tagArgs.contains(":")) {
				if (hasBody) {
					throw new JapidCompilationException(template, currentLine, "set tag cannot have value in tag and in body");
				} else {
					int i = tagArgs.indexOf(":");

					String key = tagArgs.substring(0, i).trim().replace("\"", "").replace("'", "");
					String value = tagArgs.substring(i + 1);//.replace('\'', '"');
					// cannot assume the data is string! 
//					if (!value.startsWith("\""))
//						value = "\"" + value;
//					if (!value.endsWith("\""))
//						value = value + "\"";
					this.cmd.addSetTag(key, "p(" + value + ");");
				}
			}
			// wait until end of tag
			tagsStack.push(tag);
		} else {
			// invoke a tag
			if (hasBody) {
				// old way: create a new instance for each call
				// println("new " + tagClassName + "(getOut()).render(" +
				// tagArgs + ", new " + tagName + tagIndex + "DoBody());");
				// use a field to call a tag for better performance in case of
				// loop
				// TODO: handle tags with prefix: #{my.tag}
				println("_" + tagName + tag.tagIndex + ".render(" + tag.args + ", _" + tagName + tag.tagIndex + "DoBody);");
			} else {
				// println("new " + tagClassName + "(getOut()).render(" +
				// tagArgs + ", null);");
				println("_" + tagName + tag.tagIndex + ".render(" + tag.args + ");");
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
		Tag tag = tagsStack.pop();
		String lastInStack = tag.tagName;
		if (tagName.equals("")) {
			tagName = lastInStack;
		}
		if (!lastInStack.equals(tagName)) {
			throw new JapidCompilationException(template, tag.startLine, "#{" + tag.tagName + "} is not closed.");
		}

		if ("set".equals(tagName)) {
			if (tag.hasBody) {
				String key = tag.args;
				this.cmd.addSetTag(key, tag.bodyBuffer.toString());
			}
		} else if (tagName.equals(DO_BODY)) {
		} else if ("extends".equals(tagName)) {
		} else {
			// // regular tag invocation
			// // the inferface name to create an inner class:
			// TagName_html.DoBody

			if (tag.hasBody) {
				this.cmd.addCallTagBodyInnerClass(tag.tagName, tag.tagIndex, tag.bodyArgsString, tag.bodyBuffer.toString());
			} else {
				this.cmd.addCallTagBodyInnerClass(tag.tagName, tag.tagIndex, null, null);
			}

		}
		markLine(tag.startLine);
		println();
		// tagIndex--;
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