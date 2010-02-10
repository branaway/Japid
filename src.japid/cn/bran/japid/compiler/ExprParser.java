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
package cn.bran.japid.compiler;

/**
 * parse out expression from a string, used in parsing the augument list to tags
 * 
 * e.g.:  a, b.foo('a'), 'asdas', arr[0] -> [a]  [b.foo('a')] ['asdas'] [arr[0]]
 */
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Template parser todo: add generics support: List<String> strings
 * 
 * the code is based on some Play! code
 * 
 * TODO: I have made this part of code fragile. Need more robust logic
 */
public class ExprParser {

	private String source;

	public ExprParser(String source) {
		this.source = source;
		this.len = source.length();
		emthodCallStackInExpr.push(Token.PLAIN);
	}

	// bran keep track of nested state tokens, eg. nested function calls in
	// expressions
	// what inside is not used for now, we only are interested in the depth
	Stack<ExprParser.Token> emthodCallStackInExpr = new Stack<ExprParser.Token>();

	//
	public enum Token {

		EOF, //
		PLAIN, //
		EXPR, // 
		NUMBER, // 
		EXPR_NATURAL_METHOD_CALL, // bran function call in expression:
		// ~user?.name.format( '###' )
		EXPR_STRING_LITERAL, // literal in mid of expr
		ARRAY_OP, // bran : ~myarray[-1].val
		STRING_LITERAL, // bran
		GENERICS, // List <xxx>
	}

	// end2/begin2 for mark the current returned token while the begin is the
	// start pos of next token
	private int end, begin, end2, begin2, len;
	private ExprParser.Token state = Token.PLAIN;

	private ExprParser.Token found(ExprParser.Token newState, int skip) {
		begin2 = begin;
		end2 = --end;
		begin = end += skip;
		ExprParser.Token lastState = state;

		// emthodCallStackInExpr.push(newState);
		state = newState;
		return lastState;
	}

	private void skip(int skip) {
		end2 = --end;
		end += skip;
	}

	public Integer getLine() {
		String token = source.substring(0, begin2);
		if (token.indexOf("\n") == -1) {
			return 1;
		} else {
			return token.split("\n").length;
		}
	}

	public String getToken() {
		return source.substring(begin2, end2);
	}

	public String checkNext() {
		if (end2 < source.length()) {
			return source.charAt(end2) + "";
		}
		return "";
	}

