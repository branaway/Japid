package cn.bran.japid.compiler;

import cn.bran.japid.compiler.ExprParser.Token;

public class TokenWithValue {
	public Token token;
	String value;
	public TokenWithValue(Token token, String value) {
		super();
		this.token = token;
		this.value = value;
	}
	
	@Override
	public String toString() {
		return token  + "[" + value + "]" ;
	}
}
