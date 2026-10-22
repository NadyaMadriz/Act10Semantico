package act10_analisis_semantico;

/**
 * Una fila de la Tabla de Simbolos.
 * categoria: CLASE, ATRIBUTO, CONSTANTE, METODO o VARIABLE.
 */
public class Simbolo {
    private final String nombre;
    private final String categoria;
    private final String tipoDato;
    private final Object valor;
    private final String ambito;
    private final int linea;
    private final int columna;
    private final String token;

    public Simbolo(String nombre, String categoria, String tipoDato, Object valor,
                   String ambito, int linea, int columna, String token) {
        this.nombre = nombre;
        this.categoria = categoria;
        this.tipoDato = tipoDato;
        this.valor = valor;
        this.ambito = ambito;
        this.linea = linea;
        this.columna = columna;
        this.token = token;
    }

    public String getNombre()    { return nombre; }
    public String getCategoria() { return categoria; }
    public String getTipoDato()  { return tipoDato; }
    public Object getValor()     { return valor; }
    public String getAmbito()    { return ambito; }
    public int getLinea()        { return linea; }
    public int getColumna()      { return columna; }
    public String getToken()     { return token; }

    public boolean esConstante() { return "CONSTANTE".equals(categoria); }
    public boolean esMetodo()    { return "METODO".equals(categoria); }
    public boolean esClase()     { return "CLASE".equals(categoria); }
}
