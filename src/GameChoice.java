import java.io.File;

public class GameChoice {
    private final String nextLevelFile;
    private final String choiceText;

    public GameChoice(String nextLevelFile, String choiceText) {
        this.nextLevelFile = nextLevelFile;
        this.choiceText = choiceText;
    }
    public String getNextLevelFileAsString() {
        return nextLevelFile;
    }
    public String getChoiceText(){
        return choiceText;
    }
}
