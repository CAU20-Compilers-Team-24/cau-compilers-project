package com.litsynp.lexer.state;

import com.litsynp.lexer.token.TokenType;

/**
 * The class that holds information of a state in the deterministic finite
 * automata (DFA).
 */
public enum State {
    // Start state
    START,

    // Variable types
    VAR_TYPE_Q0_f,
    VAR_TYPE_Q1_l,
    VAR_TYPE_Q2_o,

    VAR_TYPE_Q3_i,
    VAR_TYPE_Q4_n,

    VAR_TYPE_Q5_c,
    VAR_TYPE_Q6_h,
    VAR_TYPE_Q7_a,

    VAR_TYPE_Q8_b,
    VAR_TYPE_Q9_o,
    VAR_TYPE_Q10_o,
    VAR_TYPE_Q11,
    VAR_TYPE_F(TokenType.VARIABLE_TYPE),

    // Parentheses, braces, comma and semicolon
    LPAREN_Q0,
    LPAREN_F(TokenType.LPAREN),
    
    RPAREN_Q0,
    RPAREN_F(TokenType.RPAREN),

    LBRACE_Q0,
    LBRACE_F(TokenType.LBRACE),
    
    RBRACE_Q0,
    RBRACE_F(TokenType.RBRACE),
    
    COMMA_Q0,
    COMMA_F(TokenType.COMMA),
    
    SEMI_Q0,
    SEMI_F(TokenType.SEMI),

    // Operators
    ARITHMETIC_OP_Q0,
    ARITHMETIC_OP_F(TokenType.ARITHMETIC_OP),

    BITWISE_OP_Q0,
    BITWISE_OP_F(TokenType.BITWISE_OP),

    COMPARISON_OP_Q0,
    COMPARISON_OP_Q1,
    COMPARISON_OP_Q2,
    COMPARISON_OP_Q3,
    COMPARISON_OP_F(TokenType.COMP_OP),

    ASSIGN_OP_Q0,
    ASSIGN_OP_F(TokenType.ASSIGN_OP),

    // Boolean strings
    BOOLEAN_STRING_Q0_t,
    BOOLEAN_STRING_Q1_r,
    BOOLEAN_STRING_Q2_u,

    BOOLEAN_STRING_Q3_a,
    BOOLEAN_STRING_Q4_l,
    BOOLEAN_STRING_Q5,
    BOOLEAN_STRING_F(TokenType.BOOLEAN_STRING),

    // Statements
    STATEMENT_Q0_e,
    STATEMENT_Q1_l,

    STATEMENT_Q2_w,
    STATEMENT_Q3_h,
    STATEMENT_Q4_i,
    STATEMENT_Q5_l,

    STATEMENT_Q6_r,
    STATEMENT_Q7_e,
    STATEMENT_Q8_t,
    STATEMENT_Q9_u,
    STATEMENT_Q10_r,

    STATEMENT_Q11_o,
    STATEMENT_Q12,
    STATEMENT_F(TokenType.STATEMENT),

    // Numbers
    SIGNED_ICONST_Q0,
    SIGNED_ICONST_Q1,
    SIGNED_ICONST_F(TokenType.SIGNED_ICONST),

    FCONST_Q0,
    FCONST_Q1,
    FCONST_Q2,
    FCONST_F(TokenType.FCONST),
    
    // Literal string
    LITERAL_STRING_Q0,
    LITERAL_STRING_Q1,
    LITERAL_STRING_F(TokenType.LITERAL_STRING),
    
    // Identifier
    IDENTIFIER_Q0,
    IDENTIFIER_F(TokenType.IDENTIFIER),
    
    // Whitespace
    WHITESPACE_Q0,
    WHITESPACE_F(TokenType.WHITESPACE),

    // Not accepted
    NOT_ACCEPTED(TokenType.NOT_ACCEPTED);

    /**
     * Predicted token type of the current state if the transition ends there.
     * <p>
     * If tokenType is not NOT_YET_A_TOKEN, it means this is a final state.
     */
    TokenType tokenType;

    /**
     * Creates a new non-final state.
     */
    private State() {
        this.tokenType = TokenType.NOT_YET_A_TOKEN;
    }

    /**
     * Creates a new state.
     * 
     * @param isFinalState whether the state is a final state
     * @param tokenType    token type of the state if it is finally accepted and put
     *                     into a symbol table
     */
    private State(TokenType tokenType) {
        this.tokenType = tokenType;
    }

