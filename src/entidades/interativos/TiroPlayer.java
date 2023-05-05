package entidades.interativos;


import Mundo.Camera;
import entidades.Entity;
import main.Game;

import java.awt.*;
import java.awt.image.BufferedImage;

public class TiroPlayer extends Entity {

    // velocidade de deslocamento do tiro(teste)
    public double speed = 2.5;

    public int[] dimensoesTiro = {30, 2};

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

        xa += dx + speed;
        x = (int) xa;
        y = ya - Camera.y;
        timer++;

    }

    // responsável por renderizar a imagem
    public void render(Graphics g) {

        g.setColor(Color.BLACK);
        g.fillRect((int) (xa), (int) (ya), dimensoesTiro[0], dimensoesTiro[1]);

    }

    public boolean colisao(int nextx, int nexty) {
        System.out.println("testando colisão");
        System.out.println("quantidade de entidades: " + Game.entidades.size());

        Rectangle tiroRetangle = new Rectangle((int) xa, (int) ya, dimensoesTiro[0], dimensoesTiro[1]);
        System.out.println("retangulo de tiro: " + tiroRetangle);

        for (int i = 0; i < Game.entidades.size(); i++) {
            Entity entidade = Game.entidades.get(i);
            Rectangle solido = new Rectangle(entidade.getX(), entidade.getY(), Entity.SIZEENTITYX, Entity.SIZEENTITYY);
            if (tiroRetangle.intersects(solido)) {
                System.out.println("true");
                return true;
            }

        }
        System.out.println("F");
        return false;
    }


}
