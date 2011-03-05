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

import java.util.List;
import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import cn.bran.japid.classmeta.AbstractTemplateClassMetaData;
import cn.bran.japid.classmeta.InnerClassMeta;
import cn.bran.japid.classmeta.MimeTypeEnum;
import cn.bran.japid.compiler.JapidParser.Token;
import cn.bran.japid.compiler.Tag.TagIf;
import cn.bran.japid.template.ActionRunner;
import cn.bran.japid.template.JapidTemplate;
import cn.bran.japid.template.RenderResult;
import cn.bran.japid.util.DirUtil;
import cn.bran.japid.util.WebUtils;

/**
 * based on the original code from the Play! Framework
 * 
 * the parent class for all three type compilers: regular template compiler, the
 * LayoutCompiler and the TagCompiler.
 * 
 * @author original Play! authors
 * @author Bing Ran<bing_ran@hotmail.com>
 * 
 */
public abstract class JapidAbstractCompiler {
	public static final String DO_LAYOUT = "doLayout";
	private static final String ELVIS = "?:";
	// pattern: } else if xxx {
	static final String ELSE_IF_PATTERN_STRING = "\\s*\\}\\s*else\\s*if\\s+([^\\(].*)\\s*";
	static final Pattern ELSE_IF_PATTERN = Pattern.compile(ELSE_IF_PATTERN_STRING);
	static final String OPEN_ELSE_IF_PATTERN_STRING = "\\s*else\\s*if\\s+([^\\(].*)\\s*";
	static final Pattern OPEN_ELSE_IF_PATTERN = Pattern.compile(OPEN_ELSE_IF_PATTERN_STRING);
	static final String OPEN_ELSE_STRING = "\\s*else\\s*";
	static final String SPACE_OR_NONE = "\\s*";
	static final String SPACE_AT_LEAST_ONE = "\\s+";

	// please trim the string before applying the pattern
	// the idea is to substitute the open for with "each" tag
	static final String OPEN_FOR_PATTERN_STRING = "for\\s+([^\\(].+)\\s*:\\s*(.+[^\\{])";
	static final Pattern OPEN_FOR_PATTERN = Pattern.compile(OPEN_FOR_PATTERN_STRING);

	// pattern: if xxx {
	static final String OPEN_IF_PATTERN1 = "if\\s+[^\\(].*";

	private static final String JAPID_RESULT = "cn.bran.play.JapidResult";

	private static final String ARGS = "args";

	protected static final String HTML = ".html";
	// private static final String DO_BODY = "doBody";
	protected static final String SPACE = " ";
	protected static final String NEW_LINE = "\n";
	protected JapidTemplate template;
	protected JapidParser parser;
	protected boolean doNextScan = true;

	// the tagsStack only tracks the regular tags, particularly not including
	// the open if
	private Stack<Tag> tagsStack = new Stack<Tag>();
	// the shadow is not used to keep the stacking of open if and regular tag
	private Stack<Tag> tagsStackShadow = new Stack<Tag>();

	protected int tagIndex;
	protected boolean skipLineBreak;
	protected boolean useWithPlay = true;

	public void compile(JapidTemplate t) {
		template = t;
		getTemplateClassMetaData().setOriginalTemplate(t.name);
		getTemplateClassMetaData().useWithPlay = this.useWithPlay;
		hop();
	}

	/**
	 * 
	 */
	protected void parse() {
		// Parse
		loop: for (;;) {

			if (doNextScan) {
				stateBeforePreviousState = previousState;
				previousState = state;
				state = parser.nextToken();
			} else {
				doNextScan = true;
			}

			String token = parser.getToken();

			switch (state) {
			case EOF:
				break loop;
			case PLAIN:
				plain(token);
				break;
			case VERBATIM:
				plain(token);
				break;
			case SCRIPT:
				script(token);
				break;
			case CLOSING_BRACE:
				closingBrace(token);
				break;
			case SCRIPT_LINE:
				if (previousState == Token.PLAIN && stateBeforePreviousState == Token.SCRIPT_LINE) {
					String spacer = this.getTemplateClassMetaData().removeLastSingleEmptyLine();
					if (spacer != null) {
						Tag currentScope = this.tagsStack.peek();
						// currentScope.bodyBuffer.append(text);
						int lastIndex = currentScope.bodyTextList.size() - 1;
						currentScope.bodyTextList.remove(lastIndex);
						currentScope.bodyTextList.set(lastIndex - 1, spacer);
					}
				}
				scriptline(token);
				break;
			case EXPR:
				expr(token);
				break;
			case MESSAGE:
				message(token);
				break;
			case ACTION:
				action(token, false);
				break;
			case ABS_ACTION:
				action(token, true);
				break;
			case COMMENT:
				skipLineBreak = true;
				break;
			case START_TAG:
				startTag(buildTag(token));
				break;
			case END_TAG:
				String tagName = token.trim();
				if (tagsStack.isEmpty()) {
					throw new JapidCompilationException(template, currentLine, "#{/" + tagName + "} is not opened.");
				}
				Tag tag = popStack();
				endTag(tag);
				break;
			case TEMPLATE_ARGS:
				templateArgs(token);
				break;
			}
		}
	}

