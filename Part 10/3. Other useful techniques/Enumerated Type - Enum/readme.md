#### Enumerated Type - Enum
- If we know the possible values ​​of a variable in advance, we can use a class of type `enum`, ie, **enumerated type** to represent the values.
> **Enumerated types** are their own type in addition to being normal `classes` and `interfaces`. An enumerated type is defined by the keyword `enum`.
-  For example, the following `Suitenum` class defines four constant values: `DIAMOND`, `SPADE`, `CLUB` and `HEART`.
```java
public enum Suit {
    DIAMOND, SPADE, CLUB, HEART
}
```
- In its simplest form, `enum` lists the constant values ​​it declares, separated by a comma. **Enum** types, ie, constants, are conventionally written with capital letters.
> An `Enum` is (usually) written in its own file, much like a class or interface. In NetBeans, you can create an Enum by selecting new/other/java/java enum from project.
- The following is a `Card` class where the suit is represented by an enum:
```java
public enum Suit {
    DIAMOND, SPADE, CLUB, HEART
}
```
```java
public class Card {

    private int value;
    private Suit suit;

    public Card(int value, Suit suit) {
        this.value = value;
        this.suit = suit;
    }

    @Override
    public String toString() {
        return suit + " " + value;
    }

    public Suit getSuit() {
        return suit;
    }

    public int getValue() {
        return value;
    }
}
```
The card is used in the following way:
```java
Card first = new Card(10, Suit.HEART);

System.out.println(first);

if (first.getSuit() == Suit.SPADE) {
    System.out.println("is a spade");
} else {
    System.out.println("is not a spade");
}
```
```text
The output:

Sample output
HEARTS 10 is not a spade
```
#### Comparing Enums
- In the example above, two enums were compared with equal signs. How does this work?
- Each enum field gets a unique number code, and they can be compared using the equals sign. Just like other classes in Java, these values ​​also inherit the Object class and its equals method. The equals method Compares this numeric identifier in enum types too.
- The numeric identifier of an enum field value can be found with `ordinal()`. The method actually Returns an order number - if the enum value is presented first, its order number is 0. If its second, the order number is 1, and so on.
```java
public enum Suits {
    DIAMOND, CLUB, HEART, SPADE
}
```
```java
System.out.println(Suit.DIAMOND.ordinal());
System.out.println(Suit.HEART.ordinal());
```
```text
Sample output
0 3
```
#### Enumerated Type (Enum) in Java

An `enum` is a special data type that allows a variable to be a set of predefined constants. It's immutable and thread-safe.

#### Basic Definition

```java
// Define an enum
public enum Color {
    RED, GREEN, BLUE;
}

// Use it
Color myColor = Color.RED;
```
#### Enum in a Switch Statement

```java
public enum Day {
    MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY, SUNDAY;
}

Day today = Day.FRIDAY;

switch (today) {
    case MONDAY:
        System.out.println("Start of the week");
        break;
    case FRIDAY:
        System.out.println("Almost weekend!");
        break;
    case SATURDAY:
    case SUNDAY:
        System.out.println("Weekend!");
        break;
    default:
        System.out.println("Midweek");
}
```

Output: `Almost weekend!`


#### Enum with Fields & Methods

```java
public enum Season {
    SPRING(1, "March-May"),
    SUMMER(2, "June-August"),
    FALL(3, "September-November"),
    WINTER(4, "December-February");

    private final int order;
    private final String months;

    // Constructor — private by default
    Season(int order, String months) {
        this.order = order;
        this.months = months;
    }

    // Methods
    public int getOrder() {
        return order;
    }

    public String getMonths() {
        return months;
    }

    public String describe() {
        return this.name() + " (" + months + ")";
    }
}
```

**Usage:**
```java
Season s = Season.SUMMER;
System.out.println(s.getMonths());    // "June-August"
System.out.println(s.describe());     // "SUMMER (June-August)"
System.out.println(s.getOrder());     // 2
```
#### Common Enum Methods

| Method | Returns | Description |
|--------|---------|-------------|
| `name()` | `String` | Name of the constant (e.g., `"RED"`) |
| `ordinal()` | `int` | Position in enum (0-based) |
| `values()` | Array | All enum constants |
| `valueOf(String)` | Enum | Constant by name |
| `compareTo()` | `int` | Compare two enums |

```java
Color c = Color.GREEN;

c.name();           // "GREEN"
c.ordinal();        // 1 (RED is 0, GREEN is 1, BLUE is 2)

Color.values();     // [RED, GREEN, BLUE]
Color.valueOf("RED"); // Color.RED
```
#### Iterating Through an Enum

```java
public enum Status {
    PENDING, ACTIVE, INACTIVE, COMPLETED;
}

// Loop through all values
for (Status s : Status.values()) {
    System.out.println(s.name() + " - " + s.ordinal());
}
```

Output:
```
PENDING - 0
ACTIVE - 1
INACTIVE - 2
COMPLETED - 3
```

#### Enum with Abstract Methods

```java
public enum Operation {
    ADD {
        @Override
        public double apply(double a, double b) {
            return a + b;
        }
    },
    SUBTRACT {
        @Override
        public double apply(double a, double b) {
            return a - b;
        }
    },
    MULTIPLY {
        @Override
        public double apply(double a, double b) {
            return a * b;
        }
    },
    DIVIDE {
        @Override
        public double apply(double a, double b) {
            return a / b;
        }
    };

    public abstract double apply(double a, double b);
}
```

**Usage:**
```java
double result = Operation.ADD.apply(5, 3);      // 8.0
double result2 = Operation.MULTIPLY.apply(4, 7); // 28.0
```
#### Enum vs Static Final Constants

**Old way (❌ not type-safe):**
```java
public static final int RED = 0;
public static final int GREEN = 1;
public static final int BLUE = 2;

int myColor = 5; // ⚠️ No error, but invalid!
```

**Enum way (✅ type-safe):**
```java
public enum Color { RED, GREEN, BLUE; }

Color myColor = Color.RED;  // ✅ Compile error if invalid value
```

#### Practical Example: Priority Levels

```java
public enum Priority {
    LOW(1, "Can wait"),
    MEDIUM(2, "Should do soon"),
    HIGH(3, "Do now"),
    CRITICAL(4, "Emergency");

    private final int level;
    private final String description;

    Priority(int level, String description) {
        this.level = level;
        this.description = description;
    }

    public int getLevel() {
        return level;
    }

    public String getDescription() {
        return description;
    }

    public boolean isUrgent() {
        return level >= 3;
    }
}
```

**Usage:**
```java
Priority p = Priority.HIGH;

System.out.println(p.getDescription()); // "Do now"
System.out.println(p.isUrgent());       // true

if (p.getLevel() > Priority.MEDIUM.getLevel()) {
    System.out.println("This needs attention!");
}
```
#### Key Points

- Enums are **immutable** — cannot change values
- Enums are **singletons** — only one instance of each constant
- Enums are **type-safe** — compiler catches invalid values
- Use enums when you have a **fixed set of related constants**
- Enums can have **fields, methods, and constructors**





