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
    public static List<LuzWallFundo1> luzWallFundo1Vetor;
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
    public static List<FundoDarkBrick> darkBricksFundo;
    public static List<Escada> escada;
    public static List<TiroPlayer> tirosPLayer;

    // instancia sprites
    public static Spritsheet spriteSolid, spriteDecoration, spriteInterative, spritePlayer, spriteEnemy, ceu, mountain, nuvens;

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

        spriteSolid = new Spritsheet(GameSettings.spriteSolidPath);
        spriteDecoration = new Spritsheet(GameSettings.spriteDecorationPath);
        spriteInterative = new Spritsheet(GameSettings.spriteInterativePath);
        spritePlayer = new Spritsheet(GameSettings.spritePlayerPath);
        spriteEnemy = new Spritsheet(GameSettings.spriteEnemyPath);
        ceu = new Spritsheet(GameSettings.spriteCeuPath);
        mountain = new Spritsheet(GameSettings.spriteMountainPath);
        nuvens = new Spritsheet(GameSettings.spriteNuvemPath);

        entidades = new ArrayList<>();
        ceuVetor = new ArrayList<>();
        mountainVetor = new ArrayList<>();
        wallFundo1Vetor = new ArrayList<>();
        luzWallFundo1Vetor = new ArrayList<>();
        predioFundo1Vetor = new ArrayList<>();
        nuvemVetor = new ArrayList<>();
        kitHealth = new ArrayList<>();
        trashBags = new ArrayList<>();
        vidasExtras = new ArrayList<>();
        ammunitionExtras = new ArrayList<>();
        checkPoints = new ArrayList<>();
        inimigo = new ArrayList<>();
        grama = new ArrayList<>();
        espinhos = new ArrayList<>();
        darkBricksFundo = new ArrayList<>();
        escada = new ArrayList<>();
        tirosPLayer = new ArrayList<>();
        player = new Player(0, 0, Player.getLarguraPlayer(), Player.getAlturaPlayer(), spritePlayer.getSprite(0, 0, Player.getLarguraPlayer(), Player.getAlturaPlayer()), "Player");
        entidades.add(player);

        dustSpawner = new DustSpawner();

        mundo = new Mundo(GameSettings.levelPath+"level"+level+".png");

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
        luzWallFundo1Vetor = new ArrayList<>();
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
        mundo = new Mundo(GameSettings.levelPath+"level"+level+".png");
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

            for (Entity entidade : entidades) {
                entidade.tick();
            }

            for (Ceu entidade : ceuVetor) {
                entidade.tick();
            }

            for (MountainsParalax entidade : mountainVetor) {
                entidade.tick();
            }

            for (LuzWallFundo1 entidade : luzWallFundo1Vetor) {
                entidade.tick();
            }

            for (Nuvens entidade : nuvemVetor) {
                entidade.tick();
            }

            for (Inimigo entidade : inimigo) {
                entidade.tick();
            }

            for (Grama entidade : grama) {
                entidade.tick();
            }

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

        g.setColor(new Color(18, 32, 32));
        g.fillRect(0, 0, GameSettings.getGAME_WIDTH() , GameSettings.getGAME_HEIGHT());

        // renderiza as entidades

        for (Ceu ceu : ceuVetor) {
            ceu.render(g);
        }

        for (MountainsParalax mountain : mountainVetor) {
            mountain.render(g);
        }

        for (WallFundo1 wallFundo1 : wallFundo1Vetor) {
            wallFundo1.render(g);
        }

        for (LuzWallFundo1 luzWallFundo1 : luzWallFundo1Vetor) {
            luzWallFundo1.render(g);
        }

        for (PredioFundo1 predioFundo1 : predioFundo1Vetor) {
            predioFundo1.render(g);
        }

        for (Nuvens nuvem : nuvemVetor) {
            nuvem.render(g);
        }
        //-----------------//
        //   INTERATIVOS   //
        //-----------------//
        for (KitHealth entidade : kitHealth) {
            entidade.render(g);
        }

        for (TrashBag entidade : trashBags) {
            entidade.render(g);
        }

        for (VidaExtra entidade : vidasExtras) {
            entidade.render(g);
        }

        for (AmmunitionExtra entidade : ammunitionExtras) {
            entidade.render(g);
        }

        for (CheckPoint entidade : checkPoints) {
            entidade.render(g);
        }

        for (Espinho entidade : espinhos) {
            entidade.render(g);
        }

        //-----------------//
        //   DECORATIVOS   //
        //-----------------//

        for (Grama entidade : grama) {
            entidade.render(g);
        }

        for (FundoDarkBrick entidade : darkBricksFundo) {
            entidade.render(g);
        }

        for (Escada entidade : escada) {
            entidade.render(g);
        }

        //-----------------//
        //      TIROS      //
        //-----------------//

        for (TiroPlayer entidade : tirosPLayer) {
            entidade.render(g);
        }

        for (Entity entidade : entidades) {
            entidade.render(g);
        }

        //-----------------//
        //    INIMIGOS     //
        //-----------------//
        for (Inimigo entidade : inimigo) {
            entidade.render(g);
        }

        //-----------------//
        //      DUST       //
        //-----------------//

        dustSpawner.render(g);

        //-----------------//
        //       UI        //
        //-----------------//

        ui.render(g);

        //-----------------//
        //     BUFFER      //
        //-----------------//

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

    // MÉTODO RUN APRIMORADO
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
