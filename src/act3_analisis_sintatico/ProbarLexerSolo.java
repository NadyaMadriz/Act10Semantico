package act3_analisis_sintatico;

import java.io.FileReader;
import java_cup.runtime.Symbol;

public class ProbarLexerSolo {
    public static void main(String[] args) throws Exception {
        LexerC lexer = new LexerC(new FileReader("prueba.txt"));
        Symbol s;
        while ((s = lexer.next_token()).sym != sym.EOF) {
            System.out.println("Token ID: " + s.sym + " | Lexema: " + s.value);
        }
    }
    
}
