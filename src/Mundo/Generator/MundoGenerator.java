package Mundo.Generator;

import entidades.Entity;
import entidades.interativos.*;
import entidades.naoSolidos.*;
import entidades.player.Player;
import entidades.solidos.Solido;
import main.Game;

public class MundoGenerator {

    static int identificadorUnicoInimigo = 1;

    public static final String
            corFundoDarkBrickBase = "0E5050";
    public static final String corEscadaTopo = "EE8FBE";
    public  static final String corFundoDarkBrickBrokenBase1 = "0E1052";
    public static final String corEscada = "C4759D";
    public static final String corEscadaBase = "9B5D7C";
    public static final String corChaoNucleo = "086910";
    public static final String corChaoEsquerdo = "AC4839";
    public static final String corChaoEsquerdoTopo = "18B229";
    public static final String corChaoEsquerdoFundo = "946d4A";
    public static final String corJuncaoTopoEsquerda = "FF9D52";
    public static final String corJuncaoFundoEsquerda = "FF713D";
    public static final String corJuncaoTopoDireita = "FF4551";
    public static final String corJuncaoFundoDireita = "FF73F8";
    public static final String corJuncaoDupla1 = "61FF88";
    public static final String corJuncaoDupla2 = "C8FF52";
    public static final String corChaoDireito = "AC6920";
    public static final String corChaoDireitoTopo = "CD3420";
    public static final String corChaoDireitoFundo = "6722AC";
    public static final String corChaoBaseTopo = "6A91A4";
    public static final String corChaoBaseFundo = "4A2420";
    public static final String corJuncaoSimplesLateralTopoDireita = "496372";
    public static final String corNucleoConverteDireitaChaoIsoladoTopo = "55D595";
    public static final String corNucleoConverteDireitaChaoIsoladoFundo = "D5D580";
    public static final String corNucleoConverteEsquerdaChaoIsoladoTopo = "D55555";
    public static final String corNucleoConverteEsquerdaChaoIsoladoFundo = "392420";
    public static final String corJuncaoBuEsquerdaBaixo = "585392";
    public static final String corJuncaoBuDireitaBaixo = "666663";
    public static final String corBuSimples = "666248";
    public static final String corTijoloDeserto = "EE8529";
    public static final String corKitHealth = "F6EFEF";
    public static final String corGrama = "38385D";
    public static final String corEspinhos = "4D8080";
    public static final String corGalhoSeco = "4D4D80";
    public static final String corPlacaSave = "808033";
    public static final String corInimigo1 = "494900";
    public static final String corInimigo2 = "606000";
    public static final String corInimigo3 = "20140C";
    public static final String corInimigo4 = "A88F39";
    public static final String corCeu = "639BFF";
    public static final String corPlayer = "FFFF00";
    public static final String corChaoIsoladoTopo = "7845AC";
    public static final String corChaoIsoladoFundo = "AAD5C0";
    public static final String corNucleoBifurcaChaoIsoladoTopo = "45ACAC";
    public static final String corNucleoBifurcaChaoIsoladoFundo = "D57D29";
    public final static String corTrashBag = "F600F6";
    public final static String corChaoIsoladoEsquerda = "355240";
    public final static String corChaoIsoladoDireita = "793B34";
    public final static String corJuncaoSimplesFundoDireita = "F22778";
    public final static String corFundoDarkBrickEsquerdo = "646464";
    public final static String corFundoDarkBrickDireito = "494949";
    public final static String corWallFundo1 = "2D3425";
    public final static String corPredioFundo1 = "157920";
    public final static String corChaoIsoladoMeioVertical = "2D2C7A";
    public final static String corPedra1 = "1A3917";
    public final static String corVidaExtra = "97DF67";
    public final static String corAmmuBox = "827719";
    public final static String corMountainParalax = "3E7682";
    public final static String corJuncaoUmBlocoDireita = "203766";
    public final static String corJuncaoUmBlocoEsquerda = "364366";
    public final static String corJuncaoSimplesUmBlocoDuploDireita = "72CCF2";
    public final static String corJuncaoSimplesUmBlocoDuploEsquerda = "F294CB";




    public static void geraEntidades (int x, int y, int pixelAtual){

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



    public static int montaCor(String corHexaDecimal){
        int prefixCor = 0xFF;
        return prefixCor << 24 | Integer.parseInt(String.valueOf(corHexaDecimal), 16);
    }

}
