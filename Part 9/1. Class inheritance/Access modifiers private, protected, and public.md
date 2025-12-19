#### Access modifiers private, protected, and public
- If a method or variable has the access modifier `private`, it is visible only to the internal methods of that class. Subclasses will not see it, and a subclass has no direct means to access it. So, from the Engine class there is no way to directly access the variables identifier, manufacturer, and description, which are defined in the superclass Part. The programmer cannot access the variables of the superclass that have been defined with the access modifier private.
- A subclass sees everything that is defined with the `public` modifier in the superclass. If we want to define some variables or methods that are visible to the subclasses but invisible to everything else, we can use the access modifier `protected` to achieve this.
## Java Access Modifiers

Access modifiers control the **visibility** and **accessibility** of classes, methods, and variables in Java. Here's a clear breakdown:

## 1. **`private`** (Most Restrictive)
- **Accessible within the same class only**
- Not accessible by subclasses or other classes in the same package
- Best for encapsulation - hiding implementation details

```java
public class BankAccount {
    private double balance;  // Only accessible within BankAccount
    
    private void validateAmount(double amount) {
        // Only accessible within BankAccount
    }
}
```

## 2. **`protected`**
- **Accessible within the same package** AND **by subclasses (even in different packages)**
- Often used for inheritance hierarchies

```java
package animals;

public class Animal {
    protected String name;  // Accessible by subclasses
    
    protected void eat() {
        // Accessible in same package and by subclasses
    }
}

// In another package
package pets;
import animals.Animal;

public class Dog extends Animal {
    public void display() {
        System.out.println(name);  // OK - protected field accessible
        eat();                      // OK - protected method accessible
    }
}
```

## 3. **`public`** (Least Restrictive)
- **Accessible from anywhere** - any class in any package
- Used for API/interfaces that should be available to all

```java
public class Calculator {
    public int add(int a, int b) {  // Accessible from anywhere
        return a + b;
    }
}
```

## 4. **Default (Package-Private)**
- When **no modifier is specified**
- **Accessible only within the same package**
- Sometimes called "package-private"

```java
class PackageClass {  // No modifier - default access
    void packageMethod() {  // Accessible only within same package
        System.out.println("Package-private");
    }
}
```

## Access Levels Summary Table

| Modifier | Class | Package | Subclass (same pkg) | Subclass (diff pkg) | World (diff pkg) |
|----------|-------|---------|-------------------|-------------------|----------------|
| `private` | ✓ | ✗ | ✗ | ✗ | ✗ |
| `default` | ✓ | ✓ | ✓ | ✗ | ✗ |
| `protected` | ✓ | ✓ | ✓ | ✓ | ✗ |
| `public` | ✓ | ✓ | ✓ | ✓ | ✓ |

## Practical Usage Guidelines

### **`private`**
- Use for **implementation details** that shouldn't be exposed
- All fields should typically be `private` with getters/setters
- Internal helper methods

### **`protected`**
- Use when creating **framework or library classes** meant for extension
- Methods/fields that subclasses need to override/access
- Less common than `private` or `public`

### **`public`**
- Use for **API methods** that external code should use
- Constants (`public static final`)
- Main method

### **Default (Package-Private)**
- Use for **internal package implementation** 
- Classes/methods that shouldn't be used outside the package
- Useful for package-level organization

## Example Showing All Modifiers

```java
package com.example;

public class AccessExample {
    private String secret = "Private";      // Only in this class
    String packageVar = "Default";          // Only in com.example package
    protected String family = "Protected";  // Package + subclasses
    public String global = "Public";        // Everywhere
    
    private void privateMethod() { }
    void packageMethod() { }
    protected void protectedMethod() { }
    public void publicMethod() { }
}

// In another package
package com.other;
import com.example.AccessExample;

public class OtherClass {
    public void test() {
        AccessExample obj = new AccessExample();
        // obj.secret;        // ERROR - private
        // obj.packageVar;    // ERROR - default (different package)
        // obj.family;        // ERROR - protected (not subclass, diff package)
        obj.global = "OK";    // OK - public
        obj.publicMethod();   // OK - public
    }
}

// Subclass in different package
class SubClass extends AccessExample {
    public void test() {
        // this.secret;       // ERROR - private
        // this.packageVar;   // ERROR - default (different package)
        this.family = "OK";   // OK - protected (accessible to subclass)
        this.global = "OK";   // OK - public
        this.protectedMethod(); // OK
        this.publicMethod();    // OK
    }
}
```

## Key Points to Remember

1. **For classes**: Only `public` or default (top-level classes)
   - `private` and `protected` can only be used for **nested/inner classes**

2. **For constructors**: Same rules apply
   ```java
   public class MyClass {
       private MyClass() {}  // Private constructor for singleton
       public MyClass(int x) {}  // Public constructor
   }
   ```

3. **Order doesn't matter**: `public static final` vs `static public final`

4. **Interface members**: Are implicitly `public` (cannot use `private` or `protected`)

5. **Best Practice**: Use the **most restrictive** access level that makes sense for your design to maintain encapsulation and reduce coupling.

