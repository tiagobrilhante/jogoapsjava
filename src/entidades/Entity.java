package entidades;

import Mundo.Camera;
import main.Game;

import java.awt.*;
import java.awt.image.BufferedImage;


// classse muito importante
// gerencia aspectos de comportamentos das entidades


public class Entity {

    // posições das entidades no sprite x, y
    static int[]
            //--------------------------//
            //          SOLIDO          //
            //--------------------------//
            posChaoNucleo = {32, 64},
            posChaoIsoladoTopo = {96, 0},
            posChaoIsoladoFundo = {96, 96},
            posNucleoBifurcaChaoIsoladoTopo = {96, 32},
            posNucleoBifurcaChaoIsoladoFundo = {96, 64},
            posNucleoConverteDireitaChaoIsoladoTopo = {128, 32},
            posNucleoConverteEsquerdaChaoIsoladoTopo = {160, 32},
            posNucleoConverteDireitaChaoIsoladoFundo = {128, 64},
            posNucleoConverteEsquerdaChaoIsoladoFundo = {160, 64},
            posChaoEsquerdoTopo = {0, 32},
            posChaoEsquerdoCavernaTopo = {320, 32},
            posChaoEsquerdo = {0, 64},
            posChaoEsquerdoCaverna = {320, 64},
            posChaoDireitoTopo = {64, 32},
            posChaoDireitoCavernaTopo = {384, 32},
            posChaoDireito = {64, 64},
            posChaoDireitoCaverna = {384, 64},
            posChaoNormalTopo = {32, 32},
            posChaoNormalMontanhaTopo = {352, 32},
            posChaoNormalFundo = {32, 96},
            posChaoNormalMontanhaFundo = {352, 96},
            posJuncaoBuEsquerdaBaixo = {224, 96},
            posJuncaoBuDireitaBaixo = {288, 96},
            posBuSimples = {256, 96},
            posChaoEsquerdoFundo = {0, 96},
            posChaoEsquerdoCavernaFundo = {320, 96},
            posChaoDireitoFundo = {64, 96},
            posChaoDireitoCavernaFundo = {384, 96},
            posTijoloDeserto = {32, 0},
            posJuncaoTopoEsquerda = {192, 32},
            posJuncaoFundoEsquerda = {192, 64},
            posJuncaoTopoDireita = {224, 32},
            posJuncaoFundoDireita = {224, 64},
            posJuncaoDupla1 = {256, 32},
            posJuncaoDupla2 = {256, 64},
            posJuncaoSimplesLateralTopoDireita = {192, 96},
            posChaoIsoladoEsquerda = {160, 0},
            posChaoIsoladoDireita = {128, 0},
            posJuncaoSimplesFundoDireita = {64, 0},
            posJuncaoSimplesFundoDireitaCaverna = {320, 0},
            posChaoIsoladoMeioVertical = {160, 96},
            posPedra1 = {128, 96},
            posJuncaoSimplesUmBlocoDuploDireita = {192, 0},
            posJuncaoSimplesUmBlocoDuploEsquerda = {224, 0},
            posJuncaoSimplesUmBlocoDuploDireitaCaverna = {352, 0},
            posJuncaoSimplesUmBlocoDuploEsquerdaCaverna = {384, 0},
            posJuncaoUmBlocoDireita = {288, 32},
            posJuncaoUmBlocoEsquerda = {288, 64},
            posEmpty = {0, 0},
    //--------------------------//
    //        DECORATION        //
    //--------------------------//
    posGrama = {0, 64},
            posGalhoSeco = {0, 96},
            posFundoDarkBrickEsquerdo = {0, 0},
            posFundoDarkBrickDireito = {64, 0},
            posFundoDarkBrickBase = {32, 0},
            posFundoDarkBrickBrokenBase1 = {0, 32},
            posFundoCaverna1 = {96, 0},
            posFundoCavernaEntradaEsquerda = {128, 0},
            posFundoCavernaEntradaDireita = {160, 0},
    //--------------------------//
    //        INTERATIVE        //
    //--------------------------//
    posEscadaTopo = {0, 0},
            posEscada = {32, 0},
            posEscadaBase = {64, 0},
            posEspinho = {0, 96},
            posKitHealth = {32, 32},
            posSavePoint = {0, 64},
            posTrashBag = {0, 32},
            posVidaExtra = {64, 32},
            posAmmunitionExtra = {96, 32};
    // ajustar ceu e nuvens
    // ajustar inimigo

