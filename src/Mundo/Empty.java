package Mundo;

import java.awt.image.BufferedImage;

// o que é vazio no jogo (não gera colisão)
public class Empty extends Tile {
    public Empty(int x, int y, BufferedImage sprite) {
        super(x, y, sprite);
    }
}
