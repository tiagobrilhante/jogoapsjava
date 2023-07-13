package entidades.interativos;

import entidades.Entity;
import java.awt.image.BufferedImage;

// escada (não solido) especial, habilita sobre e desce
public class Escada extends Entity {

    // 3 = topo
    // 2 = meio
    // 1 = base

    public int tipoEscada;

    public Escada(int x, int y, int width, int height, int tipoEscada,  BufferedImage sprite, String tipo) {
        super(x, y, width, height, sprite, tipo);
        this.tipoEscada = tipoEscada;
    }

    @Override
    public String toString() {
        return "Escada{" +
                "tipoEscada=" + tipoEscada +
                ", x=" + x +
                ", y=" + y +
                ", width=" + width +
                ", height=" + height +
                ", sprite=" + sprite +
                ", tipo='" + tipo + '\'' +
                '}';
    }
}


