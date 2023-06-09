import java.io.*;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

public class GameLevel {
    private final String mainText;
    private final GameChoice[] choices;
    private final Player player;

    public GameLevel(String mainText,Player player, GameChoice... choices) {
        this.mainText=mainText;
        this.player=player;
        this.choices=choices;
    }
    public GameLevel(String mainText,Player player,Item key, GameChoice... choices) {
        this.mainText=mainText;
        this.player=player;
        player.getInventory().add(key);
        this.choices=choices;
    }
    public static GameLevel ofFile(String filename) {
        String betterFilename = "resources/"+filename;
        File levelFile = new File(betterFilename);
        int i = 0;
        try (Scanner scanner = new Scanner(levelFile)) {
            scanner.useDelimiter("~");

            while (scanner.hasNext()) {
                String text = scanner.next().trim();
                if (!text.isEmpty()) {
                    System.out.println(text);
                    System.out.println(i++);
                }
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        return new GameLevel(null,null, (GameChoice) null);
    }

    public GameChoice[] getCurrentChoices() {
        List<GameChoice> choiceList = Arrays.asList(choices);
        choiceList.removeIf((gameChoice -> !gameChoice.isVisibleBy(player)));
        return choiceList.toArray(new GameChoice[0]);
    }

    public String getMainText() {
        return mainText;
    }

    @Override
    public String toString() {
        StringBuilder s1 = new StringBuilder();
        GameChoice[] currentChoices = getCurrentChoices();
        for (int i = 0; i < currentChoices.length; i++) {
            s1.append(i + 1).append(" : ").append(currentChoices[i].getChoiceText()).append("\n");
        }
        return mainText+"\n"+ s1;
    }
}
