/* Muñoz Madriz Nadya Janneth */
/* Ramirez Torres Jesus Adrian */
/* Cervantes Mora Juan Pablo */

package act1_analisis_lexico;

import jflex.Main;
//import jflex.exceptions.SilentExit;

/**
 *
 * @author Profesor
 */
public class GeneradorJFlexLexico {
    public static void main(String[] args) {
        
        try {
            String rutaflex="src/act1_analisis_lexico/Lexer.jflex";
            
            String datos[]={rutaflex};
            
            Main.generate(datos);
        } catch (Exception ex) {
            System.err.println(ex.getMessage());
            System.getLogger(GeneradorJFlexLexico.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
        
    }
}