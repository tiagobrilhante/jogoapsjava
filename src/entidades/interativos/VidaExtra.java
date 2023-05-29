package entidades.interativos;

import entidades.Entity;
import entidades.solidos.Solido;
import main.Game;

import java.awt.*;
import java.awt.image.BufferedImage;


// kit saúde
public class VidaExtra extends Entity {

    public int maskx= 0, masky = 0, maskw = Entity.SIZEENTITYX, maskh = Entity.SIZEENTITYY;

    public VidaExtra(int x, int y, int width, int height, BufferedImage sprite, String tipo) {
        super(x, y, width, height, sprite, tipo);
    }

}