	public ExprParser.Token nextToken() {
		for (;;) {

			// number of unscanned chars towards the end of source
			int left = len - end;
			if (left == 0) {
				end++;
				return found(Token.EOF, 0);
			}

			char c = source.charAt(end++);
			char c1 = left > 1 ? source.charAt(end) : 0;
			char c2 = left > 2 ? source.charAt(end + 1) : 0;

			switch (state) {
			case PLAIN:
				if (Character.isJavaIdentifierStart(c)) {
					return found(Token.EXPR, 0);
				}
				if (Character.isDigit(c)) {
					return found(Token.NUMBER, 0);
				}
				if ('"' == c) {
					// this.emthodCallStackInExpr.push(Token.EXPR);
					return found(Token.STRING_LITERAL, 1);
				}
				break;
			case EXPR:
				if ('(' == c) {
					skipAhread(Token.EXPR_NATURAL_METHOD_CALL, 1);
				} else if ('[' == c) {
					skipAhread(Token.ARRAY_OP, 1);
				} else if ('<' == c) {
					skipAhread(Token.GENERICS, 1);
				} else if ('"' == c) {
					// start of literal
					skipAhread(Token.EXPR_STRING_LITERAL, 1);
				} else if (Character.isWhitespace(c) && !nextNoneSpaceCharValidExprContinuation()) {
					this.emthodCallStackInExpr.push(Token.PLAIN);
					return found(Token.PLAIN, 0); // it ea
				} else if (',' == c) {
					this.emthodCallStackInExpr.push(Token.PLAIN);
					return found(Token.PLAIN, 1); // it ea
				} else if (!Character.isWhitespace(c) && !Character.isJavaIdentifierPart(c) && c != '?' && c != '.' && c != ':' && c != '=') {
					state = Token.EXPR;
					this.emthodCallStackInExpr.push(Token.PLAIN);
					return found(Token.PLAIN, 0); // it ea
				}
				break;
			case EXPR_NATURAL_METHOD_CALL:
				if ('(' == c) {
					// nested call
					skipAhread(Token.EXPR_NATURAL_METHOD_CALL, 1);
				} else if (')' == c) {
					state = this.emthodCallStackInExpr.pop();
					skip(1);
				}
				break;
			case ARRAY_OP:
				if ('[' == c) {
					// nested call
					skipAhread(Token.ARRAY_OP, 1);
				} else if (']' == c) {
					state = this.emthodCallStackInExpr.pop();
					skip(1);
				}
				break;
			case GENERICS:
				if ('<' == c) {
					// nested generic
					skipAhread(Token.GENERICS, 1);
				} else if ('>' == c) {
					Token laststate = this.emthodCallStackInExpr.pop();
					state = laststate;
					if (laststate != Token.GENERICS) {
						// end of type declaration
						end++; // move the end position to the next char so the
								// current > is included in getToken()
						return found(Token.PLAIN, 0);
					} else {
						skip(1);
					}
				}
				break;
			case EXPR_STRING_LITERAL:
				if ('\\' == c && '"' == c1) {
					// the escaped ' in a literal string
					skip(2);
				}
				if ('"' == c) {
					state = this.emthodCallStackInExpr.pop();
					skip(1);
				}
				break;
			case STRING_LITERAL:
				if ('\\' == c && '"' == c1) {
					// the escaped ' in a literal string
					skip(2);
				}
				if ('"' == c) {
					Token prestate = this.emthodCallStackInExpr.pop();
					// end of literal
					if (Character.isJavaIdentifierStart(c1) || '.' == c1) {
						begin--;
						skipAhread(Token.EXPR, 1);
					} else {
						return found(prestate, 1);
					}
				}
				break;
			case NUMBER:
				if (!Character.isDigit(c)) {
					if (!Character.isWhitespace(c)) {
						if (',' == c) {
							return found(Token.PLAIN, 1);
						} else {
							throw new RuntimeException("bad expression: number must be terminated with a space.");
						}
					} else {
						return found(Token.PLAIN, 0);
					}
				}
			}
		}
	}

	// test if the next none space char valid as cotinuation of the current expr
	private boolean nextNoneSpaceCharValidExprContinuation() {
		char c = nextNoneSpaceChar();
		if (c == '.' || c == '[' || c == '<') {
			return true;
		} else {
			return false;
		}
	}

	private char nextNoneSpaceChar() {
		for (int p = end; p < len; p++) {
			char c = source.charAt(p);
			if (!Character.isWhitespace(c)) {
				return c;
			}
		}
		return 0;
	}

	/**
	 * use to indicate the expression goes on
	 * 
	 * @param exprNatural
	 * @param i
	 */
	private void skipAhread(ExprParser.Token exprNatural, int i) {
		this.emthodCallStackInExpr.push(state);
		state = exprNatural;
		skip(i);
	}

	void reset() {
		end = begin = end2 = begin2 = 0;
		state = Token.PLAIN;
	}

	/**
	 * return the token array
	 * 
	 * @return
	 */
	public List<String> split() {
		List<String> tl = new ArrayList<String>();
		loop: for (;;) {
			Token state = this.nextToken();
			switch (state) {
			case EOF:
				break loop;
			case STRING_LITERAL:
			case NUMBER:
			case EXPR:
				String tokenstring = this.getToken();
				tl.add(tokenstring);
				break;
			case PLAIN:
				break;
			default:
			}
		}
		return tl;
	}

}