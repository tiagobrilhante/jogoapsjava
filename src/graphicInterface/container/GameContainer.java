package graphicInterface.container;

import main.Game;
import settings.GameSettings;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

public class GameContainer extends JFrame {
    public GameContainer(Game game) {
        this.setTitle(GameSettings.getGameName());

        this.pack();
        this.setDefaultCloseOperation(this.EXIT_ON_CLOSE);
        ImageIcon icon = createImageIcon(GameSettings.getGameIconPath());
        if (icon != null) {
            this.setIconImage(icon.getImage());
        }
        this.setFocusableWindowState(true);
        this.setMinimumSize(new Dimension(GameSettings.getGAME_WIDTH()*GameSettings.getGAME_SCALE() , GameSettings.getGAME_HEIGHT()*GameSettings.getGAME_SCALE()));
        this.setPreferredSize(new Dimension(GameSettings.getGAME_WIDTH()*GameSettings.getGAME_SCALE() , GameSettings.getGAME_HEIGHT() *GameSettings.getGAME_SCALE()));
        this.setResizable(false);
        this.add(game);
        this.setFocusable(true);
        this.setLocationRelativeTo(null);

        KeyboardFocusManager.getCurrentKeyboardFocusManager().clearGlobalFocusOwner();
        KeyboardFocusManager.getCurrentKeyboardFocusManager().clearFocusOwner();
        this.requestFocusInWindow();

        this.toFront();
        this.requestFocus();
        this.setVisible(true);

    }

    // Método auxiliar para criar um ImageIcon com o caminho fornecido
    private ImageIcon createImageIcon(String path) {
        URL imgURL = getClass().getResource(path);
        if (imgURL != null) {
            return new ImageIcon(imgURL);
        } else {
            System.err.println("Imagem não encontrada: " + path);
            return null;
        }
    }

}
