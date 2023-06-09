package Mundo;

import Mundo.Generator.MundoGenerator;
import entidades.Entity;
import entidades.naoSolidos.Ceu;
import entidades.naoSolidos.FundoEstatico;
import entidades.naoSolidos.Nuvens;
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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

// extremamente importante....
// praticamente toda a lógica de como as coisas acontecem na fase estão aqui

public class Mundo {

    // tamanho
    public static int WIDTH, HEIGHT;
    //lista de tiles
    public static Tile[] tiles;

    public static double posX = 0, posY = 0;

    public Mundo() {
        // Lógica do construtor padrão, se necessário
    }

    // método construtor
    public Mundo(String path) {


        // remove o numero do level para que seja usado no carregamento das demais imagens da fase
        Pattern pattern = Pattern.compile("/res/fases/level(\\d+)\\.png");
        Matcher matcher = pattern.matcher(path);
        int number = 0;
        if (matcher.find()) {
            String numberString = matcher.group(1);
            number = Integer.parseInt(numberString);
        }

        try {

            Game.fundoEstaticoVetor.add(new FundoEstatico(0, 0, Entity.SIZEENTITYX, Entity.SIZEENTITYX, Entity.fundoEstatico, "FundoEstatico"));
            Game.nuvemVetor.add(new Nuvens(Entity.SIZEENTITYX, Entity.SIZEENTITYY, Entity.SIZEENTITYX, Entity.SIZEENTITYX, Entity.nuvens, "Nuvem"));

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

                Game.ceuVetor.add(new Ceu(x * Entity.SIZEENTITYX - 32, 0, Entity.SIZEENTITYX, Entity.SIZEENTITYX, Entity.entityGenerator("ceu"), "Ceu"));

                for (int y = 0; y < level.getHeight(); y++) {
                    posY = y;
                    int pixelAtual = pixels[x + (y * level.getWidth())];
                    // popula os tiles vazios
                    tiles[x + (y * WIDTH)] = new Empty(x * Entity.SIZEENTITYX, y * Entity.SIZEENTITYY, Entity.empty);
                    // injeta os tiles
                    MundoGenerator.injetorEntidades(x, y, pixelAtual);

                }
            }



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
    @SuppressWarnings("InstantiationOfUtilityClass")
    public static void newlevel(String level) {
        // recarga de listas
        Game.entidades = new ArrayList<>();
        Game.ceuVetor = new ArrayList<>();
        Game.mountainVetor = new ArrayList<>();
        Game.nuvemVetor = new ArrayList<>();
        Game.fundoEstaticoVetor = new ArrayList<>();
        Game.luzWallFundo1Vetor = new ArrayList<>();
        Game.checkPoints = new ArrayList<>();
        Game.espinhos = new ArrayList<>();
        Game.trashBags = new ArrayList<>();
        Game.vidasExtras = new ArrayList<>();
        Game.ammunitionExtras = new ArrayList<>();
        Game.grama = new ArrayList<>();
        Game.kitHealth = new ArrayList<>();
        Game.inimigo = new ArrayList<>();
        Game.escada = new ArrayList<>();
        Game.ceu = new Spritsheet(GameSettings.spriteCeuPath);
        Game.nuvens = new Spritsheet(GameSettings.spriteNuvemPath);
        Game.spriteSolid = new Spritsheet(GameSettings.spriteSolidPath);
        Game.spriteDecoration = new Spritsheet(GameSettings.spriteDecorationPath);
        Game.spriteInterative = new Spritsheet(GameSettings.spriteInterativePath);
        Game.mundo = new Mundo(GameSettings.levelPath + level);
        Game.player = new Player(0, 0, Player.getLarguraPlayer(), Player.getLarguraPlayer(), Game.spritePlayer.getSprite(32, 0, Player.getLarguraPlayer(), Player.getAlturaPlayer()), "Player");
        Game.entidades.add(Game.player);
    }


}
