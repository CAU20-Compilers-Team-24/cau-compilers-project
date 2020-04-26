package com.litsynp.lexer;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;

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
        int charCount = 0;

        try {
            // Create input stream
            BufferedReader reader = new BufferedReader(new FileReader(fileName));

            // String of the one whole line
            String line;

            // Read the input file line by line
            while ((line = reader.readLine()) != null) {
                System.out.println(String.format("[Line %3d START]", lineCount));

                // Read character by character in the line and transition the state
                State currentState = State.START;
                String workingString = "";
                char ch = 0;

                for (charCount = 0; charCount < line.length(); charCount++) {
                    // Get the next input character
                    ch = line.charAt(charCount);

                    System.out.println(String.format("[Line %3d] %3d", lineCount, charCount)
                            + "th character|Read character:\'" + ch + "\'|Current String:\"" + workingString + "\"");

                    // No interesting input received
                    if ((workingString.equals("") || workingString.equals(" ")) && TokenType.isWhitespace(ch)) {
                        continue;
                    }

                    /*
                     * If the state can be accepted and the input is a delimiter, create a
                     * corresponding token and put it into the symbol table
                     */
                    if (workingString.length() > 0 && TokenType.isDelimiter(ch)) {
                        System.out.println("Current workingString: \"" + workingString + "\" and found a delimiter \""
                                + ch + "\"");

                        symtab.put(new Token(currentState.getTokenType(), workingString));
                        symtab.put(new Token(TokenType.getDelimiterTokenType(ch), Character.toString(ch)));

                        // Initialize for next input word
                        workingString = "";
                        currentState = State.START;
                        continue;
                    }

                    // Also tokenize the last word in the line
                    if (charCount == line.length() - 1) {
                        workingString = workingString + ch;
                        currentState = currentState.transition(line.charAt(charCount));
                        symtab.put(new Token(currentState.getTokenType(), workingString));
                    }

                    workingString = workingString + ch;
                    currentState = currentState.transition(line.charAt(charCount));
                }

                // Prepare for next line
                lineCount = lineCount + 1;
            }

            reader.close();
        } catch (FileNotFoundException e) {
            System.out.println(e);
        } catch (IOException e) {
            System.out.println(e);
        } catch (NullTokenException e) {
            System.out.println(e + " at character " + charCount + " in line " + lineCount + " in " + fileName);
            System.exit(1);
        }

        System.out.println("\nRead " + lineCount + " line(s) from the file \"" + fileName + "\".");

        // Print information in symbol table
        symtab.printTable();

        // Print symbol table to a file
        try {
            PrintStream out = new PrintStream(new FileOutputStream("files\\output.txt"));
            System.setOut(out);
            symtab.printTable();
            System.setOut(System.out);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
