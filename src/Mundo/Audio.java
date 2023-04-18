package Mundo;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;

public class Audio {
    private Clip clip;

    public Audio(String soundPath) {
        try {
            // Local absoluto de onde tá o arquivo
            // funciona com WAV - ainda não testei mp3
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(soundPath).getAbsoluteFile());
            clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            // Repete a música continuamente
            clip.loop(Clip.LOOP_CONTINUOUSLY);
        } catch (Exception ex) {
            // exception no console
            System.out.println("Erro ao executar SOM!");
            ex.printStackTrace();
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