package com.litsynp;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


// TODO regex 패턴 이름을 enum으로 바꿔서 string이 아닌 enum으로 반환하기
// TODO 현재 괄호들 전부 delimiter로 하려고 하는데 어떤 방식으로 끊을지 결정하기
	// delimiter나 whitespace를 만나면 input buffer를 비우고 token으로 넣는 것은 맞음. 허나 방금 읽은 문자 (delimiter)도 token에 넣어야 함.
	// 근데 그 delimiter가 무조건 brace token인 것도 아님. token name 및 value가 다르기 때문 (LPAREN, RPAREN, LBRACE, ...) 콤마도 있다.
	// delimiter을 만나면 일단 하나의 함수에서 isDelimiter로 만났는지 검사하고, token name을 반환해야함. 이걸 String으로 할지 enum으로 할지가 관건

public class Main {


	public static void main(String[] args) {

		// Symbol Table
		SymbolTable symtab = new SymbolTable();

		// Number of characters read in the file
		int readCharNum = 0;
				
		try {
			// Create file object
			File file = new File("input-files\\input.txt");
			// Create input stream
			FileReader filereader = new FileReader(file);
			
			
			int ch = 0; 			// character just read
			String inputBuffer = "";  // temporary string buffer

			// Read character one by one from input stream
			while ((ch = filereader.read()) != -1) {
				readCharNum++;
				System.out.println("------ " + readCharNum + "th character ------");
				
				// Convert character to string
				String readCharStr = Character.toString((char) ch);

				// Debug print
				System.out.println("inputBuffer: " + inputBuffer);
				System.out.println("Character read: \'" + Character.toString((char) ch) + "\'");
				
				// If the read character is whitespace (\r, \t, \n)
				if (Token.isWhitespace(readCharStr)) {
					System.out.println("Character read is a whitespace character.");
					
					// If input string is not empty, save the buffer content as token
					if (inputBuffer.isEmpty() == false) {
						symtab.put(inputBuffer);
					}
					
					// Clear the string buffer and read next character
					inputBuffer = "";
					continue;
				}

				// If the read character belongs to any of the delimiters
				if (Token.isDelimiter(readCharStr)) {
					System.out.println("Character read is a delimiter character.");
					
					// If input string is not empty, save the buffer content as token
					if (inputBuffer.isEmpty() == false) {
						symtab.put(inputBuffer);
					}
					symtab.put(readCharStr);
					
					// Clear the string buffer and read next character
					inputBuffer = "";
					continue;
				}
				
				
				// If not, add the character to the string buffer
				inputBuffer += Character.toString((char) ch);

			}	
			symtab.put(inputBuffer);
			
			filereader.close();
		
		} catch (FileNotFoundException e) {
			System.out.println(e);
		} catch (IOException e) {
			System.out.println(e);
		}
		
		System.out.println("\nRead " + readCharNum + " characters from the file.\n");

		// Print information in symbol table
		symtab.printTable();
	}

}