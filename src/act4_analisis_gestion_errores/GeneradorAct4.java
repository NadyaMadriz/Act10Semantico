package act4_analisis_gestion_errores;

import java.io.File;

public class GeneradorAct4 {
    public static void main(String[] args) {
        String rutaBase = "src/act4_analisis_gestion_errores/";

        String rutaJFlex = rutaBase + "Lexer4.jflex";
        try {
            jflex.Main.main(new String[] { rutaJFlex });
            System.out.println("Lexer4 generado correctamente.");
        } catch (Exception e) {
            System.out.println("Error al generar Lexer4: " + e.getMessage());
        }

        //generar CUP (Parser y Sym)
        String[] opcionesCUP = {
            "-destdir", rutaBase,
            "-parser", "Parser4",
            "-symbols", "sym",
            rutaBase + "Parser4.cup"
        };
        try {
            java_cup.Main.main(opcionesCUP);
            System.out.println("Parser4 y sym4 generados correctamente.");
        } catch (Exception e) {
            System.out.println("Error al generar Parser4 y sym4: " + e.getMessage());
        }
    }
}
