package entidades.naoSolidos;

import Mundo.Camera;
import entidades.Entity;
import entidades.player.Player;

import java.awt.image.BufferedImage;

// ceu
public class MountainsParalax extends Entity {
    public MountainsParalax(int x, int y, int width, int height, BufferedImage sprite) {
        super(x, y, width, height, sprite);
    }

    public void tick() {
        // velocidade de deslocamento da montanha

        if (Camera.x - (Player.atualX / 8) > 0) {
            this.x = (int) (Camera.x - (Player.atualX / 8));
        } else {
            this.x = 0;
        }

        //reseta a montanha
        if (this.x <= -(this.sprite.getWidth())) {
            this.x = this.sprite.getWidth();
        }

    }
}

