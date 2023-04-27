package main;

import Mundo.Mundo;
import entidades.Entity;
import entidades.Grama;
import entidades.interativos.*;
import entidades.naoSolidos.*;
import entidades.player.Player;
import graficos.Spritsheet;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.Serial;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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
    // tamanho da tela, tem que ser ajustado
    public static int WIDTH = 520, HEIGTH = 292, SCALE = 2;

    public static Game game;
    // thread para buferização
    private Thread thread;
    // usada para definir o fundo a ser aplicado...
    // pode ser usado ou não...
    private BufferedImage fundo;
    // instancia o mundo
    public static Mundo mundo;
    // instancia o player
    public static Player player;
    // MENU
    private final Menu menu;
    private final GameOver gameOver;
    //Historia
    private final Historia historia;
    //controles
    private final Controles controles;
    // instancia a interface do usuário
    public UserInterface ui;
    // poeira na tela
    public StarSpawner starSpawner;


    //  ----------- LISTAS  -----------  //
    // lista de entidades empregada no game
    public static List<Entity> entidades;
    // prepara a lista de CEU (cenário)
    public static List<Ceu> ceuVetor;
    public static List<WallFundo1> wallFundo1Vetor;
    public static List<CheckPoint> checkPoints;
    public static List<Nuvens> nuvemVetor;
    public static List<KitHealth> kitHealth;
    public static List<TrashBag> trashBags;
    public static List<Inimigo> inimigo;
    public static List<Grama> grama;
    public static List<Escada> escada;
    public static List<FundoDarkBrick> darkBricksFundo;

    // instancia sprites
    public static Spritsheet sprite, spritePlayer, spriteEnemy, ceu, wallFundo1, nuvens;


    // objetos base (PATHS)
    public String spriteGamePath = "/res/spritesheets/spritesheet32.png", spritePlayerPath = "/res/spritesheets/spritesheetPlayer.png",
            spriteEnemyPath = "/res/spritesheets/spritesheetEnemy.png", spriteCeuPath = "/res/spritesheets/ceusprite.png",
            spriteFundo1Path = "/res/spritesheets/spritesheetfundo1.png", spriteNuvemPath = "/res/spritesheets/ceuspriteClouds.png",
            levelPath = "/res/fases/level1.png";


    // se o jogo está rodando... começa em verdadeiro
    private boolean isRuning = true;
    private static boolean isPaused = false;
    public int timer = 0;
    // definições de level
    // ainda deve implementar a tela inicial do jogo e o menu de opções
    // bem como o game over
    // por enquanto inicia no lvl 1, mas o ocrreto é iniciar no menu
    public static int level = 1;
    public int levelMaximo = 2;
    public static String gameName = "JOGO APS", gameState = "MENU";

    public int temp = 0;

    // método construtor
    public Game() {

        menu = new Menu();
        gameOver = new GameOver();
        historia = new Historia();
        controles = new Controles();

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
        // define o sprite a ser usado pelas entidades
        //sprites para as entidades base
        sprite = new Spritsheet(spriteGamePath);
        //sprite para jogador
        spritePlayer = new Spritsheet(spritePlayerPath);
        // sprite para inimigos
        spriteEnemy = new Spritsheet(spriteEnemyPath);
        // sprite para ceu
        ceu = new Spritsheet(spriteCeuPath);
        // Sprite de fundo
        wallFundo1 = new Spritsheet(spriteFundo1Path);
        //sprite de nuvem
        nuvens = new Spritsheet(spriteNuvemPath);

        // lista de entidades
        entidades = new ArrayList<>();
        // lista de ceu
        ceuVetor = new ArrayList<>();
        // lista de vetor de fundo
        wallFundo1Vetor = new ArrayList<>();
        // lista de nuvens
        nuvemVetor = new ArrayList<>();
        // lista de health kit
        kitHealth = new ArrayList<>();
        // lista de trash bags
        trashBags = new ArrayList<>();
        // lista de savepoints
        checkPoints = new ArrayList<>();
        // lista de inimigos
        inimigo = new ArrayList<>();
        // lista de grama
        grama = new ArrayList<>();
        // lista de escadas
        escada = new ArrayList<>();
        // lista de darkBrick
        darkBricksFundo = new ArrayList<>();


        // chama o player (de acordo com a posição inicial no sprite)
        player = new Player(0, 0, Player.SIZEPLAYERX, Player.SIZEPLAYERY, spritePlayer.getSprite(0, 0, Player.SIZEPLAYERX, Player.SIZEPLAYERY));
        // adiciona o player em entidades (só pode haver 1)
        entidades.add(player);

        // spawner de poeira
        starSpawner = new StarSpawner();

        // por fim carrega o mundo....
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

        jFrame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowActivated(WindowEvent e) {
                jFrame.requestFocusInWindow();
            }
        });

        jFrame.setFocusable(true);

        // pede o foco para a janela assim que ela aparecer
        KeyboardFocusManager.getCurrentKeyboardFocusManager().clearGlobalFocusOwner();
        KeyboardFocusManager.getCurrentKeyboardFocusManager().clearFocusOwner();
        jFrame.requestFocusInWindow();

        jFrame.toFront();
        jFrame.requestFocus();
        // torno visível a janela
        jFrame.setVisible(true);

    }

    // main do jogo
    // instancio um novo jogo pelo construtor e
    // chamo o método start
    public static void main(String[] args) {

        game = new Game();
        game.start();

    }

    // método que realmente inicializa o jogo
    public synchronized void start() {
        isRuning = true;
        thread = new Thread(this);
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

    public static void restart() {
        // Reiniciar atributos para recomeçar o jogo
        Menu.iniciaAudioMenu();
        gameState = "MENU";

    }


    // método que realiza ações a cada ciclo de tick do jogo
    public void tick() {
        temp++;
        System.out.println("TIC: " + temp + " - StatusRunning: " + isRuning + " - gamestate: " + gameState);

        if (Objects.equals(gameState, "MENU")) {

            menu.choose();
            // atribuo a responsabilidade para o ceu realizar os ticks do menu
            menu.tick();
        }

        if (Objects.equals(gameState, "GAMEOVER")) {

            gameOver.choose();
            gameOver.tick();
        }

        if (Objects.equals(gameState, "HISTORIA")) {

            historia.choose();
            // atribuo a responsabilidade para o ceu realizar os ticks do menu
            historia.tick();
        }

        if (Objects.equals(gameState, "CONTROLES")) {

            controles.choose();
            // atribuo a responsabilidade para o ceu realizar os ticks do menu
            controles.tick();
        }

        if (Objects.equals(gameState, "NORMAL")) {
            timer++;

            // se não houver lixo, vai pra proxima fase
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

        // renderiza as entidades
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

        // renderiza os savepoints
        for (CheckPoint entidade : checkPoints) {
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

        if (Objects.equals(gameState, "MENU")) {
            menu.render(g);
        }

        if (Objects.equals(gameState, "GAMEOVER")) {
            gameOver.render(g);
        }

        if (Objects.equals(gameState, "HISTORIA")) {
            historia.render(g);
        }

        if (Objects.equals(gameState, "CONTROLES")) {
            controles.render(g);
        }

        if (Objects.equals(gameState, "NORMAL")) {
            Mundo.render(g);
        }

        buffer.show();

    }

    //herdado de runnable (gera os ciclos de FPS)
    // foram implementadas melhorias (possibilidade de pausar o jogo)
    // Otimizações de processamento
    @Override
    public void run() {
        long lastTime = System.nanoTime();
        double amountOfTicks = 60.0f;
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;
        double timer = System.currentTimeMillis();

        while (isRuning) {
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            while (delta >= 1) {
                if (!isPaused) { // verificação de pausa
                    tick();
                    render();
                }
                delta--;
            }
            if (!isPaused) { // verificação de pausa
                if (System.currentTimeMillis() - timer >= 1000) {
                    timer += 1000;
                }
            }

            // loop de espera para limitar o uso da CPU
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
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

    public synchronized void togglePause() {
        isPaused = !isPaused;
        if (!isPaused) {
            synchronized (this) {
                notifyAll(); // notifica todos os threads que estão esperando
            }
        }
    }

    // herda de keylistener
    // método para tecla no momento que é apertada
    @Override
    public void keyPressed(KeyEvent e) {
        // temos que ter cuidado aqui, pois as teclas podem significar eventos diferentes de acordo com a fase
        // menu, tela inicial e game over tb
        if (Objects.equals(gameState, "NORMAL")) {
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

            if (e.getKeyCode() == KeyEvent.VK_P) {
                // tecla P faz o jogo pausar
                if (e.getKeyCode() == KeyEvent.VK_P) {
                    togglePause();
                }
            }
        }

        if (Objects.equals(gameState, "HISTORIA")) {
            if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                // tecla W movimenta pra cima (usado só em determindaos momentos do jogo)
                historia.end = true;
            }
        }

        if (Objects.equals(gameState, "CONTROLES")) {
            if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                // tecla W movimenta pra cima (usado só em determindaos momentos do jogo)
                controles.end = true;
            }
        }

        if (Objects.equals(gameState, "MENU")) {
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

        if (Objects.equals(gameState, "GAMEOVER")) {
            if (e.getKeyCode() == KeyEvent.VK_W || e.getKeyCode() == KeyEvent.VK_UP) {
                // tecla W movimenta pra cima (usado só em determindaos momentos do jogo)
                gameOver.up = true;
            } else if (e.getKeyCode() == KeyEvent.VK_S || e.getKeyCode() == KeyEvent.VK_DOWN) {
                // tecla S movimenta pra baixo (usado só em determindaos momentos do jogo)
                gameOver.down = true;

            }
            if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                // tecla SPACE faz o plauer pular
                gameOver.ok = true;
            }
        }

    }

    // herda de keylistener
    // momento em que a tlecla apertada é solta
    @Override
    public void keyReleased(KeyEvent e) {
        if (Objects.equals(gameState, "NORMAL")) {
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

        if (Objects.equals(gameState, "MENU")) {
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
