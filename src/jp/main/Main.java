package jp.main;

//Ejemplo de ventanas. Cajas de texto, etiquetas
import java.io.File;
import jp.auxiliar.Modos;

//y botones en java. 
public class Main extends EntradaDatos {

    private static Reproductor rep;
    private static boolean stop = false;
    private static String archivo = null;
    private static String aleatorio = "" + Math.random() / 99;

    public static int inicio(String recepcion, Modos avanceAutomatico) {

        String arch = recepcion;
        File file = new File(recepcion);

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

    public static void otraVez(String arch) {
        stop();
        inicio(arch, Modos.AVANCEN);
    }
    
        
        /*if(args == null){}
        else{
            System.out.println(new String(args.toString()));
        for(int i=0;i<args.length;i++){
            if(i==0){
                argumentos=args[i];
            }else{
                argumentos=argumentos+" "+args[i];
            }
        }
        argumentos.replace("@", " ");
        System.out.println(argumentos);
            
        }*/
}
