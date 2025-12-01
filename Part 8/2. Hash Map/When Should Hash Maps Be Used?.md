#### When Should Hash Maps Be Used?
- The hash map is implemented internally in such a way that searching by a key is very fast. The hash map generates a "hash value" from the key, i.e. a piece of code, which is used to store the value of a specific location. When a key is used to retrieve information from a hash map, this particular code identifies the location where the value associated with the key is. In practice, it's not necessary to go through all the key-value pairs in the hash map when searching for a key; the set that's checked is significantly smaller.
> We'll be taking a deeper look into the implementation of a hash map in the Advanced Programming and Data Structures and Algorithms courses.
- Consider the library example that was introduced above. The whole program could just as well have been implemented using a list. In that case, the books would be placed on the list instead of the directory, and the book search would happen by iterating over the list.
> In the example below, the books have been stored in a list and searching for them is done by going through the list.
```java
ArrayList<Book> books = new ArrayList<>();
Book senseAndSensibility = new Book("Sense and Sensibility", 1811, "...");
Book prideAndPrejudice = new Book("Pride and Prejudice", 1813, "....");
books.add(senseAndSensibility);
books.add(prideAndPrejudice);

// searching for a book named Sense and Sensibility
Book match = null;
for (Book book: books) {
    if (book.getName().equals("Sense and Sensibility") {
        match = book;
        break;
    }
}

System.out.println(match);
System.out.println();

// searching for a book named Persuasion
match = null;
for (Book book: books) {
    if (book.getName().equals("Persuasion")) {
        match = book;
        break;
    }
}

System.out.println(match);
```
- For the program above, you could create a separate class method `get` that is provided a list and the name of the book to be fetched as parameters. The method returns a book found by the given name if one exists. Otherwise, the method returns a `null` reference.
```java
public static Book get(ArrayList<Book> books, String name) {

    for (Book book: books) {
        if (book.getName().equals(name)) {
            return book;
        }
    }

    return null;
}
```
> Now the program is a bit more clear.
```java
ArrayList<Book> books = new ArrayList<>();
Book senseAndSensibility = new Book("Sense and Sensibility", 1811, "...");
Book prideAndPrejudice = new Book("Pride and Prejudice", 1813, "....");
books.add(senseAndSensibility);
books.add(prideAndPrejudice);

System.out.println(get(books, "Sense and Sensibility"));

System.out.println();

System.out.println(get(books, "Persuasion"));
```
> The program would now work in the same way as the program implemented with the hash map, right?
- Functionally, yes. Let's, however, consider the performance of the program. Java's `System.nanoTime()`method Returns the time of the computer in nanoseconds. We'll add functionality to the program considered above to calculate how long it took to retrieve the books.
```java
ArrayList<Book> books = new ArrayList<>();

// adding ten million books to the list

long start = System.nanoTime();
System.out.println(get(books, "Sense and Sensibility"));

System.out.println();

System.out.println(get(books, "Persuasion"));
long end = System.nanoTime();
double durationInMilliseconds = 1.0 * (end - start) / 1000000;

System.out.println("The book search took " + durationInMilliseconds + " milliseconds.");
```

> With ten million books, it takes almost a second to find two books. Of course, the way in which the list is ordered has an effect. If the book being searched was first on the list, the program would be faster. On the other hand, if the book were not on the list, the program would have to go through all of the books in the list before determining that such book does not exist.

> Let's consider the same program using a hash map.
```java
HashMap<String, Book> directory = new HashMap<>();

// adding ten million books to the list

long start = System.nanoTime();
System.out.println(directory.get("Sense and Sensibility"));

System.out.println();

System.out.println(directory.get("Persuasion"));
long end = System.nanoTime();
double durationInMilliseconds = 1.0 * (end - start) / 1000000;

System.out.println("The book search took " + durationInMilliseconds + " milliseconds.");
```
- It took about 0.4 milliseconds to search for two books out of ten million books with the hash map. The difference in performance in our example is over a thousandfold.

- The difference in performance is due to the fact that when a book is searched for in a list, the worst-case scenario involves going through all the books in the list. In a hash map, it isn't necessary to check all of the books as the key determines the location of a given book in a hash map. The difference in performance depends on the number of books - for example, the performance differences are negligible for 10 books. However, for millions of books, the performance differences are clearly visible.
- Does this mean that we'll only be using hash maps going forward? Of course not. Hash maps work well when we know exactly what we are looking for. If we wanted to identify books whose title contains a particular string, the hash map would be of little use.
- The hash maps also have no internal order, and it is not possible to search the hash map based on the indexes. The items in a list are in the order they were added to the list.
- Typically, hash maps and lists are used together. The hash map provides quick access to a specific key or keys, while the list is used, for instance, to maintain order.

| Characteristic | ArrayList (Linear Search) | HashMap |
|----------------|---------------------------|---------|
| **Performance with large datasets** | Slow - O(n) time complexity, must potentially check all elements. With 10M books, search takes ~881 ms. | Fast - O(1) average case, direct access via hash. With 10M books, search takes ~0.25 ms. |
| **Use cases & limitations** | Useful when: 1) Searching by content/substring (e.g., "books whose title contains a particular string")<br>2) Need to maintain insertion order<br>3) Require index-based access<br>4) Collection size is small (negligible difference for 10 items)<br>Items remain in the order they were added. | Useful when: 1) Exact key lookups are needed<br>2) Fast access is critical for large collections<br>3) Unique keys are available<br>Not useful for: substring searches, maintaining order, or index-based access. Hash maps have no internal order. |

