/* Cervantes Mora Juan Pablo */
/* Muñoz Madriz Nadya Janneth */
/* Ramirez Torres Jesus Adrian */

package act2_manejo_errores;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.List;
import java_cup.runtime.Symbol;

public class AnalizadorLexico {

    public static void main(String[] args) throws IOException {
        System.out.println("==============================================");
        System.out.println(" ANALIZADOR LEXICO - ACTIVIDAD 2 (ERRORES)");
        System.out.println("==============================================");
        System.out.println("Integrantes:");
        System.out.println("Cervantes Mora Juan Pablo");
        System.out.println("Muñoz Madriz Nadya Janneth");
        System.out.println("Ramirez Torres Jesus Adrian ");
        System.out.println();

        try {
            // Puedes cambiar la ruta a tu archivo de prueba con errores si lo deseas
            String ruta = args.length > 0
                    ? args[0]
                    : "src/act2_manejo_errores/tokens_c.txt";

            Reader lector = new BufferedReader(new FileReader(ruta));
            LexerC lexer = new LexerC(lector);
            int tokensReconocidos = 0;

            System.out.println("==============================================");
            System.out.println(" INICIO DEL ANALISIS");
            System.out.println("==============================================");
            System.out.println();

            while (true) {
                Symbol token = lexer.next_token();
                if (token.sym == sym.EOF) {
                    break;
                }
                tokensReconocidos++;
            }

            // Obtener la lista de errores capturados por el lexer
            List<LexicalError> errores = lexer.getErrores();

            System.out.println();
            System.out.println("==============================================");
            System.out.println(" RESUMEN DEL ANALISIS");
            System.out.println("==============================================");
            System.out.println("Tokens reconocidos: " + tokensReconocidos);
            System.out.println("Errores léxicos: " + errores.size());
            
            if (!errores.isEmpty()) {
                System.out.println("\nDetalle de errores:");
                for (LexicalError error : errores) {
                    System.out.println(error);
                }
            }

            System.out.println();
            System.out.println("==============================================");
            System.out.println(" FIN DEL ANALISIS LEXICO");
            System.out.println("==============================================");

        } catch (FileNotFoundException ex) {
            System.out.println("ERROR: No se encontró el archivo de prueba.");
            System.out.println("Verifica la ruta configurada.");
        } catch (IOException ex) {
            System.out.println("ERROR de lectura: " + ex.getMessage());
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