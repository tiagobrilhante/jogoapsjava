package main;
// teste

import Mundo.Mundo;
import entidades.Entity;
import entidades.Grama;
import entidades.interativos.Escada;
import entidades.interativos.Inimigo;
import entidades.interativos.KitHealth;
import entidades.interativos.TrashBag;
import entidades.naoSolidos.*;
import entidades.player.Player;
import graficos.Spritsheet;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.Serial;
import java.util.ArrayList;
import java.util.List;

// importante lembrar
// Entity — todos os comportamentos-base das entidades
// Mundo — aspectos de como as fases são montadas
// ‘Player’ — tudo relacionado a interação do ‘player’ tá aqui


public class Game extends Canvas implements Runnable, KeyListener {

    // serialização (importante para o eclipse)
    @Serial
    private static final long serialVersionUID = 1L;

    // Usa o JFRAME para criar a área do jogo
    public static JFrame jFrame;

    // thread para buferização
    private Thread thread;

    public int timer = 0;
    public StarSpawner starSpawner;

    // se o jogo está rodando... começa em verdadeiro
    private boolean isRuning = true;

    // tamanho da tela, tem que ser ajustado
    public static int WIDTH = 520, HEIGTH = 292, SCALE = 2;

    // usada para definir o fundo a ser aplicado...
    // pode ser usado ou não...
    private BufferedImage fundo;

    // lista de entidades empregada no game
    public static List<Entity> entidades;

    // instancia sprites
    public static Spritsheet sprite, spritePlayer, spriteEnemy, ceu, wallFundo1, nuvens;

    // instancia o mundo
    public static Mundo mundo;

    // instancia o player
    public static Player player;

    // prepara a lista de CEU (cenário)
    public static List<Ceu> ceuVetor;
    public static List<WallFundo1> wallFundo1Vetor;

    public static List<Nuvens> nuvemVetor;

    // popula a tela com kits de vida para o player (lista)
    public static List<KitHealth> kitHealth;

    // popula a tela com TrashBags (lista)
    public static List<TrashBag> trashBags;

    // lista de inimigos (instancia)
    public static List<Inimigo> inimigo;

    // elemento de cenário (entidade com algumas condições especiais)
    public static List<Grama> grama;

    public static List<Escada> escada;
    public static List<FundoDarkBrick> darkBricksFundo;

    // chama o menu
    public static String gameState = "MENU";
    private Menu menu;
    // instancia a interface do usuário
    // tem que ser melhorada
    public UserInterface ui;

    public String spriteGamePath = "/res/spritesheets/spritesheet32.png";
    public String spritePlayerPath = "/res/spritesheets/spritesheetPlayer.png";
    public String spriteEnemyPath = "/res/spritesheets/spritesheetEnemy.png";
    public String spriteCeuPath = "/res/spritesheets/ceusprite.png";
    public String spriteFundo1Path = "/res/spritesheets/spritesheetfundo1.png";
    public String spriteNuvemPath = "/res/spritesheets/ceuspriteClouds.png";
    public String levelPath = "/res/fases/level1.png";


    public String gameName = "JOGO APS";

    // definições de level
    // ainda deve implementar a tela inicial do jogo e o menu de opções
    // bem como o game over
    // por enquanto inicia no lvl 1, mas o ocrreto é iniciar no menu
    public static int level = 1;
    public int levelMaximo = 2;