    /**
     * Returns whether the state is acceptable.
     * 
     * @return Boolean value of whether the state is a final state
     */
    public boolean isAccepted() {
        return (tokenType != TokenType.NOT_YET_A_TOKEN);
    }

    /**
     * Returns the token type of the state
     * 
     * @return token type of the state
     */

    public TokenType getTokenType() {
        return this.tokenType;
    }

    /**
     * Returns whether a given character is a digit [0-9]
     * 
     * @param ch character to test whether it is a digit
     * @return boolean value of whether <b>ch</b> is a digit
     */
    public boolean isDigit(char ch) {
        return Character.isDigit(ch);
    }

    /**
     * Returns whether a given character is a letter [a-zA-Z]
     * 
     * @param ch character to test whether it is a letter
     * @return boolean value of whether <b>ch</b> is a letter
     */
    public boolean isLetter(char ch) {
        return Character.isLetter(ch);
    }
    
    /**
     * Returns whether a given character is considered a whitespace.
     * 
     * @param ch the candidate for a whitespace
     * @return boolean value of whether the given character is a whitespace
     */
    public static boolean isWhitespace(char ch) {
        switch (ch) {
        case ' ':
        case '\t':
        case '\n':
            return true;
        default:
            return false;
        }
    }

    /**
     * Returns whether a given character is a non-zero digit [1-9]
     * 
     * @param ch character to test whether it is a non-zero digit
     * @return boolean value of whether <b>ch</b> is a non-zero digit
     */
    public boolean isNonZeroDigit(char ch) {
        return Character.isDigit(ch) && (ch != '0');
    }
    
    /**
     * Returns whether a given character belongs to the alphabet of automata
     * 
     * @param ch character to test whether it belongs to the alphabet of automata
     * @return boolean value of whether <b>ch</b> belongs to the alphabet of automata
     */
    public boolean isAlphabet(char ch) {
        String otherAlphabet = "-+*/=!<>&|(){},;.";
        
        if (isWhitespace(ch) || isLetter(ch) || isDigit(ch)) {
            return true;
        } else {
            for (int i = 0; i < otherAlphabet.length(); i++) {
                if (ch == otherAlphabet.charAt(i)) {
                    return true;
                }
            }
            if (ch == '\"') {
                return true;
            }
            
            return false;
        }
    }
    
    /**
     * Returns whether a given character belongs to the alphabet of automata except '.' character
     * 
     * @param ch character to test whether it belongs to the alphabet of automata except '.' character
     * @return boolean value of whether <b>ch</b> belongs to the alphabet of automata except '.' character
     */
    public boolean isAlphabetExceptDot(char ch) {
        String otherAlphabet = "-+*/=!<>&|(){},;";
        
        if (isWhitespace(ch) || isLetter(ch) || isDigit(ch)) {
            return true;
        } else {
            for (int i = 0; i < otherAlphabet.length(); i++) {
                if (ch == otherAlphabet.charAt(i)) {
                    return true;
                }
            }
            if (ch == '\"') {
                return true;
            }
            
            return false;
        }
    }
    

