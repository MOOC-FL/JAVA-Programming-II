# ArrayList vs HashMap Performance Comparison

## Code Examples

### ArrayList Implementation
```java
import java.util.ArrayList;

public class ArrayListSearch {
    public static Book get(ArrayList<Book> books, String name) {
        for (Book book : books) {
            if (book.getName().equals(name)) {
                return book;
            }
        }
        return null;
    }
    
    public static void main(String[] args) {
        ArrayList<Book> books = new ArrayList<>();
        
        // Adding ten million books to the list
        for (int i = 0; i < 10_000_000; i++) {
            books.add(new Book("Book " + i, 1900 + i % 200, "Content of book " + i));
        }
        // Add specific books we want to find
        books.add(new Book("Sense and Sensibility", 1811, "Content of Sense and Sensibility..."));
        books.add(new Book("Persuasion", 1818, "Content of Persuasion..."));
        
        long start = System.nanoTime();
        System.out.println(get(books, "Sense and Sensibility"));
        System.out.println();
        System.out.println(get(books, "Persuasion"));
        long end = System.nanoTime();
        
        double durationInMilliseconds = 1.0 * (end - start) / 1_000_000;
        System.out.println("The book search took " + durationInMilliseconds + " milliseconds.");
    }
}
```

### HashMap Implementation
```java
import java.util.HashMap;

public class HashMapSearch {
    public static void main(String[] args) {
        HashMap<String, Book> directory = new HashMap<>();
        
        // Adding ten million books to the HashMap
        for (int i = 0; i < 10_000_000; i++) {
            directory.put("Book " + i, new Book("Book " + i, 1900 + i % 200, "Content of book " + i));
        }
        // Add specific books we want to find
        directory.put("Sense and Sensibility", new Book("Sense and Sensibility", 1811, "Content of Sense and Sensibility..."));
        directory.put("Persuasion", new Book("Persuasion", 1818, "Content of Persuasion..."));
        
        long start = System.nanoTime();
        System.out.println(directory.get("Sense and Sensibility"));
        System.out.println();
        System.out.println(directory.get("Persuasion"));
        long end = System.nanoTime();
        
        double durationInMilliseconds = 1.0 * (end - start) / 1_000_000;
        System.out.println("The book search took " + durationInMilliseconds + " milliseconds.");
    }
}
```

### Book Class (Common to both)
```java
public class Book {
    private String name;
    private int year;
    private String content;
    
    public Book(String name, int year, String content) {
        this.name = name;
        this.year = year;
        this.content = content;
    }
    
    public String getName() {
        return name;
    }
    
    public int getYear() {
        return year;
    }
    
    public String getContent() {
        return content;
    }
    
    @Override
    public String toString() {
        return "Name: " + name + " (" + year + ") Content: " + content.substring(0, Math.min(20, content.length())) + "...";
    }
}
```

## Performance Comparison

| Aspect | ArrayList (Linear Search) | HashMap |
|--------|--------------------------|---------|
| **Time Complexity** | O(n) - Linear time | O(1) - Constant time (average case) |
| **Search Method** | Sequential search through all elements | Hash-based direct access |
| **Performance** | ~881 ms for 2 searches with 10M items | ~0.1-1 ms for 2 searches with 10M items |
| **Memory Usage** | Lower (stores only objects) | Higher (stores objects + hash table) |
| **Best Case** | O(1) - if element is first | O(1) - regardless of position |
| **Worst Case** | O(n) - if element is last or not found | O(n) - only with many hash collisions |

## Key Differences

### ArrayList Search Characteristics:
1. **Linear Search**: Must check each book sequentially until a match is found
2. **Order Matters**: Performance depends on element position
3. **Full Scan for Misses**: Must check ALL books to confirm a book doesn't exist
4. **Memory Efficient**: Only stores the book objects

### HashMap Search Characteristics:
1. **Hash-Based Access**: Uses hash code to compute direct location
2. **Constant Time**: Average case O(1) regardless of collection size
3. **Immediate Miss Detection**: Can quickly determine if key doesn't exist
4. **Extra Memory**: Requires space for hash table structure

## When to Use Each

**Use ArrayList when:**
- Collection size is small
- You need to maintain insertion order
- Memory is a critical concern
- You frequently iterate through all elements
- You need index-based access

**Use HashMap when:**
- Collection size is large
- Fast lookups are critical
- You have unique keys for your objects
- You don't need to maintain order
- Memory overhead is acceptable

## Sample Output Comparison

**ArrayList Output:**
```
Name: Sense and Sensibility (1811) Content: Content of Sense and...

Name: Persuasion (1818) Content: Content of Persuasion...
The book search took 881.3447 milliseconds.
```

**HashMap Output:**
```
Name: Sense and Sensibility (1811) Content: Content of Sense and...

Name: Persuasion (1818) Content: Content of Persuasion...
The book search took 0.2543 milliseconds.
```

## Conclusion
For large collections where fast lookups are important, HashMap provides significantly better performance (1000x faster in this example) at the cost of additional memory usage. The choice depends on your specific use case requirements.
