import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Player {
    private final Set<Item> inventory;

    public Player() {
        inventory = new HashSet<>();
    }
    public Set<Item> getInventory() {
        return inventory;
    }
}
