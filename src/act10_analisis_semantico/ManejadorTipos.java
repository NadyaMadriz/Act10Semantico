package act10_analisis_semantico;

/**
 * Reglas de verificacion de tipos (analisis semantico) de la Actividad 10.
 * Java es un lenguaje estaticamente tipado: cada variable, atributo y constante
 * tiene un tipo fijo desde su declaracion y las expresiones devuelven un tipo.
 * Tipos: int, float, double, char, boolean, string (o el nombre de una clase).
 * El tipo "error" significa que ya se reporto un error; se propaga en silencio
 * para no repetir mensajes.
 */
public class ManejadorTipos {

    public static final String ERROR = "error";

    /** Si es true, imprime una linea por cada operacion de tipos correcta. */
    public static boolean traza = true;

    // ---------------------------------------------------------------- utilidades

    public static boolean esNumerico(String t) {
        return "int".equals(t) || "float".equals(t) || "double".equals(t);
    }

    /** Tipo de un literal a partir de su texto. */
    public static String tipoDeLiteral(Object valor) {
        String v = String.valueOf(valor);
        if (v.startsWith("\"")) return "string";
        if (v.startsWith("'")) return "char";
        if (v.equals("verdadero") || v.equals("falso")) return "boolean";
        if (v.contains(".")) return "float";
        return "int";
    }

    /** Se puede guardar un valor de tipo 'origen' en algo de tipo 'destino'? */
    public static boolean asignable(String destino, String origen) {
        if (ERROR.equals(destino) || ERROR.equals(origen)) return true;
        if (destino.equals(origen)) return true;
        if ("float".equals(destino)) return "int".equals(origen);
        if ("double".equals(destino)) return "int".equals(origen) || "float".equals(origen);
        return false;
    }

    private static boolean hayError(String t1, String t2) {
        return ERROR.equals(t1) || ERROR.equals(t2);
    }

    private static void traza(String texto) {
        if (traza) System.out.println("  [tipos] " + texto);
    }

    // ---------------------------------------------------------------- operadores

    /** + - * / % */
    public static String aritmetica(String op, String t1, String t2, int linea, int col) {
        if (hayError(t1, t2)) return ERROR;
        // Concatenacion de cadenas con +
        if ("+".equals(op) && ("string".equals(t1) || "string".equals(t2))) {
            traza(t1 + " + " + t2 + " -> string (concatenación)");
            return "string";
        }
        if (esNumerico(t1) && esNumerico(t2)) {
            String res;
            if ("double".equals(t1) || "double".equals(t2)) res = "double";
            else if ("float".equals(t1) || "float".equals(t2)) res = "float";
            else res = "int";
            traza(t1 + " " + op + " " + t2 + " -> " + res);
            return res;
        }
        ReportarError.semantico(op,
            "El operador aritmético '" + op + "' no es aplicable a los operandos de tipo '"
            + t1 + "' y '" + t2 + "'.", linea, col);
        return ERROR;
    }

    /** > < >= <= */
    public static String relacional(String op, String t1, String t2, int linea, int col) {
        if (hayError(t1, t2)) return ERROR;
        if (esNumerico(t1) && esNumerico(t2)) {
            traza(t1 + " " + op + " " + t2 + " -> boolean");
            return "boolean";
        }
        ReportarError.semantico(op,
            "El operador relacional '" + op + "' no es aplicable a los operandos de tipo '"
            + t1 + "' y '" + t2 + "' (se requieren operandos numéricos).", linea, col);
        return ERROR;
    }

    /** == != */
    public static String igualdad(String op, String t1, String t2, int linea, int col) {
        if (hayError(t1, t2)) return ERROR;
        if ((esNumerico(t1) && esNumerico(t2)) || t1.equals(t2)) {
            traza(t1 + " " + op + " " + t2 + " -> boolean");
            return "boolean";
        }
        ReportarError.semantico(op,
            "El operador '" + op + "' no puede comparar operandos de tipo '"
            + t1 + "' y '" + t2 + "'.", linea, col);
        return ERROR;
    }

    /** && || */
    public static String logica(String op, String t1, String t2, int linea, int col) {
        if (hayError(t1, t2)) return ERROR;
        if ("boolean".equals(t1) && "boolean".equals(t2)) {
            traza(t1 + " " + op + " " + t2 + " -> boolean");
            return "boolean";
        }
        ReportarError.semantico(op,
            "El operador lógico '" + op + "' no es aplicable a los operandos de tipo '"
            + t1 + "' y '" + t2 + "' (se requieren operandos boolean).", linea, col);
        return ERROR;
    }

    /** ! */
    public static String negacion(String t, int linea, int col) {
        if (ERROR.equals(t)) return ERROR;
        if ("boolean".equals(t)) {
            traza("! boolean -> boolean");
            return "boolean";
        }
        ReportarError.semantico("!",
            "El operador lógico '!' no es aplicable al operando de tipo '" + t
            + "' (se requiere boolean).", linea, col);
        return ERROR;
    }

