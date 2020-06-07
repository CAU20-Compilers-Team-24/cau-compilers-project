package com.litsynp.parser.symbol;

/***
 * Defines terminals and non-terminals of the syntax analyzer.
 */
public enum Symbol {
	// Terminals
	TERM_VTYPE("vtype", true),
	TERM_NUM("num", true),
	TERM_FLOAT("float", true),
	TERM_LITERAL("literal", true),
	TERM_ID("id", true),
	TERM_IF("if", true),
	TERM_ELSE("else", true),
	TERM_WHILE("while", true),
	TERM_FOR("for", true),
	TERM_RETURN("return", true),
	TERM_ADDSUB("addsub", true),
	TERM_MULTDIV("multdiv", true),
    TERM_ASSIGN("assign", true),
    TERM_COMP("comp", true),
    TERM_SEMI("semi", true),
    TERM_COMMA("comma", true),
    TERM_LPAREN("lparen", true),
    TERM_RPAREN("rparen", true),
    TERM_LBRACE("lbrace", true),
    TERM_RBRACE("rbrace", true),
    TERM_EOF("$", true),

	// Non-terminals
    NTERM_SPRIME("S'"), // Dummy start symbol
    NTERM_CODE("CODE"),
	NTERM_VDECL("VDECL"),
	NTERM_FDECL("FDECL"),
	NTERM_ARG("ARG"),
	NTERM_MOREARGS("MOREARGS"),
	NTERM_BLOCK("BLOCK"),
	NTERM_STMT("STMT"),
	NTERM_ELSE("ELSE"),
	NTERM_ASSIGN("ASSIGN"),
	NTERM_RHS("RHS"),
	NTERM_EXPR("EXPR"),
	NTERM_TERM("TERM"),
	NTERM_FACTOR("FACTOR"),
	NTERM_COND("COND"),
	NTERM_RETURN("RETURN"),
	
	// Neither a terminal or a non-terminal, but used for parsing
	SPLITTER("|"),
	EPSILON("ϵ");
	
	/***
	 * String value of the symbol.
	 */
	public final String svalue;
	
	/***
	 * Boolean value of whether the symbol is a terminal.
	 * True: terminal
	 * False: non-terminal
	 */
	public final boolean isTerminal;
	
	/***
	 * Creates a new symbol with a string value s.
	 * 
	 * @param s String value of the terminal
	 */
	private Symbol(String s) {
		this.svalue = s;
		this.isTerminal = false;
	}
	
	/***
	 * Creates a new symbol with a string value s.
	 * 
	 * @param s String value of the terminal
	 * @param isTerminal boolean value of whether the symbol is a terminal
	 */
	private Symbol(String s, boolean isTerminal) {
		this.svalue = s;
		this.isTerminal = isTerminal;
	}
	
	/***
	 * Returns the string value of the symbol.
	 */
	public String toString() {
		return this.svalue;
	}
}
