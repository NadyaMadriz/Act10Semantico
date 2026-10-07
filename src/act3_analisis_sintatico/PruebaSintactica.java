/* Cervantes Mora Juan Pablo */
/* Muñoz Madriz Nadya Janneth */
/* Ramirez Torres Jesus Adrian */

package act3_analisis_sintatico;

import java.io.FileReader;

public class PruebaSintactica {

    public static void main(String[] args) throws Exception {

    System.out.println("Cervantes Mora Juan Pablo");
    System.out.println("Muñoz Madriz Nadya Janneth");
    System.out.println("Ramirez Torres Jesus Adrian");

    System.out.println("==============================================");
    System.out.println("       PRUEBA DEL ANALIZADOR SINTACTICO");
        System.out.println();

        LexerC lexer = new LexerC(
            new FileReader("prueba.txt")
        );

        ParserJava parser = new ParserJava(lexer);

        parser.parse();

        System.out.println();
        System.out.println("==============================================");
        System.out.println("       ANALISIS SINTACTICO FINALIZADO");
        System.out.println("==============================================");
        System.out.println("Cervantes Mora Juan Pablo");
        System.out.println("Muñoz Madriz Nadya Janneth");
        System.out.println("Ramirez Torres Jesus Adrian");
    }
}