	/**
	 * @return
	 */
	private Tag popStack() {
		// tagsStackShadow.pop();
		return tagsStack.pop();
	}

	protected void closingBrace(String token) {
		// ok a } after some space in a line
		// treat it as `}
		print("}");
	}

	protected void plain(String token) {
		String text = token.replace("\\", "\\\\").replaceAll("\"", "\\\\\"");
		// text = text.replace("``", "`"); // escaped `, already done by parser
		if (skipLineBreak && text.startsWith(NEW_LINE)) {
			text = text.substring(1);
		}

		// add static content to classmetadata and print the ref to the
		// generated source code
		if (this.getTemplateClassMetaData().getTrimStaticContent()) {
			String r = text.trim();
			if (r.length() == 0)
				return;
			else {
				text = text.trim();
			}
		}
		String lines = composeValidMultiLines(text);
		String ref = this.getTemplateClassMetaData().addStaticText(lines, text);
		if (ref != null) {
			// print the static content via the varaible
			// print("p(" + ref + ");");
			// print the static content directly
			print("p(" + lines + ");");

			markLine(parser.getLineNumber());
			println();
		}
	}

	/**
	 * break a line with newlines to multiple lines valid in java source code
	 * 
	 * <pre>
	 * "line1\n" +
	 * "line2\n"  + 
	 * "line3";
	 * 
	 * </pre>
	 * 
	 * @param src
	 * @return
	 */
	public static String composeValidMultiLines(String text) {
		// multi-line
		String[] lines = text.split(NEW_LINE, 10000);
		String result = "";
		for (int i = 0; i < lines.length; i++) {
			String line = lines[i];
			if (line.length() > 0 && (int) line.charAt(line.length() - 1) == 13) {
				// remove the last newline
				line = line.substring(0, line.length() - 1);
			}

			result += "\"" + line;

			if (i == lines.length - 1 && !text.endsWith(NEW_LINE)) {
				// last line
				result += "\"";
			} else if (i == lines.length - 1 && line.equals("")) {
				result += "\"";
			} else {
				// regular line
				result += "\\n\" + \n";
			}

			// markLine(parser.getLine() + i);
		}
		String emptySuffix = " + \n\"\"";
		if (result.endsWith(emptySuffix)) {
			result = result.substring(0, result.length() - emptySuffix.length());
		}
		return result;
	}

	protected abstract void startTag(Tag tag);

	protected void println() {
		// print(NEW_LINE);
		Tag currentScope = this.tagsStack.peek();
		currentScope.bodyTextList.add("");
		currentLine++;
	}

	/**
	 * always append to the last line
	 * 
	 * @param text
	 */
	protected void print(String text) {
		Tag currentScope = this.tagsStack.peek();
		// currentScope.bodyBuffer.append(text);
		int lastIndex = currentScope.bodyTextList.size() - 1;
		String lastLine = currentScope.bodyTextList.get(lastIndex);
		lastLine += text;
		currentScope.bodyTextList.set(lastIndex, lastLine);
		// else if (this.currentInnerClassName != null)
		// this.currentInnerClassRenderBody.append(text);
		// else
		// mainRenderBodySource.append(text);
	}

	protected void println(String text) {
		print(text);
		println();
		int i = 0;
		while (i++ < indentLevel) {
			print("\t");
		}
	}

	protected void markLine(int line) {
		if (!this.getTemplateClassMetaData().getTrimStaticContent()) {
			print("// line " + line);
		}

		template.linesMatrix.put(currentLine, line);
	}

	protected void scriptline(String token) {
		// String line = token.trim();
		// if ()
		script(token);
	}

