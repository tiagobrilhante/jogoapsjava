package entidades.interativos;

import entidades.Entity;
import java.awt.image.BufferedImage;

// munição extra
public class AmmunitionExtra extends Entity {

    public int qtdTiroBox;

    public AmmunitionExtra(int x, int y, int width, int height, BufferedImage sprite, String tipo, int quantidade) {
        super(x, y, width, height, sprite, tipo);
        this.qtdTiroBox = quantidade;
    }

}
