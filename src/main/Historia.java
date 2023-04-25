package main;

import Mundo.Audio;
import graficos.Spritsheet;
import java.awt.*;
import java.awt.image.BufferedImage;

public class Historia {
    public int currentOption = 0;
    public boolean end;
    private final Font font;
    public static Spritsheet animaMenu;
    public static Spritsheet fundoMenu;
    public String menuAnimaPath = "/res/spritesheets/menuSprite1.png";
    public String fundoMenuPath = "/res/spritesheets/testehistoria.png";
    public BufferedImage[] playerMenuAnima;
    public BufferedImage[] fundoMenuSimples;

    public int frames = 0, maxFrames = 25, index = 0, maxIndex = 24;

    public Historia() {

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
                //inicia o jogo
                Game.gameState = "NORMAL";
                Audio audio = new Audio("src/res/sounds/soundtracks/fase"+Game.level+".wav");
                audio.start();

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
        Font biggerFont = font.deriveFont(20f);
        g.setFont(biggerFont);
        g.drawString("O mundo está devastado.", 380, 90);
        g.drawString("A falta de cuidado com o meio ambiente gerou danos", 380, 115);
        g.drawString("graves ao planeta.", 380, 140);
        g.drawString("Houveram muitas guerras por causa dos recursos que.", 380, 165);
        g.drawString("sobraram.", 380, 190);
        g.drawString("Boa parte da população pereceu.", 380, 215);
        g.drawString("Você é um dos sobreviventes, e sua missão é coletar", 380, 240);
        g.drawString("o lixo existente no planeta.", 380, 265);

        // define a fonte original para os demais textos
        g.setFont(font);
        g.drawString("Aperte ENTER para iniciar", 600, 510);

        g.drawImage(playerMenuAnima[index], 30, 30, 64*4,128*4, null);

    }

}
