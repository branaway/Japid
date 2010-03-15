package cn.bran.play;

import java.util.HashMap;
import java.util.Map;

import play.mvc.Http.Header;
import play.mvc.Http.Request;
import play.mvc.Http.Response;
import play.mvc.results.Result;
import cn.bran.japid.template.RenderResult;

/**
 * class for use to indicate that the result has been flushed to the response
 * result
 * 
 * @author bran
 * 
 */
public class JapidResult extends Result {
	public static final String CONTENT_TYPE = "Content-Type";
	public static final String CACHE_CONTROL = "Cache-Control";
	public String content;
	private RenderResult renderResult;
	private Map<String, String> headers = new HashMap<String, String>();

	// public JapidResult(String contentType) {
	// super();
	// this.contentType = contentType;
	// }
	//
	// public JapidResult(String contentType2, String string) {
	// this.contentType = contentType2;
	// this.content = string;
	// }

	public JapidResult(RenderResult r) {
		this.renderResult = r;
		StringBuilder sb = r.getContent();
		if (sb != null)
			this.content = sb.toString();
		this.headers = r.getHeaders();
	}

	@Override
	public void apply(Request request, Response response) {
		if (this.content != null)
			try {
				Response.current().out.write(content.getBytes("UTF-8"));
			} catch (Exception e) {
				throw new RuntimeException(e);
			}

		Map<String, Header> resHeaders = response.headers;

		if (headers != null) {
			for (String h : headers.keySet()) {
				String value = headers.get(h);
				if (CONTENT_TYPE.equals(h)) {
					setContentTypeIfNotSet(response, value);
				} else {
					if (resHeaders.containsKey(h)) {
						// shall I override it?
						// override it. Consider the value in templates are
						// meant to override
						response.setHeader(h, value);
					} else {
						response.setHeader(h, value);
					}
				}
			}
		}
	}

	public RenderResult getRenderResult() {
		// TODO Auto-generated method stub
		return renderResult;
	}

}
