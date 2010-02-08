package cn.bran.japid.template;

/**
 * to wrap the result of Japid template rendering
 * 
 * @author Bing Ran<bing_ran@hotmail.com>
 *
 */
public class RenderResult {
	private String contentType;
	private StringBuilder content;
	long renderTime; // in ms, for recording the time to render.

	public RenderResult(String contentType, StringBuilder content, long renderTime) {
		super();
		this.contentType = contentType;
		this.content = content;
		this.renderTime = renderTime;
	}

	public String getContentType() {
		return contentType;
	}

	
	public StringBuilder getContent() {
		return content;
	}
	
	public long getRenderTime() {
		return this.renderTime;
	}
	
}
