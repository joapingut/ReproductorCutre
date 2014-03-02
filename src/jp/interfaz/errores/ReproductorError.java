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
