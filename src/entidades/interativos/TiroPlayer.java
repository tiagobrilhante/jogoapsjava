package entidades.interativos;


import Mundo.Camera;
import Mundo.Mundo;
import entidades.Entity;
import entidades.player.Player;
import main.Game;

import java.awt.*;
import java.awt.image.BufferedImage;

public class TiroPlayer extends Entity {

    // velocidade de deslocamento do tiro(teste)
    public double speed = 2.5;

    // localização
    public double xa, ya;
    public double dx;

    // timer
    public int timer = 0;

    public String dirDisparo = "Esquerda";

    public TiroPlayer(int x, int y, int width, int height, BufferedImage sprite) {

        super(x, y, width, height, sprite);

        xa = x;
        ya = y;

        // taxa de deslocamento
        dx += 15;

        // velocidade base de deslocamento
        speed = 8;

    }

    public void tick() {

        System.out.println("tick - " + " mundo: " + Mundo.HEIGHT*32);

        System.out.println(Game.tirosPLayer);

        xa += dx + speed;
        x = (int) xa;
        y = ya - Camera.y;
        timer++;



    }

    // responsável por renderizar a imagem
    public void render(Graphics g) {

        g.setColor(Color.BLACK);
        g.fillRect((int)( xa), (int)(ya), 50, 50);

    }



}
