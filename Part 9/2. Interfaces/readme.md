#### Interfaces 
- We can use interfaces to define behavior that's required from a class, i.e., its methods. They're defined the same way that regular Java classes are, but "`public interface ...`" is used instead of "`public class ...` " at the beginning of the class.
-  Interfaces define behavior through method names and their return values. However, they don't always include the actual implementations of the methods. A visibility attribute on interfaces is not marked explicitly as they're always `public`.
>  Let's examine a Readable interface that describes readability.
```java
public interface Readable {
    String read();
}
```
- The `Readable` interface declares a `read()` method, which returns a String-type object. Readable defines certain behavior: for example, a text message or an email may be readable.
- The classes that implement the interface decide how the methods defined in the interface are implemented. A class implements the interface by adding the keyword implements after the class name followed by the name of the interface being implemented. Let's create a class called `TextMessage` that implements the `Readable` interface.
```java
public class TextMessage implements Readable {
    private String sender;
    private String content;

    public TextMessage(String sender, String content) {
        this.sender = sender;
        this.content = content;
    }

    public String getSender() {
        return this.sender;
    }

    public String read() {
        return this.content;
    }
}
```
- Since the `TextMessage` class implements the `Readable` interface (`public class TextMessage implements Readable`), the `TextMessage` class must contain an implementation of the `public String read()` method. Implementations of methods defined in the interface must always have public as their visibility attribute.
> An Interface Is a Contract of Behaviour
- When a class implements an interface, it signs an agreement. The agreement dictates that the class will implement the methods defined by the interface. If those methods are not implemented in the class, the program will not function.
- The interface defines only the names, parameters, and return values ​​of the required methods. The interface, however, does not have a say on the internal implementation of its methods. It is the responsibility of the programmer to define the internal functionality for the methods.

- In addition to the `TextMessage` class, let's add another class that implements the `Readable` interface. The `Ebook` class is an electronic implementation of a book that containing the title and pages of a book. The ebook is read page by page, and calling the `public String read()` method always returns the next page as a `string`.
```java
public class Ebook implements Readable {
    private String name;
    private ArrayList<String> pages;
    private int pageNumber;

    public Ebook(String name, ArrayList<String> pages) {
        this.name = name;
        this.pages = pages;
        this.pageNumber = 0;
    }

    public String getName() {
        return this.name;
    }

    public int pages() {
        return this.pages.size();
    }

    public String read() {
        String page = this.pages.get(this.pageNumber);
        nextPage();
        return page;
    }

    private void nextPage() {
        this.pageNumber = this.pageNumber + 1;
        if(this.pageNumber % this.pages.size() == 0) {
            this.pageNumber = 0;
        }
    }
}
```
- Objects can be instantiated from interface-implementing classes just like with normal classes. They're also used in the same way, for instance, as an ArrayList's type.
```java
TextMessage message = new TextMessage("ope", "It's going great!");
System.out.println(message.read());

ArrayList<TextMessage> textMessage = new ArrayList<>();
textMessage.add(new TextMessage("private number", "I hid the body.");
```
```text
It's going great!
```
```java
ArrayList<String> pages = new ArrayList<>();
pages.add("Split your method into short, readable entities.");
pages.add("Separate the user-interface logic from the application logic.");
pages.add("Always program a small part initially that solves a part of the problem.");
pages.add("Practice makes the master. Try different out things for yourself and work on your own projects.");

Ebook book = new Ebook("Tips for programming.", pages);

int page = 0;
while (page < book.pages()) {
    System.out.println(book.read());
    page = page + 1;
}
```
```text
Sample output
Split your method into short, readable entities.
Separate the user-interface logic from the application logic.
Always program a small part initially that solves a part of the problem.
Practice makes the master. Try different out things for yourself and work on your own projects.
```

