package com.litsynp.lexer.token;

/**
 * Class to contain information about a single token, to be put in a symbol
 * table instance.
 * <p>
 * Reference {@link com.litsynp.lexer.token.SymbolTable} for symbol table class.
 */
public class Token {
    /**
     * name of the token, or token type
     */
    private TokenType name;

    /**
     * value of the token
     */
    private String value;

    /**
     * Creates a new token
     * 
     * @param name  name for the new token, or token type, defined in
     *              {@link com.litsynp.lexer.token.TokenType}.
     * @param value value of the new token
     */
    public Token(TokenType name, String value) {
        this.name = name;
        this.value = value;
    }

    /**
     * Returns the name of the token.
     * 
     * @return name of the token type
     */
    public TokenType getName() {
        return name;
    }

    /**
     * Sets the name of the token.
     * 
     * @param name new name for the token
     */
    public void setName(TokenType name) {
        this.name = name;
    }

    /**
     * Returns the value of the token.
     * 
     * @return value of the token in string
     */
    public String getValue() {
        return value;
    }

    /**
     * Sets the value of the token.
     * 
     * @param value new value for the token
     */
    public void setValue(String value) {
        this.value = value;
    }
}
