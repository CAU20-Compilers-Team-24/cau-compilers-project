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
    VAR_TYPE_Q11(TokenType.VARIABLE_TYPE),

    // Parentheses, braces, comma and semicolon
    LPAREN_Q0(TokenType.LPAREN),
    RPAREN_Q0(TokenType.RPAREN),
    COMMA_Q0(TokenType.COMMA),
    SEMI_Q0(TokenType.SEMI),

    // Operators
    ARITHMETIC_OP_Q0(TokenType.ARITHMETIC_OP),
    ARITHMETIC_OP_Q1(TokenType.ARITHMETIC_OP),

    BITWISE_OP_Q0(TokenType.BITWISE_OP),

    COMPARISON_OP_Q0,
    COMPARISON_OP_Q1(TokenType.COMP_OP),
    COMPARISON_OP_Q2(TokenType.COMP_OP),
    COMPARISON_OP_Q3(TokenType.COMP_OP),

    ASSIGN_OP_Q0(TokenType.ASSIGN_OP),

    // Boolean strings
    BOOLEAN_STRING_Q0_t,
    BOOLEAN_STRING_Q1_r,
    BOOLEAN_STRING_Q2_u,

    BOOLEAN_STRING_Q3_a,
    BOOLEAN_STRING_Q4_l,
    BOOLEAN_STRING_Q5(TokenType.BOOLEAN_STRING),

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
    STATEMENT_Q12(TokenType.STATEMENT),

    // Numbers
    SIGNED_ICONST_Q0,
    SIGNED_ICONST_Q1(TokenType.SIGNED_ICONST),
    SIGNED_ICONST_Q2(TokenType.SIGNED_ICONST),

    FCONST_Q0,
    FCONST_Q1,
    FCONST_Q2(TokenType.FCONST),

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
     * Returns whether a given character is a non-zero digit [1-9]
     * 
     * @param ch character to test whether it is a non-zero digit
     * @return boolean value of whether <b>ch</b> is a non-zero digit
     */
    public boolean isNonZeroDigit(char ch) {
        return Character.isDigit(ch) && (ch != '0');
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
            if (input == 'f')                     return VAR_TYPE_Q0_f;
            else if (input == 'i')                return VAR_TYPE_Q3_i;
            else if (input == 'c')                return VAR_TYPE_Q5_c;
            else if (input == 'b')                return VAR_TYPE_Q8_b;
            else if (input == 'e')                return STATEMENT_Q0_e;
            else if (input == 'w')                return STATEMENT_Q2_w;
            else if (input == 'r')                return STATEMENT_Q6_r;
            else if (input == 't')                return BOOLEAN_STRING_Q0_t;
            else if (input == '(')                return LPAREN_Q0;
            else if (input == ')')                return RPAREN_Q0;
            else if (input == ',')                return COMMA_Q0;
            else if (input == ';')                return SEMI_Q0;
            else if (input == '-')                return ARITHMETIC_OP_Q0;
            else if (input == '+' 
                    || input == '*' 
                    || input == '/')              return ARITHMETIC_OP_Q1;
            else if (input == '&' 
                    || input == '|')              return BITWISE_OP_Q0;
            else if (input == '!')                return COMPARISON_OP_Q0;
            else if (input == '<')                return COMPARISON_OP_Q1;
            else if (input == '>')                return COMPARISON_OP_Q2;
            else if (input == '=')                return ASSIGN_OP_Q0;
            else if (input == '0')                return SIGNED_ICONST_Q1;
            else if (isNonZeroDigit(input))       return SIGNED_ICONST_Q2;
            else                                  return NOT_ACCEPTED;

            // Variable types
        case VAR_TYPE_Q0_f:
            if (input == 'l')                     return VAR_TYPE_Q1_l;
            else if (input == 'o')                return STATEMENT_Q11_o;
            else if (input == 'a')                return BOOLEAN_STRING_Q3_a;
            else                                  return NOT_ACCEPTED;
        case VAR_TYPE_Q1_l:
            if (input == 'o')                     return VAR_TYPE_Q2_o;
            else                                  return NOT_ACCEPTED;
        case VAR_TYPE_Q2_o:
            if (input == 'a')                     return VAR_TYPE_Q4_n;
            else                                  return NOT_ACCEPTED;

        case VAR_TYPE_Q3_i:
            if (input == 'n')                     return VAR_TYPE_Q4_n;
            else if (input == 'f')                return STATEMENT_Q12;
            else                                  return NOT_ACCEPTED;
        case VAR_TYPE_Q4_n:
            if (input == 't')                     return VAR_TYPE_Q11;
            else                                  return NOT_ACCEPTED;

        case VAR_TYPE_Q5_c:
            if (input == 'h')                     return VAR_TYPE_Q6_h;
            else                                  return NOT_ACCEPTED;
        case VAR_TYPE_Q6_h:
            if (input == 'a')                     return VAR_TYPE_Q7_a;
            else                                  return NOT_ACCEPTED;
        case VAR_TYPE_Q7_a:
            if (input == 'r')                     return VAR_TYPE_Q11;
            else                                  return NOT_ACCEPTED;

        case VAR_TYPE_Q8_b:
            if (input == 'o')                     return VAR_TYPE_Q9_o;
            else                                  return NOT_ACCEPTED;
        case VAR_TYPE_Q9_o:
            if (input == 'o')                     return VAR_TYPE_Q10_o;
            else                                  return NOT_ACCEPTED;
        case VAR_TYPE_Q10_o:
            if (input == 'l')                     return VAR_TYPE_Q11;
            else                                  return NOT_ACCEPTED;

            // Operators
        case ARITHMETIC_OP_Q0:
            if (input == '0')                     return SIGNED_ICONST_Q0;
            else if (isNonZeroDigit(input))       return SIGNED_ICONST_Q2;
            else                                  return NOT_ACCEPTED;
        case COMPARISON_OP_Q0:
            if (input == '=')                     return COMPARISON_OP_Q3;
            else                                  return NOT_ACCEPTED;
        case COMPARISON_OP_Q1:
            if (input == '=')                     return COMPARISON_OP_Q3;
            else if (input == '<')                return BITWISE_OP_Q0;
            else                                  return NOT_ACCEPTED;
        case COMPARISON_OP_Q2:
            if (input == '=')                     return COMPARISON_OP_Q3;
            else if (input == '>')                return BITWISE_OP_Q0;
            else                                  return NOT_ACCEPTED;
        case ASSIGN_OP_Q0:
            if (input == '=')                     return COMPARISON_OP_Q3;
            else                                  return NOT_ACCEPTED;

            // Statements
        case STATEMENT_Q0_e:
            if (input == 'l')                     return STATEMENT_Q1_l;
            else                                  return NOT_ACCEPTED;
        case STATEMENT_Q1_l:
            if (input == 's')                     return STATEMENT_Q5_l;
            else                                  return NOT_ACCEPTED;

        case STATEMENT_Q2_w:
            if (input == 'h')                     return STATEMENT_Q3_h;
            else                                  return NOT_ACCEPTED;
        case STATEMENT_Q3_h:
            if (input == 'i')                     return STATEMENT_Q4_i;
            else                                  return NOT_ACCEPTED;
        case STATEMENT_Q4_i:
            if (input == 'l')                     return STATEMENT_Q5_l;
            else                                  return NOT_ACCEPTED;
        case STATEMENT_Q5_l:
            if (input == 'e')                     return STATEMENT_Q12;
            else                                  return NOT_ACCEPTED;

        case STATEMENT_Q6_r:
            if (input == 'e')                     return STATEMENT_Q7_e;
            else                                  return NOT_ACCEPTED;
        case STATEMENT_Q7_e:
            if (input == 't')                     return STATEMENT_Q8_t;
            else                                  return NOT_ACCEPTED;
        case STATEMENT_Q8_t:
            if (input == 'u')                     return STATEMENT_Q9_u;
            else                                  return NOT_ACCEPTED;
        case STATEMENT_Q9_u:
            if (input == 'r')                     return STATEMENT_Q10_r;
            else                                  return NOT_ACCEPTED;
        case STATEMENT_Q10_r:
            if (input == 'n')                     return STATEMENT_Q12;
            else                                  return NOT_ACCEPTED;

        case STATEMENT_Q11_o:
            if (input == 'r')                     return STATEMENT_Q12;
            else                                  return NOT_ACCEPTED;

            // Boolean strings
        case BOOLEAN_STRING_Q0_t:
            if (input == 'r')                     return BOOLEAN_STRING_Q1_r;
            else                                  return NOT_ACCEPTED;
        case BOOLEAN_STRING_Q1_r:
            if (input == 'u')                     return BOOLEAN_STRING_Q2_u;
            else                                  return NOT_ACCEPTED;
        case BOOLEAN_STRING_Q2_u:
            if (input == 'e')                     return BOOLEAN_STRING_Q5;
            else                                  return NOT_ACCEPTED;
        case BOOLEAN_STRING_Q3_a:
            if (input == 'l')                     return BOOLEAN_STRING_Q4_l;
            else                                  return NOT_ACCEPTED;
        case BOOLEAN_STRING_Q4_l:
            if (input == 's')                     return BOOLEAN_STRING_Q2_u;
            else                                  return NOT_ACCEPTED;

        case SIGNED_ICONST_Q0:
            if (input == '.')                     return FCONST_Q0;
            else                                  return NOT_ACCEPTED;
        case SIGNED_ICONST_Q1:
            if (input == '.')                     return FCONST_Q0;
            else                                  return NOT_ACCEPTED;
        case SIGNED_ICONST_Q2:
            if (input == '.')                     return FCONST_Q0;
            else if (isDigit(input))              return SIGNED_ICONST_Q2;
            else                                  return NOT_ACCEPTED;

            // Constant numbers
        case FCONST_Q0:
            if (isDigit(input))                   return FCONST_Q2;
            else                                  return NOT_ACCEPTED;
        case FCONST_Q1:
            if (input == '0')                     return FCONST_Q1;
            else if (isNonZeroDigit(input))       return FCONST_Q2;
            else                                  return NOT_ACCEPTED;
        case FCONST_Q2:
            if (input == '0')                     return FCONST_Q1;
            else if (isNonZeroDigit(input))       return FCONST_Q2;
            else                                  return NOT_ACCEPTED;

            // Not accepted
        default:
            return NOT_ACCEPTED;
        }
    }
}
