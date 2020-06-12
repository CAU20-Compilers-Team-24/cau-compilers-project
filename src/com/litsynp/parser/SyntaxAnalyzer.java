package com.litsynp.parser;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Stack;

import com.litsynp.lexer.token.Token;
import com.litsynp.parser.exception.ReferenceException;
import com.litsynp.parser.symbol.Symbol;
import com.litsynp.parser.symbol.TokenMapper;

public class SyntaxAnalyzer {

    /***
     * The SLR parsing table of the syntax analyzer.
     */
    private HashMap<State, HashMap<Symbol, String>> parsingTable;

    /***
     * Stack that is used with the parsing table.
     */
    private Stack<State> stack;

    /***
     * Input symbols that are generated as the output of the lexical analyzer.
     */
    private ArrayList<Symbol> inputSymbols;

    /***
     * The position of the splitter '|' in the input symbols.
     */
    private int splitterPosition = 0;

    /***
     * The acceptance code of the parser.
     */
    private enum AcceptCode {
        /***
         * Means the parser accepts the input string.
         */
        ACCEPTED,
        /***
         * Means the parser does not accept the input string.
         */
        NOT_ACCEPTED,
        /***
         * Means the parser is still parsing the input string.
         */
        PARSING
    }

    /***
     * Initializes the syntax analyzer by initializing the parsing table, the stack,
     * and the input symbols, and putting a splitter at the beginning and an eof
     * symbol at the end of the input symbol list.
     * <p>
     * The SLR parsing table is generated using .ser file, which is the token list
     * that is generated from the lexical analyzer.
     * 
     * @param inputFile input file of the syntax analyzer that contains result
     *                  information of the lexical analyzer
     */
    @SuppressWarnings({ "serial", "unchecked" })
    public SyntaxAnalyzer(File inputFile) {
        System.out.println("Syntax analyzer has been set on input file \"" + inputFile.getName() + "\".");

        // Initialize the parsing table
        parsingTable = new HashMap<State, HashMap<Symbol, String>>() {
            {
                put(State.Q0, new HashMap<Symbol, String>() {
                    {
                        put(Symbol.TERM_VTYPE, "S3");
                        put(Symbol.TERM_EOF, "R4");

                        put(Symbol.NTERM_CODE, "5");
                        put(Symbol.NTERM_VDECL, "1");
                        put(Symbol.NTERM_FDECL, "2");
                    }
                });

                put(State.Q1, new HashMap<Symbol, String>() {
                    {
                        put(Symbol.TERM_VTYPE, "S3");
                        put(Symbol.TERM_EOF, "R4");

                        put(Symbol.NTERM_CODE, "4");
                        put(Symbol.NTERM_VDECL, "1");
                        put(Symbol.NTERM_FDECL, "2");
                    }
                });

                put(State.Q2, new HashMap<Symbol, String>() {
                    {
                        put(Symbol.TERM_VTYPE, "S3");
                        put(Symbol.TERM_EOF, "R4");

                        put(Symbol.NTERM_CODE, "6");
                        put(Symbol.NTERM_VDECL, "1");
                        put(Symbol.NTERM_FDECL, "2");
                    }
                });

                put(State.Q3, new HashMap<Symbol, String>() {
                    {
                        put(Symbol.TERM_ID, "S7");

                        put(Symbol.NTERM_CODE, "8");
                    }
                });

                put(State.Q4, new HashMap<Symbol, String>() {
                    {
                        put(Symbol.TERM_EOF, "R2");
                    }
                });

                put(State.Q5, new HashMap<Symbol, String>() {
                    {
                        put(Symbol.TERM_EOF, "R1");
                    }
                });

                put(State.Q6, new HashMap<Symbol, String>() {
                    {
                        put(Symbol.TERM_EOF, "R3");
                    }
                });

                put(State.Q7, new HashMap<Symbol, String>() {
                    {
                        put(Symbol.TERM_ASSIGN, "S11");
                        put(Symbol.TERM_SEMI, "S10");
                        put(Symbol.TERM_LPAREN, "S9");
                    }
                });

                put(State.Q8, new HashMap<Symbol, String>() {
                    {
                        put(Symbol.TERM_SEMI, "S12");
                    }
                });

                put(State.Q9, new HashMap<Symbol, String>() {
                    {
                        put(Symbol.TERM_VTYPE, "S15");
                        put(Symbol.TERM_RPAREN, "R10");

                        put(Symbol.NTERM_ARG, "14");
                    }
                });

                put(State.Q10, new HashMap<Symbol, String>() {
                    {
                        put(Symbol.TERM_VTYPE, "R5");
                        put(Symbol.TERM_ID, "R5");
                        put(Symbol.TERM_IF, "R5");
                        put(Symbol.TERM_WHILE, "R5");
                        put(Symbol.TERM_FOR, "R5");
                        put(Symbol.TERM_RETURN, "R5");
                        put(Symbol.TERM_RBRACE, "R5");
                    }
                });

                put(State.Q11, new HashMap<Symbol, String>() {
                    {
                        put(Symbol.TERM_NUM, "S23");
                        put(Symbol.TERM_FLOAT, "S24");
                        put(Symbol.TERM_LITERAL, "S18");
                        put(Symbol.TERM_ID, "S22");
                        put(Symbol.TERM_LPAREN, "S21");

                        put(Symbol.NTERM_RHS, "16");
                        put(Symbol.NTERM_EXPR, "17");
                        put(Symbol.NTERM_TERM, "19");
                        put(Symbol.NTERM_FACTOR, "20");
                    }
                });

                put(State.Q12, new HashMap<Symbol, String>() {
                    {
                        put(Symbol.TERM_VTYPE, "R6");
                        put(Symbol.TERM_ID, "R6");
                        put(Symbol.TERM_IF, "R6");
                        put(Symbol.TERM_WHILE, "R6");
                        put(Symbol.TERM_FOR, "R6");
                        put(Symbol.TERM_RETURN, "R6");
                        put(Symbol.TERM_RBRACE, "R6");
                    }
                });

                put(State.Q13, new HashMap<Symbol, String>() {
                    {
                        put(Symbol.TERM_ASSIGN, "S11");
                    }
                });

                put(State.Q14, new HashMap<Symbol, String>() {
                    {
                        put(Symbol.TERM_RPAREN, "S25");
                    }
                });

                put(State.Q15, new HashMap<Symbol, String>() {
                    {
                        put(Symbol.TERM_ID, "S26");
                    }
                });

                put(State.Q16, new HashMap<Symbol, String>() {
                    {
                        put(Symbol.TERM_SEMI, "R7");
                        put(Symbol.TERM_RPAREN, "R7");
                    }
                });

                put(State.Q17, new HashMap<Symbol, String>() {
                    {
                        put(Symbol.TERM_SEMI, "R22");
                        put(Symbol.TERM_RPAREN, "R22");
                    }
                });

                put(State.Q18, new HashMap<Symbol, String>() {
                    {
                        put(Symbol.TERM_SEMI, "R23");
                        put(Symbol.TERM_RPAREN, "R23");
                    }
                });

                put(State.Q19, new HashMap<Symbol, String>() {
                    {
                        put(Symbol.TERM_ADDSUB, "S27");
                        put(Symbol.TERM_SEMI, "R25");
                        put(Symbol.TERM_RPAREN, "R25");
                    }
                });

                put(State.Q20, new HashMap<Symbol, String>() {
                    {
                        put(Symbol.TERM_ADDSUB, "R27");
                        put(Symbol.TERM_MULTDIV, "S28");
                        put(Symbol.TERM_SEMI, "R27");
                        put(Symbol.TERM_RPAREN, "R27");
                    }
                });

                put(State.Q21, new HashMap<Symbol, String>() {
                    {
                        put(Symbol.TERM_NUM, "S23");
                        put(Symbol.TERM_FLOAT, "S24");
                        put(Symbol.TERM_ID, "S22");
                        put(Symbol.TERM_LPAREN, "S21");

                        put(Symbol.NTERM_EXPR, "29");
                        put(Symbol.NTERM_TERM, "19");
                        put(Symbol.NTERM_FACTOR, "20");
                    }
                });

                put(State.Q22, new HashMap<Symbol, String>() {
                    {
                        put(Symbol.TERM_ADDSUB, "R29");
                        put(Symbol.TERM_MULTDIV, "R29");
                        put(Symbol.TERM_COMP, "R29");
                        put(Symbol.TERM_SEMI, "R29");
                        put(Symbol.TERM_RPAREN, "R29");
                    }
                });

                put(State.Q23, new HashMap<Symbol, String>() {
                    {
                        put(Symbol.TERM_ADDSUB, "R30");
                        put(Symbol.TERM_MULTDIV, "R30");
                        put(Symbol.TERM_COMP, "R30");
                        put(Symbol.TERM_SEMI, "R30");
                        put(Symbol.TERM_RPAREN, "R30");
                    }
                });

                put(State.Q24, new HashMap<Symbol, String>() {
                    {
                        put(Symbol.TERM_ADDSUB, "R31");
                        put(Symbol.TERM_MULTDIV, "R31");
                        put(Symbol.TERM_COMP, "R31");
                        put(Symbol.TERM_SEMI, "R31");
                        put(Symbol.TERM_RPAREN, "R31");
                    }
                });

                put(State.Q25, new HashMap<Symbol, String>() {
                    {
                        put(Symbol.TERM_LBRACE, "S30");
                    }
                });

                put(State.Q26, new HashMap<Symbol, String>() {
                    {
                        put(Symbol.TERM_COMMA, "S32");
                        put(Symbol.TERM_RPAREN, "R12");
                        put(Symbol.NTERM_MOREARGS, "31");
                    }
                });

                put(State.Q27, new HashMap<Symbol, String>() {
                    {
                        put(Symbol.TERM_NUM, "S23");
                        put(Symbol.TERM_FLOAT, "S24");
                        put(Symbol.TERM_ID, "S22");
                        put(Symbol.TERM_LPAREN, "S21");

                        put(Symbol.NTERM_EXPR, "33");
                        put(Symbol.NTERM_TERM, "19");
                        put(Symbol.NTERM_FACTOR, "20");
                    }
                });

                put(State.Q28, new HashMap<Symbol, String>() {
                    {
                        put(Symbol.TERM_NUM, "S23");
                        put(Symbol.TERM_FLOAT, "S24");
                        put(Symbol.TERM_ID, "S22");
                        put(Symbol.TERM_LPAREN, "S21");

                        put(Symbol.NTERM_TERM, "30");
                        put(Symbol.NTERM_FACTOR, "20");
                    }
                });

                put(State.Q29, new HashMap<Symbol, String>() {
                    {
                        put(Symbol.TERM_RPAREN, "S35");
                    }
                });

                put(State.Q30, new HashMap<Symbol, String>() {
                    {
                        put(Symbol.TERM_VTYPE, "S43");
                        put(Symbol.TERM_ID, "S13");
                        put(Symbol.TERM_IF, "S40");
                        put(Symbol.TERM_WHILE, "S41");
                        put(Symbol.TERM_FOR, "S42");
                        put(Symbol.TERM_RETURN, "R14");
                        put(Symbol.TERM_RBRACE, "R14");

                        put(Symbol.NTERM_VDECL, "38");
                        put(Symbol.NTERM_BLOCK, "36");
                        put(Symbol.NTERM_STMT, "37");
                        put(Symbol.NTERM_ASSIGN, "39");
                    }
                });

                put(State.Q31, new HashMap<Symbol, String>() {
                    {
                        put(Symbol.TERM_RPAREN, "R9");
                    }
                });

                put(State.Q32, new HashMap<Symbol, String>() {
                    {
                        put(Symbol.TERM_VTYPE, "S44");
                    }
                });

                put(State.Q33, new HashMap<Symbol, String>() {
                    {
                        put(Symbol.TERM_SEMI, "R24");
                        put(Symbol.TERM_RPAREN, "R24");
                    }
                });

                put(State.Q34, new HashMap<Symbol, String>() {
                    {
                        put(Symbol.TERM_ADDSUB, "R26");
                        put(Symbol.TERM_SEMI, "R26");
                        put(Symbol.TERM_RPAREN, "R26");
                    }
                });

                put(State.Q35, new HashMap<Symbol, String>() {
                    {
                        put(Symbol.TERM_ADDSUB, "R28");
                        put(Symbol.TERM_MULTDIV, "R28");
                        put(Symbol.TERM_COMP, "R28");
                        put(Symbol.TERM_SEMI, "R28");
                        put(Symbol.TERM_RPAREN, "R28");
                    }
                });

                put(State.Q36, new HashMap<Symbol, String>() {
                    {
                        put(Symbol.TERM_RETURN, "S46");
                        put(Symbol.NTERM_RETURN, "45");
                    }
                });

                put(State.Q37, new HashMap<Symbol, String>() {
                    {
                        put(Symbol.TERM_VTYPE, "S43");
                        put(Symbol.TERM_ID, "S13");
                        put(Symbol.TERM_IF, "S40");
                        put(Symbol.TERM_WHILE, "S41");
                        put(Symbol.TERM_FOR, "S42");
                        put(Symbol.TERM_RETURN, "R14");
                        put(Symbol.TERM_RBRACE, "R14");

                        put(Symbol.NTERM_VDECL, "38");
                        put(Symbol.NTERM_BLOCK, "47");
                        put(Symbol.NTERM_STMT, "37");
                        put(Symbol.NTERM_ASSIGN, "39");
                    }
                });

                put(State.Q38, new HashMap<Symbol, String>() {
                    {
                        put(Symbol.TERM_VTYPE, "R15");
                        put(Symbol.TERM_ID, "R15");
                        put(Symbol.TERM_IF, "R15");
                        put(Symbol.TERM_WHILE, "R15");
                        put(Symbol.TERM_FOR, "R15");
                        put(Symbol.TERM_RETURN, "R15");
                        put(Symbol.TERM_RBRACE, "R15");
                    }
                });

                put(State.Q39, new HashMap<Symbol, String>() {
                    {
                        put(Symbol.TERM_SEMI, "S48");
                    }
                });

                put(State.Q40, new HashMap<Symbol, String>() {
                    {
                        put(Symbol.TERM_LPAREN, "S49");
                    }
                });

                put(State.Q41, new HashMap<Symbol, String>() {
                    {
                        put(Symbol.TERM_LPAREN, "S50");
                    }
                });

                put(State.Q42, new HashMap<Symbol, String>() {
                    {
                        put(Symbol.TERM_LPAREN, "S51");
                    }
                });

                put(State.Q43, new HashMap<Symbol, String>() {
                    {
                        put(Symbol.TERM_ID, "S52");

                        put(Symbol.NTERM_ASSIGN, "8");
                    }
                });

                put(State.Q44, new HashMap<Symbol, String>() {
                    {
                        put(Symbol.TERM_ID, "S53");
                    }
                });

                put(State.Q45, new HashMap<Symbol, String>() {
                    {
                        put(Symbol.TERM_RBRACE, "S54");
                    }
                });

                put(State.Q46, new HashMap<Symbol, String>() {
                    {
                        put(Symbol.TERM_NUM, "S23");
                        put(Symbol.TERM_FLOAT, "S24");
                        put(Symbol.TERM_ID, "S22");
                        put(Symbol.TERM_LPAREN, "S21");

                        put(Symbol.NTERM_FACTOR, "55");
                    }
                });

                put(State.Q47, new HashMap<Symbol, String>() {
                    {
                        put(Symbol.TERM_RETURN, "R13");
                        put(Symbol.TERM_RBRACE, "R13");
                    }
                });

                put(State.Q48, new HashMap<Symbol, String>() {
                    {
                        put(Symbol.TERM_VTYPE, "R16");
                        put(Symbol.TERM_ID, "R16");
                        put(Symbol.TERM_IF, "R16");
                        put(Symbol.TERM_WHILE, "R16");
                        put(Symbol.TERM_FOR, "R16");
                        put(Symbol.TERM_RETURN, "R16");
                        put(Symbol.TERM_RBRACE, "R16");
                    }
                });

                put(State.Q49, new HashMap<Symbol, String>() {
                    {
                        put(Symbol.TERM_NUM, "S23");
                        put(Symbol.TERM_FLOAT, "S24");
                        put(Symbol.TERM_ID, "S22");
                        put(Symbol.TERM_LPAREN, "S21");

                        put(Symbol.NTERM_FACTOR, "57");
                        put(Symbol.NTERM_COND, "56");
                    }
                });

                put(State.Q50, new HashMap<Symbol, String>() {
                    {
                        put(Symbol.TERM_NUM, "S23");
                        put(Symbol.TERM_FLOAT, "S24");
                        put(Symbol.TERM_ID, "S22");
                        put(Symbol.TERM_LPAREN, "S21");

                        put(Symbol.NTERM_FACTOR, "57");
                        put(Symbol.NTERM_COND, "58");
                    }
                });

                put(State.Q51, new HashMap<Symbol, String>() {
                    {
                        put(Symbol.TERM_ID, "S13");
                        put(Symbol.NTERM_ASSIGN, "59");
                    }
                });

                put(State.Q52, new HashMap<Symbol, String>() {
                    {
                        put(Symbol.TERM_ASSIGN, "S11");
                        put(Symbol.TERM_SEMI, "S10");
                    }
                });

                put(State.Q53, new HashMap<Symbol, String>() {
                    {
                        put(Symbol.TERM_COMMA, "S32");
                        put(Symbol.TERM_RPAREN, "R12");

                        put(Symbol.NTERM_MOREARGS, "60");
                    }
                });

                put(State.Q54, new HashMap<Symbol, String>() {
                    {
                        put(Symbol.TERM_VTYPE, "R8");
                        put(Symbol.TERM_EOF, "R8");
                    }
                });

                put(State.Q55, new HashMap<Symbol, String>() {
                    {
                        put(Symbol.TERM_SEMI, "S61");
                    }
                });

                put(State.Q56, new HashMap<Symbol, String>() {
                    {
                        put(Symbol.TERM_RPAREN, "S62");
                    }
                });

                put(State.Q57, new HashMap<Symbol, String>() {
                    {
                        put(Symbol.TERM_COMP, "S63");
                    }
                });

                put(State.Q58, new HashMap<Symbol, String>() {
                    {
                        put(Symbol.TERM_RPAREN, "S64");
                    }
                });

                put(State.Q59, new HashMap<Symbol, String>() {
                    {
                        put(Symbol.TERM_SEMI, "S65");
                    }
                });

                put(State.Q60, new HashMap<Symbol, String>() {
                    {
                        put(Symbol.TERM_RPAREN, "R11");
                    }
                });

                put(State.Q61, new HashMap<Symbol, String>() {
                    {
                        put(Symbol.TERM_RBRACE, "R33");
                    }
                });

                put(State.Q62, new HashMap<Symbol, String>() {
                    {
                        put(Symbol.TERM_LBRACE, "S66");
                    }
                });

                put(State.Q63, new HashMap<Symbol, String>() {
                    {
                        put(Symbol.TERM_NUM, "S23");
                        put(Symbol.TERM_FLOAT, "S24");
                        put(Symbol.TERM_ID, "S22");
                        put(Symbol.TERM_LPAREN, "S21");

                        put(Symbol.NTERM_FACTOR, "67");
                    }
                });

                put(State.Q64, new HashMap<Symbol, String>() {
                    {
                        put(Symbol.TERM_LBRACE, "S68");
                    }
                });

                put(State.Q65, new HashMap<Symbol, String>() {
                    {
                        put(Symbol.TERM_NUM, "S23");
                        put(Symbol.TERM_FLOAT, "S24");
                        put(Symbol.TERM_ID, "S22");
                        put(Symbol.TERM_LPAREN, "S21");

                        put(Symbol.NTERM_FACTOR, "57");
                        put(Symbol.NTERM_COND, "69");
                    }
                });

                put(State.Q66, new HashMap<Symbol, String>() {
                    {
                        put(Symbol.TERM_VTYPE, "S43");
                        put(Symbol.TERM_ID, "S13");
                        put(Symbol.TERM_IF, "S40");
                        put(Symbol.TERM_WHILE, "S41");
                        put(Symbol.TERM_FOR, "S42");
                        put(Symbol.TERM_RETURN, "R14");
                        put(Symbol.TERM_RBRACE, "R14");

                        put(Symbol.NTERM_VDECL, "38");
                        put(Symbol.NTERM_BLOCK, "70");
                        put(Symbol.NTERM_STMT, "37");
                        put(Symbol.NTERM_ASSIGN, "39");
                    }
                });

                put(State.Q67, new HashMap<Symbol, String>() {
                    {
                        put(Symbol.TERM_SEMI, "R32");
                        put(Symbol.TERM_RPAREN, "R32");
                    }
                });

                put(State.Q68, new HashMap<Symbol, String>() {
                    {
                        put(Symbol.TERM_VTYPE, "S43");
                        put(Symbol.TERM_ID, "S13");
                        put(Symbol.TERM_IF, "S40");
                        put(Symbol.TERM_WHILE, "S41");
                        put(Symbol.TERM_FOR, "S42");
                        put(Symbol.TERM_RETURN, "R14");
                        put(Symbol.TERM_RBRACE, "R14");

                        put(Symbol.NTERM_VDECL, "38");
                        put(Symbol.NTERM_BLOCK, "71");
                        put(Symbol.NTERM_STMT, "37");
                        put(Symbol.NTERM_ASSIGN, "39");
                    }
                });

                put(State.Q69, new HashMap<Symbol, String>() {
                    {
                        put(Symbol.TERM_SEMI, "S72");
                    }
                });

                put(State.Q70, new HashMap<Symbol, String>() {
                    {
                        put(Symbol.TERM_RBRACE, "S73");
                    }
                });

                put(State.Q71, new HashMap<Symbol, String>() {
                    {
                        put(Symbol.TERM_RBRACE, "S74");
                    }
                });

                put(State.Q72, new HashMap<Symbol, String>() {
                    {
                        put(Symbol.TERM_ID, "S13");

                        put(Symbol.NTERM_ASSIGN, "75");
                    }
                });

                put(State.Q73, new HashMap<Symbol, String>() {
                    {
                        put(Symbol.TERM_VTYPE, "R21");
                        put(Symbol.TERM_ID, "R21");
                        put(Symbol.TERM_IF, "R21");
                        put(Symbol.TERM_ELSE, "S77");
                        put(Symbol.TERM_WHILE, "R21");
                        put(Symbol.TERM_FOR, "R21");
                        put(Symbol.TERM_RETURN, "R21");
                        put(Symbol.TERM_RBRACE, "R21");

                        put(Symbol.NTERM_ELSE, "76");
                    }
                });

                put(State.Q74, new HashMap<Symbol, String>() {
                    {
                        put(Symbol.TERM_VTYPE, "R18");
                        put(Symbol.TERM_ID, "R18");
                        put(Symbol.TERM_IF, "R18");
                        put(Symbol.TERM_WHILE, "R18");
                        put(Symbol.TERM_FOR, "R18");
                        put(Symbol.TERM_RETURN, "R18");
                        put(Symbol.TERM_RBRACE, "R18");
                    }
                });

                put(State.Q75, new HashMap<Symbol, String>() {
                    {
                        put(Symbol.TERM_RPAREN, "S78");
                    }
                });

                put(State.Q76, new HashMap<Symbol, String>() {
                    {
                        put(Symbol.TERM_VTYPE, "R17");
                        put(Symbol.TERM_ID, "R17");
                        put(Symbol.TERM_IF, "R17");
                        put(Symbol.TERM_WHILE, "R17");
                        put(Symbol.TERM_FOR, "R17");
                        put(Symbol.TERM_RETURN, "R17");
                        put(Symbol.TERM_RBRACE, "R17");
                    }
                });

                put(State.Q77, new HashMap<Symbol, String>() {
                    {
                        put(Symbol.TERM_LBRACE, "S79");
                    }
                });

                put(State.Q78, new HashMap<Symbol, String>() {
                    {
                        put(Symbol.TERM_LBRACE, "S80");
                    }
                });

                put(State.Q79, new HashMap<Symbol, String>() {
                    {
                        put(Symbol.TERM_VTYPE, "S43");
                        put(Symbol.TERM_ID, "S13");
                        put(Symbol.TERM_IF, "S40");
                        put(Symbol.TERM_WHILE, "S41");
                        put(Symbol.TERM_FOR, "S42");
                        put(Symbol.TERM_RETURN, "R14");
                        put(Symbol.TERM_RBRACE, "R14");

                        put(Symbol.NTERM_VDECL, "38");
                        put(Symbol.NTERM_BLOCK, "81");
                        put(Symbol.NTERM_STMT, "37");
                        put(Symbol.NTERM_ASSIGN, "39");
                    }
                });

                put(State.Q80, new HashMap<Symbol, String>() {
                    {
                        put(Symbol.TERM_VTYPE, "S43");
                        put(Symbol.TERM_ID, "S13");
                        put(Symbol.TERM_IF, "S40");
                        put(Symbol.TERM_WHILE, "S41");
                        put(Symbol.TERM_FOR, "S42");
                        put(Symbol.TERM_RETURN, "R14");
                        put(Symbol.TERM_RBRACE, "R14");

                        put(Symbol.NTERM_VDECL, "38");
                        put(Symbol.NTERM_BLOCK, "82");
                        put(Symbol.NTERM_STMT, "37");
                        put(Symbol.NTERM_ASSIGN, "39");
                    }
                });

                put(State.Q81, new HashMap<Symbol, String>() {
                    {
                        put(Symbol.TERM_RBRACE, "S83");
                    }
                });

                put(State.Q82, new HashMap<Symbol, String>() {
                    {
                        put(Symbol.TERM_RBRACE, "S84");
                    }
                });

                put(State.Q83, new HashMap<Symbol, String>() {
                    {
                        put(Symbol.TERM_VTYPE, "R20");
                        put(Symbol.TERM_ID, "R20");
                        put(Symbol.TERM_IF, "R20");
                        put(Symbol.TERM_WHILE, "R20");
                        put(Symbol.TERM_FOR, "R20");
                        put(Symbol.TERM_RETURN, "R20");
                        put(Symbol.TERM_RBRACE, "R20");
                    }
                });

                put(State.Q84, new HashMap<Symbol, String>() {
                    {
                        put(Symbol.TERM_VTYPE, "R19");
                        put(Symbol.TERM_ID, "R19");
                        put(Symbol.TERM_IF, "R19");
                        put(Symbol.TERM_WHILE, "R19");
                        put(Symbol.TERM_FOR, "R19");
                        put(Symbol.TERM_RETURN, "R19");
                        put(Symbol.TERM_RBRACE, "R19");
                    }
                });
            }
        };

        // Initialize the stack with the initial state q0
        stack = new Stack<State>();

        // Read the token list from .ser file, and store the information into tokens
        ArrayList<Token> tokens = null;
        ObjectInputStream in;
        try {
            in = new ObjectInputStream(new FileInputStream(inputFile));
            tokens = (ArrayList<Token>) in.readObject();

            in.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.exit(1);
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            System.exit(1);
        }

        // Input symbols that are generated as the output of the lexical analyzer
        inputSymbols = new ArrayList<Symbol>();

        // Put splitter at the beginning of the input
        inputSymbols.add(Symbol.SPLITTER);

        // Convert the tokens of the lexer to the symbols of the parser
        for (int i = 0; i < tokens.size(); i++) {
            inputSymbols.add(TokenMapper.convertToken(tokens.get(i)));
        }
        inputSymbols.add(Symbol.TERM_EOF); // Put eof ($) symbol at the end
    }