	protected void script(String token) {
		String[] lines = new String[] { token };
		if (token.indexOf(NEW_LINE) > -1) {
			lines = parser.getToken().split(NEW_LINE);
		}

		for (int i = 0; i < lines.length; i++) {
			String line = lines[i];// .trim();
			if (startsWithIgnoreSpace(line, "import")) {
				getTemplateClassMetaData().addImportLine(line);
			} else if (startsWithIgnoreSpace(line, "//")) {
				// ignore
			} else if (startsWithIgnoreSpace(line, "extends")) {
				String layout = line.trim().substring("extends".length()).trim();
				// remove quotes if they present

				boolean hasParam = false;
				int p = 0;
				for (; p < layout.length(); p++) {
					char c = layout.charAt(p);
					if (c == ' ' || c == '\t' || c == '(') {
						hasParam = true;
						break;
					}
				}

				if (!hasParam) {
					layout = layout.replace("'", "");
					layout = layout.replace("\"", "");
					layout = removeEndingString(layout, ";");
					layout = removeEndingString(layout, HTML);
					layout = removeEndingString(layout, "/");
					if (layout.startsWith(".")) {
						// new feature allow extends .sub.layout.html
						if (layout.startsWith("./")) {
							layout = getTemplateClassMetaData().packageName + layout.substring(1);
						} else {
							layout = getTemplateClassMetaData().packageName + layout;
						}
					}
					getTemplateClassMetaData().superClass = layout.replace('/', '.');
				} else {
					String layoutName = layout.substring(0, p);
					layoutName = layoutName.replace("'", "");
					layoutName = layoutName.replace("\"", "");
					layoutName = layoutName.replace('/', '.');
					layoutName = removeEndingString(layoutName, HTML);

					// due to similarity, let's borrow a tag parsing
					Tag tag = new TagInvocationLineParser().parse(layoutName + layout.substring(p));

					if (tag.tagName.startsWith(".")) {
						// partial path, use current package as the root and
						// append the path to it
						tag.tagName = getTemplateClassMetaData().packageName + tag.tagName;
					}
					getTemplateClassMetaData().superClass = tag.tagName;
					getTemplateClassMetaData().superClassRenderArgs = tag.args;
				}
			} else if (startsWithIgnoreSpace(line, "contentType")) {
				// TODO: should also take standard tag name: Content-Type
				String contentType = line.trim().substring("contentType".length()).trim().replace("'", "").replace("\"", "");
				if (contentType.endsWith(";"))
					contentType = contentType.substring(0, contentType.length());
				getTemplateClassMetaData().setContentType(contentType);
			} else if (startsWithIgnoreSpace(line, "setHeader")) {
				String headerkv = line.trim().substring("setHeader".length()).trim();
				String[] split = headerkv.split("[ |\t]");
				if (split.length < 2) {
					throw new RuntimeException("setHeaader must take a key and a value string");
				}
				String name = split[0];
				String value = headerkv.substring(name.length()).trim();
				getTemplateClassMetaData().setHeader(name, value);
			} else if (startsWithIgnoreSpace(line, ARGS)) {
				String args = line.trim().substring(ARGS.length()).trim().replace(";", "").replace("'", "").replace("\"", "");
				Tag currentTag = this.tagsStack.peek();
				currentTag.callbackArgs = args;
			} else if (startsWithIgnoreSpace(line, "trim")) {
				String sw = line.trim().substring("trim".length()).trim().replace(";", "").replace("'", "").replace("\"", "");
				if ("on".equals(sw) || "true".equals(sw)) {
					getTemplateClassMetaData().trimStaticContent();
				}
			} else if (startsWithIgnoreSpace(line, "stopwatch")) {
				String sw = line.trim().substring("stopwatch".length()).trim().replace(";", "").replace("'", "").replace("\"", "");
				if ("on".equals(sw))
					getTemplateClassMetaData().turnOnStopwatch();
				// Tag currentTag = this.tagsStack.peek();
				// currentTag.bodyArgsString = contentType;
			} else if (startsWithIgnoreSpace(line, "log") || line.trim().equals("log")) {
				String args = line.trim().substring("log".length()).trim().replace(";", "");
				if (args.trim().length() == 0)
					args = "\"\"";
				String logLine = "System.out.println(\"" + this.template.name.replace('\\', '/') + "(line " + (parser.getLineNumber() + i)
						+ "): \" + " + args + ");";
				println(logLine);
			} else if (startsWithIgnoreSpace(line, "invoke")) {
				String args = line.trim().substring("invoke".length()).trim().replace(";", "");
				doActionInvokeDirective(args);
			} else if (startsWith(line, "a")) { // `a == `invoke, the a must be
												// the first char to avoid
												// collision
				String args = line.substring(2).trim().replace(";", "");
				doActionInvokeDirective(args);
			} else if (startsWithIgnoreSpace(line, "suppressNull") || line.trim().equals("suppressNull")) {
				String npe = line.trim().substring("suppressNull".length()).trim().replace(";", "").replace("'", "").replace("\"", "");
				if ("on".equals(npe) || "yes".equals(npe) || "".equals(npe))
					getTemplateClassMetaData().suppressNull();
			} else if (line.trim().equals("abstract")) {
				getTemplateClassMetaData().setAbstract(true);
			} else if (startsWithIgnoreSpace(line, "tag")) {
				String tagline = line.trim().substring(4);
				doTagDirective(tagline);
			} else if (startsWith(line, "t")) { // `t == `tag, the t must be the
												// first char to avoid collision
				String tagline = line.substring(2);
				doTagDirective(tagline);
			} else if (startsWithIgnoreSpace(line, "each") || startsWithIgnoreSpace(line, "Each")) {
				// support one line type of tag invocation.
				Tag tag = buildTagDirective(line);
				tag.tagName = "Each";
				tag.hasBody = true;
				startTag(tag);
			} else if (startsWithIgnoreSpace(line, "set")) {
				Tag set = buildTagDirective(line);
				if (line.contains(":"))
					set.hasBody = false;
				else
					set.hasBody = true;
				startTag(set);

				if (!set.hasBody) { // one liner.
					set = popStack();
				}
			} else if (startsWithIgnoreSpace(line, "get")) {
				Tag get = buildTagDirective(line);
				get.hasBody = false;
				startTag(get);
				get = popStack();
			} else if (startsWithIgnoreSpace(line, "def")) {
				// a function definition block
				Tag get = buildTagDirective(line);
				get.hasBody = true;
				startTag(get);
			} else if (line.trim().startsWith("noplay")) {
				// template is play independent
				getTemplateClassMetaData().useWithPlay = false;
			} else if (line.trim().equalsIgnoreCase("xml")) {
				// template is play independent
				getTemplateClassMetaData().setContentType(MimeTypeEnum.xml.header);
			} else if (line.trim().equalsIgnoreCase("json")) {
				// template is play independent
				getTemplateClassMetaData().setContentType(MimeTypeEnum.json.header);
			} else if (line.trim().equalsIgnoreCase("css")) {
				// template is play independent
				getTemplateClassMetaData().setContentType(MimeTypeEnum.css.header);
			} else if (line.trim().equalsIgnoreCase("txt") || line.trim().equalsIgnoreCase("text")) {
				// template is play independent
				getTemplateClassMetaData().setContentType(MimeTypeEnum.txt.header);
			} else if (line.trim().equalsIgnoreCase("js") || line.trim().equalsIgnoreCase("javascript")) {
				// template is play independent
				getTemplateClassMetaData().setContentType(MimeTypeEnum.js.header);
			} else if (line.trim().startsWith("verbatim")) {
				parser.verbatim = true;
				Tag get = buildTagDirective(line);
				get.hasBody = true;
				startTag(get);
			} else if (startsWithIgnoreSpace(line, "if")) {
				// `if expr {, the last { is optional
				String expr = line.trim();
				if (expr.matches(OPEN_IF_PATTERN1)) {
					// get the expression
					String trim = expr.substring(2).trim();
					boolean negative = trim.startsWith("!");
					if (negative)
						trim = trim.substring(1).trim();
					if (trim.endsWith("{")) {
						// semi-open
						expr = removeEndingString(trim, "{").trim();
						expr = "if(" + (negative ? "!" : "") +  "asBoolean(" + expr + ")) {";
					} else {
						// true open. out a shadow tag, since we want reuse the ` as the end delimitor
						Tag.TagIf iftag = new Tag.TagIf(trim, parser.getLineNumber());
						pushToStack(iftag);
						expr = "if(" + (negative ? "!" : "") + "asBoolean(" + trim + ")) {";
					}
					print(expr);
					markLine(parser.getLineNumber() + i);
					println();
				} else {
					// plain Java if
					print(expr);
					markLine(parser.getLineNumber() + i);
					println();
				}
			} else if (line.matches(ELSE_IF_PATTERN_STRING)) {
				// semi open
				String expr = line.trim();
				Matcher matcher = ELSE_IF_PATTERN.matcher(line);
				if (matcher.matches()) {
					expr = matcher.group(1).trim();
					boolean negative = expr.startsWith("!");
					if (negative)
						expr = expr.substring(1).trim();
					expr = "} else if(" + (negative ? "!" : "") +  "asBoolean(" + removeEndingString(expr, "{") + ")) {";
					print(expr);
					markLine(parser.getLineNumber() + i);
					println();
				} else {
					print(expr);
					markLine(parser.getLineNumber() + i);
					println();
				}
			} else if (line.matches(OPEN_ELSE_IF_PATTERN_STRING)) {
				// open
				String expr = line.trim();
				Matcher matcher = OPEN_ELSE_IF_PATTERN.matcher(line);
				if (matcher.matches()) {
					expr = matcher.group(1).trim();
					boolean negative = expr.startsWith("!");
					if (negative)
						expr = expr.substring(1).trim();

					// end previous if shadow and star a new one
					Tag tagShadow = tagsStackShadow.peek();
					if (tagShadow instanceof TagIf) {
						tagsStackShadow.pop();
						// to close an open if
						// start a new if
						Tag.TagIf iftag = new Tag.TagIf(expr, parser.getLineNumber());
						pushToStack(iftag);
						expr = "} else if(" + (negative ? "!" : "") +  "asBoolean(" + expr + ")) {";
					} else {
						throw new RuntimeException("the open \"else if\" statement is not properly matched to a previous if");
					}
				} 
				print(expr);
				markLine(parser.getLineNumber() + i);
				println();
			} else if (line.matches(OPEN_ELSE_STRING)) {
				Tag tagShadow = tagsStackShadow.peek();
				if (tagShadow instanceof TagIf) {
					tagsStackShadow.pop();
					// to close an open if
					// start a new if
					print("} else {");
					markLine(parser.getLineNumber() + i);
					println();
					Tag.TagIf iftag = new Tag.TagIf("", parser.getLineNumber());
					pushToStack(iftag);
				} else {
					throw new RuntimeException("the open \"else\" statement is not properly matched to a previous if");
				}
			} else if (line.trim().matches(OPEN_FOR_PATTERN_STRING)) {
				// simply replace it with a "each" tag call
				String expr = line.trim();
				Matcher matcher = OPEN_FOR_PATTERN.matcher(expr);
				if (matcher.matches()) {
					String instanceDecl = matcher.group(1);
					String collection = matcher.group(2);
					expr = "each " + collection + " | " + instanceDecl;
					Tag tag = buildTagDirective(expr);
					tag.tagName = "Each";
					tag.hasBody = true;
					startTag(tag);
				} else {
					// should not happen
					print(expr);
					markLine(parser.getLineNumber() + i);
					println();
				}
			} else if (line.trim().length() == 0) {
				// a single ` empty line, treated as the closing for `tag
				try {
					Tag tagShadow = tagsStackShadow.peek();
					if (tagShadow.isRoot()) {
//						System.out.println("");
					} else {
						tagShadow = tagsStackShadow.pop();

						if (tagShadow instanceof TagIf) {
							// to close an open if
							print("}");
							markLine(parser.getLineNumber() + i);
							println();
						} else {
							if (!tagsStack.empty()) {
								Tag tag = tagsStack.peek();
								if (!tag.isRoot()) {
									tag = popStack();
									endTag(tag);
								}
							}
						}
					}
				} catch (Exception e) {
					System.out.println(e);
				}
			} else {
				// OK plain Java code
				print(line);
				markLine(parser.getLineNumber() + i);
				println();
			}
		}
		skipLineBreak = true;
	}

