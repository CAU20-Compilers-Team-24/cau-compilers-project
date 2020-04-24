package com.litsynp.lexer;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import com.litsynp.lexer.state.State;
import com.litsynp.lexer.token.NullTokenException;
import com.litsynp.lexer.token.SymbolTable;
import com.litsynp.lexer.token.Token;
import com.litsynp.lexer.token.TokenType;

/**
 * Lexical analyzer that lexically analyzes an input file and creates a symbol
 * table
 */
public class LexicalAnalyzer {

	/**
	 * Lexically analyzes an input file line by line and character by character to
	 * tokenize it into a symbol table.
	 * 
	 * @param fileName the input file name to read
	 */
	public static void lex(String fileName) {

		// Symbol Table
		SymbolTable symtab = new SymbolTable();
		int lineCount = 0;

		try {
			// Create input stream
			BufferedReader reader = new BufferedReader(new FileReader(fileName));

			// String of the one whole line
			String line;

			// Read the input file line by line
			while ((line = reader.readLine()) != null) {
				System.out.println(String.format("-----Line %-4d Start---", lineCount));
				System.out.println("[Line " + lineCount + "]" + line);

				// Read character by character in the line and transition the state
				State currentState = State.START;
				String workingString = "";
				char ch = 0;

				for (int i = 0; i < line.length(); i++) {
					// Get the next input character
					ch = line.charAt(i);

					System.out.println(String.format("%4d", i) + "th character|Read ch: " + ch + "|workingString:\""
							+ workingString + "\"");

					// No interesting input received
					if ((workingString.equals("") || workingString.equals(" ")) && TokenType.isDelimiter(ch)) {
						continue;
					}

					/*
					 * If the state can be accepted and the input is a delimiter, create a
					 * corresponding token and put it into the symbol table
					 */
					if (workingString.length() > 0 && !TokenType.isDelimiter(workingString.charAt(0))
							&& TokenType.isDelimiter(ch)) {
						System.out.println("Current workingString: \"" + workingString + "\" and found a delimiter \""
								+ ch + "\"");

						symtab.put(new Token(currentState.getTokenType(), workingString));
						symtab.put(new Token(TokenType.getDelimiterTokenType(ch), Character.toString(ch)));

						// Initialize for next input word
						workingString = "";
						currentState = State.START;
						continue;
					}

					workingString = workingString + ch;
					currentState = currentState.transition(line.charAt(i));
				}
				System.out.println("End of for-loop");

				// Tokenize last word in the line
				if (workingString.length() > 0 && !TokenType.isDelimiter(workingString.charAt(0))) {
					symtab.put(new Token(currentState.getTokenType(), workingString));
				}

				// Initialize for next input word
				workingString = "";
				currentState = State.START;

				// Read next line
				lineCount = lineCount + 1;
				System.out.println(String.format("-----Line %-4d End-----", lineCount));
			}
			
			reader.close();
		} catch (FileNotFoundException e) {
			System.out.println(e);
		} catch (IOException e) {
			System.out.println(e);
		} catch (NullTokenException e) {
			System.out.println(e);
			System.exit(1);
		}
//		catch (NullTokenException e) {
//			System.out.println(e + " at character " + readCharNum + " in " + fileName);
//			System.exit(1);
//		}

		System.out.println("\nRead " + lineCount + " line(s) from the file \"" + fileName + "\".");

		// Print information in symbol table
		symtab.printTable();
	}
}
