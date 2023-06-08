import java.util.Arrays;

public abstract class GameLevel {
    private final String mainText;
    private final GameChoice[] choices;

    public GameLevel(String mainText, GameChoice... choices) {
        this.mainText=mainText;
        this.choices=choices;
    }

    public GameChoice[] getChoices() {
        return choices;
    }

    public String getMainText() {
        return mainText;
    }

    @Override
    public String toString() {
        StringBuilder s1 = new StringBuilder();
        for (int i = 0; i < choices.length; i++) {
            s1.append(i + 1).append(" : ").append(choices[i].getChoiceText()).append("\n");
        }
        return mainText+"\n"+ s1;
    }
}
