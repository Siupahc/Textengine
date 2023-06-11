import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

public class GameLevel {
    private final String mainText;
    private final GameChoice[] choices;
    private final Player player;
    private final static String NONE = "none";

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
    public static GameLevel ofFile(String filename,Player currentPlayer) {
        String betterFilename = "resources/"+filename;
        File levelFile = new File(betterFilename);
        String mainText = "";
        int i = 0;
        int numberOfChoices=0;
        Item itemReceived = null;
        int n = 0;
        boolean firstChoice = true;
        GameChoice[] gameChoices = new GameChoice[0];
        try (Scanner scanner = new Scanner(levelFile)) {
            scanner.useDelimiter("~");

            while (scanner.hasNext()) {
                String text = scanner.next().trim();
                if (!text.startsWith("//")&&!text.isEmpty()) {
                    switch (i++) {
                        case 0: {
                            mainText=text;
                            break;
                        }
                        case 1: {
                            itemReceived = Item.itemForString(text);
                            break;
                        }
                        case 2: {
                            numberOfChoices = Integer.parseInt(text);
                            break;
                        }
                        default: {
                            if (firstChoice) {
                                gameChoices = new GameChoice[numberOfChoices];
                                firstChoice = false;
                            }
                            String[] parts = text.split("\\|");
                            String choiceText = parts[0].trim();
                            String neededKey = parts[1].trim();
                            String nextFileName = parts[2].trim();
                            if (neededKey.equals(NONE)) {
                                gameChoices[n++] = new GameChoice(nextFileName,choiceText);
                            }
                            else {
                                Item neededItem = Item.itemForString(neededKey);
                                gameChoices[n++] = new ItemConditionGameChoice(nextFileName,choiceText,neededItem);
                            }
                        }
                    }
                }
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        if (itemReceived!=null) {
            return new GameLevel(mainText,currentPlayer,itemReceived,gameChoices);
        }
        return new GameLevel(mainText,currentPlayer,gameChoices);
    }

    public GameChoice[] getCurrentChoices() {
        List<GameChoice> choiceList = new ArrayList<>(Arrays.asList(choices));
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
    private static Item getItemFromString(String string) {
        Item item = null;
        try {
            Class<Item> enumClass = Item.class;
            item = Enum.valueOf(enumClass, string);
        } catch (IllegalArgumentException | NullPointerException e) {
            e.printStackTrace();
        }
        return item;
    }
}
