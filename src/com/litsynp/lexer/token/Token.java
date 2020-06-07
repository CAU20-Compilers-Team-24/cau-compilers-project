package com.litsynp.lexer.token;

import java.io.Serializable;

/**
 * Class to contain information about a single token, to be put in a symbol
 * table instance.
 * <p>
 * Reference {@link com.litsynp.lexer.token.SymbolTable} for symbol table class.
 */
public class Token implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 7623075468457151431L;

	/**
     * name of the token, or token type
     */
    private TokenType name;

    /**
     * value of the token
     */
    private String value;
    
    /**
     * line number of the token
     */
    private int lineNo;

    /**
     * Creates a new token
     * 
     * @param name  name for the new token, or token type, defined in
     *              {@link com.litsynp.lexer.token.TokenType}.
     * @param value value of the new token
     * @param lineNo line number of the new token
     */
    public Token(TokenType name, String value, int lineNo) {
        this.name = name;
        this.value = value;
        this.lineNo = lineNo;
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

    /**
     * Returns the line number of the token.
     * 
     * @return line number of the token in integer
     */
    public int getLineNo() {
        return lineNo;
    }
    
    /**
     * Returns a boolean value of whether the token is a negative number.
     * <p>
     * "Number" means having a token type of either a signed integer constant
     * (SIGNED_ICONST) or a float constant (FCONST).
     * 
     * @return boolean value of whether the token is a negative number
     */
    public boolean isNegativeNumber() {
        if ((this.getName() == TokenType.SIGNED_ICONST || this.getName() == TokenType.FCONST)
                && (this.getValue().charAt(0) == '-')) {
            return true;
        }

        return false;
    }

    /**
     * Returns a boolean value of whether the token is a number or identifier.
     * <p>
     * Identifier means having a token type of IDENTIFIER. Number means having a
     * token type of either a signed integer constant (SIGNED_ICONST) or a float
     * constant (FCONST).
     * 
     * @return boolean value of whether the token is a number or identifier
     */
    public boolean isNumberOrID() {
        return (this.getName() == TokenType.SIGNED_ICONST || this.getName() == TokenType.FCONST
                || this.getName() == TokenType.IDENTIFIER);
    }
    
    /**
     * Returns a boolean value of whether the token is a minus (-) symbol.
     * 
     * @return boolean value of whether the token is a minus symbol
     */
    public boolean isMinusSymbol() {
        if ((this.getName() == TokenType.ARITHMETIC_OP) && (this.getValue().equals("-"))) {
            return true;
        }

        return false;
    }
}
