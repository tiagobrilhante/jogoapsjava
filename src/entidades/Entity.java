package entidades;

import Mundo.Camera;
import main.Game;
import settings.GameSettings;

import java.awt.*;
import java.awt.image.BufferedImage;


// classse muito importante
// gerencia aspectos de comportamentos das entidades


public class Entity {

    public static int SIZEENTITYX = 32, SIZEENTITYY = 32;

    // buffer de todas as entidades e seus posicionamentos no sprite)

    //-------------------//
    //      SOLIDOS      //
    //-------------------//

    public static BufferedImage chaoNucleo = Game.spriteSolid.getSprite(GameSettings.posChaoNucleo[0], GameSettings.posChaoNucleo[1], SIZEENTITYX, SIZEENTITYY);
    public static BufferedImage chaoIsoladoTopo = Game.spriteSolid.getSprite(GameSettings.posChaoIsoladoTopo[0], GameSettings.posChaoIsoladoTopo[1], SIZEENTITYX, SIZEENTITYY);
    public static BufferedImage chaoIsoladoEsquerda = Game.spriteSolid.getSprite(GameSettings.posChaoIsoladoEsquerda[0], GameSettings.posChaoIsoladoEsquerda[1], SIZEENTITYX, SIZEENTITYY);
    public static BufferedImage chaoIsoladoDireita = Game.spriteSolid.getSprite(GameSettings.posChaoIsoladoDireita[0], GameSettings.posChaoIsoladoDireita[1], SIZEENTITYX, SIZEENTITYY);
    public static BufferedImage juncaoSimplesUmBlocoDuploDireita = Game.spriteSolid.getSprite(GameSettings.posJuncaoSimplesUmBlocoDuploDireita[0], GameSettings.posJuncaoSimplesUmBlocoDuploDireita[1], SIZEENTITYX, SIZEENTITYY);
    public static BufferedImage juncaoSimplesUmBlocoDuploDireitaCaverna = Game.spriteSolid.getSprite(GameSettings.posJuncaoSimplesUmBlocoDuploDireitaCaverna[0], GameSettings.posJuncaoSimplesUmBlocoDuploDireitaCaverna[1], SIZEENTITYX, SIZEENTITYY);
    public static BufferedImage juncaoSimplesUmBlocoDuploEsquerda = Game.spriteSolid.getSprite(GameSettings.posJuncaoSimplesUmBlocoDuploEsquerda[0], GameSettings.posJuncaoSimplesUmBlocoDuploEsquerda[1], SIZEENTITYX, SIZEENTITYY);
    public static BufferedImage juncaoSimplesUmBlocoDuploEsquerdaCaverna = Game.spriteSolid.getSprite(GameSettings.posJuncaoSimplesUmBlocoDuploEsquerdaCaverna[0], GameSettings.posJuncaoSimplesUmBlocoDuploEsquerdaCaverna[1], SIZEENTITYX, SIZEENTITYY);
    public static BufferedImage chaoIsoladoFundo = Game.spriteSolid.getSprite(GameSettings.posChaoIsoladoFundo[0], GameSettings.posChaoIsoladoFundo[1], SIZEENTITYX, SIZEENTITYY);
    public static BufferedImage nucleoBifurcaChaoIsoladoTopo = Game.spriteSolid.getSprite(GameSettings.posNucleoBifurcaChaoIsoladoTopo[0], GameSettings.posNucleoBifurcaChaoIsoladoTopo[1], SIZEENTITYX, SIZEENTITYY);
    public static BufferedImage nucleoBifurcaChaoIsoladoFundo = Game.spriteSolid.getSprite(GameSettings.posNucleoBifurcaChaoIsoladoFundo[0], GameSettings.posNucleoBifurcaChaoIsoladoFundo[1], SIZEENTITYX, SIZEENTITYY);
    public static BufferedImage nucleoConverteDireitaChaoIsoladoTopo = Game.spriteSolid.getSprite(GameSettings.posNucleoConverteDireitaChaoIsoladoTopo[0], GameSettings.posNucleoConverteDireitaChaoIsoladoTopo[1], SIZEENTITYX, SIZEENTITYY);
    public static BufferedImage nucleoConverteEsquerdaChaoIsoladoTopo = Game.spriteSolid.getSprite(GameSettings.posNucleoConverteEsquerdaChaoIsoladoTopo[0], GameSettings.posNucleoConverteEsquerdaChaoIsoladoTopo[1], SIZEENTITYX, SIZEENTITYY);
    public static BufferedImage nucleoConverteDireitaChaoIsoladoFundo = Game.spriteSolid.getSprite(GameSettings.posNucleoConverteDireitaChaoIsoladoFundo[0], GameSettings.posNucleoConverteDireitaChaoIsoladoTopo[1], SIZEENTITYX, SIZEENTITYY);
    public static BufferedImage nucleoConverteEsquerdaChaoIsoladoFundo = Game.spriteSolid.getSprite(GameSettings.posNucleoConverteEsquerdaChaoIsoladoFundo[0], GameSettings.posNucleoConverteEsquerdaChaoIsoladoTopo[1], SIZEENTITYX, SIZEENTITYY);
    public static BufferedImage chaoIsoladoMeioVertical = Game.spriteSolid.getSprite(GameSettings.posChaoIsoladoMeioVertical[0], GameSettings.posChaoIsoladoMeioVertical[1], SIZEENTITYX, SIZEENTITYY);
    public static BufferedImage pedra1 = Game.spriteSolid.getSprite(GameSettings.posPedra1[0], GameSettings.posPedra1[1], SIZEENTITYX, SIZEENTITYY);
    public static BufferedImage chaoEsquerdoTopo = Game.spriteSolid.getSprite(GameSettings.posChaoEsquerdoTopo[0], GameSettings.posChaoEsquerdoTopo[1], SIZEENTITYX, SIZEENTITYY);
    public static BufferedImage chaoEsquerdoCavernaTopo = Game.spriteSolid.getSprite(GameSettings.posChaoEsquerdoCavernaTopo[0], GameSettings.posChaoEsquerdoCavernaTopo[1], SIZEENTITYX, SIZEENTITYY);
    public static BufferedImage chaoEsquerdo = Game.spriteSolid.getSprite(GameSettings.posChaoEsquerdo[0], GameSettings.posChaoEsquerdo[1], SIZEENTITYX, SIZEENTITYY);
    public static BufferedImage chaoEsquerdoCaverna = Game.spriteSolid.getSprite(GameSettings.posChaoEsquerdoCaverna[0], GameSettings.posChaoEsquerdoCaverna[1], SIZEENTITYX, SIZEENTITYY);
    public static BufferedImage chaoDireitoTopo = Game.spriteSolid.getSprite(GameSettings.posChaoDireitoTopo[0], GameSettings.posChaoDireitoTopo[1], SIZEENTITYX, SIZEENTITYY);
    public static BufferedImage chaoDireitoCavernaTopo = Game.spriteSolid.getSprite(GameSettings.posChaoDireitoCavernaTopo[0], GameSettings.posChaoDireitoCavernaTopo[1], SIZEENTITYX, SIZEENTITYY);
    public static BufferedImage chaoDireito = Game.spriteSolid.getSprite(GameSettings.posChaoDireito[0], GameSettings.posChaoDireito[1], SIZEENTITYX, SIZEENTITYY);
    public static BufferedImage chaoDireitoCaverna = Game.spriteSolid.getSprite(GameSettings.posChaoDireitoCaverna[0], GameSettings.posChaoDireitoCaverna[1], SIZEENTITYX, SIZEENTITYY);
    public static BufferedImage chaoNormalTopo = Game.spriteSolid.getSprite(GameSettings.posChaoNormalTopo[0], GameSettings.posChaoNormalTopo[1], SIZEENTITYX, SIZEENTITYY);
    public static BufferedImage chaoNormalMontanhaTopo = Game.spriteSolid.getSprite(GameSettings.posChaoNormalMontanhaTopo[0], GameSettings.posChaoNormalMontanhaTopo[1], SIZEENTITYX, SIZEENTITYY);
    public static BufferedImage chaoNormalFundo = Game.spriteSolid.getSprite(GameSettings.posChaoNormalFundo[0], GameSettings.posChaoNormalFundo[1], SIZEENTITYX, SIZEENTITYY);
    public static BufferedImage chaoNormalMontanhaFundo = Game.spriteSolid.getSprite(GameSettings.posChaoNormalMontanhaFundo[0], GameSettings.posChaoNormalMontanhaFundo[1], SIZEENTITYX, SIZEENTITYY);
    public static BufferedImage chaoDireitoFundo = Game.spriteSolid.getSprite(GameSettings.posChaoDireitoFundo[0], GameSettings.posChaoDireitoFundo[1], SIZEENTITYX, SIZEENTITYY);
    public static BufferedImage chaoDireitoCavernaFundo = Game.spriteSolid.getSprite(GameSettings.posChaoDireitoCavernaFundo[0], GameSettings.posChaoDireitoCavernaFundo[1], SIZEENTITYX, SIZEENTITYY);
    public static BufferedImage chaoEsquerdoFundo = Game.spriteSolid.getSprite(GameSettings.posChaoEsquerdoFundo[0], GameSettings.posChaoEsquerdoFundo[1], SIZEENTITYX, SIZEENTITYY);
    public static BufferedImage chaoEsquerdoCavernaFundo = Game.spriteSolid.getSprite(GameSettings.posChaoEsquerdoCavernaFundo[0], GameSettings.posChaoEsquerdoCavernaFundo[1], SIZEENTITYX, SIZEENTITYY);
    public static BufferedImage juncaoTopoEsquerda = Game.spriteSolid.getSprite(GameSettings.posJuncaoTopoEsquerda[0], GameSettings.posJuncaoTopoEsquerda[1], SIZEENTITYX, SIZEENTITYY);
    public static BufferedImage juncaoFundoEsquerda = Game.spriteSolid.getSprite(GameSettings.posJuncaoFundoEsquerda[0], GameSettings.posJuncaoFundoEsquerda[1], SIZEENTITYX, SIZEENTITYY);
    public static BufferedImage juncaoTopoDireita = Game.spriteSolid.getSprite(GameSettings.posJuncaoTopoDireita[0], GameSettings.posJuncaoTopoDireita[1], SIZEENTITYX, SIZEENTITYY);
    public static BufferedImage juncaoFundoDireita = Game.spriteSolid.getSprite(GameSettings.posJuncaoFundoDireita[0], GameSettings.posJuncaoFundoDireita[1], SIZEENTITYX, SIZEENTITYY);
    public static BufferedImage juncaoBuEsquerdaBaixo = Game.spriteSolid.getSprite(GameSettings.posJuncaoBuEsquerdaBaixo[0], GameSettings.posJuncaoBuEsquerdaBaixo[1], SIZEENTITYX, SIZEENTITYY);
    public static BufferedImage juncaoBuDireitaBaixo = Game.spriteSolid.getSprite(GameSettings.posJuncaoBuDireitaBaixo[0], GameSettings.posJuncaoBuDireitaBaixo[1], SIZEENTITYX, SIZEENTITYY);
    public static BufferedImage buSimples = Game.spriteSolid.getSprite(GameSettings.posBuSimples[0], GameSettings.posBuSimples[1], SIZEENTITYX, SIZEENTITYY);
    public static BufferedImage juncaoDupla1 = Game.spriteSolid.getSprite(GameSettings.posJuncaoDupla1[0], GameSettings.posJuncaoDupla1[1], SIZEENTITYX, SIZEENTITYY);
    public static BufferedImage juncaoDupla2 = Game.spriteSolid.getSprite(GameSettings.posJuncaoDupla2[0], GameSettings.posJuncaoDupla2[1], SIZEENTITYX, SIZEENTITYY);
    public static BufferedImage juncaoUmBlocoDireita = Game.spriteSolid.getSprite(GameSettings.posJuncaoUmBlocoDireita[0], GameSettings.posJuncaoUmBlocoDireita[1], SIZEENTITYX, SIZEENTITYY);
    public static BufferedImage juncaoUmBlocoEsquerda = Game.spriteSolid.getSprite(GameSettings.posJuncaoUmBlocoEsquerda[0], GameSettings.posJuncaoUmBlocoEsquerda[1], SIZEENTITYX, SIZEENTITYY);
    public static BufferedImage juncaoSimplesLateralTopoDireita = Game.spriteSolid.getSprite(GameSettings.posJuncaoSimplesLateralTopoDireita[0], GameSettings.posJuncaoSimplesLateralTopoDireita[1], SIZEENTITYX, SIZEENTITYY);
    public static BufferedImage juncaoSimplesFundoDireita = Game.spriteSolid.getSprite(GameSettings.posJuncaoSimplesFundoDireita[0], GameSettings.posJuncaoSimplesFundoDireita[1], SIZEENTITYX, SIZEENTITYY);
    public static BufferedImage juncaoSimplesFundoDireitaCaverna = Game.spriteSolid.getSprite(GameSettings.posJuncaoSimplesFundoDireitaCaverna[0], GameSettings.posJuncaoSimplesFundoDireitaCaverna[1], SIZEENTITYX, SIZEENTITYY);
    public static BufferedImage tijoloDeserto = Game.spriteSolid.getSprite(GameSettings.posTijoloDeserto[0], GameSettings.posTijoloDeserto[1], SIZEENTITYX, SIZEENTITYY);
    public static BufferedImage empty = Game.spriteSolid.getSprite(GameSettings.posEmpty[0], GameSettings.posEmpty[1], SIZEENTITYX, SIZEENTITYY);

