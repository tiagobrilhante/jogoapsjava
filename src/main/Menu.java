package main;

import graficos.Spritsheet;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import Mundo.Audio;

public class Menu {
    public String[] options = {"Jogar", "Leaderboards", "Controles", "Sair"};

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

    Audio audio = new Audio(soundPath); // Chamando a classe aonde está o audio.

    public int frames = 0, maxFrames = 25, index = 0, maxIndex = 24;

    public Menu() {

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
            assert inputStream != null;
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
                audio.stop();
                Game.gameState = "HISTORIA";

            }
            if (currentOption == 1) {
                //leaderboards (falta implementar)
            }

            if (currentOption == 2) {
                //mostra os controles
                Game.gameState = "CONTROLES";
            }
            if (currentOption == 3) {
                //fecha o jogo
                System.exit(0);
            }
        }
    }

    public void tick() {
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
        g.drawString(Game.gameName, (Game.WIDTH * Game.SCALE)  - 650, 100);

        // define a fonte original para os demais textos
        g.setFont(font);
        g.drawString("JOGAR", 650, 350);
        g.drawString("LEADERBOARDS", 650, 390);
        g.drawString("CONTROLES", 650, 430);
        g.drawString("SAIR", 650, 470);

        if (currentOption == 0) g.drawImage(imagem, 620, 330, null);
        if (currentOption == 1) g.drawImage(imagem, 620, 370, null);
        if (currentOption == 2) g.drawImage(imagem, 620, 410, null);
        if (currentOption == 3) g.drawImage(imagem, 620, 450, null);

        g.drawImage(playerMenuAnima[index], 30, 30, 64*4,128*4, null);

    }

}
