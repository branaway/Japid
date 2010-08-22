/**
 * Copyright 2010 Bing Ran<bing_ran@hotmail.com>
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at http://www.apache.org/licenses/LICENSE-2.0.
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */

package cn.bran.japid.classmeta;

import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import cn.bran.japid.compiler.JapidAbstractCompiler.Tag;
import cn.bran.japid.template.ActionRunner;
import cn.bran.japid.template.JapidTemplateBase;
import cn.bran.japid.template.JapidTemplateBaseStreaming;
import cn.bran.japid.template.RenderResult;
import cn.bran.japid.template.RenderResultPartial;

/**
 * lots of the code block generation is done here
 * 
 * @author Bing Ran<bing_ran@hotmail.com>
 * 
 */
public class AbstractTemplateClassMetaData {
	private static final String PUBLIC = "public ";
	private static final String COMMA = ";";
	private static final String SPACE = " ";
	private static final String STATIC = "static";
	private static final String IMPORT = "import";
	private static Set<String> globalStaticImports = new HashSet<String>();
	private Set<String> staticImports = new HashSet<String>();
	private String originalTemplate;

	// control if we use a streaming based API or StringBuilder based API
	public static boolean streaming = false;
	// if we need to track the time to render
	boolean stopWatch = false;
	// control whether to allow safe expression navigation
	public boolean suppressNull = false;

	public String getOriginalTemplate() {
		return originalTemplate;
	}

	public void setOriginalTemplate(String originalTemplate) {
		this.originalTemplate = originalTemplate.replace('\\', '/');
	}

	public StringBuilder sb = new StringBuilder();
	protected static final String SEMI = COMMA;
	protected static final String TAB = "\t";
	protected static final String RENDER_RESULT = RenderResult.class.getName();
	protected static final String RENDER_RESULT_PARTIAL = RenderResultPartial.class.getName();
	public static final String ACTION_RUNNERS = "actionRunners";
	private static final String IMPORT_SPACE = IMPORT + SPACE;
	private static final String CONTENT_TYPE = "Content-Type";
	public String packageName;
	public String className;

	// each line: byte[] _lineXXX=new byte[]{12, 23, 45};
	List<String> statics = new ArrayList<String>();
	int staticCounter = 0;

	// List<String>importsLines = new ArrayList<String>();

	/**
	 * the main body part
	 */
	public String body;
	protected List<InnerClassMeta> innersforTagCalls = new ArrayList<InnerClassMeta>();

	public AbstractTemplateClassMetaData() {
		super();
	}

	protected void pln(Object... ss) {
		for (Object o : ss) {
			sb.append(o);
		}
		sb.append("\n");
	}

	void p(String s) {
		sb.append(s);
	}

	public void addCallTagBodyInnerClass(String className, int count, String classArgs, String body) {
		this.innersforTagCalls.add(new InnerClassMeta(className, count, classArgs, body));
	}

	/**
	 * 
	 */
	public void printHeaders() {
		if (packageName != null) {
			pln("package " + packageName + SEMI);
		}
		pln("import java.util.*;");
		pln("import java.io.*;");
		// some nameing convention suport
		// cannot
		// pln("import japidviews._tags.*;");
		// pln("import japidviews._layouts.*;");

		if (streaming)
			pln(IMPORT_SPACE + cn.bran.japid.tags.streaming.Each.class.getName() + COMMA);
		else
			pln(IMPORT_SPACE + cn.bran.japid.tags.Each.class.getName() + COMMA);

		if (hasActionInvocation) {
			pln(IMPORT_SPACE + ActionRunner.class.getName() + COMMA);
		}

		// pln("import java.math.*;");
		// pln("import static java.lang.Math.*;");
		// // should decouple with JavaExtensions
		// pln("import static play.templates.JavaExtensions.*;");
		for (String l : globalImports) {
			l = l.trim();
			if (!l.endsWith(COMMA))
				l = l + COMMA;
			if (!l.startsWith(IMPORT))
				l = IMPORT_SPACE + l;
			pln(l);
		}

		for (String l : imports) {
			l = l.trim();
			if (!l.endsWith(COMMA))
				l = l + COMMA;
			if (!l.startsWith(IMPORT))
				l = IMPORT_SPACE + l;
			pln(l);
		}

		for (String l : globalStaticImports) {
			l = l.trim();
			if (!l.startsWith(IMPORT))
				l = IMPORT_SPACE + STATIC + SPACE + l;

			if (!l.endsWith(".*;")) {
				l += ".*;";
			}
			pln(l);
		}

		for (String l : staticImports) {
			l = l.trim();
			if (!l.startsWith(IMPORT))
				l = IMPORT_SPACE + STATIC + SPACE + l;

			if (!l.endsWith(".*;")) {
				l += ".*;";
			}
			pln(l);
		}

		pln("// NOTE: This file was generated from: " + originalTemplate);
		pln("// Change to this file will be lost next time the template file is compiled.");

	}

