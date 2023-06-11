import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Player player = new Player();
        String firstLevelFileName = "first.txt";
        GameLevel firstLevel = GameLevel.ofFile(firstLevelFileName,player);
        GameHandler handler = new GameHandler(firstLevel,player);
        do {
            try {
                handler.runGame();
            } catch (LastLevelException e) {
                break;
            }
        } while (true);
        Scanner scanner = new Scanner(System.in);
        System.out.println("Tapez stop pour quitter");
        String text = scanner.nextLine();
        while (!text.equals("stop")) {
            text = scanner.nextLine();
        }
    }
}
