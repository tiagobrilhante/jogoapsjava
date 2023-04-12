package entidades.interativos;

import entidades.Entity;
import entidades.solidos.Solido;
import main.Game;

import java.awt.*;
import java.awt.image.BufferedImage;

// checkpoint (deve ser melhorado)
public class CheckPoint extends Entity {

    public int maskx = 0, masky = 0, maskw = Entity.SIZEENTITYX, maskh = Entity.SIZEENTITYY;
    public CheckPoint(int x, int y, int width, int height, BufferedImage sprite) {
        super(x, y, width, height, sprite);
    }

    public void tick(){
        if (!colisao((int)x, (int)(y+1))){
           // remover para retirar o efeito gravidade
            y+=2;
        }
    }

    // todas as entidades que tocam o solo devem ter isso
    public boolean colisao(int nextX, int nextY){
        Rectangle placa = new Rectangle(nextX + maskx, nextY + masky, maskw, maskh);
        for (int i = 0; i < Game.entidades.size(); i++) {
            Entity entidade = Game.entidades.get(i);
            if (entidade instanceof Solido){
                Rectangle solido = new Rectangle(entidade.getX() + maskx, entidade.getY() + masky, maskw, maskh );
                if (placa.intersects(solido)){
                    return true;
                }
            }
        }
        return false;

    }
}
