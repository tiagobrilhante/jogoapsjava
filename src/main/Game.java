package main;

import Mundo.Mundo;
import entidades.Entity;
import entidades.naoSolidos.Grama;
import entidades.interativos.*;
import entidades.naoSolidos.*;
import entidades.player.Player;
import gameListeners.GameListeners;
import graficos.Spritsheet;
import graphicInterface.container.GameContainer;
import graphicInterface.screens.*;
import graphicInterface.uI.UserInterface;
import settings.GameSettings;

import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.Serial;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

// importante lembrar
// Entity — todos os comportamentos-base das entidades
// Mundo — aspectos de como as fases são montadas
// ‘Player’ — tudo relacionado a interação do ‘player’ tá aqui


public class Game extends Canvas implements Runnable {

    // serialização (importante para o eclipse)
    @Serial
    private static final long serialVersionUID = 1L;

    public static Game game;
    public GameMenu gameMenu;


    public GameOver gameOver;
    //Historia
    public Historia historia;
    public Controles controles;
    public LeaderBoard leaderBoard;
    public Sobre sobre;



    // thread para buferização
    private Thread thread;
    // usada para definir o fundo a ser aplicado...
    // pode ser usado ou não...
    private final BufferedImage fundoBase;
    // instancia o mundo
    public static Mundo mundo;
    // instancia o player
    public static Player player;
    // MENU




    // instancia a interface do usuário
    public UserInterface ui;
    // poeira na tela
    public DustSpawner dustSpawner;


    //  ----------- LISTAS  -----------  //
    // lista de entidades empregada no game
    public static List<Entity> entidades;
    // prepara a lista de CEU (cenário)
    public static List<Ceu> ceuVetor;
    public static List<MountainsParalax> mountainVetor;
    public static List<WallFundo1> wallFundo1Vetor;
    public static List<PredioFundo1> predioFundo1Vetor;
    public static List<CheckPoint> checkPoints;
    public static List<Nuvens> nuvemVetor;
    public static List<KitHealth> kitHealth;
    public static List<TrashBag> trashBags;
    public static List<VidaExtra> vidasExtras;
    public static List<AmmunitionExtra> ammunitionExtras;
    public static List<Inimigo> inimigo;
    public static List<Grama> grama;
    public static List<Espinho> espinhos;
    public static List<Escada> escada;
    public static List<FundoDarkBrick> darkBricksFundo;
    public static List<TiroPlayer> tirosPLayer;

    // instancia sprites
    public static Spritsheet sprite, spritePlayer, spriteEnemy, ceu, mountain, wallFundo1, predioFundo1, nuvens;


    // objetos base (PATHS)
    public String spriteTerrenoPath = "/res/spritesheets/terrain/spritesheet32.png", spritePlayerPath = "/res/spritesheets/player/spritesheetPlayer3.png",
            spriteEnemyPath = "/res/spritesheets/enemy/spritesheetEnemy.png", spriteCeuPath = "/res/spritesheets/ceusprite.png",
            spriteMountainPath = "/res/spritesheets/mountain1lvlsprite.png", spriteFundo1Path = "/res/spritesheets/spritesheetfundo1.png",
            spriteFundoPredio1Path = "/res/spritesheets/spritepredio.png", spriteNuvemPath = "/res/spritesheets/ceuspriteClouds.png",
            levelPath = "/res/fases/level1.png";


    // se o jogo está rodando... começa em verdadeiro
    private boolean isRunning = true;
    private static boolean isPaused = false;
    public static int timer = 0;
    // definições de level
    // ainda deve implementar a tela inicial do jogo e o gameMenu de opções
    // bem como o game over
    // por enquanto inicia no lvl 1, mas o ocrreto é iniciar no gameMenu
    public static int level = 1;
    public int levelMaximo = 2;
    public static String gameState = "MENU";

    public int temp = 0;


