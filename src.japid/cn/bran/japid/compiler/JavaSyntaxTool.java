package cn.bran.japid.compiler;

import japa.parser.JavaParser;
import japa.parser.ParseException;
import japa.parser.ast.CompilationUnit;
import japa.parser.ast.body.MethodDeclaration;
import japa.parser.ast.body.Parameter;
import japa.parser.ast.expr.Expression;
import japa.parser.ast.expr.MethodCallExpr;
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
