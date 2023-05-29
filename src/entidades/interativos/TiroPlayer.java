package entidades.interativos;


import Mundo.Camera;
import entidades.Entity;
import entidades.player.Player;
import entidades.solidos.Solido;
import main.Game;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Arrays;
import java.util.Objects;

public class TiroPlayer extends Entity {

    // velocidade de deslocamento do tiro(teste)
    public double speed = 2.5;

    public int[] dimensoesTiro = {30, 2};

    // localização
    public double xa, ya, x , y;
    public double dx;

    public String deslocamentoTiro;


    public TiroPlayer(int x, int y, int width, int height, BufferedImage sprite, String tipo, String deslocamentoTiro) {
        super(x, y, width, height, sprite, tipo);
        this.deslocamentoTiro = deslocamentoTiro;
        xa = x;
        ya = y;
        // taxa de deslocamento
        dx += 15;
        // velocidade base de deslocamento
        speed = 8;

    }

    public void tick() {

        if (Objects.equals(deslocamentoTiro, "Direita")) {
            xa += dx + speed;
        } else {
            xa -= dx + speed ;
        }

        x = xa;
        y = ya;

    }

    // responsável por renderizar a imagem
    public void render(Graphics g) {

        g.setColor(Color.BLACK);
        g.fillRect((int) (x), (int) (y), dimensoesTiro[0], dimensoesTiro[1]);

    }

    public boolean colisao(int nextx, int nexty) {

        Rectangle tiroRetangle;
        tiroRetangle = new Rectangle((int)x, (int)y , dimensoesTiro[0], dimensoesTiro[1]);

        for (int i = 0; i < Game.entidades.size(); i++) {
            Entity entidade = Game.entidades.get(i);
            if (entidade instanceof Solido) {
                Rectangle solido = new Rectangle(entidade.getX()-Camera.x, entidade.getY()-Camera.y, Entity.SIZEENTITYX, Entity.SIZEENTITYY);
                if (tiroRetangle.intersects(solido)) {
                    return true;
                }
            }
        }
        return false;
    }


    public Entity colisaoInimigo(int nextx, int nexty) {
        Rectangle tiroRetangle;
        tiroRetangle = new Rectangle((int)x, (int)y , dimensoesTiro[0], dimensoesTiro[1]);
        for (int i = 0; i < Game.inimigo.size(); i++) {
            Inimigo entidade = Game.inimigo.get(i);
            Rectangle inimigoRectangle = new Rectangle(entidade.getX()-Camera.x, entidade.getY()-Camera.y, Entity.SIZEENTITYX, Entity.SIZEENTITYY);
            if (tiroRetangle.intersects(inimigoRectangle)) {
                return entidade;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return "TiroPlayer { " +
                " speed= " + speed +
                ", dimensoesTiro= " + Arrays.toString(dimensoesTiro) +
                ", x= " + x +
                ", y= " + y +
                ", xa= " + xa +
                ", ya= " + ya +
                ", dx= " + dx +
                ", deslocamentoTiro='" + deslocamentoTiro + '\'' +
                '}';
    }
}
