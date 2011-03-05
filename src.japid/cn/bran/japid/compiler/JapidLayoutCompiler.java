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
	protected void startTag(Tag tag) {
		if ("get".equals(tag.tagName)) {
			if (tag.hasBody) {
				throw new RuntimeException("get tag cannot have a body. ");
			}
			String var = tag.args;
			var = var.replace("'", "");
			var = var.replace("\"", "");
			this.cmd.get(var);
			print("\t" + var + "();");
		} else if (DO_LAYOUT.equals(tag.tagName)) {
			print("\tdoLayout();");
		} else {
			regularTagInvoke(tag);
		}
		pushToStack(tag);
		markLine(parser.getLineNumber());
		println();
		skipLineBreak = true;

	}

	@Override
	protected AbstractTemplateClassMetaData getTemplateClassMetaData() {
		return cmd;
	}
	
	@Override
	protected void scriptline(String token) {
		String line = token; //.trim(); no trim. some tags are space sensitive
		if (JapidAbstractCompiler.startsWithIgnoreSpace(line.trim(), DO_LAYOUT) || line.trim().equals(DO_LAYOUT)) {
			println("\tdoLayout();");
			skipLineBreak = true;
		}
		else {
			super.scriptline(token);
		}
	}

}