    /***
     * Syntactically analyzes a given input file that contains results from lexical
     * analyzer.
     * 
     * @return boolean value of whether the string can be accepted by the parser
     */
    public boolean parse() {
        System.out.println("Starting parsing...");

        // Start parsing
        stack.push(State.Q0);

        AcceptCode acode;
        try {
            // Get the accept code to determine whether to continue parsing or not
            acode = doAction();

            System.out.println("Input Symbols: " + inputSymbolsToString());
            while (acode == AcceptCode.PARSING) {
                acode = doAction();
                System.out.println("Input Symbols: " + inputSymbolsToString());
            }

            if (acode == AcceptCode.ACCEPTED) {
                // The input string is accepted
                System.out.println("The input string is ACCEPTED by the parser.");

                return true;
            } else {
                // Report error
                System.out.println("The input string has NOT been accepted by the parser, at stack top \'"
                        + getCurrentState() + "\' and next symbol \'" + getNextSymbol() + "\'.");

                return false;
            }
        } catch (ReferenceException e) {
            e.printStackTrace();
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }

        return false;
    }

    /***
     * Does parsing accordingly to the SLR parsing table.
     * 
     * @return boolean value of whether the table entry is null
     * @throws ReferenceException when the entry in the parsing table is invalidly
     *                            referenced
     */
    private AcceptCode doAction() throws ReferenceException, NumberFormatException {
        // Table entry with [State][Symbol]
        Symbol nextSymbol = getNextSymbol();
        String tableEntry = parsingTable.get(getCurrentState()).get(nextSymbol);

        if (tableEntry == null) {
            // There is no entry with the given state and symbol in the SLR parsing table
            System.out.println(
                    "> [No table entry in SLR parsing table at '" + getCurrentState() + "', '" + nextSymbol + "']");
            return AcceptCode.NOT_ACCEPTED;
        } else if (tableEntry.charAt(0) == 'R') {
            // Reduce by rule n in "Rn"
            // 1. Pop |body| from the stack (possibly except epsilon)
            int ruleNumber = Integer.parseInt(tableEntry.substring(1));
            Rule rule = Rule.valueOf(ruleNumber);
            int ruleBodyLength;

            System.out.println("> [Reducing using " + rule + "]");

            if (rule.body[0] == Symbol.EPSILON) {
                ruleBodyLength = 0;
            } else {
                ruleBodyLength = rule.body.length;
            }

            for (int i = 0; i < ruleBodyLength; i++) {
                inputSymbols.remove(splitterPosition - 1);
                splitterPosition--;

                stack.pop();
            }
            inputSymbols.add(splitterPosition, rule.head);
            splitterPosition++;

            if (rule == Rule.rule1) {
                // If the input string is finally reduced to S', it is accepted
                return AcceptCode.ACCEPTED;
            }

            // 2. GOTO(current stack top state, rule n head)
            // Table entry at [State][Non-terminal], which is the next state
            int gotoResult = Integer.parseInt(parsingTable.get(getCurrentState()).get(rule.head));
            State nextState = State.valueOf(gotoResult);
            stack.push(nextState);

        } else if (tableEntry.charAt(0) == 'S') {
            // Push next state and move the splitter
            Symbol nextTerminal = nextSymbol;
            State nextState = State
                    .valueOf(Integer.parseInt(parsingTable.get(getCurrentState()).get(nextTerminal).substring(1)));
            System.out.println("> [Shifting and going to state " + nextState + "]");
            stack.push(nextState);
            shift();

        } else {
            throw new ReferenceException(
                    "ReferenceException at table [State:" + getCurrentState() + "][Symbol:" + getNextSymbol() + "]");
        }

        return AcceptCode.PARSING;
    }

    /***
     * Shifts the splitter to the right in the input symbols.
     */
    private void shift() {
        Collections.swap(inputSymbols, splitterPosition, splitterPosition + 1);
        splitterPosition += 1;
    }

    /***
     * Returns the leftmost terminal position of the RHS of the input symbols.
     * 
     * @return the leftmost terminal position of the RHS of the input symbols
     */
    private int getLeftmostTerminalPositionOfRhs() {
        return splitterPosition + 1;
    }

    /***
     * Gets the current state of the parser, which is the stack top.
     * 
     * @return the current state of the parser, which is the stack top
     */
    private State getCurrentState() {
        return stack.peek();
    }

    /***
     * Returns the next symbol after the splitter, which is a terminal.
     * 
     * @return the next terminal symbol after the splitter in the input list
     */
    private Symbol getNextSymbol() {
        return inputSymbols.get(getLeftmostTerminalPositionOfRhs());
    }

    /***
     * Returns string value of the input symbols.
     * 
     * @return string value of the input symbols
     */
    private String inputSymbolsToString() {
        String svalue = "";

        for (int i = 0; i < inputSymbols.size(); i++) {
            svalue += inputSymbols.get(i) + " ";
        }

        return svalue;
    }
}
