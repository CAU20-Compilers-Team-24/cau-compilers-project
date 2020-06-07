package com.litsynp.lexer;

import java.io.File;

/**
 * Main class of the compiler program.
 */
public class Main {

    /**
     * Main method of the compiler program.
     * 
     * @param args args[1] contains the input file path (default: "files/a.c")
     */
    public static void main(String[] args) {
        
        // Input file name
        String fileName = null;

        // Read argument for input file name
        if (args.length > 0) {
            fileName = args[0];
        } else {
            // Default input file name
            fileName = "files" + File.separator + "a.c";
        }

        // Lexically analyze the input file
        LexicalAnalyzer.lex(new File(fileName));
    }

}
