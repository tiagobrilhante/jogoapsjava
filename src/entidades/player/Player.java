package entidades.player;

import Mundo.Camera;
import Mundo.Mundo;
import entidades.Entity;
import entidades.interativos.*;
import entidades.naoSolidos.Particula;
import entidades.solidos.Solido;
import main.Game;
import main.GameOver;

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

    // tamanho base dos plocos de player
    public static int SIZEPLAYERX = 32, SIZEPLAYERY = 48;
    // máscara do player
    public int maskx = 0, masky = 0, maskw = SIZEPLAYERX, maskh = SIZEPLAYERY;


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
    public static double atualX, atualY;
    // localização com relação ao save
    public static int posx, posy;
    public static int direita = 1;
    public static int esquerda = 0;
    // o player começa a fase virado para a direita
    public static int direcaoAtual = direita;

    // velocidade de deslocamento do player
    public double speed = 2.9;

    // o player começa parado
    public boolean movimentacao = false;

    // ####### EScada ######### //
    public boolean emEscada = false;

    // apoio para saber se está ou n ão no topo da escada
    public boolean posTopoEscada;
    public int yTopoEscada;

    // frames e indexes de movimentação do sprite do player
    public int frames = 0, maxFrames = 5, index = 0, maxIndex = 3, indexAtack = 0;

    // buffer de geração de imagem do player em movimento
    public BufferedImage[] playerRight, playerLeft, playerIdleLeft, playerIdleRigth, playerJumpLeft, playerJumpRight, playerEscada, playerAttackEsquerda, playerAttackEsquerdaArma, playerAttackDireita, playerAttackDireitaArma;

    // ######## JUMP ####### //

    // se o player está pulando (jump é o momento que aperto o pulo, isJump é enquanto o pulo está acontecendo)
    public boolean jump = false, isJump = false;
    // atributos de jump
    public int jumpTimeSound = 0, jumpHeigth = 64, jumpSpeed = 4, jumpFrames = 0;

    // ######## Attack ####### //
    public boolean attack = false;

    public static String selectedWeapon;
    public int attackTimeSound = 0;

    public int timerDamageControllPlayer = 0;
    public int timerDamageControllResetPlayer = 0;

    // quantidade de tiros iniciais do player
    public static int qtdTiro = 5;

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

    // timers de suporte para a lógica do player (TEM QUE REVISAR)
    public int timerPlayer = 0, tempoParado;
    // timer de suporte a mecânicas do inimigo (TEM QUE REVISAR)
    public int timerEnemy = 0;

    // controlador de efeitos (TEM QUE REVISAR)
    public int timeEfectsParam = 0;


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

        // loop para montar o array para a direita
        for (int i = 0; i < 5; i++) {
            playerRight[i] = Game.spritePlayer.getSprite((i * SIZEPLAYERX), 96, SIZEPLAYERX, SIZEPLAYERY);
        }

        // loop para montar o array para a esquerda
        for (int i = 0; i < 5; i++) {
            playerLeft[i] = Game.spritePlayer.getSprite((SIZEPLAYERX * 4) - (i * SIZEPLAYERX), 144, SIZEPLAYERX, SIZEPLAYERY);
        }

        // loop para montar o array para a idle direita
        for (int i = 0; i < 4; i++) {
            playerIdleRigth[i] = Game.spritePlayer.getSprite((i * SIZEPLAYERX), 0, SIZEPLAYERX, SIZEPLAYERY);
        }

        // loop para montar o array para a idle esquerda
        for (int i = 0; i < 4; i++) {
            playerIdleLeft[i] = Game.spritePlayer.getSprite((SIZEPLAYERX * 3) - (i * SIZEPLAYERX), 48, SIZEPLAYERX, SIZEPLAYERY);
        }

        // loop para montar o array para a jump direita
        for (int i = 0; i < 4; i++) {
            playerJumpRight[i] = Game.spritePlayer.getSprite((i * SIZEPLAYERX), 192, SIZEPLAYERX, SIZEPLAYERY);
        }

        // loop para montar o array para a jump esquerda
        for (int i = 0; i < 4; i++) {
            playerJumpLeft[i] = Game.spritePlayer.getSprite((SIZEPLAYERX * 4) - (i * SIZEPLAYERX), 240, SIZEPLAYERX, SIZEPLAYERY);
        }

        // loop para montar o array para escada
        for (int i = 0; i < 4; i++) {
            playerEscada[i] = Game.spritePlayer.getSprite(192 + (i * SIZEPLAYERX), 192, SIZEPLAYERX, SIZEPLAYERY);
        }

        // loop para montar o array para ataque
        for (int i = 0; i < 4; i++) {
            playerAttackEsquerda[i] = Game.spritePlayer.getSprite(192, 48, SIZEPLAYERX * 2, SIZEPLAYERY);
        }

        for (int i = 0; i < 4; i++) {
            playerAttackEsquerdaArma[i] = Game.spritePlayer.getSprite(192, 144, SIZEPLAYERX * 2, SIZEPLAYERY);
        }

        // loop para montar o array para ataque
        for (int i = 0; i < 4; i++) {
            playerAttackDireita[i] = Game.spritePlayer.getSprite(320, 0, SIZEPLAYERX * 2, SIZEPLAYERY);
        }

        // loop para montar o array para ataque
        for (int i = 0; i < 4; i++) {
            playerAttackDireitaArma[i] = Game.spritePlayer.getSprite(192, 96, SIZEPLAYERX * 2, SIZEPLAYERY);
        }

    }

    public void tick() {

        timeEfectsParam++;
        timerDamageControllPlayer++;
        timerDamageControllResetPlayer++;
        timerPlayer++;

        // aqui eu inicio a movimentação em parado ==== 0
        atualX = (int) x;
        atualY = (int) y;

        timerEnemy++;

        if (!movimentacao){
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

        // testa se estou em uma escada
        emEscada = colisaoEscada(this.getX(), this.getY());

        if (emEscada) {
            attack = false;
            jump = false;
            isJump = false;
        }

        // Gravidade
        // Situação de ação da GRAVIDADE e MOVIMENTO de PLAYER em ESCADAS (pois desafia a gravidade)
        if (!colisao((int) x, (int) (y + 2)) && !isJump && !emEscada) {
            // não existe a colisão não estou em pulo e nem em escada
            // situação normal do player
            // se não existe colisão, eu sou afetado pela gravidade
            // quando estou em uma escada, a gravidade não me afeta (posso subir e descer)
            // quando estou em pulo, eu desafio a gravidade pelo tempo do pulo
            y += 4; // índice de velocidade de queda
        } else {
            // regras para movimento em escadas (quando eu nao colido com nada)
            // vale ressaltar que a melhor estratégia é centralizar o player na escada
            // aqui obrigatoriamente:
            // - estou em colisão ou não
            // - estou pulando ou não
            // - estou em escada ou não
            if (emEscada && !colisao((int) x, (int) (y))) {
                jump = false;
                isJump = false;
                if (down) {
                    y += speed;
                    y = (int) y;
                }
                if (up) {
                    y -= speed;
                    y = (int) y;
                }
                movimentacao = true;
            } else if (emEscada && colisao((int) x, (int) (y))) {
                jump = false;
                isJump = false;
                // no caso estou em uma escada e colidindo com algo
                Rectangle playerRect = new Rectangle((int) x + maskx, (int) y + masky, maskw, maskh);

                for (int i = 0; i < Game.entidades.size(); i++) {
                    Entity entidade = Game.entidades.get(i);

                    if (entidade instanceof Solido) {
                        Rectangle solido = new Rectangle(entidade.getX() + maskx, entidade.getY() + masky, Entity.SIZEENTITYX, Entity.SIZEENTITYY);

                        if (playerRect.intersects(solido)) {
                            if (up && !posTopoEscada) {
                                y = entidade.getY() + (SIZEENTITYY);
                                up = false;
                            } else {
                                down = false;
                                this.y = entidade.getY() - (SIZEPLAYERY);
                            }
                        }
                    }
                }

            } else {

                if (down) {
                    y += 0;
                    y = (int) y;
                }
                if (up) {
                    y -= 0;
                    y = (int) y;
                }
                movimentacao = false;

            }

        }

        // caso eu me movimente para a direita
        // - não estou colidindo com nada
        // - não estou atacando
        // - estou dentro dos limites do mundo
        if (right && !attack && !colisao((int) (x + speed), this.getY()) && x <= (Mundo.WIDTH * Entity.SIZEENTITYX) - SIZEPLAYERX) {
            x += speed;
            movimentacao = true;
            direcaoAtual = direita;

        }

        // caso eu me movimente para a esquerda
        // - não estou colidindo com nada
        // - não estou atacando
        // - estou dentro dos limites do mundo
        if (left && !attack && !colisao((int) (x - speed), this.getY()) && x >= 0) {
            x -= speed;
            movimentacao = true;
            direcaoAtual = esquerda;

        }

        // momento do pulo (habilita a condição de estar pulando)
        if (jump) {
            if (colisao(this.getX(), this.getY() + 1)) {
                isJump = true;
            }
        }

        // durante a execução do pulo (comportamento)
        if (isJump) {

            if (jumpTimeSound == 0) {
                PlayerAudio.tocaAudio("jump");
            }
            jumpTimeSound++;

            // se não houver colisão
            if (!colisao(this.getX(), this.getY() - 2)) {
                // somo o y até que
                y -= jumpSpeed;
                jumpFrames += jumpSpeed;

                //a altura do frameJump seja igual ao tamanho máximo de pulo
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
                        if (direcaoAtual == 1) {
                            Game.tirosPLayer.add(new TiroPlayer((int)Player.atualX-Camera.x, (int)Player.atualY + 16-Camera.y , 30, 2, null, "tiro", "Direita"));
                        } else {
                            Game.tirosPLayer.add(new TiroPlayer((int)Player.atualX- Camera.x , (int)Player.atualY + 16- Camera.y,  30, 2, null, "tiro", "Esquerda"));
                        }
                    }

                    if (qtdTiro > 0) {
                        qtdTiro--;
                    } else {
                        selectedWeapon = "Cano";
                    }
                }

            }

            movimentacao = true;
            frames++;

            if (frames == maxFrames) {
                indexAtack++;
                frames = 0;
                if (indexAtack > maxIndex) {
                    indexAtack = 0;
                    attack = false;
                }
            }


        }

        // movimentação do player
        if (movimentacao && !attack) {
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
                if ((int)tempoParado/60 > 4) {
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
            posx = this.getX();
            posy = this.getY();
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
            setX(posx);
            setY(posy);
            life = 100;
            if (tentativas == 0) {
                GameOver.gameoverTimer = 0;
                Game.gameState = "GAMEOVER";
            }
        }

        // posicionamento da camera, sempre em relaçao ao player
        Camera.x = Camera.Clamp(this.getX() - (Game.WIDTH / 2), 0, (Mundo.WIDTH * Entity.SIZEENTITYX) - SIZEPLAYERX);
        Camera.y = Camera.Clamp(this.getY() - (Game.HEIGTH / 2), 0, (Mundo.HEIGHT * Entity.SIZEENTITYY) - SIZEPLAYERY);

    }

    // colisor base do player

    // -------------------------------- //
    //              Basico              //
    // -------------------------------- //
    //colisão (gravidade)
    public boolean colisao(int nextx, int nexty) {


        Rectangle playerRectangle = new Rectangle(nextx + maskx, nexty + masky, maskw, maskh);

        for (int i = 0; i < Game.entidades.size(); i++) {
            Entity entidade = Game.entidades.get(i);
            if (entidade instanceof Solido) {

                Rectangle solido = new Rectangle(entidade.getX() + maskx, entidade.getY() + masky, Entity.SIZEENTITYX, Entity.SIZEENTITYY);
                if (playerRectangle.intersects(solido)) {
                    Player.atualY = entidade.getY() + masky;
                    return true;
                }
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

    // -------------------------------- //
    //            Dano (tomado)         //
    // -------------------------------- //

    // dano por contato com espinhos
    public boolean danoEspinho(int nextx, int nexty) {
        Rectangle player = new Rectangle(nextx + maskx, nexty + masky, maskw, maskh);

        for (int i = 0; i < Game.espinhos.size(); i++) {
            Entity espinho = Game.espinhos.get(i);
            if (espinho != null) {
                Rectangle espinhoRetangulo = new Rectangle(espinho.getX() + maskx, espinho.getY() + masky, Entity.SIZEENTITYX, Entity.SIZEENTITYY);
                if (player.intersects(espinhoRetangulo)) {
                    timerDamageControllPlayer = 0;
                    if (timerDamageControllPlayer == 0 && timerDamageControllResetPlayer % 30 == 0) {
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
        Rectangle player = new Rectangle(nextx + maskx, nexty + masky, maskw, maskh);
        for (int i = 0; i < Game.inimigo.size(); i++) {
            Inimigo entidade = Game.inimigo.get(i);
            if (entidade != null) {
                Rectangle solido = new Rectangle(entidade.getX() + maskx, entidade.getY() + masky, Entity.SIZEENTITYX, Entity.SIZEENTITYY);
                if (player.intersects(solido)) {
                    timerDamageControllPlayer = 0;
                    enemy = entidade;
                    if (timerDamageControllPlayer == 0 && timerDamageControllResetPlayer % 30 == 0) {
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

    }

    // -------------------------------- //
    //            Ataques               //
    // -------------------------------- //

    // ataque com o cano, tenho que ajustar para impedir que o inimigo fique preso em alguma parte de tela
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

    // -------------------------------- //
    //          Interativos             //
    // -------------------------------- //

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

    // player colide com o kit de vida
    public boolean vida(int nextx, int nexty) {
        Rectangle player = new Rectangle(nextx + maskx, nexty + masky, maskw, maskh);
        for (int i = 0; i < Game.kitHealth.size(); i++) {
            KitHealth kitHealth = Game.kitHealth.get(i);
            if (kitHealth != null) {
                Rectangle kitVida = new Rectangle(kitHealth.getX() + maskx, kitHealth.getY() + masky, Entity.SIZEENTITYX, Entity.SIZEENTITYY);
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
        Rectangle player = new Rectangle(nextx + maskx, nexty + masky, maskw, maskh);
        for (int i = 0; i < Game.trashBags.size(); i++) {
            TrashBag trashBagAtivo = Game.trashBags.get(i);
            if (trashBagAtivo != null) {
                Rectangle trashRetangle = new Rectangle(trashBagAtivo.getX() + maskx, trashBagAtivo.getY() + masky, Entity.SIZEENTITYX, Entity.SIZEENTITYY);
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
        Rectangle player = new Rectangle(nextx + maskx, nexty + masky, maskw, maskh);
        for (int i = 0; i < Game.vidasExtras.size(); i++) {
            VidaExtra vidaExtraAtivo = Game.vidasExtras.get(i);
            if (vidaExtraAtivo != null) {
                Rectangle trashRetangle = new Rectangle(vidaExtraAtivo.getX() + maskx, vidaExtraAtivo.getY() + masky, Entity.SIZEENTITYX, Entity.SIZEENTITYY);
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
        Rectangle player = new Rectangle(nextx + maskx, nexty + masky, maskw, maskh);
        for (int i = 0; i < Game.ammunitionExtras.size(); i++) {
            AmmunitionExtra ammoBoxAtivo = Game.ammunitionExtras.get(i);
            if (ammoBoxAtivo != null) {
                Rectangle trashRetangle = new Rectangle(ammoBoxAtivo.getX() + maskx, ammoBoxAtivo.getY() + masky, Entity.SIZEENTITYX, Entity.SIZEENTITYY);
                if (player.intersects(trashRetangle)) {
                    ammoBox = ammoBoxAtivo;
                    PlayerAudio.tocaAudio("reload");
                    return true;
                }
            }
        }
        return false;
    }

    // -------------------------------- //
    //          Movimentacao            //
    // -------------------------------- //

    // colisão com escadas
    public boolean colisaoEscada(int nextx, int nexty) {
        Rectangle retanguloPlayer = new Rectangle(nextx + maskx, nexty + masky, maskw, maskh);
        for (int i = 0; i < Game.escada.size(); i++) {
            Escada escada = Game.escada.get(i);
            Rectangle retanguloEscada = new Rectangle(escada.getX() + maskx, escada.getY() + masky, Entity.SIZEENTITYX, Entity.SIZEENTITYY);
            if (retanguloPlayer.intersects(retanguloEscada)) {
                jump = false;
                isJump = false;
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

         //g.fillRect((int)Player.atualX-Camera.x, (int)Player.atualY + 16-Camera.y,  32,32);

        //Game.tirosPLayer.add(new TiroPlayer((int)Player.atualX-Camera.x, (int)Player.atualY + 16-Camera.y , 30, 2, null, "tiro", "Direita"));

        // quando anda para a direita (e pula para a direita)
        if (direcaoAtual == direita && movimentacao && !attack && !emEscada) {
            // caso esteja pulando
            if (isJump) {
                // executa a animação do pulo
                g.drawImage(playerJumpRight[index], this.getX() - Camera.x, this.getY() - Camera.y, null);
            } else {
                // se houver colisão por gravidade, ele executa a animação andando
                if (colisao((int) x, (int) (y + 1))) {
                    g.drawImage(playerRight[index], this.getX() - Camera.x, this.getY() - Camera.y, null);
                } else {
                    // caso contrário ele trava em um frame do pulo
                    g.drawImage(playerJumpRight[3], this.getX() - Camera.x, this.getY() - Camera.y, null);
                }
            }
        }

        // quando movimenta para a esquerda e pula para a esquerda
        if (direcaoAtual == esquerda && movimentacao && !attack && !emEscada) {
            // caso esteja pulando
            if (isJump) {
                // executa a animação do pulo
                g.drawImage(playerJumpLeft[index], this.getX() - Camera.x, this.getY() - Camera.y, null);
            } else {
                // se houver colisão por gravidade, ele executa a animação andando
                if (colisao((int) x, (int) (y + 1))) {
                    g.drawImage(playerLeft[index], this.getX() - Camera.x, this.getY() - Camera.y, null);
                } else {
                    // caso contrário ele trava em um frame do pulo
                    g.drawImage(playerJumpLeft[3], this.getX() - Camera.x, this.getY() - Camera.y, null);
                }
            }
        }


        // quando para de andar para a direita (mantem o corpo voltado pra direita)
        // animação de Idle quando o tempo for maior que 4
        if (direcaoAtual == direita && !movimentacao && !emEscada) {

            if (isJump) {
                // executa a animação do pulo
                g.drawImage(playerJumpRight[index], this.getX() - Camera.x, this.getY() - Camera.y, null);
            } else if ((int)tempoParado/60 <= 4) {
                g.drawImage(playerIdleRigth[0], this.getX() - Camera.x, this.getY() - Camera.y, null);
            } else if ((int)tempoParado/60 > 4) {
                g.drawImage(playerIdleRigth[index], this.getX() - Camera.x, this.getY() - Camera.y, null);
            } else {
                g.drawImage(playerRight[0], this.getX() - Camera.x, this.getY() - Camera.y, null);
            }
        }


        // quando para de andar para a esquerda (mantem o corpo voltado pra esquerda)
        // animação de Idle quando o tempo for maior que 4
        if (direcaoAtual == esquerda && !movimentacao && !emEscada) {
            if (isJump) {
                // executa a animação do pulo
                g.drawImage(playerJumpLeft[index], this.getX() - Camera.x, this.getY() - Camera.y, null);
            } else if ((int)tempoParado/60 <= 4) {
                g.drawImage(playerIdleLeft[0], this.getX() - Camera.x, this.getY() - Camera.y, null);
            } else if ((int)tempoParado/60 > 4) {
                g.drawImage(playerIdleLeft[index], this.getX() - Camera.x, this.getY() - Camera.y, null);
            } else {
                g.drawImage(playerLeft[0], this.getX() - Camera.x, this.getY() - Camera.y, null);
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

        // ataque (em testes)
        if (attack) {

            //g.fillRect(this.getX() - Camera.x - Player.SIZEPLAYERX, this.getY() - Camera.y, 50,50);
            //g.fillRect((int)atualX - Camera.x, (int)atualY - Camera.y, 50,50);

            if (attackTimeSound == 0) {
                if (Objects.equals(selectedWeapon, "Cano")) {
                    PlayerAudio.tocaAudio("ataqueCano");
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

    }
}
