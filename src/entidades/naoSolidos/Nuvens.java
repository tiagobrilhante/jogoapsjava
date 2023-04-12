package entidades.naoSolidos;

import entidades.Entity;

import java.awt.image.BufferedImage;

// ceu
public class Nuvens extends Entity {
    public Nuvens(int x, int y, int width, int height, BufferedImage sprite) {
        super(x, y, width, height, sprite);
    }

    // movimenta as nuvens

    public void tick() {
        // velocidade de deslocamento da nuvem
        this.x -= 0.2;

        //reseta a nuvem
        if (this.x <= -(this.sprite.getWidth())){
            this.x = this.sprite.getWidth();
        }

    }


}
