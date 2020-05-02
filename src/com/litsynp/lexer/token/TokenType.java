package com.litsynp.lexer.token;

/**
 * Class to hold information of a token type
 */
public enum TokenType {

    // Delimiters
    WHITESPACE, // ( ' | '\t' | '\n'
    LPAREN, // {
    RPAREN, // )
    LBRACE, // {
    RBRACE, // }
    COMMA, // ,
    SEMI, // ;

    // Constants and literals
    SIGNED_ICONST, // ex) 30
    FCONST, // ex) 3.0
    LITERAL_STRING,

    // Operators
    ARITHMETIC_OP, // + | - | * | /
    BITWISE_OP, // << | >> | & | |
    COMP_OP, // < | > | == | != | <= | >=
    ASSIGN_OP, // =

    // Special cases
    VARIABLE_TYPE, // int | char | bool | float
    STATEMENT, // if | else | while | for | return
    BOOLEAN_STRING, // true | false
    IDENTIFIER, // ex) hello

    // Not yet to be determined as a token
    NOT_YET_A_TOKEN,

    // Not accepted by the DFA, needs to print error
    NOT_ACCEPTED;

    /**
     * Returns whether the token type is considered a delimiter.
     * 
     * @return boolean value of whether the token type is a delimiter
     */
    public boolean isDelimiter() {
        switch (this) {
        case WHITESPACE:
        case LPAREN:
        case RPAREN:
        case LBRACE:
        case RBRACE:
        case COMMA:
        case SEMI:
            return true;
        default:
            return false;
        }
    }

    /**
     * Returns whether a given character is considered a delimiter.
     * 
     * @param ch the candidate for a delimiter
     * @return boolean value of whether the given character is a delimiter
     */
    public static boolean isDelimiter(char ch) {
        switch (ch) {
        case ' ':
        case '\t':
        case '\n':
        case '(':
        case ')':
        case '{':
        case '}':
        case ',':
        case ';':
            return true;
        default:
            return false;
        }
    }

    /**
     * Returns the token type of a given delimiter character.
     * 
     * @param ch the delimiter character to be converted to a token type
     * @return the token type the character is converted to
     */
    public static TokenType getDelimiterTokenType(char ch) {
        switch (ch) {
        case ' ':
        case '\t':
        case '\n':
            return WHITESPACE;
        case '(':
            return LPAREN;
        case ')':
            return RPAREN;
        case '{':
            return LBRACE;
        case '}':
            return RBRACE;
        case ',':
            return COMMA;
        case ';':
            return SEMI;
        default:
            return null;
        }
    }

    /**
     * Returns string value for token type.
     * 
     * @return the token type in string value
     */
    public String toString() {
        switch (this) {
        case WHITESPACE:
            return "WHITESPACE";
        case LPAREN:
            return "LPAREN";
        case RPAREN:
            return "RPAREN";
        case LBRACE:
            return "LBRACE";
        case RBRACE:
            return "RBRACE";
        case COMMA:
            return "COMMA";
        case SEMI:
            return "SEMI";

        case SIGNED_ICONST:
            return "SIGNED_ICONST";
        case FCONST:
            return "FCONST";
        case LITERAL_STRING:
            return "LITERAL_STRING";

        case ARITHMETIC_OP:
            return "ARITHMETIC_OP";
        case BITWISE_OP:
            return "BITWISE_OP";
        case COMP_OP:
            return "COMP_OP";
        case ASSIGN_OP:
            return "ASSIGN_OP";

        case VARIABLE_TYPE:
            return "VARIABLE_TYPE";
        case STATEMENT:
            return "STATEMENT";
        case BOOLEAN_STRING:
            return "BOOLEAN_STRING";
        case IDENTIFIER:
            return "ID";

        case NOT_YET_A_TOKEN:
            return "NOT_YET_A_TOKEN";
        default:
            return "NOT_ACCEPTED";
        }
    }
}