    //-----------------------//
    //      DECORATIVOS      //
    //-----------------------//

    public static BufferedImage ceu = Game.ceu.getSprite(0, 0, 32, 800);
    public static BufferedImage mountainParalax = Game.mountain.getSprite(0, 0, 3200, 800);
    public static BufferedImage wallFundo1 = Game.spriteDecoration.getSprite(240, 128, 224, 128);
    public static BufferedImage luzWallFundo1 = Game.spriteDecoration.getSprite(320, 64, 64, 64);
    public static BufferedImage predioFundo1 = Game.spriteDecoration.getSprite(0, 128, 224, 300);
    public static BufferedImage nuvens = Game.nuvens.getSprite(0, 0, 1471, 700);




    public static BufferedImage grama = Game.spriteDecoration.getSprite(GameSettings.posGrama[0], GameSettings.posGrama[1], SIZEENTITYX, SIZEENTITYY);
    public static BufferedImage galhoSeco = Game.spriteDecoration.getSprite(GameSettings.posGalhoSeco[0], GameSettings.posGalhoSeco[1], SIZEENTITYX, SIZEENTITYY);
    public static BufferedImage fundoDarkBrickBrokenBase1 = Game.spriteDecoration.getSprite(GameSettings.posFundoDarkBrickBrokenBase1[0], GameSettings.posFundoDarkBrickBrokenBase1[1], SIZEENTITYX, SIZEENTITYY);
    public static BufferedImage fundoDarkBrickEsquerdo = Game.spriteDecoration.getSprite(GameSettings.posFundoDarkBrickEsquerdo[0], GameSettings.posFundoDarkBrickEsquerdo[1], SIZEENTITYX, SIZEENTITYY);
    public static BufferedImage fundoDarkBrickDireito = Game.spriteDecoration.getSprite(GameSettings.posFundoDarkBrickDireito[0], GameSettings.posFundoDarkBrickDireito[1], SIZEENTITYX, SIZEENTITYY);
    public static BufferedImage fundoDarkBrickBase = Game.spriteDecoration.getSprite(GameSettings.posFundoDarkBrickBase[0], GameSettings.posFundoDarkBrickBase[1], SIZEENTITYX, SIZEENTITYY);
    public static BufferedImage fundoCaverna1 = Game.spriteDecoration.getSprite(GameSettings.posFundoCaverna1[0], GameSettings.posFundoCaverna1[1], SIZEENTITYX, SIZEENTITYY);
    public static BufferedImage fundoCavernaEntradaEsquerda = Game.spriteDecoration.getSprite(GameSettings.posFundoCavernaEntradaEsquerda[0], GameSettings.posFundoCavernaEntradaEsquerda[1], SIZEENTITYX, SIZEENTITYY);
    public static BufferedImage fundoCavernaEntradaDireita = Game.spriteDecoration.getSprite(GameSettings.posFundoCavernaEntradaDireita[0], GameSettings.posFundoCavernaEntradaDireita[1], SIZEENTITYX, SIZEENTITYY);

