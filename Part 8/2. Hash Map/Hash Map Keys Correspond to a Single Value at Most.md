#### Hash Map Keys Correspond to a Single Value at Most
- The hash map has a maximum of one value per key. If a new key-value pair is added to the hash map, but the key has already been associated with some other value stored in the hash map, the old value will vanish from the hash map.
```java
HashMap<String, String> numbers = new HashMap<>();
numbers.put("Uno", "One");
numbers.put("Dos", "Zwei");
numbers.put("Uno", "Ein");

String translation = numbers.get("Uno");
System.out.println(translation);

System.out.println(numbers.get("Dos"));
System.out.println(numbers.get("Tres"));
System.out.println(numbers.get("Uno"));
```
#### A Reference Type Variable as a Hash Map Value
- Let's take a look at how a spreadsheet works using a library example. You can search for books by book title. If a book is found with the given search term, the library returns a reference to the book. Let's begin by creating an example class `Book` that has its name, content and the year of publication as instance variables.
```java
public class Book {
    private String name;
    private String content;
    private int published;

    public Book(String name, int published, String content) {
        this.name = name;
        this.published = published;
        this.content = content;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPublished() {
        return this.published;
    }

    public void setPublished(int published) {
        this.published = published;
    }

    public String getContent() {
        return this.content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String toString() {
        return "Name: " + this.name + " (" + this.published + ")\n"
            + "Content: " + this.content;
    }
}
```
> Let's create a hash map that uses the book's name as a key, i.e., a String-type object, and the book we've just created as the value.
```java
HashMap<String, Book> directory = new HashMap<>();
```
- The hash map above uses a`String` object as a key. Let's expand the example so that two books are added to the directory, `"Sense and Sensibility"` and `"Pride and Prejudice"`.
 ```java
Book senseAndSensibility = new Book("Sense and Sensibility", 1811, "...");
Book prideAndPrejudice = new Book("Pride and Prejudice", 1813, "....");

HashMap<String, Book> directory = new HashMap<>();
directory.put(senseAndSensibility.getName(), senseAndSensibility);
directory.put(prideAndPrejudice.getName(), prideAndPrejudice);
  ```
- Books can be retrieved from the directory by book name. A search for "Persuasion" will not produce any results, in which case the hash map returns a null-reference. The book "Pride and Prejudice" is found, however.
```java
import java.util.HashMap;

public class BookDirectory {
    public static void main(String[] args) {
        // Create a HashMap to store books by title
        HashMap<String, Book> directory = new HashMap<>();
        
        // Add some books
        directory.put("Pride and Prejudice", 
                     new Book("Pride and Prejudice", 1813, "A romantic novel..."));
        directory.put("Moby Dick", 
                     new Book("Moby Dick", 1851, "A whale of a tale..."));
        
        // Search for books
        Book book = directory.get("Persuasion");  // Key doesn't exist
        System.out.println(book);  // Prints: null
        
        System.out.println();  // Empty line
        
        book = directory.get("Pride and Prejudice");  // Key exists
        System.out.println(book);  // Prints book details
    }
}

class Book {
    private String name;
    private int year;
    private String content;
    
    public Book(String name, int year, String content) {
        this.name = name;
        this.year = year;
        this.content = content;
    }
    
    @Override
    public String toString() {
        return "Name: " + name + " (" + year + ")\n" +
               "Content: " + content;
    }
}
```

