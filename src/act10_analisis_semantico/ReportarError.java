package act10_analisis_semantico;

/** Imprime los errores semanticos (con linea, columna, token y causa) y las advertencias, y los cuenta. */
public class ReportarError {
    private static int semanticos = 0;
    private static int advertencias = 0;

    public static void semantico(String token, String causa, int linea, int columna) {
        semanticos++;
        System.out.println("Error semántico en línea " + linea + ", columna " + columna + ":");
        System.out.println("Token: '" + token + "'");
        System.out.println("Causa: " + causa);
        System.out.println();
    }

    /** Una advertencia avisa algo, pero deja avanzar: el simbolo si se registra. */
    public static void advertencia(String token, String causa, int linea, int columna) {
        advertencias++;
        System.out.println("Advertencia en línea " + linea + ", columna " + columna + ":");
        System.out.println("Token: '" + token + "'");
        System.out.println("Causa: " + causa);
        System.out.println();
    }

    public static int getSemanticos() {
        return semanticos;
    }

    public static int getAdvertencias() {
        return advertencias;
    }

    public static void reiniciar() {
        semanticos = 0;
        advertencias = 0;
    }
}