	/**
	 * @param string
	 * @return
	 */
	private String removeEndingString(String string, String ending) {
		if (string.endsWith(ending))
			return string.substring(0, string.lastIndexOf(ending));
		else
			return string;
	}

	/**
	 * @param line
	 * @param string
	 * @return
	 */
	protected static boolean startsWithIgnoreSpace(String line, String string) {
		line = line.trim();
		return line.startsWith(string + " ") || line.startsWith(string + "\t");
	}

	/**
	 * @param line
	 * @param string
	 * @return
	 */
	private boolean startsWith(String line, String string) {
		return line.startsWith(string + " ") || line.startsWith(string + "\t");
	}

	/**
	 * @param args
	 */
	private void doActionInvokeDirective(String args) {
		if (!getTemplateClassMetaData().useWithPlay) {
			throw new RuntimeException("action invocation is only supported in Play environment. ");
		} else {
			this.getTemplateClassMetaData().setHasActionInvocation();
			if (args.trim().length() == 0)
				args = "whatyouwantoinvoke()";
			printActionInvocation(args);
		}
	}

	/**
	 * @param tagline
	 */
	private void doTagDirective(String tagline) {
		Tag tag = buildTagDirective(tagline);
		startTag(tag);
		if (!tag.hasBody) { // one liner.
			tag = tagsStackShadow.pop();
			tag = popStack();
			endTag(tag);
		}
	}

