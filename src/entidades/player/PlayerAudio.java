package entidades.player;

import audioInterface.Audio;

import java.util.Objects;

public class PlayerAudio {


    public static String soundPathAttack = "/res/sounds/soundfx/attack.wav";
    public static String soundPathJump = "/res/sounds/soundfx/jump.wav";
    public static String soundPathTiro = "/res/sounds/soundfx/go.wav";
    // public static String soundPathSteps = "/res/sounds/soundfx/step.wav";
    public static String soundPathTake = "/res/sounds/soundfx/take.wav";
    public static String soundPathReload = "/res/sounds/soundfx/reload.wav";
    public static String soundPath1up = "/res/sounds/soundfx/1up.wav";
    public static String soundPathExtraEnergy = "/res/sounds/soundfx/energy.wav";
    public static String soundPathDamage = "/res/sounds/soundfx/damage.wav";

    static Audio audioAttack = new Audio(null, false);
    static Audio audioJump = new Audio(null, false);
    static Audio audioTiro = new Audio(null, false);
    // Audio audioSteps = new Audio(null, false);
    static Audio audioTake = new Audio(null, false);
    static Audio audioReload = new Audio(null, false);
    static Audio audio1up = new Audio(null, false);
    static Audio audioEnergy = new Audio(null, false);
    static Audio audioDamage = new Audio(null, false);

    public PlayerAudio() {
    }

    public static void tocaAudio(String tipo){
        if (Objects.equals(tipo, "ataqueCano")) {
            audioAttack = new Audio(soundPathAttack, false); // Chamando a classe aonde está o audio.
            audioAttack.start();
        }
        if (Objects.equals(tipo, "jump")) {
            audioJump = new Audio(soundPathJump, false); // Chamando a classe aonde está o audio.
            audioJump.start();
        }
        if (Objects.equals(tipo, "tiro")) {
            audioTiro = new Audio(soundPathTiro, false); // Chamando a classe aonde está o audio.
            audioTiro.start();
        }
        if (Objects.equals(tipo, "pegaLixo")) {
            audioTake = new Audio(soundPathTake, false); // Chamando a classe aonde está o audio.
            audioTake.start();
        }
        if (Objects.equals(tipo, "reload")) {
            audioReload = new Audio(soundPathReload, false); // Chamando a classe aonde está o audio.
            audioReload.start();
        }
        if (Objects.equals(tipo, "vidaExtra")) {
            audio1up = new Audio(soundPath1up, false); // Chamando a classe aonde está o audio.
            audio1up.start();
        }
        if (Objects.equals(tipo, "extraEnergy")) {
            audioEnergy = new Audio(soundPathExtraEnergy, false); // Chamando a classe aonde está o audio.
            audioEnergy.start();
        }
        if (Objects.equals(tipo, "dano")) {
            audioDamage = new Audio(soundPathDamage, false); // Chamando a classe aonde está o audio.
            audioDamage.start();
        }


    }
}
