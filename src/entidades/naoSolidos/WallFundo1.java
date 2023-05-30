package entidades.naoSolidos;

import entidades.Entity;

import java.awt.image.BufferedImage;

// wall fundo 1 deverá fazer parte de uma classe de cenário não implementada
public class WallFundo1 extends Entity {
    public WallFundo1(int x, int y, int width, int height, BufferedImage sprite, String tipo) {
        super(x, y, width, height, sprite, tipo);
    }
}
