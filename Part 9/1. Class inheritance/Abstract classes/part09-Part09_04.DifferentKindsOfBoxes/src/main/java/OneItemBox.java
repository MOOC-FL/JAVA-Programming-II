/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author abdel
 */
public class OneItemBox extends Box {
    private Item singleItem;
    
    public OneItemBox() {
        super();
        this.singleItem = null;
    }
    
    @Override
    public void add(Item item) {
        if (item == null) {
            throw new IllegalArgumentException("Item cannot be null");
        }
        
        if (singleItem == null) {
            singleItem = item;
            super.add(item);
        }
        // Silently ignore if already has an item
    }
    
    @Override
    public boolean isInBox(Item item) {
        if (item == null) {
            return false; // or throw exception
        }
        return singleItem != null && singleItem.equals(item);
    }
    
    @Override
    public boolean canAdd(Item item) {
        if (item == null) {
            return false;
        }
        return singleItem == null;
    }
}