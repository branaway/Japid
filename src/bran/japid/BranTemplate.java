package bran.japid;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

/**
 * A template in Java. Should probably do away with parent Template which is Groovy based
 */
public class BranTemplate {
    public String name;
    public String source;
    public Map<Integer, Integer> linesMatrix = new HashMap<Integer, Integer>();
    public Set<Integer> doBodyLines = new HashSet<Integer>();
    public String compiledTemplateName;
    public Long timestamp = System.currentTimeMillis();

    public BranTemplate(String name, String source) {
        this.name = name;
        this.source = source;
    }

    public BranTemplate(String source) {
        this.name = UUID.randomUUID().toString();
        this.source = source;
    }
    public String javaSource;
    public Class<BranTemplateBase> compiledTemplate;

}
