package bran.play;

import play.mvc.Http.Request;
import play.mvc.Http.Response;
import play.mvc.results.Result;

/**
 * class for use to indicate that the result has been flushed to the response result 
 * 
 * @author bran
 *
 */
public class JapidEarlyResult extends Result {

	String contentType;
	
	
	public JapidEarlyResult(String contentType) {
		super();
		this.contentType = contentType;
	}


	@Override
	public void apply(Request request, Response response) {
		// do nothing the data is alreay in the response
		setContentTypeIfNotSet(response, contentType);
	}

}
