package Mundo;

import entidades.Entity;
import entidades.Grama;
import entidades.interativos.*;
import entidades.naoSolidos.Ceu;
import entidades.naoSolidos.GalhosSecos;
import entidades.naoSolidos.Nuvens;
import entidades.player.Player;
import entidades.solidos.Solido;
import graficos.Spritsheet;
import main.Game;

import javax.imageio.ImageIO;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
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

    public int timer = 0;

    public static double posX = 0, posY = 0;

    // cores das entidades mapeadas

    public int corEscadaTopo = 0xFFee8fbe, corEscada = 0xFFc4759d, corEscadaBase = 0xFF9b5d7c, corChaoNucleo = 0xFF086910,
            corChaoEsquerdo = 0xFFac4839, corChaoEsquerdoTopo = 0xFF18b229, corChaoEsquerdoFundo = 0xFF946d4a,
            corChaoDireito = 0xFFac6920, corChaoDireitoTopo = 0xFFCD3420, corChaoDireitoFundo = 0xFF6722ac,
            corChaoBaseTopo = 0xFF6a91a4, corChaoBaseFundo = 0xFF4a2420,
            corNucleoConverteDireitaChaoIsoladoTopo = 0xFF55d595, corNucleoConverteDireitaChaoIsoladoFundo = 0xFFd5d580,
            corNucleoConverteEsquerdaChaoIsoladoTopo = 0xFFd55555, corNucleoConverteEsquerdaChaoIsoladoFundo = 0xFF392420,
            corTijoloDeserto = 0xFFee8529, corKitHealth = 0xFFf6efef, corGrama = 0xFF38385d, corEspinhos = 0xFF4d8080,
            corGalhoSeco = 0xFF4d4d80, corPlacaSave = 0xFF808033, corInimigo1 = 0xFF494900, corInimigo2 = 0xFF606000,
            corCeu = 0xFF639bff, corPlayer = 0xFFffff00, corChaoIsoladoTopo = 0xFF7845ac, corChaoIsoladoFundo = 0xFFaad5c0,
            corNucleoBifurcaChaoIsoladoTopo = 0xFF45acac, corNucleoBifurcaChaoIsoladoFundo = 0xFFd57d29,
            corTrashBag = 0xFFf600F6;

    public String playerSpritePath = "/res/spritesheets/spritesheetPlayer.png";
    public static String gameSpritePath = "/res/spritesheets/spritesheet32.png";
    public static String ceuSpritePath = "/res/spritesheets/ceusprite.png";
    public static String nuvemSpritePath = "/res/spritesheets/ceuspriteClouds.png";
    public static String levelPath = "/res/fases/";
    public static String soundPath = "src/res/sounds/soundtracks/fase1.wav";

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

                    if (pixelAtual == corEscadaTopo) {
                        Escada escada = new Escada(x * Entity.SIZEENTITYX, y * Entity.SIZEENTITYY, Entity.SIZEENTITYX, Entity.SIZEENTITYY, 3, Entity.escadaTopo);
                        Game.escada.add(escada);
                    } else if (pixelAtual == corEscada) {
                        Escada escada = new Escada(x * Entity.SIZEENTITYX, y * Entity.SIZEENTITYY, Entity.SIZEENTITYX, Entity.SIZEENTITYY, 2, Entity.escada);
                        Game.escada.add(escada);
                    } else if (pixelAtual == corEscadaBase) {
                        Escada escada = new Escada(x * Entity.SIZEENTITYX, y * Entity.SIZEENTITYY, Entity.SIZEENTITYX, Entity.SIZEENTITYX, 1, Entity.escadaBase);
                        Game.escada.add(escada);
                    } else if (pixelAtual == corChaoIsoladoTopo) {
                        Solido solido = new Solido(x * Entity.SIZEENTITYX, y * Entity.SIZEENTITYY, Entity.SIZEENTITYX, Entity.SIZEENTITYY, Entity.chaoIsoladoTopo);
                        Game.entidades.add(solido);
                    } else if (pixelAtual == corChaoIsoladoFundo) {
                        Solido solido = new Solido(x * Entity.SIZEENTITYX, y * Entity.SIZEENTITYY, Entity.SIZEENTITYX, Entity.SIZEENTITYY, Entity.chaoIsoladoFundo);
                        Game.entidades.add(solido);
                    } else if (pixelAtual == corChaoEsquerdo) {
                        Solido solido = new Solido(x * Entity.SIZEENTITYX, y * Entity.SIZEENTITYY, Entity.SIZEENTITYX, Entity.SIZEENTITYY, Entity.chaoEsquerdo);
                        Game.entidades.add(solido);
                    } else if (pixelAtual == corChaoDireito) {
                        Solido solido = new Solido(x * Entity.SIZEENTITYX, y * Entity.SIZEENTITYY, Entity.SIZEENTITYX, Entity.SIZEENTITYY, Entity.chaoDireito);
                        Game.entidades.add(solido);
                    } else if (pixelAtual == corChaoEsquerdoTopo) {
                        Solido solido = new Solido(x * Entity.SIZEENTITYX, y * Entity.SIZEENTITYY, Entity.SIZEENTITYX, Entity.SIZEENTITYY, Entity.chaoEsquerdoTopo);
                        Game.entidades.add(solido);
                    } else if (pixelAtual == corChaoEsquerdoFundo) {
                        Solido solido = new Solido(x * Entity.SIZEENTITYX, y * Entity.SIZEENTITYY, Entity.SIZEENTITYX, Entity.SIZEENTITYY, Entity.chaoEsquerdoFundo);
                        Game.entidades.add(solido);
                    } else if (pixelAtual == corChaoDireitoTopo) {
                        Solido solido = new Solido(x * Entity.SIZEENTITYX, y * Entity.SIZEENTITYY, Entity.SIZEENTITYX, Entity.SIZEENTITYY, Entity.chaoDireitoTopo);
                        Game.entidades.add(solido);
                    } else if (pixelAtual == corChaoDireitoFundo) {
                        Solido solido = new Solido(x * Entity.SIZEENTITYX, y * Entity.SIZEENTITYY, Entity.SIZEENTITYX, Entity.SIZEENTITYY, Entity.chaoDireitoFundo);
                        Game.entidades.add(solido);
                    } else if (pixelAtual == corChaoNucleo) {
                        Solido solido = new Solido(x * Entity.SIZEENTITYX, y * Entity.SIZEENTITYY, Entity.SIZEENTITYX, Entity.SIZEENTITYY, Entity.chaoNucleo);
                        Game.entidades.add(solido);
                    } else if (pixelAtual == corChaoBaseTopo) {
                        Solido solido = new Solido(x * Entity.SIZEENTITYX, y * Entity.SIZEENTITYY, Entity.SIZEENTITYX, Entity.SIZEENTITYY, Entity.chaoNormalTopo);
                        Game.entidades.add(solido);
                    } else if (pixelAtual == corChaoBaseFundo) {
                        Solido solido = new Solido(x * Entity.SIZEENTITYX, y * Entity.SIZEENTITYY, Entity.SIZEENTITYX, Entity.SIZEENTITYY, Entity.chaoNormalFundo);
                        Game.entidades.add(solido);
                    } else if (pixelAtual == corNucleoConverteDireitaChaoIsoladoTopo) {
                        Solido solido = new Solido(x * Entity.SIZEENTITYX, y * Entity.SIZEENTITYY, Entity.SIZEENTITYX, Entity.SIZEENTITYY, Entity.nucleoConverteDireitaChaoIsoladoTopo);
                        Game.entidades.add(solido);
                    } else if (pixelAtual == corNucleoConverteDireitaChaoIsoladoFundo) {
                        Solido solido = new Solido(x * Entity.SIZEENTITYX, y * Entity.SIZEENTITYY, Entity.SIZEENTITYX, Entity.SIZEENTITYY, Entity.nucleoConverteDireitaChaoIsoladoFundo);
                        Game.entidades.add(solido);
                    } else if (pixelAtual == corNucleoConverteEsquerdaChaoIsoladoTopo) {
                        Solido solido = new Solido(x * Entity.SIZEENTITYX, y * Entity.SIZEENTITYY, Entity.SIZEENTITYX, Entity.SIZEENTITYY, Entity.nucleoConverteEsquerdaChaoIsoladoTopo);
                        Game.entidades.add(solido);
                    } else if (pixelAtual == corNucleoConverteEsquerdaChaoIsoladoFundo) {
                        Solido solido = new Solido(x * Entity.SIZEENTITYX, y * Entity.SIZEENTITYY, Entity.SIZEENTITYX, Entity.SIZEENTITYY, Entity.nucleoConverteEsquerdaChaoIsoladoFundo);
                        Game.entidades.add(solido);
                    } else if (pixelAtual == corNucleoBifurcaChaoIsoladoTopo) {
                        Solido solido = new Solido(x * Entity.SIZEENTITYX, y * Entity.SIZEENTITYY, Entity.SIZEENTITYX, Entity.SIZEENTITYY, Entity.nucleoBifurcaChaoIsoladoTopo);
                        Game.entidades.add(solido);
                    } else if (pixelAtual == corNucleoBifurcaChaoIsoladoFundo) {
                        Solido solido = new Solido(x * Entity.SIZEENTITYX, y * Entity.SIZEENTITYY, Entity.SIZEENTITYX, Entity.SIZEENTITYY, Entity.nucleoBifurcaChaoIsoladoFundo);
                        Game.entidades.add(solido);
                    } else if (pixelAtual == corTijoloDeserto) {
                        Solido solido = new Solido(x * Entity.SIZEENTITYX, y * Entity.SIZEENTITYY, Entity.SIZEENTITYX, Entity.SIZEENTITYY, Entity.tijoloDeserto);
                        Game.entidades.add(solido);
                    } else if (pixelAtual == corKitHealth) {
                        KitHealth kitHealth = new KitHealth(x * Entity.SIZEENTITYX, y * Entity.SIZEENTITYY, Entity.SIZEENTITYX, Entity.SIZEENTITYY, Entity.kitHealth);
                        Game.kitHealth.add(kitHealth);
                    }else if (pixelAtual == corTrashBag) {
                        TrashBag trashBag = new TrashBag(x * Entity.SIZEENTITYX, y * Entity.SIZEENTITYY, Entity.SIZEENTITYX, Entity.SIZEENTITYY, Entity.trashBag);
                        Game.trashBags.add(trashBag);
                    }
                    else if (pixelAtual == corGrama) {
                        Grama grama = new Grama(x * Entity.SIZEENTITYX, y * Entity.SIZEENTITYY, Entity.SIZEENTITYX, Entity.SIZEENTITYY, Entity.grama);
                        Game.entidades.add(grama);
                    } else if (pixelAtual == corEspinhos) {
                        Espinho espinho = new Espinho(x * Entity.SIZEENTITYX, y * Entity.SIZEENTITYY, Entity.SIZEENTITYX, Entity.SIZEENTITYX, Entity.espinho);
                        Game.entidades.add(espinho);
                    } else if (pixelAtual == corGalhoSeco) {
                        GalhosSecos galho = new GalhosSecos(x * Entity.SIZEENTITYX, y * Entity.SIZEENTITYY, Entity.SIZEENTITYX, Entity.SIZEENTITYX, Entity.galhoSeco);
                        Game.entidades.add(galho);
                    } else if (pixelAtual == corPlacaSave) {
                        CheckPoint checkPoint = new CheckPoint(x * Entity.SIZEENTITYX, y * Entity.SIZEENTITYY, Entity.SIZEENTITYX, Entity.SIZEENTITYX, Entity.save);
                        Game.entidades.add(checkPoint);
                        // adicionar, para posicionar a placa no chão (ao invés dela cair)
                        // checkPoint.setY(checkPoint.getY()+Entity.SIZEENTITYY);
                    } else if (pixelAtual == corInimigo1 || pixelAtual == corInimigo2) {
                        int tipo = 0;
                        if (pixelAtual == corInimigo1) {
                            tipo = 1;
                        } else if (pixelAtual == corInimigo2) {
                            tipo = 2;
                        }
                        Inimigo inimigo = new Inimigo(x * Inimigo.SIZEENEMYX, y * Inimigo.SIZEENEMYY, Inimigo.SIZEENEMYX, Inimigo.SIZEENEMYY, tipo, Entity.inimigo);
                        Game.inimigo.add(inimigo);
                    } else if (pixelAtual == corCeu) {
                        Ceu ceu = new Ceu(x * Entity.SIZEENTITYX, y * Entity.SIZEENTITYY, Entity.SIZEENTITYX, Entity.SIZEENTITYX, Entity.ceu);
                        Game.ceuVetor.add(ceu);
                    } else if (pixelAtual == corPlayer) {
                        Game.player.setX(x * Player.SIZEPLAYERX);
                        Game.player.setY(y * Player.SIZEPLAYERY);
                    }

                }
            }

            Nuvens nuvem = new Nuvens(Entity.SIZEENTITYX, Entity.SIZEENTITYY, Entity.SIZEENTITYX, Entity.SIZEENTITYX, Entity.nuvens);
            Game.nuvemVetor.add(nuvem);

            AudioMundo audio = new AudioMundo(); // Chamando a classe aonde está o audio.
            audio.AudioMundo(); // Chamando o método do audio


        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    // render do mundo
    public static void render(Graphics g) {

        // posicionamento da camera em relação aos itens renderizados
        // evita a renderização do que não aparece na tela...
        // poupa memória
        int xi = Camera.x / Player.SIZEPLAYERX;
        int yi = Camera.y / Player.SIZEPLAYERY;
        int xf = xi + (Game.WIDTH / Player.SIZEPLAYERX);
        int yf = yi + (Game.HEIGTH / Player.SIZEPLAYERY);
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
        Game.entidades = new ArrayList<Entity>();
        // spriteSheet e player
        Game.sprite = new Spritsheet(gameSpritePath);

        // vetor do ceu
        Game.ceuVetor = new ArrayList<Ceu>();
        Game.nuvemVetor = new ArrayList<Nuvens>();

        // kit de vida
        Game.kitHealth = new ArrayList<KitHealth>();

        // inimigo
        Game.inimigo = new ArrayList<Inimigo>();

        // ceu
        Game.ceu = new Spritsheet(ceuSpritePath);

        Game.nuvens = new Spritsheet(nuvemSpritePath);

        // escada
        Game.escada = new ArrayList<Escada>();
        // carrega a fase
        Game.mundo = new Mundo(levelPath + level);

        Game.player = new Player(0, 0, Player.SIZEPLAYERX, Player.SIZEPLAYERY, Game.sprite.getSprite(32, 0, Player.SIZEPLAYERX, Player.SIZEPLAYERY));
        Game.entidades.add(Game.player);
    }

    public class AudioMundo {

        void AudioMundo() { //Método AudioMundo para chamar na classe executavel.
            try {
                //Local absoluto de onde tá o arquivo
                // funciona com WAV - ainda não testei mp3
                AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(soundPath).getAbsoluteFile());
                Clip clip = AudioSystem.getClip();
                clip.open(audioInputStream);
                clip.start();
                // repete a música continuamente
                clip.loop(Clip.LOOP_CONTINUOUSLY);
            } catch (Exception ex) {
                // exception no console
                System.out.println("Erro ao executar SOM!");
                ex.printStackTrace();
            }
        }
    }
}
