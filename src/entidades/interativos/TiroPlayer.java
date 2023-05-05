package entidades.interativos;


import Mundo.Camera;
import Mundo.Mundo;
import entidades.Entity;
import entidades.player.Player;
import entidades.solidos.Solido;
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

        System.out.println("tick - " + " mundo: " + Mundo.HEIGHT*32);

        System.out.println(Game.tirosPLayer);

        xa += dx + speed;
        x = (int) xa;
        y = ya - Camera.y;
        timer++;

        if (colisao((int)x,(int)y)){
            System.out.println("colidiu");
        }


    }

    // responsável por renderizar a imagem
    public void render(Graphics g) {

        g.setColor(Color.BLACK);
        g.fillRect((int)( xa), (int)(ya), dimensoesTiro[0], dimensoesTiro[1]);

    }

    public boolean colisao(int nextx, int nexty) {

        Rectangle tiroRetangle = new Rectangle((int)xa, (int)ya, dimensoesTiro[0], dimensoesTiro[1]);

        for (int i = 0; i < Game.entidades.size(); i++) {
            Entity entidade = Game.entidades.get(i);
            if (entidade instanceof Solido) {
                Rectangle solido = new Rectangle(entidade.getX() + Entity.SIZEENTITYX, entidade.getY() + Entity.SIZEENTITYY, Entity.SIZEENTITYX, Entity.SIZEENTITYY);
                if (tiroRetangle.intersects(solido)) {
                    return true;
                }
            }
        }
        return false;
    }



}
