package cn.bran.japid.template;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;


/**
 * A template in Java. Code partly from Play! framework
 */
public class JapidTemplate {
    public String name;
    public String source;
    
    // supposed to the mapping of the generated Java source code to the original template file
    // but since lots of complex constructs are translated from simple syntax in the original, the mapping is not really useful
    public Map<Integer, Integer> linesMatrix = new HashMap<Integer, Integer>();
    public String compiledTemplateName;
    public Long timestamp = System.currentTimeMillis();

	/**
	 * 
	 * @param name
	 *            the source file name. Used in the generated code as comment
	 *            reference. The name should start from the package root and the
	 *            path to the file will be used as the package name and the file
	 *            name as the class name.
	 * @param source
	 *            the source of the html template
	 */
    public JapidTemplate(String name, String source) {
        this.name = name;
        this.source = source;
    }

    public JapidTemplate(String source) {
        this.name = UUID.randomUUID().toString();
        this.source = source;
    }
    public String javaSource;
    public Class<JapidTemplateBaseWithoutPlay> compiledTemplate;

}
