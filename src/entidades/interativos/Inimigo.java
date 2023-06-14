package entidades.interativos;

import Mundo.Camera;
import entidades.Entity;
import entidades.solidos.Solido;
import main.Game;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Objects;


public class Inimigo extends Entity {

    // velocidade de deslocamento do inimigo
    // pode mudar de acordo com o inimigo
    public double speed = 0.5;

    // se o inimigo está ou não se movimentando
    // 1 = movimento
    // 0 = parado
    // (poderia ser usado boolean)
    public int movimentacao = 1;

    public String frenteIni = "Esquerda";

    // ajusta o tipo de inimigo
    private final int tipoInimigo;

    // tamanho base dos inimigos
    public static int SIZEENEMYX = 32, SIZEENEMYY = 32;
    public static int SIZEENEMYX2 = 32, SIZEENEMYY2 = 48;

    // frames = momento inicial dos frames do inimigo (para loop)
    // maxFrames = numero máximo de frames para reinicio do loop
    // index = index inicial na array de frames
    // maxIndex, informa o numero máximo de posicoes da array de animacao
    public int frames = 0, maxFrames = 7, index = 0, maxIndex = 3, indexAttack = 0, maxIndexAttack = 1;

    // vida do inimigo
    // life = vida inicial
    // maxLife = vida máxima (poderá ser removido)
    public int life = 5;

    public boolean emMovimento;

    // repassa a imagem carregada do inimigo
    public BufferedImage[] inimigoFrenteEsquerda;
    public BufferedImage[] inimigoEsperaFrenteEsquerda;
    public BufferedImage[] inimigoAtaqueFrenteEsquerda;
    public BufferedImage[] inimigoFrenteDireita;
    public BufferedImage[] inimigoEsperaFrenteDireita;
    public BufferedImage[] inimigoAtaqueFrenteDireita;

    public int identificadorUnico;

    public double atualX;

