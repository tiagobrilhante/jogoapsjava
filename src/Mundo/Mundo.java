package Mundo;

import entidades.Entity;
import entidades.Grama;
import entidades.interativos.*;
import entidades.naoSolidos.*;
import entidades.player.Player;
import entidades.solidos.Solido;
import graficos.Spritsheet;
import main.Game;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

// extremamente importante....
// praticamente toda a lógica de como as coisas acontecem na fase estão aqui

public class Mundo {

    int identificadorUnicoInimigo = 1;
    // tamanho
    public static int WIDTH, HEIGHT;
    //lista de tiles
    public static Tile[] tiles;

    public int timer = 0;

    public static double posX = 0, posY = 0;

    // cores das entidades mapeadas

    public int corEscadaTopo = 0xFFee8fbe, corEscada = 0xFFc4759d, corEscadaBase = 0xFF9b5d7c, corChaoNucleo = 0xFF086910,
            corChaoEsquerdo = 0xFFac4839, corChaoEsquerdoTopo = 0xFF18b229, corChaoEsquerdoFundo = 0xFF946d4a,
            corJuncaoTopoEsquerda = 0xFFff9d52, corJuncaoFundoEsquerda = 0xFFff713d, corJuncaoTopoDireita = 0xFFff4551,
            corJuncaoFundoDireita = 0xFFff73f8,corJuncaoDupla1 = 0xFF61ff88, corJuncaoDupla2 = 0xFFc8ff52,
            corChaoDireito = 0xFFac6920, corChaoDireitoTopo = 0xFFCD3420, corChaoDireitoFundo = 0xFF6722ac,
            corChaoBaseTopo = 0xFF6a91a4, corChaoBaseFundo = 0xFF4a2420, corJuncaoSimplesLateralTopoDireita = 0xFF496372,
            corNucleoConverteDireitaChaoIsoladoTopo = 0xFF55d595, corNucleoConverteDireitaChaoIsoladoFundo = 0xFFd5d580,
            corNucleoConverteEsquerdaChaoIsoladoTopo = 0xFFd55555, corNucleoConverteEsquerdaChaoIsoladoFundo = 0xFF392420,
            corJuncaoBuEsquerdaBaixo = 0xFF585392, corJuncaoBuDireitaBaixo = 0xFF666663, corBuSimples = 0xFF666248,
            corTijoloDeserto = 0xFFee8529, corKitHealth = 0xFFf6efef, corGrama = 0xFF38385d, corEspinhos = 0xFF4d8080,
            corGalhoSeco = 0xFF4d4d80, corPlacaSave = 0xFF808033, corInimigo1 = 0xFF494900, corInimigo2 = 0xFF606000,
            corCeu = 0xFF639bff, corPlayer = 0xFFffff00, corChaoIsoladoTopo = 0xFF7845ac, corChaoIsoladoFundo = 0xFFaad5c0,
            corNucleoBifurcaChaoIsoladoTopo = 0xFF45acac, corNucleoBifurcaChaoIsoladoFundo = 0xFFd57d29,
            corTrashBag = 0xFFf600F6, corChaoIsoladoEsquerda = 0xFF355240, corChaoIsoladoDireita = 0xFF793b34,
            corJuncaoSimplesFundoDireita = 0xFFf22778, corFundoDarkBrickBase = 0xFF0e5050,
            corFundoDarkBrickEsquerdo = 0xFF646464, corFundoDarkBrickDireito = 0xFF494949,
            corFundoDarkBrickBrokenBase1 = 0xFF0e1052, corWallFundo1 = 0xFF2d3425, corPredioFundo1 = 0xFF157920,
            corChaoIsoladoMeioVertical = 0xFF2d2c7a, corPedra1 = 0xFF1a3917;

    public static String gameSpritePath = "/res/spritesheets/spritesheet32.png";
    public static String ceuSpritePath = "/res/spritesheets/ceusprite.png";
    public static String nuvemSpritePath = "/res/spritesheets/ceuspriteClouds.png";
    public static String levelPath = "/res/fases/";


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
                    if (pixelAtual == corFundoDarkBrickBase) {
                        FundoDarkBrick fundoDarkBrick = new FundoDarkBrick(x * Entity.SIZEENTITYX, y * Entity.SIZEENTITYY, Entity.SIZEENTITYX, Entity.SIZEENTITYY, Entity.fundoDarkBrickBase);
                        Game.darkBricksFundo.add(fundoDarkBrick);
                    }

