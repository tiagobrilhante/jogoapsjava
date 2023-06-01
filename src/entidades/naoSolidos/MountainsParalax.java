package entidades.naoSolidos;

import Mundo.Camera;
import entidades.Entity;
import entidades.player.Player;

import java.awt.image.BufferedImage;

// paralax para a montanha de fundo
public class MountainsParalax extends Entity {
    public MountainsParalax(int x, int y, int width, int height, BufferedImage sprite, String tipo) {
        super(x, y, width, height, sprite, tipo);
    }

    public void tick() {
        // velocidade de deslocamento da montanha
        this.x = Math.max(Camera.x - (Player.atualX / 8), 0);
        //reseta a montanha
        if (this.x <= -(this.sprite.getWidth())) {
            this.x = this.sprite.getWidth();
        }

    }
}