    //-----------------------//
    //      INTERATIVOS      //
    //-----------------------//

    // INIMIGO
    public static BufferedImage inimigo = Game.spriteEnemy.getSprite(0, 48, SIZEENTITYX, SIZEENTITYY);


    //OUTROS
    public static BufferedImage escadaTopo = Game.spriteInterative.getSprite(GameSettings.posEscadaTopo[0], GameSettings.posEscadaTopo[1], SIZEENTITYX, SIZEENTITYY);
    public static BufferedImage escadaFPTopo = Game.spriteInterative.getSprite(GameSettings.posEscadaFPTopo[0], GameSettings.posEscadaFPTopo[1], SIZEENTITYX, SIZEENTITYY);
    public static BufferedImage escada = Game.spriteInterative.getSprite(GameSettings.posEscada[0], GameSettings.posEscada[1], SIZEENTITYX, SIZEENTITYY);
    public static BufferedImage fPEscada = Game.spriteInterative.getSprite(GameSettings.posFPEscada[0], GameSettings.posFPEscada[1], SIZEENTITYX, SIZEENTITYY);
    public static BufferedImage escadaBase = Game.spriteInterative.getSprite(GameSettings.posEscadaBase[0], GameSettings.posEscadaBase[1], SIZEENTITYX, SIZEENTITYY);
    public static BufferedImage escadaFPBase = Game.spriteInterative.getSprite(GameSettings.posEscadaFPBase[0], GameSettings.posEscadaFPBase[1], SIZEENTITYX, SIZEENTITYY);
    public static BufferedImage espinho = Game.spriteInterative.getSprite(GameSettings.posEspinho[0], GameSettings.posEspinho[1], SIZEENTITYX, SIZEENTITYY);
    public static BufferedImage kitHealth = Game.spriteInterative.getSprite(GameSettings.posKitHealth[0], GameSettings.posKitHealth[1], SIZEENTITYX, SIZEENTITYY);
    public static BufferedImage trashBag = Game.spriteInterative.getSprite(GameSettings.posTrashBag[0], GameSettings.posTrashBag[1], SIZEENTITYX, SIZEENTITYY);
    public static BufferedImage vidaExtra = Game.spriteInterative.getSprite(GameSettings.posVidaExtra[0], GameSettings.posVidaExtra[1], SIZEENTITYX, SIZEENTITYY);
    public static BufferedImage checkPoint = Game.spriteInterative.getSprite(GameSettings.posSavePoint[0], GameSettings.posSavePoint[1], SIZEENTITYX, SIZEENTITYY);
    public static BufferedImage ammunitionExtra = Game.spriteInterative.getSprite(GameSettings.posAmmunitionExtra[0], GameSettings.posAmmunitionExtra[1], SIZEENTITYX, SIZEENTITYY);


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
