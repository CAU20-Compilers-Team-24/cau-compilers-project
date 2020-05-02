# CAU 20-1 Compilers - Term Project 1: Lexical Analyzer

## System Requirements
- Tested on Windows 10 and Ubuntu 18.04 LTS
- JRE 8+

## How to Modify Input File
- Create an input file containing contents to be lexically analyzed (ex. input.c)
- Or, alternatively, just modify contents in *files/a.c*
- Default input file is *files/a.c* if ran without any argument

## How to Execute (on Windows)
- Open cmd.exe
- Type:
```console
java -jar lexical_analyzer.jar <file-name>
```
- Example:
```console
java -jar lexical_analyzer.jar files/a.c
```

## Error Reports
- Error reports are shown on the console.

## Output Symbol Table as File
- Output file is created as "*file-name.out*" in the same directory as input file