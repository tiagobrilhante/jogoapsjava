package entidades.interativos;

import entidades.Entity;
import entidades.solidos.Solido;
import main.Game;

import java.awt.*;
import java.awt.image.BufferedImage;


// kit saúde
public class AmmunitionExtra extends Entity {

    public int maskx= 0, masky = 0, maskw = Entity.SIZEENTITYX, maskh = Entity.SIZEENTITYY;
    public int qtdTiroBox;

    public AmmunitionExtra(int x, int y, int width, int height, BufferedImage sprite) {
        super(x, y, width, height, sprite);
        this.qtdTiroBox = 5;
    }

    // manda para encostar no solo
    public void tick(){
        if (!colisao((int)x,(int)(y+1))){
            y+=2;
        }

    }

    // responsável pela colisão
    public boolean colisao(int nextX, int nextY){
        Rectangle leAmmo = new Rectangle(nextX + maskx, nextY + masky, maskw, maskh);
        for (int i = 0; i < Game.entidades.size(); i++) {
            Entity entidade = Game.entidades.get(i);
            if (entidade instanceof Solido){
                Rectangle solido = new Rectangle(entidade.getX() + maskx, entidade.getY() + masky, maskw, maskh );
                if (leAmmo.intersects(solido)){
                    return true;
                }
            }
        }
        return false;

    }
}
