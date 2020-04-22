package com.litsynp;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JFileChooser;

public class Main {

	public static void main(String[] args) {
		// Create file object
		File file = null;
		JFileChooser chooser = new JFileChooser();
		int returnValue = chooser.showOpenDialog(null);
		if (returnValue == JFileChooser.APPROVE_OPTION) {
		   file = chooser.getSelectedFile();
			if(file.exists()){
			}else{
				System.out.println("Wrong File");
			}
	    	}else{
	    	System.out.println("Wrong File");
	    	}
		
		// Symbol Table
		SymbolTable symtab = new SymbolTable();

		// Number of characters read in the file
		int readCharNum = 0;

		try {
			// Create input stream
			FileReader filereader = new FileReader(file);

			int ch = 0; // character just read
			String inputBuffer = ""; // temporary string buffer
			
			// true if the cursor in two double quotes, false if not
			boolean isLiteralString = false; 

			// Read character one by one from input stream
			while ((ch = filereader.read()) != -1) {
				readCharNum++;
				
				// Convert character to string
				String readCharStr = Character.toString((char) ch);

				// Debug print
				System.out.println("------ " + readCharNum + "th character ------");
				System.out.println("inputBuffer: " + inputBuffer);
				System.out.println("Character read: \'" + Character.toString((char) ch) + "\'");

				if (isLiteralString == false && Token.isDelimiter(readCharStr)) {
					// If the read character belongs to any of the delimiters
					symtab.put(inputBuffer); // Put the read buffer in the symbol table
					symtab.put(readCharStr); // Put the delimiter in the symbol table
					
					// Clear the string buffer and read next character
					inputBuffer = "";
					continue;
				}
				
				// If the sequence is not within two double quotes
				if (isLiteralString == false) {
					// If the input character is a double quote
					if (readCharStr.equals("\"")) {
						isLiteralString = true; // Mark the beginning of a literal string
					}
				} else if (readCharStr.equals("\"")) {
						// if the sequence of literal string meets its end
						inputBuffer += Character.toString((char) ch);
						
						// Read next character, with input buffer containing the full literal string
						isLiteralString = false; // Mark the end of a literal string
						continue; 
				} 

				// Add the character to the string buffer
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
			System.out.println(e + " at character " + readCharNum + " in " + file);
			System.exit(1);
		}

		System.out.println("\nRead " + readCharNum + " characters from the file.\n");

		// Print information in symbol table
		symtab.printTable();
	}

}
