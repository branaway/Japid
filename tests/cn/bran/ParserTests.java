package cn.bran;


import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import cn.bran.japid.compiler.JapidParser;


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
		JapidParser p = new JapidParser(src);
		loop: for (;;) {
			JapidParser.Token state = p.nextToken();
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
		JapidParser p = new JapidParser(source);
		loop: for (;;) {
			JapidParser.Token state = p.nextToken();
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
		JapidParser p = new JapidParser(src);
		loop: for (;;) {
			JapidParser.Token state = p.nextToken();
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
		JapidParser p = new JapidParser(src);
		loop: for (;;) {
			JapidParser.Token state = p.nextToken();
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

	/**
	 * a period at the end is not part of expression
	 */
	@Test
	public void periodAtExprEnd() {
		String src = "$hello.";
		List<String> tokens = new ArrayList<String>();
		JapidParser p = new JapidParser(src);
		loop: for (;;) {
			JapidParser.Token state = p.nextToken();
			switch (state) {
			case EOF:
				break loop;
			default:
				String tokenstring = p.getToken();
				tokens.add(tokenstring );
				System.out.println(state.name() + ": [" + tokenstring + "]");
			}
		}
		assertEquals(3, tokens.size());
		assertEquals("hello", tokens.get(1));
		
	}

	@Test
	public void scriptTags() {
		String src = "~[ my.java.method()]~ {% another one %}  ~{ and more}~";
		List<String> tokens = new ArrayList<String>();
		JapidParser p = new JapidParser(src);
		loop: for (;;) {
			JapidParser.Token state = p.nextToken();
			switch (state) {
			case EOF:
				break loop;
			default:
				String tokenstring = p.getToken();
				tokens.add(tokenstring );
				System.out.println(state.name() + ": [" + tokenstring + "]");
			}
		}
		assertEquals(7, tokens.size());
		assertEquals(" my.java.method()", tokens.get(1));
		assertEquals(" another one ", tokens.get(3));
		assertEquals(" and more", tokens.get(5));
		
	}
}

