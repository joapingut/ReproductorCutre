/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jp.auxiliar;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URISyntaxException;
import java.security.CodeSource;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Joaquin
 */
public class ModuloPropiedades {

    private static String rutaConfig;
    public static String separador;
    public static String rutaPorDefecto;
    public static int dimensionCaratula;
    public static boolean autoPlay;
    public static boolean autoAvance;
    public static boolean autoAleatorio;

    public static void cargar() {
        separador = File.separatorChar + "";
        CodeSource codeSource = ModuloPropiedades.class.getProtectionDomain().getCodeSource();
        File jarFile = null;
        try {
            jarFile = new File(codeSource.getLocation().toURI().getPath());
        } catch (URISyntaxException ex) {
            Logger.getLogger(ModuloPropiedades.class.getName()).log(Level.SEVERE, null, ex);
        }
        File jarDir = jarFile.getParentFile();
        //String loca = (new File(".")).getAbsolutePath();
        rutaConfig = jarDir+separador+"lib" + separador + "properties.cfg";
        System.out.println(rutaConfig);
        if (!leerPropiedades()) {
            defaults();
        }
        guardarPropiedades();
    }

    public static boolean leerPropiedades() {
        Properties propiedades = new Properties();
        try {
            File file = new File(rutaConfig);
            if(!file.exists()){
                return false;
            }
            propiedades.load(new FileInputStream(file));
            autoPlay = Boolean.parseBoolean(propiedades.getProperty("autoPlay"));
            autoAvance = Boolean.parseBoolean(propiedades.getProperty("autoAvance"));
            autoAleatorio = Boolean.parseBoolean(propiedades.getProperty("autoAleatorio"));
            dimensionCaratula = Integer.parseInt(propiedades.getProperty("dimensionCaratula"));
            rutaPorDefecto = propiedades.getProperty("rutaPorDefecto");
            if(rutaPorDefecto.equals("") || rutaPorDefecto.equals("null")){
                rutaPorDefecto = null;
            }
            return true;
        } catch (IOException ex) {
            Logger.getLogger(ModuloPropiedades.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public static void defaults() {
        rutaPorDefecto = null;
        dimensionCaratula = 200;
        autoPlay = false;
        autoAvance = false;
        autoAleatorio = false;
    }

    public static boolean guardarPropiedades() {
        Properties propiedades = new Properties();
        propiedades.setProperty("autoPlay", autoPlay+"");
        propiedades.setProperty("autoAvance", autoAvance+"");
        propiedades.setProperty("autoAleatorio", autoAleatorio+"");
        propiedades.setProperty("dimensionCaratula", dimensionCaratula+"");
        propiedades.setProperty("rutaPorDefecto", rutaPorDefecto+"");
        try {
            File file = new File(rutaConfig);
            if(!file.exists()){
                file.createNewFile();
            }
            propiedades.store(new FileOutputStream(file), "Archivo de propiedades del reproductor cutre");
            return true;
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ModuloPropiedades.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } catch (IOException ex) {
            Logger.getLogger(ModuloPropiedades.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
}
