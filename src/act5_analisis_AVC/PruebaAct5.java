package act5_analisis_AVC;

import java.io.StringReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class PruebaAct5 {
    public static void main(String[] args) {
        String rutaArchivo = (args.length > 0) ? args[0] : "prueba_valida.txt";

        System.out.println("Cervantes Mora Juan Pablo");
        System.out.println("Muñoz Madriz Nadya Janneth");
        System.out.println("Ramirez Torres Jesus Adrian");
        System.out.println("==============================================");
        System.out.println("       INICIO DE ANÁLISIS SINTÁCTICO");
        System.out.println("==============================================");

        try {
            Path path = Paths.get(rutaArchivo);
            String contenido = Files.readString(path, StandardCharsets.UTF_8);
            System.out.println("Analizando archivo: " + path.toAbsolutePath() + "\n");

            Lexer5 lexer = new Lexer5(new StringReader(contenido));
            Parser5 parser = new Parser5(lexer);
            parser.parse();

            System.out.println("\n==============================================");
            System.out.println("       ANALISIS SINTACTICO FINALIZADO");
            System.out.println("==============================================");
        } catch (Exception e) {
            System.out.println("\n[AVISO] El análisis se detuvo debido a un error fatal o excepción: " + e.getMessage());
        }

        System.out.println("Cervantes Mora Juan Pablo");
        System.out.println("Muñoz Madriz Nadya Janneth");
        System.out.println("Ramirez Torres Jesus Adrian");
    }
}