	protected void expr(String token) {
		String expr = token;
		// let's support the  big or operator "|||"
		int i = token.indexOf(ELVIS);
		String substitute = null;
		if (i > 0) {
			expr = token.substring(0, i);
			substitute = token.substring(i + ELVIS.length()).trim();
			if (substitute.startsWith("\""))
				substitute = substitute.substring(1);
			if (substitute.endsWith("\""))
				substitute = substitute.substring(0, substitute.length() - 1);
		}
		
		if (substitute != null) {
			// trap any null or empty string and use the substitute
			printLine("try {" +
					" Object o = " + expr + "; " + 
					"if (o.toString().length() ==0) { " + 
					"p(\"" + substitute + "\"); } " + 
					"else { p(o); } } " +
					"catch (NullPointerException npe) { " +
					"p(\"" + substitute + "\"); }");
//			printLine("try { Object o = expr; p(" + expr + "); } " +
//					"catch (NullPointerException npe) { " +
//					"p(\"" + substitute + "\"); }");
		}
		else {
			if (getTemplateClassMetaData().suppressNull)
				printLine("try { p(" + expr + "); } catch (NullPointerException npe) {}");
			else
				printLine("p(" + expr + ");");
		}
	}

	private void printLine(String string) {
		print(string);
		markLine(parser.getLineNumber());
		println();
	}

