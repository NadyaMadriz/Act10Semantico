/* Cervantes Mora Juan Pablo */
/* Muñoz Madriz Nadya Janneth */
/* Ramirez Torres Jesus Adrian */

package act3_analisis_sintatico;
import java.io.IOException;

public class GeneradorCupSintantico {
    public static void main(String[] args) throws IOException, Exception {
        String[] parametros = {
    "-destdir", "src/act3_analisis_sintatico",
    "-parser", "ParserJava",
    "-symbols", "sym",
    "src/act3_analisis_sintatico/c_lexico.cup"
};
        java_cup.Main.main(parametros);
        System.out.println("Analizador sintáctico generado correctamente.");
    }
    
   
}