    public static int SIZEENTITYX = 32, SIZEENTITYY = 32;

    // buffer de todas as entidades e seus posicionamentos no sprite)

    //-------------------//
    //      SOLIDOS      //
    //-------------------//

    public static BufferedImage chaoNucleo = Game.spriteSolid.getSprite(posChaoNucleo[0], posChaoNucleo[1], SIZEENTITYX, SIZEENTITYY);
    public static BufferedImage chaoIsoladoTopo = Game.spriteSolid.getSprite(posChaoIsoladoTopo[0], posChaoIsoladoTopo[1], SIZEENTITYX, SIZEENTITYY);
    public static BufferedImage chaoIsoladoEsquerda = Game.spriteSolid.getSprite(posChaoIsoladoEsquerda[0], posChaoIsoladoEsquerda[1], SIZEENTITYX, SIZEENTITYY);
    public static BufferedImage chaoIsoladoDireita = Game.spriteSolid.getSprite(posChaoIsoladoDireita[0], posChaoIsoladoDireita[1], SIZEENTITYX, SIZEENTITYY);
    public static BufferedImage juncaoSimplesUmBlocoDuploDireita = Game.spriteSolid.getSprite(posJuncaoSimplesUmBlocoDuploDireita[0], posJuncaoSimplesUmBlocoDuploDireita[1], SIZEENTITYX, SIZEENTITYY);
    public static BufferedImage juncaoSimplesUmBlocoDuploDireitaCaverna = Game.spriteSolid.getSprite(posJuncaoSimplesUmBlocoDuploDireitaCaverna[0], posJuncaoSimplesUmBlocoDuploDireitaCaverna[1], SIZEENTITYX, SIZEENTITYY);
    public static BufferedImage juncaoSimplesUmBlocoDuploEsquerda = Game.spriteSolid.getSprite(posJuncaoSimplesUmBlocoDuploEsquerda[0], posJuncaoSimplesUmBlocoDuploEsquerda[1], SIZEENTITYX, SIZEENTITYY);
    public static BufferedImage juncaoSimplesUmBlocoDuploEsquerdaCaverna = Game.spriteSolid.getSprite(posJuncaoSimplesUmBlocoDuploEsquerdaCaverna[0], posJuncaoSimplesUmBlocoDuploEsquerdaCaverna[1], SIZEENTITYX, SIZEENTITYY);
    public static BufferedImage chaoIsoladoFundo = Game.spriteSolid.getSprite(posChaoIsoladoFundo[0], posChaoIsoladoFundo[1], SIZEENTITYX, SIZEENTITYY);
    public static BufferedImage nucleoBifurcaChaoIsoladoTopo = Game.spriteSolid.getSprite(posNucleoBifurcaChaoIsoladoTopo[0], posNucleoBifurcaChaoIsoladoTopo[1], SIZEENTITYX, SIZEENTITYY);
    public static BufferedImage nucleoBifurcaChaoIsoladoFundo = Game.spriteSolid.getSprite(posNucleoBifurcaChaoIsoladoFundo[0], posNucleoBifurcaChaoIsoladoFundo[1], SIZEENTITYX, SIZEENTITYY);
    public static BufferedImage nucleoConverteDireitaChaoIsoladoTopo = Game.spriteSolid.getSprite(posNucleoConverteDireitaChaoIsoladoTopo[0], posNucleoConverteDireitaChaoIsoladoTopo[1], SIZEENTITYX, SIZEENTITYY);
    public static BufferedImage nucleoConverteEsquerdaChaoIsoladoTopo = Game.spriteSolid.getSprite(posNucleoConverteEsquerdaChaoIsoladoTopo[0], posNucleoConverteEsquerdaChaoIsoladoTopo[1], SIZEENTITYX, SIZEENTITYY);
    public static BufferedImage nucleoConverteDireitaChaoIsoladoFundo = Game.spriteSolid.getSprite(posNucleoConverteDireitaChaoIsoladoFundo[0], posNucleoConverteDireitaChaoIsoladoTopo[1], SIZEENTITYX, SIZEENTITYY);
    public static BufferedImage nucleoConverteEsquerdaChaoIsoladoFundo = Game.spriteSolid.getSprite(posNucleoConverteEsquerdaChaoIsoladoFundo[0], posNucleoConverteEsquerdaChaoIsoladoTopo[1], SIZEENTITYX, SIZEENTITYY);
    public static BufferedImage chaoIsoladoMeioVertical = Game.spriteSolid.getSprite(posChaoIsoladoMeioVertical[0], posChaoIsoladoMeioVertical[1], SIZEENTITYX, SIZEENTITYY);
    public static BufferedImage pedra1 = Game.spriteSolid.getSprite(posPedra1[0], posPedra1[1], SIZEENTITYX, SIZEENTITYY);
    public static BufferedImage chaoEsquerdoTopo = Game.spriteSolid.getSprite(posChaoEsquerdoTopo[0], posChaoEsquerdoTopo[1], SIZEENTITYX, SIZEENTITYY);
    public static BufferedImage chaoEsquerdoCavernaTopo = Game.spriteSolid.getSprite(posChaoEsquerdoCavernaTopo[0], posChaoEsquerdoCavernaTopo[1], SIZEENTITYX, SIZEENTITYY);
    public static BufferedImage chaoEsquerdo = Game.spriteSolid.getSprite(posChaoEsquerdo[0], posChaoEsquerdo[1], SIZEENTITYX, SIZEENTITYY);
    public static BufferedImage chaoEsquerdoCaverna = Game.spriteSolid.getSprite(posChaoEsquerdoCaverna[0], posChaoEsquerdoCaverna[1], SIZEENTITYX, SIZEENTITYY);
    public static BufferedImage chaoDireitoTopo = Game.spriteSolid.getSprite(posChaoDireitoTopo[0], posChaoDireitoTopo[1], SIZEENTITYX, SIZEENTITYY);
    public static BufferedImage chaoDireitoCavernaTopo = Game.spriteSolid.getSprite(posChaoDireitoCavernaTopo[0], posChaoDireitoCavernaTopo[1], SIZEENTITYX, SIZEENTITYY);
    public static BufferedImage chaoDireito = Game.spriteSolid.getSprite(posChaoDireito[0], posChaoDireito[1], SIZEENTITYX, SIZEENTITYY);
    public static BufferedImage chaoDireitoCaverna = Game.spriteSolid.getSprite(posChaoDireitoCaverna[0], posChaoDireitoCaverna[1], SIZEENTITYX, SIZEENTITYY);
    public static BufferedImage chaoNormalTopo = Game.spriteSolid.getSprite(posChaoNormalTopo[0], posChaoNormalTopo[1], SIZEENTITYX, SIZEENTITYY);
    public static BufferedImage chaoNormalMontanhaTopo = Game.spriteSolid.getSprite(posChaoNormalMontanhaTopo[0], posChaoNormalMontanhaTopo[1], SIZEENTITYX, SIZEENTITYY);
    public static BufferedImage chaoNormalFundo = Game.spriteSolid.getSprite(posChaoNormalFundo[0], posChaoNormalFundo[1], SIZEENTITYX, SIZEENTITYY);
    public static BufferedImage chaoNormalMontanhaFundo = Game.spriteSolid.getSprite(posChaoNormalMontanhaFundo[0], posChaoNormalMontanhaFundo[1], SIZEENTITYX, SIZEENTITYY);
    public static BufferedImage chaoDireitoFundo = Game.spriteSolid.getSprite(posChaoDireitoFundo[0], posChaoDireitoFundo[1], SIZEENTITYX, SIZEENTITYY);
    public static BufferedImage chaoDireitoCavernaFundo = Game.spriteSolid.getSprite(posChaoDireitoCavernaFundo[0], posChaoDireitoCavernaFundo[1], SIZEENTITYX, SIZEENTITYY);
    public static BufferedImage chaoEsquerdoFundo = Game.spriteSolid.getSprite(posChaoEsquerdoFundo[0], posChaoEsquerdoFundo[1], SIZEENTITYX, SIZEENTITYY);
    public static BufferedImage chaoEsquerdoCavernaFundo = Game.spriteSolid.getSprite(posChaoEsquerdoCavernaFundo[0], posChaoEsquerdoCavernaFundo[1], SIZEENTITYX, SIZEENTITYY);
    public static BufferedImage juncaoTopoEsquerda = Game.spriteSolid.getSprite(posJuncaoTopoEsquerda[0], posJuncaoTopoEsquerda[1], SIZEENTITYX, SIZEENTITYY);
    public static BufferedImage juncaoFundoEsquerda = Game.spriteSolid.getSprite(posJuncaoFundoEsquerda[0], posJuncaoFundoEsquerda[1], SIZEENTITYX, SIZEENTITYY);
    public static BufferedImage juncaoTopoDireita = Game.spriteSolid.getSprite(posJuncaoTopoDireita[0], posJuncaoTopoDireita[1], SIZEENTITYX, SIZEENTITYY);
    public static BufferedImage juncaoFundoDireita = Game.spriteSolid.getSprite(posJuncaoFundoDireita[0], posJuncaoFundoDireita[1], SIZEENTITYX, SIZEENTITYY);
    public static BufferedImage juncaoBuEsquerdaBaixo = Game.spriteSolid.getSprite(posJuncaoBuEsquerdaBaixo[0], posJuncaoBuEsquerdaBaixo[1], SIZEENTITYX, SIZEENTITYY);
    public static BufferedImage juncaoBuDireitaBaixo = Game.spriteSolid.getSprite(posJuncaoBuDireitaBaixo[0], posJuncaoBuDireitaBaixo[1], SIZEENTITYX, SIZEENTITYY);
    public static BufferedImage buSimples = Game.spriteSolid.getSprite(posBuSimples[0], posBuSimples[1], SIZEENTITYX, SIZEENTITYY);
    public static BufferedImage juncaoDupla1 = Game.spriteSolid.getSprite(posJuncaoDupla1[0], posJuncaoDupla1[1], SIZEENTITYX, SIZEENTITYY);
    public static BufferedImage juncaoDupla2 = Game.spriteSolid.getSprite(posJuncaoDupla2[0], posJuncaoDupla2[1], SIZEENTITYX, SIZEENTITYY);
    public static BufferedImage juncaoUmBlocoDireita = Game.spriteSolid.getSprite(posJuncaoUmBlocoDireita[0], posJuncaoUmBlocoDireita[1], SIZEENTITYX, SIZEENTITYY);
    public static BufferedImage juncaoUmBlocoEsquerda = Game.spriteSolid.getSprite(posJuncaoUmBlocoEsquerda[0], posJuncaoUmBlocoEsquerda[1], SIZEENTITYX, SIZEENTITYY);
    public static BufferedImage juncaoSimplesLateralTopoDireita = Game.spriteSolid.getSprite(posJuncaoSimplesLateralTopoDireita[0], posJuncaoSimplesLateralTopoDireita[1], SIZEENTITYX, SIZEENTITYY);
    public static BufferedImage juncaoSimplesFundoDireita = Game.spriteSolid.getSprite(posJuncaoSimplesFundoDireita[0], posJuncaoSimplesFundoDireita[1], SIZEENTITYX, SIZEENTITYY);
    public static BufferedImage juncaoSimplesFundoDireitaCaverna = Game.spriteSolid.getSprite(posJuncaoSimplesFundoDireitaCaverna[0], posJuncaoSimplesFundoDireitaCaverna[1], SIZEENTITYX, SIZEENTITYY);
    public static BufferedImage tijoloDeserto = Game.spriteSolid.getSprite(posTijoloDeserto[0], posTijoloDeserto[1], SIZEENTITYX, SIZEENTITYY);
    public static BufferedImage empty = Game.spriteSolid.getSprite(posEmpty[0], posEmpty[1], SIZEENTITYX, SIZEENTITYY);

