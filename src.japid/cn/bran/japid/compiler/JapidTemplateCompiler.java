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

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import cn.bran.japid.classmeta.AbstractTemplateClassMetaData;
import cn.bran.japid.classmeta.TemplateClassMetaData;
import cn.bran.japid.template.ActionRunner;
import cn.bran.japid.template.RenderResult;

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
	TemplateClassMetaData tcmd = new TemplateClassMetaData();

	@Override
	protected void startTag(Tag tag) {
		
		if (tag.tagName.equals(DO_BODY)) {
			tcmd.doBody(tag.args);
			println("if (body != null)");
			println("\tbody.render(" + tag.args + ");");
			// print to the root space before move one stack up
		} else if ("set".equals(tag.tagName)) {
			// only support value as tag content as opposed to as attribute:
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
					this.tcmd.addSetTag(key, "p(" + value + ");");
				}
			}
			else {
				Matcher matcher = setPattern.matcher(tag.args);
				if (matcher.matches()) {
					tag.hasBody = false;
					String key = matcher.group(1);
					String value = matcher.group(2);
					this.tcmd.addSetTag(key, "p(" + value + ");");
				}
			}
		} else if (tag.tagName.equals("def")) {
			def(tag);
		} else {
			regularTagInvoke(tag);
		}
		
		pushToStack(tag);
		markLine(parser.getLineNumber());
		println();
		skipLineBreak = true;

	}
	
	static Pattern setPattern = Pattern.compile("(\\w+)\\s+(.*)");
	
	@Override
	protected boolean endTagSpecial(Tag tag) {
		if ("set".equals(tag.tagName)) {
			if (tag.hasBody) {
				String key = tag.args;
				this.tcmd.addSetTag(key, tag.getBodyText());
				return true;
			}
		}
		return false;
	}

	@Override
	protected AbstractTemplateClassMetaData getTemplateClassMetaData() {
		return tcmd;
	}
	
	@Override
	protected void scriptline(String token) {
		String line = token;//.trim(); don't trim `a `t is sensitive to the space
		if (JapidAbstractCompiler.startsWithIgnoreSpace(line.trim(), DO_BODY) || line.trim().equals(DO_BODY)) {
			String args = line.trim().substring(DO_BODY.length()).trim();
			tcmd.doBody(args);
			println("if (body != null)");
			println("\tbody.render(" + args + ");");
			skipLineBreak = true;
		}
		else {
			super.scriptline(token);
		}
	}

}