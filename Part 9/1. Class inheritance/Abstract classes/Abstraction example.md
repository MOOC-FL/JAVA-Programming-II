# Complete Box System Example with Detailed Explanation

Let me provide you with a complete, working example of all the box implementations with detailed explanations.

## Project Structure Overview

We're creating a system of different box types that store items, each with unique behaviors:
1. **Item** - The object to be stored
2. **Box** - Abstract base class or interface
3. **BoxWithMaxWeight** - Box with weight capacity limit
4. **OneItemBox** - Box that holds exactly one item (once set, never changes)
5. **MisplacingBox** - Box that "loses" items (always adds, never finds)

## Complete Code Implementation

### 1. Item.java
```java
import java.util.Objects;

/**
 * Represents an item with a name and weight.
 * The equals() and hashCode() methods consider only the name field,
 * not the weight, as specified.
 */
public class Item {
    private String name;
    private int weight;
    
    public Item(String name) {
        this(name, 0);
    }
    
    public Item(String name, int weight) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Item name cannot be null or empty");
        }
        if (weight < 0) {
            throw new IllegalArgumentException("Weight cannot be negative");
        }
        this.name = name;
        this.weight = weight;
    }
    
    public String getName() {
        return name;
    }
    
    public int getWeight() {
        return weight;
    }
    
    public void setWeight(int weight) {
        if (weight < 0) {
            throw new IllegalArgumentException("Weight cannot be negative");
        }
        this.weight = weight;
    }
    
    /**
     * equals() considers only the name, not weight.
     * Two items with the same name but different weights are considered equal.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Item other = (Item) obj;
        return Objects.equals(this.name, other.name);
        // Weight is intentionally NOT compared
    }
    
    /**
     * hashCode() uses only the name to maintain consistency with equals().
     */
    @Override
    public int hashCode() {
        return Objects.hash(name);
        // Weight is intentionally NOT included
    }
    
    @Override
    public String toString() {
        return name + " (" + weight + " kg)";
    }
}
```

**Explanation:**
- `equals()` and `hashCode()` only use the `name` field
- This means two `Item` objects with the same name are considered equal, regardless of weight
- This is important for collections like `ArrayList.contains()` to work correctly

### 2. Box.java (Abstract Base Class)
```java
import java.util.ArrayList;
import java.util.List;

/**
 * Abstract base class for all box types.
 * Defines the contract for box implementations.
 */
public abstract class Box {
    // Protected list to store items (accessible to subclasses)
    protected List<Item> items;
    
    public Box() {
        this.items = new ArrayList<>();
    }
    
    /**
     * Adds an item to the box.
     * Implementation details vary by subclass.
     */
    public abstract void add(Item item);
    
    /**
     * Checks if an item is in the box.
     */
    public boolean isInBox(Item item) {
        return items.contains(item);
    }
    
    /**
     * Checks if an item can be added to the box.
     * Default implementation always returns true.
     */
    public boolean canAdd(Item item) {
        return true;
    }
    
    /**
     * Adds multiple items at once.
     * Uses the add(Item) method, respecting each box's rules.
     */
    public void add(List<Item> itemsToAdd) {
        for (Item item : itemsToAdd) {
            add(item);
        }
    }
    
    /**
     * Gets all items in the box (copy for encapsulation).
     */
    public List<Item> getItems() {
        return new ArrayList<>(items);
    }
    
    /**
     * Gets the number of items in the box.
     */
    public int getItemCount() {
        return items.size();
    }
    
    @Override
    public String toString() {
        return this.getClass().getSimpleName() + " with " + items.size() + " items";
    }
}
```

**Explanation:**
- Abstract class providing common functionality
- `items` list is protected so subclasses can access it
- `add(Item)` is abstract - each box type implements its own rules
- `isInBox()` uses the list's `contains()` method, which relies on `Item.equals()`
- `canAdd()` default implementation allows all items

### 3. BoxWithMaxWeight.java
```java
/**
 * A box with a maximum weight capacity.
 * Items can only be added if they don't exceed the capacity.
 */
public class BoxWithMaxWeight extends Box {
    private int maxWeight;
    private int currentWeight;
    
    public BoxWithMaxWeight(int capacity) {
        super(); // Initialize parent's items list
        if (capacity < 0) {
            throw new IllegalArgumentException("Capacity cannot be negative");
        }
        this.maxWeight = capacity;
        this.currentWeight = 0;
    }
    
    @Override
    public void add(Item item) {
        if (item == null) {
            throw new IllegalArgumentException("Item cannot be null");
        }
        
        if (canAdd(item)) {
            items.add(item); // Add to the list
            currentWeight += item.getWeight();
        }
        // If can't add, silently ignore (specified behavior)
    }
    
    @Override
    public boolean canAdd(Item item) {
        if (item == null) return false;
        return currentWeight + item.getWeight() <= maxWeight;
    }
    
    public int getCurrentWeight() {
        return currentWeight;
    }
    
    public int getRemainingCapacity() {
        return maxWeight - currentWeight;
    }
    
    public int getMaxWeight() {
        return maxWeight;
    }
    
    @Override
    public String toString() {
        return super.toString() + " [Weight: " + currentWeight + "/" + maxWeight + " kg]";
    }
}
```

