An **iterator** in Java is an object that lets you traverse a collection (like a List, Set, etc.) element by element without exposing the underlying structure.

#### The Iterator Interface

```java
Iterator<E> from java.util
```
Three core methods:

| Method | Description |
|--------|-------------|
| `hasNext()` | Returns `true` if more elements exist |
| `next()` | Returns the next element |
| `remove()` | Removes the last element returned by `next()` |

#### Basic Usage

```java
import java.util.*;

List<String> names = new ArrayList<>(Arrays.asList("Alice", "Bob", "Charlie"));
Iterator<String> it = names.iterator();

while (it.hasNext()) {
    String name = it.next();
    System.out.println(name);
}
```

#### Safe Removal During Iteration

You **cannot** remove elements from a collection using a regular for-each loop — it throws `ConcurrentModificationException`. Use the iterator's `remove()` instead:

```java
List<Integer> numbers = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5));
Iterator<Integer> it = numbers.iterator();

while (it.hasNext()) {
    if (it.next() % 2 == 0) {
        it.remove(); // safe removal
    }
}
// numbers = [1, 3, 5]
```

#### ListIterator (bidirectional)

`ListIterator` extends `Iterator` and works only on `List` — it can go **forward and backward**:

```java
List<String> list = new ArrayList<>(Arrays.asList("a", "b", "c"));
ListIterator<String> lit = list.listIterator(list.size()); // start at end

while (lit.hasPrevious()) {
    System.out.println(lit.previous()); // c, b, a
}
```

Extra methods: `hasPrevious()`, `previous()`, `add()`, `set()`, `nextIndex()`, `previousIndex()`

#### Implementing Iterable (Custom Class)

Make your own class work with for-each by implementing `Iterable<T>`:

```java
class Range implements Iterable<Integer> {
    private int start, end;

    Range(int start, int end) {
        this.start = start;
        this.end = end;
    }

    @Override
    public Iterator<Integer> iterator() {
        return new Iterator<>() {
            int current = start;

            public boolean hasNext() { return current < end; }
            public Integer next() { return current++; }
        };
    }
}

// Usage
for (int n : new Range(1, 5)) {
    System.out.println(n); // 1, 2, 3, 4
}
```
#### Key Points

- **For-each loops** use iterators internally — any class implementing `Iterable` works with them.
- Use `Iterator` when you need to **remove elements** while iterating.
- Use `ListIterator` when you need **bidirectional traversal** or to **modify** elements in place.
- For concurrent scenarios, prefer `CopyOnWriteArrayList` or `ConcurrentHashMap` iterators which are thread-safe.
