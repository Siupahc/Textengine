public class ItemConditionGameChoice extends GameChoice {
    private final Item key;

    public ItemConditionGameChoice(String nextLevelFile, String choiceText, Item key) {
        super(nextLevelFile, choiceText);
        this.key=key;
    }
    @Override
    public boolean isVisibleBy(Player player) {
        return player.getInventory().contains(key);
    }
}
