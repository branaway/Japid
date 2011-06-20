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

import japa.parser.ast.body.Parameter;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import cn.bran.japid.compiler.JavaSyntaxTool;
import cn.bran.japid.compiler.NamedArgRuntime;
import cn.bran.japid.compiler.Tag;
import cn.bran.japid.compiler.Tag.TagSet;

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

	// there are the "#{set var:val /}
	// <methName, methodBody
	Map<String, String> setMethods = new HashMap<String, String>();
	Map<String, TagSet> setTags = new HashMap<String, TagSet>();

	// Experiment: allow any template to be callable as a tag and can handle
	// doBody
	// null: no doBody, "": there is doBody but no parameters passed back
	private String doBodyArgsString;
	private char[] doBodyGenericTypeParams = new char[] { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J' };

	//
	public void addSetTag(String setMethodName, String methodBody, TagSet tag) {
		setMethods.put(setMethodName, methodBody);
		setTags.put(setMethodName, tag);
	}

	// for the doBody in the tag template
	public void addDoBodyInterface(String bodyArgsString) {
		this.doBodyArgsString = bodyArgsString;
	}

	public void doBody(String tagArgs) {
		tagArgs = tagArgs == null ? "" : tagArgs.trim();
		this.addDoBodyInterface(tagArgs);
	}

	// public void addDefTag(String key, String string) {
	// // TODO Auto-generated method stub
	// }

	/**
	 * 
	 */
	@Override
	protected void getterSetter() {
		// #{set "block name"} tags
		pln();
		for (Entry<String, String> en : setMethods.entrySet()) {
			String meth = en.getKey();
			String setBody = en.getValue();
			pln("\t@Override protected void " + meth + "() {");
			// local tag defs
			TagSet set = setTags.get(meth);
			for (Tag t: set.tags) {
				declareTagInstance(t);
			}
			pln("\t\t" + setBody + ";");
			pln("\t}");
		}
	}

	/**
	 * the main part of the render logic
	 */
	protected void layoutMethod() {
		// doLayout body
		pln(TAB + "@Override protected void doLayout() {");
		super.setupTagObjectsAsFields();
//		super.addImplicitVariables(); // move to fields
		pln("//------");
		pln(super.body);
		pln("\t}");
	}

	/**
	 * the entry point of the template: render(...). Concrete views have this
	 * method while the layouts do not.
	 */
	protected void renderMethod() {
		String resultType = useWithPlay? RENDER_RESULT : "String";

		String paramNameArray = "";
		String paramTypeArray = "";
		String paramDefaultsArray = "";
		String currentClassFQN = (this.packageName == null ? "":  this.packageName + ".") + this.className;

		if (renderArgs != null) {
			// create fields for the render args and create a render method to
			List<Parameter> params = JavaSyntaxTool.parseParams(this.renderArgs);

			String renderArgsWithoutAnnos = "";
			/// named param stuff
			for (Parameter p: params) {
				paramNameArray  += "\"" + p.getId() + "\", ";
				paramTypeArray  += "\"" + p.getType() + "\", ";
				String defa = JavaSyntaxTool.getDefault(p);
				paramDefaultsArray += defa + ",";
				renderArgsWithoutAnnos += p.getType() + " " + p.getId() + ",";
			}
			if (renderArgsWithoutAnnos.endsWith(",")){
				renderArgsWithoutAnnos = renderArgsWithoutAnnos.substring(0, renderArgsWithoutAnnos.length() - 1);
			}
			String nameParamCode = String.format(NAMED_PARAM_CODE, paramNameArray, paramTypeArray, paramDefaultsArray, currentClassFQN);
			pln(nameParamCode);
			///
			
			for (Parameter p : params) {
				addField(p);
			}

			// set the render(xxx)
			if (doBodyArgsString != null) {
				pln(NAMED_PARAM_WITH_BODY);
				// the template can be called with a callback body
				// the field
				pln(TAB + "private DoBody body;");
				doBodyInterface();
				pln("\tpublic " + resultType + " render(" + renderArgsWithoutAnnos + ", DoBody body) {");
				pln("\t\t" + "this.body = body;");
			} else {
				pln("\tpublic " + resultType + " render(" + renderArgsWithoutAnnos + ") {");
			}
			// assign the params to fields
			for (Parameter p : params) {
				pln("\t\tthis." + p.getId() + " = " + p.getId() + ";");
			}
		} else {
			String nameParamCode = String.format(NAMED_PARAM_CODE, paramNameArray, paramTypeArray, paramDefaultsArray, currentClassFQN);
			pln(nameParamCode);
			if (doBodyArgsString != null) {
				pln(NAMED_PARAM_WITH_BODY);
				// the field
				pln(TAB + "DoBody body;");
				doBodyInterface();
				pln("\tpublic " + resultType + " render(DoBody body) {");
				pln("\t\t" + "this.body = body;");
			} else {
				pln("\tpublic " + resultType + " render() {");
			}
		}
		pln("\t\tlong t = -1;");
		if (stopWatch)
//			pln("\t\tt = System.currentTimeMillis();");
			pln("\t\t t = System.nanoTime();");
		pln("\t\tsuper.layout(" + superClassRenderArgs +  ");");
		if (stopWatch) {
			pln("     	String l = \"\" + (System.nanoTime() - t) / 100000;\n" + 
					"		int len = l.length();\n" + 
					"		l = l.substring(0, len - 1) + \".\" +  l.substring(len - 1);\n" + 
					"");
			pln("\t\tSystem.out.println(\"[" + super.className + "] rendering time(ms): \" + l);");
		}
		// bug fix: always assume there is action invocation in the super class or it won't get rendered!
		hasActionInvocation = true;
		
		if (streaming) {
			if (useWithPlay && hasActionInvocation)
				pln("\t\treturn new " + RENDER_RESULT_PARTIAL + "(this.headers, null, t, " + ACTION_RUNNERS + ");");
			else {
				if (useWithPlay) {
					pln("\t\treturn new " + resultType + "(this.headers, null, t);");
				}
				else {
					pln("\t\t if (t != -1) System.out.println(\"[" + super.className + "] rendering time: \" + t);");
					pln("\t\treturn getOut().toString();");
				}
			}

		} else {
			if (useWithPlay) {
				if (hasActionInvocation) 
					pln("\t\treturn new " + RENDER_RESULT_PARTIAL + "(this.headers, getOut(), t, " + ACTION_RUNNERS + ");");
				else
					pln("\t\treturn new " + resultType + "(this.headers, getOut(), t);");
			}
			else {
				pln("\t\t if (t != -1) System.out.println(\"[" + super.className + "] rendering time: \" + t);");
				pln("\t\treturn getOut().toString();");
			}
		}
		pln("\t}");
	}

	private void doBodyInterface() {
		// let do the dobody callback interface
		// doBody interface:
		if (doBodyArgsString != null) {
			List<String> args = JavaSyntaxTool.parseArgs(doBodyArgsString);

			String genericTypeList = "";
			String renderArgList = "";
			int i = 0;
			for (String arg : args) {
				char c = doBodyGenericTypeParams[i++];
				genericTypeList += "," + c;
				renderArgList += "," + c + " " + Character.toLowerCase(c);
			}
			if (genericTypeList.startsWith(",")) {
				// remove the first comma
				genericTypeList = "<" + genericTypeList.substring(1) + ">";
				renderArgList = renderArgList.substring(1); 
			}
			pln("\tpublic static interface DoBody", genericTypeList, " {");
			pln("\t\t void render(" + renderArgList + ");");
			pln("\t}");
		}
	}

	@Override
	void childLayout() {
		// concrete views do not have this
	}

	protected static final String NAMED_PARAM_CODE = "" +
			"/* based on https://github.com/branaway/Japid/issues/12\n" + 
			" */\n" +
			"public static final String[] argNames = new String[] {/* args of the template*/%s };\n" + 
			"public static final String[] argTypes = new String[] {/* arg types of the template*/%s };\n" + 
			"public static final Object[] argDefaults= new Object[] {%s };\n"  + 
			"public static java.lang.reflect.Method renderMethod = getRenderMethod(%s.class);\n" + 
			"{\n" + 
			"	setRenderMethod(renderMethod);\n" + 
			"	setArgNames(argNames);\n" + 
			"	setArgTypes(argTypes);\n" + 
			"	setArgDefaults(argDefaults);\n" + 
			"}\n" +
			"" + 
			"////// end of named args stuff\n";
	
	protected static final String NAMED_PARAM_WITH_BODY = 
		"public cn.bran.japid.template.RenderResult render(DoBody body, cn.bran.japid.compiler.NamedArgRuntime... named) {\n" + 
		"    Object[] args = buildArgs(named, body);\n" + 
		"    return runRenderer(args);\n" + 
		"}\n"; 

}
