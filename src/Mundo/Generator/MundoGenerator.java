package Mundo.Generator;

import entidades.Entity;
import entidades.interativos.*;
import entidades.naoSolidos.*;
import entidades.player.Player;
import entidades.solidos.Solido;
import main.Game;
import settings.GameSettings;

import java.awt.image.BufferedImage;
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
                } else if (Arrays.asList(GameSettings.arrayFundoDarkBrickBase).contains(valor[1])) {
                    if (pixelAtual == montaCor(valor[0])) {
                        String valorOpcao = valor[1];

                        try {
                            Field field = Entity.class.getField(valorOpcao);
                            BufferedImage fundoDarkBrickValue = (BufferedImage) field.get(null);

                            FundoDarkBrick fundoDarkBrick = new FundoDarkBrick(x * Entity.SIZEENTITYX, y * Entity.SIZEENTITYY, Entity.SIZEENTITYX, Entity.SIZEENTITYY, fundoDarkBrickValue, "CenarioDarkBrick");
                            Game.darkBricksFundo.add(fundoDarkBrick);
                        } catch (NoSuchFieldException | IllegalAccessException e) {
                            // Lidar com exceções
                            e.printStackTrace();
                        }
                    }
                } else if (Arrays.asList(GameSettings.arrayGrama).contains(valor[1])) {
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

                } else if (Arrays.asList(GameSettings.arrayEspinho).contains(valor[1])) {
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
                } else if (Arrays.asList(GameSettings.arrayGalhosSecos).contains(valor[1])) {
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
                } else if (Arrays.asList(GameSettings.arrayWallFundo1).contains(valor[1])) {

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
                } else if (Arrays.asList(GameSettings.arrayLuzWallFundo1).contains(valor[1])) {

                    if (pixelAtual == montaCor(valor[0])) {
                        String nomeAtributo = campo.getName().substring(3); // Remove os primeiros três caracteres ("cor")
                        nomeAtributo = nomeAtributo.substring(0, 1).toLowerCase() + nomeAtributo.substring(1);
                        try {
                            Field field = Entity.class.getDeclaredField(nomeAtributo);
                            BufferedImage imagem = (BufferedImage) field.get(Entity.class);
                            LuzWallFundo1 luzWallFundo1 = new LuzWallFundo1(x* Entity.SIZEENTITYX-16 , y* Entity.SIZEENTITYY , 64, 64, imagem, nomeAtributo);
                            Game.luzWallFundo1Vetor.add(luzWallFundo1);
                        } catch (NoSuchFieldException | IllegalAccessException e) {
                            e.printStackTrace();
                        }
                    }
                }else if (Arrays.asList(GameSettings.arrayPredioFundo1).contains(valor[1])) {
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
                }

                else if (Arrays.asList(GameSettings.arrayCeu).contains(valor[1])) {
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
                } else if (Arrays.asList(GameSettings.arrayMountainParalax).contains(valor[1])) {
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
                } else if (Arrays.asList(GameSettings.arrayVidaExtra).contains(valor[1])) {
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
                } else if (Arrays.asList(GameSettings.arrayAmmunitionExtra).contains(valor[1])) {
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
                } else if (Arrays.asList(GameSettings.arrayTrashBag).contains(valor[1])) {
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
                } else if (Arrays.asList(GameSettings.arrayKitHealth).contains(valor[1])) {
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
                } else if (Arrays.asList(GameSettings.arrayCheckPoint).contains(valor[1])){
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
}
