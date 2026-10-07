/* Cervantes Mora Juan Pablo */
/* Muñoz Madriz Nadya Janneth */
/* Ramirez Torres Jesus Adrian */

package act2_manejo_errores;

/**
 * Describe un error encontrado durante el análisis léxico.
 */
public final class LexicalError {
    private final String tipo;
    private final int linea;
    private final int columna;
    private final String lexema;
    private final String descripcion;

    public LexicalError(String tipo, int linea, int columna, String lexema, String descripcion) {
        this.tipo = tipo;
        this.linea = linea;
        this.columna = columna;
        this.lexema = lexema;
        this.descripcion = descripcion;
    }

    public String getTipo() {
        return tipo;
    }

    public int getLinea() {
        return linea;
    }

    public int getColumna() {
        return columna;
    }

    public String getLexema() {
        return lexema;
    }

    public String getDescripcion() {
        return descripcion;
    }

    @Override
    public String toString() {
        return String.format(
                "ERROR LEXICO | tipo=%s | linea=%d | columna=%d | lexema=\"%s\" | %s",
                tipo, linea, columna, lexema, descripcion);
    }
}