package act10_analisis_semantico;

import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Main {

    private static final String CARPETA = "act10_analisis_semantico";

    public static void main(String[] args) {
        String rutaArchivo = (args.length > 0) ? args[0] : "prueba_valida.txt";

        imprimirIntegrantes();
        System.out.println("==============================================");
        System.out.println("   INICIO DE ANALISIS SEMANTICO (ACTIVIDAD 10)");
        System.out.println("==============================================");

        try {
            Path path = buscarArchivo(rutaArchivo);
            System.out.println("Analizando archivo: " + path.toAbsolutePath() + "\n");

            ReportarError.reiniciar();
            Reader lector = new InputStreamReader(Files.newInputStream(path), StandardCharsets.UTF_8);
            Lexer10 lexer = new Lexer10(lector);
            parser10 parser = new parser10(lexer);
            parser.parse();

            System.out.println("==============================================");
            System.out.println("       RESULTADO DEL ANALISIS SEMANTICO");
            System.out.println("==============================================");
            int errores = ReportarError.getSemanticos();
            if (errores == 0) {
                System.out.println("Sin errores semánticos: los tipos de todas las expresiones son correctos.");
            } else {
                System.out.println("Errores semánticos encontrados: " + errores);
            }

            System.out.println("Advertencias: " + ReportarError.getAdvertencias());
            parser.tabla.mostrarTabla();
        } catch (Exception e) {
            System.out.println("\n[AVISO] El análisis se detuvo debido a un error fatal o excepción: " + e.getMessage());
        }

        imprimirIntegrantes();
    }

    private static void imprimirIntegrantes() {
        System.out.println("Cervantes Mora Juan Pablo");
        System.out.println("Muñoz Madriz Nadya Janneth");
        System.out.println("Ramirez Torres Jesus Adrian");
    }

    // Busca el archivo tal cual, dentro de la carpeta de la actividad o dentro de src.
    private static Path buscarArchivo(String ruta) {
        String[] candidatas = { ruta, CARPETA + "/" + ruta, "src/" + CARPETA + "/" + ruta };
        for (String c : candidatas) {
            Path p = Paths.get(c);
            if (Files.exists(p)) {
                return p;
            }
        }
        return Paths.get(ruta);
    }
}
