# Interface and Implementation in Programming

The **interface-implementation pattern** is a fundamental concept in object-oriented programming that separates **what** a component does from **how** it does it.

## **Core Concept**

### **Interface** - The "What"
- Defines a contract/specification
- Declares capabilities without implementation
- Answers: **What** can be done?

### **Implementation** - The "How"
- Provides concrete code that fulfills the contract
- Contains the actual logic and data
- Answers: **How** is it done?

## **Real-World Analogy**

Think of a **power outlet (interface)** vs. **electrical wiring (implementation)**:
- Interface: 3 holes, 120V AC, specific shape
- Implementation: Different wiring behind walls (copper vs. aluminum, different circuit breakers, etc.)

## **Code Examples**

### **Simple Example**
```java
// INTERFACE - What can be done
public interface PaymentProcessor {
    boolean processPayment(double amount);
    String getProcessorName();
}

// IMPLEMENTATION #1 - How it's done
public class CreditCardProcessor implements PaymentProcessor {
    @Override
    public boolean processPayment(double amount) {
        // Connect to bank, validate card, charge amount
        System.out.println("Processing $" + amount + " via credit card");
        return true;
    }
    
    @Override
    public String getProcessorName() {
        return "Credit Card Processor";
    }
}

// IMPLEMENTATION #2 - Different implementation
public class PayPalProcessor implements PaymentProcessor {
    @Override
    public boolean processPayment(double amount) {
        // Call PayPal API, handle OAuth, process payment
        System.out.println("Processing $" + amount + " via PayPal");
        return true;
    }
    
    @Override
    public String getProcessorName() {
        return "PayPal Processor";
    }
}
```

## **Benefits of Separation**

### **1. Abstraction**
```java
// Client code only knows the interface
public class ShoppingCart {
    private PaymentProcessor processor;
    
    public ShoppingCart(PaymentProcessor processor) {
        this.processor = processor;  // Works with ANY implementation
    }
    
    public void checkout(double total) {
        processor.processPayment(total);
    }
}

// Usage - can switch implementations easily
ShoppingCart cart1 = new ShoppingCart(new CreditCardProcessor());
ShoppingCart cart2 = new ShoppingCart(new PayPalProcessor());
```

### **2. Polymorphism**
```java
// Single interface, multiple implementations
PaymentProcessor[] processors = {
    new CreditCardProcessor(),
    new PayPalProcessor(),
    new CryptocurrencyProcessor()  // New implementation added later
};

for (PaymentProcessor p : processors) {
    p.processPayment(100.0);  // Same call, different behaviors
}
```

### **3. Testability**
```java
// Mock implementation for testing
public class MockPaymentProcessor implements PaymentProcessor {
    private boolean shouldSucceed;
    
    public MockPaymentProcessor(boolean shouldSucceed) {
        this.shouldSucceed = shouldSucceed;
    }
    
    @Override
    public boolean processPayment(double amount) {
        return shouldSucceed;  // No real payment processing
    }
    
    @Override
    public String getProcessorName() {
        return "Mock Processor";
    }
}

// Test with mock
@Test
public void testPaymentSuccess() {
    PaymentProcessor mock = new MockPaymentProcessor(true);
    ShoppingCart cart = new ShoppingCart(mock);
    // Test without hitting real payment systems
}
```

## **Design Patterns Using Interface-Implementation**

### **Strategy Pattern**
```java
// Interface for sorting strategy
public interface SortStrategy {
    void sort(int[] array);
}

// Different implementations
public class BubbleSort implements SortStrategy {
    @Override
    public void sort(int[] array) {
        // Bubble sort implementation
    }
}

public class QuickSort implements SortStrategy {
    @Override
    public void sort(int[] array) {
        // Quick sort implementation
    }
}

// Context class uses strategy
public class Sorter {
    private SortStrategy strategy;
    
    public void setStrategy(SortStrategy strategy) {
        this.strategy = strategy;
    }
    
    public void performSort(int[] array) {
        strategy.sort(array);  // Uses whichever implementation is set
    }
}
```

