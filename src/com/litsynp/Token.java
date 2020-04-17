package com.litsynp;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

// Class to contain information about a single token
public class Token {
	private String name;
	private String value;

	// Predefined token patterns
	public static String whitespacePattern = "\\s";

	private static String delimiterPattern = "[\\(\\)\\{\\};,]";

	private static String digitPattern = "[0..9]";

	private static String variableTypePattern = "int|char|bool|float";
	private static String statementPattern = "if|else|while|for|return";

	private static String bracePattern = "{|}"; // area or scope of variables and functions
	private static String parenPattern = "\\(|\\)"; // function or statement

	private static Pattern pattern;
	private static Matcher match;

	// Returns true if a given string matches with one whitespace character
	public static boolean isWhitespace(String input) {
		pattern = Pattern.compile(whitespacePattern);
		match = pattern.matcher(input);

		if (match.matches()) {
			return true;
		}

		return false;
	}

	public static boolean isDelimiter(String input) {
		pattern = Pattern.compile(delimiterPattern);
		match = pattern.matcher(input);

		if (match.matches()) {
			return true;
		}

		return false;
	}

	// If tokenCandidate matches with any of the defined pattern, returns the token
	// name (type)
	public static String getTokenName(String tokenCandidate) {

		System.out.println("getTokenName(\"" + tokenCandidate + "\")");

		pattern = Pattern.compile(variableTypePattern);
		match = pattern.matcher(tokenCandidate);
		if (match.matches()) {
			return "VARIABLE_TYPE";
		}

		pattern = Pattern.compile(statementPattern);
		match = pattern.matcher(tokenCandidate);
		if (match.matches()) {
			return "STATEMENT";
		}

		if (tokenCandidate.equals("(")) {
			return "LPAREN";
		}

		if (tokenCandidate.equals(")")) {
			return "RPAREN";
		}

		if (tokenCandidate.equals("{")) {
			return "LBRACE";
		}

		if (tokenCandidate.equals("}")) {
			return "RBRACE";
		}
		
		if (tokenCandidate.equals(",")) {
			return "COMMA";
		}
		
		if (tokenCandidate.equals(";")) {
			return "SEMI";
		}

		return "NONE"; // belongs to none of the patterns
	}

	public Token(String name, String value) {
		this.name = name;
		this.value = value;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
}
