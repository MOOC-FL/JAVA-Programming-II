**HashMap vs ArrayList in Java**

## **ArrayList** (`java.util.ArrayList`)
- **Implements**: `List<E>` interface
- **Underlying structure**: Dynamic array
- **Order**: Maintains insertion order
- **Access**: Index-based (0 to size-1)
- **Duplicates**: Allowed
- **Null values**: Allowed
- **Performance**:
  - `get(index)`, `set(index)`: O(1)
  - `add()`: O(1) amortized (resizing needed sometimes)
  - `remove(index)`: O(n) (needs shifting)
  - `contains()`: O(n) linear search

```java
import java.util.ArrayList;

ArrayList<String> fruits = new ArrayList<>();
fruits.add("Apple");     // index 0
fruits.add("Banana");    // index 1
fruits.add("Apple");     // duplicates allowed

String first = fruits.get(0);        // "Apple" - O(1)
boolean hasBanana = fruits.contains("Banana"); // O(n) - sequential search
fruits.remove(1);                    // shifts elements - O(n)
```

## **HashMap** (`java.util.HashMap`)
- **Implements**: `Map<K,V>` interface
- **Underlying structure**: Array of buckets (with linked list/red-black tree for collisions)
- **Order**: No guaranteed order (Java 8+: insertion order partially preserved)
- **Access**: Key-based lookup
- **Duplicates**: Unique keys, duplicate values allowed
- **Null values**: One null key allowed, multiple null values allowed
- **Performance**:
  - `get(key)`, `put(key,value)`: O(1) average, O(n) worst-case
  - `containsKey()`: O(1) average
  - `containsValue()`: O(n) (must search all values)

```java
import java.util.HashMap;

HashMap<Integer, String> students = new HashMap<>();
students.put(101, "Alice");  // key=101, value="Alice"
students.put(102, "Bob");
students.put(101, "Charlie"); // Overwrites Alice (keys unique)

String name = students.get(101);     // "Charlie" - O(1) average
boolean hasKey = students.containsKey(102); // O(1) average
boolean hasValue = students.containsValue("Bob"); // O(n)
```

## **When to Use Which:**

**Use ArrayList when:**
- You need ordered collection with index access
- You frequently iterate through all elements
- You need to maintain insertion order
- Duplicates are meaningful
- Example: List of messages, cart items, search results

**Use HashMap when:**
- You need key-value associations
- Fast lookup by key is critical
- You need to check existence of keys quickly
- Keys are unique identifiers
- Example: Cache, dictionary, database ID → object mapping

## **Memory Comparison:**
```java
ArrayList<String> list = new ArrayList<>();
// Stores: ["A", "B", "C"] 
// Memory: ~24 bytes per element overhead

HashMap<String, Integer> map = new HashMap<>();
map.put("A", 1);
// Stores: Entry objects with key, value, hash, next pointer
// Memory: Higher overhead than ArrayList
```

## **Common Operations Comparison:**

| Operation | ArrayList | HashMap |
|-----------|-----------|---------|
| Add element | `add(value)` - O(1) amortized | `put(key,value)` - O(1) avg |
| Get by index/key | `get(index)` - O(1) | `get(key)` - O(1) avg |
| Check contains | `contains(value)` - O(n) | `containsKey(key)` - O(1) avg |
| Remove | `remove(index)` - O(n) | `remove(key)` - O(1) avg |
| Iteration | Fast (sequential memory) | Slower (scattered entries) |

## **Thread Safety:**
- Neither is thread-safe
- Use `Collections.synchronizedList()` or `CopyOnWriteArrayList` for ArrayList
- Use `ConcurrentHashMap` or `Collections.synchronizedMap()` for HashMap
- Better: Use `ConcurrentHashMap` for concurrent maps

## **Example Use Cases:**

```java
// ArrayList example - ordered list
ArrayList<Order> recentOrders = new ArrayList<>();
recentOrders.add(order1);  // Maintains chronological order
recentOrders.get(0);       // Get first order

// HashMap example - fast lookup
HashMap<String, User> userCache = new HashMap<>();
userCache.put("john123", userObject);
User user = userCache.get("john123");  // Instant lookup by username
```

## **Hybrid Approach:**
Sometimes you need both:
```java
// Maintain order AND fast lookup
ArrayList<String> orderedList = new ArrayList<>();
HashMap<String, Integer> indexMap = new HashMap<>();

void addItem(String item) {
    orderedList.add(item);
    indexMap.put(item, orderedList.size() - 1);
}

int getIndex(String item) {
    return indexMap.get(item);  // O(1) instead of O(n)
}
```

**Bottom line**: Choose based on your primary access pattern:
- **Index-based access/iteration** → ArrayList
- **Key-based lookup** → HashMap
- **Need both?** Consider LinkedHashMap or maintain both structures
