#### Hash Map as an Instance Variable
- The example considered above on storing books is problematic in that the book's spelling format must be remembered accurately. Someone may search for a book with a lowercase letter, while another may, for example, enter a space to start typing a name. Let's take a look at a slightly more forgiving search by book title.
- We make use of the tools provided by the String-class to handle strings.
- he `toLowerCase()`method creates a new string with all letters converted to lowercase. The `trim()`method, on the other hand, creates a new string where empty characters such as spaces at the beginning and end have been removed.
```java
String text = "Pride and Prejudice ";
text = text.toLowerCase(); // text currently "pride and prejudice "
text = text.trim(); // text now "pride and prejudice
```
- The conversion of the string described above will result in the book being found, even if the user happens to type the title of the book with lower-case letters.
- Let's create a `Libraryclass` that encapsulates a hash map containing books, and enables you to case-independent search for books.
- We'll add methods for adding, retrieving and deleting to the `Library` class. Each of these is based on a sanitized name - this involves converting the name to lower case and removing extraneous spaces from the beginning and end.
- Let's first outline the method for adding. The book is added to the hash map with the book name as the key and the book itself as the value.
- Since we want to allow for minor misspellings, such as capitalized or lower-cased strings, or ones with spaces at the beginning and/or end, the key - the title of the book - is converted to lowercase, and spaces at the beginning and end are removed.
```java
public class Library {
    private HashMap<String, Book> directory;

    public Library() {
        this.directory = new HashMap<>();
    }

    public void addBook(Book book) {
        String name = book.getName()
        if (name == null) {
            name = "";
        }

        name = name.toLowerCase();
        name = name.trim();

        if (this.directory.containsKey(name)) {
            System.out.println("Book is already in the library!");
        } else {
            directory.put(name, book);
        }
    }
}

```
> The `containsKey` method of the hash map is being used above to check for the existence of a key. The method Returns `true`if any value has been added to the hash map with the given key. Otherwise, the method Returns `false`.

- We can already see that code dealing with string sanitization is needed in every method that handles a book, which makes it a good candidate for a separate helper method. The method is implemented as a class method since it doesn't handle object variables.
```java
public static String sanitizedString(String string) {
    if (string == null) {
        return "";
    }

    string = string.toLowerCase();
    return string.trim();
}
```
- The implementation is much neater when the helper method is used.
```java
public class Library {
    private HashMap<String, Book> directory;

    public Library() {
        this.directory = new HashMap<>();
    }

    public void addBook(Book book) {
        String name = sanitizedString(book.getName());

        if (this.directory.containsKey(name)) {
            System.out.println("Book is already in the library!");
        } else {
            directory.put(name, book);
        }
    }

    public Book getBook(String bookTitle) {
        bookTitle = sanitizedString(bookTitle);
        return this.directory.get(bookTitle);
    }

    public void removeBook(String bookTitle) {
        bookTitle = sanitizedString(bookTitle);

        if (this.directory.containsKey(bookTitle)) {
            this.directory.remove(bookTitle);
        } else {
            System.out.println("Book was not found, cannot be removed!");
        }
    }

    public static String sanitizedString(String string) {
        if (string == null) {
            return "";
        }

        string = string.toLowerCase();
        return string.trim();
    }
}
```

> Let's take a look at the class in use.

```java
Book senseAndSensibility = new Book("Sense and Sensibility", 1811, "...");
Book prideAndPrejudice = new Book("Pride and Prejudice", 1813, "....");

Library library = new Library();
library.addBook(senseAndSensibility);
library.addBook(prideAndPrejudice);

System.out.println(library.getBook("pride and prejudice");
System.out.println();

System.out.println(library.getBook("PRIDE AND PREJUDICE");
System.out.println();

System.out.println(library.getBook("SENSE"));
```
- In the above example, we adhered to the **DRY (Don't Repeat Yourself)** principle according to which code duplication should be avoided.
- `Sanitizing a string`, ie, `changing it to lowercase`, and `trimming`
- ie, removing empty characters from the beginning and end, would have been repeated many times in our library class without the `sanitizedString` method.
  > Repetitive code is often not noticed until it has already been written, which means that it almost always makes its way into the code. There's nothing wrong with that - the important thing is that the code is cleaned up so that places that require tidying up are noticed.
