package cn.bran.japid.template;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.UUID;


/**
 * A template in Java. Code partly from Play! framwork
 */
public class JapidTemplate {
    public String name;
    public String source;
    public Map<Integer, Integer> linesMatrix = new HashMap<Integer, Integer>();
    public String compiledTemplateName;
    public Long timestamp = System.currentTimeMillis();

    public JapidTemplate(String name, String source) {
        this.name = name;
        this.source = source;
    }

    public JapidTemplate(String source) {
        this.name = UUID.randomUUID().toString();
        this.source = source;
    }
    public String javaSource;
    public Class<JapidTemplateBase> compiledTemplate;

}
