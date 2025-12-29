#### Built-in Interfaces
- Java offers a considerable amount of built-in interfaces. Here we'll get familiar with four commonly used interfaces: `List` , `Map` , `Set` , and `Collection` .
  
#### Java Collection Interfaces Comparison Table

| **Feature** | **List Interface** | **Set Interface** | **Map Interface** | **Collection Interface** |
|-------------|-------------------|-------------------|------------------|--------------------------|
| **Core Purpose** | Ordered sequence of elements | Unique elements collection | Key-Value pairs | Root interface for most collections |
| **Duplicates** | ✅ Allowed | ❌ Not allowed | ❌ Keys must be unique (values can duplicate) | Depends on implementation |
| **Order** | ✅ Insertion order maintained | Depends on implementation | Depends on implementation | Depends on implementation |
| **Null Elements** | ✅ Allowed | Depends on implementation | Depends on implementation | Depends on implementation |
| **Primary Operations** | `add()`, `get(index)`, `set(index)`, `remove(index)` | `add()`, `contains()`, `remove()` | `put()`, `get(key)`, `containsKey()`, `remove(key)` | `add()`, `remove()`, `contains()`, `size()` |
| **Index Access** | ✅ Direct (by position) | ❌ No index access | ❌ No index access (access by key) | ❌ No index access |
| **Common Implementations** | `ArrayList`, `LinkedList`, `Vector` | `HashSet`, `TreeSet`, `LinkedHashSet` | `HashMap`, `TreeMap`, `LinkedHashMap`, `Hashtable` | Implemented by List, Set, Queue |
| **Iteration Order** | Predictable (insertion order) | `HashSet`: Unpredictable<br>`LinkedHashSet`: Insertion order<br>`TreeSet`: Natural sorted order | `HashMap`: Unpredictable<br>`LinkedHashMap`: Insertion order<br>`TreeMap`: Key-sorted order | Depends on implementation |
| **Thread Safety** | `Vector`: Thread-safe<br>`ArrayList/LinkedList`: Not thread-safe | Not thread-safe by default | `Hashtable`: Thread-safe<br>`HashMap`: Not thread-safe | Depends on implementation |
| **Performance** | `ArrayList`: Fast random access<br>`LinkedList`: Fast insert/delete | `HashSet`: O(1) average<br>`TreeSet`: O(log n) | `HashMap`: O(1) average<br>`TreeMap`: O(log n) | Varies by implementation |
| **Interface Hierarchy** | Extends `Collection` | Extends `Collection` | Standalone (doesn't extend `Collection`) | Root interface |
| **Sorting** | Can be sorted with `Collections.sort()` | `TreeSet`: Auto-sorted<br>Others: Can be converted to sorted | `TreeMap`: Auto-sorted by keys<br>Others: Can be converted | Depends on implementation |
| **Use Cases** | Lists of items where order matters, sequential data | Unique item collections, mathematical sets | Dictionaries, caches, configurations, relationships | General collection operations |
| **Example Declarations** | `List<String> list = new ArrayList<>();` | `Set<Integer> set = new HashSet<>();` | `Map<String, Integer> map = new HashMap<>();` | `Collection<Object> coll = new ArrayList<>();` |

---

#### Performance Comparison Table

| **Operation** | **ArrayList** | **LinkedList** | **HashSet** | **TreeSet** | **HashMap** | **TreeMap** |
|--------------|--------------|---------------|-------------|-------------|-------------|-------------|
| **Add** | O(1) amortized | O(1) | O(1) avg | O(log n) | O(1) avg | O(log n) |
| **Remove** | O(n) | O(1) | O(1) avg | O(log n) | O(1) avg | O(log n) |
| **Get/Contains** | O(1) | O(n) | O(1) avg | O(log n) | O(1) avg | O(log n) |
| **Memory** | Less overhead | More overhead (node objects) | Moderate | Moderate | Moderate | Moderate |
| **Best For** | Random access, iteration | Insertion/removal at ends | Fast lookups, unique items | Sorted iteration | Fast key-value lookups | Sorted key-value pairs |

---

#### Null Value Support Table

| **Implementation** | **Null Keys** | **Null Values** | **Null Elements** |
|-------------------|---------------|-----------------|-------------------|
| **ArrayList** | N/A | N/A | ✅ Allowed |
| **LinkedList** | N/A | N/A | ✅ Allowed |
| **HashSet** | N/A | N/A | ✅ Allowed |
| **TreeSet** | N/A | N/A | ❌ Not allowed |
| **HashMap** | ✅ Allowed | ✅ Allowed | N/A |
| **TreeMap** | ❌ Not allowed | ✅ Allowed | N/A |
| **Hashtable** | ❌ Not allowed | ❌ Not allowed | N/A |
| **LinkedHashSet** | N/A | N/A | ✅ Allowed |

---

#### Common Methods Comparison

| **Method Type** | **List** | **Set** | **Map** | **Collection** |
|-----------------|----------|---------|---------|----------------|
| **Add Element** | `add(E e)`<br>`add(int index, E e)` | `add(E e)` | `put(K key, V value)` | `add(E e)` |
| **Remove** | `remove(int index)`<br>`remove(Object o)` | `remove(Object o)` | `remove(Object key)` | `remove(Object o)` |
| **Get/Access** | `get(int index)`<br>`set(int index, E e)` | N/A | `get(Object key)`<br>`getOrDefault()` | N/A |
| **Check Existence** | `contains(Object o)`<br>`indexOf(Object o)` | `contains(Object o)` | `containsKey(Object key)`<br>`containsValue(Object v)` | `contains(Object o)` |
| **Size** | `size()` | `size()` | `size()` | `size()` |
| **Iteration** | `listIterator()`<br>`iterator()` | `iterator()` | `keySet().iterator()`<br>`values().iterator()`<br>`entrySet().iterator()` | `iterator()` |
| **Bulk Operations** | `addAll()`<br>`removeAll()`<br>`retainAll()` | `addAll()`<br>`removeAll()`<br>`retainAll()` | `putAll()` | `addAll()`<br>`removeAll()`<br>`retainAll()` |
| **Clear** | `clear()` | `clear()` | `clear()` | `clear()` |
| **Empty Check** | `isEmpty()` | `isEmpty()` | `isEmpty()` | `isEmpty()` |

---

#### Quick Decision Guide

**Choose `List` when:**
- Order matters
- Duplicates needed
- Index-based access required
- **ArrayList**: Frequent random access
- **LinkedList**: Frequent insertions/deletions

**Choose `Set` when:**
- Uniqueness is required
- No duplicates allowed
- **HashSet**: Fastest, no order needed
- **TreeSet**: Need sorted order
- **LinkedHashSet**: Need insertion order preserved

**Choose `Map` when:**
- Key-value associations needed
- Fast lookup by key required
- **HashMap**: General purpose, fastest
- **TreeMap**: Keys need to be sorted
- **LinkedHashMap**: Insertion order of keys needed
- **Hashtable**: Legacy, thread-safe

**Remember:** Always program to interfaces (`List`, `Set`, `Map`, `Collection`) rather than concrete implementations for better code flexibility and polymorphism.
