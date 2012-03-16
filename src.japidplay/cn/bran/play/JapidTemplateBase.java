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
package cn.bran.play;

import java.io.File;
import java.io.IOException;
import java.util.TreeMap;

import org.apache.commons.io.FileUtils;

import play.Play;
import play.Play.Mode;
import play.classloading.ApplicationClasses.ApplicationClass;
import play.exceptions.TemplateExecutionException;
import cn.bran.japid.classmeta.MimeTypeEnum;
import cn.bran.japid.template.JapidTemplateBaseWithoutPlay;

/**
 * a java based template using StringBuilder as the content buffer
 * 
 * @author bran
 * 
 */ 
public abstract class JapidTemplateBase extends JapidTemplateBaseWithoutPlay {
//	public static final String CONTENT_TYPE_JSON = MimeTypeEnum.json.header;
//	public static final String CONTENT_TYPE_HTML = MimeTypeEnum.html.header;
//	public static final String CONTENT_TYPE_XML = MimeTypeEnum.xml.header;
//	public static final String CONTENT_TYPE_TXT = MimeTypeEnum.txt.header;
//	public static final String CONTENT_TYPE_CSS = MimeTypeEnum.css.header;
	
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

	public JapidTemplateBaseWithoutPlay setActionRunners(TreeMap<Integer, cn.bran.japid.template.ActionRunner> actionRunners) {
		this.actionRunners = actionRunners;
		return this;
	}

	/**
	 * translate japid runtime exception to Play's TemplateExecutionException for formated error reporting
	 */
	@Override
	protected void handleException(RuntimeException e) {
		if (Play.mode == Mode.PROD)
			throw e;
		
		if (e instanceof TemplateExecutionException)
			throw e;
		
		// find the latest japidviews exception
		StackTraceElement[] stackTrace = e.getStackTrace();
		for (StackTraceElement ele : stackTrace){
			String className = ele.getClassName();
			if (className.startsWith("japidviews")){
				int lineNumber = ele.getLineNumber();
				// TODO: should really remove the Play reference.  Shall we jump to the file system for the source?
				ApplicationClass applicationClass = Play.classes.getApplicationClass(className);
				if (applicationClass != null){
					// let's get the line of problem
					String jsrc = applicationClass.javaSource;
					String[] splitSrc = jsrc.split("\n");
					String line = splitSrc[lineNumber - 1];
					// can we have a line marker?
					int lineMarker = line.lastIndexOf("// line ");
					if (lineMarker > 0) {
						int oriLineNumber = Integer.parseInt(line.substring(lineMarker + 8).trim());
						StackTraceElement[] newStack = new StackTraceElement[stackTrace.length + 1];
						newStack[0] = new StackTraceElement(sourceTemplate, "", sourceTemplate, oriLineNumber);
						System.arraycopy(stackTrace, 0, newStack, 1, stackTrace.length);
						e.setStackTrace(newStack);

						File file = new File("app/" + sourceTemplate);
//			
						JapidPlayTemplate jpt = new JapidPlayTemplate();
						jpt.name = sourceTemplate;
						try {
							jpt.source = FileUtils.readFileToString(file);
						} catch (IOException e1) {
							e1.printStackTrace();
						}
						throw new TemplateExecutionException(jpt, oriLineNumber, e.getMessage(), e);
					}
				}
			}
		}
		throw e;
	}

}