    /**
     * Transitions a state with input to a new state.
     * <p>
     * This method holds the whole transition table of the DFA.
     * <p>
     * This transition table does not include the transitions for final state.
     * 
     * @param input the input character which the state can transition with
     * @return the next state after the transition with input
     */
    public State transition(char input) {
        switch (this) {

        // Starting state
        case START:
            if (input == 'f')                               return VAR_TYPE_Q0_f;
            else if (input == 'i')                          return VAR_TYPE_Q3_i;
            else if (input == 'c')                          return VAR_TYPE_Q5_c;
            else if (input == 'b')                          return VAR_TYPE_Q8_b;
            else if (input == 'e')                          return STATEMENT_Q0_e;
            else if (input == 'w')                          return STATEMENT_Q2_w;
            else if (input == 'r')                          return STATEMENT_Q6_r;
            else if (input == 't')                          return BOOLEAN_STRING_Q0_t;
            else if (input == '(')                          return LPAREN_Q0;
            else if (input == ')')                          return RPAREN_Q0;
            else if (input == '{')                          return LBRACE_Q0;
            else if (input == '}')                          return RBRACE_Q0;
            else if (input == ',')                          return COMMA_Q0;
            else if (input == ';')                          return SEMI_Q0;
            else if (input == '-'
                    || input == '+'
                    || input == '*'
                    || input == '/')                        return ARITHMETIC_OP_Q0;
            else if (input == '&'
                    || input == '|')                        return BITWISE_OP_Q0;
            else if (input == '!')                          return COMPARISON_OP_Q0;
            else if (input == '<')                          return COMPARISON_OP_Q1;
            else if (input == '>')                          return COMPARISON_OP_Q2;
            else if (input == '=')                          return ASSIGN_OP_Q0;
            else if (input == '0')                          return SIGNED_ICONST_Q0;
            else if (isNonZeroDigit(input))                 return SIGNED_ICONST_Q1;
            else if (input == '"')                          return LITERAL_STRING_Q0;
            else if (isLetter(input)
                    || (input == '_'))                      return IDENTIFIER_Q0;
            else if (isWhitespace(input))                   return WHITESPACE_Q0;
            else                                            return NOT_ACCEPTED;

            // Variable types
        case VAR_TYPE_Q0_f:
            if (input == 'l')                               return VAR_TYPE_Q1_l;
            else if (input == 'o')                          return STATEMENT_Q11_o;
            else if (input == 'a')                          return BOOLEAN_STRING_Q3_a;
            else if (isLetter(input)
                    || isDigit(input)
                    || (input == '_'))                      return IDENTIFIER_Q0;
            else                                            return IDENTIFIER_F;
        case VAR_TYPE_Q1_l:
            if (input == 'o')                               return VAR_TYPE_Q2_o;
            else if (isLetter(input)
                    || isDigit(input)
                    || (input == '_'))                      return IDENTIFIER_Q0;
            else                                            return IDENTIFIER_F;
        case VAR_TYPE_Q2_o:
            if (input == 'a')                               return VAR_TYPE_Q4_n;
            else if (isLetter(input)
                    || isDigit(input)
                    || (input == '_'))                      return IDENTIFIER_Q0;
            else                                            return IDENTIFIER_F;

        case VAR_TYPE_Q3_i:
            if (input == 'n')                               return VAR_TYPE_Q4_n;
            else if (input == 'f')                          return STATEMENT_Q12;
            else if (isLetter(input)
                    || isDigit(input)
                    || (input == '_'))                      return IDENTIFIER_Q0;
            else                                            return IDENTIFIER_F;
        case VAR_TYPE_Q4_n:
            if (input == 't')                               return VAR_TYPE_Q11;
            else if (isLetter(input)
                    || isDigit(input)
                    || (input == '_'))                      return IDENTIFIER_Q0;
            else                                            return IDENTIFIER_F;

        case VAR_TYPE_Q5_c:
            if (input == 'h')                               return VAR_TYPE_Q6_h;
            else if (isLetter(input)
                    || isDigit(input)
                    || (input == '_'))                      return IDENTIFIER_Q0;
            else                                            return IDENTIFIER_F;
        case VAR_TYPE_Q6_h:
            if (input == 'a')                               return VAR_TYPE_Q7_a;
            else if (isLetter(input)
                    || isDigit(input)
                    || (input == '_'))                      return IDENTIFIER_Q0;
            else                                            return IDENTIFIER_F;
        case VAR_TYPE_Q7_a:
            if (input == 'r')                               return VAR_TYPE_Q11;
            else if (isLetter(input)
                    || isDigit(input)
                    || (input == '_'))                      return IDENTIFIER_Q0;
            else                                            return IDENTIFIER_F;

        case VAR_TYPE_Q8_b:
            if (input == 'o')                               return VAR_TYPE_Q9_o;
            else if (isLetter(input)
                    || isDigit(input)
                    || (input == '_'))                      return IDENTIFIER_Q0;
            else                                            return IDENTIFIER_F;
        case VAR_TYPE_Q9_o:
            if (input == 'o')                               return VAR_TYPE_Q10_o;
            else if (isLetter(input)
                    || isDigit(input)
                    || (input == '_'))                      return IDENTIFIER_Q0;
            else                                            return IDENTIFIER_F;
        case VAR_TYPE_Q10_o:
            if (input == 'l')                               return VAR_TYPE_Q11;
            else if (isLetter(input)
                    || isDigit(input)
                    || (input == '_'))                      return IDENTIFIER_Q0;
            else                                            return IDENTIFIER_F;
        case VAR_TYPE_Q11:
            if (isLetter(input)
                    || isDigit(input)
                    || (input == '_'))                      return IDENTIFIER_Q0;
            else                                            return VAR_TYPE_F;
            
            // Parentheses, braces, comma and semicolon 
        case LPAREN_Q0:
                                                            return LPAREN_F; 
        case RPAREN_Q0:
                                                            return RPAREN_F; 
        case LBRACE_Q0:
                                                            return LBRACE_F; 
        case RBRACE_Q0:
                                                            return RBRACE_F;
        case COMMA_Q0:
                                                            return COMMA_F;
        case SEMI_Q0:
                                                            return SEMI_F;
            
            // Operators
        case ARITHMETIC_OP_Q0:
                                                            return ARITHMETIC_OP_F;
        case BITWISE_OP_Q0:
                                                            return BITWISE_OP_F;
        case COMPARISON_OP_Q0:
            if (input == '=')                               return COMPARISON_OP_Q3;
            else                                            return NOT_ACCEPTED;
        case COMPARISON_OP_Q1:
            if (input == '=')                               return COMPARISON_OP_Q3;
            else if (input == '<')                          return BITWISE_OP_Q0;
            else                                            return COMPARISON_OP_F;
        case COMPARISON_OP_Q2:
            if (input == '=')                               return COMPARISON_OP_Q3;
            else if (input == '>')                          return BITWISE_OP_Q0;
            else                                            return COMPARISON_OP_F;
        case COMPARISON_OP_Q3:
                                                            return COMPARISON_OP_F;
        case ASSIGN_OP_Q0:
            if (input == '=')                               return COMPARISON_OP_Q3;
            else                                            return ASSIGN_OP_F;

            // Statements
        case STATEMENT_Q0_e:
            if (input == 'l')                               return STATEMENT_Q1_l;
            else if (isLetter(input)
                    || isDigit(input)
                    || (input == '_'))                      return IDENTIFIER_Q0;
            else                                            return IDENTIFIER_F;
        case STATEMENT_Q1_l:
            if (input == 's')                               return STATEMENT_Q5_l;
            else if (isLetter(input)
                    || isDigit(input)
                    || (input == '_'))                      return IDENTIFIER_Q0;
            else                                            return IDENTIFIER_F;

        case STATEMENT_Q2_w:
            if (input == 'h')                               return STATEMENT_Q3_h;
            else if (isLetter(input)
                    || isDigit(input)
                    || (input == '_'))                      return IDENTIFIER_Q0;
            else                                            return IDENTIFIER_F;
        case STATEMENT_Q3_h:
            if (input == 'i')                               return STATEMENT_Q4_i;
            else if (isLetter(input)
                    || isDigit(input)
                    || (input == '_'))                      return IDENTIFIER_Q0;
            else                                            return IDENTIFIER_F;
        case STATEMENT_Q4_i:
            if (input == 'l')                               return STATEMENT_Q5_l;
            else if (isLetter(input)
                    || isDigit(input)
                    || (input == '_'))                      return IDENTIFIER_Q0;
            else                                            return IDENTIFIER_F;
        case STATEMENT_Q5_l:
            if (input == 'e')                               return STATEMENT_Q12;
            else if (isLetter(input)
                    || isDigit(input)
                    || (input == '_'))                      return IDENTIFIER_Q0;
            else                                            return IDENTIFIER_F;

        case STATEMENT_Q6_r:
            if (input == 'e')                               return STATEMENT_Q7_e;
            else if (isLetter(input)
                    || isDigit(input)
                    || (input == '_'))                      return IDENTIFIER_Q0;
            else                                            return IDENTIFIER_F;
        case STATEMENT_Q7_e:
            if (input == 't')                               return STATEMENT_Q8_t;
            else if (isLetter(input)
                    || isDigit(input)
                    || (input == '_'))                      return IDENTIFIER_Q0;
            else                                            return IDENTIFIER_F;
        case STATEMENT_Q8_t:
            if (input == 'u')                               return STATEMENT_Q9_u;
            else if (isLetter(input)
                    || isDigit(input)
                    || (input == '_'))                      return IDENTIFIER_Q0;
            else                                            return IDENTIFIER_F;
        case STATEMENT_Q9_u:
            if (input == 'r')                               return STATEMENT_Q10_r;
            else if (isLetter(input)
                    || isDigit(input)
                    || (input == '_'))                      return IDENTIFIER_Q0;
            else                                            return IDENTIFIER_F;
        case STATEMENT_Q10_r:
            if (input == 'n')                               return STATEMENT_Q12;
            else if (isLetter(input)
                    || isDigit(input)
                    || (input == '_'))                      return IDENTIFIER_Q0;
            else                                            return IDENTIFIER_F;

        case STATEMENT_Q11_o:
            if (input == 'r')                               return STATEMENT_Q12;
            else if (isLetter(input)
                    || isDigit(input)
                    || (input == '_'))                      return IDENTIFIER_Q0;
            else                                            return IDENTIFIER_F;
        case STATEMENT_Q12:
            if (isLetter(input)
                    || isDigit(input)
                    || (input == '_'))                      return IDENTIFIER_Q0;
            else                                            return STATEMENT_F;

            // Boolean strings
        case BOOLEAN_STRING_Q0_t:
            if (input == 'r')                               return BOOLEAN_STRING_Q1_r;
            else if (isLetter(input)
                    || isDigit(input)
                    || (input == '_'))                      return IDENTIFIER_Q0;
            else                                            return IDENTIFIER_F;
        case BOOLEAN_STRING_Q1_r:
            if (input == 'u')                               return BOOLEAN_STRING_Q2_u;
            else if (isLetter(input)
                    || isDigit(input)
                    || (input == '_'))                      return IDENTIFIER_Q0;
            else                                            return IDENTIFIER_F;
        case BOOLEAN_STRING_Q2_u:
            if (input == 'e')                               return BOOLEAN_STRING_Q5;
            else if (isLetter(input)
                    || isDigit(input)
                    || (input == '_'))                      return IDENTIFIER_Q0;
            else                                            return IDENTIFIER_F;
        case BOOLEAN_STRING_Q3_a:
            if (input == 'l')                               return BOOLEAN_STRING_Q4_l;
            else if (isLetter(input)
                    || isDigit(input)
                    || (input == '_'))                      return IDENTIFIER_Q0;
            else                                            return IDENTIFIER_F;
        case BOOLEAN_STRING_Q4_l:
            if (input == 's')                               return BOOLEAN_STRING_Q2_u;
            else if (isLetter(input)
                    || isDigit(input)
                    || (input == '_'))                      return IDENTIFIER_Q0;
            else                                            return IDENTIFIER_F;
        case BOOLEAN_STRING_Q5:
            if (isLetter(input)
                    || isDigit(input)
                    || (input == '_'))                      return IDENTIFIER_Q0;
            else                                            return BOOLEAN_STRING_F;

        case SIGNED_ICONST_Q0:
            if (input == '.')                               return FCONST_Q0;
            else                                            return SIGNED_ICONST_F;
        case SIGNED_ICONST_Q1:
            if (input == '.')                               return FCONST_Q0;
            else if (isDigit(input))                        return SIGNED_ICONST_Q1;
            else                                            return SIGNED_ICONST_F;

            // Constant numbers
        case FCONST_Q0:
            if (isDigit(input))                             return FCONST_Q2;
            else                                            return NOT_ACCEPTED;
        case FCONST_Q1:
            if (input == '0')                               return FCONST_Q1;
            else if (isNonZeroDigit(input))                 return FCONST_Q2;
            else                                            return NOT_ACCEPTED;
        case FCONST_Q2:
            if (input == '0')                               return FCONST_Q1;
            else if (isNonZeroDigit(input))                 return FCONST_Q2;
            else                                            return FCONST_F;

            // Literal string
        case LITERAL_STRING_Q0:
            if (isDigit(input)
                    || isLetter(input)
                    || isWhitespace(input))                 return LITERAL_STRING_Q0;
            else if (input == '"')                          return LITERAL_STRING_Q1;
            else                                            return NOT_ACCEPTED;
        case LITERAL_STRING_Q1:
                                                            return LITERAL_STRING_F;
            
            // Identifier
        case IDENTIFIER_Q0:
            if (isLetter(input)
                    || isDigit(input)
                    || (input == '_'))                      return IDENTIFIER_Q0;
            else                                            return IDENTIFIER_F;
            
            // Whitespace
        case WHITESPACE_Q0:
                                                            return WHITESPACE_F;
            
            // Not accepted
        default:
                                                            return NOT_ACCEPTED;
        }
    }
}
