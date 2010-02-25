package cn.bran.japid.template;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TreeMap;


/**
 * Represented a partially rendered result at the top level. Pre-rendered
 * content is in the content in the super class, which is to be interpolated
 * with the result from each of the ActionRunners that represent dynamic content
 * that lazily generated at the moment of getContent().
 * 
 * Objects of this class can be cached and reevalued.
 * 
 * TODO: make partial render result take arguments to fine control action runners's
 * behavior, such as use different argument to the nested action.
 * 
 * @author Bing Ran<bing_ran@hotmail.com>
 * 
 */
public class RenderResultPartial extends RenderResult {
	Map<Integer, ActionRunner> actionRunners;

	public RenderResultPartial(Map<String, String> headers, StringBuilder content, long renderTime, Map<Integer, ActionRunner> actions) {
		super(headers, content, renderTime);
		this.actionRunners = actions;
		// TODO Auto-generated constructor stub
	}

	@Override
	public StringBuilder getContent() {
		// let's interpolate the static content with the result from the actions

		StringBuilder superContent = super.getContent();
		if (actionRunners != null && actionRunners.size() > 0) {
			StringBuilder sb = new StringBuilder();
			int segStart = 0;
			for (Map.Entry<Integer, ActionRunner> arEntry : actionRunners.entrySet()) {
				int pos = arEntry.getKey();
				sb.append(superContent.substring(segStart, pos));
				segStart = pos;
				ActionRunner a = arEntry.getValue();
				sb.append(a.run().getContent().toString());
			}
			sb.append(superContent.substring(segStart));
			return sb;
		} else {
			return superContent;
		}
	}

}
