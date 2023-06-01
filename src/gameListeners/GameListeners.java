package gameListeners;

import main.Game;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Objects;

public class GameListeners implements KeyListener {

    private final Game game;

    public GameListeners(Game game) {
        this.game = game;
    }

    // sem uso
    @Override
    public void keyTyped(KeyEvent e) {

    }

    // herda de keylistener
    // método para tecla no momento que é apertada
    @Override
    public void keyPressed(KeyEvent e) {

        // temos que ter cuidado aqui, pois as teclas podem significar eventos diferentes de acordo com a fase
        // gameMenu, tela inicial e game over tb
        if (Objects.equals(Game.gameState, "NORMAL")) {
            if (e.getKeyCode() == KeyEvent.VK_D || e.getKeyCode() == KeyEvent.VK_RIGHT) {
                // tecla D movimenta o player para a direita
                Game.player.right = true;
            } else if (e.getKeyCode() == KeyEvent.VK_A || e.getKeyCode() == KeyEvent.VK_LEFT) {
                // tecla A movimenta o player para a esquerda
                Game.player.left = true;
            } else if (e.getKeyCode() == KeyEvent.VK_W || e.getKeyCode() == KeyEvent.VK_UP) {
                // tecla W movimenta pra cima (usado só em determindaos momentos do jogo)
                Game.player.up = true;
            } else if (e.getKeyCode() == KeyEvent.VK_S || e.getKeyCode() == KeyEvent.VK_DOWN) {
                // tecla S movimenta pra baixo (usado só em determindaos momentos do jogo)
                Game.player.down = true;
            }
            if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                // tecla SPACE faz o plauer pular
                Game.player.jump = true;
            }
            if (e.getKeyCode() == KeyEvent.VK_C) {
                // tecla C faz o plauer atacar
                Game.player.attack = true;
            }
            if (e.getKeyCode() == KeyEvent.VK_P) {
                // tecla P faz o jogo pausar
                if (e.getKeyCode() == KeyEvent.VK_P) {
                    game.togglePause();
                }
            }
            if (e.getKeyCode() == KeyEvent.VK_B) {
                // tecla B muda a arma
                Game.player.toggleWeapon();

            }

        }

        if (Objects.equals(Game.gameState, "HISTORIA")) {
            if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                // tecla W movimenta pra cima (usado só em determindaos momentos do jogo)
                this.game.historia.end = true;
            }
        }

        if (Objects.equals(Game.gameState, "CONTROLES")) {
            if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                // tecla W movimenta pra cima (usado só em determindaos momentos do jogo)
                this.game.controles.end = true;
            }
        }

        if (Objects.equals(Game.gameState, "LEADERBOARDS")) {
            if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                // tecla W movimenta pra cima (usado só em determindaos momentos do jogo)
                this.game.leaderBoard.end = true;
            }
        }

        if (Objects.equals(Game.gameState, "SOBRE")) {
            if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                // tecla W movimenta pra cima (usado só em determindaos momentos do jogo)
                this.game.sobre.end = true;
            }
        }

        if (Objects.equals(Game.gameState, "MENU")) {
            if (e.getKeyCode() == KeyEvent.VK_W || e.getKeyCode() == KeyEvent.VK_UP) {
                // tecla W movimenta pra cima (usado só em determindaos momentos do jogo)
                this.game.gameMenu.up = true;
            } else if (e.getKeyCode() == KeyEvent.VK_S || e.getKeyCode() == KeyEvent.VK_DOWN) {
                // tecla S movimenta pra baixo (usado só em determindaos momentos do jogo)
                this.game.gameMenu.down = true;
            }
            if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                // tecla SPACE faz o plauer pular
                this.game.gameMenu.ok = true;
            }
        }

        if (Objects.equals(Game.gameState, "GAMEOVER")) {
            if (e.getKeyCode() == KeyEvent.VK_W || e.getKeyCode() == KeyEvent.VK_UP) {
                // tecla W movimenta pra cima (usado só em determindaos momentos do jogo)
                game.gameOver.up = true;
            } else if (e.getKeyCode() == KeyEvent.VK_S || e.getKeyCode() == KeyEvent.VK_DOWN) {
                // tecla S movimenta pra baixo (usado só em determindaos momentos do jogo)
                game.gameOver.down = true;

            }
            if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                // tecla SPACE faz o plauer pular
                game.gameOver.ok = true;
            }
        }

    }

    // herda de keylistener
    // momento em que a tlecla apertada é solta
    @Override
    public void keyReleased(KeyEvent e) {
        if (Objects.equals(Game.gameState, "NORMAL")) {
            if (e.getKeyCode() == KeyEvent.VK_D || e.getKeyCode() == KeyEvent.VK_RIGHT) {
                // tecla D movimenta o player para a direita
                Game.player.right = false;
            } else if (e.getKeyCode() == KeyEvent.VK_A || e.getKeyCode() == KeyEvent.VK_LEFT) {
                // tecla A movimenta o player para a esquerda
                Game.player.left = false;
            } else if (e.getKeyCode() == KeyEvent.VK_W || e.getKeyCode() == KeyEvent.VK_UP) {
                // tecla W movimenta pra cima (usado só em determindaos momentos do jogo)
                Game.player.up = false;
            } else if (e.getKeyCode() == KeyEvent.VK_S || e.getKeyCode() == KeyEvent.VK_DOWN) {
                // tecla S movimenta pra baixo (usado só em determindaos momentos do jogo)
                Game.player.down = false;
            }
            if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                // tecla SPACE faz o plauer pular
                Game.player.jump = false;
            }

        }

        if (Objects.equals(Game.gameState, "MENU")) {
            if (e.getKeyCode() == KeyEvent.VK_W || e.getKeyCode() == KeyEvent.VK_UP) {
                // tecla W movimenta pra cima (usado só em determindaos momentos do jogo)
                this.game.gameMenu.up = false;
            } else if (e.getKeyCode() == KeyEvent.VK_S || e.getKeyCode() == KeyEvent.VK_DOWN) {
                // tecla S movimenta pra baixo (usado só em determindaos momentos do jogo)
                this.game.gameMenu.down = false;
            }
            if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                // tecla SPACE faz o plauer pular
                this.game.gameMenu.ok = false;
            }
        }

    }

}
