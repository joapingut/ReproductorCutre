package jp.main;

//Ejemplo de ventanas. Cajas de texto, etiquetas
import java.io.FileNotFoundException;
import jp.auxiliar.Modos;

//y botones en java. 
public class ManejadorReproductor extends EntradaDatos {

    private static Reproductor rep;
    private static boolean stop = false;
    private static String archivo = null;
    private static String aleatorio = "" + Math.random() / 99;

    public static int inicio(String recepcion, Modos avanceAutomatico){

        String arch = recepcion;
        archivo = arch;

        if (rep != null && stop == false) {
            stop = false;
            if (rep.getruta().equals(archivo) && Reproductor.comprobar == 0) {
            } else {
                rep.stop();
                rep = new Reproductor(archivo, avanceAutomatico);
                if (Reproductor.comprobar == 0) {
                    rep.play();
                } else {
                    rep = new Reproductor(aleatorio, avanceAutomatico);
                    Reproductor.comprobar = 0;
                }
            }
        } else {
            stop = false;
            rep = new Reproductor(archivo, avanceAutomatico);
            if (Reproductor.comprobar == 0) {
                rep.play();
            } else {
                rep = new Reproductor(aleatorio, avanceAutomatico);
                Reproductor.comprobar = 0;
            }
        }

        return 0;
    }

    public static void parar() {
        rep.stop();
        stop = true;

    }

    public static void pausa() {
        rep.pause();
    }

    public static void stop() {
        rep.stop();
        stop = true;

    }

    public static void reiniciar() {
        rep.resume();
    }
    
    public static void seek(int sek){
        rep.seek(sek);
    }
    
    public static void volumen(int vol){
        rep.volumen(vol);
    }

    public static void otraVez(String arch) {
        stop();
        inicio(arch, Modos.AVANCEN);
    }
}