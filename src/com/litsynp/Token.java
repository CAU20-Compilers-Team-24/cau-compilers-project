package com.litsynp;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

// Class to contain information about a single token
public class Token {
	private String name;
	private String value;

	// Predefined token patterns
	private static String delimiterPattern = "[\\s\\(\\)\\{\\};,]";

	private static String digitPattern = "[0-9]";
	private static String signedIconstPattern = "0|(-|)([1-9][0-9]*)";
	private static String fconstPattern = "(-|)(0|[1-9][0-9]*)\\.(0|[0-9]*[1-9])";
	private static String literalStringPattern = "\"[^\"]*\"";
	
	private static String arithmeticPattern = "[+]|[-]|[*]|[/]";
	private static String bitwisePattern = "<<|>>|[&]|[|]";
	private static String comparisonPattern = "<|>|==|!=|<=|>=";
	
	private static String variableTypePattern = "int|char|bool|float";
	private static String statementPattern = "if|else|while|for|return";
	private static String booleanStringPattern = "true|false";

	private static Pattern pattern;
	private static Matcher match;
	
	// Returns true if a given string matches with one whitespace character
	public static boolean isDelimiter(String input) {
		// TODO: Add operators
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
		
		pattern = Pattern.compile(booleanStringPattern);
		match = pattern.matcher(tokenCandidate);
		if (match.matches()) {
			return "BOOLEAN_STRING";
		}
		
		pattern = Pattern.compile(signedIconstPattern);
		match = pattern.matcher(tokenCandidate);
		if (match.matches()) {
			return "SIGNED_ICONST";
		}
		
		pattern = Pattern.compile(fconstPattern);
		match = pattern.matcher(tokenCandidate);
		if (match.matches()) {
			return "FCONST";
		}
		
		pattern = Pattern.compile(literalStringPattern);
		match = pattern.matcher(tokenCandidate);
		if (match.matches()) {
			return "LITERAL_STRING";
		}
		
		// Check for operators
		pattern = Pattern.compile(arithmeticPattern);
		match = pattern.matcher(tokenCandidate);
		if (match.matches()) {
			return "ARITHMETIC_OPERATOR";
		}
		
		pattern = Pattern.compile(bitwisePattern);
		match = pattern.matcher(tokenCandidate);
		if (match.matches()) {
			return "BITWISE_OPERATOR";
		}
		
		pattern = Pattern.compile(comparisonPattern);
		match = pattern.matcher(tokenCandidate);
		if (match.matches()) {
			return "COMP_OPERATOR";
		}
		
		if (tokenCandidate.equals("=")) {
			return "ASSIGN";
		}
		
		// Check for delimiters
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

		// "WHITESPACE" is not put in the symbol table
		pattern = Pattern.compile("\\s");
		match = pattern.matcher(tokenCandidate);
		if (match.matches()) {
			return "WHITESPACE";
		}

		// "NONE" means to print errors
		return "NONE";
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
