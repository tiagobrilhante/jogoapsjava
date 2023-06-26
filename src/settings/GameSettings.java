package settings;

import java.util.Objects;

public abstract class GameSettings {
    //-------------//
    //  GAME NAME  //
    //-------------//
    private final static String gameName = "ScrAPS World";

    // ----------------------------------------- //
    //                                           //
    //                                           //
    //                    PATHS                  //
    //                                           //
    //                                           //
    // ----------------------------------------- //

    //-------------//
    //  GAME ICON  //
    //-------------//
    private final static String gameIconPath ="/res/icons/scrapsicon.png";

    //----------------//
    //  GAME SPRITES  //
    //----------------//

    public static String spriteSolidPath = "/res/spritesheets/terrain/solid/spritesheet32solid.png";
    public static String spriteDecorationPath = "/res/spritesheets/terrain/decoration/spritesheet32decoration.png";
    public static String spriteInterativePath = "/res/spritesheets/terrain/interative/spritesheet32interativo.png";
    public static String spritePlayerPath = "/res/spritesheets/player/spritesheetPlayer3.png";
    public static String spriteEnemyPath = "/res/spritesheets/enemy/spritesheetEnemy.png";
    public static String spriteCeuPath = "/res/spritesheets/terrain/decoration/ceusprite3.png";
    public static String spriteMountainPath = "/res/spritesheets/terrain/decoration/mountain1lvlsprite2.png";
    public static String spriteNuvemPath = "/res/spritesheets/terrain/decoration/ceuspriteClouds.png";
    public static String levelPath = "/res/fases/";

    //-------------------//
    //  Entity Settings  //
    //-------------------//

    public static class Objetos {
        final String[]
                // -----------------//
                //  DECORATIVOS     //
                // -----------------//
                corFundoDarkBrickBase = {"0E5050", "fundoDarkBrickBase"},
                corFundoDarkBrickBrokenBase1 = {"0E1052", "fundoDarkBrickBrokenBase1"},
                corFundoDarkBrickDireito = {"494949", "fundoDarkBrickDireito"},
                corFundoDarkBrickEsquerdo = {"646464", "fundoDarkBrickEsquerdo"},
                corGrama = {"38385D", "Grama"},
                corEspinho = {"4D8080", "Espinho"},
                corGalhoSeco = {"4D4D80", "GalhosSecos"},
                corWallFundo1 = {"2D3425", "WallFundo1"},
                corLuzWallFundo1 = {"604FFE", "LuzWallFundo1"},
                corFundoCaverna1 = {"1D3B32", "fundoCaverna1"},
                corFundoCavernaEntradaEsquerda = {"236351", "fundoCavernaEntradaEsquerda"},
                corFundoCavernaEntradaDireita = {"634425", "fundoCavernaEntradaDireita"},
                corPredioFundo1 = {"157920", "PredioFundo1"},
                corCeu = {"639BFF", "Ceu"},
                corMountainParalax = {"3E7682", "MountainParalax"},
        // --------------//
        //  INTERATIVOS  //
        // --------------//
        corVidaExtra = {"97DF67", "VidaExtra"},
                corAmmunitionExtra= {"827719", "AmmunitionExtra"},
                corEscadaTopo = {"EE8FBE", "EscadaTopo"},
                corEscadaFPTopo = {"822D37", "EscadaFPTopo"},
                corEscada = {"C4759D", "Escada"},
                corFPEscada = {"827869", "FPEscada"},
                corEscadaBase = {"9B5D7C", "EscadaBase"},
                corEscadaFPBase = {"3A8082", "EscadaFPBase"},
                corTrashBag = {"F600F6", "TrashBag"},
                corKitHealth = {"F6EFEF", "KitHealth"},
                corCheckPoint = {"808033", "CheckPoint"},
                corInimigo1 = {"494900", "Inimigo1"},
                corInimigo2 = {"606000", "Inimigo2"},
                corInimigo3 = {"20140C", "Inimigo3"},
                corInimigo4 = {"A88F39", "Inimigo4"},
                corPlayer = {"FFFF00", "Player"},
        // -------------//
        //   SOLIDOS    //
        // -------------//
        corChaoNucleo = {"086910", "Solido"},
                corChaoEsquerdo = {"AC4839", "Solido"},
                corChaoEsquerdoCaverna = {"F279E0", "Solido"},
                corChaoEsquerdoTopo = {"18B229", "Solido"},
                corChaoEsquerdoCavernaTopo = {"A9F2E7", "Solido"},
                corChaoEsquerdoFundo = {"946d4A", "Solido"},
                corChaoEsquerdoCavernaFundo = {"F24138", "Solido"},
                corJuncaoTopoEsquerda = {"FF9D52", "Solido"},
                corJuncaoFundoEsquerda = {"FF713D", "Solido"},
                corJuncaoTopoDireita = {"FF4551", "Solido"},
                corJuncaoFundoDireita = {"FF73F8", "Solido"},
                corJuncaoDupla1 = {"61FF88", "Solido"},
                corJuncaoDupla2 = {"C8FF52", "Solido"},
                corChaoDireito = {"AC6920", "Solido"},
                corChaoDireitoCaverna = {"4C678F", "Solido"},
                corChaoDireitoTopo = {"CD3420", "Solido"},
                corChaoDireitoCavernaTopo = {"F2AD35", "Solido"},
                corChaoDireitoFundo = {"6722AC", "Solido"},
                corChaoDireitoCavernaFundo = {"1E2415", "Solido"},
                corChaoNormalTopo = {"6A91A4", "Solido"},
                corChaoNormalMontanhaTopo = {"5A6EF2", "Solido"},
                corChaoNormalFundo = {"4A2420", "Solido"},
                corChaoNormalMontanhaFundo = {"F24D95", "Solido"},
                corJuncaoSimplesLateralTopoDireita = {"496372", "Solido"},
                corNucleoConverteDireitaChaoIsoladoTopo = {"55D595", "Solido"},
                corNucleoConverteDireitaChaoIsoladoFundo = {"D5D580", "Solido"},
                corNucleoConverteEsquerdaChaoIsoladoTopo = {"D55555", "Solido"},
                corNucleoConverteEsquerdaChaoIsoladoFundo = {"392420", "Solido"},
                corJuncaoBuEsquerdaBaixo = {"585392", "Solido"},
                corJuncaoBuDireitaBaixo = {"666663", "Solido"},
                corBuSimples = {"666248", "Solido"},
                corTijoloDeserto = {"EE8529", "Solido"},
                corChaoIsoladoTopo = {"7845AC", "Solido"},
                corChaoIsoladoFundo = {"AAD5C0", "Solido"},
                corNucleoBifurcaChaoIsoladoTopo = {"45ACAC", "Solido"},
                corNucleoBifurcaChaoIsoladoFundo = {"D57D29", "Solido"},
                corChaoIsoladoEsquerda = {"355240", "Solido"},
                corChaoIsoladoDireita = {"793B34", "Solido"},
                corJuncaoSimplesFundoDireita = {"F22778", "Solido"},
                corJuncaoSimplesFundoDireitaCaverna = {"1C2F42", "Solido"},
                corChaoIsoladoMeioVertical = {"2D2C7A", "Solido"},
                corPedra1 = {"1A3917", "Solido"},
                corJuncaoUmBlocoDireita = {"203766", "Solido"},
                corJuncaoUmBlocoEsquerda = {"364366", "Solido"},
                corJuncaoSimplesUmBlocoDuploDireita = {"72CCF2", "Solido"},
                corJuncaoSimplesUmBlocoDuploDireitaCaverna = {"6D1857", "Solido"},
                corJuncaoSimplesUmBlocoDuploEsquerda = {"F294CB", "Solido"},
                corJuncaoSimplesUmBlocoDuploEsquerdaCaverna = {"6B6D4F", "Solido"};

    }

