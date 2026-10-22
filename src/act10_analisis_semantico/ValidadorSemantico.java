package act10_analisis_semantico;

/** Validaciones de convenciones de nombres (se conservan de la Act 9). */
public class ValidadorSemantico {

    /** Clases: PascalCase (empieza con mayuscula, sin guiones bajos). */
    public static boolean esPascalCase(String nombre) {
        return nombre != null && nombre.matches("[A-Z][A-Za-z0-9]*");
    }

    /** Atributos y metodos: camelCase (empieza con minuscula, sin guiones bajos). */
    public static boolean esCamelCase(String nombre) {
        return nombre != null && nombre.matches("[a-z][A-Za-z0-9]*");
    }
}
