package entidades.naoSolidos;

import Mundo.Camera;
import entidades.Entity;
import main.Game;

import java.awt.*;
import java.awt.image.BufferedImage;

// luz wall fundo 1 deverá fazer parte de uma classe de cenário não implementada
public class LuzWallFundo1 extends Entity {

    public int frames = 0, maxFrames = 4,
            index = 0,
            maxIndex = 3;

    public BufferedImage[] luzAnimation;

    public LuzWallFundo1(int x, int y, int width, int height, BufferedImage sprite, String tipo) {
        super(x, y, width, height, sprite, tipo);
        luzAnimation = new BufferedImage[4];
        for (int i = 0; i < 4; i++) {
            luzAnimation[i] = Game.spriteDecoration.getSprite((i * 64)+320, 64, 64, 64);
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
        g.drawImage(luzAnimation[index], this.getX() - Camera.x, this.getY() - Camera.y, null);
    }



}
