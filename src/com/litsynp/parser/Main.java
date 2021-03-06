package com.litsynp.parser;

import java.io.File;

/**
 * Main class of the compiler program.
 */
public class Main {

	/**
	 * Main method of the compiler program.
	 * 
	 * @param args args[1] contains the input file path (default: "files/a.ser")
	 */
	public static void main(String[] args) {

		// Input file name
		String fileName = null;

		// Read argument for input file name
		if (args.length > 0) {
			fileName = args[0];
		} else {
			// Default input file name
			fileName = "files" + File.separator + "a.ser";
		}

		// Syntactically analyze the input file
		SyntaxAnalyzer parser = new SyntaxAnalyzer(new File(fileName));
		boolean isAccepted = parser.parse();
	}

}
