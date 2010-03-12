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
import cn.bran.japid.compiler.JapidAbstractCompiler.Tag;
import cn.bran.japid.template.ActionRunner;
import cn.bran.japid.template.RenderResult;
import cn.bran.play.JapidResult;

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
		Tag tag = buildTag();

		if ("extends".equals(tag.tagName)) {
			String layoutName = tag.args;
			layoutName = layoutName.replace("'", "");
			layoutName = layoutName.replace("\"", "");
			if (layoutName.endsWith(HTML)) {
				layoutName = layoutName.substring(0, layoutName.indexOf(HTML));
			}
			if (layoutName.startsWith("/")) {
				layoutName = layoutName.substring(1);
			}
			this.cmd.superClass = layoutName.replace('/', '.');
		} else if (tag.tagName.equals(DO_BODY)) {
			cmd.doBody(tag.args);
			println("if (body != null)");
			println("\tbody.render(" + tag.args + ");");
			// print to the root space before move one stack up
		} else if ("set".equals(tag.tagName)) {
			// only support value as tag content as opposed to as attribut:
			// #{set key}value#{/}
			if (tag.args.contains(":")) {
				if (tag.hasBody) {
					throw new JapidCompilationException(template, currentLine, "set tag cannot have value in tag and in body");
				} else {
					int i = tag.args.indexOf(":");

					String key = tag.args.substring(0, i).trim().replace("\"", "").replace("'", "");
					String value = tag.args.substring(i + 1);//.replace('\'', '"');
					// cannot assume the data is string! 
//					if (!value.startsWith("\""))
//						value = "\"" + value;
//					if (!value.endsWith("\""))
//						value = value + "\"";
					this.cmd.addSetTag(key, "p(" + value + ");");
				}
			}
		} else if (tag.tagName.equals("invoke")) {
			invokeAction(tag);
		} else if (tag.tagName.equals("def")) {
			def(tag);
		} else {
			regularTagInvoke(tag);
		}
		
		tagsStack.push(tag);
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
		} else	if ("def".equals(tagName)) {
			endDef(tag);
		} else if (tagName.equals(DO_BODY)) {
		} else if (tagName.equals("invoke")) {
		} else if (tagName.equals("extends")) {
		} else {
			endRegularTag(tag);
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