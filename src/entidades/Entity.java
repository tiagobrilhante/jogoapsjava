package entidades;

import Mundo.Camera;
import main.Game;
import settings.GameSettings;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;


// classse muito importante
// gerencia aspectos de comportamentos das entidades


public class Entity {

    // tamanho básico de entidades
    public static int SIZEENTITYX = 32, SIZEENTITYY = 32;

    // Estidades específicas
    // empty
    public static BufferedImage empty = Game.spriteSolid.getSprite(GameSettings.posEmpty[0], GameSettings.posEmpty[1], SIZEENTITYX, SIZEENTITYY);

    //-----------------------//
    //      DECORATIVOS      //
    //-----------------------//

    public static BufferedImage nuvens = Game.nuvens.getSprite(GameSettings.attrNuvens[0], GameSettings.attrNuvens[1], GameSettings.attrNuvens[2], GameSettings.attrNuvens[3]);

    //-----------------------//
    //      INTERATIVOS      //
    //-----------------------//

    // INIMIGO
    public static BufferedImage inimigo;

    //OUTROS



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


    public static BufferedImage entityGenerator(String type) {

        if (Objects.equals(type, "ammunitionExtra")) {
            return Game.spriteInterative.getSprite(GameSettings.posAmmunitionExtra[0], GameSettings.posAmmunitionExtra[1], SIZEENTITYX, SIZEENTITYY);
        } else if (Objects.equals(type, "vidaExtra")) {
            return Game.spriteInterative.getSprite(GameSettings.posVidaExtra[0], GameSettings.posVidaExtra[1], SIZEENTITYX, SIZEENTITYY);
        } else if (Objects.equals(type, "trashBag")) {
            return Game.spriteInterative.getSprite(GameSettings.posTrashBag[0], GameSettings.posTrashBag[1], SIZEENTITYX, SIZEENTITYY);
        } else if (Objects.equals(type, "kitHealth")) {
            return Game.spriteInterative.getSprite(GameSettings.posKitHealth[0], GameSettings.posKitHealth[1], SIZEENTITYX, SIZEENTITYY);
        } else if (Objects.equals(type, "checkPoint")) {
            return Game.spriteInterative.getSprite(GameSettings.posSavePoint[0], GameSettings.posSavePoint[1], SIZEENTITYX, SIZEENTITYY);
        } else if (Objects.equals(type, "mountainParalax")) {
            return Game.mountain.getSprite(GameSettings.attrMountainParalax[0], GameSettings.attrMountainParalax[1], GameSettings.attrMountainParalax[2], GameSettings.attrMountainParalax[3]);
        } else if (Objects.equals(type, "ceu")) {
            return Game.ceu.getSprite(GameSettings.attrCeu[0], GameSettings.attrCeu[1], GameSettings.attrCeu[2], GameSettings.attrCeu[3]);
        } else if (Objects.equals(type, "predioFundo1")) {
            return Game.spriteDecoration.getSprite(GameSettings.attrPredioFundo1[0], GameSettings.attrPredioFundo1[1], GameSettings.attrPredioFundo1[2], GameSettings.attrPredioFundo1[3]);
        } else if (Objects.equals(type, "luzWallFundo1Vetor")) {
            return Game.spriteDecoration.getSprite(GameSettings.attrLuzWallFundo1[0], GameSettings.attrLuzWallFundo1[1], GameSettings.attrLuzWallFundo1[2], GameSettings.attrLuzWallFundo1[3]);
        } else if (Objects.equals(type, "wallFundo1")) {
            return Game.spriteDecoration.getSprite(GameSettings.attrWallFundo1[0], GameSettings.attrWallFundo1[1], GameSettings.attrWallFundo1[2], GameSettings.attrWallFundo1[3]);
        }else if (Objects.equals(type, "galhoSeco")) {
            return Game.spriteDecoration.getSprite(GameSettings.posGalhoSeco[0], GameSettings.posGalhoSeco[1], SIZEENTITYX, SIZEENTITYY);
        }else if (Objects.equals(type, "espinho")) {
            return Game.spriteInterative.getSprite(GameSettings.posEspinho[0], GameSettings.posEspinho[1], SIZEENTITYX, SIZEENTITYY);
        } else if (Objects.equals(type, "grama")) {
            return Game.spriteDecoration.getSprite(GameSettings.posGrama[0], GameSettings.posGrama[1], SIZEENTITYX, SIZEENTITYY);
        }else if (Arrays.asList(GameSettings.arrayFundoDarkBrickBase).contains(type)) {
            Map<String, BufferedImage> imageMap = new HashMap<>();
            imageMap.put("fundoDarkBrickBase", Game.spriteDecoration.getSprite(GameSettings.posFundoDarkBrickBase[0], GameSettings.posFundoDarkBrickBase[1], SIZEENTITYX, SIZEENTITYY));
            imageMap.put("fundoDarkBrickBrokenBase1", Game.spriteDecoration.getSprite(GameSettings.posFundoDarkBrickBrokenBase1[0], GameSettings.posFundoDarkBrickBrokenBase1[1], SIZEENTITYX, SIZEENTITYY));
            imageMap.put("fundoDarkBrickDireito", Game.spriteDecoration.getSprite(GameSettings.posFundoDarkBrickDireito[0], GameSettings.posFundoDarkBrickDireito[1], SIZEENTITYX, SIZEENTITYY));
            imageMap.put("fundoDarkBrickEsquerdo", Game.spriteDecoration.getSprite(GameSettings.posFundoDarkBrickEsquerdo[0], GameSettings.posFundoDarkBrickEsquerdo[1], SIZEENTITYX, SIZEENTITYY));
            imageMap.put("fundoCaverna1", Game.spriteDecoration.getSprite(GameSettings.posFundoCaverna1[0], GameSettings.posFundoCaverna1[1], SIZEENTITYX, SIZEENTITYY));
            imageMap.put("fundoCavernaEntradaEsquerda", Game.spriteDecoration.getSprite(GameSettings.posFundoCavernaEntradaEsquerda[0], GameSettings.posFundoCavernaEntradaEsquerda[1], SIZEENTITYX, SIZEENTITYY));
            imageMap.put("fundoCavernaEntradaDireita", Game.spriteDecoration.getSprite(GameSettings.posFundoCavernaEntradaDireita[0], GameSettings.posFundoCavernaEntradaDireita[1], SIZEENTITYX, SIZEENTITYY));

            return imageMap.get(type);
        } else if (Arrays.asList(GameSettings.arraySolidoDisc).contains(type)){

            Map<String, BufferedImage> imageMap = new HashMap<>();

            imageMap.put("chaoNucleo", Game.spriteSolid.getSprite(GameSettings.posChaoNucleo[0], GameSettings.posChaoNucleo[1], SIZEENTITYX, SIZEENTITYY));
            imageMap.put("chaoIsoladoTopo", Game.spriteSolid.getSprite(GameSettings.posChaoIsoladoTopo[0], GameSettings.posChaoIsoladoTopo[1], SIZEENTITYX, SIZEENTITYY));
            imageMap.put("chaoIsoladoEsquerda", Game.spriteSolid.getSprite(GameSettings.posChaoIsoladoEsquerda[0], GameSettings.posChaoIsoladoEsquerda[1], SIZEENTITYX, SIZEENTITYY));
            imageMap.put("chaoIsoladoDireita", Game.spriteSolid.getSprite(GameSettings.posChaoIsoladoDireita[0], GameSettings.posChaoIsoladoDireita[1], SIZEENTITYX, SIZEENTITYY));
            imageMap.put("juncaoSimplesUmBlocoDuploDireita", Game.spriteSolid.getSprite(GameSettings.posJuncaoSimplesUmBlocoDuploDireita[0], GameSettings.posJuncaoSimplesUmBlocoDuploDireita[1], SIZEENTITYX, SIZEENTITYY));
            imageMap.put("juncaoSimplesUmBlocoDuploDireitaCaverna", Game.spriteSolid.getSprite(GameSettings.posJuncaoSimplesUmBlocoDuploDireitaCaverna[0], GameSettings.posJuncaoSimplesUmBlocoDuploDireitaCaverna[1], SIZEENTITYX, SIZEENTITYY));
            imageMap.put("juncaoSimplesUmBlocoDuploEsquerda", Game.spriteSolid.getSprite(GameSettings.posJuncaoSimplesUmBlocoDuploEsquerda[0], GameSettings.posJuncaoSimplesUmBlocoDuploEsquerda[1], SIZEENTITYX, SIZEENTITYY));
            imageMap.put("juncaoSimplesUmBlocoDuploEsquerdaCaverna", Game.spriteSolid.getSprite(GameSettings.posJuncaoSimplesUmBlocoDuploEsquerdaCaverna[0], GameSettings.posJuncaoSimplesUmBlocoDuploEsquerdaCaverna[1], SIZEENTITYX, SIZEENTITYY));
            imageMap.put("chaoIsoladoFundo", Game.spriteSolid.getSprite(GameSettings.posChaoIsoladoFundo[0], GameSettings.posChaoIsoladoFundo[1], SIZEENTITYX, SIZEENTITYY));
            imageMap.put("nucleoBifurcaChaoIsoladoTopo", Game.spriteSolid.getSprite(GameSettings.posNucleoBifurcaChaoIsoladoTopo[0], GameSettings.posNucleoBifurcaChaoIsoladoTopo[1], SIZEENTITYX, SIZEENTITYY));
            imageMap.put("nucleoBifurcaChaoIsoladoFundo", Game.spriteSolid.getSprite(GameSettings.posNucleoBifurcaChaoIsoladoFundo[0], GameSettings.posNucleoBifurcaChaoIsoladoFundo[1], SIZEENTITYX, SIZEENTITYY));
            imageMap.put("nucleoConverteDireitaChaoIsoladoTopo", Game.spriteSolid.getSprite(GameSettings.posNucleoConverteDireitaChaoIsoladoTopo[0], GameSettings.posNucleoConverteDireitaChaoIsoladoTopo[1], SIZEENTITYX, SIZEENTITYY));
            imageMap.put("nucleoConverteEsquerdaChaoIsoladoTopo", Game.spriteSolid.getSprite(GameSettings.posNucleoConverteEsquerdaChaoIsoladoTopo[0], GameSettings.posNucleoConverteEsquerdaChaoIsoladoTopo[1], SIZEENTITYX, SIZEENTITYY));
            imageMap.put("nucleoConverteDireitaChaoIsoladoFundo", Game.spriteSolid.getSprite(GameSettings.posNucleoConverteDireitaChaoIsoladoFundo[0], GameSettings.posNucleoConverteDireitaChaoIsoladoTopo[1], SIZEENTITYX, SIZEENTITYY));
            imageMap.put("nucleoConverteEsquerdaChaoIsoladoFundo", Game.spriteSolid.getSprite(GameSettings.posNucleoConverteEsquerdaChaoIsoladoFundo[0], GameSettings.posNucleoConverteEsquerdaChaoIsoladoTopo[1], SIZEENTITYX, SIZEENTITYY));
            imageMap.put("chaoIsoladoMeioVertical", Game.spriteSolid.getSprite(GameSettings.posChaoIsoladoMeioVertical[0], GameSettings.posChaoIsoladoMeioVertical[1], SIZEENTITYX, SIZEENTITYY));
            imageMap.put("pedra1", Game.spriteSolid.getSprite(GameSettings.posPedra1[0], GameSettings.posPedra1[1], SIZEENTITYX, SIZEENTITYY));
            imageMap.put("chaoEsquerdoTopo", Game.spriteSolid.getSprite(GameSettings.posChaoEsquerdoTopo[0], GameSettings.posChaoEsquerdoTopo[1], SIZEENTITYX, SIZEENTITYY));
            imageMap.put("chaoEsquerdoCavernaTopo", Game.spriteSolid.getSprite(GameSettings.posChaoEsquerdoCavernaTopo[0], GameSettings.posChaoEsquerdoCavernaTopo[1], SIZEENTITYX, SIZEENTITYY));
            imageMap.put("chaoEsquerdo", Game.spriteSolid.getSprite(GameSettings.posChaoEsquerdo[0], GameSettings.posChaoEsquerdo[1], SIZEENTITYX, SIZEENTITYY));
            imageMap.put("chaoEsquerdoCaverna", Game.spriteSolid.getSprite(GameSettings.posChaoEsquerdoCaverna[0], GameSettings.posChaoEsquerdoCaverna[1], SIZEENTITYX, SIZEENTITYY));
            imageMap.put("chaoDireitoTopo", Game.spriteSolid.getSprite(GameSettings.posChaoDireitoTopo[0], GameSettings.posChaoDireitoTopo[1], SIZEENTITYX, SIZEENTITYY));
            imageMap.put("chaoDireitoCavernaTopo", Game.spriteSolid.getSprite(GameSettings.posChaoDireitoCavernaTopo[0], GameSettings.posChaoDireitoCavernaTopo[1], SIZEENTITYX, SIZEENTITYY));
            imageMap.put("chaoDireito", Game.spriteSolid.getSprite(GameSettings.posChaoDireito[0], GameSettings.posChaoDireito[1], SIZEENTITYX, SIZEENTITYY));
            imageMap.put("chaoDireitoCaverna", Game.spriteSolid.getSprite(GameSettings.posChaoDireitoCaverna[0], GameSettings.posChaoDireitoCaverna[1], SIZEENTITYX, SIZEENTITYY));
            imageMap.put("chaoNormalTopo", Game.spriteSolid.getSprite(GameSettings.posChaoNormalTopo[0], GameSettings.posChaoNormalTopo[1], SIZEENTITYX, SIZEENTITYY));
            imageMap.put("chaoNormalMontanhaTopo", Game.spriteSolid.getSprite(GameSettings.posChaoNormalMontanhaTopo[0], GameSettings.posChaoNormalMontanhaTopo[1], SIZEENTITYX, SIZEENTITYY));
            imageMap.put("chaoNormalFundo", Game.spriteSolid.getSprite(GameSettings.posChaoNormalFundo[0], GameSettings.posChaoNormalFundo[1], SIZEENTITYX, SIZEENTITYY));
            imageMap.put("chaoNormalMontanhaFundo", Game.spriteSolid.getSprite(GameSettings.posChaoNormalMontanhaFundo[0], GameSettings.posChaoNormalMontanhaFundo[1], SIZEENTITYX, SIZEENTITYY));
            imageMap.put("chaoDireitoFundo", Game.spriteSolid.getSprite(GameSettings.posChaoDireitoFundo[0], GameSettings.posChaoDireitoFundo[1], SIZEENTITYX, SIZEENTITYY));
            imageMap.put("chaoDireitoCavernaFundo", Game.spriteSolid.getSprite(GameSettings.posChaoDireitoCavernaFundo[0], GameSettings.posChaoDireitoCavernaFundo[1], SIZEENTITYX, SIZEENTITYY));
            imageMap.put("chaoEsquerdoFundo", Game.spriteSolid.getSprite(GameSettings.posChaoEsquerdoFundo[0], GameSettings.posChaoEsquerdoFundo[1], SIZEENTITYX, SIZEENTITYY));
            imageMap.put("chaoEsquerdoCavernaFundo", Game.spriteSolid.getSprite(GameSettings.posChaoEsquerdoCavernaFundo[0], GameSettings.posChaoEsquerdoCavernaFundo[1], SIZEENTITYX, SIZEENTITYY));
            imageMap.put("juncaoTopoEsquerda", Game.spriteSolid.getSprite(GameSettings.posJuncaoTopoEsquerda[0], GameSettings.posJuncaoTopoEsquerda[1], SIZEENTITYX, SIZEENTITYY));
            imageMap.put("juncaoFundoEsquerda", Game.spriteSolid.getSprite(GameSettings.posJuncaoFundoEsquerda[0], GameSettings.posJuncaoFundoEsquerda[1], SIZEENTITYX, SIZEENTITYY));
            imageMap.put("juncaoTopoDireita", Game.spriteSolid.getSprite(GameSettings.posJuncaoTopoDireita[0], GameSettings.posJuncaoTopoDireita[1], SIZEENTITYX, SIZEENTITYY));
            imageMap.put("juncaoFundoDireita", Game.spriteSolid.getSprite(GameSettings.posJuncaoFundoDireita[0], GameSettings.posJuncaoFundoDireita[1], SIZEENTITYX, SIZEENTITYY));
            imageMap.put("juncaoBuEsquerdaBaixo", Game.spriteSolid.getSprite(GameSettings.posJuncaoBuEsquerdaBaixo[0], GameSettings.posJuncaoBuEsquerdaBaixo[1], SIZEENTITYX, SIZEENTITYY));
            imageMap.put("juncaoBuDireitaBaixo", Game.spriteSolid.getSprite(GameSettings.posJuncaoBuDireitaBaixo[0], GameSettings.posJuncaoBuDireitaBaixo[1], SIZEENTITYX, SIZEENTITYY));
            imageMap.put("buSimples", Game.spriteSolid.getSprite(GameSettings.posBuSimples[0], GameSettings.posBuSimples[1], SIZEENTITYX, SIZEENTITYY));
            imageMap.put("juncaoDupla1", Game.spriteSolid.getSprite(GameSettings.posJuncaoDupla1[0], GameSettings.posJuncaoDupla1[1], SIZEENTITYX, SIZEENTITYY));
            imageMap.put("juncaoDupla2", Game.spriteSolid.getSprite(GameSettings.posJuncaoDupla2[0], GameSettings.posJuncaoDupla2[1], SIZEENTITYX, SIZEENTITYY));
            imageMap.put("juncaoUmBlocoDireita", Game.spriteSolid.getSprite(GameSettings.posJuncaoUmBlocoDireita[0], GameSettings.posJuncaoUmBlocoDireita[1], SIZEENTITYX, SIZEENTITYY));
            imageMap.put("juncaoUmBlocoEsquerda", Game.spriteSolid.getSprite(GameSettings.posJuncaoUmBlocoEsquerda[0], GameSettings.posJuncaoUmBlocoEsquerda[1], SIZEENTITYX, SIZEENTITYY));
            imageMap.put("juncaoSimplesLateralTopoDireita", Game.spriteSolid.getSprite(GameSettings.posJuncaoSimplesLateralTopoDireita[0], GameSettings.posJuncaoSimplesLateralTopoDireita[1], SIZEENTITYX, SIZEENTITYY));
            imageMap.put("juncaoSimplesFundoDireita", Game.spriteSolid.getSprite(GameSettings.posJuncaoSimplesFundoDireita[0], GameSettings.posJuncaoSimplesFundoDireita[1], SIZEENTITYX, SIZEENTITYY));
            imageMap.put("juncaoSimplesFundoDireitaCaverna", Game.spriteSolid.getSprite(GameSettings.posJuncaoSimplesFundoDireitaCaverna[0], GameSettings.posJuncaoSimplesFundoDireitaCaverna[1], SIZEENTITYX, SIZEENTITYY));
            imageMap.put("tijoloDeserto", Game.spriteSolid.getSprite(GameSettings.posTijoloDeserto[0], GameSettings.posTijoloDeserto[1], SIZEENTITYX, SIZEENTITYY));

            return imageMap.get(type);
        }else if (Arrays.asList(GameSettings.arrayEscadaDisc).contains(type)) {
            Map<String, BufferedImage> imageMap = new HashMap<>();
            imageMap.put("escadaTopo", Game.spriteInterative.getSprite(GameSettings.posEscadaTopo[0], GameSettings.posEscadaTopo[1], SIZEENTITYX, SIZEENTITYY));
            imageMap.put("escadaFPTopo", Game.spriteInterative.getSprite(GameSettings.posEscadaFPTopo[0], GameSettings.posEscadaFPTopo[1], SIZEENTITYX, SIZEENTITYY));
            imageMap.put("escada", Game.spriteInterative.getSprite(GameSettings.posEscada[0], GameSettings.posEscada[1], SIZEENTITYX, SIZEENTITYY));
            imageMap.put("fPEscada", Game.spriteInterative.getSprite(GameSettings.posFPEscada[0], GameSettings.posFPEscada[1], SIZEENTITYX, SIZEENTITYY));
            imageMap.put("escadaBase", Game.spriteInterative.getSprite(GameSettings.posEscadaBase[0], GameSettings.posEscadaBase[1], SIZEENTITYX, SIZEENTITYY));
            imageMap.put("escadaFPBase", Game.spriteInterative.getSprite(GameSettings.posEscadaFPBase[0], GameSettings.posEscadaFPBase[1], SIZEENTITYX, SIZEENTITYY));

            return imageMap.get(type);
        }
        else {
            return null;
        }
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
