package main;


import entidades.player.Player;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
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
            InputStream inputStream = classLoader.getResourceAsStream("res/spaceship1small.png");
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

                if (arquivo.length() == 0) {
                    if (Player.pontos > 0) {
                        /*
                        String nome = JOptionPane.showInputDialog(null, "Digite seu nome:");

                        if (nome != null && !nome.isEmpty()) {
                            System.out.println(nome);
                        }
                        */

                        JTextField textField = new JTextField();
                        JPanel panel = new JPanel(new GridLayout(0, 1));
                        panel.add(new JLabel("Digite seu nome:"));
                        panel.add(textField);

                        int result = JOptionPane.showOptionDialog(null, panel, "Você obteve um record",
                                JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, null, null);

                        if (result == JOptionPane.OK_OPTION) {
                            String nome = textField.getText();
                            // salvar o nome do jogador ou fazer outras operações com o nome aqui
                            PrintWriter escritor = new PrintWriter(arquivo);
                            escritor.println(Player.pontos + " - " + nome);
                            escritor.close();
                        }



                    }
                } else {
                    ArrayList<Integer> numeros = new ArrayList<>(); // lista para armazenar os números
                    ArrayList<String> pessoas = new ArrayList<>(); // lista para armazenar os números

                    while (scanner.hasNextLine()) {
                        String linha = scanner.nextLine(); // lê a linha do arquivo
                        String[] partes = linha.split(" ", 2);

                        int valor = Integer.parseInt(partes[0]); // converte o número para inteiro
                        String nome = partes[1]; // recupera o nome
                        numeros.add(valor); // armazena na lista
                        pessoas.add(nome); // armazena na lista
                    }

                    scanner.close();



                    boolean maiorQueTodos = true;

                    for (int i = 0; i < numeros.size(); i++) {
                        if (Player.pontos <= numeros.get(i)) {
                            maiorQueTodos = false;
                            break;
                        }
                    }


                    if (maiorQueTodos) {


                        JTextField textField = new JTextField();
                        JPanel panel = new JPanel(new GridLayout(0, 1));
                        panel.add(new JLabel("Digite seu nome:"));
                        panel.add(textField);

                        int result = JOptionPane.showOptionDialog(null, panel, "Você obteve um record",
                                JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, null, null);

                        if (result == JOptionPane.OK_OPTION) {
                            String nome = textField.getText();
                            // salvar o nome do jogador ou fazer outras operações com o nome aqui
                           /* PrintWriter escritor = new PrintWriter(arquivo);
                            escritor.println(Player.pontos + " - " + nome);
                            escritor.close();
                            */

                        }



                        numeros.add(Player.pontos);
                        numeros.sort(Collections.reverseOrder());
                        if (numeros.size() > 3) {
                            numeros.remove(3);
                        }

                        // imprime a lista
                        PrintWriter escritor = new PrintWriter(arquivo);
                        for (Integer num : numeros) {
                            escritor.println(num);
                        }
                        escritor.close();
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
        g.fillRect(Game.WIDTH / 2, Game.HEIGTH / 2, Game.WIDTH, Game.HEIGTH);
        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.BOLD, 30));
        g.drawString("GAME OVER", Game.WIDTH / 2 + 160, Game.HEIGTH / 2 + 50);
        g.setFont(new Font("Times New Roman", Font.BOLD, 20));
        g.drawString("Pontuação: " + Player.pontos, Game.WIDTH / 2 + 200, Game.HEIGTH / 2 + 90);
        g.setFont(new Font("Arial", Font.BOLD, 20));
        g.drawString(options[0], Game.WIDTH / 2 + 80, Game.HEIGTH / 2 + 160);
        g.drawString(options[1], Game.WIDTH / 2 + 80, Game.HEIGTH / 2 + 210);

        if (currentOption == 0) g.drawImage(imagem, Game.WIDTH / 2 + 40, Game.HEIGTH / 2 + 140, null);
        if (currentOption == 1) g.drawImage(imagem, Game.WIDTH / 2 + 40, Game.HEIGTH / 2 + 200, null);

    }

}
