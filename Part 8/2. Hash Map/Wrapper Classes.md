## **Primitive Wrapper Classes**

Java provides **object equivalents** for each primitive type. These are called **wrapper classes**.

| Primitive | Wrapper Class | Inheritance Chain                     |
|-----------|---------------|--------------------------------------|
| `byte`    | `Byte`        | `Number` → `Object`                  |
| `short`   | `Short`       | `Number` → `Object`                  |
| `int`     | `Integer`     | `Number` → `Object`                  |
| `long`    | `Long`        | `Number` → `Object`                  |
| `float`   | `Float`       | `Number` → `Object`                  |
| `double`  | `Double`      | `Number` → `Object`                  |
| `char`    | `Character`   | `Object` (directly)                  |
| `boolean` | `Boolean`     | `Object` (directly)                  |

---

## **Why Wrapper Classes Exist?**

### **1. Collections & Generics Requirement**
Collections (ArrayList, HashMap, etc.) and generics **only work with objects**, not primitives:

```java
// WRONG - Can't use primitives in collections
// ArrayList<int> numbers = new ArrayList<>();

// CORRECT - Must use wrapper classes
ArrayList<Integer> numbers = new ArrayList<>();
numbers.add(10);      // Autoboxing: int → Integer
numbers.add(20);
```

### **2. Nullability**
Wrapper classes can be `null`, primitives cannot:

```java
int score;           // Always has a value (default 0)
Integer highScore;   // Can be null (no score yet)

highScore = null;    // Valid - represents "no score"
// score = null;     // COMPILE ERROR!
```

### **3. Utility Methods**
Wrapper classes provide useful methods:

```java
// Integer utility methods
int max = Integer.MAX_VALUE;     // 2147483647
int min = Integer.MIN_VALUE;     // -2147483648
String binary = Integer.toBinaryString(10);  // "1010"
int parsed = Integer.parseInt("123");        // 123

// Character utility methods
boolean isDigit = Character.isDigit('5');    // true
boolean isLetter = Character.isLetter('A');  // true
char lower = Character.toLowerCase('Z');     // 'z'
```

---

## **Autoboxing & Unboxing (Java 5+)**

Java automatically converts between primitives and their wrapper classes:

### **Autoboxing**: Primitive → Wrapper
```java
Integer num = 42;      // Automatically boxes int to Integer
// Equivalent to: Integer num = Integer.valueOf(42);

List<Integer> list = new ArrayList<>();
list.add(100);         // Autoboxing: int → Integer
```

### **Unboxing**: Wrapper → Primitive
```java
Integer wrapper = 50;
int primitive = wrapper;  // Unboxing: Integer → int
// Equivalent to: int primitive = wrapper.intValue();

int sum = wrapper + 10;   // Unboxing happens automatically
```

---

## **Performance Considerations**

**Primitives are faster and use less memory:**

```java
// Memory usage comparison
int primitive = 10;              // 4 bytes
Integer wrapper = 10;           // ~16-24 bytes (object overhead)

// Performance test
long start = System.nanoTime();
int sum = 0;
for (int i = 0; i < 1_000_000; i++) {
    sum += i;                    // Pure primitive arithmetic
}
// Much faster than using Integer objects
```

---

## **Important Details & Pitfalls**

### **1. Caching (Integer Pool)**
Wrapper classes cache commonly used values:

```java
Integer a = 127;
Integer b = 127;
System.out.println(a == b);      // true (cached values)

Integer c = 128;
Integer d = 128;
System.out.println(c == d);      // false (outside cache range)
System.out.println(c.equals(d)); // true (always use .equals()!)
```

**Cache ranges:**
- `Byte`: all values (-128 to 127)
- `Short`, `Integer`, `Long`: -128 to 127
- `Character`: 0 to 127
- `Boolean`: `true` and `false` (two instances)

### **2. Null Pointer Danger**
```java
Integer count = null;
int value = count;      // Runtime: NullPointerException!
// Solution: Always check for null before unboxing
if (count != null) {
    value = count;
}
```

### **3. Comparison Issues**
```java
Integer x = 1000;
Integer y = 1000;

// WRONG approach
if (x == y) {           // Compares references, not values!
    // Might not work as expected
}

// CORRECT approach
if (x.equals(y)) {      // Compares values
    // This works correctly
}
if (x.intValue() == y.intValue()) {  // Also correct
    // Explicit unboxing
}
```

---

## **When to Use Which?**

### **Use Primitives when:**
- Performance is critical
- Memory usage matters
- You need default values (0, false)
- Working with large arrays
- Simple arithmetic operations

```java
// Good for primitives
int[] scores = new int[10000];  // Efficient array
double total = 0.0;              // Simple accumulation
boolean isActive = true;         // Simple flag
```

### **Use Wrapper Classes when:**
- Working with collections
- Need nullability
- Using generics
- Need utility methods
- Working with APIs that require objects

```java
// Good for wrappers
List<Integer> studentIds = new ArrayList<>();  // Collection
Integer age = null;  // Age might be unknown
Map<String, Integer> wordCounts = new HashMap<>();  // Generic Map
```

---

## **Common Use Cases**

```java
// 1. Collections (must use wrappers)
Set<Integer> uniqueNumbers = new HashSet<>();
uniqueNumbers.add(42);

// 2. JSON/API responses (often have null values)
public class User {
    private Integer age;      // Can be null if not provided
    private String name;
}

// 3. Database entities
@Entity
public class Product {
    @Id
    private Long id;          // Wrapper type
    private Double price;     // Could be null
    private Integer quantity; // Could be null
}

// 4. Optional values
Optional<Integer> optionalScore = Optional.ofNullable(getScore());
```

## **Best Practice Summary**
1. **Default to primitives** for better performance
2. **Use wrappers** when null values are meaningful
3. **Always use `.equals()`** for comparing wrapper objects
4. **Be cautious with autoboxing** in performance-critical loops
5. **Check for null** before unboxing to avoid NPE