    //-----------------------//
    //      DECORATIVOS      //
    //-----------------------//

    public static BufferedImage ceu = Game.ceu.getSprite(0, 0, 32, 800);
    public static BufferedImage mountainParalax = Game.mountain.getSprite(0, 0, 3200, 800);
    public static BufferedImage wallFundo1 = Game.spriteDecoration.getSprite(240, 128, 224, 128);
    public static BufferedImage luzWallFundo1 = Game.spriteDecoration.getSprite(320, 64, 64, 64);
    public static BufferedImage predioFundo1 = Game.spriteDecoration.getSprite(0, 128, 224, 300);
    public static BufferedImage nuvens = Game.nuvens.getSprite(0, 0, 1471, 700);
    public static BufferedImage grama = Game.spriteDecoration.getSprite(posGrama[0], posGrama[1], SIZEENTITYX, SIZEENTITYY);
    public static BufferedImage galhoSeco = Game.spriteDecoration.getSprite(posGalhoSeco[0], posGalhoSeco[1], SIZEENTITYX, SIZEENTITYY);
    public static BufferedImage fundoDarkBrickBrokenBase1 = Game.spriteDecoration.getSprite(posFundoDarkBrickBrokenBase1[0], posFundoDarkBrickBrokenBase1[1], SIZEENTITYX, SIZEENTITYY);
    public static BufferedImage fundoDarkBrickEsquerdo = Game.spriteDecoration.getSprite(posFundoDarkBrickEsquerdo[0], posFundoDarkBrickEsquerdo[1], SIZEENTITYX, SIZEENTITYY);
    public static BufferedImage fundoDarkBrickDireito = Game.spriteDecoration.getSprite(posFundoDarkBrickDireito[0], posFundoDarkBrickDireito[1], SIZEENTITYX, SIZEENTITYY);
    public static BufferedImage fundoDarkBrickBase = Game.spriteDecoration.getSprite(posFundoDarkBrickBase[0], posFundoDarkBrickBase[1], SIZEENTITYX, SIZEENTITYY);
    public static BufferedImage fundoCaverna1 = Game.spriteDecoration.getSprite(posFundoCaverna1[0], posFundoCaverna1[1], SIZEENTITYX, SIZEENTITYY);
    public static BufferedImage fundoCavernaEntradaEsquerda = Game.spriteDecoration.getSprite(posFundoCavernaEntradaEsquerda[0], posFundoCavernaEntradaEsquerda[1], SIZEENTITYX, SIZEENTITYY);
    public static BufferedImage fundoCavernaEntradaDireita = Game.spriteDecoration.getSprite(posFundoCavernaEntradaDireita[0], posFundoCavernaEntradaDireita[1], SIZEENTITYX, SIZEENTITYY);

