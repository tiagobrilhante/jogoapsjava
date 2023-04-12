package main;

import entidades.player.Player;

import java.awt.*;

public class UserInterface {

    // por enquanto esse método só renderiza a barra de vida do player
    // deve evoluir para uma interface melhorada
    public void render(Graphics g) {

        g.setColor(Color.GRAY);
        g.fillRect(0, (Game.HEIGTH) - (Game.HEIGTH - 258), Game.WIDTH, Game.HEIGTH - (Game.HEIGTH - 150) );

        g.setColor(Color.BLACK);
        g.fillRect(59, (Game.HEIGTH) - (Game.HEIGTH - 265), 52, 9);


        g.setColor(Color.RED);
        g.fillRect(60, (Game.HEIGTH) - (Game.HEIGTH - 266), 50, 7);


        g.setColor(Color.GREEN);

        g.fillRect(60, (Game.HEIGTH) - (Game.HEIGTH - 266), (int) ((Game.player.life / Game.player.maxLife) * 50), 7);

        g.setFont(new Font("Arial", Font.BOLD, 7));
        g.setColor(Color.BLACK);
        g.drawString("Vida", 65, (Game.HEIGTH) - (Game.HEIGTH - 266) + 6);

        g.setFont(new Font("Arial", Font.BOLD, 10));
        g.setColor(Color.BLACK);
        g.drawString("Pontos:  " + Player.pontos, 60, (Game.HEIGTH) - (Game.HEIGTH - 266) + 20);


        g.setColor(Color.BLACK);
        g.fillRect(10, (Game.HEIGTH) - (Game.HEIGTH - 261),30, 28 );
    }

}
