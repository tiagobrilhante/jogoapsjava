package entidades.player;

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


// aqui tem coisas bem importantes sobre a mecânica do player
public class Player extends Entity {

    // movimentação basica do player
    public boolean right, left, down, up;

    public static double atualX, atualY;

    // velocidade de deslocamento do player
    public double speed = 2.9;

    // vida inicial e máximo de vida permitido no jogo
    public static double life = 100, maxLife = 100;

    // o player começa a fase virado para a direita
    public int direita = 1, esquerda = 0;
    public int direcaoAtual = direita;

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
    public BufferedImage[] playerRight, playerLeft, playerIdleLeft, playerIdleRigth, playerJumpLeft, playerJumpRight, playerEscada, playerAttackEsquerda, playerAttackDireita;

    // se o player está pulando (jump é o momento que aperto o pulo, isJump é enquanto o pulo está acontecendo)
    public boolean jump = false, isJump = false;

    public boolean attack = false;

    // altura de incremento do pulo
    public int jumpHeigth = 64;
    // frame inicial do pulo
    public int jumpFrames = 0;

    // inimigo
    public Inimigo enemy;

    // kits de vida
    public KitHealth vida;
    public TrashBag trashBag;

    // posicionamento do player
    public int posx, posy;

    // indice de dano (será alterado face a cada ameaça)
    public double damageFactor = 0.20;

    // o quanto de vida um kit de recuperação recupera (no futuro poderão existir tipos de kits diferentes)
    public int indiceRecuperacaoKitVida = 10;

    public int timerPlayer = 0, tempoParado;
    public int timerEnemy = 0;

    public List<Particula> particulas = new ArrayList<>();


    // construtor
    public Player(int x, int y, int width, int height, BufferedImage sprite) {
        super(x, y, width, height, sprite);

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
        playerAttackDireita = new BufferedImage[4];

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

        // loop para montar o array para ataque
        for (int i = 0; i < 4; i++) {
            playerAttackDireita[i] = Game.spritePlayer.getSprite(0, 80, SIZEPLAYERX * 2, SIZEPLAYERY);
        }

    }

