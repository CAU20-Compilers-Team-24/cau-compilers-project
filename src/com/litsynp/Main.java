package com.litsynp;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


// TODO regex ���� �̸��� enum���� �ٲ㼭 string�� �ƴ� enum���� ��ȯ�ϱ�
// TODO ���� ��ȣ�� ���� delimiter�� �Ϸ��� �ϴµ� � ������� ������ �����ϱ�
	// delimiter�� whitespace�� ������ input buffer�� ���� token���� �ִ� ���� ����. �㳪 ��� ���� ���� (delimiter)�� token�� �־�� ��.
	// �ٵ� �� delimiter�� ������ brace token�� �͵� �ƴ�. token name �� value�� �ٸ��� ���� (LPAREN, RPAREN, LBRACE, ...) �޸��� �ִ�.
	// delimiter�� ������ �ϴ� �ϳ��� �Լ����� isDelimiter�� �������� �˻��ϰ�, token name�� ��ȯ�ؾ���. �̰� String���� ���� enum���� ������ ����

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