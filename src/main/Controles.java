package main;

import graficos.Spritsheet;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Controles {
    public int currentOption = 0;
    public boolean end;
    private final Font font;
    public static Spritsheet animaMenu;
    public static Spritsheet fundoMenu;
    public String menuAnimaPath = "/res/spritesheets/menuSprite1.png";
    public String fundoMenuPath = "/res/spritesheets/testecontrole.png";
    public BufferedImage[] playerMenuAnima;
    public BufferedImage[] fundoMenuSimples;

    public int frames = 0, maxFrames = 25, index = 0, maxIndex = 24;

    public Controles() {

        animaMenu = new Spritsheet(menuAnimaPath);
        fundoMenu = new Spritsheet(fundoMenuPath);
        playerMenuAnima = new BufferedImage[25];
        fundoMenuSimples = new BufferedImage[1];

        // animacao do personagem no menu
        for (int i = 0; i < 25; i++) {
            playerMenuAnima[i] = animaMenu.getSprite((i * 64), 0, 64, 128);
        }

        fundoMenuSimples[0] = fundoMenu.getSprite(0,0, 520,292);
        font = new Font("Arial", Font.BOLD, 24);

    }

    public void choose() {
        if (end) {
            end = false;
            if (currentOption == 0) {
                //volta pro menu
                Game.gameState = "MENU";

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

        // define a fonte original para os demais textos
        g.setFont(font);
        g.drawString("Aperte ENTER para voltar", 600, 510);

        g.drawImage(playerMenuAnima[index], 30, 30, 64*4,128*4, null);

    }

}
