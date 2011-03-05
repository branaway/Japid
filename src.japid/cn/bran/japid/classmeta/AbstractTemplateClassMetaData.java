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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import cn.bran.japid.compiler.Tag;
import cn.bran.japid.template.ActionRunner;
import cn.bran.japid.template.JapidTemplateBase;
import cn.bran.japid.template.JapidTemplateBaseStreaming;
import cn.bran.japid.template.JapidTemplateBaseWithoutPlay;
import cn.bran.japid.template.RenderResult;
import cn.bran.japid.template.RenderResultPartial;
//import cn.bran.play.JapidPlayAdapter;

/**
 * lots of the code block generation is done here
 * 
 * @author Bing Ran<bing_ran@hotmail.com>
 * 
 */
public abstract class AbstractTemplateClassMetaData {
	private static final String PUBLIC = "public ";
	private static final String COMMA = ";";
	private static final String SPACE = " ";
	private static final String STATIC = "static";
	private static final String IMPORT = "import";
	private static Set<String> globalStaticImports = new HashSet<String>();
	private Set<String> staticImports = new HashSet<String>();
	private String originalTemplate;
	private static Pattern partialImport = Pattern.compile("import\\s+\\.(.+)");

	// control if we use a streaming based API or StringBuilder based API
	public static boolean streaming = false;
	// if we need to track the time to render
	boolean stopWatch = false;
	// control whether to allow safe expression navigation
	public boolean suppressNull = false;

	public boolean useWithPlay = true;

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
	protected String className;

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	// each line: byte[] _lineXXX=new byte[]{12, 23, 45};
	List<String> statics = new ArrayList<String>();
	// the source of the plain text line
	List<String> staticsSrc = new ArrayList<String>();
	int staticCounter = 0;

	// List<String>importsLines = new ArrayList<String>();

	/**
	 * the main body part
	 */
	public String body;
	protected List<InnerClassMeta> innersforTagCalls = new ArrayList<InnerClassMeta>();
	protected List<InnerClassMeta> innersInvokeCalls = new ArrayList<InnerClassMeta>();
	private boolean isAbstract;

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

	public InnerClassMeta addCallTagBodyInnerClass(String className, int count, String callbackArgs, String body) {
		if (specialTags.contains(className))
			return null;
		InnerClassMeta inner = new InnerClassMeta(className, count, callbackArgs, body);
		this.innersforTagCalls.add(inner);
		return inner;
	}

	private static Set<String> specialTags = new HashSet<String>();
	static {
		specialTags.add("set");
		specialTags.add("get");
		specialTags.add("invoke");
		specialTags.add("doBody");
		specialTags.add("doLayout");
		specialTags.add("extends");
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

		if (hasActionInvocation && useWithPlay) {
			pln(IMPORT_SPACE + ActionRunner.class.getName() + COMMA);
		}

		for (String l : imports) {
			l = l.trim();
			if (!l.endsWith(COMMA))
				l = l + COMMA;
			if (!l.startsWith(IMPORT))
				l = IMPORT_SPACE + l;
			// extension: allow partial imports. e.g.: import .sub.*
			// which requires the current package name to prefix it.
			l = expandPartialImport(l);
			if (considerPlayDependency(l))
				pln(l);
		}

		for (String l : globalStaticImports) {
			l = l.trim();
			if (!l.startsWith(IMPORT))
				l = IMPORT_SPACE + STATIC + SPACE + l;

			if (!l.endsWith(".*;")) {
				l += ".*;";
			}
			l = expandPartialImport(l);
			if (considerPlayDependency(l))
				pln(l);
		}

		for (String l : staticImports) {
			l = l.trim();
			if (!l.startsWith(IMPORT))
				l = IMPORT_SPACE + STATIC + SPACE + l;

			if (!l.endsWith(".*;")) {
				l += ".*;";
			}
			l = expandPartialImport(l);
			if (considerPlayDependency(l))
				pln(l);
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
			l = expandPartialImport(l);
			if (considerPlayDependency(l))
				pln(l);
		}

		pln("//");
		pln("// NOTE: This file was generated from: " + originalTemplate);
		pln("// Change to this file will be lost next time the template file is compiled.");
		pln("//");

	}

