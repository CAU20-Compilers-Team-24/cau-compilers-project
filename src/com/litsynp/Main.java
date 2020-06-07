package com.litsynp;

import java.io.File;

import com.litsynp.lexer.LexicalAnalyzer;
import com.litsynp.parser.SyntaxAnalyzer;

/**
 * Main class of the compiler program.
 */
public class Main {

    /**
     * Main method of the compiler program.
     * 
     * @param args args[1] contains the input file path (default: "files/a.txt")
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
        
        // Syntactically analyze the input file
        SyntaxAnalyzer parser = new SyntaxAnalyzer(new File("files\\a.ser"));
        parser.parse();
    }

}
