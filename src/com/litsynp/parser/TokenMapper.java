package com.litsynp.parser;

import com.litsynp.lexer.token.Token;

/***
 * Maps a token into a terminal symbol to be used in the syntax analyzer
 */
public class TokenMapper {
	public static Symbol convertToken(Token token) {
		Symbol symbol = null;

		String tokenValue = token.getValue();

		switch (token.getName()) {
		case LPAREN:
			symbol = Symbol.TERM_LPAREN;
			break;
		case RPAREN:
			symbol = Symbol.TERM_RPAREN;
			break;
		case LBRACE:
			symbol = Symbol.TERM_LBRACE;
			break;
		case RBRACE:
			symbol = Symbol.TERM_RBRACE;
			break;
		case COMMA:
			symbol = Symbol.TERM_COMMA;
			break;
		case SEMI:
			symbol = Symbol.TERM_SEMI;
			break;
		case SIGNED_ICONST:
			symbol = Symbol.TERM_NUM;
			break;
		case FCONST:
			symbol = Symbol.TERM_FLOAT;
			break;
		case LITERAL_STRING:
			symbol = Symbol.TERM_LITERAL;
			break;
		case ARITHMETIC_OP:
			if (tokenValue.equals("+") || tokenValue.equals("-")) {
				symbol = Symbol.TERM_ADDSUB;
			} else {
				symbol = Symbol.TERM_MULTDIV;
			}
			break;
		case COMP_OP:
			symbol = Symbol.TERM_COMP;
			break;
		case ASSIGN_OP:
			symbol = Symbol.TERM_ASSIGN;
			break;
		case VARIABLE_TYPE:
			symbol = Symbol.TERM_VTYPE;
			break;
		case STATEMENT:
			if (token.getValue().equals("if")) {
				symbol = Symbol.TERM_IF;
			} else if (token.getValue().equals("else")) {
				symbol = Symbol.TERM_ELSE;
			} else if (token.getValue().equals("while")) {
				symbol = Symbol.TERM_WHILE;
			} else if (token.getValue().equals("for")) {
				symbol = Symbol.TERM_FOR;
			} else {
				symbol = Symbol.TERM_RETURN;
			}
			break;
		case IDENTIFIER:
			symbol = Symbol.TERM_ID;
			break;
		case BITWISE_OP:
		case BOOLEAN_STRING:
		default:
			System.out.println("Error while converting {" + token.getName() + " : " + token.getValue() + "}");
			symbol = null;
		}

		return symbol;
	}
}
