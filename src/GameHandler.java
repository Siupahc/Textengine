import java.util.InputMismatchException;
import java.util.Scanner;

public class GameHandler {
    private GameLevel currentLevel;
    private final Player player;
    private Scanner scanner = new Scanner(System.in);

    public GameHandler(GameLevel startLevel,Player player) {
        currentLevel=startLevel;
        this.player=player;
    }
    public void runGame() {
        System.out.println(currentLevel);
        int chosenIndex = takeUserInput();
        System.out.println("\n");
        currentLevel = GameLevel.ofFile(currentLevel.getCurrentChoices()[chosenIndex-1].getNextLevelFileAsString(),player);
    }
    private int takeUserInput() {
        System.out.println("Faites votre choix : tapez le numéro à côté de l'option voulue, puis appuyez sur enter");
        System.out.println("Je choisis le numéro : ");
        int i = 0;

        while (!scanner.hasNextInt()) {
            System.out.println("Veuillez entrer un numéro valide.");
            scanner.nextLine(); // Vider l'entrée non valide
        }

        i = scanner.nextInt();
        scanner.nextLine(); // Vider la ligne après avoir lu l'entier

        return i;
    }




}
