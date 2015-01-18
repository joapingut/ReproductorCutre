package jp.auxiliar;

import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

/**
 *
 * @author Joaquín
 */
public class OrdenacionDeDirectorios {

    private static int actual;
    private static int tamDir = -1;
    private static File[] ficheros;
    private static String directorio;
    private static Random randoni;
    private static Set<Integer> usados;

    public static void crear(String ruta, String nombre) {
        int vervose = -1;
        File directori = new File(ruta);
        List<File> aux = new ArrayList<File>();
        if (directori.isDirectory()) {
            directorio = ruta;
            for (File fichero : directori.listFiles()) {
                if (fichero.isDirectory()) {
                    continue;
                }
                vervose = vervose + 1;
                if (fichero.getName().equals(nombre)) {
                    actual = vervose;
                }
                aux.add(fichero);
            }
            tamDir = directori.listFiles().length;
            System.out.println(aux);
            ficheros = new File[aux.size()];
            for (int i = 0; i < aux.size(); i++) {
                ficheros[i] = aux.get(i);
            }
            randoni = new Random();
            usados = new HashSet<Integer>();
            if (nombre == null) {
                actual = randoni.nextInt();
            }
            System.out.println(vervose);
        } else {
            System.out.println("FALLO: desactivado directorios");
        }
    }

    public static String siguiente() {
        refrescar(false);
        int siguiente = actual + 1;
        if (siguiente >= ficheros.length) {
            siguiente = 0;
        }
        actual = siguiente;
        return ficheros[siguiente].getAbsolutePath();
    }

    public static String anterior() {
        refrescar(false);
        int siguiente = actual - 1;
        if (siguiente < 0) {
            siguiente = ficheros.length - 1;
        }
        actual = siguiente;
        return ficheros[siguiente].getAbsolutePath();
    }

    public static String aleatorio() {
        refrescar(false);
        int ran = actual;
        System.out.println("CMP: " + usados.size() + ":" + ficheros.length);
        if (usados.size() >= ficheros.length) {
            usados.clear();
            System.out.println("CLEAR: " + usados.size() + ":" + ficheros.length);
        }
        while (ran == actual || usados.contains(ran)) {
            ran = randoni.nextInt(ficheros.length);
        }
        usados.add(ran);
        actual = ran;
        System.out.println(usados);
        return ficheros[ran].getAbsolutePath();
    }

    public static String getDirectorioActual() {
        return directorio;
    }

    public static void refrescar(boolean forzar) {
        File directori = new File(directorio);
        System.out.println("CMP: " + tamDir + ":" + directori.listFiles().length);
        if (forzar || directori.listFiles().length != tamDir) {
            crear(directorio, ficheros[actual].getName());
        }
    }

    public static String cadenaInfo() {
        String res;
        int mal = 30;
        int oxi = actual - 1;
        if (oxi < 0) {
            oxi = ficheros.length - 1;
        }
        String aux = ficheros[oxi].getName();
        if (aux.length() < 29) {
            mal = aux.length();
            res = aux.substring(0, mal) + "    || ";
        } else {
            res = aux.substring(0, mal) + "... || ";
        }
        oxi = actual + 1;
        if (oxi >= ficheros.length) {
            oxi = 0;
        }
        aux = ficheros[oxi].getName();
        if (aux.length() < 29) {
            mal = aux.length();
            res = res + aux.substring(0, mal);
        } else {
            mal = 30;
            aux = ficheros[actual + 1].getName();
            res = res + aux.substring(0, mal) + "...";
        }
        return res;
    }
}
