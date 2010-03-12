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
import java.util.List;


public class LayoutClassMetaData  extends AbstractTemplateClassMetaData {

	List<String> getterMethods = new ArrayList<String>();
//	public String renderBody;

	@Override
	public String toString() {
		printHeaders();
		printAnnotations();
		classDeclare();
		embedSourceTemplateName();
		printHttpHeaderMap();
		buildStatics();
		addConstructors();
		
		p("\t@Override public void layout() {");
		p("\t\t" + body);
		p("\t}");
		
		for (String key : getterMethods) {
			p("\tprotected abstract void " + key + "();\n");
		}
		
		p("\tprotected abstract void doLayout();\n");

		callTags();
		super.processDefTags();
		
		p("}");

		return sb.toString();
	}

	// map the #{get} tag
	public void get(String string) {
		this.getterMethods.add(string);
		
	}
	
}
