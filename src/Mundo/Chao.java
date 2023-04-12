package Mundo;

import java.awt.image.BufferedImage;

public class Chao extends Tile {
    // renderiza o chão (sólido onde o player pisa)
    public Chao(int x, int y, BufferedImage sprite) {
        super(x, y, sprite);
    }
}
