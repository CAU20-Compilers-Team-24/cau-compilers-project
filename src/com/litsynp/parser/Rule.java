package com.litsynp.parser;

import java.util.HashMap;
import java.util.Map;

import com.litsynp.parser.symbol.Symbol;

/***
 * Defines the CFG production rules of the syntax analyzer.
 */
public enum Rule {
	rule1(1, Symbol.NTERM_SPRIME, new Symbol[] { Symbol.NTERM_CODE }),
	rule2(2, Symbol.NTERM_CODE, new Symbol[] { Symbol.NTERM_VDECL, Symbol.NTERM_CODE }),
	rule3(3, Symbol.NTERM_CODE, new Symbol[] { Symbol.NTERM_FDECL, Symbol.NTERM_CODE }),
	rule4(4, Symbol.NTERM_CODE, new Symbol[] { Symbol.EPSILON }),
	rule5(5, Symbol.NTERM_VDECL, new Symbol[] { Symbol.TERM_VTYPE, Symbol.TERM_ID, Symbol.TERM_SEMI }),
	rule6(6, Symbol.NTERM_VDECL, new Symbol[] { Symbol.TERM_VTYPE, Symbol.NTERM_ASSIGN, Symbol.TERM_SEMI }),
	rule7(7, Symbol.NTERM_ASSIGN, new Symbol[] { Symbol.TERM_ID, Symbol.TERM_ASSIGN, Symbol.NTERM_RHS }),
	rule8(8, Symbol.NTERM_VDECL,
			new Symbol[] { Symbol.TERM_VTYPE, Symbol.TERM_ID, Symbol.TERM_LPAREN, Symbol.NTERM_ARG, Symbol.TERM_RPAREN,
					Symbol.TERM_LBRACE, Symbol.NTERM_BLOCK, Symbol.NTERM_RETURN, Symbol.TERM_RBRACE }),
	rule9(9, Symbol.NTERM_ARG, new Symbol[] { Symbol.TERM_VTYPE, Symbol.TERM_ID, Symbol.NTERM_MOREARGS }),
	rule10(10, Symbol.NTERM_ARG, new Symbol[] { Symbol.EPSILON }),
	rule11(11, Symbol.NTERM_MOREARGS,
			new Symbol[] { Symbol.TERM_COMMA, Symbol.TERM_VTYPE, Symbol.TERM_ID, Symbol.NTERM_MOREARGS }),
	rule12(12, Symbol.NTERM_MOREARGS, new Symbol[] { Symbol.EPSILON }),
	rule13(13, Symbol.NTERM_BLOCK, new Symbol[] { Symbol.NTERM_STMT, Symbol.NTERM_BLOCK }),
	rule14(14, Symbol.NTERM_BLOCK, new Symbol[] { Symbol.EPSILON }),
	rule15(15, Symbol.NTERM_STMT, new Symbol[] { Symbol.NTERM_VDECL }),
	rule16(16, Symbol.NTERM_STMT, new Symbol[] { Symbol.NTERM_ASSIGN, Symbol.TERM_SEMI }),
	rule17(17, Symbol.NTERM_STMT,
			new Symbol[] { Symbol.TERM_IF, Symbol.TERM_LPAREN, Symbol.NTERM_COND, Symbol.TERM_RPAREN,
					Symbol.TERM_LBRACE, Symbol.NTERM_BLOCK, Symbol.TERM_RBRACE, Symbol.NTERM_ELSE }),
	rule18(18, Symbol.NTERM_STMT,
			new Symbol[] { Symbol.TERM_WHILE, Symbol.TERM_LPAREN, Symbol.NTERM_COND, Symbol.TERM_RPAREN,
					Symbol.TERM_LBRACE, Symbol.NTERM_BLOCK, Symbol.TERM_RBRACE }),
	rule19(19, Symbol.NTERM_STMT,
			new Symbol[] { Symbol.TERM_FOR, Symbol.TERM_LPAREN, Symbol.NTERM_ASSIGN, Symbol.TERM_SEMI,
					Symbol.NTERM_COND, Symbol.TERM_SEMI, Symbol.NTERM_ASSIGN, Symbol.TERM_RPAREN, Symbol.TERM_LBRACE,
					Symbol.NTERM_BLOCK, Symbol.TERM_RBRACE }),
	rule20(20, Symbol.NTERM_ELSE,
			new Symbol[] { Symbol.TERM_ELSE, Symbol.TERM_LBRACE, Symbol.NTERM_BLOCK, Symbol.TERM_RBRACE }),
	rule21(21, Symbol.NTERM_ELSE, new Symbol[] { Symbol.EPSILON }),
	rule22(22, Symbol.NTERM_RHS, new Symbol[] { Symbol.NTERM_EXPR }),
	rule23(23, Symbol.NTERM_RHS, new Symbol[] { Symbol.TERM_LITERAL }),
	rule24(24, Symbol.NTERM_EXPR, new Symbol[] { Symbol.NTERM_TERM, Symbol.TERM_ADDSUB, Symbol.NTERM_EXPR }),
	rule25(25, Symbol.NTERM_EXPR, new Symbol[] { Symbol.NTERM_TERM }),
	rule26(26, Symbol.NTERM_TERM, new Symbol[] { Symbol.NTERM_FACTOR, Symbol.TERM_MULTDIV, Symbol.NTERM_TERM }),
	rule27(27, Symbol.NTERM_TERM, new Symbol[] { Symbol.NTERM_FACTOR }),
	rule28(28, Symbol.NTERM_FACTOR, new Symbol[] { Symbol.TERM_LPAREN, Symbol.NTERM_EXPR, Symbol.TERM_RPAREN }),
	rule29(29, Symbol.NTERM_FACTOR, new Symbol[] { Symbol.TERM_ID }),
	rule30(30, Symbol.NTERM_FACTOR, new Symbol[] { Symbol.TERM_NUM }),
	rule31(31, Symbol.NTERM_FACTOR, new Symbol[] { Symbol.TERM_FLOAT }),
	rule32(32, Symbol.NTERM_COND, new Symbol[] { Symbol.NTERM_FACTOR, Symbol.TERM_COMP, Symbol.NTERM_FACTOR }),
	rule33(33, Symbol.NTERM_RETURN, new Symbol[] { Symbol.TERM_RETURN, Symbol.NTERM_FACTOR, Symbol.TERM_SEMI });