    // método construtor
    public Game() {

        menu = new Menu();

        // escutador de teclado
        addKeyListener(this);

        // ajusta a preferência do tamanho do container do jogo
        this.setPreferredSize(new Dimension(WIDTH * SCALE, HEIGTH * SCALE));

        // inicia os parametros de frames (FPS)
        initFrame();
        // chama a user interface
        ui = new UserInterface();
        // chama o fundo
        fundo = new BufferedImage(WIDTH, HEIGTH, BufferedImage.TYPE_INT_RGB);


        // chama as entidades (classe abstrata)
        entidades = new ArrayList<>();
        // define o sprite a ser usado pelas entidades
        sprite = new Spritsheet(spriteGamePath);
        spritePlayer = new Spritsheet(spritePlayerPath);
        spriteEnemy = new Spritsheet(spriteEnemyPath);
        // chama o céu
        ceuVetor = new ArrayList<>();
        wallFundo1Vetor = new ArrayList<>();
        nuvemVetor = new ArrayList<>();
        // define o sprite a ser usado pelo ceu
        ceu = new Spritsheet(spriteCeuPath);
        wallFundo1 = new Spritsheet(spriteFundo1Path);
        nuvens = new Spritsheet(spriteNuvemPath);
        // chama os kits de vida
        kitHealth = new ArrayList<>();
        trashBags = new ArrayList<>();
        // chama os inimigos
        inimigo = new ArrayList<>();
        // chama a grama que não tem colisão
        grama = new ArrayList<>();
        // escadas
        escada = new ArrayList<>();
        darkBricksFundo = new ArrayList<>();
        // chama o player (de acordo com a posição inicial no sprite)
        player = new Player(0, 0, Player.SIZEPLAYERX, Player.SIZEPLAYERY, spritePlayer.getSprite(0, 0, Player.SIZEPLAYERX, Player.SIZEPLAYERY));
        // adiciona o player em entidades (só pode haver 1)
        entidades.add(player);

        starSpawner = new StarSpawner();


        // por fim carrega o mundo....
        //isso provavelmente vai ser modificado para carregar a tela inicial primeiro,
        // depois o menu...
        // e por fim, o jogo.
        mundo = new Mundo(levelPath);
    }

    // definições do frame onde o jogo vai aparecer
    public void initFrame() {
        jFrame = new JFrame(gameName);
        jFrame.add(this);
        // não permito que a janela seja redimensionada
        // poderá mudar ao longo do tempo, mas temos apenas 3 meses para terminar o jogo
        // não vamos perder tempo tendo que estudar resoluções de tela no momento...
        // preocupação para o futuro
        jFrame.setResizable(false);
        jFrame.pack();
        // aqui eu ajusto a posição relativa da janela (geralmente no meio da tela)
        jFrame.setLocationRelativeTo(null);
        // comportamento esperado quando eu aperto o botão de fechar a janela
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setFocusableWindowState(true);
        // torno visível a janela
        jFrame.setVisible(true);
        jFrame.setFocusable(true);
        jFrame.toFront();
        jFrame.requestFocus();
    }

    // main do jogo
    // instancio um novo jogo pelo construtor e
    // chamo o método start
    public static void main(String[] args) {

        Game game = new Game();
        game.start();

    }

    // método que realmente inicializa o jogo
    public synchronized void start() {
        thread = new Thread(this);
        isRuning = true;
        thread.start();
    }