    public static int[]
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
            posEscadaFPTopo = {96, 0},
            posEscada = {32, 0},
            posFPEscada = {128, 0},
            posEscadaBase = {64, 0},
            posEscadaFPBase = {160, 0},
            posEspinho = {0, 96},
            posKitHealth = {32, 32},
            posSavePoint = {0, 64},
            posTrashBag = {0, 32},
            posVidaExtra = {64, 32},
            posAmmunitionExtra = {96, 32};


    // array de conjuntos de entidades basicas:

    // fundoDarkBrick e fundoCaverna
    public static String[] arrayFundoDarkBrickBase = {"fundoDarkBrickBase", "fundoDarkBrickBrokenBase1", "fundoDarkBrickDireito", "fundoDarkBrickEsquerdo", "fundoCaverna1", "fundoCavernaEntradaEsquerda", "fundoCavernaEntradaDireita"};
    // escadas
    public static String[] arrayEscadas = {"EscadaTopo", "Escada", "EscadaBase", "EscadaFPTopo", "FPEscada", "EscadaFPBase"};
    public static String[] arrayEscadasTopo = {"EscadaTopo", "EscadaFPTopo"};
    public static String[] arrayEscadasBase = {"EscadaBase", "EscadaFPBase"};
    // inimigos
    public static String[] arrayInimigos = {"Inimigo1", "Inimigo2", "Inimigo3", "Inimigo4"};

    // Solido
    public static String[] arraySolido = {"Solido"};
    // Grama
    public static String[] arrayGrama = {"Grama"};
    // Espinho
    public static String[] arrayEspinho = {"Espinho"};
    //Galhos Secos
    public static String[] arrayGalhosSecos = {"GalhosSecos"};
    //Wall Fundo 1
    public static String[] arrayWallFundo1 = {"WallFundo1"};
    // luz de wallfundo1
    public static String[] arrayLuzWallFundo1 = {"LuzWallFundo1"};
    // predio de fundo
    public static String[] arrayPredioFundo1 = {"PredioFundo1"};
    // ceu
    public static String[] arrayCeu = {"Ceu"};
    // mountain paralax
    public static String[] arrayMountainParalax = {"MountainParalax"};
    //vida extra
    public static String[] arrayVidaExtra = {"VidaExtra"};
    // municao extra
    public static String[] arrayAmmunitionExtra = {"AmmunitionExtra"};
    // saco de lixo
    public static String[] arrayTrashBag = {"TrashBag"};
    // kit de saude
    public static String[] arrayKitHealth = {"KitHealth"};
    //checkpoint
    public static String[] arrayCheckPoint = {"CheckPoint"};












    // WIDTH, HEIGHT, SCALE
    private final static int[] gameDimensions = {520, 300,2};

    public static String getGameName() {
        return gameName;
    }

    public static String getGameIconPath() {
        return gameIconPath;
    }

    public static int getGAME_WIDTH() {
        return gameDimensions[0];
    }

    public static int getGAME_HEIGHT() {
        return gameDimensions[1];
    }

    public static int getGAME_SCALE() {
        return gameDimensions[2];
    }




}

