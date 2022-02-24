/*
 * generated by Xtext 2.25.0
 */
lexer grammar InternalSTFunctionLexer;

@header {
package org.eclipse.fordiac.ide.structuredtextfunctioneditor.ide.contentassist.antlr.lexer;

// Hack: Use our own Lexer superclass by means of import. 
// Currently there is no other way to specify the superclass for the lexer.
import org.eclipse.xtext.ide.editor.contentassist.antlr.internal.Lexer;
}

LDATE_AND_TIME : ('L'|'l')('D'|'d')('A'|'a')('T'|'t')('E'|'e')'_'('A'|'a')('N'|'n')('D'|'d')'_'('T'|'t')('I'|'i')('M'|'m')('E'|'e');

DATE_AND_TIME : ('D'|'d')('A'|'a')('T'|'t')('E'|'e')'_'('A'|'a')('N'|'n')('D'|'d')'_'('T'|'t')('I'|'i')('M'|'m')('E'|'e');

END_FUNCTION : ('E'|'e')('N'|'n')('D'|'d')'_'('F'|'f')('U'|'u')('N'|'n')('C'|'c')('T'|'t')('I'|'i')('O'|'o')('N'|'n');

LTIME_OF_DAY : ('L'|'l')('T'|'t')('I'|'i')('M'|'m')('E'|'e')'_'('O'|'o')('F'|'f')'_'('D'|'d')('A'|'a')('Y'|'y');

TIME_OF_DAY : ('T'|'t')('I'|'i')('M'|'m')('E'|'e')'_'('O'|'o')('F'|'f')'_'('D'|'d')('A'|'a')('Y'|'y');

END_REPEAT : ('E'|'e')('N'|'n')('D'|'d')'_'('R'|'r')('E'|'e')('P'|'p')('E'|'e')('A'|'a')('T'|'t');

VAR_OUTPUT : ('V'|'v')('A'|'a')('R'|'r')'_'('O'|'o')('U'|'u')('T'|'t')('P'|'p')('U'|'u')('T'|'t');

END_WHILE : ('E'|'e')('N'|'n')('D'|'d')'_'('W'|'w')('H'|'h')('I'|'i')('L'|'l')('E'|'e');

VAR_INPUT : ('V'|'v')('A'|'a')('R'|'r')'_'('I'|'i')('N'|'n')('P'|'p')('U'|'u')('T'|'t');

CONSTANT : ('C'|'c')('O'|'o')('N'|'n')('S'|'s')('T'|'t')('A'|'a')('N'|'n')('T'|'t');

CONTINUE : ('C'|'c')('O'|'o')('N'|'n')('T'|'t')('I'|'i')('N'|'n')('U'|'u')('E'|'e');

END_CASE : ('E'|'e')('N'|'n')('D'|'d')'_'('C'|'c')('A'|'a')('S'|'s')('E'|'e');

FUNCTION : ('F'|'f')('U'|'u')('N'|'n')('C'|'c')('T'|'t')('I'|'i')('O'|'o')('N'|'n');

VAR_TEMP : ('V'|'v')('A'|'a')('R'|'r')'_'('T'|'t')('E'|'e')('M'|'m')('P'|'p');

END_FOR : ('E'|'e')('N'|'n')('D'|'d')'_'('F'|'f')('O'|'o')('R'|'r');

END_VAR : ('E'|'e')('N'|'n')('D'|'d')'_'('V'|'v')('A'|'a')('R'|'r');

WSTRING : ('W'|'w')('S'|'s')('T'|'t')('R'|'r')('I'|'i')('N'|'n')('G'|'g');

END_IF : ('E'|'e')('N'|'n')('D'|'d')'_'('I'|'i')('F'|'f');

REPEAT : ('R'|'r')('E'|'e')('P'|'p')('E'|'e')('A'|'a')('T'|'t');

RETURN : ('R'|'r')('E'|'e')('T'|'t')('U'|'u')('R'|'r')('N'|'n');

STRING : ('S'|'s')('T'|'t')('R'|'r')('I'|'i')('N'|'n')('G'|'g');

ARRAY : ('A'|'a')('R'|'r')('R'|'r')('A'|'a')('Y'|'y');

DWORD : ('D'|'d')('W'|'w')('O'|'o')('R'|'r')('D'|'d');

ELSIF : ('E'|'e')('L'|'l')('S'|'s')('I'|'i')('F'|'f');

FALSE : ('F'|'f')('A'|'a')('L'|'l')('S'|'s')('E'|'e');

LDATE : ('L'|'l')('D'|'d')('A'|'a')('T'|'t')('E'|'e');

LREAL : ('L'|'l')('R'|'r')('E'|'e')('A'|'a')('L'|'l');

LTIME : ('L'|'l')('T'|'t')('I'|'i')('M'|'m')('E'|'e');

LWORD : ('L'|'l')('W'|'w')('O'|'o')('R'|'r')('D'|'d');

UDINT : ('U'|'u')('D'|'d')('I'|'i')('N'|'n')('T'|'t');

ULINT : ('U'|'u')('L'|'l')('I'|'i')('N'|'n')('T'|'t');

UNTIL : ('U'|'u')('N'|'n')('T'|'t')('I'|'i')('L'|'l');

