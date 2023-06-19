package Mundo;

import Mundo.Generator.MundoGenerator;
import entidades.Entity;
import entidades.naoSolidos.*;
import entidades.player.Player;
import graficos.Spritsheet;
import main.Game;
import settings.GameSettings;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

// extremamente importante....
// praticamente toda a lógica de como as coisas acontecem na fase estão aqui

public class Mundo {

    // tamanho
    public static int WIDTH, HEIGHT;
    //lista de tiles
    public static Tile[] tiles;

    public static double posX = 0, posY = 0;

    // método construtor
    public Mundo(String path) {

        try {

            // mapeamento do mundo (de acordo com os pixel da base da fase)
            BufferedImage level = ImageIO.read(Objects.requireNonNull(getClass().getResource(path)));
            int[] pixels = new int[level.getWidth() * level.getHeight()];
            tiles = new Tile[level.getWidth() * level.getHeight()];

            // tamanho da fase (arquivo .png)
            WIDTH = level.getWidth();
            HEIGHT = level.getHeight();

            // pega as cores dos pixel do arquivo base e prepara para a conversão
            level.getRGB(0, 0, level.getWidth(), level.getHeight(), pixels, 0, level.getWidth());

            // passa pelo exito x e y do arquivo de fase
            for (int x = 0; x < level.getWidth(); x++) {
                posX = x;
                for (int y = 0; y < level.getHeight(); y++) {
                    posY = y;
                    int pixelAtual = pixels[x + (y * level.getWidth())];
                    // popula os tiles vazios
                    tiles[x + (y * WIDTH)] = new Empty(x * Entity.SIZEENTITYX, y * Entity.SIZEENTITYY, Entity.empty);
                    // injeta os tiles
                    MundoGenerator.geraEntidades(x, y, pixelAtual);

                }
            }

            Nuvens nuvem = new Nuvens(Entity.SIZEENTITYX, Entity.SIZEENTITYY, Entity.SIZEENTITYX, Entity.SIZEENTITYX, Entity.nuvens, "Nuvem");
            Game.nuvemVetor.add(nuvem);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    // render do mundo
    public static void render(Graphics g) {
        // posicionamento da camera em relação aos itens renderizados
        // evita a renderização do que não aparece na tela...
        // poupa memória
        int xi = Camera.x / Player.getLarguraPlayer();
        int yi = Camera.y / Player.getAlturaPlayer();
        int xf = xi + (GameSettings.getGAME_WIDTH() / Player.getLarguraPlayer());
        int yf = yi + (GameSettings.getGAME_HEIGHT() / Player.getAlturaPlayer());
        for (int x = xi; x <= xf; x++) {
            for (int y = yi; y <= yf; y++) {
                if (x < 0 || y < 0 || x >= WIDTH || y >= HEIGHT)
                    continue;
                Tile tile = tiles[x + (y * WIDTH)];
                tile.render(g);
            }
        }
    }

    // inicia o level 1
    public static void newlevel(String level) {
        // recarga de listas
        Game.entidades = new ArrayList<>();
        Game.ceuVetor = new ArrayList<>();
        Game.mountainVetor = new ArrayList<>();
        Game.nuvemVetor = new ArrayList<>();
        Game.predioFundo1Vetor = new ArrayList<>();
        Game.wallFundo1Vetor = new ArrayList<>();
        Game.checkPoints = new ArrayList<>();
        Game.espinhos = new ArrayList<>();
        Game.darkBricksFundo = new ArrayList<>();
        Game.trashBags = new ArrayList<>();
        Game.vidasExtras = new ArrayList<>();
        Game.ammunitionExtras = new ArrayList<>();
        Game.grama = new ArrayList<>();
        Game.kitHealth = new ArrayList<>();
        Game.inimigo = new ArrayList<>();
        Game.escada = new ArrayList<>();
        Game.ceu = new Spritsheet(Game.spriteCeuPath);
        Game.nuvens = new Spritsheet(Game.spriteNuvemPath);
        Game.sprite = new Spritsheet(Game.spriteTerrenoPath);
        Game.mundo = new Mundo(Game.levelPath + level);
        Game.player = new Player(0, 0, Player.getLarguraPlayer(), Player.getLarguraPlayer(), Game.sprite.getSprite(32, 0, Player.getLarguraPlayer(), Player.getAlturaPlayer()), "Player");
        Game.entidades.add(Game.player);
    }



}