                   else if (pixelAtual == corFundoDarkBrickBrokenBase1) {
                        FundoDarkBrick fundoDarkBrick = new FundoDarkBrick(x * Entity.SIZEENTITYX, y * Entity.SIZEENTITYY, Entity.SIZEENTITYX, Entity.SIZEENTITYY, Entity.fundoDarkBrickBrokenBase1);
                        Game.darkBricksFundo.add(fundoDarkBrick);
                    }

                    else if (pixelAtual == corFundoDarkBrickEsquerdo) {
                        FundoDarkBrick fundoDarkBrick = new FundoDarkBrick(x * Entity.SIZEENTITYX, y * Entity.SIZEENTITYY, Entity.SIZEENTITYX, Entity.SIZEENTITYY, Entity.fundoDarkBrickEsquerdo);
                        Game.darkBricksFundo.add(fundoDarkBrick);
                    }

                    else if (pixelAtual == corFundoDarkBrickDireito) {
                        FundoDarkBrick fundoDarkBrick = new FundoDarkBrick(x * Entity.SIZEENTITYX, y * Entity.SIZEENTITYY, Entity.SIZEENTITYX, Entity.SIZEENTITYY, Entity.fundoDarkBrickDireito);
                        Game.darkBricksFundo.add(fundoDarkBrick);
                    }

                    else if (pixelAtual == corPlacaSave) {
                        CheckPoint checkPoint = new CheckPoint(x * Entity.SIZEENTITYX, y * Entity.SIZEENTITYY, Entity.SIZEENTITYX, Entity.SIZEENTITYX, Entity.save);
                        Game.checkPoints.add(checkPoint);
                        // adicionar, para posicionar a placa no chão (ao invés dela cair)
                        // checkPoint.setY(checkPoint.getY()+Entity.SIZEENTITYY);
                    }


                    else if (pixelAtual == corEscadaTopo) {
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
                    }



                    else if (pixelAtual == corChaoIsoladoEsquerda) {
                        Solido solido = new Solido(x * Entity.SIZEENTITYX, y * Entity.SIZEENTITYY, Entity.SIZEENTITYX, Entity.SIZEENTITYY, Entity.chaoIsoladoEsquerda);
                        Game.entidades.add(solido);
                    }

                    else if (pixelAtual == corChaoIsoladoDireita) {
                        Solido solido = new Solido(x * Entity.SIZEENTITYX, y * Entity.SIZEENTITYY, Entity.SIZEENTITYX, Entity.SIZEENTITYY, Entity.chaoIsoladoDireita);
                        Game.entidades.add(solido);
                    }

                    else if (pixelAtual == corChaoIsoladoMeioVertical) {
                        Solido solido = new Solido(x * Entity.SIZEENTITYX, y * Entity.SIZEENTITYY, Entity.SIZEENTITYX, Entity.SIZEENTITYY, Entity.chaoIsoladoMeioVertical);
                        Game.entidades.add(solido);
                    }
                    else if (pixelAtual == corPedra1) {
                        Solido solido = new Solido(x * Entity.SIZEENTITYX, y * Entity.SIZEENTITYY, Entity.SIZEENTITYX, Entity.SIZEENTITYY, Entity.pedra1);
                        Game.entidades.add(solido);
                    }

                    else if (pixelAtual == corChaoIsoladoFundo) {
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
                    }

                    else if (pixelAtual == corJuncaoBuEsquerdaBaixo) {
                        Solido solido = new Solido(x * Entity.SIZEENTITYX, y * Entity.SIZEENTITYY, Entity.SIZEENTITYX, Entity.SIZEENTITYY, Entity.juncaoBuEsquerdaBaixo);
                        Game.entidades.add(solido);
                    }

