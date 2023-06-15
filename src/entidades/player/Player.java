package entidades.player;

import Mundo.Camera;
import Mundo.Mundo;
import entidades.Entity;
import entidades.interativos.*;
import entidades.naoSolidos.Particula;
import entidades.solidos.Solido;
import graphicInterface.screens.GameOver;
import main.Game;
import settings.GameSettings;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


// aqui tem coisas bem importantes sobre a mecânica do player
public class Player extends Entity {

    // -------------------------------------- //
    // --------Atributos da Entidade--------- //
    // -------------------------------------- //

    // tamanho base dos plocos de player (x = largura, y = altura)
    private final static int SIZEPLAYERX = 32, SIZEPLAYERY = 48;

    // ------------------------------------------- //
    // --------Atributos de Vida e energia-------- //
    // ------------------------------------------- //

    // vida inicial e máximo de vida permitido no jogo
    public static double life = 100, maxLife = 100;

    // numero de vidas que o player tem
    public static int tentativas = 3;

    // -------------------------------------- //
    // --------Atributos de Movimento-------- //
    // -------------------------------------- //

    // movimentação basica do player
    public boolean right, left, down, up;
    // posição atual
    public static int atualX, atualY;
    // localização com relação ao save
    public static int posSaveX, posSaveY;

    // o player começa a fase virado para a direita
    public static String direcaoAtual = "direita";
    // velocidade de deslocamento do player
    public double speed = 2.9;
    // o player começa parado
    public boolean emMovimento = false;
    public boolean emColisao;

    public final int TAXADEQUEDA = 4;

    // ######################## //
    // ####### Escada ######### //
    // ######################## //
    // inicia fora de escada / apoio para saber se está ou n ão no topo da escada

    public boolean emEscada;
    public boolean posTopoEscada, posBottomEscada;
    public int yTopoEscada;

    // frames e indexes de movimentação do sprite do player
    public int frames = 0, maxFrames = 5,
            index = 0,
            maxIndex = 3, indexAtack = 0;

    // buffer de geração de imagem do player em movimento
    public BufferedImage[] playerRight, playerLeft, playerIdleLeft, playerIdleRigth, playerJumpLeft, playerJumpRight, playerEscada, playerAttackEsquerda, playerAttackEsquerdaArma, playerAttackDireita, playerAttackDireitaArma;

    // ######################## //
    // ######## Pulo ########## //
    // ######################## //

    // se o player está pulando (jump é o momento que aperto o pulo, isJump é enquanto o pulo está acontecendo)
    public boolean jump = false, isJump = false;
    // atributos de jump
    public int jumpTimeSound = 0, jumpHeigth = 64, jumpSpeed = 4, jumpFrames = 0;

    // ######################## //
    // ####### Ataque ######### //
    // ######################## //
    public boolean attack = false;
    public static String selectedWeapon;
    public int attackTimeSound = 0;
    public int timerDamageControllPlayer = 0;
    public int timerDamageControllResetPlayer = 0;
    // quantidade de tiros iniciais do player
    public static int qtdTiro = 50;

    // ------------------------------------------- //
    // --------Atributos de Pontuação ------------ //
    // ------------------------------------------- //

    public static int pontos = 0;


    // ------------------ //
    //      inimigo       //
    // ------------------ //

    public Inimigo enemy;

    // estado em que o inimigo não recebe dano (recuperação)
    public boolean noDamageStateEnemy = false;

    // tempo de invulnerabilidade do inimigo
    public int timerNoDamageEnemy = 0;

    // ------------------------------------- //
    //     Itens de interação do player      //
    // ------------------------------------- //
    public KitHealth vida;
    public TrashBag trashBag;
    public VidaExtra vidaExtra;
    public AmmunitionExtra ammoBox;


    // --------------------------------- //
    //      indice de dano do player     //
    // --------------------------------- //

    // indice de dano (será alterado face a cada ameaça)
    // o índice de dano representa o fator de diminuição de vida do player ao receber dano
    // por enquanto está fixado em cada entidade que causa dano, a posteriori será passado como parâmetro

