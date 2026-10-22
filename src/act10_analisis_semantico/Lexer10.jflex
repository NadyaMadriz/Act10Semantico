package act10_analisis_semantico;

import java_cup.runtime.Symbol;

%%

%class Lexer10
%type java_cup.runtime.Symbol
%cup
%unicode
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
COMENTARIO_LINEA = "//" [^\r\n]*
COMENTARIO_BLOQUE = "/*" ~"*/"

%%

<YYINITIAL> {

  {ESPACIO}             { /* ignorar */ }
  {COMENTARIO_LINEA}    { /* ignorar */ }
  {COMENTARIO_BLOQUE}   { /* ignorar */ }

  "package"             { return token(sym.PAQUETE); }
  "import"              { return token(sym.IMPORTAR); }
  "class" | "clase"     { return token(sym.CLASE); }
  "final" | "const"     { return token(sym.FINAL); }
  "void"                { return token(sym.VOID); }
  "leer"                { return token(sym.LEER); }
  "escribir"            { return token(sym.ESCRIBIR); }
  "int"                 { return token(sym.INT); }
  "float"               { return token(sym.FLOAT); }
  "double"              { return token(sym.DOUBLE); }
  "char"                { return token(sym.CHAR); }
  "boolean"             { return token(sym.BOOLEAN); }
  "String"              { return token(sym.STRING); }
  "true" | "verdadero"  { return token(sym.VERDADERO); }
  "false" | "falso"     { return token(sym.FALSO); }

  /* Estructuras de control (Act 7) */
  "si"                  { return token(sym.SI); }
  "sino"                { return token(sym.SINO); }
  "segun"               { return token(sym.SEGUN); }
  "caso"                { return token(sym.CASO); }
  "defecto"             { return token(sym.DEFECTO); }
  "romper"              { return token(sym.ROMPER); }
  "mientras"            { return token(sym.MIENTRAS); }
  "hacer"               { return token(sym.HACER); }
  "para"                { return token(sym.PARA); }

  ";"                   { return token(sym.PUNTO_COMA); }
  ":"                   { return token(sym.DOS_PUNTOS); }
  "."                   { return token(sym.PUNTO); }
  "{"                   { return token(sym.LLAVE_A); }
  "}"                   { return token(sym.LLAVE_C); }
  "("                   { return token(sym.PAREN_A); }
  ")"                   { return token(sym.PAREN_C); }
  ","                   { return token(sym.COMA); }

  /* Operadores relacionales y logicos */
  ">="                  { return token(sym.MAYOR_IGUAL); }
  "<="                  { return token(sym.MENOR_IGUAL); }
  "=="                  { return token(sym.IGUAL_IGUAL); }
  "!="                  { return token(sym.DIFERENTE); }
  ">"                   { return token(sym.MAYOR); }
  "<"                   { return token(sym.MENOR); }
  "="                   { return token(sym.IGUAL); }

  "+"                   { return token(sym.MAS); }
  "-"                   { return token(sym.MENOS); }
  "*"                   { return token(sym.POR); }
  "/"                   { return token(sym.DIV); }
  "%"                   { return token(sym.MOD); }
  "&&"                  { return token(sym.Y); }
  "||"                  { return token(sym.O); }
  "!"                   { return token(sym.NO); }

  {DECIMAL}             { return token(sym.DECIMAL, yytext()); }
  {ENTERO}              { return token(sym.NUMERO, yytext()); }
  {CADENA}              { return token(sym.CADENA, yytext()); }
  {CARACTER}            { return token(sym.CARACTER, yytext()); }
  {IDENTIFICADOR}       { return token(sym.IDENTIFICADOR, yytext()); }

  .                     {
                            System.out.println("Error léxico en línea " + (yyline + 1) + ", columna " + (yycolumn + 1) + ": carácter o token encontrado '" + yytext() + "'");
                            return token(sym.ERROR_LEXICO, yytext());
                        }
}

<<EOF>>                 { return token(sym.EOF); }
