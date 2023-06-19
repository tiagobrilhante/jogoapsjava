package Mundo;

import entidades.Entity;
import entidades.interativos.*;
import entidades.naoSolidos.*;
import entidades.player.Player;
import entidades.solidos.Solido;
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

    int identificadorUnicoInimigo = 1;
    // tamanho
    public static int WIDTH, HEIGHT;
    //lista de tiles
    public static Tile[] tiles;

    public static double posX = 0, posY = 0;

    // cores das entidades mapeadas
    public String corFundoDarkBrickBase = "0E5050", corEscadaTopo = "EE8FBE", corFundoDarkBrickBrokenBase1 = "0E1052",
            corEscada = "C4759D", corEscadaBase = "9B5D7C", corChaoNucleo = "086910", corChaoEsquerdo = "AC4839",
            corChaoEsquerdoTopo = "18B229",  corChaoEsquerdoFundo = "946d4A", corJuncaoTopoEsquerda = "FF9D52",
            corJuncaoFundoEsquerda = "FF713D", corJuncaoTopoDireita = "FF4551",  corJuncaoFundoDireita = "FF73F8",
            corJuncaoDupla1 = "61FF88", corJuncaoDupla2 = "C8FF52", corChaoDireito = "AC6920", corChaoDireitoTopo = "CD3420",
            corChaoDireitoFundo = "6722AC", corChaoBaseTopo = "6A91A4", corChaoBaseFundo = "4A2420",
            corJuncaoSimplesLateralTopoDireita = "496372", corNucleoConverteDireitaChaoIsoladoTopo = "55D595",
            corNucleoConverteDireitaChaoIsoladoFundo = "D5D580", corNucleoConverteEsquerdaChaoIsoladoTopo = "D55555",
            corNucleoConverteEsquerdaChaoIsoladoFundo = "392420", corJuncaoBuEsquerdaBaixo = "585392",
            corJuncaoBuDireitaBaixo = "666663", corBuSimples = "666248", corTijoloDeserto = "EE8529",
            corKitHealth = "F6EFEF", corGrama = "38385D", corEspinhos = "4D8080", corGalhoSeco = "4D4D80",
            corPlacaSave = "808033", corInimigo1 = "494900", corInimigo2 = "606000", corInimigo3 = "20140C", corInimigo4 = "A88F39",
            corCeu = "639BFF", corPlayer = "FFFF00", corChaoIsoladoTopo = "7845AC", corChaoIsoladoFundo = "AAD5C0" ,
            corNucleoBifurcaChaoIsoladoTopo = "45ACAC", corNucleoBifurcaChaoIsoladoFundo = "D57D29",
            corTrashBag = "F600F6", corChaoIsoladoEsquerda = "355240", corChaoIsoladoDireita = "793B34",
            corJuncaoSimplesFundoDireita = "F22778", corFundoDarkBrickEsquerdo = "646464", corFundoDarkBrickDireito = "494949",
            corWallFundo1 = "2D3425", corPredioFundo1 = "157920", corChaoIsoladoMeioVertical = "2D2C7A",
            corPedra1 = "1A3917", corVidaExtra = "97DF67", corAmmuBox = "827719", corMountainParalax = "3E7682",
            corJuncaoUmBlocoDireita = "203766", corJuncaoUmBlocoEsquerda = "364366", corJuncaoSimplesUmBlocoDuploDireita = "72CCF2",
            corJuncaoSimplesUmBlocoDuploEsquerda = "F294CB";

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



                    if (pixelAtual == montaCor(corFundoDarkBrickBase)) {
                        FundoDarkBrick fundoDarkBrick = new FundoDarkBrick(x * Entity.SIZEENTITYX, y * Entity.SIZEENTITYY, Entity.SIZEENTITYX, Entity.SIZEENTITYY, Entity.fundoDarkBrickBase, "CenarioDarkBrick");
                        Game.darkBricksFundo.add(fundoDarkBrick);
                    } else if (pixelAtual == montaCor(corFundoDarkBrickBrokenBase1)) {
                        FundoDarkBrick fundoDarkBrick = new FundoDarkBrick(x * Entity.SIZEENTITYX, y * Entity.SIZEENTITYY, Entity.SIZEENTITYX, Entity.SIZEENTITYY, Entity.fundoDarkBrickBrokenBase1, "CenarioDarkBrick");
                        Game.darkBricksFundo.add(fundoDarkBrick);
                    } else if (pixelAtual == montaCor(corFundoDarkBrickEsquerdo)) {
                        FundoDarkBrick fundoDarkBrick = new FundoDarkBrick(x * Entity.SIZEENTITYX, y * Entity.SIZEENTITYY, Entity.SIZEENTITYX, Entity.SIZEENTITYY, Entity.fundoDarkBrickEsquerdo, "CenarioDarkBrick");
                        Game.darkBricksFundo.add(fundoDarkBrick);
                    } else if (pixelAtual == montaCor(corFundoDarkBrickDireito)) {
                        FundoDarkBrick fundoDarkBrick = new FundoDarkBrick(x * Entity.SIZEENTITYX, y * Entity.SIZEENTITYY, Entity.SIZEENTITYX, Entity.SIZEENTITYY, Entity.fundoDarkBrickDireito, "CenarioDarkBrick");
                        Game.darkBricksFundo.add(fundoDarkBrick);
                    } else if (pixelAtual ==  montaCor(corPlacaSave)) {
                        CheckPoint checkPoint = new CheckPoint(x * Entity.SIZEENTITYX, y * Entity.SIZEENTITYY, Entity.SIZEENTITYX, Entity.SIZEENTITYX, Entity.save, "savePoint");
                        Game.checkPoints.add(checkPoint);
                    } else if (pixelAtual == montaCor(corEscadaTopo)) {
                        Escada escada = new Escada(x * Entity.SIZEENTITYX, y * Entity.SIZEENTITYY, Entity.SIZEENTITYX, Entity.SIZEENTITYY, 3, Entity.escadaTopo, "escada");
                        Game.escada.add(escada);
                    } else if (pixelAtual == montaCor(corEscada)) {
                        Escada escada = new Escada(x * Entity.SIZEENTITYX, y * Entity.SIZEENTITYY, Entity.SIZEENTITYX, Entity.SIZEENTITYY, 2, Entity.escada, "escada");
                        Game.escada.add(escada);
                    } else if (pixelAtual == montaCor(corEscadaBase)) {
                        Escada escada = new Escada(x * Entity.SIZEENTITYX, y * Entity.SIZEENTITYY, Entity.SIZEENTITYX, Entity.SIZEENTITYX, 1, Entity.escadaBase, "escada");
                        Game.escada.add(escada);
                    } else if (pixelAtual == montaCor(corChaoIsoladoTopo)) {
                        Solido solido = new Solido(x * Entity.SIZEENTITYX, y * Entity.SIZEENTITYY, Entity.SIZEENTITYX, Entity.SIZEENTITYY, Entity.chaoIsoladoTopo, "chaoIsoladoTopo");
                        Game.entidades.add(solido);
                    } else if (pixelAtual == montaCor(corChaoIsoladoEsquerda)) {
                        Solido solido = new Solido(x * Entity.SIZEENTITYX, y * Entity.SIZEENTITYY, Entity.SIZEENTITYX, Entity.SIZEENTITYY, Entity.chaoIsoladoEsquerda, "chaoIsoladoEsquerda");
                        Game.entidades.add(solido);
                    } else if (pixelAtual == montaCor(corChaoIsoladoDireita)) {
                        Solido solido = new Solido(x * Entity.SIZEENTITYX, y * Entity.SIZEENTITYY, Entity.SIZEENTITYX, Entity.SIZEENTITYY, Entity.chaoIsoladoDireita, "chaoIsoladoDireita");
                        Game.entidades.add(solido);
                    } else if (pixelAtual == montaCor(corJuncaoSimplesUmBlocoDuploDireita)) {
                        Solido solido = new Solido(x * Entity.SIZEENTITYX, y * Entity.SIZEENTITYY, Entity.SIZEENTITYX, Entity.SIZEENTITYY, Entity.juncaoSimplesUmBlocoDuploDireita, "juncaoSimplesUmBlocoDuploDireita");
                        Game.entidades.add(solido);
                    } else if (pixelAtual == montaCor(corJuncaoSimplesUmBlocoDuploEsquerda)) {
                        Solido solido = new Solido(x * Entity.SIZEENTITYX, y * Entity.SIZEENTITYY, Entity.SIZEENTITYX, Entity.SIZEENTITYY, Entity.juncaoSimplesUmBlocoDuploEsquerda, "juncaoSimplesUmBlocoDuploEsquerda");
                        Game.entidades.add(solido);
                    } else if (pixelAtual == montaCor(corChaoIsoladoMeioVertical)) {
                        Solido solido = new Solido(x * Entity.SIZEENTITYX, y * Entity.SIZEENTITYY, Entity.SIZEENTITYX, Entity.SIZEENTITYY, Entity.chaoIsoladoMeioVertical, "chaoIsoladoMeioVertical");
                        Game.entidades.add(solido);
                    } else if (pixelAtual == montaCor(corPedra1)) {
                        Solido solido = new Solido(x * Entity.SIZEENTITYX, y * Entity.SIZEENTITYY, Entity.SIZEENTITYX, Entity.SIZEENTITYY, Entity.pedra1, "pedra1");
                        Game.entidades.add(solido);
                    } else if (pixelAtual == montaCor(corChaoIsoladoFundo)) {
                        Solido solido = new Solido(x * Entity.SIZEENTITYX, y * Entity.SIZEENTITYY, Entity.SIZEENTITYX, Entity.SIZEENTITYY, Entity.chaoIsoladoFundo, "chaoIsoladoFundo");
                        Game.entidades.add(solido);
                    } else if (pixelAtual == montaCor(corChaoEsquerdo)) {
                        Solido solido = new Solido(x * Entity.SIZEENTITYX, y * Entity.SIZEENTITYY, Entity.SIZEENTITYX, Entity.SIZEENTITYY, Entity.chaoEsquerdo, "chaoEsquerdo");
                        Game.entidades.add(solido);
                    } else if (pixelAtual == montaCor(corChaoDireito)) {
                        Solido solido = new Solido(x * Entity.SIZEENTITYX, y * Entity.SIZEENTITYY, Entity.SIZEENTITYX, Entity.SIZEENTITYY, Entity.chaoDireito, "chaoDireito");
                        Game.entidades.add(solido);
                    } else if (pixelAtual == montaCor(corChaoEsquerdoTopo)) {
                        Solido solido = new Solido(x * Entity.SIZEENTITYX, y * Entity.SIZEENTITYY, Entity.SIZEENTITYX, Entity.SIZEENTITYY, Entity.chaoEsquerdoTopo, "chaoEsquerdoTopo");
                        Game.entidades.add(solido);
                    } else if (pixelAtual == montaCor(corChaoEsquerdoFundo)) {
                        Solido solido = new Solido(x * Entity.SIZEENTITYX, y * Entity.SIZEENTITYY, Entity.SIZEENTITYX, Entity.SIZEENTITYY, Entity.chaoEsquerdoFundo, "chaoEsquerdoFundo");
                        Game.entidades.add(solido);
                    } else if (pixelAtual == montaCor(corJuncaoBuEsquerdaBaixo)) {
                        Solido solido = new Solido(x * Entity.SIZEENTITYX, y * Entity.SIZEENTITYY, Entity.SIZEENTITYX, Entity.SIZEENTITYY, Entity.juncaoBuEsquerdaBaixo, "juncaoBuEsquerdaBaixo");
                        Game.entidades.add(solido);
                    } else if (pixelAtual == montaCor(corJuncaoBuDireitaBaixo)) {
                        Solido solido = new Solido(x * Entity.SIZEENTITYX, y * Entity.SIZEENTITYY, Entity.SIZEENTITYX, Entity.SIZEENTITYY, Entity.juncaoBuDireitaBaixo, "juncaoBuDireitaBaixo");
                        Game.entidades.add(solido);
                    } else if (pixelAtual == montaCor(corBuSimples)) {
                        Solido solido = new Solido(x * Entity.SIZEENTITYX, y * Entity.SIZEENTITYY, Entity.SIZEENTITYX, Entity.SIZEENTITYY, Entity.buSimples, "buSimples");
                        Game.entidades.add(solido);
                    } else if (pixelAtual == montaCor(corJuncaoTopoEsquerda)) {
                        Solido solido = new Solido(x * Entity.SIZEENTITYX, y * Entity.SIZEENTITYY, Entity.SIZEENTITYX, Entity.SIZEENTITYY, Entity.juncaoTopoEsquerda, "juncaoTopoEsquerda");
                        Game.entidades.add(solido);
                    } else if (pixelAtual == montaCor(corJuncaoFundoEsquerda)) {
                        Solido solido = new Solido(x * Entity.SIZEENTITYX, y * Entity.SIZEENTITYY, Entity.SIZEENTITYX, Entity.SIZEENTITYY, Entity.juncaoFundoEsquerda, "juncaoFundoEsquerda");
                        Game.entidades.add(solido);
                    } else if (pixelAtual == montaCor(corJuncaoTopoDireita)) {
                        Solido solido = new Solido(x * Entity.SIZEENTITYX, y * Entity.SIZEENTITYY, Entity.SIZEENTITYX, Entity.SIZEENTITYY, Entity.juncaoTopoDireita, "juncaoTopoDireita");
                        Game.entidades.add(solido);
                    } else if (pixelAtual == montaCor(corJuncaoUmBlocoDireita)) {
                        Solido solido = new Solido(x * Entity.SIZEENTITYX, y * Entity.SIZEENTITYY, Entity.SIZEENTITYX, Entity.SIZEENTITYY, Entity.juncaoUmBlocoDireita, "juncaoUmBlocoDireita");
                        Game.entidades.add(solido);
                    } else if (pixelAtual == montaCor(corJuncaoUmBlocoEsquerda)) {
                        Solido solido = new Solido(x * Entity.SIZEENTITYX, y * Entity.SIZEENTITYY, Entity.SIZEENTITYX, Entity.SIZEENTITYY, Entity.juncaoUmBlocoEsquerda, "juncaoUmBlocoEsquerda");
                        Game.entidades.add(solido);
                    } else if (pixelAtual == montaCor(corJuncaoFundoDireita)) {
                        Solido solido = new Solido(x * Entity.SIZEENTITYX, y * Entity.SIZEENTITYY, Entity.SIZEENTITYX, Entity.SIZEENTITYY, Entity.juncaoFundoDireita, "juncaoFundoDireita");
                        Game.entidades.add(solido);
                    } else if (pixelAtual == montaCor(corJuncaoDupla1)) {
                        Solido solido = new Solido(x * Entity.SIZEENTITYX, y * Entity.SIZEENTITYY, Entity.SIZEENTITYX, Entity.SIZEENTITYY, Entity.juncaoDupla1, "juncaoDupla1");
                        Game.entidades.add(solido);
                    } else if (pixelAtual == montaCor(corJuncaoDupla2)) {
                        Solido solido = new Solido(x * Entity.SIZEENTITYX, y * Entity.SIZEENTITYY, Entity.SIZEENTITYX, Entity.SIZEENTITYY, Entity.juncaoDupla2, "juncaoDupla2");
                        Game.entidades.add(solido);
                    } else if (pixelAtual == montaCor(corJuncaoSimplesLateralTopoDireita)) {
                        Solido solido = new Solido(x * Entity.SIZEENTITYX, y * Entity.SIZEENTITYY, Entity.SIZEENTITYX, Entity.SIZEENTITYY, Entity.juncaoSimplesLateralTopoDireita, "juncaoSimplesLateralTopoDireita");
                        Game.entidades.add(solido);
                    } else if (pixelAtual == montaCor(corJuncaoSimplesFundoDireita)) {
                        Solido solido = new Solido(x * Entity.SIZEENTITYX, y * Entity.SIZEENTITYY, Entity.SIZEENTITYX, Entity.SIZEENTITYY, Entity.juncaoSimplesFundoDireita, "juncaoSimplesFundoDireita");
                        Game.entidades.add(solido);
                    } else if (pixelAtual == montaCor(corChaoDireitoTopo)) {
                        Solido solido = new Solido(x * Entity.SIZEENTITYX, y * Entity.SIZEENTITYY, Entity.SIZEENTITYX, Entity.SIZEENTITYY, Entity.chaoDireitoTopo, "chaoDireitoTopo");
                        Game.entidades.add(solido);
                    } else if (pixelAtual == montaCor(corChaoDireitoFundo)) {
                        Solido solido = new Solido(x * Entity.SIZEENTITYX, y * Entity.SIZEENTITYY, Entity.SIZEENTITYX, Entity.SIZEENTITYY, Entity.chaoDireitoFundo, "chaoDireitoFundo");
                        Game.entidades.add(solido);
                    } else if (pixelAtual == montaCor(corChaoNucleo)) {
                        Solido solido = new Solido(x * Entity.SIZEENTITYX, y * Entity.SIZEENTITYY, Entity.SIZEENTITYX, Entity.SIZEENTITYY, Entity.chaoNucleo, "chaoNucleo");
                        Game.entidades.add(solido);
                    } else if (pixelAtual == montaCor(corChaoBaseTopo)) {
                        Solido solido = new Solido(x * Entity.SIZEENTITYX, y * Entity.SIZEENTITYY, Entity.SIZEENTITYX, Entity.SIZEENTITYY, Entity.chaoNormalTopo, "chaoNormalTopo");
                        Game.entidades.add(solido);
                    } else if (pixelAtual == montaCor(corChaoBaseFundo)) {
                        Solido solido = new Solido(x * Entity.SIZEENTITYX, y * Entity.SIZEENTITYY, Entity.SIZEENTITYX, Entity.SIZEENTITYY, Entity.chaoNormalFundo, "chaoNormalFundo");
                        Game.entidades.add(solido);
                    } else if (pixelAtual == montaCor(corNucleoConverteDireitaChaoIsoladoTopo)) {
                        Solido solido = new Solido(x * Entity.SIZEENTITYX, y * Entity.SIZEENTITYY, Entity.SIZEENTITYX, Entity.SIZEENTITYY, Entity.nucleoConverteDireitaChaoIsoladoTopo, "nucleoConverteDireitaChaoIsoladoTopo");
                        Game.entidades.add(solido);
                    } else if (pixelAtual == montaCor(corNucleoConverteDireitaChaoIsoladoFundo)) {
                        Solido solido = new Solido(x * Entity.SIZEENTITYX, y * Entity.SIZEENTITYY, Entity.SIZEENTITYX, Entity.SIZEENTITYY, Entity.nucleoConverteDireitaChaoIsoladoFundo, "nucleoConverteDireitaChaoIsoladoFundo");
                        Game.entidades.add(solido);
                    } else if (pixelAtual == montaCor(corNucleoConverteEsquerdaChaoIsoladoTopo)) {
                        Solido solido = new Solido(x * Entity.SIZEENTITYX, y * Entity.SIZEENTITYY, Entity.SIZEENTITYX, Entity.SIZEENTITYY, Entity.nucleoConverteEsquerdaChaoIsoladoTopo, "nucleoConverteEsquerdaChaoIsoladoTopo");
                        Game.entidades.add(solido);
                    } else if (pixelAtual == montaCor(corNucleoConverteEsquerdaChaoIsoladoFundo)) {
                        Solido solido = new Solido(x * Entity.SIZEENTITYX, y * Entity.SIZEENTITYY, Entity.SIZEENTITYX, Entity.SIZEENTITYY, Entity.nucleoConverteEsquerdaChaoIsoladoFundo, "nucleoConverteEsquerdaChaoIsoladoFundo");
                        Game.entidades.add(solido);
                    } else if (pixelAtual == montaCor(corNucleoBifurcaChaoIsoladoTopo)) {
                        Solido solido = new Solido(x * Entity.SIZEENTITYX, y * Entity.SIZEENTITYY, Entity.SIZEENTITYX, Entity.SIZEENTITYY, Entity.nucleoBifurcaChaoIsoladoTopo, "nucleoBifurcaChaoIsoladoTopo");
                        Game.entidades.add(solido);
                    } else if (pixelAtual == montaCor(corNucleoBifurcaChaoIsoladoFundo)) {
                        Solido solido = new Solido(x * Entity.SIZEENTITYX, y * Entity.SIZEENTITYY, Entity.SIZEENTITYX, Entity.SIZEENTITYY, Entity.nucleoBifurcaChaoIsoladoFundo, "nucleoBifurcaChaoIsoladoFundo");
                        Game.entidades.add(solido);
                    } else if (pixelAtual == montaCor(corTijoloDeserto)) {
                        Solido solido = new Solido(x * Entity.SIZEENTITYX, y * Entity.SIZEENTITYY, Entity.SIZEENTITYX, Entity.SIZEENTITYY, Entity.tijoloDeserto, "tijoloDeserto");
                        Game.entidades.add(solido);
                    } else if (pixelAtual == montaCor(corKitHealth)) {
                        KitHealth kitHealth = new KitHealth(x * Entity.SIZEENTITYX, y * Entity.SIZEENTITYY, Entity.SIZEENTITYX, Entity.SIZEENTITYY, Entity.kitHealth, "kitHealth");
                        Game.kitHealth.add(kitHealth);
                    } else if (pixelAtual == montaCor(corTrashBag)) {
                        TrashBag trashBag = new TrashBag(x * Entity.SIZEENTITYX, y * Entity.SIZEENTITYY, Entity.SIZEENTITYX, Entity.SIZEENTITYY, Entity.trashBag, "TrashBag");
                        Game.trashBags.add(trashBag);
                    } else if (pixelAtual == montaCor(corVidaExtra)) {
                        VidaExtra vidaExtra = new VidaExtra(x * Entity.SIZEENTITYX, y * Entity.SIZEENTITYY, Entity.SIZEENTITYX, Entity.SIZEENTITYY, Entity.vidaExtra, "VidaExtra");
                        Game.vidasExtras.add(vidaExtra);
                    } else if (pixelAtual == montaCor(corAmmuBox)) {
                        AmmunitionExtra ammunitionExtra = new AmmunitionExtra(x * Entity.SIZEENTITYX, y * Entity.SIZEENTITYY, Entity.SIZEENTITYX, Entity.SIZEENTITYY, Entity.ammunitionExtra, "ExtraAmmo", 5);
                        Game.ammunitionExtras.add(ammunitionExtra);
                    } else if (pixelAtual == montaCor(corGrama)) {
                        Grama grama = new Grama(x * Entity.SIZEENTITYX, y * Entity.SIZEENTITYY, Entity.SIZEENTITYX, Entity.SIZEENTITYY, Entity.grama, "Grama");
                        Game.entidades.add(grama);
                    } else if (pixelAtual == montaCor(corEspinhos)) {
                        Espinho espinho = new Espinho(x * Entity.SIZEENTITYX, y * Entity.SIZEENTITYY, Entity.SIZEENTITYX, Entity.SIZEENTITYX, Entity.espinho, "espinho");
                        Game.espinhos.add(espinho);
                    } else if (pixelAtual == montaCor(corGalhoSeco)) {
                        GalhosSecos galho = new GalhosSecos(x * Entity.SIZEENTITYX, y * Entity.SIZEENTITYY, Entity.SIZEENTITYX, Entity.SIZEENTITYX, Entity.galhoSeco, "GalhoSeco");
                        Game.entidades.add(galho);
                    } else if (pixelAtual == montaCor(corInimigo1) || pixelAtual == montaCor(corInimigo2) || pixelAtual == montaCor(corInimigo3) || pixelAtual == montaCor(corInimigo4)) {

                        // isso vai ser alterado a medida que novos inimigos aparecem no jogo
                        int tipoInimigo;
                        if (pixelAtual == montaCor(corInimigo1)) {
                            tipoInimigo = 1;
                        } else if (pixelAtual == montaCor(corInimigo2)){
                            tipoInimigo = 2;
                        } else if (pixelAtual == montaCor(corInimigo3)){
                            tipoInimigo = 3;
                        }
                        else {
                            tipoInimigo = 4;
                        }
                        // aqui é im incremento para por o inimigo no lugar correto
                        // tem que reajustar quando os inimigos passarem a ocupar 32 pixeis ao invés de 16
                        int incremento = 1;
                        if (Inimigo.SIZEENEMYX == 16) {
                            incremento = 2;
                        }
                        Inimigo inimigo = new Inimigo(identificadorUnicoInimigo, x * Inimigo.SIZEENEMYX * incremento, y * Inimigo.SIZEENEMYY * incremento, Inimigo.SIZEENEMYX, Inimigo.SIZEENEMYY, tipoInimigo, Entity.inimigo, "Inimigo");
                        identificadorUnicoInimigo++;
                        Game.inimigo.add(inimigo);
                    } else if (pixelAtual == montaCor(corCeu)) {
                        Ceu ceu = new Ceu(x * Entity.SIZEENTITYX, y * Entity.SIZEENTITYY, Entity.SIZEENTITYX, Entity.SIZEENTITYX, Entity.ceu, "ceu");
                        Game.ceuVetor.add(ceu);
                    } else if (pixelAtual == montaCor(corMountainParalax)) {
                        MountainsParalax mountainsParalax = new MountainsParalax(x * Entity.SIZEENTITYX, y * Entity.SIZEENTITYY, Entity.SIZEENTITYX, Entity.SIZEENTITYX, Entity.mountainParalax, "mountain");
                        Game.mountainVetor.add(mountainsParalax);
                    } else if (pixelAtual == montaCor(corWallFundo1)) {
                        WallFundo1 wallFundo1 = new WallFundo1(x * Entity.SIZEENTITYX, y * Entity.SIZEENTITYY, Entity.SIZEENTITYX, Entity.SIZEENTITYX, Entity.wallFundo1, "CenarioWallFundo");
                        Game.wallFundo1Vetor.add(wallFundo1);
                    } else if (pixelAtual == montaCor(corPredioFundo1)) {
                        PredioFundo1 predioFundo1 = new PredioFundo1(x * Entity.SIZEENTITYX, y * Entity.SIZEENTITYY, Entity.SIZEENTITYX, Entity.SIZEENTITYX, Entity.predioFundo1, "CenarioPredio");
                        Game.predioFundo1Vetor.add(predioFundo1);
                    } else if (pixelAtual == montaCor(corPlayer)) {
                        Game.player.setX(x * Player.getLarguraPlayer());
                        Game.player.setY(y * Player.getAlturaPlayer());
                    }
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

    // em desenvolvimento
    public int obterCor(int pixelAtual) {
        int prefixCor = 0xFF;
        String corEscadaTopo = "ee8fbe",
                corEscada = "c4759d",
                corEscadaBase = "9b5d7c",
                corChaoNucleo = "086910",
                corChaoEsquerdo = "ac4839",
                corChaoEsquerdoTopo = "18b229",
                corChaoEsquerdoFundo = "946d4a",
                corJuncaoTopoEsquerda = "ff9d52",
                corJuncaoFundoEsquerda = "ff713d",
                corJuncaoTopoDireita = "ff4551",
                corJuncaoFundoDireita = "ff73f8",
                corJuncaoDupla1 = "61ff88",
                corJuncaoDupla2 = "c8ff52",
                corChaoDireito = "ac6920",
                corChaoDireitoTopo = "CD3420",
                corChaoDireitoFundo = "6722ac",
                corChaoBaseTopo = "6a91a4",
                corChaoBaseFundo = "4a2420",
                corJuncaoSimplesLateralTopoDireita = "496372",
                corNucleoConverteDireitaChaoIsoladoTopo = "55d595",
                corNucleoConverteDireitaChaoIsoladoFundo = "d5d580",
                corNucleoConverteEsquerdaChaoIsoladoTopo = "d55555",
                corNucleoConverteEsquerdaChaoIsoladoFundo = "392420",
                corJuncaoBuEsquerdaBaixo = "585392",
                corJuncaoBuDireitaBaixo = "666663",
                corBuSimples = "666248",
                corTijoloDeserto = "ee8529",
                corKitHealth = "f6efef",
                corGrama = "38385d",
                corEspinhos = "4d8080",
                corGalhoSeco = "4d4d80",
                corPlacaSave = "808033",
                corInimigo1 = "494900",
                corInimigo2 = "606000",
                corInimigo3 = "20140C",
                corCeu = "639bff",
                corPlayer = "ffff00",
                corChaoIsoladoTopo = "7845ac",
                corChaoIsoladoFundo = "aad5c0",
                corNucleoBifurcaChaoIsoladoTopo = "45acac",
                corNucleoBifurcaChaoIsoladoFundo = "d57d29",
                corTrashBag = "f600F6",
                corChaoIsoladoEsquerda = "355240",
                corChaoIsoladoDireita = "793b34",
                corJuncaoSimplesFundoDireita = "f22778",
                corFundoDarkBrickBase = "0e5050",
                corFundoDarkBrickEsquerdo = "646464",
                corFundoDarkBrickDireito = "494949",
                corFundoDarkBrickBrokenBase1 = "0e1052",
                corWallFundo1 = "2d3425",
                corPredioFundo1 = "157920",
                corChaoIsoladoMeioVertical = "2d2c7a",
                corPedra1 = "1a3917",
                corVidaExtra = "97df67",
                corAmmuBox = "827719",
                corMountainParalax = "3e7682",
                corJuncaoUmBlocoDireita = "203766",
                corJuncaoUmBlocoEsquerda = "364366",
                corJuncaoSimplesUmBlocoDuploDireita = "72ccf2",
                corJuncaoSimplesUmBlocoDuploEsquerda = "f294cb";

        if (pixelAtual == (prefixCor << 24 | Integer.parseInt(corEscada, 16))){
            // return
            return 101010101;
        }

        //return prefixCor << 24 | Integer.parseInt(corEscada, 16);
        return 999999;
    }

    public int montaCor(String corHexaDecimal){
        int prefixCor = 0xFF;
        return prefixCor << 24 | Integer.parseInt(String.valueOf(corHexaDecimal), 16);
    }

}
