package entidades.interativos;


import Mundo.Camera;
import entidades.Entity;
import entidades.naoSolidos.Particula;
import entidades.player.Player;
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

    public TiroPlayer(int x, int y, int width, int height, BufferedImage sprite, String tipo) {

        super(x, y, width, height, sprite, tipo);

        xa = x;
        ya = y;

        // taxa de deslocamento
        dx += 15;

        // velocidade base de deslocamento
        speed = 8;

    }

    public void tick() {

        if (Player.direcaoAtual == 1) {
            xa += dx + speed;
        } else {
            xa -= dx + speed;
        }

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

        Rectangle tiroRetangle;

        if (Player.direcaoAtual == 1) {
            tiroRetangle = new Rectangle(nextx - Camera.x + 20, nexty - Camera.y +16, dimensoesTiro[0], dimensoesTiro[1]);
        } else {
            tiroRetangle = new Rectangle(nextx - Camera.x - 20, nexty - Camera.y +16, dimensoesTiro[0], dimensoesTiro[1]);
        }


        for (int i = 0; i < Game.entidades.size(); i++) {
            Entity entidade = Game.entidades.get(i);
            Rectangle solido = new Rectangle(entidade.getX(), entidade.getY(), Entity.SIZEENTITYX, Entity.SIZEENTITYY);
            if (tiroRetangle.intersects(solido)) {
                System.out.println(entidade);
                System.out.println("colisão com solido");
                return true;
            }

        }
        return false;
    }


    public Entity colisaoInimigo(int nextx, int nexty) {
        Rectangle tiroRetangle;

        if (Player.direcaoAtual == 1) {
            tiroRetangle = new Rectangle(nextx - Camera.x + 20, nexty - Camera.y +16, dimensoesTiro[0], dimensoesTiro[1]);
        } else {
            tiroRetangle = new Rectangle(nextx - Camera.x - 20, nexty - Camera.y +16, dimensoesTiro[0], dimensoesTiro[1]);
        }
        for (int i = 0; i < Game.inimigo.size(); i++) {
            Inimigo entidade = Game.inimigo.get(i);
            Rectangle inimigoRectangle = new Rectangle(entidade.getX(), entidade.getY(), Entity.SIZEENTITYX, Entity.SIZEENTITYY);
            if (tiroRetangle.intersects(inimigoRectangle)) {
                System.out.println("dano");
                return entidade;
            }
        }
        return null;
    }


}
