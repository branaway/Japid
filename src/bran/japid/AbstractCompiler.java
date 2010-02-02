package bran.japid;

import java.io.File;
import java.util.Stack;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * the parent class for all three type compilers: regular template conpiler, the
 * LayoutCompiler and the TagCompiler.
 * 
 * @author bran
 * 
 */
public abstract class AbstractCompiler {
	private static Log log = LogFactory.getLog(AbstractCompiler.class);

	protected static final String HTML = ".html";
	// private static final String DO_BODY = "doBody";
	protected static final String SPACE = " ";
	protected static final String NEW_LINE = "\n";
	protected BranTemplate template;
	protected BranParser parser;
	protected boolean doNextScan = true;
	protected Stack<BranTemplateCompiler.Tag> tagsStack = new Stack<BranTemplateCompiler.Tag>();
	protected int tagIndex;
	protected boolean skipLineBreak;

	protected static class Tag {
		String tagName;
		int startLine;
		boolean hasBody;
		// bran: put everything in the args tag in it
		String bodyArgsString;
		StringBuffer bodyBuffer = new StringBuffer(2000);
		public String innerClassName;
		public String args;
	}

	public void compile(BranTemplate t) {
		template = t;
		getTemplateClassMetaData().setOriginalTemplate (t.name);
		hop();
	}

	/**
	 * 
	 */
	protected void parse() {
		// Parse
		loop: for (;;) {

			if (doNextScan) {
				state = parser.nextToken();
			} else {
				doNextScan = true;
			}

			switch (state) {
			case EOF:
				break loop;
			case PLAIN:
				plain();
				break;
			case SCRIPT:
				script();
				break;
			case EXPR:
				expr();
				break;
			case MESSAGE:
				message();
				break;
			case ACTION:
				action(false);
				break;
			case ABS_ACTION:
				action(true);
				break;
			case COMMENT:
				skipLineBreak = true;
				break;
			case START_TAG:
				startTag();
				break;
			case END_TAG:
				endTag();
				break;
			case TEMPLATE_ARGS:
				templateArgs();
				break;
			}
		}
	}

	protected void plain() {
		String text = parser.getToken().replace("\\", "\\\\").replaceAll("\"", "\\\\\"").replace("$", "\\$");
		
		if (skipLineBreak && text.startsWith(NEW_LINE)) {
			text = text.substring(1);
		}

		// add static content to classmetadata and print the ref to the generated source code
		
		String ref = this.getTemplateClassMetaData().addStaticText(composeValidMultiLines(text));
		print("p(");
		print(ref);
		print(");");
		markLine(parser.getLine());
		println();
		
//		skipLineBreak = false;
//		if (!skipLineBreak && text.indexOf(NEW_LINE) > -1) {
////			// multi-line
//			String multiline = composeValidMultiLines(text);
//			markLine(parser.getLine());
//			if (text.endsWith(NEW_LINE)) {
//				print("\npln(" + multiline + ");");
//			}
//			else {
//				print("\np(" + multiline + ");");
//			}
//			
//// old way: do aprint each line. s little expensive
////			String[] lines = text.split(NEW_LINE, 10000);
////			for (int i = 0; i < lines.length; i++) {
////				String line = lines[i];
////				if (line.length() > 0 && (int) line.charAt(line.length() - 1) == 13) {
////					// remove the last newline
////					line = line.substring(0, line.length() - 1);
////				}
////
////				if (i == lines.length - 1 && !text.endsWith(NEW_LINE)) {
////					// last line
////					print("p(\"");
////				} else if (i == lines.length - 1 && line.equals("")) {
////					continue;
////				} else {
////					// regular line
////					print("pln(\"");
////				}
////				print(line);
////				print("\");");
////
////				markLine(parser.getLine() + i);
////				println();
////			}
//		} else {
//			print("p(\"");
//			print(text);
//			print("\");");
//			markLine(parser.getLine());
//			println();
//		}
	}