    //-----------------------//
    //      INTERATIVOS      //
    //-----------------------//

    // INIMIGO
    public static BufferedImage inimigo = Game.spriteEnemy.getSprite(0, 48, SIZEENTITYX, SIZEENTITYY);
    //OUTROS
    public static BufferedImage escadaTopo = Game.spriteInterative.getSprite(posEscadaTopo[0], posEscadaTopo[1], SIZEENTITYX, SIZEENTITYY);
    public static BufferedImage escada = Game.spriteInterative.getSprite(posEscada[0], posEscada[1], SIZEENTITYX, SIZEENTITYY);
    public static BufferedImage escadaBase = Game.spriteInterative.getSprite(posEscadaBase[0], posEscadaBase[1], SIZEENTITYX, SIZEENTITYY);
    public static BufferedImage espinho = Game.spriteInterative.getSprite(posEspinho[0], posEspinho[1], SIZEENTITYX, SIZEENTITYY);
    public static BufferedImage kitHealth = Game.spriteInterative.getSprite(posKitHealth[0], posKitHealth[1], SIZEENTITYX, SIZEENTITYY);
    public static BufferedImage trashBag = Game.spriteInterative.getSprite(posTrashBag[0], posTrashBag[1], SIZEENTITYX, SIZEENTITYY);
    public static BufferedImage vidaExtra = Game.spriteInterative.getSprite(posVidaExtra[0], posVidaExtra[1], SIZEENTITYX, SIZEENTITYY);
    public static BufferedImage checkPoint = Game.spriteInterative.getSprite(posSavePoint[0], posSavePoint[1], SIZEENTITYX, SIZEENTITYY);
    public static BufferedImage ammunitionExtra = Game.spriteInterative.getSprite(posAmmunitionExtra[0], posAmmunitionExtra[1], SIZEENTITYX, SIZEENTITYY);


