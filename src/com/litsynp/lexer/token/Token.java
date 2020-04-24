package com.litsynp.lexer.token;

// Class to contain information about a single token
public class Token {
	private TokenType name;
	private String value;

	public Token(TokenType name, String value) {
		this.name = name;
		this.value = value;
	}

	public TokenType getName() {
		return name;
	}

	public void setName(TokenType name) {
		this.name = name;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
}