	protected void embedSourceTemplateName() {
		pln("\t" + "public static final String sourceTemplate = \"" + originalTemplate + "\";");
	}

	// protected void embedContentType() {
	// String t = contentType == null ? "text/html" : contentType;
	// pln("\t" + "public static final String contentType = \"" + t + "\";");
	// }

	/**
	 * 
	 */
	protected void callTags() {
		// inners
		for (InnerClassMeta inner : this.innersforTagCalls) {
			// create a resuable instance _tagName_indexand a instance
			// initializer
			String tagClassName = inner.tagName;
			String field = "private " + tagClassName + " _" + inner.tagName + inner.counter + " = new " + tagClassName + "(getOut());";
			pln("\t" + field);

			if (inner.renderBody != null) {
				// body class
				pln(inner.toString());
			}
		}
	}

	protected void printAnnotations() {
		for (Class<? extends Annotation> anno : typeAnnotations) {
			pln("@" + anno.getName());
		}
	}

	/**
	 * add import lines to the to be generated imports lines, import and the
	 * ending ; are optional
	 * 
	 * @param imp
	 */
	public static void addImportLineGlobal(String imp) {
		imp = imp.trim();
		if (imp.startsWith(IMPORT)) {
			imp = imp.substring(IMPORT.length()).trim();
		}

		globalImports.add(imp);
	}

	// protected void buildStatics() {
	// for (int i = 0; i < statics.size(); i++) {
	// pln("static byte[] static_" + i + " = getBytes(" + statics.get(i) +
	// ");");
	// }
	// }

	protected void buildStatics() {
		for (int i = 0; i < statics.size(); i++) {
			if (streaming)
				pln("static private final byte[] static_" + i + " = getBytes(" + statics.get(i) + ");");
			else
				pln("static private final String static_" + i + " = " + statics.get(i) + COMMA);
		}
	}

	protected void addConstructors() {
		if (!streaming) {
			// for StringBuilder data collection, create a default constructor
			pln(TAB + PUBLIC + className + "() {\r\n" + "		super(null);\r\n" + "	}");

		}

		if (streaming)
			pln(TAB + PUBLIC + className + "(OutputStream out) {");
		else
			pln(TAB + PUBLIC + className + "(StringBuilder out) {");

		pln(TAB + TAB + "super(out);");
		pln(TAB + "}");
	}

	/**
	 * 
	 */
	protected void classDeclare() {
		if (superClass == null) {
			String superName = JapidTemplateBase.class.getName();
			if (streaming)
				superName = JapidTemplateBaseStreaming.class.getName();
			if (this.getClass() == LayoutClassMetaData.class) {
				// abstract
				pln("public abstract class " + className + " extends " + superName + "{");
			} else {
				pln("public class " + className + " extends " + superName + "{");
			}
		} else {
			pln("public class " + className + " extends " + superClass + "{");
		}

	}

	public String superClass;

	public static void addImportStatic(Class<?> class1) {
		String className = class1.getName();
		globalStaticImports.add(className);
	}

	/**
	 * this is for globally adding static imports, usually by tools.
	 * 
	 * @param imp
	 */
	public static void addImportStaticGlobal(String imp) {
		if (imp.startsWith(IMPORT))
			imp = imp.substring(IMPORT.length()).trim();

		if (imp.startsWith(STATIC))
			imp = imp.substring(IMPORT.length()).trim();

		globalStaticImports.add(imp);
	}