                    else if (pixelAtual == corJuncaoBuDireitaBaixo) {
                        Solido solido = new Solido(x * Entity.SIZEENTITYX, y * Entity.SIZEENTITYY, Entity.SIZEENTITYX, Entity.SIZEENTITYY, Entity.juncaoBuDireitaBaixo);
                        Game.entidades.add(solido);
                    }

                    else if (pixelAtual == corBuSimples ) {
                        Solido solido = new Solido(x * Entity.SIZEENTITYX, y * Entity.SIZEENTITYY, Entity.SIZEENTITYX, Entity.SIZEENTITYY, Entity.buSimples );
                        Game.entidades.add(solido);
                    }



                    else if (pixelAtual == corJuncaoTopoEsquerda) {
                        Solido solido = new Solido(x * Entity.SIZEENTITYX, y * Entity.SIZEENTITYY, Entity.SIZEENTITYX, Entity.SIZEENTITYY, Entity.juncaoTopoEsquerda);
                        Game.entidades.add(solido);
                    }

                    else if (pixelAtual == corJuncaoFundoEsquerda) {
                        Solido solido = new Solido(x * Entity.SIZEENTITYX, y * Entity.SIZEENTITYY, Entity.SIZEENTITYX, Entity.SIZEENTITYY, Entity.juncaoFundoEsquerda);
                        Game.entidades.add(solido);
                    }

                    else if (pixelAtual == corJuncaoTopoDireita) {
                        Solido solido = new Solido(x * Entity.SIZEENTITYX, y * Entity.SIZEENTITYY, Entity.SIZEENTITYX, Entity.SIZEENTITYY, Entity.juncaoTopoDireita);
                        Game.entidades.add(solido);
                    }

                    else if (pixelAtual == corJuncaoFundoDireita) {
                        Solido solido = new Solido(x * Entity.SIZEENTITYX, y * Entity.SIZEENTITYY, Entity.SIZEENTITYX, Entity.SIZEENTITYY, Entity.juncaoFundoDireita);
                        Game.entidades.add(solido);
                    }

                    else if (pixelAtual == corJuncaoDupla1) {
                        Solido solido = new Solido(x * Entity.SIZEENTITYX, y * Entity.SIZEENTITYY, Entity.SIZEENTITYX, Entity.SIZEENTITYY, Entity.juncaoDupla1);
                        Game.entidades.add(solido);
                    }

                    else if (pixelAtual == corJuncaoDupla2) {
                        Solido solido = new Solido(x * Entity.SIZEENTITYX, y * Entity.SIZEENTITYY, Entity.SIZEENTITYX, Entity.SIZEENTITYY, Entity.juncaoDupla2);
                        Game.entidades.add(solido);
                    }

                    else if (pixelAtual == corJuncaoSimplesLateralTopoDireita) {
                        Solido solido = new Solido(x * Entity.SIZEENTITYX, y * Entity.SIZEENTITYY, Entity.SIZEENTITYX, Entity.SIZEENTITYY, Entity.juncaoSimplesLateralTopoDireita);
                        Game.entidades.add(solido);
                    }

                    else if (pixelAtual == corJuncaoSimplesFundoDireita) {
                        Solido solido = new Solido(x * Entity.SIZEENTITYX, y * Entity.SIZEENTITYY, Entity.SIZEENTITYX, Entity.SIZEENTITYY, Entity.juncaoSimplesFundoDireita);
                        Game.entidades.add(solido);
                    }

