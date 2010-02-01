package bran.japid;

import java.util.ArrayList;
import java.util.List;

public class LayoutClassMetaData  extends AbstractTemplateClassMetaData {

	List<String> getterMethods = new ArrayList<String>();
//	public String renderBody;

	@Override
	public String toString() {
		printHeaders();

		classDeclare();
		embedSourceTemplateName();
		buildStatics();
		addConstructors();
		
		p("\t@Override public void layout() {");
		p("\t\t" + body);
		p("\t}");
		
		for (String key : getterMethods) {
			p("\tprotected abstract void " + key + "();\n");
		}
		
		p("\tprotected abstract void doLayout();\n");

		callTags();
		
		p("}");

		return sb.toString();
	}

	// map the #{get} tag
	public void get(String string) {
		this.getterMethods.add(string);
		
	}
	
}