	protected void message(String token) {
		String expr = token.trim().replace('\'', '"');
		print(";p(getMessage(" + expr + "));");
		markLine(parser.getLineNumber());
		println();
	}

	/**
	 * TODO: remove all the dependency on the Play classes
	 * 
	 * @param token
	 * 
	 * @param absolute
	 */
	protected void action(String token, boolean absolute) {
		String action = token.trim();
		if (action.matches("^'.*'$") || action.matches("^\".*\"$")) {
			// static content like @{'my.css'}
			action = action.replace('\'', '"');
			// remove Play dependecy
			if (absolute) {
				// print("p(request.getBase() + play.mvc.Router.reverseWithCheck("
				// + action + ", play.Play.getVirtualFile(" + action + ")));");
				print("p(lookupStaticAbs(" + action + "));");
			} else {
				// print("p(play.mvc.Router.reverseWithCheck(" + action +
				// ", play.Play.getVirtualFile(" + action + ")));");
				print("p(lookupStatic(" + action + "));");
			}
		} else {
			if (!action.endsWith(")")) {
				action = action + "()";
			}
			// extract params if any
			int indexOfParam = action.indexOf("(");
			if (indexOfParam < 1) {
				throw new TemplateSyntaxException("action arguments must be enclosed in parenthesis.", template.name, action,
						this.currentLine);
			}

			String actionPart = action.substring(0, indexOfParam).trim();

			// extract the param list part
			String params = action.substring(indexOfParam + 1);
			params = params.substring(0, params.length() - 1).trim();
			if (params.length() == 0)
				params = "new Object[]{}";

			if (absolute) {
				print("p(lookupAbs(\"" + actionPart + "\", " + params + "));");
			} else {
				print("p(lookup(\"" + actionPart + "\", " + params + "));");
			}
		}
		markLine(parser.getLineNumber());
		println();
	}

	protected void hop() {

		String source = template.source;
		Tag rootTag = new Tag() {
			{
				tagName = ROOT_TAGNAME;
				startLine = 0;
				hasBody = true;
			}
		};

		this.tagsStack.push(rootTag);
		this.tagsStackShadow.push(rootTag);

		this.parser = new JapidParser(source);

		String tempName = template.name.replace("-", "_");// .replace('.', '_');
		String contentTypeHeader = MimeTypeEnum.getHeader(tempName.substring(tempName.lastIndexOf('.')));
		getTemplateClassMetaData().setContentType(contentTypeHeader);
		tempName = DirUtil.mapSrcToJava(tempName);
		tempName = tempName.substring(0, tempName.lastIndexOf(".java"));
		tempName = tempName.replace('\\', '/');
		// if (tempName.endsWith(HTML)) {
		// tempName = tempName.substring(0, tempName.indexOf(HTML));
		// }

		// extract path
		int lastSep = tempName.lastIndexOf('/');
		if (lastSep > 0) {
			String path = tempName.substring(0, lastSep);
			path = path.replace('/', '.');
			path = path.replace('\\', '.');
			getTemplateClassMetaData().packageName = path;
			getTemplateClassMetaData().setClassName(tempName.substring(lastSep + 1));
		} else {
			getTemplateClassMetaData().setClassName(tempName);
		}

		parse();
		// for (String n : BranTemplateCompiler.extensionsClassnames) {
		// println(" } ");
		// }
		// println("} }");
		// println("}");

		Tag tag = popStack();
		if (!tagsStack.empty())
			throw new RuntimeException("There is(are) " + tagsStack.size() + " unclosed tag(s) in the template: " + this.template.name);
		// remove print nothing statement to save a few CPU cycles
		this.getTemplateClassMetaData().body = tag.getBodyText().replace("p(\"\")", "").replace("pln(\"\")", "pln()");
		postParsing(tag);
		template.javaSource = this.getTemplateClassMetaData().generateCode();

	}

	/**
	 * add anything before the java source generation
	 */
	protected void postParsing(Tag tag) {
		this.getTemplateClassMetaData().renderArgs = tag.callbackArgs;
	}

