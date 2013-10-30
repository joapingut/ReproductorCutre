/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jp.interfaz.errores;

/**
 *
 * @author Joaquín
 */
public class ReproductorError extends IllegalArgumentException{

    String mensjae;
    
    public ReproductorError(String s) {
        super(s);
        mensjae = s;
    }
    
    public String getMensaje(){
        return mensjae;
    }
}
