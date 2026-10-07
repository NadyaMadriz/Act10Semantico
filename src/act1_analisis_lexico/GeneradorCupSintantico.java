/* Muñoz Madriz Nadya Janneth */
/* Ramirez Torres Jesus Adrian */
/* Cervantes Mora Juan Pablo */

package act1_analisis_lexico;
import java.io.IOException;

public class GeneradorCupSintantico {
    public static void main(String[] args) throws IOException, Exception {
        String[] parametros = {
            "-destdir", "src/act1_analisis_lexico",
            "-parser", "ParserJava",
            "-symbols", "sym", 
            "src/act1_analisis_lexico/c_lexico.cup"
        };
        java_cup.Main.main(parametros);
        System.out.println("Analizador sintáctico generado correctamente.");
    }
    
   
}