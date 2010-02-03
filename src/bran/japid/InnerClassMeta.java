package bran.japid;

import java.util.List;

import bran.japid.tags.Each;

/**
 * used to wrapped the body of an invocation of a user defined tag based on tag template file
 * 
 * @author bran
 *
 */
public class InnerClassMeta {
	private static final String EXTRA_LOOP_ATTRS = ", int _index, boolean _isOdd, boolean _isFirst, boolean _isLast";
	String tagName;
	// the sequence of the same tag called in a single template
	int counter;
	// like in a fcuntioan call
	String renderArgs;
	String renderBody;
//	private String interfaceName;
	public InnerClassMeta(String tagName, int counter, String renderArgs, String renderBody) {
		super();
		this.tagName = tagName;
		this.counter = counter;
		this.renderArgs = renderArgs;
		this.renderBody = renderBody;
//		this.interfaceName = interfaceName;
	}

	/**
	 * somthing like this: 	
	 * class Display1_Body implements DoBodyInterface{
		void render(String title) {
			pln ("The real title is: ", title);
		}
	}
	 */
	@Override
	public String toString() {
		ExprParser ep = new ExprParser(this.renderArgs);
		List<String> argTokens = ep.split();
		// something String a Date b
		assert(argTokens.size() % 2 == 0);
		
		String[] argTypes = new String[argTokens.size() /2];
		
		String classParams = "";
		for (int i = 0; i < argTypes.length; i++) {
			classParams += ", " + argTokens.get(i * 2);
		}
		
		if (Each.class.getSimpleName().equals(tagName)) {
			// append extra argument to the render method
			renderArgs += EXTRA_LOOP_ATTRS;
		}
		
		// remove the leading ,
		
		
		classParams = "<" + classParams.substring(1) + ">";

		StringBuilder sb = new StringBuilder();
		line(sb, "class " + tagName + counter + "DoBody implements " + tagName + ".DoBody" +  classParams + "{");
		line(sb, "\tpublic void render(" + renderArgs  + ") {");
		line(sb, "\t\t" + renderBody);
		line(sb, "\t}");
		line(sb, "}");
		
		// bodyclass instance
		String bodyClassName = tagName + counter +  "DoBody"; 
		String bodyField = "private " + bodyClassName +" _" + bodyClassName + 
			" = new " + bodyClassName + "();";
		line(sb, "\t" + bodyField);

		return sb.toString();
	}
	
	private void line (StringBuilder sb, String line) {
		sb.append(line + "\n");
	}
}
