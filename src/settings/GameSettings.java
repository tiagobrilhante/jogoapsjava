package settings;

public abstract class GameSettings {
    private final static String gameName = "ScrAPS World";
    private final static String gameIconPath ="/res/icons/scrapsicon.png";

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

