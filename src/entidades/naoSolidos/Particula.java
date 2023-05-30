package entidades.naoSolidos;

import java.awt.*;
import java.util.Random;

// particulas usadas quando um inimigo explode e morre
public class Particula extends Rectangle {

    // objeto particula
    // acontece quando o inimigo explode ou o player explode (player ainda não implementado)
    public Color color;

    // velocidade das particulas
    public int speed;

    // direções das particulas
    public double xa, ya;
    public double dx, dy;

    // timer
    public int timer = 0;

    // construtor
    public Particula(int x, int y, int width, int height, Color color) {

        super(x, y, width, height);

        xa = x;
        ya = y;
        // recebe a cor de acordo com o inimigo... vai ser modificado mais pra frente
        this.color = color;

        // gera o gaussian para disparar as particulas em direções distintas partindo de um ponto x e y
        dx = new Random().nextGaussian()*0.02;
        dy = new Random().nextGaussian()*0.02;

        // velocidade de deslocamento das particulas
        speed = 5;

    }

    // realiza o update das particulas em deslocamento
    public void update() {

        xa += dx * speed*20;
        ya += dy * speed*20;

        timer++;

    }

    // renderiza as particulas
    public void render(Graphics g) {
        g.setColor(this.color);
        g.fillRect((int) xa, (int) ya, width, height);
    }
}
