package act7_estructuras_control;

import java_cup.runtime.Symbol;

%%

%class Lexer7
%type java_cup.runtime.Symbol
%cup
%full
%line
%column
%public

%{
    private Symbol token(int type, Object value) {
        return new Symbol(type, yyline + 1, yycolumn + 1, value);
    }

    private Symbol token(int type) {
        return new Symbol(type, yyline + 1, yycolumn + 1, yytext());
    }
%}

Letra = [a-zA-Z_]
Digito = [0-9]
ENTERO = {Digito}+
DECIMAL = {Digito}+ "." {Digito}+
IDENTIFICADOR = {Letra}({Letra}|{Digito})*
ESPACIO = [ \t\r\n]+ 
CADENA = \"([^\\\n\r\"]|\\.)*\"
CARACTER = \'([^\\\n\r\']|\\.)\'

%%

<YYINITIAL> {
    {ESPACIO}                    { /* ignorar */ }

    "package"                    { return token(sym.PAQUETE); }
    "import"                     { return token(sym.IMPORTAR); }
    "class"                      { return token(sym.CLASE); }
    "final"                      { return token(sym.FINAL); }
    "void"                       { return token(sym.VOID); }
    "leer"                       { return token(sym.LEER); }
    "escribir"                   { return token(sym.ESCRIBIR); }
    "int"                        { return token(sym.INT); }
    "float"                      { return token(sym.FLOAT); }
    "double"                     { return token(sym.DOUBLE); }
    "char"                       { return token(sym.CHAR); }
    "boolean"                    { return token(sym.BOOLEAN); }
    "String"                     { return token(sym.STRING); }
    "true"                       { return token(sym.VERDADERO); }
    "false"                      { return token(sym.FALSO); }

    /* Palabras reservadas Actividad 7: Estructuras de control */
    "si"                         { return token(sym.SI); }
    "sino"                       { return token(sym.SINO); }
    "segun"                      { return token(sym.SEGUN); }
    "caso"                       { return token(sym.CASO); }
    "defecto"                    { return token(sym.DEFECTO); }
    "romper"                     { return token(sym.ROMPER); }
    "mientras"                   { return token(sym.MIENTRAS); }
    "hacer"                      { return token(sym.HACER); }
    "para"                       { return token(sym.PARA); }

    ";"                          { return token(sym.PUNTO_COMA); }
    ":"                          { return token(sym.DOS_PUNTOS); }
    "."                          { return token(sym.PUNTO); }
    "{"                          { return token(sym.LLAVE_A); }
    "}"                          { return token(sym.LLAVE_C); }
    "("                          { return token(sym.PAREN_A); }
    ")"                          { return token(sym.PAREN_C); }
    ","                          { return token(sym.COMA); }
    
    /* Operadores relacionales y lógicos */
    ">="                         { return token(sym.MAYOR_IGUAL); }
    "<="                         { return token(sym.MENOR_IGUAL); }
    "=="                         { return token(sym.IGUAL_IGUAL); }
    "!="                         { return token(sym.DIFERENTE); }
    ">"                          { return token(sym.MAYOR); }
    "<"                          { return token(sym.MENOR); }
    "="                          { return token(sym.IGUAL); }
    "+"                          { return token(sym.MAS); }
    "-"                          { return token(sym.MENOS); }
    "*"                          { return token(sym.POR); }
    "/"                          { return token(sym.DIV); }
    "%"                          { return token(sym.MOD); }
    "&&"                         { return token(sym.Y); }
    "||"                         { return token(sym.O); }
    "!"                          { return token(sym.NO); }

    {DECIMAL}                    { return token(sym.DECIMAL, yytext()); }
    {ENTERO}                     { return token(sym.NUMERO, yytext()); }
    {CADENA}                     { return token(sym.CADENA, yytext()); }
    {CARACTER}                   { return token(sym.CARACTER, yytext()); }
    {IDENTIFICADOR}              { return token(sym.IDENTIFICADOR, yytext()); }

    .                            { 
                                    System.out.println("Error léxico en línea " + (yyline + 1) + ", columna " + (yycolumn + 1) + ": carácter o token encontrado '" + yytext() + "'");
                                    return token(sym.ERROR_LEXICO, yytext());
                                 }
}

<<EOF>>                      { return token(sym.EOF); }