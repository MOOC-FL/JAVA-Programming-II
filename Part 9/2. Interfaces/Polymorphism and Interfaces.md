#### Interfaces and polymorphism
Polymorphism with interfaces allows objects of different types to be treated as instances of a common interface type. This enables code to work with objects in a generic way, without needing to know their specific concrete implementations.
> **Reducing the dependencies between classes**
> 
>  Using interfaces in programming enables reducing dependencies between classes. In the previous example the Packer does not depend on the classes that implement the Packable interface. Instead, it just depends on the interface. This makes it possible to add new classes that implement the interface without changing the Packer class. What is more, adding new Packable classes doesn't affect the classes that use the Packer class.

#### Core Concept

When a class implements an interface, it promises to provide specific behaviors. Through polymorphism, we can:
1. Use interface references to refer to objects of implementing classes
2. Call methods defined in the interface without knowing the concrete type
3. Change behavior at runtime by substituting different implementations

#### Example 1: Payment Processing System

```java
// Interface defining the contract
interface PaymentMethod {
    void processPayment(double amount);
    String getProviderName();
}

// Different implementations
class CreditCardPayment implements PaymentMethod {
    private String cardNumber;
    
    public CreditCardPayment(String cardNumber) {
        this.cardNumber = cardNumber;
    }
    
    @Override
    public void processPayment(double amount) {
        System.out.println("Processing $" + amount + " via credit card ending in " 
                          + cardNumber.substring(cardNumber.length() - 4));
        // Credit card specific logic
    }
    
    @Override
    public String getProviderName() {
        return "Visa/MasterCard Network";
    }
}

class PayPalPayment implements PaymentMethod {
    private String email;
    
    public PayPalPayment(String email) {
        this.email = email;
    }
    
    @Override
    public void processPayment(double amount) {
        System.out.println("Processing $" + amount + " via PayPal account: " + email);
        // PayPal specific logic
    }
    
    @Override
    public String getProviderName() {
        return "PayPal";
    }
}

class CryptoPayment implements PaymentMethod {
    private String walletAddress;
    
    public CryptoPayment(String walletAddress) {
        this.walletAddress = walletAddress;
    }
    
    @Override
    public void processPayment(double amount) {
        System.out.println("Processing $" + amount + " worth of cryptocurrency to wallet: " 
                          + walletAddress.substring(0, 8) + "...");
        // Cryptocurrency specific logic
    }
    
    @Override
    public String getProviderName() {
        return "Blockchain Network";
    }
}

// Client code using polymorphism
public class PaymentProcessor {
    public void processCheckout(PaymentMethod payment, double amount) {
        // Polymorphism in action: We don't know the concrete type
        System.out.println("Using " + payment.getProviderName());
        payment.processPayment(amount);
        System.out.println("Payment completed successfully!\n");
    }
    
    public static void main(String[] args) {
        PaymentProcessor processor = new PaymentProcessor();
        
        // Same interface type, different implementations
        PaymentMethod creditCard = new CreditCardPayment("4111111111111111");
        PaymentMethod paypal = new PayPalPayment("user@example.com");
        PaymentMethod crypto = new CryptoPayment("0x742d35Cc6634C0532925a3b844Bc9e");
        
        // Polymorphic behavior - same method call, different implementations
        processor.processCheckout(creditCard, 100.0);
        processor.processCheckout(paypal, 50.0);
        processor.processCheckout(crypto, 75.0);
        
        // Runtime polymorphism with collections
        List<PaymentMethod> payments = Arrays.asList(creditCard, paypal, crypto);
        System.out.println("Processing batch payments:");
        for (PaymentMethod payment : payments) {
            payment.processPayment(25.0);  // Same method, different behaviors
        }
    }
}
```

#### Example 2: Shape Drawing System