    // método construtor
    public Game() {
        // instancia dos elementos base do jogo
        gameMenu = new GameMenu();
        gameOver = new GameOver();
        historia = new Historia();
        controles= new Controles();
        leaderBoard = new LeaderBoard();
        sobre = new Sobre();

        // chama a user interface
        ui = new UserInterface();

        // chama o fundo (para o container)
        fundoBase = new BufferedImage(GameSettings.getGAME_WIDTH(), GameSettings.getGAME_HEIGHT(), BufferedImage.TYPE_INT_RGB);

        // chama as entidades (classe abstrata)
        // define o sprite a ser usado pelas entidades
        //sprites para as entidades base
        sprite = new Spritsheet(spriteTerrenoPath);
        //sprite para jogador
        spritePlayer = new Spritsheet(spritePlayerPath);
        // sprite para inimigos
        spriteEnemy = new Spritsheet(spriteEnemyPath);
        // sprite para ceu
        ceu = new Spritsheet(spriteCeuPath);
        mountain = new Spritsheet(spriteMountainPath);
        // Sprite de fundo
        wallFundo1 = new Spritsheet(spriteFundo1Path);
        predioFundo1 = new Spritsheet(spriteFundoPredio1Path);
        //sprite de nuvem
        nuvens = new Spritsheet(spriteNuvemPath);

        // lista de entidades
        entidades = new ArrayList<>();
        // lista de ceu
        ceuVetor = new ArrayList<>();
        mountainVetor = new ArrayList<>();
        // lista de vetor de fundo
        wallFundo1Vetor = new ArrayList<>();
        predioFundo1Vetor = new ArrayList<>();
        // lista de nuvens
        nuvemVetor = new ArrayList<>();
        // lista de health kit
        kitHealth = new ArrayList<>();
        // lista de trash bags
        trashBags = new ArrayList<>();
        vidasExtras = new ArrayList<>();
        ammunitionExtras = new ArrayList<>();
        // lista de savepoints
        checkPoints = new ArrayList<>();
        // lista de inimigos
        inimigo = new ArrayList<>();
        // lista de grama
        grama = new ArrayList<>();
        espinhos = new ArrayList<>();
        // lista de escadas
        escada = new ArrayList<>();
        // lista de darkBrick
        darkBricksFundo = new ArrayList<>();
        tirosPLayer = new ArrayList<>();

        // chama o player (de acordo com a posição inicial no sprite)
        player = new Player(0, 0, Player.getLarguraPlayer(), Player.getAlturaPlayer(), spritePlayer.getSprite(0, 0, Player.getLarguraPlayer(), Player.getAlturaPlayer()), "Player");
        // adiciona o player em entidades (só pode haver 1)
        entidades.add(player);

        // spawner de poeira
        dustSpawner = new DustSpawner();

        // por fim carrega o mundo....
        mundo = new Mundo(levelPath);

        new GameContainer(this);
        addKeyListener(new GameListeners(this));
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
        isRunning = true;
        thread = new Thread(this);
        thread.start();
    }

