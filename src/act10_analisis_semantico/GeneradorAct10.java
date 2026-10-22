package act10_analisis_semantico;

import java.nio.file.Files;
import java.nio.file.Paths;

public class GeneradorAct10 {

    private static final String CARPETA = "act10_analisis_semantico";

    public static void main(String[] args) {
        String rutaBase = ubicarCarpeta();
        String rutaJFlex = rutaBase + "/Lexer10.jflex";

        try {
            jflex.Main.main(new String[] { rutaJFlex });
            System.out.println("Lexer10.java generado correctamente.");
        } catch (Exception e) {
            System.out.println("Error al generar Lexer10.java: " + e.getMessage());
        }

        String[] opcionesCUP = {
            "-destdir", rutaBase,
            "-parser", "parser10",
            "-symbols", "sym",
            rutaBase + "/parser10.cup"
        };

        try {
            java_cup.Main.main(opcionesCUP);
            System.out.println("parser10.java y sym.java generados correctamente.");
        } catch (Exception e) {
            System.out.println("Error al generar parser10.java y sym.java: " + e.getMessage());
        }
    }

    // Encuentra la carpeta de la actividad sin importar desde donde se ejecute.
    private static String ubicarCarpeta() {
        String[] candidatas = { ".", CARPETA, "src/" + CARPETA };
        for (String c : candidatas) {
            if (Files.exists(Paths.get(c, "Lexer10.jflex"))) {
                return c;
            }
        }
        return ".";
    }
}