	abstract protected AbstractTemplateClassMetaData getTemplateClassMetaData();

	protected void templateArgs(String token) {
		Tag currentTag = this.tagsStack.peek();
		String args = token;
		currentTag.callbackArgs = args;
	}

	/**
	 * @return
	 */
	protected Tag buildTag(String token) {
		String tagText = token.trim().replaceAll(NEW_LINE, SPACE);

		boolean hasBody = !parser.checkNext().endsWith("/");

		Tag tag = new TagInvocationLineParser().parse(tagText);
		if (tag.tagName == null || tag.tagName.length() == 0)
			throw new RuntimeException("tag name was empty: " + tagText);

		if (tag.tagName.startsWith(".")) {
			// partial path, use current package as the root and append the path
			// to it
			tag.tagName = getTemplateClassMetaData().packageName + tag.tagName;
		}
		tag.startLine = parser.getLineNumber();
		tag.hasBody = hasBody;
		tag.tagIndex = tagIndex++;
		return tag;
	}

	/**
	 * e.g.:
	 * 
	 * <pre>
	 * `tag myTag a, c |String c
	 * </pre>
	 * 
	 * @return
	 */
	protected Tag buildTagDirective(String token) {
		String tagText = token.trim();

		Tag tag = new TagInvocationLineParser().parse(tagText);
		if (tag.tagName == null || tag.tagName.length() == 0)
			throw new RuntimeException("tag name was empty: " + tagText);

		if (tag.tagName.startsWith(".")) {
			// partial path, use current package as the root and append the path
			// to it
			tag.tagName = getTemplateClassMetaData().packageName + tag.tagName;
		}
		tag.startLine = parser.getLineNumber();
		tag.tagIndex = tagIndex++;
		return tag;
	}

	/**
	 * @param actionInvocationWithCache
	 */
	protected static String createActionRunner(String actionInvocationWithCache) {
		List<String> params = new TagArgsParser(actionInvocationWithCache).split();
		String action = params.get(0);
		// remove the argument part to extract action string as key base
		int left = action.indexOf('(');
		if (left < 1) {
			throw new RuntimeException("invoke: action arguments must be enclosed in parenthesis.");
		}
		int right = action.lastIndexOf(')');
		String actionPath = "\"" + action.substring(0, left) + "\"";
		String args = action.substring(left + 1, right).trim();
		String ttl = "\"\"";

		if (params.size() >= 2) {
			ttl = params.get(1);
			if (params.size() > 2) {
				for (int i = 2; i < params.size(); i++) {
					args += "," + params.get(i);
				}

				if (args.startsWith(","))
					args = args.substring(1);

				if (args.endsWith(","))
					args = args.substring(0, args.length() - 1);
			}
		}
		return (createActionRunner(action, ttl, actionPath, args));
	}

	protected void printActionInvocation(String action) {
		println(createActionRunner(action));
	}

	/**
	 * @param tag
	 */
	protected void regularTagInvoke(Tag tag) {
		if ("extends".equals(tag.tagName)) {
			String layoutName = tag.args;
			layoutName = layoutName.replace("'", "");
			layoutName = layoutName.replace("\"", "");
			layoutName = removeEndingString(layoutName, HTML);
			layoutName = removeEndingString(layoutName, "/");

			getTemplateClassMetaData().superClass = layoutName.replace('/', '.');
		} else if (tag.tagName.equals("invoke")) {
			invokeAction(tag);
		} else {
			String tagVar = "_" + tag.getTagVarName() + tag.tagIndex;
			if (!tag.hasBody) {
				String tagline = tagVar + ".setOut(getOut()); ";
				tagline += tagVar + ".render(" + tag.args + ");";
				println(tagline);
			}
		}
	}

	/**
	 * @param tag
	 */
	protected void invokeAction(Tag tag) {
		if (tag.hasBody) {
			throw new JapidCompilationException(template, currentLine, "invoke tag cannot have a body. Must be ended with /}");
		}

		this.getTemplateClassMetaData().setHasActionInvocation();
		String action = tag.args;
		printActionInvocation(action);
	}

	/**
	 * @param tag
	 */
	protected void endRegularTag(Tag tag) {
		if (tag.hasBody) {
			InnerClassMeta inner = this.getTemplateClassMetaData().addCallTagBodyInnerClass(tag.tagName, tag.tagIndex, tag.callbackArgs,
					tag.getBodyText());
			if (inner == null)
				System.out.println(tag.tagName + " not allowed to have instance of this tag");
			String tagVar = "_" + tag.getTagVarName() + tag.tagIndex;
			String tagLine = tagVar + ".setOut(getOut()); "; // make sure to use the current string builder
			tagLine += tagVar + ".render(" + (WebUtils.asBoolean(tag.args) ? tag.args + ", " : "") + inner.getAnonymous() + ");";
			println(tagLine);
		} else {
			// for simple tag call without call back:
			this.getTemplateClassMetaData().addCallTagBodyInnerClass(tag.tagName, tag.tagIndex, null, null);
		}
	}

