/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jp.auxiliar;

import java.io.File;

/**
 *
 * @author usuario
 */
public class OrdenacionDeDirectorios {
    
    private static int actual;
    private static File[] ficheros;
    private static String directorio;
    
    public static void crear(String ruta, String nombre){
        int vervose=-1;
        File directori= new File (ruta);
        if (directori.isDirectory()) {
            ficheros = directori.listFiles();
            directorio = ruta;
            for (File fichero : ficheros){
                vervose=vervose+1;
                if(fichero.getName().equals(nombre)){
                    break;
                }
                
            }            
        }
        actual=vervose;
        System.out.println(vervose);
    
    }
    
    public static String siguiente(){
        int siguiente=actual+1;
        if(siguiente>=ficheros.length){
        siguiente=0;
        }
        return ficheros[siguiente].getAbsolutePath();
    }
    
    public static String anterior(){
        int siguiente=actual-1;
        if(siguiente<0){
        siguiente=ficheros.length-1;
        }
        return ficheros[siguiente].getAbsolutePath();
    }
    
    public static String getDirectorioActual(){
        return directorio;
    }
    
}
