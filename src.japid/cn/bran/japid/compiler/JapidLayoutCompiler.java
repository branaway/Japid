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
import cn.bran.japid.classmeta.LayoutClassMetaData;

/**
 * Code partly from Play! Framework
 * 
 * specifically for layouts
 * 
 * supprt #{get xxx}, #{doLayout /} will allow param for doLayout later.
 * 
 * 
 */

public class JapidLayoutCompiler extends JapidAbstractCompiler {

	// StringBuilder mainRenderBodySource = new StringBuilder();
	LayoutClassMetaData cmd = new LayoutClassMetaData();

	@Override
	protected void templateArgs() {
		Tag currentTag = this.tagsStack.peek();
		if ("root".equals(currentTag.tagName)) {
			throw new RuntimeException("Layouts don't take script level parameters!");
		}
		super.templateArgs();
	}

	@Override
	protected void startTag() {
		Tag tag = buildTag();

		if ("get".equals(tag.tagName)) {
			if (tag.hasBody) {
				throw new RuntimeException("get tag cannot have a body. not closed?");
			}
			String var = tag.args;
			var = var.replace("'", "");
			var = var.replace("\"", "");
			this.cmd.get(var);
			print("\t" + var + "();");
		} else if ("doLayout".equals(tag.tagName)) {
			print("\tdoLayout();");
		} else if (tag.tagName.equals("invoke")) {
			// invoke an action #{invoke myPackage.MyController.runthis(param1,
			// param2)/}
			if (tag.hasBody) {
				throw new JapidCompilationException(template, currentLine, "invoke tag cannot have a body. Must be ended with /}");
			}

			this.cmd.setHasActionInvocation();
			String action = tag.args;
			printActionInvocation(action);

			tagsStack.push(tag);
		} else {
			// String tagClassName = "tags." + tagName + "_html";

			// // collect tab invocation body and embed an innner class
			// tag.innerClassName = tagName + tagIndex + "_Body";
			// print("new " + tagClassName + "().render(" + tagArgs + ", new " +
			// tag.innerClassName + "());");

			// build something like this: new Display_html().render(frontPost,
			// "home", new Display1_Body());
			// String tagClassName = "tag." + tagName;
			// String bodyInnerClassName = tagClassName.replace('.', '_') +
			// tagIndex + "_Body";
			tag.tagName = tag.tagName;
			if (tag.hasBody) {
				// println("new " + tagClassName + "(getOut()).render(" +
				// tagArgs + ", new " + tagName + tagIndex + "DoBody());");
				println("_" + tag.tagName + tag.tagIndex + ".render(" + tag.args + ", _" + tag.tagName + tag.tagIndex + "DoBody);");
			} else {
				println("_" + tag.tagName + tag.tagIndex + ".render(" + tag.args + ", null);");
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

		Tag tag = tagsStack.pop();
		String lastInStack = tag.tagName;
		if (tagName.equals("")) {
			tagName = lastInStack;
		}
		if (!lastInStack.equals(tagName)) {
			throw new JapidCompilationException(template, tag.startLine, "#{" + tag.tagName + "} is not closed.");
		}

		if ("get".equals(tagName)) {
			// // only support value as tag content as opposed to as attribut:
			// // #{set key}value#{/}
			// String key = tag.args;
			// this.cmd.addSetTag(key, tag.bodyBuffer.toString());
		} else if (tagName.equals("invoke")) {
		} else {
			// // regular tag invocation
			// // the inferface name to create an inner class:
			// TagName_html.DoBody

			if (tag.hasBody) {
				this.cmd.addCallTagBodyInnerClass(tag.tagName, tag.tagIndex, tag.bodyArgsString, tag.bodyBuffer.toString());
			} else if (!"doLayout".equals(tagName)) {
				this.cmd.addCallTagBodyInnerClass(tag.tagName, tag.tagIndex, null, null);
			}

		}
		markLine(tag.startLine);
		println();
		// tagIndex--;
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