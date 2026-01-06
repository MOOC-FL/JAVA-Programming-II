## **Reference Types vs Value Types in Java**

Java has a **strict distinction** between reference types and value types (primitives). Here's the detailed comparison:

## **Fundamental Differences**

| Aspect | Reference Types in Java | Value Types in Java |
|--------|------------------------|---------------------|
| **What they store** | Memory address (reference) to object | Actual value |
| **Default value** | `null` | Zero/false (e.g., `0`, `0.0`, `false`) |
| **Memory location** | Object: Heap<br>Reference variable: Stack | Stack (for local vars)<br>Inside object: Heap (as part of object) |
| **Assignment** | Copies reference (points to same object) | Copies value (independent) |
| **Comparison with `==`** | Compares references (memory addresses) | Compares values |
| **Parameter passing** | Pass by value (reference value is copied) | Pass by value (actual value is copied) |
| **Types** | All classes, interfaces, arrays, enums | `byte`, `short`, `int`, `long`, `float`, `double`, `char`, `boolean` |
| **Wrapper classes** | `Integer`, `Double`, `Boolean`, etc. | None (primitives themselves) |
| **Can be `null`** | Yes | No |
| **Memory management** | Garbage collected | Automatic (stack pop) or part of object GC |
| **Size** | Reference: 4/8 bytes<br>Object: varies | Fixed (depends on type)<br>e.g., int: 4 bytes, double: 8 bytes |
| **Performance** | Slower (heap allocation, indirection) | Faster (direct stack access) |

## **Code Examples**

### **1. Assignment Behavior**
```java
// VALUE TYPES (primitives)
int x = 10;
int y = x;     // Copy of value (10)
y = 20;        // Only y changes
System.out.println(x);  // 10
System.out.println(y);  // 20

// REFERENCE TYPES
int[] arr1 = {1, 2, 3};
int[] arr2 = arr1;      // Copy of reference (same array)
arr2[0] = 100;          // Both see the change
System.out.println(arr1[0]);  // 100
System.out.println(arr2[0]);  // 100
```

### **2. Comparison with `==`**
```java
// VALUE TYPES
int a = 5;
int b = 5;
System.out.println(a == b);  // true (values equal)

// REFERENCE TYPES
String s1 = new String("hello");
String s2 = new String("hello");
System.out.println(s1 == s2);      // false (different objects)
System.out.println(s1.equals(s2)); // true (content equal)
```

### **3. Method Parameters**
```java
public class Test {
    // Value type parameter
    static void modifyPrimitive(int val) {
        val = 100;  // Local copy only
    }
    
    // Reference type parameter  
    static void modifyArray(int[] arr) {
        if (arr.length > 0) {
            arr[0] = 100;  // Modifies the shared object
        }
    }
    
    public static void main(String[] args) {
        int num = 5;
        modifyPrimitive(num);
        System.out.println(num);  // 5 (unchanged)
        
        int[] numbers = {1, 2, 3};
        modifyArray(numbers);
        System.out.println(numbers[0]);  // 100 (changed)
    }
}
```

## **Memory Diagrams**

### **Value Types in Memory**
```java
int x = 42;
int y = x;
```
```
Stack:
┌─────┐
│  x  │ → 42
│  y  │ → 42 (copy)
└─────┘
```

### **Reference Types in Memory**
```java
int[] a = {1, 2, 3};
int[] b = a;
```
```
Stack:           Heap:
┌─────┐         ┌─────────────┐
│  a  │ → 0x100 │ 0x100: [1, 2, 3]
│  b  │ → 0x100 └─────────────┘
└─────┘
```

## **Special Cases**

### **Autoboxing & Unboxing**
```java
// Value type to reference type (autoboxing)
Integer refInt = 42;  // Automatically boxes primitive int
int primInt = refInt; // Automatically unboxes

// But behavior differs!
Integer a = 100;
Integer b = 100;
System.out.println(a == b);  // true (cached values -128 to 127)

Integer c = 200;
Integer d = 200;
System.out.println(c == d);  // false (different objects)
```

### **Strings (Special Reference Type)**
```java
// String pool optimization
String s1 = "hello";        // Uses string pool
String s2 = "hello";        // Reuses from pool
String s3 = new String("hello");  // Creates new object

System.out.println(s1 == s2);  // true (same reference from pool)
System.out.println(s1 == s3);  // false (different objects)
```

### **Arrays (Reference Type)**
```java
// Multi-dimensional arrays
int[][] matrix = new int[3][4];  // Array of arrays (all reference types)
int[] row = matrix[0];           // Reference to first row
```

## **Performance Implications**

```java
// Value types are faster for simple operations
long start = System.nanoTime();
int sum = 0;
for (int i = 0; i < 1000000; i++) {
    sum += i;  // Direct stack operations
}
long duration = System.nanoTime() - start;

// Reference types have overhead
start = System.nanoTime();
Integer sumObj = 0;
for (int i = 0; i < 1000000; i++) {
    sumObj += i;  // Autoboxing creates new Integer objects!
}
duration = System.nanoTime() - start;
```

## **Best Practices**

1. **Use primitives** for performance-critical code
2. **Use wrapper classes** when you need nullability or use with generics
3. **Be aware of autoboxing overhead** in loops
4. **Use `.equals()`** for reference type content comparison
5. **Consider defensive copying** when sharing mutable objects
6. **Use `String` pool** for string literals when appropriate

## **Java 14+ Value Types (Preview)**

```java
// Records (simplified reference types)
record Point(int x, int y) {}  // Immutable by default

// Pattern matching (simplifies type checking)
if (obj instanceof String s) {
    System.out.println(s.length());
}

// Sealed classes (restricted inheritance)
sealed interface Shape permits Circle, Rectangle {}
```

**Key Takeaway**: Java's strict separation of reference and value types provides predictability but requires awareness of their different behaviors, especially regarding assignment, comparison, and performance characteristics.
