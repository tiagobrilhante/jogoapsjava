package graphicInterface.uI;

import entidades.player.Player;
import main.Game;
import settings.GameSettings;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;

public class UserInterface {
    private BufferedImage imagemArma;
    private final int uiWidth = GameSettings.getGAME_WIDTH();
    private final int uiHeigth = 40;
    private final int uiYBase = GameSettings.getGAME_HEIGHT() - uiHeigth - 10;
    private final int linhaEspecial = uiYBase + 4;
    private final int linha1 = uiYBase + 16;
    private final int linha2 = uiYBase + 28;

    public void render(Graphics g) {

        int separador = 120;
        int baseColunas = 60;
        int coluna1 = 10;
        int coluna3 = baseColunas + separador;
        int coluna4 = coluna3 + separador;
        int coluna5 = coluna4 + separador;

        // fundo base da UI
        g.setColor(Color.BLACK);
        g.fillRect(0, uiYBase, uiWidth, uiHeigth);
        // fim do fundo base

        // sobreposição de fundo da ui
        g.setColor(Color.GRAY);
        g.fillRect(2, uiYBase + 2, uiWidth - 4, uiHeigth - 8);
        // fim da sobreposição de fundo

        // Barra de energia do Player
        g.setColor(Color.BLACK);
        g.fillRect(baseColunas, linha1-8, 52, 9);

        g.setColor(Color.RED);
        g.fillRect(baseColunas + 1, linha1 -7, 50, 7);

        g.setColor(Color.GREEN);
        g.fillRect(baseColunas + 1, linha1 -7, (int) ((Player.life / Player.maxLife) * 50), 7);

        g.setFont(new Font("Arial", Font.BOLD, 7));
        g.setColor(Color.BLACK);
        g.drawString("Energia", baseColunas +5, linha1-1);
        // fim da barra de enrgia

        // ajuste de fonte
        g.setFont(new Font("Arial", Font.BOLD, 10));
        g.setColor(Color.BLACK);

        //Munição
        g.drawString("Municao:  " + Player.qtdTiro , baseColunas, linha2);
        // tempo
        g.drawString("Tempo:  " + Game.timer / 60, coluna3, linha2 );
        // vidas
        g.drawString("Vidas:  " + Player.tentativas, coluna3, linha1);
        // level
        g.drawString("Level:  " + Game.level, coluna4, linha1);
        // lixos restantes
        g.drawString("Lixos Restantes:  " + Game.trashBags.size(), coluna4, linha2);
        // pontos
        g.drawString("Pontos:  " + Player.pontos, coluna5, linha1);

        String pathArma;

        // Arma Selecionada
        if (Objects.equals(Player.selectedWeapon, "Cano")) {
            pathArma = "res/spritesheets/canoUi.png";
            // cor para o cano
            g.setColor(Color.BLACK);
        } else {
            pathArma = "res/spritesheets/armaUi.png";
            // cor para a arma de fogo
            g.setColor(Color.YELLOW);
        }

        try {
            ClassLoader classLoader = getClass().getClassLoader();
            InputStream inputStream = classLoader.getResourceAsStream(pathArma);
            assert inputStream != null;
            imagemArma = ImageIO.read(inputStream);
        } catch (IOException e) {
            System.out.println("Erro ao carregar a imagem: " + e.getMessage());
        }

        g.fillRect(coluna1, linhaEspecial, 38, 28);
        g.drawImage(imagemArma, coluna1, linhaEspecial, null);
    }

}
