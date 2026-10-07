/* Muñoz Madriz Nadya Janneth */
/* Ramirez Torres Jesus Adrian */
/* Cervantes Mora Juan Pablo */

package act2_manejo_errores;
import java.io.IOException;

public class GeneradorCupSintantico {
    public static void main(String[] args) throws IOException, Exception {
        String[] parametros = {
            "-destdir", "src/act2_manejo_errores",
            "-parser", "ParserJava",
            "-symbols", "sym", 
            "src/act2_manejo_errores/c_lexico.cup"
        };
        java_cup.Main.main(parametros);
        System.out.println("Analizador sintáctico generado correctamente.");
    }
    
   
}