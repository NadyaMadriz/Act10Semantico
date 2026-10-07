package act5_analisis_AVC;

public class GeneradorAct5 {
    public static void main(String[] args) {
        String rutaBase = ".";

        String rutaJFlex = rutaBase + "/Lexer5.jflex";
        try {
            jflex.Main.main(new String[] { rutaJFlex });
            System.out.println("Lexer5.java generado correctamente.");
        } catch (Exception e) {
            System.out.println("Error al generar Lexer5.java: " + e.getMessage());
        }

        String[] opcionesCUP = {
            "-destdir", rutaBase,
            "-parser", "Parser5",
            "-symbols", "sym",
            rutaBase + "/parser5.cup"
        };
        try {
            java_cup.Main.main(opcionesCUP);
            System.out.println("Parser5.java y sym.java generados correctamente.");
        } catch (Exception e) {
            System.out.println("Error al generar Parser5.java y sym.java: " + e.getMessage());
        }
    }
}
