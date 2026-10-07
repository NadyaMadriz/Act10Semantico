/* Muñoz Madriz Nadya Janneth */
/* Ramirez Torres Jesus Adrian */
/* Cervantes Mora Juan Pablo */

package act1_analisis_lexico;

import java_cup.runtime.Symbol;

%%

%class LexerC
%public
%unicode
%cup
%line
%column

%{
  private Symbol token(int type) {
    System.out.printf(
        "[Linea %d, Columna %d] %-18s -> %s%n",
        yyline + 1,
        yycolumn + 1,
        sym.terminalNames[type],
        yytext()
    );

    return new Symbol(
        type,
        yyline + 1,
        yycolumn + 1,
        yytext()
    );
  }
%}

LineTerminator = \r|\n|\r\n
InputCharacter = [^\r\n]
WhiteSpace     = {LineTerminator} | [ \t\f]

/* Comentarios en C */

TraditionalComment = "/*" [^*] ~"*/" | "/*" "*"+ "/"
EndOfLineComment   = "//" {InputCharacter}* {LineTerminator}?
Comment            = {TraditionalComment} | {EndOfLineComment}

/* Expresiones regulares */

Identifier        = [a-zA-Z_] [a-zA-Z0-9_]*
DecIntegerLiteral = 0 | [1-9][0-9]*
FloatLiteral      = [0-9]+\.[0-9]+
StringLiteral     = \"([^\"\\]|\\.)*\"

%%

/* Ignorar comentarios */

{Comment} {
    /* Ignorar comentarios */
}

/* Ignorar espacios */

{WhiteSpace} {
    /* Ignorar espacios */
}

/* Palabras reservadas */

"int"       { return token(sym.INT); }
"float"     { return token(sym.FLOAT); }
"char"      { return token(sym.CHAR); }
"void"      { return token(sym.VOID); }

"if"        { return token(sym.IF); }
"else"      { return token(sym.ELSE); }
"while"     { return token(sym.WHILE); }
"for"       { return token(sym.FOR); }
"return"    { return token(sym.RETURN); }
"break"     { return token(sym.BREAK); }

/* Operadores aritmeticos */

"="         { return token(sym.IGUAL); }
"+"         { return token(sym.SUMA); }
"-"         { return token(sym.RESTA); }
"*"         { return token(sym.MULTIPLICACION); }
"/"         { return token(sym.DIVISION); }

/* Operadores relacionales */

"=="        { return token(sym.IGUAL_QUE); }
"!="        { return token(sym.DIFERENTE_QUE); }
"<="        { return token(sym.MENOR_IGUAL); }
">="        { return token(sym.MAYOR_IGUAL); }
">"         { return token(sym.MAYOR_QUE); }
"<"         { return token(sym.MENOR_QUE); }

/* Operadores logicos */

"&&"        { return token(sym.AND_LOGICO); }
"||"        { return token(sym.OR_LOGICO); }
"!"         { return token(sym.NOT_LOGICO); }

/* Parentesis */

"("         { return token(sym.PARENTESIS_A); }
")"         { return token(sym.PARENTESIS_C); }

/* Llaves */

"{"         { return token(sym.LLAVE_A); }
"}"         { return token(sym.LLAVE_C); }

/* Corchetes */

"["         { return token(sym.CORCHETE_A); }
"]"         { return token(sym.CORCHETE_C); }

/* Otros simbolos */

";"         { return token(sym.PUNTO_COMA); }
","         { return token(sym.COMA); }
"."         { return token(sym.PUNTO); }

/* Tipos dinamicos */

{FloatLiteral}        { return token(sym.DECIMAL); }
{DecIntegerLiteral}   { return token(sym.NUMERO); }
{StringLiteral}       { return token(sym.CADENA); }
{Identifier}          { return token(sym.IDENTIFICADOR); }

/* Errores lexicos */

. {
    System.out.println(
        "[Linea " + (yyline + 1) +
        ", Columna " + (yycolumn + 1) +
        "] ERROR LEXICO -> caracter no reconocido: " +
        yytext()
    );
}