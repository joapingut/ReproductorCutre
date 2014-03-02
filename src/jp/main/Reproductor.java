/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jp.main;

import java.io.File;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javazoom.jlgui.basicplayer.BasicController;
import javazoom.jlgui.basicplayer.BasicPlayer;
import javazoom.jlgui.basicplayer.BasicPlayerEvent;
import javazoom.jlgui.basicplayer.BasicPlayerException;
import javazoom.jlgui.basicplayer.BasicPlayerListener;
import jp.auxiliar.Modos;
import jp.auxiliar.ModuloAntiErrores;
import jp.interfaz.Principal;
import jp.interfaz.errores.ErrorInicio;
import jp.interfaz.errores.ReproductorError;

/**
 *
 * @author usuario
 */
public class Reproductor implements BasicPlayerListener {

    private double bytesLength;
    private BasicPlayer basicPlayer;
    private ErrorInicio FU;
    public static Integer comprobar = 0;
    private String Archivo;

    public Reproductor(String ruta, Modos capturar) throws ReproductorError {
        this.Archivo = ruta;
        if (basicPlayer == null) {
            basicPlayer = new BasicPlayer();
            File fil = new File(ruta);
            try {
                bytesLength = fil.length();
                basicPlayer.open(fil);
                ModuloAntiErrores.apuntarAcierto(fil.getName().substring(fil.getName().lastIndexOf("."), fil.getName().length()));
            } catch (BasicPlayerException ex) {
                if (capturar == Modos.AVANCEN) {
                    if (FU == null) {
                        FU = new ErrorInicio(new javax.swing.JFrame(), true, "Error al abrir: \n" + ex.getMessage());
                        comprobar = 1;
                    }
                    FU.setVisible(true);
                    ManejadorReproductor.stop();
                } else {
                    ManejadorReproductor.stop();
                    basicPlayer.addBasicPlayerListener(this);
                    comprobar = 0;
                    throw new ReproductorError("Erorr en avance automatico");
                }
                ModuloAntiErrores.apuntarFallo(fil.getName().substring(fil.getName().lastIndexOf("."), fil.getName().length()));
            }
        }
        basicPlayer.addBasicPlayerListener(this);
        comprobar = 0;
    }

    public String getruta() {
        return Archivo;
    }

    public boolean equals(Reproductor a) {
        boolean result;
        result = a.getruta().equals(this.getruta());
        if (result == false) {
            a.stop();
        }
        return result;
    }

    public void play() {
        try {
            basicPlayer.play();
        } catch (BasicPlayerException e) {
            // TODO Auto-generated catch block  e.printStackTrace();
        }
    }

    public void pause() {
        try {
            basicPlayer.pause();
        } catch (BasicPlayerException e) {
            // TODO Auto-generated catch block  e.printStackTrace();
        }
    }

    public void stop() {
        try {
            basicPlayer.stop();
        } catch (BasicPlayerException e) {
            // TODO Auto-generated catch block  e.printStackTrace();
        }
    }

    public void resume() {
        try {
            basicPlayer.resume();
        } catch (BasicPlayerException e) {
            // TODO Auto-generated catch block  e.printStackTrace();
        }
    }
    
    public void seek(int sek){
        try {
            basicPlayer.seek((long)sek);
        } catch (BasicPlayerException ex) {
            Logger.getLogger(Reproductor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void volumen(int vol){
        double ganancia = vol/100.0;
        try {
            System.out.println("VOL:"+ganancia);
            basicPlayer.setGain(ganancia);
        } catch (BasicPlayerException ex) {
            Logger.getLogger(Reproductor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * * Necesario por implementar BasicPlayerListener. Es ejecutado una vez se
     * carga un fichero. En este caso, obtiene el tamaño en bytes del fichero.
     */
    public void opened(Object arg0, Map arg1) {
        System.out.println("HOLA MARIOLA: " + arg1.toString());
        if (arg1.containsKey("audio.length.bytes")) {
            bytesLength = Double.parseDouble(arg1.get("audio.length.bytes").toString());
        }
    }
    /**
     * * Necesario por implementar BasicPlayerListener. Según la documentación,
     * este método es llamado varias veces por segundo para informar del
     * progreso en la reproducción.
     */
    private long dt = 0;
    private long tv = 0;
    private long tn = 0;

    public void progress(int bytesread, long microseconds, byte[] pcmdata, Map properties) {
        tn = System.currentTimeMillis();
        dt = tn - tv;
        if (dt > 420 || (bytesLength-bytesread<1000)) {
            float progressUpdate = (float) (bytesread * 1.0f / bytesLength * 1.0f);
            int progressNow = (int) (bytesLength * progressUpdate);
            // Descomentando la siguiente línea se mosrtaría el progreso
            System.out.println(" -&CPC; " + properties.toString());
            System.out.println(" -&Progreso; " + progressNow);
            System.out.println(" -&Duracion; " + properties.get("mp3.frame.bitrate"));
            System.out.println(" -&Total; " + (int) bytesLength);
            System.out.println(" -&Microsegundo; " + properties.get("mp3.position.microseconds"));
            Principal.rellenar(progressNow, (int) bytesLength, new Long(properties.get("mp3.position.microseconds").toString()));
            tv = tn;
        }
    }

    public void stateUpdated(BasicPlayerEvent bpe) {
        System.out.println(bpe.toString());
    }

    public void setController(BasicController bc) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
