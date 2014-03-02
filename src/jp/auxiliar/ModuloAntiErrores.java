package jp.auxiliar;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
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
    private static File archivo;
    private static Map<String, Integer> mapa;
    
    public static void Cargar(){
        try {
            archivo = new File(ModuloAntiErrores.class.getResource("/resources/Formatos.txt").toURI());
            FileReader reade =  new FileReader(archivo);
            BufferedReader reader = new BufferedReader(reade);
            String linea;
            mapa =  new HashMap<String, Integer>();
            while((linea=reader.readLine())!=null){
                mapa.put(linea.substring(0, linea.lastIndexOf("\\")), Integer.parseInt(linea.substring(linea.lastIndexOf("\\")+1, linea.length())));
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
        } catch (URISyntaxException ex) {
            Logger.getLogger(ModuloAntiErrores.class.getName()).log(Level.SEVERE, null, ex);
            enUso = false;
        }
    }
    
    public static boolean esAceptable(String ext){
        if(!enUso)
            return true;
        if(mapa.containsKey(ext))
            return mapa.get(ext) < 6;
        return true;
    }
    
    public static void apuntarFallo(String ext){
        if(!enUso)
            return;
        if(mapa.containsKey(ext))
            mapa.put(ext, mapa.get(ext) + 1);
        else 
            mapa.put(ext, 1);
    }
    
    public static void apuntarAcierto(String ext){
        if(!enUso)
            return;
        if(mapa.containsKey(ext))
            mapa.put(ext, mapa.get(ext) - 1);
        else 
            mapa.put(ext, 0);
    }
    
    public static void guardar(){
        if(!enUso)
            return;
        try {
            FileWriter reade =  new FileWriter(archivo);
            PrintWriter writer = new PrintWriter(reade);
            String linea;
            for(Entry e :mapa.entrySet()){
                linea = e.getKey()+"\\"+e.getValue();
                writer.println(linea);
            }
            System.out.println(mapa);
            writer.close();
            reade.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ModuloAntiErrores.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ModuloAntiErrores.class.getName()).log(Level.SEVERE, null, ex);
        }
        enUso = false;
    }
}
