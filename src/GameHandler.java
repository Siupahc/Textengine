import java.util.Scanner;

public class GameHandler {
    private GameLevel currentLevel;
    private final Player player;

    public GameHandler(GameLevel startLevel,Player player) {
        currentLevel=startLevel;
        this.player=player;
    }
    public void runGame() {
        System.out.println(currentLevel);
        int chosenIndex = takeUserInput();
        System.out.println("\n");
        currentLevel = GameLevel.ofFile(currentLevel.getCurrentChoices()[chosenIndex].getNextLevelFileAsString());
    }
    private int takeUserInput() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Faites votre choix : tapez le numéro à côté de l'option voulue, puis appuyez sur enter");
        System.out.println("Je choisis le numéro : ");
        int i = 0;
        while (i<1||i>currentLevel.getCurrentChoices().length) {
            i = scanner.nextInt();
        }
        scanner.close();
        return i;
    }
}
