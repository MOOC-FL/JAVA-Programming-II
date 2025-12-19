Here's an example demonstrating how to use `this()` and `super()` in constructors:

```java
// Superclass with object variable and constructor chaining using 'this'
class Superclass {
    private int value;  // Object variable
    
    // No-argument constructor - calls the parameterized constructor using 'this'
    public Superclass() {
        this(0);  // Call parameterized constructor with default value
        System.out.println("Superclass default constructor");
    }
    
    // Parameterized constructor
    public Superclass(int value) {
        this.value = value;
        System.out.println("Superclass parameterized constructor with value: " + value);
    }
    
    public int getValue() {
        return value;
    }
}

// Subclass that extends Superclass
class Subclass extends Superclass {
    // Parameterized constructor - calls superclass constructor using 'super'
    public Subclass(int value) {
        super(value);  // Call parameterized constructor of Superclass
        System.out.println("Subclass constructor with value: " + value);
    }
}

// Main class to demonstrate
public class ConstructorChainDemo {
    public static void main(String[] args) {
        System.out.println("=== Creating Subclass object ===");
        Subclass sub = new Subclass(42);
        System.out.println("Value stored in Superclass: " + sub.getValue());
        
        System.out.println("\n=== Creating Superclass objects ===");
        Superclass sup1 = new Superclass();
        System.out.println("Value: " + sup1.getValue());
        
        Superclass sup2 = new Superclass(100);
        System.out.println("Value: " + sup2.getValue());
    }
}
```

## Output:
```
=== Creating Subclass object ===
Superclass parameterized constructor with value: 42
Subclass constructor with value: 42
Value stored in Superclass: 42

=== Creating Superclass objects ===
Superclass parameterized constructor with value: 0
Superclass default constructor
Value: 0
Superclass parameterized constructor with value: 100
Value: 100
```

## Key Points in this Example:

### **In Superclass:**
1. **Object variable**: `private int value`
2. **Constructor chaining with `this()`**:
   - The no-argument constructor calls `this(0)` as its first line
   - This invokes the parameterized constructor with default value 0
   - This ensures all constructors initialize the `value` field consistently

### **In Subclass:**
1. **No object variables**: As specified in the requirements
2. **Constructor with `super()`**:
   - The parameterized constructor calls `super(value)` as its first line
   - This invokes the parameterized constructor of Superclass
   - Passes the value to initialize the superclass's `value` field

## Constructor Execution Flow:
When creating `new Subclass(42)`:
1. `Subclass(int value)` constructor starts
2. First line `super(42)` calls Superclass's parameterized constructor
3. Superclass's constructor sets `this.value = 42` and prints message
4. Control returns to Subclass constructor
5. Subclass constructor continues and prints its message

## Rules Demonstrated:

1. **`this()` must be first statement in constructor**:
   ```java
   public Superclass() {
       this(0);  // OK - first statement
       System.out.println("Superclass default constructor");
   }
   ```

2. **`super()` must be first statement in constructor**:
   ```java
   public Subclass(int value) {
       super(value);  // OK - first statement
       System.out.println("Subclass constructor with value: " + value);
   }
   ```

3. **Cannot use both `this()` and `super()`** in same constructor

4. **Default `super()` call** (if not specified):
   ```java
   // If we didn't specify super(value), Java would add:
   // super();  // Calling Superclass's no-arg constructor
   ```

## Alternate Example Showing Multiple Constructors:

```java
class Superclass {
    private int value;
    
    public Superclass() {
        this(0);  // Chain to parameterized constructor
    }
    
    public Superclass(int value) {
        this.value = value;
    }
}

class Subclass extends Superclass {
    private String name;
    
    public Subclass() {
        this("Default");  // Chain to other constructor
    }
    
    public Subclass(String name) {
        this(name, 0);  // Chain to third constructor
    }
    
    public Subclass(String name, int value) {
        super(value);  // Call superclass constructor
        this.name = name;
    }
}
```

## Summary of Constructor Chaining Rules:

1. **Every constructor must call a superclass constructor** (explicit `super()` or implicit)
2. **Every constructor can call another constructor in the same class** using `this()`
3. **The chain must eventually end with a `super()` call**
4. **The call (`this()` or `super()`) must be the first statement**
5. **Cannot have both `this()` and `super()` in the same constructor**
