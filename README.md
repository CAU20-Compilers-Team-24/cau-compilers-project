# CAU 20-1 Compilers - Term Project: Lexical and Syntax Analyzer

## System Requirements
- Tested on Windows 10 and Ubuntu 18.04 LTS
- JRE 8+

## How to Modify Input C File
- Create an input file containing contents to be lexically and syntactically analyzed (ex. `input.c`)
- Or, alternatively, just modify contents in `files/a.c`
- Default input file is `files/a.c` if ran without any argument.

## Execution (on Windows)
- Open `cmd.exe`

### Lexical Analyzer (Lexer)
- Type:
```shell
java -jar lexical_analyzer.jar <file-name>.c
```
- Example:
```shell
java -jar lexical_analyzer.jar files/a.c
```
- The result file will be generated as `<file-name>.ser`.
    - The generated file contains token list (`ArrayList<Token>`) information of the symbol table `symtab`.

### Syntax Analyzer (Parser)
- Type:
```shell
java -jar syntax_analyzer.jar <file-name>.ser
```
- Example:
```shell
java -jar syntax_analyzer.jar files/a.ser
```

## Error Reports
- Error reports are shown on the console.
