package act7_estructuras_control;

public class GeneradorAct7 {
    public static void main(String[] args) {
        try {
            jflex.Main.main(new String[] { "act7_estructuras_control/Lexer7.jflex" });
            System.out.println("Lexer7 generado con exito.");
        } catch (Exception e) {
            System.out.println("Error JFlex: " + e.getMessage());
        }

        try {
            java_cup.Main.main(new String[] {
                "-destdir", "act7_estructuras_control",
                "-parser", "Parser7",
                "-symbols", "sym",
                "act7_estructuras_control/parser7.cup"
            });
            System.out.println("Parser7 y sym generados con exito.");
        } catch (Exception e) {
            System.out.println("Error CUP: " + e.getMessage());
        }
    }
}