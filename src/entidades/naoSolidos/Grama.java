package entidades.naoSolidos;

import Mundo.Camera;
import entidades.Entity;
import main.Game;

import java.awt.*;
import java.awt.image.BufferedImage;

// grama (não solido)
public class Grama extends Entity {

    public int frames = 0, maxFrames = 5,
            index = 0,
            maxIndex = 3;

    public BufferedImage[] gramaAnimation;

    public Grama(int x, int y, int width, int height, BufferedImage sprite, String tipo) {
        super(x, y, width, height, sprite, tipo);
        gramaAnimation = new BufferedImage[4];
        for (int i = 0; i < 4; i++) {
            gramaAnimation[i] = Game.spriteDecoration.getSprite((i * Entity.SIZEENTITYX)+96, 192, Entity.SIZEENTITYX, Entity.SIZEENTITYY);
        }
    }

    public void tick() {
        frames++;
        if (frames == maxFrames) {
            index++;
            frames = 0;
            if (index > maxIndex) {
                index = 0;
            }
        }


    }

    public void render(Graphics g) {
        g.drawImage(gramaAnimation[index], this.getX() - Camera.x, this.getY() - Camera.y, null);
    }

}
