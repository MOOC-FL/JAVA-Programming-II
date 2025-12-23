#### Interfaces as Method Parameters
- The true benefits of interfaces are reaped when they are used as the type of parameter provided to a method. Since an interface can be used as a variable's type, it can also be used as a parameter type in method calls. For example, the `print` method in the `Printer` class of the class below gets a variable of type **read**.
```java
public class Printer {
    public void print(Readable readable) {
        System.out.println(readable.read());
    }
}
```  
- The value of the `print` method of the `printer` class lies in the fact that it can be given any class that implements the `Readable` interface as a parameter. Were we to call the method with any object instantiated from a class that inherits the `Readable` class, the method would function as desired.
```java
TextMessage message = new TextMessage("ope", "Oh wow, this printer knows how to print these as well!");

ArrayList<String> pages = new ArrayList<>();
pages.add("Values common to both {1, 3, 5} and {2, 3, 4, 5} are {3, 5}.");
Ebook book = new Ebook("Introduction to University Mathematics.", pages);

Printer printer = new Printer();
printer.print(message);
printer.print(book);
``` 
```text
Oh wow, this printer knows how to print these as well!
Values common to both {1, 3, 5} and {2, 3, 4, 5} are {3, 5}.
```
- Let's make another class called `ReadingList` to which we can add interesting things to read. The class has an `ArrayList` instance as an instance variable, where the things to be read are added. Adding to the reading list is done using the `add` method, which receives a `Readable-type` object as its parameter.
```java
public class ReadingList {
    private ArrayList<Readable> readables;

    public ReadingList() {
        this.readables = new ArrayList<>();
    }

    public void add(Readable readable) {
        this.readables.add(readable);
    }

    public int toRead() {
        return this.readables.size();
    }
}
``` 
- Reading lists are usually readable, so let's have the `ReadingList` class implement the `Readable` interface. The `read` method of the reading list reads all the objects in the `readables` list, and adds them to the string returned by the `read()` method one-by-one.
```java
public class ReadingList implements Readable {
    private ArrayList<Readable> readables;

    public ReadingList() {
        this.readables = new ArrayList<>();
    }

    public void add(Readable readable) {
        this.readables.add(readable);
    }

    public int toRead() {
        return this.readables.size();
    }

    public String read() {
        String read = "";

        for (Readable readable: this.readables) {
            read = read + readable.read() + "\n";
        }

        // once the reading list has been read, we empty it
        this.readables.clear();
        return read;
    }
}
``` 
```java
ReadingList jonisList = new ReadingList();
jonisList.add(new TextMessage("arto", "have you written the tests yet?"));
jonisList.add(new TextMessage("arto", "have you checked the submissions yet?"));

System.out.println("Joni's to-read: " + jonisList.toRead());
```
```text
Joni's to-read: 2
```
- Because the `ReadingList` is of type `Readable`, we're able to add `ReadingList` objects to the reading list. In the example below, Joni has a lot to read. Fortunately for him, Verna comes to the rescue and reads the messages on Joni's behalf.
```java
ReadingList jonisList = new ReadingList();
int i = 0;
while (i < 1000) {
    jonisList.add(new TextMessage("arto", "have you written the tests yet?"));
    i = i + 1;
}

System.out.println("Joni's to-read: " + jonisList.toRead());
System.out.println("Delegating the reading to Verna");

ReadingList vernasList = new ReadingList();
vernasList.add(jonisList);
vernasList.read();

System.out.println();
System.out.println("Joni's to-read: " + jonisList.toRead());
``` 
```text
Joni's to-read: 1000
Delegating the reading to Verna

Joni's to-read:0
```
- The `read` method called on Verna's list goes through all the `Readable` objects and calls the `read` method on them. When the `read` method is called on Verna's list it also goes through Joni's reading list that's included in Verna's reading list. Joni's reading list is run through by calling its `read` method. At the end of each `read` method call, the `read` list is cleared. In this way, Joni's reading list empties when Verna reads it.
- As you notice, the program already contains a lot of references. It's a good idea to draw out the state of the program step-by-step on paper and outline how the read method call of the `vernasList` object proceeds!



