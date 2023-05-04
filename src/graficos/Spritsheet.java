package graficos;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

// serve para load dos spritesheet
public class Spritsheet {
    public BufferedImage spritSheet;

    public Spritsheet(String path) {

        try {
            spritSheet = ImageIO.read(Objects.requireNonNull(getClass().getResource(path)));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public BufferedImage getSprite(int x, int y, int width, int height){
        return spritSheet.getSubimage(x,y,width,height);
    }
}
