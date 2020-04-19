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
	public void put(String input) throws NullTokenException {
		// If the input string is empty, return without putting in the symbol table
		if (input.isEmpty()) {
			return;
		}
		
		// Determine if the string matches any of the predefined regex patterns
		String tokenName = Token.getTokenName(input);

		// If token name is "NULL", it does not belong to any token type - hence, error
		if (tokenName.equals("NULL")) {
			throw new NullTokenException("Token value \"" + input + "\" is not a valid token");
		}
		
		if (tokenName.equals("WHITESPACE") == false) {
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
		System.out.println("┌────────────────────────────┬────────────────────────────┐"); // 1 + 28 + 1 + 28 + 1
		
		System.out.println(String.format("│%-28s│%-28s│", "Token Name", "Token Value"));
		
		for (int i = 0; i < tokens.size(); i++) {
			Token token = tokens.get(i); // Print i'th token in the symbol table
			System.out.println("├────────────────────────────┼────────────────────────────┤");
			System.out.println(String.format("│%-28s│%-28s│", token.getName(), token.getValue()));
		}
		
		System.out.println("└────────────────────────────┴────────────────────────────┘");
	}
}
