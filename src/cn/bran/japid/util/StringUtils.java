package cn.bran.japid.util;

import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.CharacterCodingException;
import java.nio.charset.Charset;
import java.nio.charset.CharsetEncoder;
import java.nio.charset.CoderResult;
import java.nio.charset.CodingErrorAction;

/**
 * basically
 * 
 * @author bran
 * 
 */
public class StringUtils {

	static CharsetEncoder ce;
	static {
		Charset cs = Charset.forName("UTF-8");
		ce = cs.newEncoder().onMalformedInput(CodingErrorAction.REPLACE).onUnmappableCharacter(CodingErrorAction.REPLACE);
	}

	/**
	 * try to encode a char[] as fast as possible for later use in outputstream
	 * 
	 * @param ca
	 * @param off
	 * @param len
	 * @return
	 */
	static public ByteBuffer encodeUTF8(String src) {
		// char[] ca = src.toCharArray();
		int len = src.length();
		int off = 0;
		int en = (int) (len * ce.maxBytesPerChar());
		byte[] ba = new byte[en];
		if (len == 0)
			return null;

		ce.reset();
		ByteBuffer bb = ByteBuffer.wrap(ba);
		CharBuffer cb = CharBuffer.wrap(src, off, len);
		try {
			CoderResult cr = ce.encode(cb, bb, true);
			if (!cr.isUnderflow())
				cr.throwException();
			cr = ce.flush(bb);
			if (!cr.isUnderflow())
				cr.throwException();
			return bb;
		} catch (CharacterCodingException x) {
			// Substitution is always enabled,
			// so this shouldn't happen
			throw new Error(x);
		}
	}

}