### **Factory Pattern**
```java
public interface Vehicle {
    void drive();
}

public class Car implements Vehicle {
    @Override
    public void drive() {
        System.out.println("Driving a car");
    }
}

public class Bike implements Vehicle {
    @Override
    public void drive() {
        System.out.println("Riding a bike");
    }
}

public class VehicleFactory {
    public Vehicle createVehicle(String type) {
        switch(type.toLowerCase()) {
            case "car": return new Car();
            case "bike": return new Bike();
            default: throw new IllegalArgumentException("Unknown vehicle type");
        }
    }
}
```

## **Dependency Injection**
```java
// Interface
public interface DataRepository {
    User findUserById(int id);
    void saveUser(User user);
}

// Implementation
public class DatabaseRepository implements DataRepository {
    // Actual database connection and queries
    @Override
    public User findUserById(int id) {
        // SQL query to database
        return queryDatabase("SELECT * FROM users WHERE id = " + id);
    }
}

public class UserService {
    // Depends on interface, not concrete implementation
    private DataRepository repository;
    
    // Implementation injected via constructor
    public UserService(DataRepository repository) {
        this.repository = repository;
    }
    
    public User getUser(int id) {
        return repository.findUserById(id);
    }
}
```

## **Interface Segregation Principle (SOLID)**
Better to have many specific interfaces than one large interface:

```java
// BAD - One fat interface
public interface Worker {
    void work();
    void eat();
    void sleep();
    void code();
    void design();
    void test();
}

// GOOD - Segregated interfaces
public interface Workable {
    void work();
}

public interface Eatable {
    void eat();
}

public interface Sleepable {
    void sleep();
}

public interface Programmer extends Workable {
    void code();
    void test();
}

public interface Designer extends Workable {
    void design();
    void prototype();
}

// Implement only what's needed
public class SoftwareEngineer implements Programmer, Eatable, Sleepable {
    @Override
    public void work() { /* ... */ }
    @Override
    public void code() { /* ... */ }
    @Override
    public void test() { /* ... */ }
    @Override
    public void eat() { /* ... */ }
    @Override 
    public void sleep() { /* ... */ }
}
```

## **Practical Example: Plugin Architecture**

```java
// Interface for plugins
public interface Plugin {
    String getName();
    void initialize();
    void execute();
    void cleanup();
}

// Different plugin implementations
public class LoggerPlugin implements Plugin {
    @Override
    public String getName() { return "Logger"; }
    
    @Override
    public void initialize() {
        System.out.println("Initializing Logger Plugin");
    }
    
    @Override
    public void execute() {
        System.out.println("Logging application events");
    }
    
    @Override
    public void cleanup() {
        System.out.println("Cleaning up Logger Plugin");
    }
}

public class AnalyticsPlugin implements Plugin {
    @Override
    public String getName() { return "Analytics"; }
    
    @Override
    public void initialize() {
        System.out.println("Initializing Analytics Plugin");
    }
    
    @Override
    public void execute() {
        System.out.println("Collecting analytics data");
    }
    
    @Override
    public void cleanup() {
        System.out.println("Cleaning up Analytics Plugin");
    }
}

// Plugin manager works with interface
public class PluginManager {
    private List<Plugin> plugins = new ArrayList<>();
    
    public void registerPlugin(Plugin plugin) {
        plugins.add(plugin);
    }
    
    public void runAllPlugins() {
        for (Plugin plugin : plugins) {
            plugin.initialize();
            plugin.execute();
            plugin.cleanup();
        }
    }
}
```

## **When to Use Interface-Implementation Pattern**

1. **Multiple implementations** - When you need different ways to do the same thing
2. **Testing** - To create mocks and stubs
3. **Future-proofing** - When requirements might change
4. **Team collaboration** - Different teams can work on interfaces and implementations separately
5. **Framework development** - Allow users to provide their own implementations
6. **Dependency injection** - To decouple components
7. **Plugin systems** - To allow extensibility

## **Key Principles**

1. **Program to an interface, not an implementation**
2. **Depend on abstractions, not concretions**
3. **A class should not be forced to implement interfaces it doesn't use**
4. **Clients should not depend on methods they don't use**

## **Common Pitfalls**

1. **Interface pollution** - Too many methods in one interface
2. **Empty implementations** - Implementing interface methods with empty bodies
3. **Leaky abstractions** - Implementation details leaking through interface
4. **Over-engineering** - Creating interfaces for everything, even when unnecessary

**Remember**: Use interfaces when you need abstraction and flexibility. For simple cases with single implementations, a concrete class might be sufficient. The goal is to balance flexibility with simplicity.
