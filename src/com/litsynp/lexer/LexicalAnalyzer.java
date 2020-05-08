package com.litsynp.lexer;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;

import com.litsynp.lexer.state.State;
import com.litsynp.lexer.token.NullTokenException;
import com.litsynp.lexer.token.SymbolTable;
import com.litsynp.lexer.token.Token;
import com.litsynp.lexer.token.TokenType;

// TODO : Minus 기호 만나면 그냥 넣어버려라.. 구분 힘들다

/**
 * Lexical analyzer that lexically analyzes an input file and creates a symbol
 * table
 */
public class LexicalAnalyzer {

    /**
     * Lexically analyzes an input file line by line and character by character to
     * tokenize it into a symbol table.
     * 
     * @param inputFile the input file to read
     */
    public static void lex(File inputFile) {

        // Symbol Table
        SymbolTable symtab = new SymbolTable();
        int lineCount = 0;
        int charCount = 0;

        try {
            // Create input stream
            BufferedReader reader = new BufferedReader(new FileReader(inputFile));

            // String of the one whole line
            String line;

            // Read the input file line by line
            while ((line = reader.readLine()) != null) {
                System.out.println(String.format("[Line %3d Content]", lineCount + 1) + line);

                // Initialize variables for reading the line
                State currentState = State.START;
                String workingString = "";
                char ch = 0;

                // Read character by character in the line and transition the state
                for (charCount = 0; charCount < line.length() + 1; charCount++) {

                    // Check if it is the last character
                    ch = (charCount == line.length()) ? ' ' : line.charAt(charCount);

                    System.out.println(String.format("[Line %3d] %3d", lineCount + 1, charCount)
                            + "th character|Read character:\'" + ch + "\'|Current String:" + workingString);

                    // Transition
                    currentState = currentState.transition(ch);
                    
                    // If accepted
                    if (currentState.isAccepted()) {
                        symtab.put(new Token(currentState.getTokenType(), workingString, lineCount));

                        currentState = State.START;
                        workingString = Character.toString(ch);
                        currentState = currentState.transition(ch);
                    } else {
                        // If not yet accepted
                        workingString += ch;
                    }
                }
                
                // Prepare for next line
                lineCount = lineCount + 1;
            }

            reader.close();

        } catch (FileNotFoundException e) {
            System.out.println(e);
            System.exit(1);
        } catch (IOException e) {
            System.out.println(e);
            System.exit(1);
        } catch (NullTokenException e) {
            System.out.println(
                    e + " at character " + charCount + " in line " + (lineCount + 1) + " in " + inputFile.getName());
            System.exit(1);
        }

        System.out.println("\nRead " + lineCount + " line(s) from the file \"" + inputFile.getPath() + "\".");

        // Print information in symbol table
        symtab.printTable();

        // Print symbol table to a file
        try {
            String inputFilePath = inputFile.getPath();
            int pos = inputFilePath.lastIndexOf(".");
            if (pos > 0 && pos < (inputFilePath.length() - 1)) { // If '.' is not the first or last character.
                inputFilePath = inputFilePath.substring(0, pos);
            }

            File outputFile = new File(inputFilePath + ".out");

            /*
             * Print the symbol table to the output file and set the output stream back to
             * console standard output
             */
            PrintStream stdout = System.out;
            PrintStream out = new PrintStream(new FileOutputStream(outputFile), false, "UTF-8");
            System.setOut(out);
            symtab.printTable();
            System.setOut(stdout);

            System.out.println("Output file is generated in \"" + outputFile.getPath() + "\".");
        } catch (FileNotFoundException e) {
            System.out.println(e);
            System.exit(1);
        } catch (UnsupportedEncodingException e) {
            System.out.println(e);
            System.exit(1);
        }
    }
}
