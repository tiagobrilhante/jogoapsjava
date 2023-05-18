package main;

import entidades.player.Player;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

public class UserInterface {

    private BufferedImage imagemArma;



    // por enquanto esse método só renderiza a barra de vida do player
    // deve evoluir para uma interface melhorada
    public void render(Graphics g) {



        g.setColor(Color.GRAY);
        g.fillRect(0, (Game.HEIGTH) - (Game.HEIGTH - 258), Game.WIDTH, Game.HEIGTH - (Game.HEIGTH - 150));


        g.setColor(Color.BLACK);
        g.fillRect(59, (Game.HEIGTH) - (Game.HEIGTH - 265), 52, 9);

        g.setColor(Color.RED);
        g.fillRect(60, (Game.HEIGTH) - (Game.HEIGTH - 266), 50, 7);


        g.setColor(Color.GREEN);

        g.fillRect(60, (Game.HEIGTH) - (Game.HEIGTH - 266), (int) ((Player.life / Player.maxLife) * 50), 7);

        g.setFont(new Font("Arial", Font.BOLD, 7));
        g.setColor(Color.BLACK);
        g.drawString("Energia", 65, (Game.HEIGTH) - (Game.HEIGTH - 266) + 6);

        g.setFont(new Font("Arial", Font.BOLD, 10));
        g.setColor(Color.BLACK);
        g.drawString("Municao:  " + Player.qtdTiro, 60, (Game.HEIGTH) - (Game.HEIGTH - 266) + 20);

        g.drawString("Vidas:  " + Player.tentativas, 150, (Game.HEIGTH) - (Game.HEIGTH - 266) + 6);

        g.drawString("Tempo:  " + Game.timer / 60, 150, (Game.HEIGTH) - (Game.HEIGTH - 266) + 20);
        g.drawString("Lixos Restantes:  " + Game.trashBags.size(), 240, (Game.HEIGTH) - (Game.HEIGTH - 266) + 6);
        g.drawString("Level:  " + Game.level, 240, (Game.HEIGTH) - (Game.HEIGTH - 266) + 20);

        g.setColor(Color.BLACK);
        g.drawString("Pontos:  " + Player.pontos, 400, (Game.HEIGTH) - (Game.HEIGTH - 266) + 6);

        // cano é retangulo preto, arma de fogo amarelo
        if (Player.selectedWeapon == "Cano") {

            try {
                ClassLoader classLoader = getClass().getClassLoader();
                InputStream inputStream = classLoader.getResourceAsStream("res/spritesheets/canoUi.png");
                assert inputStream != null;
                imagemArma = ImageIO.read(inputStream);
                // agora você pode usar a variável 'imagem' para manipular a imagem PNG

            } catch (IOException e) {
                System.out.println("Erro ao carregar a imagem: " + e.getMessage());
            }

            g.setColor(Color.BLACK);
        } else {
            g.setColor(Color.YELLOW);

            try {
                ClassLoader classLoader = getClass().getClassLoader();
                InputStream inputStream = classLoader.getResourceAsStream("res/spritesheets/armaUi.png");
                assert inputStream != null;
                imagemArma = ImageIO.read(inputStream);
                // agora você pode usar a variável 'imagem' para manipular a imagem PNG

            } catch (IOException e) {
                System.out.println("Erro ao carregar a imagem: " + e.getMessage());
            }
        }

        g.fillRect(10, (Game.HEIGTH) - (Game.HEIGTH - 261), 38, 28);
        g.drawImage(imagemArma, 10, (Game.HEIGTH) - (Game.HEIGTH - 261), null);
    }

}
