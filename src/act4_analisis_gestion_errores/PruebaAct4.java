package act4_analisis_gestion_errores;

import java.io.StringReader;
import java.nio.file.Files;
import java.nio.file.Paths;

public class PruebaAct4 {
    public static void main(String[] args) {
        System.out.println("Cervantes Mora Juan Pablo");
        System.out.println("Muñoz Madriz Nadya Janneth");
        System.out.println("Ramirez Torres Jesus Adrian");

        System.out.println("==============================================");
        System.out.println("       INICIO DE ANÁLISIS SINTÁCTICO");
        System.out.println("==============================================");

        String rutaArchivo = "src/act4_analisis_gestion_errores/prueba_errores.txt";

        try {
            String contenido = new String(Files.readAllBytes(Paths.get(rutaArchivo)));
            System.out.println("Analizando archivo: " + rutaArchivo + "\n");

            LexerC4 lexer = new LexerC4(new StringReader(contenido));
            Parser4 parser = new Parser4(lexer);

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
