/* Muñoz Madriz Nadya Janneth */
/* Ramirez Torres Jesus Adrian */
/* Cervantes Mora Juan Pablo */

package act3_analisis_sintatico;

import jflex.Main;
//import jflex.exceptions.SilentExit;

/**
 *
 * @author Profesor
 */
public class GeneradorJFlexLexico {
    public static void main(String[] args) {
        
        try {
            String rutaflex="src/act3_analisis_sintatico/Lexer.jflex";
            
            String datos[]={rutaflex};
            
            Main.generate(datos);
        } catch (Exception ex) {
            System.err.println(ex.getMessage());
            System.getLogger(GeneradorJFlexLexico.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
        
    }
}