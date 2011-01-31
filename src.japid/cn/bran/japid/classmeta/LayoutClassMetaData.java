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
package cn.bran.japid.classmeta;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import cn.bran.japid.template.JapidTemplateBase;
import cn.bran.japid.template.JapidTemplateBaseStreaming;

public class LayoutClassMetaData extends AbstractTemplateClassMetaData {

	{
		setAbstract(true);
	}
	
	Set<String> getterMethods = new HashSet<String>();

	/**
	 * map the #{get} tag
	 * 
	 * @param string
	 */
	public void get(String string) {
		this.getterMethods.add(string);
	}

	/**
	 * 
	 */
	protected void childLayout() {
		p("\n\tprotected abstract void doLayout();\n");
	}

	/**
	 * #{get "block name" /}
	 * was creating abstract. Now changed to a no operation method stub so
	 * subclass can selectively override the getters in the layout
	 */
	protected void getterSetter() {
		pln();
		for (String key : getterMethods) {
//			p("\t protected abstract void " + key + "();\n");
			p("\t protected void " + key + "() {};\n");
		}
	}

	/**
	 * 
	 */
	protected void layoutMethod() {
		p("\t@Override public void layout() {");
		super.setupTagObjects();
		super.addImplicitVariables();
		// the code to render things.
		p("\t\t" + body);
		p("\t}");
	}

	@Override
	void renderMethod() {
		// no such method in layout
	}

}
