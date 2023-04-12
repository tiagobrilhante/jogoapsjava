package main;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

public class Menu {
    public String[] options = {"Jogar", "Leaderboards", "Sair"};
    public int currentOption = 0;
    public int maxOption = options.length - 1;
    public boolean up,down,ok;
    private BufferedImage imagem;
    private Font font;

    public Menu() {try {
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
        if(down) {
            currentOption++;
            down = false;
            if(currentOption > maxOption) {
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
        if(ok) {
            ok = false;
            if(currentOption == 0) {
                //inicia o jogo
                Game.gameState = "NORMAL";
            }
            if(currentOption == 1) {
                //leaderboards (falta implementar)
            }
            if(currentOption == 2) {
                //fecha o jogo
                System.exit(0);
            }
        }
    }

    public void render(Graphics g) {
        g.fillRect(0, 0, Game.WIDTH*Game.SCALE, Game.HEIGTH*Game.SCALE);
        g.setColor(new Color(255, 255,255));

        // define a nova fonte
        Font biggerFont = font.deriveFont(60f);
        g.setFont(biggerFont);
        g.drawString("GameAps", ((Game.WIDTH*Game.SCALE)/2) - 140, 280);

        // define a fonte original para os demais textos
        g.setFont(font);
        g.drawString("JOGAR", ((Game.WIDTH*Game.SCALE) / 2) - 35, 380);
        g.drawString("LEADERBOARDS", ((Game.WIDTH*Game.SCALE) / 2) - 90, 420);
        g.drawString("SAIR", ((Game.WIDTH*Game.SCALE) / 2) - 23, 460);

        if(currentOption == 0)g.drawImage(imagem, ((Game.WIDTH*Game.SCALE) / 2) + 55, 360, null);
        if(currentOption == 1)g.drawImage(imagem, ((Game.WIDTH*Game.SCALE) / 2) + 117, 400, null);
        if(currentOption == 2)g.drawImage(imagem, ((Game.WIDTH*Game.SCALE) / 2) + 40, 440, null);
    }
}
