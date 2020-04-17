package com.litsynp;

import java.util.ArrayList;

// Class to hold information about accumulated tokens
public class SymbolTable {
	private ArrayList<Token> tokens;
	
	public SymbolTable() {
		tokens = new ArrayList<Token>();
	}
	
	// Put token into the symbol table
	public void putToken(Token token) {
		tokens.add(token);
	}
	
	// Determine if the input string belongs to any token pattern, and put in the table if it is
	// If not, the input is not put in the table
	public void put(String input) {
		// Determine if the string matches any of the predefined regex patterns
		String tokenName = Token.getTokenName(input);
		if (tokenName.equals("NONE") == false) {
			this.putToken(new Token(tokenName, input));
		}
	}
	
	// Remove the last token from the symbol table and return the removed token
	public Token pop() {
		int size = tokens.size();
		
		Token delToken = tokens.get(size - 1);
		String delTokenName = delToken.getName();
		String delTokenValue = delToken.getValue();

		tokens.remove(size - 1);
		return new Token(delTokenName, delTokenValue); // return a clone of the deleted token
	}
	
	// Get index'th symbol in the symbol table
	public Token get(int index) {
		return tokens.get(index);
	}
	
	// Get a reference of the last token in the symbol table
	public Token getLast() {
		int size = tokens.size();
		return tokens.get(size - 1);
	}
	
	// Print all of the tokens in the symbol table
	public void printTable() {
		System.out.println("忙式式式式式式式式式式式式式式式式式式式式式式式式式式式式成式式式式式式式式式式式式式式式式式式式式式式式式式式式式忖"); // 1 + 28 + 1 + 28 + 1
		
		System.out.println(String.format("弛%-28s弛%-28s弛", "Token Name", "Token Value"));
		
		for (int i = 0; i < tokens.size(); i++) {
			Token token = tokens.get(i); // Print i'th token in the symbol table
			System.out.println("戍式式式式式式式式式式式式式式式式式式式式式式式式式式式式托式式式式式式式式式式式式式式式式式式式式式式式式式式式式扣");
			System.out.println(String.format("弛%-28s弛%-28s弛", token.getName(), token.getValue()));
		}
		
		System.out.println("戌式式式式式式式式式式式式式式式式式式式式式式式式式式式式扛式式式式式式式式式式式式式式式式式式式式式式式式式式式式戎");
	}
}
