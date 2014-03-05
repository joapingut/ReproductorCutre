package jp.auxiliar;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Joaquin
 * 
 * Modulo encargado de aprender que extensiones son posibles de reproducir
 * para evitar mas errores de la cuenta
 */
public class ModuloAntiErrores {
    
    private static boolean enUso = false;
    private static boolean omitir = false;
    private static String archivo = "/resources/Formatos.txt";
    private static Set<String> mapa;
    
    public static void Cargar(){
        try {
            InputStream is = ModuloAntiErrores.class.getResource(archivo).openStream();
            InputStreamReader reade =  new InputStreamReader(is, "UTF-8");
            BufferedReader reader = new BufferedReader(reade);
            String linea;
            mapa =  new HashSet<String>();
            while((linea=reader.readLine())!=null){
                mapa.add(linea);
            }
            System.out.println(mapa);
            enUso = true;
            reader.close();
            reade.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ModuloAntiErrores.class.getName()).log(Level.SEVERE, null, ex);
            enUso = false;
        } catch (IOException ex) {
            Logger.getLogger(ModuloAntiErrores.class.getName()).log(Level.SEVERE, null, ex);
            enUso = false;
        }
    }
    
    public static boolean esAceptable(String ext){
        if(!enUso || omitir)
            return true;
        if(mapa.contains(ext))
            return true;
        return false;
    }
}