    // construtor de inimigo
    // passa a posicao inicial (x,Y)
    // tamanho, altura
    // e sprite a ser usado
    // herda de entidade
    public Inimigo(int identificadorUnico, int x, int y, int width, int height, int tipoInimigo, BufferedImage sprite, String tipo) {

        super(x, y, width, height, sprite, tipo);

        this.identificadorUnico = identificadorUnico;
        this.tipoInimigo = tipoInimigo;
        this.tipo = "Inimigo";

        // aqui eu crio a array responsável pelas imagens de movimento do inimigo
        // nesse caso, existem 3 posições (ver spritesheet)

        if (tipoInimigo == 1) {

            inimigoFrenteEsquerda = new BufferedImage[4];
            inimigoEsperaFrenteEsquerda = new BufferedImage[4];
            inimigoAtaqueFrenteEsquerda = new BufferedImage[2];
            inimigoFrenteDireita = new BufferedImage[4];
            inimigoEsperaFrenteDireita = new BufferedImage[4];
            inimigoAtaqueFrenteDireita = new BufferedImage[2];

            // populo array por loop, passando a posição dele e tamanho de acordo com o sprite
            for (int i = 0; i < 4; i++) {
                inimigoFrenteEsquerda[i] = Game.spriteEnemy.getSprite((i * SIZEENEMYX), 0, SIZEENEMYX, SIZEENEMYY);
                inimigoEsperaFrenteEsquerda[i] = Game.spriteEnemy.getSprite((160 + (i * SIZEENEMYX)), 0, SIZEENEMYX, SIZEENEMYY);
                inimigoFrenteDireita[i] = Game.spriteEnemy.getSprite((i * SIZEENEMYX), 32, SIZEENEMYX, SIZEENEMYY);
                inimigoEsperaFrenteDireita[i] = Game.spriteEnemy.getSprite(160 + (i * SIZEENEMYX), 32, SIZEENEMYX, SIZEENEMYY);
            }

            for (int i = 0; i < 2; i++) {
                inimigoAtaqueFrenteEsquerda[i] = Game.spriteEnemy.getSprite((320 + (i * SIZEENEMYX)), 0, SIZEENEMYX, SIZEENEMYY);
                inimigoAtaqueFrenteDireita[i] = Game.spriteEnemy.getSprite((320 + (i * SIZEENEMYX)), 32, SIZEENEMYX, SIZEENEMYY);
            }


        } else if (tipoInimigo == 2) {
            inimigoFrenteEsquerda = new BufferedImage[4];
            inimigoEsperaFrenteEsquerda = new BufferedImage[4];
            inimigoAtaqueFrenteEsquerda = new BufferedImage[2];
            inimigoFrenteDireita = new BufferedImage[4];
            inimigoEsperaFrenteDireita = new BufferedImage[4];
            inimigoAtaqueFrenteDireita = new BufferedImage[2];

            // populo array por loop, passando a posição dele e tamanho de acordo com o sprite
            for (int i = 0; i < 4; i++) {
                inimigoFrenteEsquerda[i] = Game.spriteEnemy.getSprite((i * SIZEENEMYX), 64, SIZEENEMYX, SIZEENEMYY);
                inimigoEsperaFrenteEsquerda[i] = Game.spriteEnemy.getSprite((160 + (i * SIZEENEMYX)), 64, SIZEENEMYX, SIZEENEMYY);
                inimigoFrenteDireita[i] = Game.spriteEnemy.getSprite((i * SIZEENEMYX), 96, SIZEENEMYX, SIZEENEMYY);
                inimigoEsperaFrenteDireita[i] = Game.spriteEnemy.getSprite(160 + (i * SIZEENEMYX), 96, SIZEENEMYX, SIZEENEMYY);
            }

            for (int i = 0; i < 2; i++) {
                inimigoAtaqueFrenteEsquerda[i] = Game.spriteEnemy.getSprite((320 + (i * SIZEENEMYX)), 64, SIZEENEMYX, SIZEENEMYY);
                inimigoAtaqueFrenteDireita[i] = Game.spriteEnemy.getSprite((320 + (i * SIZEENEMYX)), 96, SIZEENEMYX, SIZEENEMYY);
            }
        } else if (tipoInimigo == 3) {
            inimigoFrenteEsquerda = new BufferedImage[5];
            inimigoEsperaFrenteEsquerda = new BufferedImage[4];
            inimigoAtaqueFrenteEsquerda = new BufferedImage[2];
            inimigoFrenteDireita = new BufferedImage[5];
            inimigoEsperaFrenteDireita = new BufferedImage[4];
            inimigoAtaqueFrenteDireita = new BufferedImage[2];

            // populo array por loop, passando a posição dele e tamanho de acordo com o sprite
            for (int i = 0; i < 4; i++) {
                inimigoFrenteEsquerda[i] = Game.spriteEnemy.getSprite((i * SIZEENEMYX2), 272, SIZEENEMYX2, SIZEENEMYY2);
                inimigoEsperaFrenteEsquerda[i] = Game.spriteEnemy.getSprite(((i * SIZEENEMYX2)), 176, SIZEENEMYX2, SIZEENEMYY2);
                inimigoFrenteDireita[i] = Game.spriteEnemy.getSprite((i * SIZEENEMYX2), 224, SIZEENEMYX2, SIZEENEMYY2);
                inimigoEsperaFrenteDireita[i] = Game.spriteEnemy.getSprite((i * SIZEENEMYX2), 128, SIZEENEMYX2, SIZEENEMYY2);

            }

            for (int i = 0; i < 2; i++) {
                inimigoAtaqueFrenteEsquerda[i] = Game.spriteEnemy.getSprite(((i * SIZEENEMYX)), 272, SIZEENEMYX, SIZEENEMYY);
                inimigoAtaqueFrenteDireita[i] = Game.spriteEnemy.getSprite(((i * SIZEENEMYX)), 224, SIZEENEMYX, SIZEENEMYY);
            }
        }
    }

