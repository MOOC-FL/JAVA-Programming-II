/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author abdel
 */
import java.util.List;
import java.util.ArrayList;
public class MisplacingBox extends Box {
    private List<Item> lostItems;
    
    public MisplacingBox() {
        super();
        this.lostItems = new ArrayList<>();
    }
    
    @Override
    public void add(Item item) {
        if (item == null) {
            throw new IllegalArgumentException("Cannot add null item");
        }
        lostItems.add(item);
        // Optionally call super.add(item) if using parent's storage
    }
    
    @Override
    public void add(List<Item> items) {
        if (items == null) {
            throw new IllegalArgumentException("Item list cannot be null");
        }
        for (Item item : items) {
            add(item);
        }
    }
    
    @Override
    public boolean isInBox(Item item) {
        // Consistent behavior: always false
        return false;
    }
    
    @Override
    public boolean canAdd(Item item) {
        // Can add any non-null item
        return item != null;
    }
    
    // Bonus method: see what's "lost" in the box
    public void revealContents() {
        System.out.println("MisplacingBox contents (even though isInBox returns false):");
        for (Item item : lostItems) {
            System.out.println("  - " + item);
        }
    }
}
