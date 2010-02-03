package bran.japid;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import bran.japid.tags.Each;

public class AbstractTemplateClassMetaData {
	private static final String SPACE = " ";
	private static final String STATIC = "static";
	private static final String IMPORT = "import";
	private static Set<String> extraStaticImports = new HashSet<String>();
	private String originalTemplate;

	public String getOriginalTemplate() {
		return originalTemplate;
	}

	public void setOriginalTemplate(String originalTemplate) {
		this.originalTemplate = originalTemplate.replace('\\', '/');
	}

	protected StringBuilder sb = new StringBuilder();
	protected static final String SEMI = ";";
	protected static final String TAB = "\t";
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
	protected void printHeaders() {
		if (packageName != null) {
			pln("package " + packageName + SEMI);
		}
		pln("import java.util.*;");
		pln("import java.io.*;");
		pln("import tag.*;");
		pln("import " + Each.class.getName() + ";");
		// pln("import java.math.*;");
		// pln("import static java.lang.Math.*;");
		// // should decouple with JavaExtensions
		// pln("import static play.templates.JavaExtensions.*;");
		for (String l : extraImports) {
			l = l.trim();
			if (!l.endsWith(";"))
				l = l + ";";
			if (!l.startsWith(IMPORT))
				l = IMPORT + SPACE + l;
			pln(l);
		}

		for (String l : extraStaticImports) {
			l = l.trim();
			if (!l.startsWith(IMPORT))
				l = IMPORT + SPACE + STATIC + SPACE + l;

			if (!l.endsWith(".*;")) {
				l += ".*;";
			}
			pln(l);
		}

		pln("// This file was generated from: " + originalTemplate);
		pln("// Change to this file will be lost next time the template file is compiled.");

	}

	protected void embedSourceTemplateName() {
		pln("\t" + "public static final String sourceTemplate = \"" + originalTemplate + "\";");
	}

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

	/**
	 * add import lines to the to be generated imports lines, import and the
	 * ending ; are optional
	 * 
	 * @param imp
	 */
	public static void addImportLine(String imp) {
		imp = imp.trim();
		if (imp.startsWith(IMPORT)) {
			imp = imp.substring(IMPORT.length()).trim();
		}

		extraImports.add(imp);
	}

	protected void buildStatics() {
		for (int i = 0; i < statics.size(); i++) {
			pln("static byte[] static_" + i + " = getBytes(" + statics.get(i) + ");");
		}
	}

	protected void addConstructors() {
		// add one default and one to take a PrintWriter
		// pln(TAB + "public " + className + "() {\r\n" +
		// "		super();\r\n" +
		// "	}");
		pln(TAB + "public " + className + "(OutputStream out) {\r\n" + "		super(out);\r\n" + "	}");
	}

	/**
	 * 
	 */
	protected void classDeclare() {
		if (superClass == null) {
			if (this.getClass() == LayoutClassMetaData.class) {
				// abstract
				pln("public abstract class " + className + " extends " + BranTemplateBase.class.getName() + "{");
			} else {
				pln("public class " + className + " extends " + BranTemplateBase.class.getName() + "{");
			}
		} else {
			pln("public class " + className + " extends " + superClass + "{");
		}

	}

	public String superClass;

	public static void addImportStatic(Class<?> class1) {
		String className = class1.getName();
		extraStaticImports.add(className);
	}

	public static void addImportStatic(String imp) {
		if (imp.startsWith(IMPORT))
			imp = imp.substring(IMPORT.length()).trim();

		if (imp.startsWith(STATIC))
			imp = imp.substring(IMPORT.length()).trim();

		extraStaticImports.add(imp);
	}

	public void addImport(Class<?> class1) {
		String className = class1.getName();
		addImportLine(className);
	}

	private static Set<String> extraImports = new HashSet<String>();

	/**
	 * 
	 * @param text
	 *            something like \"hello\"
	 * @return
	 */
	public String addStaticText(String text) {
		this.statics.add(text);
		return "static_" + (statics.size() - 1);
	}
}