	/**
	 * define a string returning method from a block
	 * 
	 * @param tag
	 */
	protected void def(Tag tag) {
	}

	protected void endDef(Tag tag) {
		if (tag.hasBody) { // must always
			this.getTemplateClassMetaData().addDefTag(tag);
		}
	}

	protected void endTag(Tag tag) {
		String lastInStack = tag.tagName;
		String tagName = lastInStack;
		// if (!lastInStack.equals(tagName)) {
		// throw new JapidCompilationException(template, tag.startLine, "#{" +
		// tag.tagName + "} is not closed.");
		// }
		if (tagName.equals("def")) {
			endDef(tag);
		} else if (tagName.equals("doBody")) {
		} else if (tagName.equals("extends")) {
		} else if (tagName.equals("get")) {
			// } else if (tagName.equals("set")) { // the set is handled in the
			// JapidTemplateCompiler endTagSpecial()
		} else if (tagName.equals("invoke")) {
		} else if (tagName.equals(DO_LAYOUT)) {
		} else if (endTagSpecial(tag)) {
		} else {
			endRegularTag(tag);
		}
		markLine(tag.startLine);
		println();
		// tagIndex--;
		skipLineBreak = true;
	}

	/**
	 * sub class can detect special tag and return true to indicate the tag has
	 * been processed.
	 * 
	 * @param tag
	 * @return
	 */
	protected boolean endTagSpecial(Tag tag) {
		return false;
	}

	static String createActionRunner(String action, String ttl, String base, String keys) {
		String actionEscaped = action.replace("\"", "\\\"");
		String controllerActionPart = action.substring(0, action.indexOf('('));
		int lastDot = controllerActionPart.lastIndexOf('.');
		String controllerName = controllerActionPart.substring(0, lastDot);
		String actionName = controllerActionPart.substring(lastDot + 1);

		if (ttl == null) {
			String template =
					"		%s.put(getOut().length(), new %s() {\n" +
							"			@Override\n" +
							"			public %s run() {\n" +
							"				try {\n" +
							"					play.classloading.enhancers.ControllersEnhancer.ControllerInstrumentation.initActionCall();\n" +
							"					%s;\n" +
							"				} catch (%s jr) {\n" +
							"					return jr.getRenderResult();\n" +
							"				}\n" +
							"				throw new RuntimeException(\"No render result from running: %s\");\n" +
							"			}\n" +
							"		});";
			return String.format(template,
					AbstractTemplateClassMetaData.ACTION_RUNNERS,
					ActionRunner.class.getName(),
					RenderResult.class.getName(),
					action,
					JAPID_RESULT,
					actionEscaped);
		} else {
			// hardcode the cache action runner name to avoid dependency on the
			// Play jar

			String template =
					"		%s.put(getOut().length(), new %s(%s, %s, %s, %s) {\r\n" +
							"			@Override\r\n" +
							"			public void runPlayAction() throws %s {\r\n" +
							// "				super.checkActionCacheFor(%s.class, \"%s\");\n"
							// +
							"				%s; //\r\n" +
							"			}\r\n" +
							"		});\r\n";
			return String.format(template,
					AbstractTemplateClassMetaData.ACTION_RUNNERS,
					"cn.bran.play.CacheablePlayActionRunner",
					ttl,
					controllerName + ".class",
					"\"" + actionName + "\"",
					"".equals(keys) ? "\"\"" : keys,
					JAPID_RESULT,
					action
					);
			// return String.format(template,
			// AbstractTemplateClassMetaData.ACTION_RUNNERS,
			// "cn.bran.play.CacheablePlayActionRunner",
			// ttl,
			// base,
			// "".equals(keys) ? "\"\"" : keys,
			// JAPID_RESULT,
			// controllerName,
			// actionName,
			// action
			// );
		}

	}

	protected int currentLine = 1;

	protected int indentLevel = 0;
	JapidParser.Token state;
	JapidParser.Token previousState;
	JapidParser.Token stateBeforePreviousState;

	public JapidAbstractCompiler() {
		super();
	}

	public void setUseWithPlay(boolean play) {
		this.useWithPlay = play;
	}

	/**
	 * @param tag
	 */
	protected void pushToStack(Tag tag) {
		tagsStackShadow.push(tag);
		if (!(tag instanceof TagIf))
			tagsStack.push(tag);

	}
}