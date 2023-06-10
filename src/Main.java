public class Main {
    public static void main(String[] args) {
        Player player = new Player();
        String firstLevelFileName = "test.txt";
        GameLevel firstLevel = GameLevel.ofFile(firstLevelFileName,player);
        GameHandler handler = new GameHandler(firstLevel,player);
        while (true) {
            handler.runGame();
        }
    }
}
