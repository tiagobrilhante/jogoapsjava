package entidades.player;

import Mundo.Audio;
import Mundo.Camera;
import Mundo.Mundo;
import entidades.Entity;
import entidades.interativos.*;
import entidades.naoSolidos.Particula;
import entidades.solidos.Solido;
import main.Game;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


// aqui tem coisas bem importantes sobre a mecânica do player
public class Player extends Entity {

    // movimentação basica do player
    public boolean right, left, down, up;

    public static String selectedWeapon;

    public static String soundPathAttack = "src/res/sounds/soundfx/attack.wav";
    public static String soundPathJump = "src/res/sounds/soundfx/jump.wav";
    public static String soundPathTiro = "src/res/sounds/soundfx/go.wav";

    Audio audioAttack = new Audio(null, false);
    Audio audioJump = new Audio(null, false);
    Audio audioTiro = new Audio(null, false);

    public static double atualX, atualY;

    // velocidade de deslocamento do player
    public double speed = 2.9;

    // vida inicial e máximo de vida permitido no jogo
    public static double life = 100, maxLife = 100;

    // o player começa a fase virado para a direita
    public static int direita = 1;
    public static int esquerda = 0;
    public  static int direcaoAtual = direita;

    // o player começa parado
    public int movimentacao = 0;

    public boolean emEscada;

    public static int pontos = 0;

    // tamanho base dos plocos de player
    public static int SIZEPLAYERX = 16, SIZEPLAYERY = 16;

    // máscara do player
    public int maskx = 0, masky = 0, maskw = SIZEPLAYERX, maskh = SIZEPLAYERY;

    // frames e indexes de movimentação do sprite do player
    public int frames = 0, maxFrames = 5, index = 0, maxIndex = 3, indexAtack = 0;


    // buffer de geração de imagem do player em movimento
    public BufferedImage[] playerRight, playerLeft, playerIdleLeft, playerIdleRigth, playerJumpLeft, playerJumpRight, playerEscada, playerAttackEsquerda, playerAttackEsquerdaArma, playerAttackDireita, playerAttackDireitaArma;

    // se o player está pulando (jump é o momento que aperto o pulo, isJump é enquanto o pulo está acontecendo)
    public boolean jump = false, isJump = false;

    public boolean attack = false;
    public int attackTimeSound = 0;
    public int jumpTimeSound = 0;

    // altura de incremento do pulo
    public int jumpHeigth = 64;

    // velocidade do pulo
    public int jumpSpeed = 4;
    // frame inicial do pulo
    public int jumpFrames = 0;

    // inimigo
    public Inimigo enemy;

    // kits de vida
    public KitHealth vida;
    public TrashBag trashBag;
    public VidaExtra vidaExtra;

    public static int qtdTiro = 5;

    public boolean posTopoEscada;
    public int yTopoEscada;

    // posicionamento do player
    public int posx, posy;

    // indice de dano (será alterado face a cada ameaça)
    public double damageFactor = 0.40;
    public static int tentativas = 3;
    public double damageFactorEspinho = 0.20;

    // o quanto de vida um kit de recuperação recupera (no futuro poderão existir tipos de kits diferentes)
    public int indiceRecuperacaoKitVida = 10;

    public int timerPlayer = 0, tempoParado;
    public int timerEnemy = 0;

    public int timerNoDamageEnemy = 0;

    public boolean noDamageStateEnemy = false;

    public List<Particula> particulas = new ArrayList<>();