**Explanation:**
- Tracks both current and maximum weight
- `add()` only succeeds if weight limit won't be exceeded
- `canAdd()` checks weight before allowing addition
- Maintains encapsulation by not exposing internal weight tracking

### 4. OneItemBox.java
```java
/**
 * A box that can hold exactly one item.
 * Once an item is added, it cannot be replaced or removed.
 */
public class OneItemBox extends Box {
    private boolean hasItem = false;
    
    public OneItemBox() {
        super();
    }
    
    @Override
    public void add(Item item) {
        if (item == null) {
            throw new IllegalArgumentException("Item cannot be null");
        }
        
        // Only add if box is empty
        if (!hasItem) {
            items.add(item);
            hasItem = true;
        }
        // If already has an item, do nothing (specified behavior)
    }
    
    @Override
    public boolean canAdd(Item item) {
        if (item == null) return false;
        return !hasItem; // Can add only if empty
    }
    
    /**
     * Override to provide faster lookup for single item.
     */
    @Override
    public boolean isInBox(Item item) {
        if (item == null || !hasItem) return false;
        // Check if the single item matches
        return items.get(0).equals(item);
    }
    
    public Item getItem() {
        if (!hasItem) return null;
        return items.get(0);
    }
    
    @Override
    public String toString() {
        if (!hasItem) {
            return "OneItemBox: empty";
        }
        return "OneItemBox: contains " + getItem();
    }
}
```

**Explanation:**
- Uses a `hasItem` flag to track state
- Only accepts the first item added
- Subsequent `add()` calls are ignored
- Weight is irrelevant (not checked)
- Optimized `isInBox()` for single item

### 5. MisplacingBox.java
```java
/**
 * A box that "loses" items.
 * Items can always be added, but can never be found.
 */
public class MisplacingBox extends Box {
    
    public MisplacingBox() {
        super();
    }
    
    @Override
    public void add(Item item) {
        if (item == null) {
            throw new IllegalArgumentException("Item cannot be null");
        }
        // Always add successfully
        items.add(item);
    }
    
    @Override
    public boolean isInBox(Item item) {
        // Always return false, even though items are stored
        return false;
    }
    
    @Override
    public boolean canAdd(Item item) {
        return item != null; // Can add any non-null item
    }
    
    /**
     * Special method to see what's "lost" in the box.
     * This violates the box's normal behavior but is useful for debugging.
     */
    public List<Item> peekAtLostItems() {
        return new ArrayList<>(items);
    }
    
    @Override
    public String toString() {
        return "MisplacingBox: " + items.size() + " items (all lost)";
    }
}
```

**Explanation:**
- Items are actually stored in the list
- But `isInBox()` always returns `false`
- This creates paradoxical behavior: items exist but can't be found
- Useful for testing or special scenarios

## Complete Test Program

