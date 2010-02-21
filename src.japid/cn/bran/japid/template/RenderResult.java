/**
 * Copyright 2010 Bing Ran<bing_ran@hotmail.com> 
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not 
 * use this file except in compliance with the License. You may obtain a copy 
 * of the License at http://www.apache.org/licenses/LICENSE-2.0.
 * 
 * Unless required by applicable law or agreed to in writing, software 
 * distributed under the License is distributed on an "AS IS" BASIS, 
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. 
 * See the License for the specific language governing permissions and 
 * limitations under the License. 
 */
package cn.bran.japid.template;

import java.io.Serializable;

/**
 * to wrap the result of Japid template rendering. 
 * 
 * Objects of this class can be cached.
 * 
 * @author Bing Ran<bing_ran@hotmail.com>
 * 
 */
public class RenderResult implements Serializable {
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

	/**
	 * get the interpolated content in StringBuilder. In case of nested action
	 * calls, all the content tiles are generated and interpolated
	 * 
	 * @return the fully interpolated content
	 */
	public StringBuilder getContent() {
		return content;
	}

	public long getRenderTime() {
		return this.renderTime;
	}

}
