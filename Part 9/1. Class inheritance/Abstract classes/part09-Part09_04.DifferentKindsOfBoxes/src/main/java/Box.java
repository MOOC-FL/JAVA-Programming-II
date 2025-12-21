import java.util.ArrayList;
import java.util.List;

public abstract class Box {
    private List<Item> items;
    
    public Box() {
        this.items = new ArrayList<>();
    }
    
    public void add(Item item) {
        items.add(item);
    }
    
    public void add(List<Item> items) {
        for (Item item : items) {
            add(item);
        }
    }
    
    public boolean isInBox(Item item) {
        return items.contains(item);
    }
    
    protected List<Item> getItems() {
        return items;
    }
    
    public abstract boolean canAdd(Item item);
}