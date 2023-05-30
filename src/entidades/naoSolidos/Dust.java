package entidades.naoSolidos;

import java.awt.*;
import java.util.Random;

public class Dust extends Rectangle {

    // objeto estrela

    // quando em turbo as estrelas apresentam um comportamento diferente
    // como se fossem umas linhas maiores

    public Color color;

    public int speed;

    public Dust(int x, int y, int width, int height) {

        super(x, y, width, height);
        color = Color.yellow;
        speed = new Random().nextInt(2);

    }

    // as estrelas são movimentadas da esquerda para a direita
    public void update() {
        y++;
        x -= speed;
    }
}
