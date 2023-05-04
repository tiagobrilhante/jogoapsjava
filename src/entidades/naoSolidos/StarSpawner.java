package entidades.naoSolidos;

import Mundo.Mundo;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class StarSpawner {

    // realiza o spawn das estrelas na tela

    public int timer = 0;

    // lista de estrelas
    public List<Star> stars = new ArrayList<>();

    // updte (comportamento)
    public void update() {
        timer++;

        // preciso ajustar para que a poeira se mantenha fixa na tela
        if (stars.size() < 200) {
            // adiciono as estrelas pequenas a partir do fim da tela e desloco para a esquerda
            stars.add(new Star(new Random().nextInt((int)Mundo.posX*16), new Random().nextInt((int)Mundo.posX*16), 1, 1));

            // populo a tela inicial com estrelas um pouco maiores
           stars.add(new Star(new Random().nextInt((int)Mundo.posX*16), new Random().nextInt((int)Mundo.posX*16), 2, 2));
        }

        // isso cria uma forte ilusão de profundidade e deslocamento
        // atentar que estrelas quando o turbo for acionado apresentam comportamentos diferentes...
        // se alongando como um risco (linha)

        // para cada item da lista de estrelas
        for (int i = 0; i < stars.size(); i++) {

            Star current = stars.get(i);

            // faz o deslocamento da estrela para a esquerda
            stars.get(i).update();
            current.x--;
            // se chegar ao final da tela, remove (otimnização)

            if (current.x <= 0 || current.y >= Mundo.posY*16) {
                stars.remove(current);
            }

        }

    }

    // renderiza
    public void render(Graphics g) {

        // para cada item da lista, renderiza uma estrela
        for (Star current : stars) {
            Graphics2D g2 = (Graphics2D) g;
            g2.setColor(current.color);
            g2.fillRect(current.x, current.y, current.width, current.height);
        }
    }
}
