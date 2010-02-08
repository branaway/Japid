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
			body.render(o, i, i % 2 == 1, i == start + 1, !it.hasNext());
		}
	}

	@Override
	protected void doLayout() {
		// dummy
	}

	public static interface DoBody<E> {
		void render(E _, int _index, boolean _isOdd, boolean _first, boolean _last);
	}

}