	private boolean considerPlayDependency(String l) {
		if (useWithPlay)
			return true;

		// filter out all Play related imports
		if (l.startsWith(IMPORT)) {
			l = l.substring(IMPORT.length()).trim();
		}

		if (l.startsWith(STATIC)) {
			l = l.substring(STATIC.length()).trim();
		}

		if (l.startsWith("play"))
			return false;

		if (l.startsWith("cn.bran.play"))
			return false;

//		if (l.startsWith("japidviews"))
//			return false;

//		if (l.startsWith("models"))
//			return false;

		if (l.contains("JapidWebUtil"))
			return false;

//		if (l.startsWith("controllers"))
//			return false;

		return true;
	}

	/**
	 * @param l
	 *            a partially specified import line such as: import .tags.*;
	 * @return
	 */
	public String expandPartialImport(String l) {
		Matcher matcher = partialImport.matcher(l);
		if (matcher.matches()) {
			l = "import " + packageName + "." + matcher.group(1);
		}
		return l;
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
	// protected void callTags() {
	// // inners
	// for (InnerClassMeta inner : this.innersforTagCalls) {
	// // create a reusable instance _tagName_indexand a instance
	// // initializer
	// String tagClassName = inner.tagName;
	// String field = "private " + tagClassName + " _" +
	// inner.getInnerClassName() + inner.counter + " = new " + tagClassName +
	// "(getOut());";
	// pln("\t" + field);
	//
	// if (inner.renderBody != null) {
	// // body class
	// pln(inner.toString());
	// }
	// }
	// }

//	/**
//	 * @deprecated declare it as fields instead
//	 */
//	protected void setupTagObjectsAsVariables() {
//		boolean hasTags = this.innersforTagCalls.size() > 0;
//		if (hasTags)
//			pln("\n// -- set up the tag objects");
//		for (InnerClassMeta inner : this.innersforTagCalls) {
//			// create a reusable instance _tagName_indexand a instance
//			// initializer
//			String tagClassName = inner.tagName;
//			String var = "_" + inner.getVarRoot() + inner.counter;
//			String decl = "final " + tagClassName + " " + var + " = new " + tagClassName + "(getOut());";
//			pln(decl);
//			if (useWithPlay) {
//				String addRunner = var + ".setActionRunners(getActionRunners());";
//				pln(addRunner);
//			}
//			pln();
//		}
//		if (hasTags)
//			pln("// -- end of the tag objects\n");
//	}

	protected void setupTagObjectsAsFields() {
		boolean hasTags = this.innersforTagCalls.size() > 0;
		if (hasTags)
			pln("\n// -- set up the tag objects");
		for (InnerClassMeta inner : this.innersforTagCalls) {
			// create a reusable instance _tagName_indexand a instance
			// initializer
			String tagClassName = inner.tagName;
			String var = "_" + inner.getVarRoot() + inner.counter;
			String decl = "final " + tagClassName + " " + var + " = new " + tagClassName + "(getOut());";
			pln(decl);
			if (useWithPlay) {
				String addRunner = "{ " +  var + ".setActionRunners(getActionRunners()); }";
				pln(addRunner);
			}
			pln();
		}
		if (hasTags)
			pln("// -- end of the tag objects\n");
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
			pln(TAB + PUBLIC + className + "() {\n" + "		super(null);\n" + "	}");

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
	private void classDeclare() {
		if (superClass == null) {
			if (useWithPlay) {
				superClass = JapidTemplateBase.class.getName();
				if (streaming)
					superClass = JapidTemplateBaseStreaming.class.getName();
			}
			else {
				superClass = JapidTemplateBaseWithoutPlay.class.getName();
			}
		}

		String abs = isAbstract ? "abstract " : "";

		pln("public " + abs + "class " + className + " extends " + superClass);

	}

	/**
	 * set the generated class to be abstract
	 * 
	 * @param isAbstract
	 */
	public void setAbstract(boolean isAbstract) {
		this.isAbstract = isAbstract;
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
	 * @param src
	 * @return
	 */
	public String addStaticText(String text, String src) {
		if (text != null && !text.isEmpty()) {
			if (trimStaticContent) {
				if (text.trim().length() == 0) {
					return null;
				}
			}
			this.statics.add(text);
			this.staticsSrc.add(src);
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
		if (contentType != null)
			this.headers.put(CONTENT_TYPE, contentType);
	}

	// String contentType;
	private boolean trimStaticContent = false;
	protected boolean hasActionInvocation;
	private Map<String, String> headers = new HashMap<String, String>();
	private List<Tag> defTags = new ArrayList<Tag>();
	public String renderArgs;
	// to support extends layout (arg1, arg2)
	public String superClassRenderArgs = "";

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
	 * space, tab, \n etc.
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
		// now we use the headers var the template base, for slightly
		// performance penalty
		// pln("	private static final Map<String, String> headers = new HashMap<String, String>();");
		if (useWithPlay && headers.size() > 0) {
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
			String meth = tag.args.trim();
			if (meth.endsWith(")")) {
				pln("public String " + meth + " {");
			} else {
				pln("public String " + meth + "() {");
			}
			pln("StringBuilder sb = new StringBuilder();");
			pln("StringBuilder ori = getOut();");
			pln("this.setOut(sb);");
			pln(tag.getBodyText());
			pln("this.setOut(ori);");
			pln("return sb.toString();");
			pln("}");
		}
	}

	/**
	 * added field declarations such as request, response, errors
	 * Some of the implicit objects are defined in the JapidPlayAdapter
	 * Note: this basically removes the possibility to reuse the same instance of renderer to render multiple requests. 
	 */
	protected void addImplicitFields() {
		
		if (useWithPlay) {
			pln("\n// - add implicit fields with Play\n");
			pln("	final Request request = Request.current(); \n" +
					"	final Response response = Response.current(); \n" +
					"	final Session session = Session.current();\n" +
					"	final RenderArgs renderArgs = RenderArgs.current();\n" +
					"	final Params params = Params.current();\n" +
					"	final Validation validation = Validation.current();\n" +
					"	final cn.bran.play.FieldErrors errors = new cn.bran.play.FieldErrors(validation);\n" +
					"	final play.Play _play = new play.Play(); \n");
			pln("// - end of implicit fields with Play \n\n");
		}
	}
	
	/**
	 * remove the plain text line between two consecutive script line, if the
	 * plain text is made of space chars only .
	 * 
	 * return true if that's the case
	 */
	public String removeLastSingleEmptyLine() {
		int last = staticsSrc.size() - 1;
		String s = this.staticsSrc.get(last);
		char[] charArray = s.toCharArray();
		for (char c : charArray) {
			if (!Character.isSpaceChar(c))
				return null;
		}

		if (s.contains("\n")) {
			if (s.indexOf('\n') == s.lastIndexOf('\n')) {
				// it contains only one newline
				this.statics.remove(last);
				this.staticsSrc.remove(last);
				return s;
			} else
				return null;
		} else {
			this.statics.remove(last);
			this.staticsSrc.remove(last);
			return s;
		}

	}

	/**
	 * output the java code
	 * 
	 * @return
	 */
	public String generateCode() {
		printHeaders();
		printAnnotations();
		classDeclare();
		p("{");
		embedSourceTemplateName();
		printHttpHeaderMap();
		// buildStatics();
		if (useWithPlay) {
			addImplicitFields();
		}
		setupTagObjectsAsFields();
		
		addConstructors();

		renderMethod();

		layoutMethod();

		getterSetter();

		childLayout();

		processDefTags();

		p("}");

		return sb.toString();

	}

	abstract void renderMethod();

	abstract void layoutMethod();

	abstract void getterSetter();

	abstract void childLayout();

	@Override
	public String toString() {
		return generateCode();
	}

}