    /** - unario */
    public static String menosUnario(String t, int linea, int col) {
        if (ERROR.equals(t)) return ERROR;
        if (esNumerico(t)) {
            traza("- " + t + " -> " + t);
            return t;
        }
        ReportarError.semantico("-",
            "El operador aritmético '-' no es aplicable al operando de tipo '" + t
            + "' (se requiere un tipo numérico).", linea, col);
        return ERROR;
    }

    // ---------------------------------------------- uso de la Tabla de Simbolos

    /** Busca el identificador en la tabla y devuelve su tipo (o "error"). */
    public static String tipoDeIdentificador(TablaSimbolos tabla, String ambito,
                                             String id, int linea, int col) {
        Simbolo s = tabla.buscarVisible(id, ambito);
        if (s == null) {
            ReportarError.semantico(id,
                "El identificador '" + id + "' no ha sido declarado en este ámbito.", linea, col);
            return ERROR;
        }
        if (s.esMetodo() || s.esClase()) {
            ReportarError.semantico(id,
                "El identificador '" + id + "' es " + (s.esMetodo() ? "un método" : "una clase")
                + " y no puede usarse como variable en una expresión.", linea, col);
            return ERROR;
        }
        traza("'" + id + "' encontrado en la Tabla de Símbolos (" + s.getCategoria()
              + ", tipo " + s.getTipoDato() + ")");
        return s.getTipoDato();
    }

    private static String articulo(String categoria) {
        if ("CONSTANTE".equals(categoria)) return "a la constante";
        if ("ATRIBUTO".equals(categoria)) return "al atributo";
        return "a la variable";
    }

    /** Asignacion: id = expresion; */
    public static void validarAsignacion(TablaSimbolos tabla, String ambito, String id,
                                         String tipoExpr, int linea, int col) {
        Simbolo s = tabla.buscarVisible(id, ambito);
        if (s == null) {
            ReportarError.semantico(id,
                "El identificador '" + id + "' no ha sido declarado en este ámbito.", linea, col);
            return;
        }
        if (s.esConstante()) {
            ReportarError.semantico(id,
                "No se puede reasignar un valor a la constante '" + id + "'.", linea, col);
            return;
        }
        if (s.esMetodo() || s.esClase()) {
            ReportarError.semantico(id,
                "'" + id + "' no es una variable; no se le puede asignar un valor.", linea, col);
            return;
        }
        if (ERROR.equals(tipoExpr)) return; // el error ya se reporto en la expresion
        if (!asignable(s.getTipoDato(), tipoExpr)) {
            ReportarError.semantico(id,
                "Incompatibilidad de tipos: No se puede asignar un valor de tipo '" + tipoExpr + "' "
                + articulo(s.getCategoria()) + " '" + id + "' de tipo '" + s.getTipoDato() + "'.",
                linea, col);
            return;
        }
        traza("Asignación válida: '" + id + "' (" + s.getTipoDato() + ") = expresión de tipo '" + tipoExpr + "'");
    }

    /**
     * Declaracion con valor inicial: tipo id = valor;
     * Devuelve true si es correcta. Si devuelve false ya se reporto el error
     * y el simbolo NO debe registrarse en la Tabla de Simbolos.
     */
    public static boolean validarInicializacion(String categoria, String id, String tipoDeclarado,
                                                String tipoValor, int linea, int col) {
        if (ERROR.equals(tipoValor)) return false; // el error ya se reporto en la expresion
        if (!asignable(tipoDeclarado, tipoValor)) {
            ReportarError.semantico(id,
                "Incompatibilidad de tipos: No se puede asignar un valor de tipo '" + tipoValor + "' "
                + articulo(categoria) + " '" + id + "' de tipo '" + tipoDeclarado + "'.",
                linea, col);
            return false;
        }
        traza("Inicialización válida: '" + id + "' (" + tipoDeclarado + ") = valor de tipo '" + tipoValor + "'");
        return true;
    }

    /** Condicion de si / mientras / hacer-mientras / para: debe ser boolean. */
    public static void validarCondicion(String estructura, String tipo, int linea, int col) {
        if (ERROR.equals(tipo)) return;
        if (!"boolean".equals(tipo)) {
            ReportarError.semantico(estructura,
                "La expresión dentro de la condición '" + estructura
                + "' debe ser de tipo 'boolean', se encontró '" + tipo + "'.", linea, col);
            return;
        }
        traza("Condición de '" + estructura + "' es boolean: válida");
    }

    /** leer(id): el identificador debe existir y no ser constante. */
    public static void validarLectura(TablaSimbolos tabla, String ambito, String id,
                                      int linea, int col) {
        Simbolo s = tabla.buscarVisible(id, ambito);
        if (s == null) {
            ReportarError.semantico(id,
                "El identificador '" + id + "' no ha sido declarado en este ámbito.", linea, col);
            return;
        }
        if (s.esConstante()) {
            ReportarError.semantico(id,
                "No se puede reasignar un valor a la constante '" + id + "' (leer cambia su valor).",
                linea, col);
            return;
        }
        if (s.esMetodo() || s.esClase()) {
            ReportarError.semantico(id,
                "'" + id + "' no es una variable; no se puede usar en leer().", linea, col);
            return;
        }
        traza("Lectura válida: '" + id + "' (" + s.getTipoDato() + ")");
    }
}
