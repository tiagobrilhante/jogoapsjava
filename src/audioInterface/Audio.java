package audioInterface;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.net.URL;

public class Audio {
    private Clip clip;

    public Audio(String soundPath, boolean repeat) {
        if (soundPath != null) {
            try {
                URL url = getClass().getResource(soundPath);
                assert url != null;
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