    public void tick() {
        // aqui eu inicio a movimentação em parado ==== 0
        movimentacao = 0;

        atualX = (int) x;
        atualY = (int) y;

        System.out.println("x: " + x + "y: " + y);
        timerPlayer++;
        double rawTime = timerPlayer / 60;
        tempoParado = (int) rawTime;


        timerEnemy++;

        // gerenciamento de update das particulas
        for (int i = 0; i < particulas.size(); i++) {
            particulas.get(i).update();
            Particula part = particulas.get(i);
            // duração da particula na tela (otimização)
            if (part.timer >= 60) {
                particulas.remove(part);
            }
        }


        if (!colisao((int) x, (int) (y + 1)) && !isJump && !emEscada) {
            // não existe a colisão e não estou pulando no inimigo
            // situação normal do player
            // estou parado de boas
            // sou empurrado para o chão
            y += 2;

            // esssa situação foi posta apenas pra teste
            // caso eu pule em cima do inimigo, eu reduzo a vida dele (e mato se zerar)
            for (int i = 0; i < Game.inimigo.size(); i++) {
                Inimigo e = Game.inimigo.get(i);
                // se o player estiver no eixo x e y do personagem

                // tenho que ajustar a lógica disso...
                // na verdade, implementar logo o tiro e arpa de impacto
                if (ataquePulo(this.getX(), this.getY() - 1) && e.getX() == this.getX()) {
                    isJump = true;
                    e.life--;
                    if (e.life == 0) {

                        // adiciona particulas da explosao

                        // adiciona particulas da explosao
                        for (int j = 0; j < 320; j++) {
                            particulas.add(new Particula((int) e.x, (int) e.y, 3, 3, Color.BLUE));
                        }

                        Game.inimigo.remove(e);

                        break;
                    }
                    break;
                }


            }
        }

        // caso eu me movimente para a direita
        if (right && !colisao((int) (x + speed), this.getY())) {
            x += speed;
            movimentacao = 1;
            timerPlayer = 0;
            direcaoAtual = direita;
        }

        // caso eu me movimente para a esquerda
        if (left && !colisao((int) (x - speed), this.getY())) {
            x -= speed;
            movimentacao = 1;
            timerPlayer = 0;
            direcaoAtual = esquerda;
        }

        // momento do pulo
        if (jump) {
            if (colisao(this.getX(), this.getY() + 1)) {
                isJump = true;
                timerPlayer = 0;
            }
        }

        // durante a execução do pulo
        if (isJump) {
            timerPlayer = 0;
            if (!colisao(this.getX(), this.getY() - 2)) {
                y -= 2;
                jumpFrames += 2;
                if (jumpFrames == jumpHeigth) {
                    isJump = false;
                    jump = false;
                    jumpFrames = 0;
                }
            } else {
                isJump = false;
                jump = false;
                jumpFrames = 0;
            }

        }


        //Só deve ser implementado nas fases de nave e para subir e descer escadas
        if (emEscada && !colisao((int)x, (int)(y+2))) {
            if (down) {
                y += speed;
                y = (int)y;
            }
            if (up) {
                y -= speed;
                y = (int)y;
            }
            movimentacao = 1;
            timerPlayer = 0;
        }

        if (emEscada && colisao((int)x, (int)(y+2))) {
            System.out.println("estou aqui");
            if (up) {
                y -= speed;
                y = (int)y;
            }
            if (down){
                y = (int)y-4;
            }
        }

        if (attack) {


            for (int i = 0; i < Game.inimigo.size(); i++) {
                Inimigo e = Game.inimigo.get(i);
                // se o player estiver no eixo x e y do personagem

                // tenho que ajustar a lógica disso...
                // na verdade, implementar logo o tiro e arpa de impacto
                System.out.println("this.getx"+this.getX());
                System.out.println("this.gety"+this.getY());
                System.out.println("enemyGetx" + e.getX());
                System.out.println("enemyGetY" + e.getY());
                if (ataqueCano(this.getX(), this.getY())) {

                    System.out.println("life: " + e.life);
                    e.life--;
                    e.setX(e.getX()-20);
                    if (e.life == 0) {

                        // adiciona particulas da explosao
                        for (int j = 0; j < 320; j++) {
                            particulas.add(new Particula(e.getX(), e.getY(), 3, 3, Color.BLUE));
                        }

                        Game.inimigo.remove(e);

                        break;
                    }
                    break;
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

        // mnovimentação do player
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


        emEscada = colisaoEscada(this.getX(), this.getY());

        // guarda a posição da placa de checkpoint
        if (checkPoint(this.getX(), this.getY())) {
            posx = this.getX();
            posy = this.getY();
        }

        // manda de volta para a placa de checkpoint quando a vida chega a zero
        // pode e deve ser melhorado com a tela de game over...
        // que vai perguntar ao player se ele quer voltar ao menu inicial ou se ele quer
        // voltar para o último check point
        if (life <= 0) {
            setX(posx);
            setY(posy);
            // quando volta para o checkPoint, volta com a vida cheia
            // pode ser alterado para voltar com a vida quando ele encostou no check point
            life = 100;
        }

        // posicionamento da camera, sempre em relaçao ao player
        Camera.x = Camera.Clamp(this.getX() - (Game.WIDTH / 2), 0, Mundo.WIDTH * Entity.SIZEENTITYX - SIZEPLAYERX);
        Camera.y = Camera.Clamp(this.getY() - (Game.HEIGTH / 2), 0, Mundo.HEIGHT * Entity.SIZEENTITYY - SIZEPLAYERY);

    }

    // colisor base do player
    public boolean colisao(int nextx, int nexty) {

        // tenho que ajustar a colisão da escada
        Rectangle player = new Rectangle(nextx + maskx, nexty + masky, maskw, maskh);

        for (int i = 0; i < Game.entidades.size(); i++) {
            Entity entidade = Game.entidades.get(i);
            if (entidade instanceof Solido) {
                Rectangle solido = new Rectangle(entidade.getX() + maskx, entidade.getY() + masky, Entity.SIZEENTITYX, Entity.SIZEENTITYY);
                if (player.intersects(solido)) {
                    return true;
                }
            }
        }
        return false;
    }

    // checkpoint (player toca o checkpoint)
    public boolean checkPoint(int nextx, int nexty) {
        Rectangle player = new Rectangle(nextx + maskx, nexty + masky, maskw, maskh);

        for (int i = 0; i < Game.entidades.size(); i++) {
            Entity entidade = Game.entidades.get(i);
            if (entidade instanceof CheckPoint) {
                Rectangle placa = new Rectangle(entidade.getX() + maskx, entidade.getY() + masky, Entity.SIZEENTITYX, Entity.SIZEENTITYY);
                if (player.intersects(placa)) {
                    return true;
                }
            }
        }
        return false;
    }

    // player toma dano dos inimigos
    public boolean damage(int nextx, int nexty) {
        Rectangle player = new Rectangle(nextx + maskx, nexty + masky, maskw, maskh);
        for (int i = 0; i < Game.inimigo.size(); i++) {
            Inimigo entidade = Game.inimigo.get(i);
            if (entidade != null) {
                Rectangle solido = new Rectangle(entidade.getX() + maskx, entidade.getY() + masky, maskw, maskh);
                if (player.intersects(solido)) {
                    enemy = entidade;
                    return true;
                }
            }
        }
        return false;
    }

    // player ataca com pulo o inimigo
    public boolean ataquePulo(int nextx, int nexty) {
        Rectangle retanguloPlayer = new Rectangle(nextx + maskx, nexty + masky, maskw, maskh);

        for (int i = 0; i < Game.inimigo.size(); i++) {
            Inimigo inimigo = Game.inimigo.get(i);
            if (inimigo != null) {
                Rectangle retanguloInimigo = new Rectangle(inimigo.getX() + maskx, inimigo.getY() + masky, maskw, maskh);
                if (retanguloPlayer.intersects(retanguloInimigo)) {
                    enemy = inimigo;
                    return true;
                }
            }
        }
        return false;
    }

    public boolean ataqueCano(int nextx, int nexty) {

        int incremento = 0;
        if (direcaoAtual == esquerda){
            System.out.println("esquerda");
            incremento -= Player.SIZEPLAYERX;
        } else {
            System.out.println("direita");
            incremento += Player.SIZEPLAYERX;
        }

        System.out.println("incremento" + incremento);

        Rectangle retanguloPlayer = new Rectangle(nextx + maskx + incremento, nexty + masky, maskw, maskh);

        for (int i = 0; i < Game.inimigo.size(); i++) {
            Inimigo inimigo = Game.inimigo.get(i);
            if (inimigo != null) {
                Rectangle retanguloInimigo = new Rectangle(inimigo.getX() + maskx, inimigo.getY() + masky, maskw, maskh);
                if (retanguloPlayer.intersects(retanguloInimigo)) {
                    enemy = inimigo;
                    System.out.println("contato");
                    return true;
                }
            }
        }
        System.out.println("erro");
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

    public boolean colisaoEscada(int nextx, int nexty) {
        Rectangle retanguloPlayer = new Rectangle(nextx + maskx, nexty + masky, maskw, maskh);
        for (int i = 0; i < Game.escada.size(); i++) {
            Escada escada = Game.escada.get(i);

            Rectangle retanguloEscada = new Rectangle(escada.getX() + maskx, escada.getY() + masky, Entity.SIZEENTITYX, Entity.SIZEENTITYY);
            if (retanguloPlayer.intersects(retanguloEscada)) {

                // primeiro - os tipos 1 e 3 são limitadores
                // se eu chego no 1 não posso mais descer
                // se eu chego no 3, só posso subir a minha própria altura
                // se eu estou no bloco 3 posso descer

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
            if (direcaoAtual == esquerda) {
                g.drawImage(playerAttackEsquerda[indexAtack], this.getX() - Camera.x -Player.SIZEPLAYERX, this.getY() - Camera.y , null);
                if (indexAtack == 3) {
                    attack = false;
                    indexAtack = 0;
                }
            } else {
                g.drawImage(playerAttackDireita[indexAtack], this.getX() - Camera.x, this.getY() - Camera.y, null);
                if (indexAtack == 3) {
                    attack = false;
                    indexAtack = 0;
                }
            }

        }

        // fazer a animação de dash

        // fazer a animação de tiro

        // fazer a animação de arma de impacto


    }
}
