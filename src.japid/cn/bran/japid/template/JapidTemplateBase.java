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

import java.io.IOException;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Method;
import java.nio.charset.Charset;
import java.util.Map;
import java.util.TreeMap;

import cn.bran.japid.classmeta.MimeTypeEnum;

/**
 * a java based template suing StringBuilder as the content buffer
 * 
 * @author bran
 * 
 */ 
public abstract class JapidTemplateBase extends JapidTemplateBaseWithoutPlay {
	public static final String CONTENT_TYPE_JSON = MimeTypeEnum.json.header;
	public static final String CONTENT_TYPE_HTML = MimeTypeEnum.html.header;
	public static final String CONTENT_TYPE_XML = MimeTypeEnum.xml.header;
	public static final String CONTENT_TYPE_TXT = MimeTypeEnum.txt.header;
	public static final String CONTENT_TYPE_CSS = MimeTypeEnum.css.header;
	
	public JapidTemplateBase(StringBuilder out) {
		super(out);
	}

	/**
	 * to keep track of all the action invocations by #{invoke} tag
	 */
	protected TreeMap<Integer, cn.bran.japid.template.ActionRunner> actionRunners = new TreeMap<Integer, cn.bran.japid.template.ActionRunner>();

	public TreeMap<Integer, cn.bran.japid.template.ActionRunner> getActionRunners() {
		return actionRunners;
	}

	public void setActionRunners(TreeMap<Integer, cn.bran.japid.template.ActionRunner> actionRunners) {
		this.actionRunners = actionRunners;
	}
}