                    else if (pixelAtual == corChaoDireitoTopo) {
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
                    } else if (pixelAtual == corTrashBag) {
                        TrashBag trashBag = new TrashBag(x * Entity.SIZEENTITYX, y * Entity.SIZEENTITYY, Entity.SIZEENTITYX, Entity.SIZEENTITYY, Entity.trashBag);
                        Game.trashBags.add(trashBag);
                    } else if (pixelAtual == corGrama) {
                        Grama grama = new Grama(x * Entity.SIZEENTITYX, y * Entity.SIZEENTITYY, Entity.SIZEENTITYX, Entity.SIZEENTITYY, Entity.grama);
                        Game.entidades.add(grama);
                    } else if (pixelAtual == corEspinhos) {
                        Espinho espinho = new Espinho(x * Entity.SIZEENTITYX, y * Entity.SIZEENTITYY, Entity.SIZEENTITYX, Entity.SIZEENTITYX, Entity.espinho);
                        Game.espinhos.add(espinho);
                    } else if (pixelAtual == corGalhoSeco) {
                        GalhosSecos galho = new GalhosSecos(x * Entity.SIZEENTITYX, y * Entity.SIZEENTITYY, Entity.SIZEENTITYX, Entity.SIZEENTITYX, Entity.galhoSeco);
                        Game.entidades.add(galho);
                    } else if (pixelAtual == corInimigo1 || pixelAtual == corInimigo2) {

                        int tipoInimigo = 0;
                        if (pixelAtual == corInimigo1) {
                            tipoInimigo = 1;
                        } else if (pixelAtual == corInimigo2) {
                            tipoInimigo = 2;
                        }


                        // aqui é im incremento para por o inimigo no lugar correto
                        // tem que reajustar quando os inimigos passarem a ocupar 32 pixeis ao invés de 16
                        int incremento = 1;
                        if (Inimigo.SIZEENEMYX == 16){
                            incremento = 2;}

                        Inimigo inimigo = new Inimigo(identificadorUnicoInimigo,x * Inimigo.SIZEENEMYX*incremento, y * Inimigo.SIZEENEMYY*incremento, Inimigo.SIZEENEMYX, Inimigo.SIZEENEMYY, tipoInimigo, Entity.inimigo);

                        identificadorUnicoInimigo++;

                        Game.inimigo.add(inimigo);


                    } else if (pixelAtual == corCeu) {
                        Ceu ceu = new Ceu(x * Entity.SIZEENTITYX, y * Entity.SIZEENTITYY, Entity.SIZEENTITYX, Entity.SIZEENTITYX, Entity.ceu);
                        Game.ceuVetor.add(ceu);
                    }
                    else if (pixelAtual == corWallFundo1) {
                        WallFundo1 wallFundo1 = new WallFundo1(x * Entity.SIZEENTITYX, y * Entity.SIZEENTITYY, Entity.SIZEENTITYX, Entity.SIZEENTITYX, Entity.wallFundo1);
                        Game.wallFundo1Vetor.add(wallFundo1);
                    }
                    else if (pixelAtual == corPredioFundo1) {
                        PredioFundo1 predioFundo1 = new PredioFundo1(x * Entity.SIZEENTITYX, y * Entity.SIZEENTITYY, Entity.SIZEENTITYX, Entity.SIZEENTITYX, Entity.predioFundo1);
                        Game.predioFundo1Vetor.add(predioFundo1);
                    }

                    else if (pixelAtual == corPlayer) {
                        Game.player.setX(x * Player.SIZEPLAYERX);
                        Game.player.setY(y * Player.SIZEPLAYERY);
                    }

                }
            }

            Nuvens nuvem = new Nuvens(Entity.SIZEENTITYX, Entity.SIZEENTITYY, Entity.SIZEENTITYX, Entity.SIZEENTITYX, Entity.nuvens);
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

        // recarga de listas
        Game.entidades = new ArrayList<>();
        Game.ceuVetor = new ArrayList<>();
        Game.nuvemVetor = new ArrayList<>();
        Game.predioFundo1Vetor = new ArrayList<>();
        Game.wallFundo1Vetor = new ArrayList<>();
        Game.checkPoints = new ArrayList<>();
        Game.espinhos = new ArrayList<>();
        Game.darkBricksFundo = new ArrayList<>();
        Game.trashBags = new ArrayList<>();
        Game.grama = new ArrayList<>();
        Game.kitHealth = new ArrayList<>();
        Game.inimigo = new ArrayList<>();
        Game.ceu = new Spritsheet(ceuSpritePath);
        Game.nuvens = new Spritsheet(nuvemSpritePath);
        Game.escada = new ArrayList<>();

        Game.sprite = new Spritsheet(gameSpritePath);
        Game.mundo = new Mundo(levelPath + level);

        Game.player = new Player(0, 0, Player.SIZEPLAYERX, Player.SIZEPLAYERY, Game.sprite.getSprite(32, 0, Player.SIZEPLAYERX, Player.SIZEPLAYERY));
        Game.entidades.add(Game.player);
    }

}