```java
public class BoxSystemDemo {
    public static void main(String[] args) {
        System.out.println("=== BOX SYSTEM DEMONSTRATION ===\n");
        
        // Create some items
        Item book = new Item("Book", 2);
        Item laptop = new Item("Laptop", 3);
        Item pen = new Item("Pen", 1);
        Item phone = new Item("Phone", 1);
        
        // Test 1: Item equality (based on name only)
        System.out.println("1. ITEM EQUALITY TEST:");
        Item book2 = new Item("Book", 5); // Same name, different weight
        System.out.println("   book.equals(book2): " + book.equals(book2)); // true
        System.out.println("   book.hashCode() == book2.hashCode(): " + 
                          (book.hashCode() == book2.hashCode())); // true
        
        // Test 2: BoxWithMaxWeight
        System.out.println("\n2. BOX WITH MAX WEIGHT (capacity: 5 kg):");
        BoxWithMaxWeight weightBox = new BoxWithMaxWeight(5);
        System.out.println("   Adding book (2kg): " + weightBox.canAdd(book));
        weightBox.add(book);
        System.out.println("   Adding laptop (3kg): " + weightBox.canAdd(laptop));
        weightBox.add(laptop);
        System.out.println("   Adding pen (1kg): " + weightBox.canAdd(pen)); // false (total would be 6kg)
        weightBox.add(pen); // Won't be added
        System.out.println("   Current weight: " + weightBox.getCurrentWeight() + "/" + 
                          weightBox.getMaxWeight() + " kg");
        System.out.println("   Is book in box? " + weightBox.isInBox(book)); // true
        System.out.println("   Is pen in box? " + weightBox.isInBox(pen)); // false
        
        // Test 3: OneItemBox
        System.out.println("\n3. ONE ITEM BOX:");
        OneItemBox oneItemBox = new OneItemBox();
        System.out.println("   Adding book: " + oneItemBox.canAdd(book));
        oneItemBox.add(book);
        System.out.println("   Adding laptop: " + oneItemBox.canAdd(laptop)); // false
        oneItemBox.add(laptop); // Won't be added
        System.out.println("   Is book in box? " + oneItemBox.isInBox(book)); // true
        System.out.println("   Is laptop in box? " + oneItemBox.isInBox(laptop)); // false
        System.out.println("   Box contents: " + oneItemBox);
        
        // Test 4: MisplacingBox
        System.out.println("\n4. MISPLACING BOX:");
        MisplacingBox misplacingBox = new MisplacingBox();
        System.out.println("   Adding book: " + misplacingBox.canAdd(book));
        misplacingBox.add(book);
        System.out.println("   Adding laptop: " + misplacingBox.canAdd(laptop));
        misplacingBox.add(laptop);
        System.out.println("   Adding pen: " + misplacingBox.canAdd(pen));
        misplacingBox.add(pen);
        System.out.println("   Is book in box? " + misplacingBox.isInBox(book)); // false
        System.out.println("   Is laptop in box? " + misplacingBox.isInBox(laptop)); // false
        System.out.println("   Is pen in box? " + misplacingBox.isInBox(pen)); // false
        System.out.println("   Item count: " + misplacingBox.getItemCount()); // 3
        System.out.println("   Peek at lost items: " + misplacingBox.peekAtLostItems());
        
        // Test 5: Polymorphism demonstration
        System.out.println("\n5. POLYMORPHISM DEMONSTRATION:");
        Box[] boxes = new Box[3];
        boxes[0] = new BoxWithMaxWeight(10);
        boxes[1] = new OneItemBox();
        boxes[2] = new MisplacingBox();
        
        Item testItem = new Item("Test", 1);
        
        for (int i = 0; i < boxes.length; i++) {
            System.out.println("   Box " + (i+1) + " (" + boxes[i].getClass().getSimpleName() + "):");
            System.out.println("     Can add item? " + boxes[i].canAdd(testItem));
            boxes[i].add(testItem);
            System.out.println("     Is item in box? " + boxes[i].isInBox(testItem));
        }
        
        System.out.println("\n=== END OF DEMONSTRATION ===");
    }
}
```

## Key Object-Oriented Concepts Demonstrated

### 1. **Inheritance**
- All box types inherit from `Box` base class
- Common functionality in base class, specialized behavior in subclasses

### 2. **Polymorphism**
- Can treat all boxes as `Box` objects
- Each box type behaves differently when `add()` or `isInBox()` is called

### 3. **Encapsulation**
- Internal state (like `currentWeight` in `BoxWithMaxWeight`) is hidden
- Public methods provide controlled access

### 4. **Method Overriding**
- Each subclass overrides `add()`, `canAdd()`, and sometimes `isInBox()`
- Different implementations for different behaviors

### 5. **Abstract Classes**
- `Box` defines the interface that all boxes must implement
- Cannot instantiate `Box` directly

### 6. **equals() and hashCode() Contract**
- `Item` class implements proper `equals()` and `hashCode()`
- Only `name` is considered, not `weight`
- Essential for collections to work correctly

## Expected Output
```
=== BOX SYSTEM DEMONSTRATION ===

1. ITEM EQUALITY TEST:
   book.equals(book2): true
   book.hashCode() == book2.hashCode(): true

2. BOX WITH MAX WEIGHT (capacity: 5 kg):
   Adding book (2kg): true
   Adding laptop (3kg): true
   Adding pen (1kg): false
   Current weight: 5/5 kg
   Is book in box? true
   Is pen in box? false

3. ONE ITEM BOX:
   Adding book: true
   Adding laptop: false
   Is book in box? true
   Is laptop in box? false
   Box contents: OneItemBox: contains Book (2 kg)

4. MISPLACING BOX:
   Adding book: true
   Adding laptop: true
   Adding pen: true
   Is book in box? false
   Is laptop in box? false
   Is pen in box? false
   Item count: 3
   Peek at lost items: [Book (2 kg), Laptop (3 kg), Pen (1 kg)]

5. POLYMORPHISM DEMONSTRATION:
   Box 1 (BoxWithMaxWeight):
     Can add item? true
     Is item in box? true
   Box 2 (OneItemBox):
     Can add item? true
     Is item in box? true
   Box 3 (MisplacingBox):
     Can add item? true
     Is item in box? false

=== END OF DEMONSTRATION ===
```

This complete example demonstrates how object-oriented principles allow us to create a family of related classes with consistent interfaces but different behaviors. Each box type follows the Liskov Substitution Principle (can be used wherever a `Box` is expected) while providing unique functionality.
