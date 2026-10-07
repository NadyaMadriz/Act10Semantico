package act6_sintatico_EAL;

import java.io.StringReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class PruebaAct6 {

    private static final String CARPETA = "act6_sintatico_EAL";

    public static void main(String[] args) {
        String rutaArchivo = (args.length > 0) ? args[0] : "prueba_valida.txt";

        imprimirIntegrantes();
        System.out.println("==============================================");
        System.out.println("       INICIO DE ANÁLISIS SINTÁCTICO");
        System.out.println("==============================================");

        try {
            Path path = buscarArchivo(rutaArchivo);
            String contenido = Files.readString(path, StandardCharsets.UTF_8);
            System.out.println("Analizando archivo: " + path.toAbsolutePath() + "\n");

            Lexer6 lexer = new Lexer6(new StringReader(contenido));
            Parser6 parser = new Parser6(lexer);
            parser.parse();

            System.out.println("\n==============================================");
            System.out.println("         ANALISIS SINTACTICO FINALIZADO");
            System.out.println("==============================================");
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