	/***
	 * The rule number that is referenced in the SLR parsing table.
	 */
	public int ruleNumber;

	/***
	 * Head part of a CFG production rule.
	 * <p>
	 * Head can only be a non-terminal symbol.
	 * <p>
	 * "HEAD" -> BODY
	 */
	public Symbol head;

	/***
	 * Body part of a CFG production rule.
	 * <p>
	 * Consists of a list of symbols that can be either a non-terminal or a
	 * terminal.
	 * <p>
	 * HEAD -> "BODY"
	 */
	public Symbol[] body;

	/***
	 * Hash map to find a rule by the rule number.
	 */
	private static Map<Integer, Rule> map = new HashMap<Integer, Rule>();

	static {
		for (Rule rule : Rule.values()) {
			map.put(rule.ruleNumber, rule);
		}
	}

	/***
	 * Creates a production rule of the syntax analyzer.
	 * 
	 * @param ruleNumber the rule number
	 * @param head the head part of the production rule, consisting of a non-terminal
	 * @param body the body part of the production rule, consisting of terminals and non-terminals
	 */
	private Rule(int ruleNumber, Symbol head, Symbol[] body) {
		this.ruleNumber = ruleNumber;
		this.head = head;
		this.body = body;
	}

	/***
	 * Finds the rule by the rule number.
	 * 
	 * @param ruleNumber the rule number
	 * @return the rule enum
	 */
	public static Rule valueOf(int ruleNumber) {
		return map.get(ruleNumber);
	}

	/***
	 * Returns string value of HEAD -> BODY.
	 */
	@Override
	public String toString() {
		String bodyString = "";
		for (int i = 0; i < body.length - 1; i++) {
			bodyString += body[i] + " ";
		}
		bodyString += body[body.length - 1];

		return "rule" + ruleNumber + ": " + head + " -> " + bodyString;
	}
}