USINT : ('U'|'u')('S'|'s')('I'|'i')('N'|'n')('T'|'t');

WCHAR : ('W'|'w')('C'|'c')('H'|'h')('A'|'a')('R'|'r');

WHILE : ('W'|'w')('H'|'h')('I'|'i')('L'|'l')('E'|'e');

BOOL : ('B'|'b')('O'|'o')('O'|'o')('L'|'l');

BYTE : ('B'|'b')('Y'|'y')('T'|'t')('E'|'e');

CASE : ('C'|'c')('A'|'a')('S'|'s')('E'|'e');

CHAR : ('C'|'c')('H'|'h')('A'|'a')('R'|'r');

DATE : ('D'|'d')('A'|'a')('T'|'t')('E'|'e');

DINT : ('D'|'d')('I'|'i')('N'|'n')('T'|'t');

ELSE : ('E'|'e')('L'|'l')('S'|'s')('E'|'e');

EXIT : ('E'|'e')('X'|'x')('I'|'i')('T'|'t');

LINT : ('L'|'l')('I'|'i')('N'|'n')('T'|'t');

LTOD : ('L'|'l')('T'|'t')('O'|'o')('D'|'d');

REAL : ('R'|'r')('E'|'e')('A'|'a')('L'|'l');

SINT : ('S'|'s')('I'|'i')('N'|'n')('T'|'t');

THEN : ('T'|'t')('H'|'h')('E'|'e')('N'|'n');

TIME : ('T'|'t')('I'|'i')('M'|'m')('E'|'e');

TRUE : ('T'|'t')('R'|'r')('U'|'u')('E'|'e');

UINT : ('U'|'u')('I'|'i')('N'|'n')('T'|'t');

WORD : ('W'|'w')('O'|'o')('R'|'r')('D'|'d');

AND : ('A'|'a')('N'|'n')('D'|'d');

FOR : ('F'|'f')('O'|'o')('R'|'r');

INT : ('I'|'i')('N'|'n')('T'|'t');

LDT : ('L'|'l')('D'|'d')('T'|'t');

MOD : ('M'|'m')('O'|'o')('D'|'d');

NOT : ('N'|'n')('O'|'o')('T'|'t');

TOD : ('T'|'t')('O'|'o')('D'|'d');

VAR : ('V'|'v')('A'|'a')('R'|'r');

XOR : ('X'|'x')('O'|'o')('R'|'r');

B : '%'('B'|'b');

D_1 : '%'('D'|'d');

L : '%'('L'|'l');

W : '%'('W'|'w');

X : '%'('X'|'x');

AsteriskAsterisk : '*''*';

FullStopFullStop : '.''.';

ColonEqualsSign : ':''=';

LessThanSignEqualsSign : '<''=';

LessThanSignGreaterThanSign : '<''>';

EqualsSignGreaterThanSign : '=''>';

GreaterThanSignEqualsSign : '>''=';

AT : ('A'|'a')('T'|'t');

BY : ('B'|'b')('Y'|'y');

DO : ('D'|'d')('O'|'o');

DT : ('D'|'d')('T'|'t');

IF : ('I'|'i')('F'|'f');

LD : ('L'|'l')('D'|'d');

LT : ('L'|'l')('T'|'t');

MS : ('M'|'m')('S'|'s');

NS : ('N'|'n')('S'|'s');

OF : ('O'|'o')('F'|'f');

OR : ('O'|'o')('R'|'r');

TO : ('T'|'t')('O'|'o');

US : ('U'|'u')('S'|'s');

NumberSign : '#';

Ampersand : '&';

LeftParenthesis : '(';

RightParenthesis : ')';

Asterisk : '*';

PlusSign : '+';

Comma : ',';

HyphenMinus : '-';

FullStop : '.';

Solidus : '/';

Colon : ':';

Semicolon : ';';

LessThanSign : '<';

EqualsSign : '=';

GreaterThanSign : '>';

D : ('D'|'d');

H : ('H'|'h');

M : ('M'|'m');

S : ('S'|'s');

T : ('T'|'t');

LeftSquareBracket : '[';

RightSquareBracket : ']';

KW__ : '_';

fragment RULE_HEX_DIGIT : ('0'..'9'|'a'..'f'|'A'..'F'|'_');

RULE_NON_DECIMAL : ('2#'|'8#'|'16#') RULE_HEX_DIGIT+;

RULE_EXT_INT : RULE_INT ('e'|'E') ('-'|'+')? RULE_INT;

RULE_INT : '0'..'9' ('_'? '0'..'9')*;

RULE_ID : '^'? ('a'..'z'|'A'..'Z'|'_') ('a'..'z'|'A'..'Z'|'_'|'0'..'9')*;

RULE_STRING : ('"' ('$' .|~(('$'|'"')))* '"'|'\'' ('$' .|~(('$'|'\'')))* '\'');

RULE_ML_COMMENT : ('/*' ( options {greedy=false;} : . )*'*/'|'(*' ( options {greedy=false;} : . )*'*)');

RULE_SL_COMMENT : '//' ~(('\n'|'\r'))* ('\r'? '\n')?;

RULE_WS : (' '|'\t'|'\r'|'\n')+;

RULE_ANY_OTHER : .;
