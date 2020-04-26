package com.litsynp;

import com.litsynp.lexer.LexicalAnalyzer;

/**
 * Main class of the compiler program.
 */
public class Main {

    /**
     * Main method of the compiler program.
     * 
     * @param args args[1] contains the input file path (default: "files/input.txt")
     */
    public static void main(String[] args) {

        // Input file name
        String fileName = null;

        // Read argument for input file name
        if (args.length > 0) {
            fileName = args[0];
        } else {
            // Default input file name
            fileName = "files\\input.txt";
        }

        // Lexically analyze the input file
        LexicalAnalyzer.lex(fileName);
    }

}
