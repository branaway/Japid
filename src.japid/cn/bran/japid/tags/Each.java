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
package cn.bran.japid.tags;

import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Iterator;

/**
 * a buit-in tag for looping, with helping attributes such as, index odd/even
 * line, isFirst, isLast, etc
 * 
 * @author Bing Ran<bing_ran@hotmail.com>
 * 
 */
public class Each extends cn.bran.japid.template.JapidTemplateBase {
	public static final String sourceTemplate = "tag/Each.html";

	public Each(StringBuilder out) {
		super(out);
	}

	public void render(Iterable it, DoBody body) {
		Iterator itor = it.iterator();
		itBody(body, itor);
	}

	// TODO: more polymorphic renders

	/**
	 * @param body
	 * @param it
	 */
	private void itBody(DoBody body, Iterator it) {
		int start = 0;
		int i = 0;
		while (it.hasNext()) {
			i++;
			Object o = it.next();
			boolean isOdd = i % 2 == 1;
			body.render(o, i, isOdd, isOdd?"odd":"even", i == start + 1, !it.hasNext());
		}
	}

	@Override
	protected void doLayout() {
		// dummy
	}

	public static interface DoBody<E> {
		// the _parity is for quick use as a CSS class, or one can use "${_isOdd?"odd":"even"}"
		void render(E e, int _index, boolean _isOdd, String _parity, boolean _isFirst, boolean _isLast);
	}

}
