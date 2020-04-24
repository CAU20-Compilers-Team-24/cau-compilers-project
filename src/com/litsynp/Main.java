package com.litsynp;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import com.litsynp.lexer.LexicalAnalyzer;

public class Main {

	public static void main(String[] args) {
		
		// Input file name
		String fileName = null;
		
		// Read argument for input file name
		if (args.length > 0){
            fileName = args[0];
        } else {
        	// Default input file name
        	fileName = "input-files\\input.txt";
        }

		LexicalAnalyzer.lex(fileName);
		
	}

}
