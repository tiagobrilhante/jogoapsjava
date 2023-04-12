package Mundo;

import main.Game;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Tile {

    public int x,y;
    public BufferedImage sprite;

    // renderiza tiles(tem que sem ajustado)
    public Tile( int x, int y, BufferedImage sprite){
        this.x = x;
        this.y = y;
        this.sprite = sprite;
    }

    public void render (Graphics g){
        g.drawImage(sprite, x-Camera.x, y-Camera.y, null);
    }


}