    public double damageFactor = 0.40;
    public double damageFactorEspinho = 0.20;

    // --------------------------------- //
    //         Outros (Arrumar)          //
    // --------------------------------- //

    // o quanto de vida um kit de recuperação recupera (no futuro poderão existir tipos de kits diferentes)
    public int indiceRecuperacaoKitVida = 10;

    // timer de para Idle
    public int tempoParado;

    public Entity escadaEmColisao;
    public boolean colisaoTopoBloco, colisaoFundoBloco, colisaoEsquerdaBloco, colisaoDireitaBloco;

    // ######################## //
    // ###### Particulas ###### //
    // ######################## //
    // particulas geradas para mortes de inimigos
    public List<Particula> particulas = new ArrayList<>();

    // construtor
    public Player(int x, int y, int width, int height, BufferedImage sprite, String tipo) {
        super(x, y, width, height, sprite, tipo);
        // inicia com o cano
        selectedWeapon = "Cano";
        // quantidade de posições da animação do player (quando andando para a direita)
        playerRight = new BufferedImage[5];
        // quantidade de posições da animação do player (quando andando para a esquerda)
        playerLeft = new BufferedImage[5];
        // array para idle
        playerIdleLeft = new BufferedImage[4];
        playerIdleRigth = new BufferedImage[4];
        // jump
        playerJumpLeft = new BufferedImage[5];
        playerJumpRight = new BufferedImage[5];
        // escada
        playerEscada = new BufferedImage[4];
        //ataques
        // cano
        playerAttackEsquerda = new BufferedImage[4];
        playerAttackDireita = new BufferedImage[4];
        // pistola
        playerAttackEsquerdaArma = new BufferedImage[4];
        playerAttackDireitaArma = new BufferedImage[4];

        // loop para montar o array para a direita e esquerda
        for (int i = 0; i < 5; i++) {
            playerRight[i] = Game.spritePlayer.getSprite((i * getLarguraPlayer()), 96, getLarguraPlayer(), getAlturaPlayer());
            playerLeft[i] = Game.spritePlayer.getSprite((getLarguraPlayer() * 4) - (i * getLarguraPlayer()), 144, getLarguraPlayer(), getAlturaPlayer());
        }

        // loop para montar o array para a idle direita e esquerda, pulo, escada, ataque
        for (int i = 0; i < 4; i++) {
            playerIdleRigth[i] = Game.spritePlayer.getSprite((i * getLarguraPlayer()), 0, getLarguraPlayer(), getAlturaPlayer());
            playerIdleLeft[i] = Game.spritePlayer.getSprite((getLarguraPlayer() * 3) - (i * getLarguraPlayer()), 48, getLarguraPlayer(), getAlturaPlayer());
            playerJumpRight[i] = Game.spritePlayer.getSprite((i * getLarguraPlayer()), 192, getLarguraPlayer(), getAlturaPlayer());
            playerJumpLeft[i] = Game.spritePlayer.getSprite((getLarguraPlayer() * 4) - (i * getLarguraPlayer()), 240, getLarguraPlayer(), getAlturaPlayer());
            playerEscada[i] = Game.spritePlayer.getSprite(192 + (i * getLarguraPlayer()), 192, getLarguraPlayer(), getAlturaPlayer());
            playerAttackEsquerda[i] = Game.spritePlayer.getSprite(192, 48, getLarguraPlayer() * 2, getAlturaPlayer());
            playerAttackEsquerdaArma[i] = Game.spritePlayer.getSprite(192, 144, getLarguraPlayer() * 2, getAlturaPlayer());
            playerAttackDireita[i] = Game.spritePlayer.getSprite(320, 0, getLarguraPlayer() * 2, getAlturaPlayer());
            playerAttackDireitaArma[i] = Game.spritePlayer.getSprite(192, 96, getLarguraPlayer() * 2, getAlturaPlayer());
        }

    }

