package cn.bran;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import cn.bran.japid.compiler.ExprParser;
import cn.bran.japid.compiler.TokenWithValue;
import cn.bran.japid.compiler.ExprParser.Token;



public class ExprParserTests {

	@Test
	public void testParse1() {
		String src = "a, b.f(\"a\"), \"cc\" \"a\".toUpper(), 123 arr[0].name ";
		ExprParser p = new ExprParser(src);
		loop: for (;;) {
			Token state = p.nextToken();
            switch (state) {
            case EOF:
                break loop;
            case STRING_LITERAL:
            case NUMBER:
            case EXPR:
            	String tokenstring = p.getToken();
//            	tokens.add(tokenstring );
            	System.out.println(state.name() + ": [" + tokenstring + "]");
            	break;
            case PLAIN:
            	break;
            default:
            }
		}
	}

	@Test
	public void testParseArgs() {
		String src = "String s, java.util.Date d";
		ExprParser p = new ExprParser(src);
		List<String> argTokens = p.split();
		assertEquals("String", argTokens.get(0));
		assertEquals("s", argTokens.get(1));
		assertEquals("java.util.Date", argTokens.get(2));
		assertEquals("d", argTokens.get(3));
	}
	
	
	@Test
	public void testParseGenricsInParamList() {
		String src = "List<String> s, List < java.util.Date>d";
		ExprParser p = new ExprParser(src);
		List<String> argTokens = p.split();
		assertEquals("List<String>", argTokens.get(0));
		assertEquals("s", argTokens.get(1));
		assertEquals("List < java.util.Date>", argTokens.get(2));
		assertEquals("d", argTokens.get(3));
	}
	
}