    // para o jogo
    public synchronized void stop() {
        isRuning = false;
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    // método que realiza ações a cada ciclo de tick do jogo
    public void tick() {

        if (gameState == "MENU") {

            menu.choose();
            // atribuo a responsabilidade para o ceu realizar os ticks do menu
            menu.tick();
        }

        if (gameState == "NORMAL") {
            timer++;

            // se não houver lixo, vai pra proxiuma fase
            if (trashBags.size() == 0) {
                // level inicia em 1 (lá em cima) teste
                level++;
                // caso o level (após o incremento) seja maior do que o level máximo, voltaremos para a fase 1
                // isso será substituído pela tela de finalização do jogo (jogo zerado)
                if (level > levelMaximo) {
                    level = 1;
                }

                // aqui eu monto a string do arquivo base pra enviar para que o mundo carregue a nova fase
                String Level = "level" + level + ".png";
                Mundo.newlevel(Level);
            }

            // atribuo a responsabilidade para a entidade realizar os ticks dos seus filhos
            for (Entity entidade : entidades) {
                entidade.tick();
            }


            // atribuo a responsabilidade para o ceu realizar os ticks dos seus filhos
            for (Ceu entidade : ceuVetor) {
                entidade.tick();
            }


            // atribuo a responsabilidade para o ceu realizar os ticks dos seus filhos
            for (WallFundo1 entidade : wallFundo1Vetor) {
                entidade.tick();
            }

            // atribuo a responsabilidade para o nuvens realizar os ticks dos seus filhos
            for (Nuvens entidade : nuvemVetor) {
                entidade.tick();
            }

            // atribuo a responsabilidade para o kitHealth (vida) realizar os ticks dos seus filhos
            for (KitHealth entidade : kitHealth) {
                entidade.tick();
            }

            // atribuo a responsabilidade para o kitHealth (vida) realizar os ticks dos seus filhos
            for (TrashBag entidade : trashBags) {
                entidade.tick();
            }

            // atribuo a responsabilidade para o inimigo realizar os ticks dos seus filhos
            for (Inimigo entidade : inimigo) {
                entidade.tick();
            }

            // atribuo a responsabilidade para grama, que é uma entidade não colisora, realize os seus ticks
            for (Grama entidade : grama) {
                entidade.tick();
            }

            // atribuo a responsabilidade para escadas, que é uma entidade não colisora, realize os seus ticks
            for (Escada entidade : escada) {
                entidade.tick();
            }

            // atribuo a responsabilidade para darkBricks, que é uma entidade não colisora, realize os seus ticks
            for (FundoDarkBrick entidade : darkBricksFundo) {
                entidade.tick();
            }

            starSpawner.update();
        }
    }

    // renderiza os elementos de acordo com a estratégia de buffer
    public void render() {

        BufferStrategy buffer = this.getBufferStrategy();
        if (buffer == null) {
            // 3 é um bom número para manter a fluidez em 60 FPS com a resolução aplicada
            this.createBufferStrategy(3);
            return;
        }

        // seta o fundo do jogo quando não há elementos gráficos carregados
        Graphics g = fundo.getGraphics();

        //g.setColor(new Color(0, 0, 0, 0));
        g.setColor(new Color(0, 0, 0));
        g.fillRect(0, 0, WIDTH, HEIGTH);

        // renderiza o mundo
        Mundo.render(g);

        // popula o mundo com o vetor do céu (de acordo com a fase)
        for (Ceu ceu : ceuVetor) {
            ceu.render(g);
        }

        // popula o mundo com o vetor do céu (de acordo com a fase)
        for (WallFundo1 wallFundo1 : wallFundo1Vetor) {
            wallFundo1.render(g);
        }

        // popula o mundo com o vetor do céu (de acordo com a fase)
        for (Nuvens nuvem : nuvemVetor) {
            nuvem.render(g);
        }

        // renderiza os kits de vida
        for (KitHealth entidade : kitHealth) {
            entidade.render(g);
        }

        // renderiza os trashBags
        for (TrashBag entidade : trashBags) {
            entidade.render(g);
        }

        // renderiza os inimigos
        for (Inimigo entidade : inimigo) {
            entidade.render(g);
        }

        // renderiza a grama
        for (Grama entidade : grama) {
            entidade.render(g);
        }

        // renderiza escadas
        for (Escada entidade : escada) {
            entidade.render(g);
        }

        // renderiza darkBrickFundo
        for (FundoDarkBrick entidade : darkBricksFundo) {
            entidade.render(g);
        }

        // renderiza as entidades
        for (Entity entidade : entidades) {
            entidade.render(g);
        }

        // renderiza estrelas / poeira / particulas
        starSpawner.render(g);

        // renderiza a interface do usuário
        ui.render(g);

        // ajusta o buffer do fundo
        g = buffer.getDrawGraphics();
        g.drawImage(fundo, 0, 0, WIDTH * SCALE, HEIGTH * SCALE, null);

        if (gameState == "MENU") {
            menu.render(g);
        } if (gameState == "NORMAL"){
            Mundo.render(g);
        }

        buffer.show();

    }

    //herdado de runnable (gera os ciclos de FPS)
    @Override
    public void run() {

        long lastTime = System.nanoTime();
        double amountOfTicks = 60.0f;
        double ms = 1000000000 / amountOfTicks;
        double delta = 0;
        int frames = 0;
        double timer = System.currentTimeMillis();

        while (isRuning) {
            long now = System.nanoTime();
            delta += (now - lastTime) / ms;
            lastTime = now;
            if (delta > 1) {
                tick();
                render();
                frames++;
                delta--;
            }
            if (System.currentTimeMillis() - timer >= 1000) {
                // mostra no console os fps... eu comentei por dificulta quando queremos
                // ter retorno no console de alguma informação
                //System.out.println("FPS : " + frames);
                frames = 0;
                timer += 1000;
            }
        }
        stop();
    }

    // herda de keylistener
    // ouve as teclas do jogo
    // método para tecla após apertada e solta
    @Override
    public void keyTyped(KeyEvent e) {
        // não vamos trabalhar nela
    }

    // herda de keylistener
    // método para tecla no momento que é apertada
    @Override
    public void keyPressed(KeyEvent e) {
        // temos que ter cuidado aqui, pois as teclas podem significar eventos diferentes de acordo com a fase
        // menu, tela inicial e game over tb
        if (gameState == "NORMAL") {
            if (e.getKeyCode() == KeyEvent.VK_D || e.getKeyCode() == KeyEvent.VK_RIGHT) {
                // tecla D movimenta o player para a direita
                player.right = true;
            } else if (e.getKeyCode() == KeyEvent.VK_A || e.getKeyCode() == KeyEvent.VK_LEFT) {
                // tecla A movimenta o player para a esquerda
                player.left = true;
            } else if (e.getKeyCode() == KeyEvent.VK_W || e.getKeyCode() == KeyEvent.VK_UP) {
                // tecla W movimenta pra cima (usado só em determindaos momentos do jogo)
                player.up = true;
            } else if (e.getKeyCode() == KeyEvent.VK_S || e.getKeyCode() == KeyEvent.VK_DOWN) {
                // tecla S movimenta pra baixo (usado só em determindaos momentos do jogo)
                player.down = true;
            }
            if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                // tecla SPACE faz o plauer pular
                player.jump = true;
            }
            if (e.getKeyCode() == KeyEvent.VK_C) {
                // tecla C faz o plauer atacar
                player.attack = true;
            }
        }

        if (gameState == "MENU") {
            if (e.getKeyCode() == KeyEvent.VK_W || e.getKeyCode() == KeyEvent.VK_UP) {
                // tecla W movimenta pra cima (usado só em determindaos momentos do jogo)
                menu.up = true;
            } else if (e.getKeyCode() == KeyEvent.VK_S || e.getKeyCode() == KeyEvent.VK_DOWN) {
                // tecla S movimenta pra baixo (usado só em determindaos momentos do jogo)
                menu.down = true;
            }
            if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                // tecla SPACE faz o plauer pular
                menu.ok = true;
            }
        }


    }

    // herda de keylistener
    // momento em que a tlecla apertada é solta
    @Override
    public void keyReleased(KeyEvent e) {
        if (gameState == "NORMAL") {
            if (e.getKeyCode() == KeyEvent.VK_D || e.getKeyCode() == KeyEvent.VK_RIGHT) {
                // tecla D movimenta o player para a direita
                player.right = false;
            } else if (e.getKeyCode() == KeyEvent.VK_A || e.getKeyCode() == KeyEvent.VK_LEFT) {
                // tecla A movimenta o player para a esquerda
                player.left = false;
            } else if (e.getKeyCode() == KeyEvent.VK_W || e.getKeyCode() == KeyEvent.VK_UP) {
                // tecla W movimenta pra cima (usado só em determindaos momentos do jogo)
                player.up = false;
            } else if (e.getKeyCode() == KeyEvent.VK_S || e.getKeyCode() == KeyEvent.VK_DOWN) {
                // tecla S movimenta pra baixo (usado só em determindaos momentos do jogo)
                player.down = false;
            }
            if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                // tecla SPACE faz o plauer pular
                player.jump = false;
            }
        }

        if (gameState == "MENU") {
            if (e.getKeyCode() == KeyEvent.VK_W || e.getKeyCode() == KeyEvent.VK_UP) {
                // tecla W movimenta pra cima (usado só em determindaos momentos do jogo)
                menu.up = false;
            } else if (e.getKeyCode() == KeyEvent.VK_S || e.getKeyCode() == KeyEvent.VK_DOWN) {
                // tecla S movimenta pra baixo (usado só em determindaos momentos do jogo)
                menu.down = false;
            }
            if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                // tecla SPACE faz o plauer pular
                menu.ok = false;
            }
        }
    }


}
