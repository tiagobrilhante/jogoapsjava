package Mundo;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;
import java.io.InputStream;
import java.net.URL;
import java.util.Arrays;

public class Audio {
    private Clip clip;

   /* public Audio(String soundPath , boolean repeat) {
        if (soundPath != null) {
            File file = new File(soundPath);
            if (file.exists()) {
                try {
                    AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(file.getAbsoluteFile());
                    clip = AudioSystem.getClip();
                    clip.open(audioInputStream);
                    if (repeat) {
                        clip.loop(Clip.LOOP_CONTINUOUSLY);
                    }
                } catch (Exception ex) {
                    System.out.println("Erro ao executar SOM!");
                    ex.printStackTrace();
                }
            }
        }
    }
*/
   public Audio(String soundPath, boolean repeat) {
       if (soundPath != null) {
           try {
               URL url = getClass().getResource(soundPath);
               AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(url);
               clip = AudioSystem.getClip();
               clip.open(audioInputStream);
               if (repeat) {
                   clip.loop(Clip.LOOP_CONTINUOUSLY);
               }
           } catch (Exception ex) {
               System.out.println("Erro ao executar SOM!");
               ex.printStackTrace();
           }
       }
   }




    public void start() {
        if (clip != null) {
            clip.start();
        }
    }

    public void stop() {
        if (clip != null) {
            clip.stop();
        }
    }
}