/* Cervantes Mora Juan Pablo */
/* Muñoz Madriz Nadya Janneth */
/* Ramirez Torres Jesus Adrian */

package act1_analisis_lexico;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java_cup.runtime.Symbol;

public class AnalizadorLexico {

    public static void main(String[] args) throws IOException {

        System.out.println("==============================================");
        System.out.println("       ANALIZADOR LEXICO DEL LENGUAJE C");
        System.out.println("==============================================");
        System.out.println("Integrantes:");
        System.out.println("Cervantes Mora Juan Pablo");
        System.out.println("Muñoz Madriz Nadya Janneth");
        System.out.println("Ramirez Torres Jesus Adrian");
        System.out.println();

        try {

            Reader lector = new BufferedReader(
                new FileReader(
                    "src/act1_analisis_lexico/tokens_c.txt"
                )
            );

            LexerC lexer = new LexerC(lector);

            System.out.println("==============================================");
            System.out.println("             INICIO DEL ANALISIS");
            System.out.println("==============================================");
            System.out.println();

            while (true) {

                Symbol token = lexer.next_token();

                if (token.sym == sym.EOF) {
                    break;
                }
            }

            System.out.println();
            System.out.println("==============================================");
            System.out.println("          FIN DEL ANALISIS LEXICO");
            System.out.println("==============================================");

        } catch (FileNotFoundException ex) {

            System.out.println(
                "ERROR: No se encontro el archivo de prueba."
            );

            System.out.println(
                "Verifica la ruta: " +
                "src/act1_analisis_lexico/tokens_c.txt"
            );

        } catch (IOException ex) {

            System.out.println(
                "ERROR de lectura: " + ex.getMessage()
            );
        }

        System.out.println();
        System.out.println("==============================================");
        System.out.println("Integrantes:");
        System.out.println("Cervantes Mora Juan Pablo");
        System.out.println("Muñoz Madriz Nadya Janneth");
        System.out.println("Ramirez Torres Jesus Adrian");
        System.out.println("==============================================");
    }
}