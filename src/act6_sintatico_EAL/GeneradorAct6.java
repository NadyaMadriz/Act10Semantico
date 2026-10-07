package act6_sintatico_EAL;

import java.nio.file.Files;
import java.nio.file.Paths;

public class GeneradorAct6 {

    private static final String CARPETA = "act6_sintatico_EAL";

    public static void main(String[] args) {
        String rutaBase = ubicarCarpeta();
        String rutaJFlex = rutaBase + "/Lexer6.jflex";

        try {
            jflex.Main.main(new String[] { rutaJFlex });
            System.out.println("Lexer6.java generado correctamente.");
        } catch (Exception e) {
            System.out.println("Error al generar Lexer6.java: " + e.getMessage());
        }

        String[] opcionesCUP = {
            "-destdir", rutaBase,
            "-parser", "Parser6",
            "-symbols", "sym",
            rutaBase + "/parser6.cup"
        };

        try {
            java_cup.Main.main(opcionesCUP);
            System.out.println("Parser6.java y sym.java generados correctamente.");
        } catch (Exception e) {
            System.out.println("Error al generar Parser6.java y sym.java: " + e.getMessage());
        }
    }

    // Encuentra la carpeta de la actividad sin importar desde donde se ejecute.
    private static String ubicarCarpeta() {
        String[] candidatas = { ".", CARPETA, "src/" + CARPETA };
        for (String c : candidatas) {
            if (Files.exists(Paths.get(c, "Lexer6.jflex"))) {
                return c;
            }
        }
        return ".";
    }
}
