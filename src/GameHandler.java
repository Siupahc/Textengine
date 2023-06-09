public class GameHandler {
    private GameLevel currentLevel;
    private Player player;

    public GameHandler(GameLevel startLevel) {
        currentLevel=startLevel;
    }
    public void runGame() {
        System.out.println(currentLevel);

    }
    }
