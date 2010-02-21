package cn.bran.play;

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
	public String contentType;
	public String content;
	private RenderResult renderResult;

	public JapidResult(String contentType) {
		super();
		this.contentType = contentType;
	}

	public JapidResult(String contentType2, String string) {
		this.contentType = contentType2;
		this.content = string;
	}

	public JapidResult(RenderResult r) {
		this.renderResult = r;
		this.contentType = r.getContentType();
		StringBuilder sb = r.getContent();
		if (sb != null)
			this.content = sb.toString();
	}

	@Override
	public void apply(Request request, Response response) {
		if (this.content != null)
			try {
				Response.current().out.write(content.getBytes("UTF-8"));
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		setContentTypeIfNotSet(response, contentType);
	}

	public RenderResult getRenderResult() {
		// TODO Auto-generated method stub
		return renderResult;
	}

}
