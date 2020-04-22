package com.litsynp;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Main {

	public static void main(String[] args) {
		
		// Input file name
		String inputFileName = null;
		
		// Read argument for input file name
		if (args.length > 0){
            inputFileName = args[0];
        } else {
        	// Default input file name
        	inputFileName = "input-files\\input.txt";
        }
		
		// Symbol Table
		SymbolTable symtab = new SymbolTable();

		// Number of characters read in the file
		int readCharNum = 0;

		try {
			// Create input stream
			FileReader filereader = new FileReader(inputFileName);

			int ch = 0; // character just read
			String inputBuffer = ""; // temporary string buffer

			// Read character one by one from input stream
			while ((ch = filereader.read()) != -1) {
				readCharNum++;
				// Convert character to string
				String readCharStr = Character.toString((char) ch);

				// Debug print
				System.out.println("------ " + readCharNum + "th character ------");
				System.out.println("inputBuffer: " + inputBuffer);
				System.out.println("Character read: \'" + Character.toString((char) ch) + "\'");

				// If the read character belongs to any of the delimiters
				if (Token.isDelimiter(readCharStr)) {
					symtab.put(inputBuffer); // Put the read buffer in the symbol table
					symtab.put(readCharStr); // Put the delimiter in the symbol table

					// Clear the string buffer and read next character
					inputBuffer = "";
					continue;
				}

				// If not, add the character to the string buffer
				inputBuffer += Character.toString((char) ch);
			}
			// Put the last input string in the symbol table
			symtab.put(inputBuffer);

			filereader.close();

		} catch (FileNotFoundException e) {
			System.out.println(e);
		} catch (IOException e) {
			System.out.println(e);
		} catch (NullTokenException e) {
			System.out.println(e + " at character " + readCharNum + " in " + inputFileName);
			System.exit(1);
		}

		System.out.println("\nRead " + readCharNum + " characters from the file.\n");

		// Print information in symbol table
		symtab.printTable();
	}

}
