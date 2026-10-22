package act10_analisis_semantico;

import java.util.ArrayList;
import java.util.List;

/**
 * Tabla de Simbolos. Guarda clases, atributos, constantes, metodos y variables locales.
 * Los atributos y metodos viven en el ambito "GLOBAL"; las variables locales,
 * en el ambito del metodo donde se declararon.
 */
public class TablaSimbolos {
    public static final String GLOBAL = "GLOBAL";

    private final List<Simbolo> simbolos = new ArrayList<>();

    /** Devuelve true (y reporta el error) si ya existe un simbolo igual en el mismo ambito. */
    public boolean duplicado(Simbolo s) {
        for (Simbolo existente : simbolos) {
            if (existente.getNombre().equals(s.getNombre())
                    && existente.getAmbito().equals(s.getAmbito())
                    && mismoGrupo(existente, s)) {
                ReportarError.semantico(s.getNombre(),
                    "El identificador '" + s.getNombre() + "' ya fue declarado en el ámbito '"
                    + s.getAmbito() + "' (primera declaración en la línea " + existente.getLinea() + ").",
                    s.getLinea(), s.getColumna());
                return true;
            }
        }
        return false;
    }

    /** Inserta un simbolo. Si ya existe en el mismo ambito, reporta el error y no lo inserta. */
    public boolean insertar(Simbolo s) {
        if (duplicado(s)) {
            return false;
        }
        simbolos.add(s);
        if (ManejadorTipos.traza) {
            System.out.println("  [tabla] '" + s.getNombre() + "' registrado (" + s.getCategoria()
                + ", tipo " + s.getTipoDato() + ", ámbito " + s.getAmbito() + ")");
        }
        return true;
    }

    /** Busca un simbolo exactamente en un ambito. */
    public Simbolo buscar(String nombre, String ambito) {
        for (Simbolo s : simbolos) {
            if (s.getNombre().equals(nombre) && s.getAmbito().equals(ambito)) {
                return s;
            }
        }
        return null;
    }

    /** Busca primero en el ambito actual (variables locales) y despues en el global. */
    public Simbolo buscarVisible(String nombre, String ambitoActual) {
        Simbolo s = buscar(nombre, ambitoActual);
        if (s == null && !GLOBAL.equals(ambitoActual)) {
            s = buscar(nombre, GLOBAL);
        }
        return s;
    }

    public void mostrarTabla() {
        String formato = "%-18s %-11s %-9s %-14s %-12s %-6s %-7s%n";
        System.out.println("\n================ TABLA DE SIMBOLOS ================");
        System.out.printf(formato, "Nombre", "Categoria", "Tipo", "Valor", "Ambito", "Linea", "Columna");
        System.out.println("---------------------------------------------------------------------------------");
        for (Simbolo s : simbolos) {
            System.out.printf(formato, s.getNombre(), s.getCategoria(), s.getTipoDato(),
                    String.valueOf(s.getValor()), s.getAmbito(), s.getLinea(), s.getColumna());
        }
        System.out.println("=================================================\n");
    }

    // Un metodo puede llamarse igual que un atributo; solo se compara dentro del mismo grupo.
    private boolean mismoGrupo(Simbolo a, Simbolo b) {
        return grupo(a) == grupo(b);
    }

    private int grupo(Simbolo s) {
        if (s.esMetodo()) return 1;
        if (s.esClase()) return 2;
        return 0; // atributos, constantes y variables
    }
}
