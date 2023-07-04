package graphicInterface.screens;

import audioInterface.Audio;
import graficos.Spritsheet;
import main.Game;
import settings.GameSettings;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

public class GameMenu {
    public String[] options = {"Jogar", "Leaderboards", "Controles", "Sobre", "Sair"};

    public int currentOption = 0;
    public int maxOption = options.length - 1;
    public boolean up, down, ok;
    private BufferedImage imagem;
    public static Spritsheet animaMenu;
    public static Spritsheet fundoMenu;
    public String menuAnimaPath = "/res/menus/animations/menuSprite1.png";
    public String fundoMenuPath = "/res/menus/images/testemenu.png";

    public static String soundPath = "/res/sounds/soundtracks/fase0.wav";

    public BufferedImage[] playerMenuAnima;
    public BufferedImage fundoMenuSimples;

    public static Audio audio = new Audio(soundPath, true); // Chamando a classe aonde está o audio.

    public int frames = 0, maxFrames = 25, index = 0, maxIndex = 24;

    public GameMenu() {

        animaMenu = new Spritsheet(menuAnimaPath);
        fundoMenu = new Spritsheet(fundoMenuPath);
        playerMenuAnima = new BufferedImage[25];

        audio.start();

        // animacao do personagem no menu
        for (int i = 0; i < 25; i++) {
            playerMenuAnima[i] = animaMenu.getSprite((i * 64), 0, 64, 128);
        }

        fundoMenuSimples = fundoMenu.getSprite(0, 0, 520, 292);

        try {
            ClassLoader classLoader = getClass().getClassLoader();
            InputStream inputStream = classLoader.getResourceAsStream("res/ui/spaceship1small.png");
            assert inputStream != null;
            imagem = ImageIO.read(inputStream);
            // agora você pode usar a variável 'imagem' para manipular a imagem PNG

        } catch (IOException e) {
            System.out.println("Erro ao carregar a imagem: " + e.getMessage());
        }
    }

    public static void iniciaAudioMenu() {
        // Audio audio = new Audio(soundPath); // Chamando a classe aonde está o audio
        audio.start();

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
                audio.stop();
                Game.gameState = "HISTORIA";

            }
            if (currentOption == 1) {
                Game.gameState = "LEADERBOARDS";
            }

            if (currentOption == 2) {
                //mostra os controles
                Game.gameState = "CONTROLES";
            }
            if (currentOption == 3) {
                //mostra a aba sobre
                Game.gameState = "SOBRE";
            }
            if (currentOption == 4) {
                //fecha o jogo
                System.exit(0);
            }
        }
    }

    public void tick() {
        frames++;
        if (frames >= maxFrames / 3) {
            index++;
            frames = 0;
            if (index > maxIndex) {
                index = 0;
            }
        }

    }

    public void render(Graphics g) {

        g.fillRect(0, 0, GameSettings.getGAME_WIDTH() * GameSettings.getGAME_SCALE(), GameSettings.getGAME_HEIGHT() * GameSettings.getGAME_SCALE());
        g.setColor(new Color(255, 255, 255));

        g.drawImage(fundoMenuSimples, 0, 0, 520 * GameSettings.getGAME_SCALE(), 292 * GameSettings.getGAME_SCALE(), null);


        if (currentOption == 0) g.drawImage(imagem, 300*GameSettings.getGAME_SCALE(), 156*GameSettings.getGAME_SCALE(), 71 * GameSettings.getGAME_SCALE(), 78 * GameSettings.getGAME_SCALE(),null);
        if (currentOption == 1) g.drawImage(imagem, 300*GameSettings.getGAME_SCALE(), 173*GameSettings.getGAME_SCALE(), 71 * GameSettings.getGAME_SCALE(), 78 * GameSettings.getGAME_SCALE(),null);
        if (currentOption == 2) g.drawImage(imagem, 300*GameSettings.getGAME_SCALE(), 190*GameSettings.getGAME_SCALE(), 71 * GameSettings.getGAME_SCALE(), 78 * GameSettings.getGAME_SCALE(),null);
        if (currentOption == 3) g.drawImage(imagem, 300*GameSettings.getGAME_SCALE(), 207*GameSettings.getGAME_SCALE(), 71 * GameSettings.getGAME_SCALE(), 78 * GameSettings.getGAME_SCALE(),null);
        if (currentOption == 4) g.drawImage(imagem, 300*GameSettings.getGAME_SCALE(), 224*GameSettings.getGAME_SCALE(), 71 * GameSettings.getGAME_SCALE(), 78 * GameSettings.getGAME_SCALE(),null);

        g.drawImage(playerMenuAnima[index], 30+GameSettings.getGAME_SCALE(), 30+GameSettings.getGAME_SCALE(), 64 * (GameSettings.getGAME_SCALE()*2), 128 * (GameSettings.getGAME_SCALE()*2), null);

    }

}
