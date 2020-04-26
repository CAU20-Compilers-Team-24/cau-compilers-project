package com.litsynp.lexer.token;

import java.util.ArrayList;

// Class to hold information about accumulated tokens
public class SymbolTable {
    private ArrayList<Token> tokens;

    public SymbolTable() {
        tokens = new ArrayList<Token>();
    }

    // Put token into the symbol table
    public void put(Token token) throws NullTokenException {

        // If the token is a whitespace, skip
        if (token.getName() == TokenType.WHITESPACE) {
            return;
        }

        // If the token is not accepted, print error
        if (token.getName() == null || (token.getName() == TokenType.NOT_ACCEPTED)) {
            throw new NullTokenException(
                    "{KEY=" + token.getName() + ":VALUE=" + token.getValue() + "} is not accepted");
        }

        // If the type is yet to be defined, but attempted to be put in the table, print
        // error
        else if (token.getName() == TokenType.NOT_YET_A_TOKEN) {
            throw new NullTokenException("{KEY=" + token.getName() + ":VALUE=" + token.getValue()
                    + "} is not yet a token and hence not accepted");
        }

        // If not, put the token in the symbol table
        else {
            System.out.println("Successively put {KEY=" + token.getName() + ":VALUE=" + token.getValue()
                    + "} into the symbol table.");
            tokens.add(token);
            return;
        }
    }

    // Remove the last token from the symbol table and return the removed token
    public Token pop() {
        int size = tokens.size();

        Token delToken = tokens.get(size - 1);
        TokenType delTokenName = delToken.getName();
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
