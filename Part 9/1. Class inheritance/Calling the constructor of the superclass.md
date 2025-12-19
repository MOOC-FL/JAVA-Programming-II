#### Calling the constructor of the superclass
- You use the keyword `super` to call the constructor of the superclass. The call receives as parameters the types of values that the superclass constructor requires. If there are multiple constructors in the superclass, the parameters of the super call dictate which of them is used.
- When the constructor (of the subclass) is called, the variables defined in the superclass are initialized. The events that occur during the constructor call are practically identical to what happens with a normal constructor call. If the superclass doesn't provide a non-parameterized constructor, there must always be an explicit call to the constructor of the superclass in the constructors of the subclass.
- We demonstrate in the example below how to call `this` and `super`.
- The class `Superclass` includes an object variable and two constructors. One of them calls the other `constructor` with the this keyword.
- The class `Subclass` includes a parameterized constructor, but it has no object variables. The constructor of `Subclass` calls the parameterized constructor of the S`uperclass`.
```java
public class Superclass {

    private String objectVariable;

    public Superclass() {
        this("Example");
    }

    public Superclass(String value) {
        this.objectVariable = value;
    }

    public String toString() {
        return this.objectVariable;
    }
}
```
```java
public class Subclass extends Superclass {

    public Subclass() {
        super("Subclass");
    }
}
```
```java
Superclass sup = new Superclass();
Subclass sub = new Subclass();

System.out.println(sup);
System.out.println(sub);
```
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
```text
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
