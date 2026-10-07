/* Muñoz Madriz Nadya Janneth */
/* Ramirez Torres Jesus Adrian */
/* Cervantes Mora Juan Pablo */

package act2_manejo_errores;

import jflex.Main;
//import jflex.exceptions.SilentExit;

/**
 *
 * @author Profesor
 */
public class GeneradorJFlexLexico {
    public static void main(String[] args) {
        
        try {
            String rutaflex="src/act2_manejo_errores/Lexer.jflex";
            
            String datos[]={rutaflex};
            
            Main.generate(datos);
        } catch (Exception ex) {
            System.err.println(ex.getMessage());
            System.getLogger(GeneradorJFlexLexico.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
        
    }
}