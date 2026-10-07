package act5_analisis_sintactico;

import java_cup.runtime.Symbol;

%%

%class Lexer5
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
    {ESPACIO}                   { /* ignorar */ }

    "package"                  { return token(sym.PAQUETE); }
    "import"                   { return token(sym.IMPORTAR); }
    "class"                    { return token(sym.CLASE); }
    "final"                    { return token(sym.FINAL); }
    "int"                      { return token(sym.INT); }
    "float"                    { return token(sym.FLOAT); }
    "double"                   { return token(sym.DOUBLE); }
    "char"                     { return token(sym.CHAR); }
    "boolean"                  { return token(sym.BOOLEAN); }
    "String"                   { return token(sym.STRING); }
    "true"                     { return token(sym.VERDADERO); }
    "false"                    { return token(sym.FALSO); }

    ";"                        { return token(sym.PUNTO_COMA); }
    "."                        { return token(sym.PUNTO); }
    "{"                        { return token(sym.LLAVE_A); }
    "}"                        { return token(sym.LLAVE_C); }
    ","                        { return token(sym.COMA); }
    "="                        { return token(sym.IGUAL); }

    {ENTERO}                   { return token(sym.NUMERO, yytext()); }
    {DECIMAL}                  { return token(sym.DECIMAL, yytext()); }
    {CADENA}                   { return token(sym.CADENA, yytext()); }
    {CARACTER}                 { return token(sym.CARACTER, yytext()); }
    {IDENTIFICADOR}            { return token(sym.IDENTIFICADOR, yytext()); }

    .                          { 
                                    System.out.println("Error léxico en línea " + (yyline + 1) + ", columna " + (yycolumn + 1) + ": carácter o token encontrado '" + yytext() + "'");
                                    return token(sym.ERROR_LEXICO, yytext());
                                }
}

<<EOF>>                       { return token(sym.EOF); }