    // construtor
    public Player(int x, int y, int width, int height, BufferedImage sprite) {
        super(x, y, width, height, sprite);
        selectedWeapon = "Cano";

        // quantidade de posições da animação do player (quando andando para a direita)
        playerRight = new BufferedImage[4];
        // quantidade de posições da animação do player (quando andando para a esquerda)
        playerLeft = new BufferedImage[4];
        // array para idle
        playerIdleLeft = new BufferedImage[4];
        playerIdleRigth = new BufferedImage[4];

        playerJumpLeft = new BufferedImage[4];
        playerJumpRight = new BufferedImage[4];
        playerEscada = new BufferedImage[4];
        playerAttackEsquerda = new BufferedImage[4];
        playerAttackEsquerdaArma = new BufferedImage[4];
        playerAttackDireita = new BufferedImage[4];
        playerAttackDireitaArma = new BufferedImage[4];

        // loop para montar o array para a direita
        for (int i = 0; i < 4; i++) {
            playerRight[i] = Game.spritePlayer.getSprite((i * SIZEPLAYERX), 0, SIZEPLAYERX, SIZEPLAYERY);
        }

        // loop para montar o array para a esquerda
        for (int i = 0; i < 4; i++) {
            playerLeft[i] = Game.spritePlayer.getSprite(48 - (i * SIZEPLAYERX), 16, SIZEPLAYERX, SIZEPLAYERY);
        }

        // loop para montar o array para a idle direita
        for (int i = 0; i < 4; i++) {
            playerIdleRigth[i] = Game.spritePlayer.getSprite((i * SIZEPLAYERX), 32, SIZEPLAYERX, SIZEPLAYERY);
        }

        // loop para montar o array para a idle esquerda
        for (int i = 0; i < 4; i++) {
            playerIdleLeft[i] = Game.spritePlayer.getSprite(48 - (i * SIZEPLAYERX), 48, SIZEPLAYERX, SIZEPLAYERY);
        }

        // loop para montar o array para a jump direita
        for (int i = 0; i < 4; i++) {
            playerJumpRight[i] = Game.spritePlayer.getSprite(80 + (i * SIZEPLAYERX), 0, SIZEPLAYERX, SIZEPLAYERY);
        }

        // loop para montar o array para a jump esquerda
        for (int i = 0; i < 4; i++) {
            playerJumpLeft[i] = Game.spritePlayer.getSprite(128 - (i * SIZEPLAYERX), 16, SIZEPLAYERX, SIZEPLAYERY);
        }

        // loop para montar o array para escada
        for (int i = 0; i < 4; i++) {
            playerEscada[i] = Game.spritePlayer.getSprite(80 + (i * SIZEPLAYERX), 48, SIZEPLAYERX, SIZEPLAYERY);
        }

        // loop para montar o array para ataque
        for (int i = 0; i < 4; i++) {
            playerAttackEsquerda[i] = Game.spritePlayer.getSprite(0, 64, SIZEPLAYERX * 2, SIZEPLAYERY);
        }

        for (int i = 0; i < 4; i++) {
            playerAttackEsquerdaArma[i] = Game.spritePlayer.getSprite(32, 64, SIZEPLAYERX * 2, SIZEPLAYERY);
        }

        // loop para montar o array para ataque
        for (int i = 0; i < 4; i++) {
            playerAttackDireita[i] = Game.spritePlayer.getSprite(0, 80, SIZEPLAYERX * 2, SIZEPLAYERY);
        }

        // loop para montar o array para ataque
        for (int i = 0; i < 4; i++) {
            playerAttackDireitaArma[i] = Game.spritePlayer.getSprite(32, 80, SIZEPLAYERX * 2, SIZEPLAYERY);
        }

    }