    public void tick() {

        timerDamageControllPlayer++;
        timerDamageControllResetPlayer++;

        // aqui eu inicio a movimentação em parado ==== 0
        atualX = (int) x;
        atualY = (int) y;
        emEscada = colisaoEscada(this.getX(), this.getY());
        emColisao = colisao(atualX, atualY + 1);

        if (!right && !left) {
            emMovimento = false;
        }

        if (!emMovimento) {
            tempoParado++;
        } else {
            tempoParado = 0;
        }

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

        // Gravidade
        // Situação de ação da GRAVIDADE no player
        if (!emColisao && !isJump && !emEscada) {
            // não existe a colisão (ou seja, não estou em contato com uma entidade solida), não estou em pulo e nem em escada eu caio
            emMovimento = true;
            jump = false;
            attack = false;
            isFalling(true);

        }

        // se eu estou em uma escada e não existe colisão
        if (emEscada && !emColisao) {

            if (down) {
                y += speed;
                y = (int) y;
            }
            if (up) {
                y -= speed;
                y = (int) y;
            }

        }
        if (emEscada && emColisao) {
            // se eu tenho uma colisão, tenho que ver a posição que eu me encontro

            // no caso inicial eu não estou no topo da escada e não estou no bottom (ou seja, estou no meio)
            if (!posTopoEscada && !posBottomEscada) {

                // estou colidindo, mas não estou no topo de uma escada
                // ou seja nesse caso em especifico eu posso
                // - tentar descer e bater em algo
                // - tentar subir e bater em algo

                if (up) {
                    y = atualY + getAlturaPlayer() - Entity.SIZEENTITYY - 13;
                    up = false;
                }
                if (down) {
                    y = atualY - 1;
                    down = false;
                }

            } else if (posBottomEscada) {
                // nesse caso estou na base de uma escada, colidindo com mo chão provavelmente
                // eu posso:
                // - voltar a subir
                // - tentar descer e bater no chão
                if (up) {
                    y -= speed;
                    y = (int) y;
                }

                if (down) {
                    y = atualY - 1;
                    down = false;
                }

            } else {
                // nesse caso eu estou no topo de uma escada
                if (up) {
                    y -= speed;
                    y = (int) y;
                }
                if (down) {
                    x = escadaEmColisao.getX();
                    up = false;
                }
            }


        }

        // caso eu me movimente para a direita
        // - não estou colidindo com nada
        // - não estou atacando
        // - estou dentro dos limites do mundo
        if (right && !attack && !colisao((int) (x + speed), this.getY()) && x <= (Mundo.WIDTH * Entity.SIZEENTITYX) - getLarguraPlayer()) {
            x += speed;
            emMovimento = true;
            direcaoAtual = "direita";

        }

        // caso eu me movimente para a esquerda
        // - não estou colidindo com nada
        // - não estou atacando
        // - estou dentro dos limites do mundo
        if (left && !attack && !colisao((int) (x - speed), this.getY()) && x >= 0) {
            x -= speed;
            emMovimento = true;
            direcaoAtual = "esquerda";
        }

        // momento do pulo (habilita a condição de estar pulando)
        if (jump) {
            if (emColisao) {
                isJump = true;
                attack = false;
                emMovimento = true;
            }
        }

        // durante a execução do pulo (comportamento)
        if (isJump) {
            attack = false;
            if (jumpTimeSound == 0) {
                PlayerAudio.tocaAudio("jump");
            }
            jumpTimeSound++;

            // se não houver colisão
            if (!colisao(this.getX(), this.getY() - 1)) {

                // somo o y até que
                y -= jumpSpeed;
                jumpFrames += jumpSpeed;

                //a altura do frameJump seja igual ao tamanho máximo de pulo
                if (jumpFrames == jumpHeigth) {
                    isFalling(true);
                    jumpFrames = 0;

                }
            } else {
                jumpTimeSound = 0;
                jumpFrames = 0;
                isFalling(true);
            }

        } else {
            jumpTimeSound = 0;
        }

        // ação de ataque em relação ao inimigo (com o cano)
        if (attack) {

            // após o ataque, tenho que fazer o ataque ser falso

            if (Objects.equals(selectedWeapon, "Cano")) {
                // aqui existe a colisão de ataque pelo cano (passo a minha posição)
                if (ataqueCano(this.getX(), this.getY())) {

                    causaDanoInimigo(timerNoDamageEnemy, enemy);

                }
            } else {
                if (attackTimeSound == 0) {
                    // o tick é muito rápido
                    if (qtdTiro > 0) {
                        PlayerAudio.tocaAudio("tiro");
                        // aqui começa o problema de ajuste de tiro
                        if (Objects.equals(direcaoAtual, "direita")) {
                            Game.tirosPLayer.add(new TiroPlayer(Player.atualX - Camera.x, Player.atualY - 32 - Camera.y, 30, 2, null, "tiro", "Direita"));
                        } else {
                            Game.tirosPLayer.add(new TiroPlayer(Player.atualX - Camera.x, Player.atualY - 32 - Camera.y, 30, 2, null, "tiro", "Esquerda"));
                        }
                    }

                    if (qtdTiro > 0) {
                        qtdTiro--;
                    } else {
                        selectedWeapon = "Cano";
                    }
                }

            }

            emMovimento = true;
            frames++;

            if (frames == maxFrames) {
                indexAtack++;
                frames = 0;
                if (indexAtack >= maxIndex) {
                    indexAtack = 0;
                    attack = false;
                    attackTimeSound = 0;
                }
            }
        }

        // movimentação do player (CONTROLE DE FRAMES)
        if (emMovimento && !attack || emEscada) {
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
                if (tempoParado / 60 > 4) {
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

        if (colisaoDireitaBloco || colisaoEsquerdaBloco){
            emMovimento = false;
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

        // pegar Lixo no chão
        if (pegaLixo(this.getX(), this.getY())) {
            pontos++;
            Game.trashBags.remove(trashBag);
        }

        // vidas extras para o player
        if (pegaVidaExtra(this.getX(), this.getY())) {
            tentativas++;
            Game.vidasExtras.remove(vidaExtra);
        }

        // Munição extra para o player
        if (pegaMunicaoExtra(this.getX(), this.getY())) {
            qtdTiro += ammoBox.qtdTiroBox;
            Game.ammunitionExtras.remove(ammoBox);
        }

        // guarda a posição da placa de checkpoint
        if (checkPoint(this.getX(), this.getY())) {
            posSaveX = this.getX();
            posSaveY = this.getY();
        }

        // dano de espinhos
        if (danoEspinho(this.getX(), this.getY())) {
            life -= damageFactorEspinho;
        }

        // caindo fora da tela (dano)
        if (y >= (Mundo.HEIGHT * Entity.SIZEENTITYY)) {
            life -= damageFactor * 4;
        }

        // quaando a energia acaba
        if (life <= 0) {
            tentativas--;
            setX(posSaveX);
            setY(posSaveY);
            life = 100;
            if (tentativas == 0) {
                GameOver.gameoverTimer = 0;
                Game.gameState = "GAMEOVER";
            }
        }

        // posicionamento da camera, sempre em relaçao ao player
        Camera.x = Camera.Clamp(this.getX() - (GameSettings.getGAME_WIDTH() / 2), 0, (Mundo.WIDTH * Entity.SIZEENTITYX) - getLarguraPlayer());
        Camera.y = Camera.Clamp(this.getY() - (GameSettings.getGAME_HEIGHT() / 2), 0, (Mundo.HEIGHT * Entity.SIZEENTITYY) - getAlturaPlayer());

    }

    // colisor base do player

    // -------------------------------- //
    //              Basico              //
    // -------------------------------- //
    //colisão (gravidade)
    // ------------------------- //
    // ---------REVISÃO--------- //
    // ------------------------- //
    public boolean colisao(int nextx, int nexty) {
        Rectangle playerRectangle = new Rectangle(nextx, nexty, getLarguraPlayer(), getAlturaPlayer());
        for (int i = 0; i < Game.entidades.size(); i++) {
            Entity entidade = Game.entidades.get(i);
            if (entidade instanceof Solido) {
                Rectangle solido = new Rectangle(entidade.getX(), entidade.getY(), Entity.SIZEENTITYX, Entity.SIZEENTITYY);
                if (playerRectangle.intersects(solido)) {

                    // Determinar direção da colisão
                    colisaoTopoBloco = playerRectangle.getMaxY()-1 <= solido.getMinY();
                    colisaoFundoBloco = playerRectangle.getMinY()+1 >= solido.getMaxY();
                    colisaoEsquerdaBloco = playerRectangle.getMaxX()-3 <= solido.getMinX();
                    colisaoDireitaBloco = playerRectangle.getMinX()+3 >= solido.getMaxX();

                    // Retorne true para indicar que houve colisão
                    return true;
                }
            }
        }
        // Retorne false se não houver colisão
        return false;
    }

    // colisão com escadas
    // ------------------------- //
    // ---------REVISÃO--------- //
    // ------------------------- //
    public boolean colisaoEscada(int nextx, int nexty) {
        Rectangle retanguloPlayer = new Rectangle(nextx, nexty, getLarguraPlayer() - (Entity.SIZEENTITYX / 2), getAlturaPlayer() - (getAlturaPlayer() - Entity.SIZEENTITYY));

        for (int i = 0; i < Game.escada.size(); i++) {
            Escada escada = Game.escada.get(i);

            Rectangle retanguloEscada = new Rectangle(escada.getX()-10,
                    escada.getY() - Entity.SIZEENTITYY + (getLarguraPlayer() - (Entity.SIZEENTITYX / 2)) - 2, Entity.SIZEENTITYX, Entity.SIZEENTITYY);

            if (retanguloPlayer.intersects(retanguloEscada)) {
                escadaEmColisao = escada;

                jump = false;
                isJump = false;
                attack = false;
                emMovimento = true;
                if (escada.tipoEscada == 3) {
                    posTopoEscada = true;
                    posBottomEscada = false;
                    yTopoEscada = escada.getY();
                } else if (escada.tipoEscada == 1) {
                    posTopoEscada = false;
                    posBottomEscada = true;
                } else {
                    posTopoEscada = false;
                    posBottomEscada = false;
                }
                return true;
            }

        }

        return false;
    }


    // troca de armas
    public synchronized void toggleWeapon() {

        if (Objects.equals(selectedWeapon, "Cano")) {
            if (qtdTiro > 0) {
                selectedWeapon = "Arma";
            }
        } else {
            selectedWeapon = "Cano";
        }
    }

    public void isFalling(boolean falling) {
        if (falling) {
            attack = false;
            jump = false;
            isJump = false;

            int newY = atualY + TAXADEQUEDA;

            // tenho que me certificar que a TAXADEQUEDA não vai me enfiar no chão

            if (!colisao(atualX, newY + 1)) {
                y += TAXADEQUEDA;
            } else {

                y += 1;
            }
        }
    }

    // -------------------------------- //
    //            Dano (tomado)         //
    // -------------------------------- //

    // dano por contato com espinhos
    public boolean danoEspinho(int nextx, int nexty) {
        Rectangle player = new Rectangle(nextx, nexty, getLarguraPlayer(), getAlturaPlayer());

        for (int i = 0; i < Game.espinhos.size(); i++) {
            Entity espinho = Game.espinhos.get(i);
            if (espinho != null) {
                Rectangle espinhoRetangulo = new Rectangle(espinho.getX(), espinho.getY(), Entity.SIZEENTITYX, Entity.SIZEENTITYY);
                if (player.intersects(espinhoRetangulo)) {
                    timerDamageControllPlayer = 0;
                    if (timerDamageControllResetPlayer % 30 == 0) {
                        PlayerAudio.tocaAudio("dano");
                    }
                    return true;

                }
            }
        }
        return false;
    }

    // dano por contato com inimigos
    public boolean damage(int nextx, int nexty) {
        Rectangle player = new Rectangle(nextx, nexty, getLarguraPlayer(), getAlturaPlayer());
        for (int i = 0; i < Game.inimigo.size(); i++) {
            Inimigo entidade = Game.inimigo.get(i);
            if (entidade != null) {
                Rectangle solido = new Rectangle(entidade.getX(), entidade.getY(), Entity.SIZEENTITYX, Entity.SIZEENTITYY);
                if (player.intersects(solido)) {
                    timerDamageControllPlayer = 0;
                    enemy = entidade;
                    if (timerDamageControllResetPlayer % 30 == 0) {
                        PlayerAudio.tocaAudio("dano");
                    }
                    return true;
                }
            }
        }
        return false;
    }

    // -------------------------------- //
    //            Dano (causado)        //
    // -------------------------------- //

    // Dano que o player causa no inimigo
    public void causaDanoInimigo(int timerNoDamageEnemy, Entity inimigo) {
        this.enemy = (Inimigo) inimigo;
        if (timerNoDamageEnemy == 0) {
            // removo a vida do inimigo
            enemy.life--;
            noDamageStateEnemy = true;
            if (enemy.life <= 0) {
                // adiciona particulas da explosao
                for (int i = 0; i < 100; i++) {
                    particulas.add(new Particula(enemy.getX() - Camera.x, enemy.getY() - Camera.y, 3, 3, Color.BLUE));
                }

                Game.inimigo.remove(enemy);
            }

        }

        // movimenta o inimigo na direção oposta a que eu me encontro
        // direita
        if (Objects.equals(direcaoAtual, "direita")) {
            // executo o loop por 19 ticks (índice de afastamento)
            for (int j = 0; j < 19; j++) {
                if (!enemy.colisao(enemy.getX(), enemy.getY(), enemy.getTipoInimigo())) {
                    // se não houver objeto para colidir, movimento o inimigo para a direita
                    enemy.setX(enemy.getX() + 1);

                } else {
                    // nesse caso existe objeto para colidir
                    enemy.setX(enemy.getX() - 3);
                }

            }
        } else {
            for (int j = 0; j < 19; j++) {
                if (!enemy.colisao(enemy.getX(), enemy.getY(), enemy.getTipoInimigo())) {
                    // se não houver objeto para colidir, movimento o inimigo para a direita
                    enemy.setX(enemy.getX() - 1);
                } else {
                    // nesse caso existe objeto para colidir
                    enemy.setX(enemy.getX() + 3);
                }

            }

        }

    }

    // -------------------------------- //
    //            Ataques               //
    // -------------------------------- //

    // ataque com o cano, tenho que ajustar para impedir que o inimigo fique preso em alguma parte de tela
    public boolean ataqueCano(int nextx, int nexty) {

        int incremento = 0;
        if (Objects.equals(direcaoAtual, "esquerda")) {
            incremento -= Player.getLarguraPlayer();
        }

        Rectangle retanguloPlayer = new Rectangle(nextx + incremento, nexty, getLarguraPlayer() + Player.getLarguraPlayer(), getAlturaPlayer());

        for (int i = 0; i < Game.inimigo.size(); i++) {
            Inimigo inimigo = Game.inimigo.get(i);
            if (inimigo != null) {
                Rectangle retanguloInimigo = new Rectangle(inimigo.getX(), inimigo.getY(), Entity.SIZEENTITYX, Entity.SIZEENTITYY);
                if (retanguloPlayer.intersects(retanguloInimigo)) {
                    enemy = inimigo;
                    return true;
                }
            }
        }
        return false;
    }

    // -------------------------------- //
    //          Interativos             //
    // -------------------------------- //

    // checkpoint (player toca o checkpoint)
    public boolean checkPoint(int nextx, int nexty) {
        Rectangle player = new Rectangle(nextx, nexty, getLarguraPlayer(), getAlturaPlayer());
        for (int i = 0; i < Game.checkPoints.size(); i++) {
            Entity checkPoint = Game.checkPoints.get(i);
            if (checkPoint != null) {
                Rectangle placa = new Rectangle(checkPoint.getX(), checkPoint.getY(), Entity.SIZEENTITYX, Entity.SIZEENTITYY);
                if (player.intersects(placa)) {
                    return true;
                }
            }
        }
        return false;
    }

    // player colide com o kit de vida
    public boolean vida(int nextx, int nexty) {
        Rectangle player = new Rectangle(nextx, nexty, getLarguraPlayer(), getAlturaPlayer());
        for (int i = 0; i < Game.kitHealth.size(); i++) {
            KitHealth kitHealth = Game.kitHealth.get(i);
            if (kitHealth != null) {
                Rectangle kitVida = new Rectangle(kitHealth.getX(), kitHealth.getY(), Entity.SIZEENTITYX, Entity.SIZEENTITYY);
                if (player.intersects(kitVida)) {
                    vida = kitHealth;
                    PlayerAudio.tocaAudio("extraEnergy");
                    return true;
                }
            }
        }
        return false;
    }

    //coleta de lixo
    public boolean pegaLixo(int nextx, int nexty) {
        Rectangle player = new Rectangle(nextx, nexty, getLarguraPlayer(), getAlturaPlayer());
        for (int i = 0; i < Game.trashBags.size(); i++) {
            TrashBag trashBagAtivo = Game.trashBags.get(i);
            if (trashBagAtivo != null) {
                Rectangle trashRetangle = new Rectangle(trashBagAtivo.getX(), trashBagAtivo.getY(), Entity.SIZEENTITYX, Entity.SIZEENTITYY);
                if (player.intersects(trashRetangle)) {
                    trashBag = trashBagAtivo;
                    PlayerAudio.tocaAudio("pegaLixo");
                    return true;
                }
            }
        }
        return false;
    }

    // vida extra
    public boolean pegaVidaExtra(int nextx, int nexty) {
        Rectangle player = new Rectangle(nextx, nexty, getLarguraPlayer(), getAlturaPlayer());
        for (int i = 0; i < Game.vidasExtras.size(); i++) {
            VidaExtra vidaExtraAtivo = Game.vidasExtras.get(i);
            if (vidaExtraAtivo != null) {
                Rectangle trashRetangle = new Rectangle(vidaExtraAtivo.getX(), vidaExtraAtivo.getY(), Entity.SIZEENTITYX, Entity.SIZEENTITYY);
                if (player.intersects(trashRetangle)) {
                    vidaExtra = vidaExtraAtivo;
                    PlayerAudio.tocaAudio("vidaExtra");
                    return true;
                }
            }
        }
        return false;
    }

    // munição extra
    public boolean pegaMunicaoExtra(int nextx, int nexty) {
        Rectangle player = new Rectangle(nextx, nexty, getLarguraPlayer(), getAlturaPlayer());
        for (int i = 0; i < Game.ammunitionExtras.size(); i++) {
            AmmunitionExtra ammoBoxAtivo = Game.ammunitionExtras.get(i);
            if (ammoBoxAtivo != null) {
                Rectangle trashRetangle = new Rectangle(ammoBoxAtivo.getX(), ammoBoxAtivo.getY(), Entity.SIZEENTITYX, Entity.SIZEENTITYY);
                if (player.intersects(trashRetangle)) {
                    ammoBox = ammoBoxAtivo;
                    PlayerAudio.tocaAudio("reload");
                    return true;
                }
            }
        }
        return false;
    }


    // renderiza tudão
    public void render(Graphics g) {

        // Auxiliar (remover depois)
        // g.fillRect((int)Player.atualX-Camera.x, (int)Player.atualY + 16-Camera.y,  32,32);

        // situação quando virado para a direita e esquerda
        // você pode estar parado
        // você pode estar andando
        // você pode estar pulando
        // vc pode estar em queda

        if (Objects.equals(direcaoAtual, "direita")&& !emEscada && !attack) {
            // estou em pulo
            if (jump) {
                g.drawImage(playerJumpRight[0], this.getX() - Camera.x, this.getY() - Camera.y, null);
            }
            // estou caindo
            if (!emColisao && !jump) {
                g.drawImage(playerJumpRight[3], this.getX() - Camera.x, this.getY() - Camera.y, null);
            }
            // aqui eu estou no chão provavelmente
            if (emColisao) {
                // aqui eu estou andando
                if (emMovimento) {
                    g.drawImage(playerRight[index], this.getX() - Camera.x, this.getY() - Camera.y, null);
                }
                // aqui estou parado
                if (!emMovimento) {
                    if (tempoParado / 60 <= 4) {
                        g.drawImage(playerIdleRigth[0], this.getX() - Camera.x, this.getY() - Camera.y, null);
                    } else if (tempoParado / 60 > 4) {
                        g.drawImage(playerIdleRigth[index], this.getX() - Camera.x, this.getY() - Camera.y, null);
                    } else {
                        g.drawImage(playerRight[0], this.getX() - Camera.x, this.getY() - Camera.y, null);
                    }
                }
            }
        } else if (Objects.equals(direcaoAtual, "esquerda")&& !emEscada && !attack){

            // estou em pulo
            if (jump) {
                g.drawImage(playerJumpLeft[0], this.getX() - Camera.x, this.getY() - Camera.y, null);
            }
            // estou caindo
            if (!emColisao && !jump) {
                g.drawImage(playerJumpLeft[3], this.getX() - Camera.x, this.getY() - Camera.y, null);
            }
            // aqui eu estou no chão provavelmente
            if (emColisao) {
                // aqui eu estou andando
                if (emMovimento) {
                    g.drawImage(playerLeft[index], this.getX() - Camera.x, this.getY() - Camera.y, null);
                }
                // aqui estou parado
                if (!emMovimento) {
                    if (tempoParado / 60 <= 4) {
                        g.drawImage(playerIdleLeft[0], this.getX() - Camera.x, this.getY() - Camera.y, null);
                    } else if (tempoParado / 60 > 4) {
                        g.drawImage(playerIdleLeft[index], this.getX() - Camera.x, this.getY() - Camera.y, null);
                    } else {
                        g.drawImage(playerLeft[0], this.getX() - Camera.x, this.getY() - Camera.y, null);
                    }
                }
            }
        }

        // rederização para movimento em escada
        if (emEscada) {
            if (up || down) {
                g.drawImage(playerEscada[index], this.getX() - Camera.x, this.getY() - Camera.y, null);
            } else {
                g.drawImage(playerEscada[0], this.getX() - Camera.x, this.getY() - Camera.y, null);
            }
        }

        // renderiza as particulas se houverem
        for (Particula particula : particulas) {
            particula.render(g);
        }

        // ataque
        if (attack) {

            if (attackTimeSound == 0) {
                if (Objects.equals(selectedWeapon, "Cano")) {
                    PlayerAudio.tocaAudio("ataqueCano");
                }

            }
            attackTimeSound++;

            if (Objects.equals(direcaoAtual, "esquerda")) {
                if (Objects.equals(selectedWeapon, "Cano")) {
                    g.drawImage(playerAttackEsquerda[indexAtack], this.getX() - Camera.x - Player.getLarguraPlayer(), this.getY() - Camera.y, null);
                } else {
                    g.drawImage(playerAttackEsquerdaArma[indexAtack], this.getX() - Camera.x - Player.getLarguraPlayer(), this.getY() - Camera.y, null);
                }

            } else {
                if (Objects.equals(selectedWeapon, "Cano")) {
                    g.drawImage(playerAttackDireita[indexAtack], this.getX() - Camera.x, this.getY() - Camera.y, null);
                } else {
                    g.drawImage(playerAttackDireitaArma[indexAtack], this.getX() - Camera.x, this.getY() - Camera.y, null);
                }
            }

        }

    }

    public static int getLarguraPlayer() {
        return SIZEPLAYERX;
    }

    public static int getAlturaPlayer() {
        return SIZEPLAYERY;
    }
}