	/**
	 * break a line with newlines to multiple lines valid in java source code
	 * <pre>
"line1\n" +
"line2\n"  + 
"line3"; 

	 * </pre>
	 * @param src
	 * @return
	 */
	static String composeValidMultiLines(String text) {
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
				result += "\"\n";
			} else if (i == lines.length - 1 && line.equals("")) {
				result += "\"";
			} else {
				// regular line
				result += "\\n\" + \n";
			}
	
//			markLine(parser.getLine() + i);
		}
		return result;
	}
	protected abstract void startTag();

	protected abstract void endTag();

	protected abstract void templateArgs();

	protected void println() {
		print(NEW_LINE);

		currentLine++;
	}

	protected void print(String text) {
		Tag currentScope = this.tagsStack.peek();
		currentScope.bodyBuffer.append(text);
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
		print("// line " + line);

		template.linesMatrix.put(currentLine, line);
	}

	protected void script() {
		String text = parser.getToken();
		if (text.indexOf(NEW_LINE) > -1) {
			String[] lines = parser.getToken().split(NEW_LINE);
			for (int i = 0; i < lines.length; i++) {
				String line = lines[i];
				String temp = line.trim();
				if (temp.startsWith("import ") || temp.startsWith("import	")) {
					getTemplateClassMetaData().addImportLine(temp);
				} else if (temp.startsWith("extends ") || temp.startsWith("extends	")) {
					String layoutName = temp.substring("extends".length()).trim();
					layoutName = layoutName.replace("'", "");
					if (layoutName.endsWith(";")) { 
						layoutName = layoutName.substring(0, layoutName.length() - 1);
					}
					if (layoutName.endsWith(HTML)) {
						layoutName = layoutName.substring(0, layoutName.indexOf(HTML));
					}
					if (layoutName.startsWith("/")) {
						layoutName = layoutName.substring(1);
					}
					getTemplateClassMetaData().superClass = layoutName.replace('/', '.');
				} else {
					print(line);
					markLine(parser.getLine() + i);
					println();
				}
			}
		} else {
			String temp = text.trim();
			if (temp.startsWith("imports ")) {
				getTemplateClassMetaData().addImportLine(temp);
			} else {
				print(text);
				markLine(parser.getLine());
				println();
			}
		}
		skipLineBreak = true;
	}

	protected void expr() {
		// TODO: make difference of safe expression and raw expression
		// safe expressions are wrapped in
		String expr = parser.getToken().trim();
		print("p(" + expr + ");");
		markLine(parser.getLine());
		println();
	}

	protected void message() {
		String expr = parser.getToken().trim().replace('\'', '"');
		print(";p(getMessage(" + expr + "));");
		markLine(parser.getLine());
		println();
	}

	/**
	 * TODO: remove all the dependency on the Play classes
	 * 
	 * @param absolute
	 */
	protected void action(boolean absolute) {
		String action = parser.getToken().trim();
		if (action.matches("^'.*'$")) {
			// static content like @{'my.css'}
			if (absolute) {
				print("p(request.getBase() + play.mvc.Router.reverseWithCheck(" + action + ", play.Play.getVirtualFile(" + action + ")));");
			} else {
				print("p(play.mvc.Router.reverseWithCheck(" + action + ", play.Play.getVirtualFile(" + action + ")));");
			}
		} else {
			if (!action.endsWith(")")) {
				action = action + "()";
			}
			// extract params if any
			int indexOfParam = action.indexOf("(");
			if (indexOfParam <1 ) {
				throw new TemplateSyntaxException("action needs pair of ()", template.name, action, this.currentLine);
			}
			
			String actionPart = action.substring(0, indexOfParam).trim();

			// extract the param list part
			String params = action.substring(indexOfParam + 1);
			params.substring(0, params.length() - 1);
			
			
			if (absolute) {
				print("p(lookupAbs(\"" + actionPart + "\", "  + params + ");");
			} else {
				print("p(lookup(\"" + actionPart + "\", "  + params + ");");
			}
		}
		markLine(parser.getLine());
		println();
	}

	protected void hop() {

		String source = template.source;
		Tag rootTag = new Tag() {
			{
				tagName = "root";
				startLine = 0;
				hasBody = true;
			}
		};

		this.tagsStack.push(rootTag);

		this.parser = new BranParser(source);

		String tempName = template.name.replace("-", "_");// .replace('.', '_');
		if (tempName.endsWith(HTML)) {
			tempName = tempName.substring(0, tempName.indexOf(HTML));
		}
		// extract path
		int lastIndexOf = tempName.lastIndexOf(File.separator);
		if (lastIndexOf > 0) {
			String path = tempName.substring(0, lastIndexOf);
			path = path.replace('/', '.');
			getTemplateClassMetaData().packageName = path;
			getTemplateClassMetaData().className = tempName.substring(lastIndexOf + 1);
		} else {
			getTemplateClassMetaData().className = tempName;
		}

		parse();
		// for (String n : BranTemplateCompiler.extensionsClassnames) {
		// println(" } ");
		// }
		// println("} }");
		// println("}");

		BranLayoutCompiler.Tag tag = tagsStack.pop();
		assert (tagsStack.empty());

		this.getTemplateClassMetaData().body = tag.bodyBuffer.toString().replace("p(\"\")", "").replace("pln(\"\")", "pln()");
		postParsing(tag);
		template.javaSource = this.getTemplateClassMetaData().toString();

		try {
			log.trace(String.format("%s is compiled to %s", template.name, template.javaSource));
		} catch (Exception e) {
			//
		}

	}

	/**
	 * add anything before the java source generation
	 */
	abstract protected void postParsing(Tag tag);

	abstract protected AbstractTemplateClassMetaData getTemplateClassMetaData();

	protected int currentLine = 1;

	protected int indentLevel = 0;
	BranParser.Token state;

	public AbstractCompiler() {
		super();
	}

}