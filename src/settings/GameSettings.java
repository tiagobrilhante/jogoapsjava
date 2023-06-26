package settings;

public abstract class GameSettings {
    //-------------//
    //  GAME NAME  //
    //-------------//
    private final static String gameName = "ScrAPS World";

    //-------------//
    //  GAME ICON  //
    //-------------//
    private final static String gameIconPath ="/res/icons/scrapsicon.png";

    //----------------//
    //  GAME SPRITES  //
    //----------------//

    public static String spriteSolidPath = "/res/spritesheets/terrain/solid/spritesheet32solid.png";
    public static String spriteDecorationPath = "/res/spritesheets/terrain/decoration/spritesheet32decoration.png";
    public static String spriteInterativePath = "/res/spritesheets/terrain/interative/spritesheet32interativo.png";
    public static String spritePlayerPath = "/res/spritesheets/player/spritesheetPlayer3.png";
    public static String spriteEnemyPath = "/res/spritesheets/enemy/spritesheetEnemy.png";
    public static String spriteCeuPath = "/res/spritesheets/terrain/decoration/ceusprite3.png";
    public static String spriteMountainPath = "/res/spritesheets/terrain/decoration/mountain1lvlsprite2.png";
    public static String spriteNuvemPath = "/res/spritesheets/terrain/decoration/ceuspriteClouds.png";
    public static String levelPath = "/res/fases/";

    // WIDTH, HEIGHT, SCALE
    private final static int[] gameDimensions = {520, 300,2};

    public static String getGameName() {
        return gameName;
    }

    public static String getGameIconPath() {
        return gameIconPath;
    }

    public static int getGAME_WIDTH() {
        return gameDimensions[0];
    }

    public static int getGAME_HEIGHT() {
        return gameDimensions[1];
    }

    public static int getGAME_SCALE() {
        return gameDimensions[2];
    }
}