    // para o jogo
    public synchronized void stop() {
        isRunning = false;
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    public void resetVariables() {
        // redefine todas as variáveis para seus valores padrão
        level = 1;
        isPaused = false;
        gameState = "MENU";
        temp = 0;
        // recria as listas de entidades
        entidades = new ArrayList<>();
        ceuVetor = new ArrayList<>();
        mountainVetor = new ArrayList<>();
        wallFundo1Vetor = new ArrayList<>();
        predioFundo1Vetor = new ArrayList<>();
        checkPoints = new ArrayList<>();
        nuvemVetor = new ArrayList<>();
        kitHealth = new ArrayList<>();
        trashBags = new ArrayList<>();
        vidasExtras = new ArrayList<>();
        ammunitionExtras = new ArrayList<>();
        inimigo = new ArrayList<>();
        grama = new ArrayList<>();
        espinhos = new ArrayList<>();
        escada = new ArrayList<>();
        darkBricksFundo = new ArrayList<>();
        tirosPLayer = new ArrayList<>();

        // reinicia o mundo e o jogador
        mundo = new Mundo(levelPath);
        player = new Player(64, 0, Player.getLarguraPlayer(), Player.getAlturaPlayer(), spritePlayer.getSprite(0, 0, Player.getLarguraPlayer(), Player.getAlturaPlayer()), "Player");
        Player.pontos = 0;
        Player.tentativas = 3;
        Player.qtdTiro = 5;
        GameOver.gameoverTimer = 0;
        entidades.add(player);
    }

    public static void restart() {
        // Reiniciar atributos para recomeçar o jogo
        GameMenu.iniciaAudioMenu();
        game.resetVariables();

    }

    // método que realiza ações a cada ciclo de tick do jogo
    public void tick() {


        if (Objects.equals(gameState, "MENU")) {
            gameMenu.choose();
            gameMenu.tick();
        }

        if (Objects.equals(gameState, "GAMEOVER")) {

            gameOver.choose();
            gameOver.tick();
        }

        if (Objects.equals(gameState, "HISTORIA")) {

            historia.choose();
            // atribuo a responsabilidade para o ceu realizar os ticks do gameMenu
            historia.tick();
        }

        if (Objects.equals(gameState, "CONTROLES")) {

            controles.choose();
            // atribuo a responsabilidade para o ceu realizar os ticks do gameMenu
            controles.tick();
        }

        if (Objects.equals(gameState, "LEADERBOARDS")) {

            leaderBoard.choose();
            // atribuo a responsabilidade para o realizar os ticks do leadboards
            leaderBoard.tick();
        }

        if (Objects.equals(gameState, "SOBRE")) {

            sobre.choose();
            // atribuo a responsabilidade para o ceu realizar os ticks do gameMenu
            sobre.tick();
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

            for (int i = 0; i < tirosPLayer.size(); i++) {
                TiroPlayer myShot = tirosPLayer.get(i);
                if (myShot.colisaoInimigo() != null) {
                    Entity inimigo = myShot.colisaoInimigo();
                    player.causaDanoInimigo(0, inimigo);
                    Game.tirosPLayer.remove(myShot);
                } else {
                    // if (myShot.x >= Mundo.WIDTH * 32 || myShot.x < 0 || myShot.colisao((int) myShot.x, (int) myShot.y)) {
                    if (myShot.x >= GameSettings.getGAME_WIDTH() || myShot.x < 0 || myShot.colisao()) {

                        Game.tirosPLayer.remove(myShot);
                    }
                }
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
            for (MountainsParalax entidade : mountainVetor) {
                entidade.tick();
            }

            // atribuo a responsabilidade para o ceu realizar os ticks dos seus filhos
            for (WallFundo1 entidade : wallFundo1Vetor) {
                entidade.tick();
            }

            // atribuo a responsabilidade para o ceu realizar os ticks dos seus filhos
            for (PredioFundo1 entidade : predioFundo1Vetor) {
                entidade.tick();
            }

            // atribuo a responsabilidade para o nuvens realizar os ticks dos seus filhos
            for (Nuvens entidade : nuvemVetor) {
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

            // atribuo a responsabilidade para grama, que é uma entidade não colisora, realize os seus ticks
            for (Espinho entidade : espinhos) {
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

            // atribuo a responsabilidade para tiroPLayer, realize os seus ticks
            for (TiroPlayer entidade : tirosPLayer) {
                entidade.tick();
            }

            dustSpawner.update();
        }

    }

    // renderiza os elementos de acordo com a estratégia de buffer
    public void render() {

        BufferStrategy buffer = this.getBufferStrategy();
        if (buffer == null) {
            this.createBufferStrategy(3);
            return;
        }

        // seta o fundo do jogo quando não há elementos gráficos carregados
        Graphics g = fundoBase.getGraphics();

        g.setColor(new Color(26, 122, 62));
        g.fillRect(0, 0, GameSettings.getGAME_WIDTH() , GameSettings.getGAME_HEIGHT());

        // renderiza as entidades
        // popula o mundo com o vetor do céu (de acordo com a fase)
        for (Ceu ceu : ceuVetor) {
            ceu.render(g);
        }

        for (MountainsParalax mountain : mountainVetor) {
            mountain.render(g);
        }

        // popula o mundo com o vetor do céu (de acordo com a fase)
        for (WallFundo1 wallFundo1 : wallFundo1Vetor) {
            wallFundo1.render(g);
        }

        // popula o mundo com o vetor do céu (de acordo com a fase)
        for (PredioFundo1 predioFundo1 : predioFundo1Vetor) {
            predioFundo1.render(g);
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

        // renderiza vidas extras
        for (VidaExtra entidade : vidasExtras) {
            entidade.render(g);
        }

        // renderiza os ammuBox
        for (AmmunitionExtra entidade : ammunitionExtras) {
            entidade.render(g);
        }

        // renderiza os savepoints
        for (CheckPoint entidade : checkPoints) {
            entidade.render(g);
        }

        // renderiza a grama
        for (Grama entidade : grama) {
            entidade.render(g);
        }

        // renderiza espinhos
        for (Espinho entidade : espinhos) {
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

        // renderiza darkBrickFundo
        for (TiroPlayer entidade : tirosPLayer) {
            entidade.render(g);
        }

        for (Entity entidade : entidades) {
            entidade.render(g);
        }

        // renderiza os inimigos
        for (Inimigo entidade : inimigo) {
            entidade.render(g);
        }

        // renderiza estrelas / poeira / particulas
        dustSpawner.render(g);

        // renderiza a interface do usuário
        ui.render(g);

        // ajusta o buffer do fundo
        g = buffer.getDrawGraphics();
        g.drawImage(fundoBase, 0, 0, GameSettings.getGAME_WIDTH() * GameSettings.getGAME_SCALE(), GameSettings.getGAME_HEIGHT() * GameSettings.getGAME_SCALE(), null);

        if (Objects.equals(gameState, "MENU")) {
            gameMenu.render(g);
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

        if (Objects.equals(gameState, "LEADERBOARDS")) {
            leaderBoard.render(g);
        }

        if (Objects.equals(gameState, "SOBRE")) {
            sobre.render(g);
        }

        if (Objects.equals(gameState, "NORMAL")) {
            Mundo.render(g);
        }

        buffer.show();

    }

    public synchronized void togglePause() {
        isPaused = !isPaused;
        if (!isPaused) {
            synchronized (this) {
                notifyAll(); // notifica todos os threads que estão esperando
            }
        }
    }

    //herdado de runnable (gera os ciclos de FPS)
    // foram implementadas melhorias (possibilidade de pausar o jogo)
    // Otimizações de processamento
    @Override
    public void run() {
        long lastTime = System.nanoTime();
        double amountOfTicks = 60.0;
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;
        double timer = System.currentTimeMillis();
        while (isRunning) {
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            while (delta >= 1) {
                if (!isPaused) {
                    tick();
                    render();
                }
                delta--;
            }
            if (!isPaused) {
                if (System.currentTimeMillis() - timer >= 1000) {
                    timer += 1000;
                }
            }
            try {
                TimeUnit.MILLISECONDS.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        stop();
    }







}