    // relatico a cada atualização de tela
    public void tick() {
        emMovimento = atualX != x;
        atualX = x;

        // inicia a colisão com objetos solidos
        if (!colisao((int) x, (int) (y + 1), this.getTipoInimigo())) {
            y += 2;
        }

        // tenta se movimentar para a posição onde está o player... pode ser melhorado
        // nesse caso movimenta para a ESQUERDA
        if (Game.player.getX() < this.getX() && !colisao((int) (x - speed), this.getY(), this.getTipoInimigo())) {
            frenteIni = "Esquerda";
            x -= speed;
        }

        // nesse caso movimenta o inimigo para a DIREITA
        if (Game.player.getX() > this.getX() && !colisao((int) (x + speed), this.getY(), this.getTipoInimigo())) {
            x += speed;
            frenteIni = "Direita";
        }

        // ativa a funcionalidade de loop de imagens quando o inimigo está se movimentando
        if (movimentacao == 1) {
            frames++;
            if (this.getTipoInimigo() == 1 || this.getTipoInimigo() == 2) {
                maxFrames = 7;
            } else {
                maxFrames = 10;
            }
            if (frames == maxFrames) {
                index++;
                frames = 0;
                if (index > maxIndex) {
                    index = 0;
                }
            }
        }

        if (ataque(getX(), getY(), this.getTipoInimigo())){
            frames++;
            if (frames == maxFrames) {
                indexAttack++;
                frames = 0;
                if (indexAttack > maxIndexAttack) {
                    indexAttack = 0;
                }
            }
        }

    }

    // responsável por renderizar a imagem
    public void render(Graphics g) {

        if (Objects.equals(frenteIni, "Esquerda")) {
            if (emMovimento) {
                g.drawImage(inimigoFrenteEsquerda[index], this.getX() - Camera.x, this.getY() - Camera.y, null);
            } else {
                g.drawImage(inimigoEsperaFrenteEsquerda[index], this.getX() - Camera.x, this.getY() - Camera.y, null);
            }
        } else {
            if (emMovimento) {
                g.drawImage(inimigoFrenteDireita[index], this.getX() - Camera.x, this.getY() - Camera.y, null);
            } else {
                g.drawImage(inimigoEsperaFrenteDireita[index], this.getX() - Camera.x, this.getY() - Camera.y, null);
            }
        }

        if (ataque(getX(), getY(), this.getTipoInimigo())){
            if (Objects.equals(frenteIni, "Esquerda")) {
                g.drawImage(inimigoAtaqueFrenteEsquerda[indexAttack], this.getX() - Camera.x, this.getY() - Camera.y, null);
            } else {
                g.drawImage(inimigoAtaqueFrenteDireita[indexAttack], this.getX() - Camera.x, this.getY() - Camera.y, null);
            }
        }


    }


    // função para colisão do inimigo
    public boolean colisao(int nextx, int nexty, int tipo) {
        // prepara um retângulo da entidade inimigo

        Rectangle inimigo;
        if (tipo == 1 || tipo == 2) {
            inimigo = new Rectangle(nextx, nexty, SIZEENEMYX, SIZEENEMYY);
        } else {
            inimigo = new Rectangle(nextx, nexty, SIZEENEMYX2, SIZEENEMYY2);
        }

        // para cada entidade, checar se tem intersect com solido e puxa o inimigo em direção ao chão (solido)
        for (int i = 0; i < Game.entidades.size(); i++) {
            Entity entidade = Game.entidades.get(i);
            if (entidade instanceof Solido) {
                Rectangle solido = new Rectangle(entidade.getX(), entidade.getY(), Entity.SIZEENTITYX, Entity.SIZEENTITYY);
                if (inimigo.intersects(solido)) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean ataque(int nextx, int nexty, int tipo) {
        // prepara um retângulo da entidade inimigo

        Rectangle inimigo;
        if (tipo == 1 || tipo == 2) {
            inimigo = new Rectangle(nextx, nexty, SIZEENEMYX, SIZEENEMYY);
        } else {
            inimigo = new Rectangle(nextx, nexty, SIZEENEMYX2, SIZEENEMYY2);
        }

        // faço o retangulo de player

        Rectangle player = new Rectangle(Game.player.getX(), Game.player.getY(), Game.player.getWidth(), Game.player.getHeight());
        return inimigo.intersects(player);
    }

    public int getTipoInimigo() {
        return tipoInimigo;
    }

    @Override
    public String toString() {
        return "Inimigo{" +
                " identificadorUnico=" + identificadorUnico +
                " x=" + x +
                " y=" + y +
                ", tipoInimigo=" + tipoInimigo +
                ", life=" + life +
                ", speed=" + speed +
                ", movimentacao=" + movimentacao +
                ", frames=" + frames +
                ", maxFrames=" + maxFrames +
                ", index=" + index +
                ", maxIndex=" + maxIndex +
                '}';
    }
}
