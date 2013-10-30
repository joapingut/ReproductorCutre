/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jp;

import java.io.*;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author usuario
 */
public class ModuloActualizacion implements Serializable {
    public static String nombre = "RepCutre";
    public static Double version=0.5;
    public static Integer revision=5;
    public static Double verActualizador=0.1;
    public static Date fecha;
        
    static String ruta="Actu.kin";
    static File txt= new File (ruta);
    static FileOutputStream escritor = null;
    static ObjectOutputStream pescritor = null;
    static FileInputStream lector = null;
    static ObjectInputStream blector = null;
    
    public static void crearkin(){
    if(txt.exists()){
    //Ya esiste de momento no es necesario hacer nada, mas adelante alomejor habra que borrar algo
        borrarKin();
    }
        try{
            escritor= new FileOutputStream(ruta);
            pescritor= new ObjectOutputStream(escritor);
        }catch (Exception e){
        String mensaje =("Error al crear el arcivo txt de opciones, el programa se va a cerrar, vuelva a ejecutarlo, si persiste el error pregunta a joaquin");
        errorFatal(mensaje);
        }
            try {
                pescritor.writeObject(nombre);
                pescritor.flush();
                pescritor.writeObject(version);
                pescritor.flush();
                pescritor.writeObject(revision);
                pescritor.flush();
                pescritor.writeObject(verActualizador);
                pescritor.flush();
                fecha= new java.util.Date();
                pescritor.writeObject(fecha);
                pescritor.flush();
            } catch (IOException ex) {
                Logger.getLogger(ModuloActualizacion.class.getName()).log(Level.SEVERE, null, ex);
            }
    }
    
    /*
    public static void modificarTXT(){
    try{ 
         lector = new FileInputStream (txt);
         blector = new ObjectInputStream(lector);
         escritor= new FileOutputStream(ruta);
         pescritor= new ObjectOutputStream(escritor);
        }catch (Exception e){
        e.printStackTrace();
        String mensaje=("Error al leer el archivo txt de opciones, este se borrara y se creara uno nuevo, se va a cerrar el programa, reinicielo para aplicar los cambios");
        errorFatal(mensaje);
        }
        String linea=null;
        try {
            Integer[] aux = ipp;
            pescritor.writeObject(aux);
              /* pescritor.println(ipp[0]+"."+ipp[1]+"."+ipp[2]+"."+ipp[3]);
               pescritor.println("[Port]9000");
               pescritor.println("[Others]");
               pescritor.close();         
                    
                } catch (Exception ex) {
            Logger.getLogger(ArchivoTXT.class.getName()).log(Level.SEVERE, null, ex);
            String mensaje=("Error al leer el archivo txt de opciones, este se borrara y se creara uno nuevo, se va a cerrar el programa, reinicielo para aplicar los cambios");
            errorFatal(mensaje);
        }
    }
*/
    
    public static void borrarKin(){
        try {
            pescritor.close();
        } catch (Exception ex) {
            Logger.getLogger(ModuloActualizacion.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            escritor.close();
        } catch (IOException ex) {
            Logger.getLogger(ModuloActualizacion.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            lector.close();
        } catch (IOException ex) {
            Logger.getLogger(ModuloActualizacion.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            blector.close();
        } catch (IOException ex) {
            Logger.getLogger(ModuloActualizacion.class.getName()).log(Level.SEVERE, null, ex);
        }
    txt.delete();
    }
    public static void errorFatal(String mensajeError){
    JOptionPane.showMessageDialog(null, "Error fatal."+System.getProperty("line.separator")+mensajeError,"Informacion para el usuario",JOptionPane.WARNING_MESSAGE);
    System.exit(-1);
    }
}