	public void addImport(Class<?> class1) {
		String className = class1.getName();
		addImportLine(className);
	}

	private static Set<String> globalImports = new HashSet<String>();
	private Set<String> imports = new HashSet<String>();

	/**
	 * 
	 * @param text
	 *            something like \"hello\"
	 * @return
	 */
	public String addStaticText(String text) {
		if (text != null && !text.isEmpty()) {
			if (trimStaticContent) {
				if (text.trim().length() == 0) {
					return null;
				}
			}
			this.statics.add(text);
			return "static_" + (statics.size() - 1);
		} else
			return null;
	}

	/**
	 * add class level annotation
	 * 
	 * @param anno
	 */
	public static void addAnnotation(Class<? extends Annotation> anno) {
		typeAnnotations.add(anno);
	}

	static Set<Class<? extends Annotation>> typeAnnotations = new HashSet<Class<? extends Annotation>>();

	public void setContentType(String contentType) {
		this.headers.put(CONTENT_TYPE, contentType);
	}

	// String contentType;
	private boolean trimStaticContent = false;
	protected boolean hasActionInvocation;
	private Map<String, String> headers = new HashMap<String, String>();
	private List<Tag> defTags = new ArrayList<Tag>();

	public void turnOnStopwatch() {
		this.stopWatch = true;
	}

	/**
	 * suppress all NPE in expression ${} and display empty string 
	 */
	public void suppressNull() {
		this.suppressNull = true;
	}
	
	
	public void addStaticImports(String im) {
		staticImports.add(im);
	}

	public void addImportLine(String line) {
		this.imports.add(line);
	}

	/**
	 * ignore static content that contains whitespace chars only, including
	 * space, tab, \r\n etc.
	 */
	public void trimStaticContent() {
		this.trimStaticContent = true;
	}

	public boolean getTrimStaticContent() {
		return this.trimStaticContent;
	}

	public void setHasActionInvocation() {
		this.hasActionInvocation = true;

	}

	public void setHeader(String name, String value) {
		this.headers.put(name, value);
	}

	public void printHttpHeaderMap() {
		// now we use the headers var the template base, for slighly perfromance
		// penalty
		// pln("	private static final Map<String, String> headers = new HashMap<String, String>();");
		if (headers.size() > 0) {
			// pln("	static {");
			pln("{");
			for (String k : headers.keySet()) {
				String v = headers.get(k);
				pln("	headers.put(\"" + k + "\", \"" + v + "\");");
			}
			pln("}");
		}
	}

	public void addDefTag(Tag tag) {
		this.defTags.add(tag);
	}

	protected void processDefTags() {
		for (Tag tag : this.defTags) {
			pln("public String " + tag.args + "() {");
			pln("StringBuilder sb = new StringBuilder();");
			pln("StringBuilder ori = getOut();");
			pln("this.setOut(sb);");
			pln(tag.bodyBuffer.toString());
			pln("this.setOut(ori);");
			pln("return sb.toString();");
			pln("}");
		}
	}

	/**
	 * added variable declarations such as request, response, errors, flash, etc.
	 */
	protected void addImplicitVariables() {
		pln("\r\n		play.mvc.Http.Request request = play.mvc.Http.Request.current(); assert request != null;\r\n" + 
				"		play.mvc.Http.Response response = play.mvc.Http.Response.current(); assert response != null;\r\n" + 
				"		play.mvc.Scope.Flash flash = play.mvc.Scope.Flash.current();assert flash != null;\r\n" + 
				"		play.mvc.Scope.Session session = play.mvc.Scope.Session.current();assert session != null;\r\n" + 
				"		play.mvc.Scope.RenderArgs renderArgs = play.mvc.Scope.RenderArgs.current(); assert renderArgs != null;\r\n" + 
				"		play.mvc.Scope.Params params = play.mvc.Scope.Params.current();assert params != null;\r\n" + 
				"		play.data.validation.Validation validation = play.data.validation.Validation.current();assert validation!= null;\r\n" + 
				"		cn.bran.play.FieldErrors errors = new cn.bran.play.FieldErrors(validation.errors());assert errors != null;\r\n" + 
				"		play.Play _play = new play.Play(); assert _play != null;" + 
				"");
	}
}