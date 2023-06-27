package Mundo.Generator;

import entidades.Entity;
import entidades.interativos.*;
import entidades.naoSolidos.*;
import entidades.player.Player;
import entidades.solidos.Solido;
import main.Game;
import settings.GameSettings;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Objects;

public class MundoGenerator {
    static int identificadorUnicoInimigo = 1;

    public static void injetorEntidades(int x, int y, int pixelAtual) {
        GameSettings.Objetos objetos = new GameSettings.Objetos();
        Class<?> classe = objetos.getClass();
        Field[] campos = classe.getDeclaredFields();

        for (Field campo : campos) {
            campo.setAccessible(true);
            try {
                Object campoValor = campo.get(objetos);

                String[] valor = (String[]) campoValor;

                if (Arrays.asList(GameSettings.arraySolido).contains(valor[1])) {
                    if (pixelAtual == montaCor(valor[0])) {
                        Game.entidades.add(new Solido(x * Entity.SIZEENTITYX, y * Entity.SIZEENTITYY, Entity.SIZEENTITYX, Entity.SIZEENTITYY, Entity.entityGenerator(pegaNomeAttr(campo)), pegaNomeAttr(campo)));
                    }
                } else if (Arrays.asList(GameSettings.arrayFundoDarkBrickBase).contains(valor[1])) {
                    if (pixelAtual == montaCor(valor[0])) {
                        Game.darkBricksFundo.add(new FundoDarkBrick(x * Entity.SIZEENTITYX, y * Entity.SIZEENTITYY, Entity.SIZEENTITYX, Entity.SIZEENTITYY, Entity.entityGenerator(pegaNomeAttr(campo)), pegaNomeAttr(campo)));
                    }
                } else if (Arrays.asList(GameSettings.arrayGrama).contains(valor[1])) {
                    if (pixelAtual == montaCor(valor[0])) {
                        Game.entidades.add(new Grama(x * Entity.SIZEENTITYX, y * Entity.SIZEENTITYY, Entity.SIZEENTITYX, Entity.SIZEENTITYY, Entity.entityGenerator(pegaNomeAttr(campo)), pegaNomeAttr(campo)));
                    }
                } else if (Arrays.asList(GameSettings.arrayEspinho).contains(valor[1])) {
                    if (pixelAtual == montaCor(valor[0])) {
                        Game.espinhos.add(new Espinho(x * Entity.SIZEENTITYX, y * Entity.SIZEENTITYY, Entity.SIZEENTITYX, Entity.SIZEENTITYY, Entity.entityGenerator(pegaNomeAttr(campo)), pegaNomeAttr(campo)));
                    }
                } else if (Arrays.asList(GameSettings.arrayGalhosSecos).contains(valor[1])) {
                    if (pixelAtual == montaCor(valor[0])) {
                        Game.entidades.add(new GalhosSecos(x * Entity.SIZEENTITYX, y * Entity.SIZEENTITYY, Entity.SIZEENTITYX, Entity.SIZEENTITYY, Entity.entityGenerator(pegaNomeAttr(campo)), pegaNomeAttr(campo)));
                    }
                } else if (Arrays.asList(GameSettings.arrayWallFundo1).contains(valor[1])) {
                    if (pixelAtual == montaCor(valor[0])) {
                        Game.wallFundo1Vetor.add(new WallFundo1(x * Entity.SIZEENTITYX, y * Entity.SIZEENTITYY, Entity.SIZEENTITYX, Entity.SIZEENTITYX, Entity.entityGenerator(pegaNomeAttr(campo)), pegaNomeAttr(campo)));
                    }
                } else if (Arrays.asList(GameSettings.arrayLuzWallFundo1).contains(valor[1])) {
                    if (pixelAtual == montaCor(valor[0])) {
                        Game.luzWallFundo1Vetor.add(new LuzWallFundo1(x * Entity.SIZEENTITYX - 16, y * Entity.SIZEENTITYY, 64, 64, Entity.entityGenerator(pegaNomeAttr(campo)), pegaNomeAttr(campo)));
                    }
                } else if (Arrays.asList(GameSettings.arrayPredioFundo1).contains(valor[1])) {
                    if (pixelAtual == montaCor(valor[0])) {
                        Game.predioFundo1Vetor.add(new PredioFundo1(x * Entity.SIZEENTITYX, y * Entity.SIZEENTITYY, Entity.SIZEENTITYX, Entity.SIZEENTITYX, Entity.entityGenerator(pegaNomeAttr(campo)), pegaNomeAttr(campo)));
                    }
                } else if (Arrays.asList(GameSettings.arrayCeu).contains(valor[1])) {
                    if (pixelAtual == montaCor(valor[0])) {
                        Game.ceuVetor.add(new Ceu(x * Entity.SIZEENTITYX, y * Entity.SIZEENTITYY, Entity.SIZEENTITYX, Entity.SIZEENTITYX, Entity.entityGenerator(pegaNomeAttr(campo)), pegaNomeAttr(campo)));
                    }
                } else if (Arrays.asList(GameSettings.arrayMountainParalax).contains(valor[1])) {
                    if (pixelAtual == montaCor(valor[0])) {
                        Game.mountainVetor.add(new MountainsParalax(x * Entity.SIZEENTITYX, y * Entity.SIZEENTITYY, Entity.SIZEENTITYX, Entity.SIZEENTITYX, Entity.entityGenerator(pegaNomeAttr(campo)), pegaNomeAttr(campo)));
                    }
                } else if (Arrays.asList(GameSettings.arrayVidaExtra).contains(valor[1])) {
                    if (pixelAtual == montaCor(valor[0])) {
                        Game.vidasExtras.add(new VidaExtra(x * Entity.SIZEENTITYX, y * Entity.SIZEENTITYY, Entity.SIZEENTITYX, Entity.SIZEENTITYX, Entity.entityGenerator(pegaNomeAttr(campo)), pegaNomeAttr(campo)));
                    }
                } else if (Arrays.asList(GameSettings.arrayAmmunitionExtra).contains(valor[1])) {
                    if (pixelAtual == montaCor(valor[0])) {
                        Game.ammunitionExtras.add(new AmmunitionExtra(x * Entity.SIZEENTITYX, y * Entity.SIZEENTITYY, Entity.SIZEENTITYX, Entity.SIZEENTITYX, Entity.entityGenerator(pegaNomeAttr(campo)), pegaNomeAttr(campo), 5));
                    }
                } else if (Arrays.asList(GameSettings.arrayTrashBag).contains(valor[1])) {
                    if (pixelAtual == montaCor(valor[0])) {
                        Game.trashBags.add(new TrashBag(x * Entity.SIZEENTITYX, y * Entity.SIZEENTITYY, Entity.SIZEENTITYX, Entity.SIZEENTITYX, Entity.entityGenerator(pegaNomeAttr(campo)), pegaNomeAttr(campo)));
                    }
                } else if (Arrays.asList(GameSettings.arrayKitHealth).contains(valor[1])) {
                    if (pixelAtual == montaCor(valor[0])) {
                        Game.kitHealth.add(new KitHealth(x * Entity.SIZEENTITYX, y * Entity.SIZEENTITYY, Entity.SIZEENTITYX, Entity.SIZEENTITYX, Entity.entityGenerator(pegaNomeAttr(campo)), pegaNomeAttr(campo)));
                    }
                } else if (Arrays.asList(GameSettings.arrayCheckPoint).contains(valor[1])) {
                    if (pixelAtual == montaCor(valor[0])) {
                        Game.checkPoints.add(new CheckPoint(x * Entity.SIZEENTITYX, y * Entity.SIZEENTITYY, Entity.SIZEENTITYX, Entity.SIZEENTITYX, Entity.entityGenerator(pegaNomeAttr(campo)), pegaNomeAttr(campo)));
                    }
                } else if (Arrays.asList(GameSettings.arrayEscadas).contains(valor[1])) {
                    int tipo;
                    if (Arrays.asList(GameSettings.arrayEscadasTopo).contains(valor[1])) {
                        tipo = 3;
                    } else if (Arrays.asList(GameSettings.arrayEscadasBase).contains(valor[1])) {
                        tipo = 1;
                    } else {
                        tipo = 2;
                    }
                    if (pixelAtual == montaCor(valor[0])) {
                        Game.escada.add(new Escada(x * Entity.SIZEENTITYX, y * Entity.SIZEENTITYY, Entity.SIZEENTITYX, Entity.SIZEENTITYX, tipo, Entity.entityGenerator(pegaNomeAttr(campo)), pegaNomeAttr(campo)));
                    }
                } else if (Arrays.asList(GameSettings.arrayInimigos).contains(valor[1])) {

                    int tipoInimigo = 0;
                    String valorOpcao = valor[1];

                    try {
                        tipoInimigo = Integer.parseInt(valorOpcao.replaceAll("[^0-9]", ""));
                    } catch (NumberFormatException e) {
                        System.out.println("Tipo de Inimigo não Encontrado");
                    }

                    if (pixelAtual == montaCor(valor[0])) {

                        String nomeAtributo = campo.getName().substring(3); // Remove os primeiros três caracteres ("cor")
                        nomeAtributo = nomeAtributo.substring(0, 1).toLowerCase() + nomeAtributo.substring(1);

                        Inimigo inimigo = new Inimigo(identificadorUnicoInimigo, x * Inimigo.SIZEENEMYX, y * Inimigo.SIZEENEMYY, Inimigo.SIZEENEMYX, Inimigo.SIZEENEMYY, tipoInimigo, Entity.inimigo, nomeAtributo);
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

    public static String pegaNomeAttr(Field campo) {
        String nomeAtributo = campo.getName().substring(3); // Remove os primeiros três caracteres ("cor")
        nomeAtributo = nomeAtributo.substring(0, 1).toLowerCase() + nomeAtributo.substring(1);
        return nomeAtributo;
    }
}
