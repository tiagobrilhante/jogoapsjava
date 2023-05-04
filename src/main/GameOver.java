package main;


import entidades.player.Player;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

public class GameOver {
    public String[] options = {"Voltar para o menu", "Sair"};
    public int currentOption = 0;
    public int maxOption = options.length - 1;
    public boolean up, down, ok;
    private BufferedImage imagem;

    public int frames = 0, maxFrames = 25, index = 0, maxIndex = 24;

    public GameOver() {

        try {
            ClassLoader classLoader = getClass().getClassLoader();
            InputStream inputStream = classLoader.getResourceAsStream("res/spaceship1small.png");
            assert inputStream != null;
            imagem = ImageIO.read(inputStream);

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
                Historia.stopGameAudio();
                //inicia o jogo
                Player.life = Player.maxLife;
                Game.restart();

            }

            if (currentOption == 1) {
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

        g.setColor(Color.BLACK);
        g.fillRect(Game.WIDTH / 2, Game.HEIGTH / 2, Game.WIDTH, Game.HEIGTH);
        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.BOLD, 30));
        g.drawString("GAME OVER", Game.WIDTH / 2 + 160, Game.HEIGTH / 2 + 50);
        g.setFont(new Font("Arial", Font.BOLD, 20));
        g.drawString(options[0], Game.WIDTH / 2 + 80, Game.HEIGTH / 2 + 130);
        g.drawString(options[1], Game.WIDTH / 2 + 80, Game.HEIGTH / 2 + 180);

        if (currentOption == 0) g.drawImage(imagem, Game.WIDTH / 2 + 40, Game.HEIGTH / 2 + 110, null);
        if (currentOption == 1) g.drawImage(imagem, Game.WIDTH / 2 + 40, Game.HEIGTH / 2 + 160, null);

    }

}
