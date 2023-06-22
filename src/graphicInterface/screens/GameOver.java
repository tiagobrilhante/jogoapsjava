package graphicInterface.screens;


import entidades.player.Player;
import main.Game;
import settings.GameSettings;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class GameOver {
    public String[] options = {"Voltar para o menu", "Sair"};
    public int currentOption = 0;
    public int maxOption = options.length - 1;
    public boolean up, down, ok;
    private BufferedImage imagem;

    public int frames = 0, maxFrames = 25, index = 0, maxIndex = 24;

    public static int gameoverTimer = 0;

    public GameOver() {
        gameoverTimer = 0;
        try {
            ClassLoader classLoader = getClass().getClassLoader();
            InputStream inputStream = classLoader.getResourceAsStream("res/ui/spaceship1small.png");
            assert inputStream != null;
            imagem = ImageIO.read(inputStream);

        } catch (IOException e) {
            System.out.println("Erro ao carregar a imagem: " + e.getMessage());
        }
    }




    public void lerArquivo() {
        try {
            if (Objects.equals(Game.gameState, "GAMEOVER")) {
                File arquivo = new File("src/score/leaderboard.txt");
                Scanner scanner = new Scanner(arquivo);

                // Lê as pontuações existentes e armazena em uma lista
                ArrayList<Integer> numeros = new ArrayList<>();
                ArrayList<String> pessoas = new ArrayList<>();

                while (scanner.hasNextLine()) {
                    String linha = scanner.nextLine();
                    String[] partes = linha.split(" - ");

                    int valor = Integer.parseInt(partes[0]);
                    String nome = partes[1];

                    // adiciona somente se for maior que zero e não for uma pontuação já registrada
                    if (valor > 0 && !pessoas.contains(nome)) {
                        numeros.add(valor);
                        pessoas.add(nome);
                    }
                }
                scanner.close();

                // Adiciona o jogador atual se sua pontuação for maior ou igual a qualquer jogador existente
                int posicao = -1;
                for (int i = 0; i < numeros.size(); i++) {
                    if (Player.pontos >= numeros.get(i)) {
                        posicao = i;
                        break;
                    }
                }

                if (Player.pontos > 0 && (numeros.size() < 3 || posicao != -1)) {
                    JTextField textField = new JTextField();
                    JPanel panel = new JPanel(new GridLayout(0, 1));
                    panel.add(new JLabel("Digite seu nome:"));
                    panel.add(textField);

                    int result = JOptionPane.showOptionDialog(null, panel, "Você obteve um recorde",
                            JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, null, null);

                    if (result == JOptionPane.OK_OPTION) {
                        String nome = textField.getText();
                        if (!nome.isEmpty()) {
                            // Adiciona o novo jogador à lista
                            if (posicao == -1) {
                                numeros.add(Player.pontos);
                                pessoas.add(nome);
                            } else {
                                numeros.add(posicao, Player.pontos);
                                pessoas.add(posicao, nome);
                            }
                        }

                        // Remove o último registro, se necessário
                        if (numeros.size() > 3) {
                            numeros.remove(3);
                            pessoas.remove(3);
                        }

                        // Escreve a lista atualizada no arquivo
                        if (arquivo.canWrite()) {
                            PrintWriter escritor = new PrintWriter(arquivo);
                            for (int i = 0; i < numeros.size(); i++) {
                                escritor.println(numeros.get(i) + " - " + pessoas.get(i));
                            }
                            escritor.close();
                        } else {
                            System.out.println("Não é possível escrever no arquivo.");
                        }
                    }
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Arquivo não encontrado!");
        }
    }


    public void choose() {
        if (down) {
            currentOption++;
            down = false;
            if (currentOption > maxOption) {
                currentOption = 0;
            }
        }
        if (up) {
            currentOption--;
            up = false;
            if (currentOption < 0) {
                currentOption = maxOption;
            }
        }
        if (ok) {
            ok = false;
            if (currentOption == 0) {
                Historia.stopGameAudio();
                //inicia o jogo
                Player.life = Player.maxLife;
                Game.restart();

            }

            if (currentOption == 1) {
                //fecha o jogo
                System.exit(0);
            }
        }
    }

    public void tick() {

        if (gameoverTimer == 0) {
            lerArquivo();
        }
        gameoverTimer++;

        frames++;
        if (frames >= maxFrames / 3) {
            index++;
            frames = 0;
            if (index > maxIndex) {
                index = 0;
            }
        }

    }


    public void render(Graphics g) {

        g.setColor(Color.BLACK);
        g.fillRect(GameSettings.getGAME_WIDTH() / 2, GameSettings.getGAME_HEIGHT() / 2, GameSettings.getGAME_WIDTH(), GameSettings.getGAME_HEIGHT());
        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.BOLD, 30));
        g.drawString("GAME OVER", GameSettings.getGAME_WIDTH() / 2 + 160, GameSettings.getGAME_HEIGHT() / 2 + 50);
        g.setFont(new Font("Times New Roman", Font.BOLD, 20));
        g.drawString("Pontuação: " + Player.pontos, GameSettings.getGAME_WIDTH() / 2 + 200, GameSettings.getGAME_HEIGHT() / 2 + 90);
        g.setFont(new Font("Arial", Font.BOLD, 20));
        g.drawString(options[0], GameSettings.getGAME_WIDTH() / 2 + 80, GameSettings.getGAME_HEIGHT() / 2 + 160);
        g.drawString(options[1], GameSettings.getGAME_WIDTH() / 2 + 80, GameSettings.getGAME_HEIGHT() / 2 + 210);

        if (currentOption == 0) g.drawImage(imagem, GameSettings.getGAME_WIDTH() / 2 + 40, GameSettings.getGAME_HEIGHT() / 2 + 140, null);
        if (currentOption == 1) g.drawImage(imagem, GameSettings.getGAME_WIDTH() / 2 + 40, GameSettings.getGAME_HEIGHT() / 2 + 200, null);

    }

}
