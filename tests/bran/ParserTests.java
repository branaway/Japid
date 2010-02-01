package bran;


import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import bran.japid.BranParser;

public class ParserTests {

	/**
	 * mkae sure the parser can parse value expression without {}
	 */
	@Test
	public void testSimpleExpression() {
		
		final String D = "~";
		final String L = "{";
		final String R = "}";
		
		String source = "~ ~~ ~ur	~u ~_ ~_index3\r\n ~'hello'[1..3].length.hi(foo(var+ 'sd')) etc... ~a=='a'";
//		String source = "hello ~user.name.toUpperCase()! ~user";
		String[] se = new String[] {
				"~ ~~ ", 
				"ur", 
				"	", 
				"u", 
				" ", 
				"_", 
				" ", 
				"_index3", 
				"\r\n ",  
				"'hello'[1..3].length.hi(foo(var+ 'sd'))", 
				" etc... ",
				"a=='a'",
				};
		int i = 0;
		String src = 
			se[i++] + 
			D + se[i++] + 
			se[i++] + 
			D + se[i++] +  
			se[i++] + 
			D + se[i++]+ 
			se[i++] + 
			D + se[i++] + 
			se[i++] + 
			D +  se[i++] + 
			se[i++] + 
			D +  se[i++] 
			;
		assertEquals(source, src);
		List<String> tokens = new ArrayList<String>();
		BranParser p = new BranParser(src);
		loop: for (;;) {
			BranParser.Token state = p.nextToken();
            switch (state) {
            case EOF:
                break loop;
            default:
            	String tokenstring = p.getToken();
            	tokens.add(tokenstring );
				System.out.println(state.name() + ": [" + tokenstring + "]");
            }
		}
		
		assertEquals(se.length, tokens.size());
		for (i = 0; i < se.length; i++) {
			assertEquals(se[i], tokens.get(i));
		}
	}

	@Test
	public void testDollarJQuery() {
		
		final String D = "$";
		final String L = "{";
		final String R = "}";
		
		String source = "$(doc) $('a') $'a' $doc('b')";
		List<String> tokens = new ArrayList<String>();
		BranParser p = new BranParser(source);
		loop: for (;;) {
			BranParser.Token state = p.nextToken();
			switch (state) {
			case EOF:
				break loop;
			default:
				String tokenstring = p.getToken();
				tokens.add(tokenstring );
				System.out.println(state.name() + ": [" + tokenstring + "]");
			}
		}
		
		int i = 0;
		assertEquals(4, tokens.size());
		assertEquals("$(doc) $('a') ", tokens.get(i++));
		assertEquals("'a'", tokens.get(i++));
		assertEquals(" ", tokens.get(i++));
		assertEquals("doc('b')", tokens.get(i++));
		
	}
	@Test
	public void testDollarExpression() {
		
		final String D = "$";
		final String L = "{";
		final String R = "}";
		
		String source = "$ $$ $ur	$u $_ $_index3\r\n $'hello'[1..3].length.hi(foo(var+ 'sd')) etc... $a=='a'";
//		String source = "hello $user.name.toUpperCase()! $user";
		String[] se = new String[] {
				"$ $$ ", 
				"ur", 
				"	", 
				"u", 
				" ", 
				"_", 
				" ", 
				"_index3", 
				"\r\n ",  
				"'hello'[1..3].length.hi(foo(var+ 'sd'))", 
				" etc... ",
				"a=='a'",
		};
		int i = 0;
		String src = 
			se[i++] + 
			D + se[i++] + 
			se[i++] + 
			D + se[i++] +  
			se[i++] + 
			D + se[i++]+ 
			se[i++] + 
			D + se[i++] + 
			se[i++] + 
			D +  se[i++] + 
			se[i++] + 
			D +  se[i++] 
			        ;
		assertEquals(source, src);
		List<String> tokens = new ArrayList<String>();
		BranParser p = new BranParser(src);
		loop: for (;;) {
			BranParser.Token state = p.nextToken();
			switch (state) {
			case EOF:
				break loop;
			default:
				String tokenstring = p.getToken();
				tokens.add(tokenstring );
				System.out.println(state.name() + ": [" + tokenstring + "]");
			}
		}
		
		assertEquals(se.length, tokens.size());
		for (i = 0; i < se.length; i++) {
			assertEquals(se[i], tokens.get(i));
		}
	}
	
	/**
	 * #{tag sdfsdf/} or #{tag sdfsdf} xxx #{/tag}
	 */
	@Test
	public void testTags() {
		String src = " #{tag sdfsdf/}  #{if true} xxx #{/if} #{mytag ccc} yyy #{/} ";
		List<String> tokens = new ArrayList<String>();
		BranParser p = new BranParser(src);
		loop: for (;;) {
			BranParser.Token state = p.nextToken();
            switch (state) {
            case EOF:
                break loop;
            default:
            	String tokenstring = p.getToken();
            	tokens.add(tokenstring );
				System.out.println(state.name() + ": [" + tokenstring + "]");
            }
		}
	}
}

