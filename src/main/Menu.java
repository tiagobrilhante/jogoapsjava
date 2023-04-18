package main;

import graficos.Spritsheet;

import javax.imageio.ImageIO;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

public class Menu {
    public String[] options = {"Jogar", "Leaderboards", "Sair"};

    public static String gameState = "MENU";
    
    public int currentOption = 0;
    public int maxOption = options.length - 1;
    public boolean up, down, ok;
    private BufferedImage imagem;
    private Font font;

    public static Spritsheet animaMenu;
    public static Spritsheet fundoMenu;
    public String menuAnimaPath = "/res/spritesheets/menuSprite1.png";
    public String fundoMenuPath = "/res/spritesheets/testemenu.png";

    public static String soundPath = "src/res/sounds/soundtracks/fase0.wav";

    public BufferedImage[] playerMenuAnima;
    public BufferedImage[] fundoMenuSimples;

    AudioMundo audio = new AudioMundo(soundPath); // Chamando a classe aonde está o audio.

    public int frames = 0, maxFrames = 25, index = 0, maxIndex = 24;

    public Menu() {

        System.out.println(Game.gameState);

        animaMenu = new Spritsheet(menuAnimaPath);
        fundoMenu = new Spritsheet(fundoMenuPath);
        playerMenuAnima = new BufferedImage[25];
        fundoMenuSimples = new BufferedImage[1];
       audio.start();

        // animacao do personagem no menu
        for (int i = 0; i < 25; i++) {
            playerMenuAnima[i] = animaMenu.getSprite((i * 64), 0, 64, 128);
        }

        fundoMenuSimples[0] = fundoMenu.getSprite(0,0, 520,292);

        try {
            ClassLoader classLoader = getClass().getClassLoader();
            InputStream inputStream = classLoader.getResourceAsStream("res/spaceship1small.png");
            imagem = ImageIO.read(inputStream);
            // agora você pode usar a variável 'imagem' para manipular a imagem PNG

            font = new Font("Arial", Font.BOLD, 24);
        } catch (IOException e) {
            System.out.println("Erro ao carregar a imagem: " + e.getMessage());
        }
    }

    public void choose() {
        if (down) {
            currentOption++;
            down = false;
            if (currentOption > maxOption) {
                currentOption = 0;
            }
        }
        if (up) {
            currentOption--;
            up = false;
            if (currentOption < 0) {
                currentOption = maxOption;
            }
        }
        if (ok) {
            ok = false;
            if (currentOption == 0) {
                //inicia o jogo
                Game.gameState = "NORMAL";
                audio.stop();
                AudioMundo audio = new AudioMundo("src/res/sounds/soundtracks/fase"+Game.level+".wav");
                audio.start();

            }
            if (currentOption == 1) {
                //leaderboards (falta implementar)
            }
            if (currentOption == 2) {
                //fecha o jogo
                System.exit(0);
            }
        }
    }

    public void tick() {

        System.out.println("tick: "+ Game.gameState);
        frames++;
        if (frames >= maxFrames/3) {
            index++;
            frames = 0;
            if (index > maxIndex) {
                index = 0;
            }
        }

    }

    public void render(Graphics g) {

        g.fillRect(0, 0, Game.WIDTH * Game.SCALE, Game.HEIGTH * Game.SCALE);
        g.setColor(new Color(255, 255, 255));

        g.drawImage(fundoMenuSimples[0], 0, 0, 520*2,292*2, null);

        // define a nova fonte
        Font biggerFont = font.deriveFont(60f);
        g.setFont(biggerFont);
        g.drawString("GameAps", (Game.WIDTH * Game.SCALE)  - 650, 100);

        // define a fonte original para os demais textos
        g.setFont(font);
        g.drawString("JOGAR", 650, 380);
        g.drawString("LEADERBOARDS", 650, 420);
        g.drawString("SAIR", 650, 460);

        if (currentOption == 0) g.drawImage(imagem, 620, 360, null);
        if (currentOption == 1) g.drawImage(imagem, 620, 400, null);
        if (currentOption == 2) g.drawImage(imagem, 620, 440, null);

        g.drawImage(playerMenuAnima[index], 30, 30, 64*4,128*4, null);

    }



    public class AudioMundo {
        private Clip clip;

        public AudioMundo(String soundPath) {
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

}