    public void tick() {

        // aqui eu inicio a movimentação em parado ==== 0
        movimentacao = 0;
        atualX = (int) x;
        atualY = (int) y;
        timerPlayer++;
        double rawTime = (double) timerPlayer / 60;
        tempoParado = (int) rawTime;
        timerEnemy++;


        // reset de invulnerabilidade do inimigo
        if (noDamageStateEnemy) {
            timerNoDamageEnemy++;
            if (timerNoDamageEnemy == 20) {
                noDamageStateEnemy = false;
                timerNoDamageEnemy = 0;
            }
        }

        // gerenciamento de update das particulas (quando o inimigo é derrotado)
        for (int i = 0; i < particulas.size(); i++) {
            particulas.get(i).update();
            Particula part = particulas.get(i);
            // duração da particula na tela (otimização)
            if (part.timer >= 100) {
                particulas.remove(part);
            }
        }

        // testa se estou em uma escada
        emEscada = colisaoEscada(this.getX(), this.getY());

        // Gravidade
        // Situação de ação da GRAVIDADE e MOVIMENTO de PLAYER em ESCADAS (pois desafia a gravidade)
        if (!colisao((int) x, (int) (y + 1)) && !isJump && !emEscada) {
            // não existe a colisão não estou em pulo e nem em escada
            // situação normal do player
            // se não existe colisão, eu sou afetado pela gravidade
            // quando estou em uma escada, a gravidade não me afeta (posso subir e descer)
            // quando estou em pulo, eu desafio a gravidade pelo tempo do pulo
            y += 4; // índice de velocidade de queda
        } else {
            // regras para movimento em escadas
            if (emEscada && !colisao((int) x, (int) (y))) {
                if (down) {
                    y += speed;
                    y = (int) y;
                }
                if (up) {
                    y -= speed;
                    y = (int) y;
                }
                movimentacao = 1;
                timerPlayer = 0;
            } else {
                // nesse caso, eu tento me colocar no nivel y da entidade solida ao qual eu colido
                // evita que o player fique travado no chão
                Rectangle playerRect = new Rectangle((int) x + maskx, (int) y + masky, maskw, maskh);

                for (int i = 0; i < Game.entidades.size(); i++) {
                    Entity entidade = Game.entidades.get(i);
                    if (entidade instanceof Solido) {
                        Rectangle solido = new Rectangle(entidade.getX() + maskx, entidade.getY() + masky, Entity.SIZEENTITYX, Entity.SIZEENTITYY);
                        if (playerRect.intersects(solido)) {
                            this.y = entidade.getY() - (SIZEPLAYERY);
                        }
                    }
                }
            }

        }

        // caso eu me movimente para a direita
        if (right && !colisao((int) (x + speed), this.getY()) && x <= (Mundo.WIDTH * Entity.SIZEENTITYX) - SIZEPLAYERX) {
            x += speed;
            movimentacao = 1;
            timerPlayer = 0;
            direcaoAtual = direita;
        }

        // caso eu me movimente para a esquerda
        if (left && !colisao((int) (x - speed), this.getY()) && x >= 0) {
            x -= speed;
            movimentacao = 1;
            timerPlayer = 0;
            direcaoAtual = esquerda;
        }

        // momento do pulo (habilita a condição de estar pulando)
        if (jump) {
            if (colisao(this.getX(), this.getY() + 1)) {
                isJump = true;
                timerPlayer = 0;
            }
        }

        // durante a execução do pulo (comportamento)
        if (isJump) {
            timerPlayer = 0;

            if (jumpTimeSound == 0) {
                audioJump = new Audio(soundPathJump, false); // Chamando a classe aonde está o audio.
                audioJump.start();
            }
            jumpTimeSound++;


            if (!colisao(this.getX(), this.getY() - 2)) {
                y -= jumpSpeed;
                jumpFrames += jumpSpeed;
                if (jumpFrames == jumpHeigth) {
                    isJump = false;
                    jump = false;
                    jumpFrames = 0;
                }
            } else {
                isJump = false;
                jumpTimeSound = 0;
                jump = false;
                jumpFrames = 0;
            }

        } else {
            jumpTimeSound = 0;
        }

        // ação de ataque em relação ao inimigo (com o cano)
        if (attack) {

            if (Objects.equals(selectedWeapon, "Cano")) {
                // aqui existe a colisão de ataque pelo cano (passo a minha posição)
                if (ataqueCano(this.getX(), this.getY())) {

                    // movimenta o inimigo na direção oposta a que eu me encontro
                    // direita
                    if (direcaoAtual == 1) {

                        // executo o loop por 19 ticks (índice de afastamento)
                        for (int j = 0; j < 19; j++) {
                            if (!enemy.colisao(enemy.getX(), enemy.getY())) {
                                // se não houver objeto para colidir, movimento o inimigo para a direita
                                enemy.setX(enemy.getX() + 1);

                            } else {
                                // nesse caso existe objeto para colidir
                                enemy.setX(enemy.getX() - 3);
                            }

                        }
                    } else {
                        for (int j = 0; j < 19; j++) {
                            if (!enemy.colisao(enemy.getX(), enemy.getY())) {
                                // se não houver objeto para colidir, movimento o inimigo para a direita
                                enemy.setX(enemy.getX() - 1);
                            } else {
                                // nesse caso existe objeto para colidir
                                enemy.setX(enemy.getX() + 3);
                            }

                        }

                    }

                    causaDanoInimigo(timerNoDamageEnemy);

                }
            } else {

                if (attackTimeSound == 0) {
                    // o tick é muito rápido
                    if (qtdTiro > 0) {
                        audioTiro = new Audio(soundPathTiro, false); // Chamando a classe aonde está o audio.
                        audioTiro.start();

                        if (direcaoAtual == 1) {
                            Game.tirosPLayer.add(new TiroPlayer(this.getX() - Camera.x + 10, this.getY() - Camera.y, 50, 50, null));
                        } else {
                            Game.tirosPLayer.add(new TiroPlayer(this.getX() - Camera.x -10, this.getY() - Camera.y, 50, 50, null));
                        }
                    }

                    if (qtdTiro > 0) {
                        qtdTiro--;
                    } else {
                        selectedWeapon = "Cano";
                    }
                }


            }

            timerPlayer = 0;
            movimentacao = 1;
            frames++;

            if (frames == maxFrames) {
                indexAtack++;
                frames = 0;
                if (indexAtack > maxIndex) {
                    indexAtack = 0;
                }
            }

        }

        // movimentação do player
        if (movimentacao == 1) {
            timerPlayer = 0;
            frames++;
            if (frames == maxFrames) {
                index++;
                frames = 0;
                if (index > maxIndex) {
                    index = 0;
                }
            }

        } else {

            // em pulo
            if (isJump) {
                timerPlayer = 0;
                frames++;
                if (frames == maxFrames) {
                    index++;
                    frames = 0;
                    if (index > maxIndex) {
                        index = 0;
                    }
                }
            } else {

                // comportamento em idle
                if (tempoParado > 4) {
                    frames++;
                    if (frames == maxFrames) {
                        index++;
                        frames = 0;
                        if (index > maxIndex) {
                            index = 0;
                        }
                    }
                }
            }
        }

        // dano contra o player (deverá levar em consideração a ameaça)
        if (damage((int) (x + speed), this.getY())) {
            life -= damageFactor;
        }

        // kit de vida (se tiver com a vida cheia não pega, caso contrário pega, recupera a vida e remove da tela)
        if (vida(this.getX(), this.getY()) && life < 100) {
            life += indiceRecuperacaoKitVida;
            if (life > 100) {
                life = 100;
            }
            Game.kitHealth.remove(vida);
        }

        // kit de vida (se tiver com a vida cheia não pega, caso contrário pega, recupera a vida e remove da tela)
        if (pegaLixo(this.getX(), this.getY())) {
            pontos++;
            Game.trashBags.remove(trashBag);
        }

        // vidas extras para o player
        if (pegaVidaExtra(this.getX(), this.getY())) {
            tentativas++;
            Game.vidasExtras.remove(vidaExtra);
        }

        // guarda a posição da placa de checkpoint
        if (checkPoint(this.getX(), this.getY())) {
            posx = this.getX();
            posy = this.getY();
        }

        // dano de espinhos
        if (danoEspinho(this.getX(), this.getY())) {
            life -= damageFactorEspinho;
        }

        // caindo fora da tela
        if (y >= (Mundo.HEIGHT * Entity.SIZEENTITYY)) {
            life -= damageFactor * 4;
        }

        // quaando a energia acaba
        if (life <= 0) {

            tentativas--;

            setX(posx);
            setY(posy);

            life = 100;

            if (tentativas == 0) {
                Game.gameState = "GAMEOVER";
            }
        }

        // posicionamento da camera, sempre em relaçao ao player
        Camera.x = Camera.Clamp(this.getX() - (Game.WIDTH / 2), 0, (Mundo.WIDTH * Entity.SIZEENTITYX) - SIZEPLAYERX);
        Camera.y = Camera.Clamp(this.getY() - (Game.HEIGTH / 2), 0, (Mundo.HEIGHT * Entity.SIZEENTITYY) - SIZEPLAYERY);

    }

