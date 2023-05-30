package graphicInterface.screens;

import graficos.Spritsheet;
import main.Game;
import settings.GameSettings;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;


public class LeaderBoard {
    public int currentOption = 0;
    public boolean end;
    public static Spritsheet animaMenu;
    public static Spritsheet fundoMenu;
    public String menuAnimaPath = "/res/menus/animations/menuSprite1.png";
    public String fundoMenuPath = "/res/menus/images/testeleaderboards.png";
    public BufferedImage[] playerMenuAnima;
    public BufferedImage[] fundoMenuSimples;

    public int frames = 0, maxFrames = 25, index = 0, maxIndex = 24;

    public LeaderBoard() {

        animaMenu = new Spritsheet(menuAnimaPath);
        fundoMenu = new Spritsheet(fundoMenuPath);
        playerMenuAnima = new BufferedImage[25];
        fundoMenuSimples = new BufferedImage[1];

        // animacao do personagem no menu
        for (int i = 0; i < 25; i++) {
            playerMenuAnima[i] = animaMenu.getSprite((i * 64), 0, 64, 128);
        }

        fundoMenuSimples[0] = fundoMenu.getSprite(0, 0, 520, 292);

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


        g.drawImage(fundoMenuSimples[0], 0, 0, 520 * 2, 292 * 2, null);

        g.drawImage(playerMenuAnima[index], 30, 30, 64 * 4, 128 * 4, null);

        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.BOLD, 30));
        g.drawString( "Maior pontuação: ", GameSettings.getGAME_WIDTH() / 2 + 160, GameSettings.getGAME_HEIGHT() / 2 + 50);
        try {
            File arquivo = new File("src/score/leaderboard.txt");
            Scanner scanner = new Scanner(arquivo);

            int saltoLinha = 100;
            while (scanner.hasNextLine()) {
                String linha = scanner.nextLine(); // lê a linha do arquivo
                g.drawString( linha, GameSettings.getGAME_WIDTH() / 2 + 160, GameSettings.getGAME_HEIGHT() / 2 + saltoLinha);
                saltoLinha+=40;

            }
        } catch (FileNotFoundException e) {
            System.out.println("Arquivo não encontrado!");
        }


    }

}
