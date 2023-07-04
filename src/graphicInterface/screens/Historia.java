package graphicInterface.screens;

import audioInterface.Audio;
import graficos.Spritsheet;
import main.Game;
import settings.GameSettings;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Historia {
    public int currentOption = 0;
    public boolean end;
    public static Spritsheet animaMenu;
    public static Spritsheet fundoMenu;
    public String menuAnimaPath = "/res/menus/animations/menuSprite1.png";
    public String fundoMenuPath = "/res/menus/images/testehistoria.png";
    public BufferedImage[] playerMenuAnima;
    public BufferedImage fundoMenuSimples;

    public static Audio audio = new Audio("/res/sounds/soundtracks/fase2.wav", true);

    public int frames = 0, maxFrames = 25, index = 0, maxIndex = 24;

    public Historia() {
        audio.stop();

        animaMenu = new Spritsheet(menuAnimaPath);
        fundoMenu = new Spritsheet(fundoMenuPath);
        playerMenuAnima = new BufferedImage[25];


        // animacao do personagem no menu
        for (int i = 0; i < 25; i++) {
            playerMenuAnima[i] = animaMenu.getSprite((i * 64), 0, 64, 128);
        }

        fundoMenuSimples = fundoMenu.getSprite(0, 0, 520, 292);
    }

    public void choose() {
        if (end) {
            end = false;
            if (currentOption == 0) {
                //inicia o jogo
                audio.stop();
                audio = new Audio("/res/sounds/soundtracks/fase"+ Game.level+".wav", true);
                Game.gameState = "NORMAL";
                audio.start();

            }
        }
    }

    public static void stopGameAudio(){
        audio.stop();
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

        g.fillRect(0, 0, GameSettings.getGAME_WIDTH() * GameSettings.getGAME_SCALE(), GameSettings.getGAME_HEIGHT() * GameSettings.getGAME_SCALE());
        g.setColor(new Color(255, 255, 255));

        g.drawImage(fundoMenuSimples, 0, 0, 520*GameSettings.getGAME_SCALE(),292*GameSettings.getGAME_SCALE(), null);

        g.drawImage(playerMenuAnima[index], 30+GameSettings.getGAME_SCALE(), 30+GameSettings.getGAME_SCALE(), 64 * (GameSettings.getGAME_SCALE()*2), 128 * (GameSettings.getGAME_SCALE()*2), null);

    }

}
