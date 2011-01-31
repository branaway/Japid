package cn.bran.japid.compiler;

import japa.parser.JavaParser;
import japa.parser.ParseException;
import japa.parser.ast.CompilationUnit;
import japa.parser.ast.body.MethodDeclaration;
import japa.parser.ast.body.ModifierSet;
import japa.parser.ast.body.Parameter;
import japa.parser.ast.expr.Expression;
import japa.parser.ast.expr.MethodCallExpr;
import japa.parser.ast.type.Type;
import japa.parser.ast.visitor.VoidVisitorAdapter;

import java.io.ByteArrayInputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

public class JavaSyntaxTool {
	private static final String UTF_8 = "UTF-8";

	public static CompilationUnit parse(String src) throws ParseException {
		ByteArrayInputStream in;
		try {
			in = new ByteArrayInputStream(src.getBytes(UTF_8));
			CompilationUnit cu;
			cu = JavaParser.parse(in, UTF_8);
			return cu;
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public static boolean isValid(String src) {
		try {
			CompilationUnit cu = parse(src);
			return true;
		} catch (ParseException e) {
			String m = e.getMessage();
			System.out.println(m.substring(0, m.indexOf('\n')));
			return false;
		}
	}

	/**
	 * 
	 * @author Bing Ran<bing_ran@hotmail.com>
	 * @deprecated use the original Parameter for complete control
	 */
	public static class Param {
		public String type, name;

		public Param(String type, String name) {
			this.type = type;
			this.name = name;
		}
	}

	private static final String classTempForParams = "class T { void t(%s) {} }";

	/**
	 * parse a line of text that is supposed to be parameter list for a method
	 * declaration.
	 * 
	 * TODO: the parameter annotation, modifiers, etc ignored. should do it!
	 * 
	 * @param line
	 * @return
	 */
	public static List<Parameter> parseParams(String line) {
		final List<Parameter> ret = new ArrayList<Parameter>();
		if (line == null || line.trim().length() == 0)
			return ret;
		String cl = String.format(classTempForParams, line);
		try {
			CompilationUnit cu = parse(cl);
			VoidVisitorAdapter visitor = new VoidVisitorAdapter() {
				@Override
				public void visit(Parameter p, Object arg) {
					ret.add(p);
				}
			};
			cu.accept(visitor, null);
		} catch (ParseException e) {
			throw new RuntimeException("the line does not seem to be a valid param declaration list: " + line);
		}
		return ret;
	}

	private static final String classTempForArgs = "class T {  {  foo(%s); } }";

	public static List<String> parseArgs(String line) {
		final List<String> ret = new ArrayList<String>();
		if (line == null || line.trim().length() == 0)
			return ret;

		String cl = String.format(classTempForArgs, line);
		try {
			CompilationUnit cu = parse(cl);
			VoidVisitorAdapter visitor = new VoidVisitorAdapter() {
				@Override
				public void visit(MethodCallExpr n, Object arg) {
					List<Expression> args = n.getArgs();
					// api issue: args can be null in case of empty arg list
					if (args != null)
						for (Expression e : args) {
							ret.add(e.toString());
						}
				}
			};
			cu.accept(visitor, null);
		} catch (ParseException e) {
			throw new RuntimeException("the line does not seem to be a valid arg list: " + line);
		}
		return ret;
	}

	public static boolean hasMethod(String javaSource, String string) throws ParseException {
		CompilationUnit cu = parse(javaSource);
		return hasMethod(cu, string);
	}

	public static boolean hasMethodInvocatioin(CompilationUnit cu, final String string) {
		if (string == null || string.trim().length() == 0)
			return false;

		final StringBuilder re = new StringBuilder();

		VoidVisitorAdapter visitor = new VoidVisitorAdapter() {
			@Override
			public void visit(MethodCallExpr n, Object arg) {
				if (string.equals(n.getName())) {
					re.append(1);
					return;
				} else {
					super.visit(n, arg);
				}
			}
		};
		cu.accept(visitor, null);
		if (re.length() == 0)
			return false;
		else
			return true;
	}

	public static boolean hasMethod(CompilationUnit cu, final String string) {
		final StringBuilder sb = new StringBuilder();

		VoidVisitorAdapter visitor = new VoidVisitorAdapter() {
			@Override
			public void visit(MethodDeclaration n, Object arg) {
				if (n.getName().equals(string)) {
					sb.append(1);
					return;
				}
			}
		};
		cu.accept(visitor, null);
		if (sb.length() == 0)
			return false;
		else
			return true;
	}

	public static boolean hasMethod(CompilationUnit cu, final String name, final int modis, 
			final String returnType, String paramList) {
		final StringBuilder sb = new StringBuilder();
		
		if (paramList == null)
			paramList = "";
		String formalParamList = addParamNamesPlaceHolder(paramList);
		
		final List<Parameter> params = parseParams(formalParamList);
		
		VoidVisitorAdapter visitor = new VoidVisitorAdapter() {
			@Override
			public void visit(MethodDeclaration n, Object arg) {
				if (n.getName().equals(name)) {
					int modifiers2 = n.getModifiers();
					if (modifiers2 == modis) {
						Type type = n.getType();
						if (type.toString().equals(returnType)) {
							List<Parameter> ps = n.getParameters();
							if (ps == null)
								ps = new ArrayList<Parameter>();
							if (paramsMatch(params, ps)) {
								sb.append(1);
								return;
							}
						}
					}
				}
			}
		};
		cu.accept(visitor, null);
		if (sb.length() == 0)
			return false;
		else
			return true;
	}
			/**
	 * 
	 * @param cu
	 * @param name
	 * @param modifiers
	 * @param returnType
	 * @param paramList, parameter type only: String, final int, etc
	 * @return
	 */
	public static boolean hasMethod(CompilationUnit cu, final String name, final String modifiers, 
			final String returnType, String paramList) {
		final int modis = parseModifiers(modifiers);
		return hasMethod(cu, name, modis, returnType, paramList);
	}

	/**
	 * @param paramList
	 * @return
	 */
	static String addParamNamesPlaceHolder(String paramList) {
		List<String> names = getNames(paramList);

		String formalParamList = "";
		for (int i = 0; i < names.size(); i++) {
			formalParamList += names.get(i) + " " + (char)('a' + i) + ",";
		}
		
		if (formalParamList.endsWith(","))
			formalParamList = formalParamList.substring(0, formalParamList.length() - 1);
		return formalParamList;
	}

	/**
	 * @param paramList
	 * @return
	 */
	private static List<String> getNames(String paramList) {
		paramList = paramList.replace(' ', ',');
		String[] pams = paramList.split(",");
		List<String> names = new ArrayList<String>();
		for (int i = 0; i < pams.length; i++) {
			String p = pams[i].trim();
			if (p.length() > 0)
				names.add(p);
		}
		return names;
	}

	protected static boolean paramsMatch(List<Parameter> params, List<Parameter> ps) {
		if (params == ps)
			return true;
		
		if ((params == null && ps != null) || (params != null && ps == null) )
			return false;
		
		if (params.size() != ps.size()) {
			return false;
		}
		
		for (int i = 0; i < params.size(); i++) {
			Parameter p1 = params.get(i);
			Parameter p2 = ps.get(i);
			if (!matchParams(p1, p2)) {
				return false;
			}
		}
		return true;
	}

	/**
	 * 
	 * @param p1
	 * @param p2
	 * @return true if the parameters signature matches. Parameter name does not matter.
	 */
	private static boolean matchParams(Parameter p1, Parameter p2) {
		if (p1.equals(p2)) 
			return true;

		if (p1.getModifiers() != p2.getModifiers())
			return false;
		
		if (!p1.getType().equals(p2.getType())) 
			return false;
		
		// TODO: compare annotations
		
		return true;
	}

	private static int parseModifiers(String modifiers) {
		int ret = 0;
		
		List<String> names = getNames(modifiers);
		
		for (String m: names) {
			if (m.equals("public")) {
				ret |= ModifierSet.PUBLIC;
			}
			else if (m.equals("private")) {
				ret |= ModifierSet.PRIVATE;
			}
			else if (m.equals("protected")) {
				ret |= ModifierSet.PROTECTED;
			}
			else if (m.equals("static")) {
				ret |= ModifierSet.STATIC;
			}
			else if (m.equals("final")) {
				ret |= ModifierSet.FINAL;
			}
			else if (m.equals("final")) {
				ret |= ModifierSet.FINAL;
			}
			else if (m.equals("synchronized")) {
				ret |= ModifierSet.SYNCHRONIZED;
			}
		}
		
		return ret;
	}

	/**
	 * 
	 * @param params
	 * 	 Type1 p1, Type2 p2...
	 * @return
	 *   Final Type1 p1, final Type2 p2...
	 */
	public static String addFinalToAllParams(String paramline) {
		if (paramline == null)
			return null;
		paramline  = paramline.trim();
		if (paramline.length() == 0)
			return "";
		List<Parameter> params = parseParams(paramline);
		String s = "";
		for (Parameter p: params) {
			s += "final " + p.getType() + " " + p.getId().getName() + ", ";
		}
		
		return s.substring(0, s.lastIndexOf(", "));
	}
	
	private static final String classTempForExpr = "class T {  {  f = %s ; } }";

	/**
	 * starting from the start of a string and find out the longest possible valid java expression
	 * 
	 * @param src
	 * @return the longest or "" in case of none
	 */
	public static String matchLongestPossibleExpr(String src) {
		if (src == null || src.trim().length() == 0)
			return "";
		
		src = src.trim();
		
		String expr = "";
		int i = src.length();
		for (; i > 0; i--) {
			expr = src.substring(0, i);
			String ss = String.format(classTempForExpr, expr);
			try {
				parse(ss);
				break;
			} catch (ParseException e) {
				continue;
			}
		}
		
		return expr.trim();
	}
}
