package Mundo.Generator;

import entidades.Entity;
import entidades.interativos.*;
import entidades.naoSolidos.*;
import entidades.player.Player;
import entidades.solidos.Solido;
import main.Game;

import java.awt.image.BufferedImage;
import java.lang.reflect.Field;
import java.util.Objects;

public class MundoGenerator {
    static int identificadorUnicoInimigo = 1;

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
                corPredioFundo1 = {"157920", "PredioFundo1"},
                corCeu = {"639BFF", "Ceu"},
                corMountainParalax = {"3E7682", "MountainParalax"},
        // --------------//
        //  INTERATIVOS  //
        // --------------//
        corVidaExtra = {"97DF67", "VidaExtra"},
                corAmmunitionExtra= {"827719", "AmmunitionExtra"},
                corEscadaTopo = {"EE8FBE", "EscadaTopo"},
                corEscada = {"C4759D", "Escada"},
                corEscadaBase = {"9B5D7C", "EscadaBase"},
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
                corChaoEsquerdoTopo = {"18B229", "Solido"},
                corChaoEsquerdoFundo = {"946d4A", "Solido"},
                corJuncaoTopoEsquerda = {"FF9D52", "Solido"},
                corJuncaoFundoEsquerda = {"FF713D", "Solido"},
                corJuncaoTopoDireita = {"FF4551", "Solido"},
                corJuncaoFundoDireita = {"FF73F8", "Solido"},
                corJuncaoDupla1 = {"61FF88", "Solido"},
                corJuncaoDupla2 = {"C8FF52", "Solido"},
                corChaoDireito = {"AC6920", "Solido"},
                corChaoDireitoTopo = {"CD3420", "Solido"},
                corChaoDireitoFundo = {"6722AC", "Solido"},
                corChaoNormalTopo = {"6A91A4", "Solido"},
                corChaoNormalFundo = {"4A2420", "Solido"},
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
                corChaoIsoladoMeioVertical = {"2D2C7A", "Solido"},
                corPedra1 = {"1A3917", "Solido"},
                corJuncaoUmBlocoDireita = {"203766", "Solido"},
                corJuncaoUmBlocoEsquerda = {"364366", "Solido"},
                corJuncaoSimplesUmBlocoDuploDireita = {"72CCF2", "Solido"},
                corJuncaoSimplesUmBlocoDuploEsquerda = {"F294CB", "Solido"};

    }

    public static void injetorEntidades(int x, int y, int pixelAtual) {
        Objetos objetos = new Objetos();
        Class<?> classe = objetos.getClass();
        Field[] campos = classe.getDeclaredFields();
        for (Field campo : campos) {
            campo.setAccessible(true);
            try {
                Object campoValor = campo.get(objetos);

                String[] valor = (String[]) campoValor;

                if (Objects.equals(valor[1], "Solido")) {
                    if (pixelAtual == montaCor(valor[0])) {
                        String nomeAtributo = campo.getName().substring(3); // Remove os primeiros três caracteres ("cor")
                        nomeAtributo = nomeAtributo.substring(0, 1).toLowerCase() + nomeAtributo.substring(1);
                        try {
                            Field field = Entity.class.getDeclaredField(nomeAtributo);
                            BufferedImage imagem = (BufferedImage) field.get(Entity.class);
                            Solido solido = new Solido(x * Entity.SIZEENTITYX, y * Entity.SIZEENTITYY, Entity.SIZEENTITYX, Entity.SIZEENTITYY, imagem, nomeAtributo);
                            Game.entidades.add(solido);
                        } catch (NoSuchFieldException | IllegalAccessException e) {
                            e.printStackTrace();
                        }
                    }
                } else if (Objects.equals(valor[1], "fundoDarkBrickBase") || Objects.equals(valor[1], "fundoDarkBrickBrokenBase1") || Objects.equals(valor[1], "fundoDarkBrickDireito") || Objects.equals(valor[1], "fundoDarkBrickEsquerdo")) {
                    if (pixelAtual == montaCor(valor[0])) {
                        String valorOpcao = valor[1];
                        BufferedImage fundoDarkBrickValue = switch (valorOpcao) {
                            case "fundoDarkBrickBase" -> Entity.fundoDarkBrickBase;
                            case "fundoDarkBrickBrokenBase1" -> Entity.fundoDarkBrickBrokenBase1;
                            case "fundoDarkBrickDireito" -> Entity.fundoDarkBrickDireito;
                            case "fundoDarkBrickEsquerdo" -> Entity.fundoDarkBrickEsquerdo;
                            default ->
                                // Valor padrão caso não seja nenhuma das opções
                                    null;
                        };
                        FundoDarkBrick fundoDarkBrick = new FundoDarkBrick(x * Entity.SIZEENTITYX, y * Entity.SIZEENTITYY, Entity.SIZEENTITYX, Entity.SIZEENTITYY, fundoDarkBrickValue, "CenarioDarkBrick");
                        Game.darkBricksFundo.add(fundoDarkBrick);
                    }
                } else if (Objects.equals(valor[1], "Grama")) {
                    if (pixelAtual == montaCor(valor[0])) {
                        String nomeAtributo = campo.getName().substring(3); // Remove os primeiros três caracteres ("cor")
                        nomeAtributo = nomeAtributo.substring(0, 1).toLowerCase() + nomeAtributo.substring(1);
                        try {
                            Field field = Entity.class.getDeclaredField(nomeAtributo);
                            BufferedImage imagem = (BufferedImage) field.get(Entity.class);
                            Grama grama = new Grama(x * Entity.SIZEENTITYX, y * Entity.SIZEENTITYY, Entity.SIZEENTITYX, Entity.SIZEENTITYY, imagem, nomeAtributo);
                            Game.entidades.add(grama);
                        } catch (NoSuchFieldException | IllegalAccessException e) {
                            e.printStackTrace();
                        }
                    }

                } else if (Objects.equals(valor[1], "Espinho")) {
                    if (pixelAtual == montaCor(valor[0])) {
                        String nomeAtributo = campo.getName().substring(3); // Remove os primeiros três caracteres ("cor")
                        nomeAtributo = nomeAtributo.substring(0, 1).toLowerCase() + nomeAtributo.substring(1);
                        try {
                            Field field = Entity.class.getDeclaredField(nomeAtributo);
                            BufferedImage imagem = (BufferedImage) field.get(Entity.class);
                            Espinho espinho = new Espinho(x * Entity.SIZEENTITYX, y * Entity.SIZEENTITYY, Entity.SIZEENTITYX, Entity.SIZEENTITYY, imagem, nomeAtributo);
                            Game.espinhos.add(espinho);
                        } catch (NoSuchFieldException | IllegalAccessException e) {
                            e.printStackTrace();
                        }
                    }
                } else if (Objects.equals(valor[1], "GalhosSecos")) {
                    if (pixelAtual == montaCor(valor[0])) {
                        String nomeAtributo = campo.getName().substring(3); // Remove os primeiros três caracteres ("cor")
                        nomeAtributo = nomeAtributo.substring(0, 1).toLowerCase() + nomeAtributo.substring(1);
                        try {
                            Field field = Entity.class.getDeclaredField(nomeAtributo);
                            BufferedImage imagem = (BufferedImage) field.get(Entity.class);
                            GalhosSecos galho = new GalhosSecos(x * Entity.SIZEENTITYX, y * Entity.SIZEENTITYY, Entity.SIZEENTITYX, Entity.SIZEENTITYY, imagem, nomeAtributo);
                            Game.entidades.add(galho);
                        } catch (NoSuchFieldException | IllegalAccessException e) {
                            e.printStackTrace();
                        }
                    }
                } else if (Objects.equals(valor[1], "WallFundo1")) {

                    if (pixelAtual == montaCor(valor[0])) {
                        String nomeAtributo = campo.getName().substring(3); // Remove os primeiros três caracteres ("cor")
                        nomeAtributo = nomeAtributo.substring(0, 1).toLowerCase() + nomeAtributo.substring(1);
                        try {
                            Field field = Entity.class.getDeclaredField(nomeAtributo);
                            BufferedImage imagem = (BufferedImage) field.get(Entity.class);
                            WallFundo1 wallFundo1 = new WallFundo1(x * Entity.SIZEENTITYX, y * Entity.SIZEENTITYY, Entity.SIZEENTITYX, Entity.SIZEENTITYX, imagem, nomeAtributo);
                            Game.wallFundo1Vetor.add(wallFundo1);
                        } catch (NoSuchFieldException | IllegalAccessException e) {
                            e.printStackTrace();
                        }
                    }
                } else if (Objects.equals(valor[1], "PredioFundo1")) {
                    if (pixelAtual == montaCor(valor[0])) {
                        String nomeAtributo = campo.getName().substring(3); // Remove os primeiros três caracteres ("cor")
                        nomeAtributo = nomeAtributo.substring(0, 1).toLowerCase() + nomeAtributo.substring(1);
                        try {
                            Field field = Entity.class.getDeclaredField(nomeAtributo);
                            BufferedImage imagem = (BufferedImage) field.get(Entity.class);
                            PredioFundo1 predioFundo1 = new PredioFundo1(x * Entity.SIZEENTITYX, y * Entity.SIZEENTITYY, Entity.SIZEENTITYX, Entity.SIZEENTITYX, imagem, nomeAtributo);
                            Game.predioFundo1Vetor.add(predioFundo1);
                        } catch (NoSuchFieldException | IllegalAccessException e) {
                            e.printStackTrace();
                        }
                    }
                } else if (Objects.equals(valor[1], "Ceu")) {
                    if (pixelAtual == montaCor(valor[0])) {
                        String nomeAtributo = campo.getName().substring(3); // Remove os primeiros três caracteres ("cor")
                        nomeAtributo = nomeAtributo.substring(0, 1).toLowerCase() + nomeAtributo.substring(1);
                        try {
                            Field field = Entity.class.getDeclaredField(nomeAtributo);
                            BufferedImage imagem = (BufferedImage) field.get(Entity.class);
                            Ceu ceu = new Ceu(x * Entity.SIZEENTITYX, y * Entity.SIZEENTITYY, Entity.SIZEENTITYX, Entity.SIZEENTITYX, imagem, nomeAtributo);
                            Game.ceuVetor.add(ceu);
                        } catch (NoSuchFieldException | IllegalAccessException e) {
                            e.printStackTrace();
                        }
                    }
                } else if (Objects.equals(valor[1], "MountainParalax")) {
                    if (pixelAtual == montaCor(valor[0])) {
                        String nomeAtributo = campo.getName().substring(3); // Remove os primeiros três caracteres ("cor")
                        nomeAtributo = nomeAtributo.substring(0, 1).toLowerCase() + nomeAtributo.substring(1);
                        try {
                            Field field = Entity.class.getDeclaredField(nomeAtributo);
                            BufferedImage imagem = (BufferedImage) field.get(Entity.class);
                            MountainsParalax mountainsParalax = new MountainsParalax(x * Entity.SIZEENTITYX, y * Entity.SIZEENTITYY, Entity.SIZEENTITYX, Entity.SIZEENTITYX, imagem, nomeAtributo);
                            Game.mountainVetor.add(mountainsParalax);
                        } catch (NoSuchFieldException | IllegalAccessException e) {
                            e.printStackTrace();
                        }
                    }
                } else if (Objects.equals(valor[1], "VidaExtra")) {
                    if (pixelAtual == montaCor(valor[0])) {
                        String nomeAtributo = campo.getName().substring(3); // Remove os primeiros três caracteres ("cor")
                        nomeAtributo = nomeAtributo.substring(0, 1).toLowerCase() + nomeAtributo.substring(1);
                        try {
                            Field field = Entity.class.getDeclaredField(nomeAtributo);
                            BufferedImage imagem = (BufferedImage) field.get(Entity.class);
                            VidaExtra vidaExtra = new VidaExtra(x * Entity.SIZEENTITYX, y * Entity.SIZEENTITYY, Entity.SIZEENTITYX, Entity.SIZEENTITYX, imagem, nomeAtributo);
                            Game.vidasExtras.add(vidaExtra);
                        } catch (NoSuchFieldException | IllegalAccessException e) {
                            e.printStackTrace();
                        }
                    }
                } else if (Objects.equals(valor[1], "AmmunitionExtra")) {
                    if (pixelAtual == montaCor(valor[0])) {
                        String nomeAtributo = campo.getName().substring(3); // Remove os primeiros três caracteres ("cor")
                        nomeAtributo = nomeAtributo.substring(0, 1).toLowerCase() + nomeAtributo.substring(1);
                        try {
                            Field field = Entity.class.getDeclaredField(nomeAtributo);
                            BufferedImage imagem = (BufferedImage) field.get(Entity.class);
                            AmmunitionExtra ammunitionExtra = new AmmunitionExtra(x * Entity.SIZEENTITYX, y * Entity.SIZEENTITYY, Entity.SIZEENTITYX, Entity.SIZEENTITYX, imagem, nomeAtributo, 5);
                            Game.ammunitionExtras.add(ammunitionExtra);
                        } catch (NoSuchFieldException | IllegalAccessException e) {
                            e.printStackTrace();
                        }
                    }
                } else if (Objects.equals(valor[1], "TrashBag")) {
                    if (pixelAtual == montaCor(valor[0])) {
                        String nomeAtributo = campo.getName().substring(3); // Remove os primeiros três caracteres ("cor")
                        nomeAtributo = nomeAtributo.substring(0, 1).toLowerCase() + nomeAtributo.substring(1);
                        try {
                            Field field = Entity.class.getDeclaredField(nomeAtributo);
                            BufferedImage imagem = (BufferedImage) field.get(Entity.class);
                            TrashBag trashBag = new TrashBag(x * Entity.SIZEENTITYX, y * Entity.SIZEENTITYY, Entity.SIZEENTITYX, Entity.SIZEENTITYX, imagem, nomeAtributo);
                            Game.trashBags.add(trashBag);
                        } catch (NoSuchFieldException | IllegalAccessException e) {
                            e.printStackTrace();
                        }
                    }
                } else if (Objects.equals(valor[1], "KitHealth")) {
                    if (pixelAtual == montaCor(valor[0])) {
                        String nomeAtributo = campo.getName().substring(3); // Remove os primeiros três caracteres ("cor")
                        nomeAtributo = nomeAtributo.substring(0, 1).toLowerCase() + nomeAtributo.substring(1);
                        try {
                            Field field = Entity.class.getDeclaredField(nomeAtributo);
                            BufferedImage imagem = (BufferedImage) field.get(Entity.class);
                            KitHealth kitHealth = new KitHealth(x * Entity.SIZEENTITYX, y * Entity.SIZEENTITYY, Entity.SIZEENTITYX, Entity.SIZEENTITYX, imagem, nomeAtributo);
                            Game.kitHealth.add(kitHealth);
                        } catch (NoSuchFieldException | IllegalAccessException e) {
                            e.printStackTrace();
                        }
                    }
                } else if (Objects.equals(valor[1], "CheckPoint")) {
                    if (pixelAtual == montaCor(valor[0])) {
                        String nomeAtributo = campo.getName().substring(3); // Remove os primeiros três caracteres ("cor")
                        nomeAtributo = nomeAtributo.substring(0, 1).toLowerCase() + nomeAtributo.substring(1);
                        try {
                            Field field = Entity.class.getDeclaredField(nomeAtributo);
                            BufferedImage imagem = (BufferedImage) field.get(Entity.class);
                            CheckPoint checkPoint = new CheckPoint(x * Entity.SIZEENTITYX, y * Entity.SIZEENTITYY, Entity.SIZEENTITYX, Entity.SIZEENTITYX, imagem, nomeAtributo);
                            Game.checkPoints.add(checkPoint);
                        } catch (NoSuchFieldException | IllegalAccessException e) {
                            e.printStackTrace();
                        }
                    }
                } else if (Objects.equals(valor[1], "EscadaTopo") || Objects.equals(valor[1], "Escada") || Objects.equals(valor[1], "EscadaBase")) {
                    int tipo;
                    if (Objects.equals(valor[1], "EscadaTopo")) {
                        tipo = 3;
                    } else if (Objects.equals(valor[1], "Escada")) {
                        tipo = 2;
                    } else {
                        tipo = 1;
                    }
                    if (pixelAtual == montaCor(valor[0])) {
                        String nomeAtributo = campo.getName().substring(3); // Remove os primeiros três caracteres ("cor")
                        nomeAtributo = nomeAtributo.substring(0, 1).toLowerCase() + nomeAtributo.substring(1);
                        try {
                            Field field = Entity.class.getDeclaredField(nomeAtributo);
                            BufferedImage imagem = (BufferedImage) field.get(Entity.class);
                            Escada escada = new Escada(x * Entity.SIZEENTITYX, y * Entity.SIZEENTITYY, Entity.SIZEENTITYX, Entity.SIZEENTITYX, tipo, imagem, nomeAtributo);
                            Game.escada.add(escada);
                        } catch (NoSuchFieldException | IllegalAccessException e) {
                            e.printStackTrace();
                        }
                    }
                } else if (Objects.equals(valor[1], "Inimigo1") || Objects.equals(valor[1], "Inimigo2") || Objects.equals(valor[1], "Inimigo3") || Objects.equals(valor[1], "Inimigo4")) {

                    int tipoInimigo = switch (valor[1]) {
                        case "Inimigo1" -> 1;
                        case "Inimigo2" -> 2;
                        case "Inimigo3" -> 3;
                        default -> 4;
                    };

                    int incremento = 1;
                    if (Inimigo.SIZEENEMYX == 16) {
                        incremento = 2;
                    }

                    if (pixelAtual == montaCor(valor[0])) {

                        String nomeAtributo = campo.getName().substring(3); // Remove os primeiros três caracteres ("cor")
                        nomeAtributo = nomeAtributo.substring(0, 1).toLowerCase() + nomeAtributo.substring(1);

                        Inimigo inimigo = new Inimigo(identificadorUnicoInimigo, x * Inimigo.SIZEENEMYX * incremento, y * Inimigo.SIZEENEMYY * incremento, Inimigo.SIZEENEMYX, Inimigo.SIZEENEMYY, tipoInimigo, Entity.inimigo, nomeAtributo);
                        identificadorUnicoInimigo++;
                        Game.inimigo.add(inimigo);

                    }
                } else if (Objects.equals(valor[1], "Player")) {
                    if (pixelAtual == montaCor(valor[0])) {
                        Game.player.setX(x * Player.getLarguraPlayer());
                        Game.player.setY(y * Player.getAlturaPlayer());
                    }
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }

    public static int montaCor(String corHexaDecimal) {
        int prefixCor = 0xFF;
        return prefixCor << 24 | Integer.parseInt(String.valueOf(corHexaDecimal), 16);
    }
}
