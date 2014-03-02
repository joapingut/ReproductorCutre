package jp.auxiliar;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 *
 * @author Joaquín
 */
public class OrdenacionDeDirectorios {
    
    private static int actual;
    private static File[] ficheros;
    private static String directorio;
    private static Random randoni;
    
    public static void crear(String ruta, String nombre){
        int vervose=-1;
        File directori= new File (ruta);
        List<File> aux = new ArrayList<File>();
        if (directori.isDirectory()) {
            directorio = ruta;
            for (File fichero : directori.listFiles()){
                if(fichero.isDirectory())
                    continue;
                vervose=vervose+1;
                if(fichero.getName().equals(nombre)){
                    actual=vervose;
                }
                aux.add(fichero);
            }            
        }
        System.out.println(aux);
        ficheros = new File[aux.size()];
        for(int i = 0; i<aux.size();i++)
            ficheros[i] = aux.get(i);
        randoni = new Random();
        
        System.out.println(vervose);
    }
    
    public static String siguiente(){
        int siguiente=actual+1;
        if(siguiente>=ficheros.length){
        siguiente=0;
        }
        actual = siguiente;
        return ficheros[siguiente].getAbsolutePath();
    }
    
    public static String anterior(){
        int siguiente=actual-1;
        if(siguiente<0){
        siguiente=ficheros.length-1;
        }
        actual = siguiente;
        return ficheros[siguiente].getAbsolutePath();
    }
    
    public static String aleatorio(){
        int ran = actual;
        while (ran == actual){
            ran = randoni.nextInt(ficheros.length-1);
        }
        actual = ran;
        return ficheros[ran].getAbsolutePath();
    }
    
    public static String getDirectorioActual(){
        return directorio;
    }
    
    public static String cadenaInfo(){
        String res;
        int mal = 30;
        int oxi = actual-1;
        if(oxi<0){
            oxi = ficheros.length-1;
        }
        String aux = ficheros[oxi].getName();
        if(aux.length()<30){
            mal = aux.length();
            res = aux.substring(0, mal) + "    || ";
        }else{
            res = aux.substring(0, mal) + "... || ";
        }
        oxi = actual+1;
        if(oxi>=ficheros.length){
            oxi = 0;
        }
        aux = ficheros[oxi].getName();
        if(aux.length()<30){
            mal = aux.length();
            res = res + aux.substring(0, mal);
        }else{
            mal = 30;
            aux = ficheros[actual+1].getName();
            res = res + aux.substring(0, mal)+"...";
        }
        return res;
    }
    
}
