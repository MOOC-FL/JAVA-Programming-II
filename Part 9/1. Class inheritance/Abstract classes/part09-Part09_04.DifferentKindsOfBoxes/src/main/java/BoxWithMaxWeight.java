/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author abdel
 */
import java.util.ArrayList;
import java.util.List;

public class BoxWithMaxWeight extends Box {
    private int maxWeight;
    private int currentWeight;
    
    public BoxWithMaxWeight(int capacity) {
        super(); // Initialize parent's item list
        this.maxWeight = capacity;
        this.currentWeight = 0;
    }
    
    @Override
    public void add(Item item) {
        // Check if we can add the item based on weight
        if (canAdd(item)) {
            super.add(item); // Call parent's add method
            currentWeight += item.getWeight();
        }
        // If weight would be exceeded, do nothing (item is not added)
    }
    
    @Override
    public boolean canAdd(Item item) {
        // Check if adding this item would exceed max weight
        return currentWeight + item.getWeight() <= maxWeight;
    }
    
    // Helper method to get current weight (optional)
    public int getCurrentWeight() {
        return currentWeight;
    }
    
    // Helper method to get remaining capacity (optional)
    public int getRemainingCapacity() {
        return maxWeight - currentWeight;
    }
}
