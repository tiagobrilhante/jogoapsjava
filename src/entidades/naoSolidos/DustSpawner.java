package entidades.naoSolidos;

import Mundo.Mundo;
import settings.GameSettings;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class DustSpawner {
    public int timer = 0;

    // lista de poeira
    public List<Dust> dusts = new ArrayList<>();

    // updte (comportamento)
    public void update() {
        timer++;

        // preciso ajustar para que a poeira se mantenha fixa na tela
        if (dusts.size() < 200) {
            // adiciono as poeiras pequenas a partir do fim da tela e desloco para a esquerda
            dusts.add(new Dust(new Random().nextInt((int) Mundo.posX * 16), new Random().nextInt((int) Mundo.posX * 16), 1, 1));

            // populo a tela inicial com poeiras um pouco maiores
            dusts.add(new Dust(new Random().nextInt((int) Mundo.posX * 16), new Random().nextInt((int) Mundo.posX * 16), 2, 2));
        }

        // para cada item da lista de poeira
        for (int i = 0; i < dusts.size(); i++) {
            Dust current = dusts.get(i);
            // faz o deslocamento da poeira para a esquerda
            dusts.get(i).update();
            current.x--;
            // se chegar ao final da tela, remove (otimnização)
            if (current.x <= 0 || current.y >= GameSettings.getGAME_HEIGHT()) {
                dusts.remove(current);
            }

        }

    }

    // renderiza
    public void render(Graphics g) {

        // para cada item da lista, renderiza uma poeira
        for (Dust current : dusts) {
            Graphics2D g2 = (Graphics2D) g;
            g2.setColor(current.color);
            g2.fillRect(current.x, current.y, current.width, current.height);
        }
    }
}
