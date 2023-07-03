package entidades.naoSolidos;

import java.awt.*;
import java.util.Random;

public class Dust extends Rectangle {

    public Color color;

    public int speed;

    public Dust(int x, int y, int width, int height) {

        super(x, y, width, height);
        color = Color.yellow;
        speed = new Random().nextInt(2);

    }

    public void update() {
        y++;
        x -= speed;
    }
}