```java
// Interface
interface Drawable {
    void draw();
    double calculateArea();
}

// Multiple implementations
class Circle implements Drawable {
    private double radius;
    
    public Circle(double radius) {
        this.radius = radius;
    }
    
    @Override
    public void draw() {
        System.out.println("Drawing a circle with radius " + radius);
        // Circle-specific drawing logic
    }
    
    @Override
    public double calculateArea() {
        return Math.PI * radius * radius;
    }
}

class Rectangle implements Drawable {
    private double width;
    private double height;
    
    public Rectangle(double width, double height) {
        this.width = width;
        this.height = height;
    }
    
    @Override
    public void draw() {
        System.out.println("Drawing a rectangle " + width + "x" + height);
        // Rectangle-specific drawing logic
    }
    
    @Override
    public double calculateArea() {
        return width * height;
    }
}

class Triangle implements Drawable {
    private double base;
    private double height;
    
    public Triangle(double base, double height) {
        this.base = base;
        this.height = height;
    }
    
    @Override
    public void draw() {
        System.out.println("Drawing a triangle with base " + base + " and height " + height);
        // Triangle-specific drawing logic
    }
    
    @Override
    public double calculateArea() {
        return 0.5 * base * height;
    }
}

// Using polymorphism
public class DrawingApp {
    public static void renderScene(List<Drawable> shapes) {
        double totalArea = 0;
        
        for (Drawable shape : shapes) {
            shape.draw();  // Polymorphic method call
            double area = shape.calculateArea();  // Another polymorphic call
            totalArea += area;
            System.out.println("Area: " + area + "\n");
        }
        
        System.out.println("Total area of all shapes: " + totalArea);
    }
    
    public static void main(String[] args) {
        List<Drawable> scene = new ArrayList<>();
        scene.add(new Circle(5.0));
        scene.add(new Rectangle(4.0, 6.0));
        scene.add(new Triangle(3.0, 7.0));
        scene.add(new Circle(2.5));
        
        renderScene(scene);  // All different types treated uniformly
    }
}
```

#### Key Benefits of Polymorphism with Interfaces

1. **Loose Coupling**: Code depends on interfaces, not concrete implementations
2. **Extensibility**: Easy to add new implementations without modifying existing code
3. **Runtime Flexibility**: Can change behavior at runtime by swapping implementations
4. **Testability**: Easy to mock implementations for testing
5. **Code Reusability**: Methods written against interfaces work with any implementation

#### Real-World Patterns

```java
// Strategy Pattern Example
interface SortingAlgorithm {
    void sort(int[] array);
}

class QuickSort implements SortingAlgorithm {
    @Override
    public void sort(int[] array) { /* Quick sort implementation */ }
}

class MergeSort implements SortingAlgorithm {
    @Override
    public void sort(int[] array) { /* Merge sort implementation */ }
}

class Sorter {
    private SortingAlgorithm algorithm;  // Polymorphic field
    
    public void setAlgorithm(SortingAlgorithm algorithm) {
        this.algorithm = algorithm;  // Can assign any implementation
    }
    
    public void performSort(int[] data) {
        algorithm.sort(data);  // Polymorphic method call
    }
}

// Factory Pattern Example
interface Logger {
    void log(String message);
}

class FileLogger implements Logger {
    @Override
    public void log(String message) { /* Write to file */ }
}

class DatabaseLogger implements Logger {
    @Override
    public void log(String message) { /* Write to database */ }
}

class LoggerFactory {
    public static Logger getLogger(String type) {
        switch(type.toLowerCase()) {
            case "file": return new FileLogger();
            case "database": return new DatabaseLogger();
            default: throw new IllegalArgumentException("Unknown logger type");
        }
    }
}
```

#### How Polymorphism Works with Interfaces

1. **Compile-Time**: The compiler checks that the method exists in the interface
2. **Run-Time**: The JVM determines which concrete implementation to execute based on the actual object type
3. **Method Dispatch**: Uses virtual method table (vtable) to resolve the correct method implementation

This combination of interfaces and polymorphism is fundamental to writing flexible, maintainable, and scalable object-oriented systems.
