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

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import cn.bran.japid.compiler.ExprParser;
import cn.bran.japid.template.ActionRunner;


/**
 * the class meta data for templates that are directly renderable, meaning this
 * is not for Layout template nor for tag template
 * 
 * special:
 * <ul>
 * <li>no dobody</li>
 * <li>can #{extends 'layout.html'}</li>
 * <li>take script params, like in tag template</li>
 * <li>the whole body is wrapped as body(), to be called from layout class</li>
 * <li>support #{set}</li>
 * </ul>
 * ,
 * 
 * 
 * 
 * @author Bing Ran<bing_ran@hotmail.com>
 * 
 */
public class TemplateClassMetaData extends AbstractTemplateClassMetaData {

	public String renderArgs;
	// there are the "#{set var:val /}
	// <methName, methodBody
	Map<String, String> setMethods = new HashMap<String, String>();

	// experiement: allow any template to be callable as a tag and can handle
	// doBody
	// null: no doBody, "": there is doBody but no parameters passed back
	private String doBodyArgsString;
	private char[] doBodyGenericTypeParams = new char[] { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J' };
	//	
	public void addSetTag(String setMethodName, String methodBody) {
		setMethods.put(setMethodName, methodBody);
	}

	@Override
	public String toString() {
		printHeaders();
		// the class layout diffs depending on the extends
		// if there is layout, the render method set up the fields to take the
		// parameters and
		// call the super.layout(), which in turn call the doLayout of this
		// class.
		// otherwise the render method renders everything directly.
		printAnnotations();
		classDeclare();
		embedSourceTemplateName();
//		embedContentType();
		printHttpHeaderMap();
		buildStatics();
		addConstructors();
// the render mthod
		if (renderArgs != null) {
			// create fields for the render args and create a render method to
			ExprParser ep = new ExprParser(this.renderArgs);
			List<String> argTokens = ep.split();
			// something like String a Date b
			assert (argTokens.size() % 2 == 0);

			String[] argNames = new String[argTokens.size() / 2];

			for (int i = 0; i < argNames.length; i++) {
				pln(TAB + argTokens.get(i * 2) + " " + argTokens.get(i * 2 + 1) + ";");
			}

			// set the render(xxx)
			if (doBodyArgsString != null) {
				// the template can be called with a callbacl body
				// the field
				pln (TAB + "DoBody body;" );
				doBodyInterface();
				pln("\tpublic " + RENDER_RESULT + " render(" + renderArgs + ", DoBody body) {");
				pln("\t\t" + "this.body = body;");
			} else {
				pln("\tpublic " + RENDER_RESULT + " render(" + renderArgs + ") {");
			}
			// assign the params to fields
			for (int i = 0; i < argNames.length; i++) {
				argNames[i] = argTokens.get(2 * i + 1);
			}
			for (String arg : argNames) {
				pln("\t\tthis." + arg + " = " + arg + ";");
			}
		}
		else {
			if (doBodyArgsString != null) {
				// the field
				pln (TAB + "DoBody body;" );
				doBodyInterface();
				pln("\tpublic " + RENDER_RESULT + " render(DoBody body) {");
				pln("\t\t" + "this.body = body;");
			} else {
				pln("\tpublic " + RENDER_RESULT + " render() {");
			}
		}
		pln("\t\tlong t = -1;");
		if (stopWatch)
			pln("\t\tt = System.currentTimeMillis();");
		pln("\t\tsuper.layout();");
		if (stopWatch) {
			pln("\t\tt = System.currentTimeMillis() - t;");
			pln("\t\tSystem.out.println(\"[" + super.className + "] rendering time: \" + t);");
		}

		
		if (streaming) {
			if (true || hasActionInvocation) 
				pln("\t\treturn new " + RENDER_RESULT_PARTIAL + "(this.headers, null, t, " + ACTION_RUNNERS + ");");
			else 
				pln("\t\treturn new " + RENDER_RESULT + "(this.headers, null, t);");
				
		}
		else {
			if (true || hasActionInvocation) 
				pln("\t\treturn new " + RENDER_RESULT_PARTIAL + "(this.headers, getOut(), t, " + ACTION_RUNNERS + ");");
			else 
				pln("\t\treturn new " + RENDER_RESULT + "(this.headers, getOut(), t);");
		}
		pln("\t}");

		
		// doLayout body
		pln(TAB + "@Override protected void doLayout() {");
		addImplicitVariables();
		pln(super.body);
		pln("\t}");
		// #{set } tags
		//
		for (Entry<String, String> en : setMethods.entrySet()) {
			String meth = en.getKey();
			String setBody = en.getValue();
			pln("\t@Override protected void " + meth + "() {");
			pln("\t\t" + setBody + ";");
			pln("\t}");
		}

		callTags();
		super.processDefTags();
		
		pln("}");

		return sb.toString();
	}

	// code copied from the TagClassMetaData
	private void doBodyInterface() {
		// let do the dobody callback interface
		// doBody interface:
		if (doBodyArgsString != null && doBodyArgsString.length() > 0) {
			ExprParser p = new ExprParser(doBodyArgsString);
			List<String> argTokens = p.split();
			// int numOfArgs = argTokens.size() /2;
			String genericTypeList = "";
			String renderArgList = "";
			for (int i = 0; i < argTokens.size(); i++) {
				char c = doBodyGenericTypeParams[i];
				genericTypeList += "," + c;
				renderArgList += "," + c + " " + Character.toLowerCase(c);
			}
			genericTypeList = genericTypeList.substring(1); // remove the
			// first
			// comma
			renderArgList = renderArgList.substring(1); // remove the first
			// comma
			pln("\tpublic static interface DoBody<", genericTypeList, "> {");
			pln("\t\t void render(" + renderArgList + ");");
			pln("\t}");
		} 
	}

	// for the doBody in the tag template
	public void addDoBodyInterface(String bodyArgsString) {
		this.doBodyArgsString = bodyArgsString;
	}

	public void doBody(String tagArgs) {

		tagArgs = tagArgs == null ? "" : tagArgs;
		this.addDoBodyInterface(tagArgs);
	}

	public void addDefTag(String key, String string) {
		// TODO Auto-generated method stub
		
	}

}