    // colisor base do player
    public boolean colisao(int nextx, int nexty) {

        Rectangle playerRectangle = new Rectangle(nextx + maskx, nexty + masky, maskw, maskh);

        for (int i = 0; i < Game.entidades.size(); i++) {
            Entity entidade = Game.entidades.get(i);
            if (entidade instanceof Solido) {
                Rectangle solido = new Rectangle(entidade.getX() + maskx, entidade.getY() + masky, Entity.SIZEENTITYX, Entity.SIZEENTITYY);
                if (playerRectangle.intersects(solido)) {
                    return true;
                }
            }
        }
        return false;
    }

    // checkpoint (player toca o checkpoint)
    public boolean checkPoint(int nextx, int nexty) {
        Rectangle player = new Rectangle(nextx + maskx, nexty + masky, maskw, maskh);

        for (int i = 0; i < Game.checkPoints.size(); i++) {
            Entity checkPoint = Game.checkPoints.get(i);
            if (checkPoint != null) {
                Rectangle placa = new Rectangle(checkPoint.getX() + maskx, checkPoint.getY() + masky, Entity.SIZEENTITYX, Entity.SIZEENTITYY);
                if (player.intersects(placa)) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean danoEspinho(int nextx, int nexty) {
        Rectangle player = new Rectangle(nextx + maskx, nexty + masky, maskw, maskh);

        for (int i = 0; i < Game.espinhos.size(); i++) {
            Entity espinho = Game.espinhos.get(i);
            if (espinho != null) {
                Rectangle espinhoRetangulo = new Rectangle(espinho.getX() + maskx, espinho.getY() + masky, Entity.SIZEENTITYX, Entity.SIZEENTITYY);
                if (player.intersects(espinhoRetangulo)) {
                    return true;

                }
            }
        }
        return false;
    }

    // player toma dano dos inimigos no contato
    public boolean damage(int nextx, int nexty) {
        Rectangle player = new Rectangle(nextx + maskx, nexty + masky, maskw, maskh);
        for (int i = 0; i < Game.inimigo.size(); i++) {
            Inimigo entidade = Game.inimigo.get(i);
            if (entidade != null) {
                Rectangle solido = new Rectangle(entidade.getX() + maskx, entidade.getY() + masky, Entity.SIZEENTITYX, Entity.SIZEENTITYY);
                if (player.intersects(solido)) {
                    enemy = entidade;
                    return true;
                }
            }
        }
        return false;
    }

    public void causaDanoInimigo(int timerNoDamageEnemy) {
        if (timerNoDamageEnemy == 0) {
            // removo a vida do inimigo
            enemy.life--;

            noDamageStateEnemy = true;
            if (enemy.life == 0) {
                // adiciona particulas da explosao

                for (int i = 0; i < 100; i++) {
                    particulas.add(new Particula(enemy.getX() - Camera.x, enemy.getY() - Camera.y, 3, 3, Color.BLUE));
                }

                Game.inimigo.remove(enemy);

            }

        }

    }

    public boolean ataqueCano(int nextx, int nexty) {

        int incremento = 0;
        if (direcaoAtual == esquerda) {
            incremento -= Player.SIZEPLAYERX;
        }

        Rectangle retanguloPlayer = new Rectangle(nextx + maskx + incremento, nexty + masky, maskw + Player.SIZEPLAYERX, maskh);

        for (int i = 0; i < Game.inimigo.size(); i++) {
            Inimigo inimigo = Game.inimigo.get(i);
            if (inimigo != null) {
                Rectangle retanguloInimigo = new Rectangle(inimigo.getX() + maskx, inimigo.getY() + masky, Entity.SIZEENTITYX, Entity.SIZEENTITYY);
                if (retanguloPlayer.intersects(retanguloInimigo)) {
                    enemy = inimigo;
                    return true;
                }
            }
        }
        return false;
    }

    // player colide com o kit de vida
    public boolean vida(int nextx, int nexty) {
        Rectangle player = new Rectangle(nextx + maskx, nexty + masky, maskw, maskh);
        for (int i = 0; i < Game.kitHealth.size(); i++) {
            KitHealth kitHealth = Game.kitHealth.get(i);
            if (kitHealth != null) {
                Rectangle kitVida = new Rectangle(kitHealth.getX() + maskx, kitHealth.getY() + masky, Entity.SIZEENTITYX, Entity.SIZEENTITYY);
                if (player.intersects(kitVida)) {
                    vida = kitHealth;
                    return true;
                }
            }
        }
        return false;
    }

    public boolean pegaLixo(int nextx, int nexty) {
        Rectangle player = new Rectangle(nextx + maskx, nexty + masky, maskw, maskh);
        for (int i = 0; i < Game.trashBags.size(); i++) {
            TrashBag trashBagAtivo = Game.trashBags.get(i);
            if (trashBagAtivo != null) {
                Rectangle trashRetangle = new Rectangle(trashBagAtivo.getX() + maskx, trashBagAtivo.getY() + masky, Entity.SIZEENTITYX, Entity.SIZEENTITYY);
                if (player.intersects(trashRetangle)) {
                    trashBag = trashBagAtivo;
                    return true;
                }
            }
        }
        return false;
    }

    public boolean pegaVidaExtra(int nextx, int nexty) {
        Rectangle player = new Rectangle(nextx + maskx, nexty + masky, maskw, maskh);
        for (int i = 0; i < Game.vidasExtras.size(); i++) {
            VidaExtra vidaExtraAtivo = Game.vidasExtras.get(i);
            if (vidaExtraAtivo != null) {
                Rectangle trashRetangle = new Rectangle(vidaExtraAtivo.getX() + maskx, vidaExtraAtivo.getY() + masky, Entity.SIZEENTITYX, Entity.SIZEENTITYY);
                if (player.intersects(trashRetangle)) {
                    vidaExtra = vidaExtraAtivo;
                    return true;
                }
            }
        }
        return false;
    }

    public synchronized void toggleWeapon() {

        if (Objects.equals(selectedWeapon, "Cano")) {
            if (qtdTiro > 0) {
                selectedWeapon = "Arma";
            }
        } else {
            selectedWeapon = "Cano";
        }
    }

    public boolean colisaoEscada(int nextx, int nexty) {
        Rectangle retanguloPlayer = new Rectangle(nextx + maskx, nexty + masky, maskw, maskh);
        for (int i = 0; i < Game.escada.size(); i++) {
            Escada escada = Game.escada.get(i);

            Rectangle retanguloEscada = new Rectangle(escada.getX() + maskx, escada.getY() + masky, Entity.SIZEENTITYX, Entity.SIZEENTITYY);
            if (retanguloPlayer.intersects(retanguloEscada)) {
                if (escada.tipoEscada == 3) {
                    posTopoEscada = true;
                    yTopoEscada = escada.getY();
                } else {
                    posTopoEscada = false;
                }

                return true;
            }

        }

        return false;
    }


    // renderiza tudão
    public void render(Graphics g) {

        // quando anda para a direita
        if (direcaoAtual == direita && movimentacao == 1) {
            if (isJump) {
                g.drawImage(playerJumpRight[index], this.getX() - Camera.x, this.getY() - Camera.y, null);
            } else if (emEscada) {
                g.drawImage(playerEscada[index], this.getX() - Camera.x, this.getY() - Camera.y, null);
            } else {
                g.drawImage(playerRight[index], this.getX() - Camera.x, this.getY() - Camera.y, null);
            }
        }

        // quando para de andar para a direita (mantem o corpo voltado pra direita)
        // animação de Idle quando o tempo for maior que 4
        if (direcaoAtual == direita && movimentacao == 0) {

            if (isJump) {

                g.drawImage(playerJumpRight[index], this.getX() - Camera.x, this.getY() - Camera.y, null);
            } else if (emEscada) {
                g.drawImage(playerEscada[index], this.getX() - Camera.x, this.getY() - Camera.y, null);
            } else {

                if (tempoParado > 4) {
                    g.drawImage(playerIdleRigth[index], this.getX() - Camera.x, this.getY() - Camera.y, null);
                } else {
                    g.drawImage(playerRight[0], this.getX() - Camera.x, this.getY() - Camera.y, null);
                }
            }

        }

        // quando movimenta para a esquerda
        if (direcaoAtual == esquerda && movimentacao == 1) {
            if (isJump) {
                g.drawImage(playerJumpLeft[index], this.getX() - Camera.x, this.getY() - Camera.y, null);
            } else if (emEscada) {
                g.drawImage(playerEscada[index], this.getX() - Camera.x, this.getY() - Camera.y, null);
            } else {
                g.drawImage(playerLeft[index], this.getX() - Camera.x, this.getY() - Camera.y, null);
            }
        }

        // quando para de andar para a esquerda (mantem o corpo voltado pra esquerda)
        // animação de Idle quando o tempo for maior que 4
        if (direcaoAtual == esquerda && movimentacao == 0) {

            if (isJump) {
                g.drawImage(playerJumpLeft[index], this.getX() - Camera.x, this.getY() - Camera.y, null);
            } else if (emEscada) {
                g.drawImage(playerEscada[index], this.getX() - Camera.x, this.getY() - Camera.y, null);
            } else {

                if (tempoParado > 4) {
                    g.drawImage(playerIdleLeft[index], this.getX() - Camera.x, this.getY() - Camera.y, null);
                } else {
                    g.drawImage(playerLeft[0], this.getX() - Camera.x, this.getY() - Camera.y, null);
                }
            }
        }

        // renderiza as particulas se houverem
        for (Particula particula : particulas) {
            particula.render(g);
        }

        // ataque (em testes)
        if (attack) {

            if (attackTimeSound == 0) {
                if (selectedWeapon == "Cano") {
                    audioAttack = new Audio(soundPathAttack, false); // Chamando a classe aonde está o audio.
                    audioAttack.start();
                }
            }
            attackTimeSound++;

            if (direcaoAtual == esquerda) {
                if (Objects.equals(selectedWeapon, "Cano")) {
                    g.drawImage(playerAttackEsquerda[indexAtack], this.getX() - Camera.x - Player.SIZEPLAYERX, this.getY() - Camera.y, null);
                } else {
                    g.drawImage(playerAttackEsquerdaArma[indexAtack], this.getX() - Camera.x - Player.SIZEPLAYERX, this.getY() - Camera.y, null);
                }

            } else {
                if (Objects.equals(selectedWeapon, "Cano")) {
                    g.drawImage(playerAttackDireita[indexAtack], this.getX() - Camera.x, this.getY() - Camera.y, null);
                } else {
                    g.drawImage(playerAttackDireitaArma[indexAtack], this.getX() - Camera.x, this.getY() - Camera.y, null);
                }
            }
            if (indexAtack == 3) {
                attack = false;
                indexAtack = 0;
                attackTimeSound = 0;
            }

        }

        // fazer a animação de dash

        // fazer a animação de tiro

        // fazer a animação de arma de impacto


    }
}