    // posicionamento das entidades (public)
    public double x, y;
    // tamanho
    protected int width, height;
    //sprite
    protected BufferedImage sprite;

    public String tipo;

    // construtor
    public Entity(int x, int y, int width, int height, BufferedImage sprite, String tipo) {
        this.x = x;
        this.y = y;
        this.tipo = tipo;
        this.width = width;
        this.height = height;
        this.sprite = sprite;
    }

    // ticks de entidades (repassam o comportamento para as entidades específicas)
    public void tick() {

    }

    // render das entidades (em relação ao player)
    public void render(Graphics g) {

        g.drawImage(sprite, this.getX() - Camera.x, this.getY() - Camera.y, null);

    }

    // getters and setters

    public void setX(int newX) {
        this.x = newX;
    }

    public void setY(int newY) {
        this.y = newY;
    }

    public int getX() {
        return (int) this.x;
    }

    public int getY() {
        return (int) this.y;
    }

    public int getWidth() {
        return this.width;
    }

    public int getHeight() {
        return this.height;
    }

    @Override
    public String toString() {
        return "Entity{" +
                "x=" + x +
                ", y=" + y +
                ", width=" + width +
                ", height=" + height +
                ", sprite=" + sprite +
                ", tipo=" + tipo +
                '}';
    }
}
