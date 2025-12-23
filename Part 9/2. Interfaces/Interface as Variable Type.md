#### interfaces as variable type 
- The type of a variable is always stated as its introduced. There are two kinds of type, the primitive-type variables (int, double, ...) and reference-type variables (all objects). We've so far used an object's class as the type of a reference-type variable.
```java
String string = "string-object";
TextMessage message = new TextMessage("ope", "many types for the same object");
```
- An object's type can be other than its class.
  For example, the type of the `Ebook` class that implements the `Readable` interface is both `Ebook` and `Readable`.
  Similarly, the text message also has multiple types. Because the `TextMessage` class implements the `Readable` interface, it has a `Readable` type in addition to the `TextMessage` type.
```java
TextMessage message = new TextMessage("ope", "Something cool's about to happen);
Readable readable = new TextMessage("ope", "The text message is Readable!");
```
```java
ArrayList<String> pages = new ArrayList<>();
pages.add("A method can call itself.");

Readable book = new Ebook("Introduction to Recursion", pages);

int page = 0;
while (page < book.pages()) {
    System.out.println(book.read());
    page = page + 1;
}
```
- Because an interface can be used as a type, it's possible to create a list that contains objects of the interface's type.
```java
ArrayList<Readable> readingList = new ArrayList<>();

readingList.add(new TextMessage("ope", "never been programming before..."));
readingList.add(new TextMessage("ope", "gonna love it i think!"));
readingList.add(new TextMessage("ope", "give me something more challenging! :)"));
readingList.add(new TextMessage("ope", "you think i can do it?"));
readingList.add(new TextMessage("ope", "up here we send several messages each day"));


ArrayList<String> pages = new ArrayList<>();
pages.add("A method can call itself.");

readingList.add(new Ebook("Introduction to Recursion.", pages));

for (Readable readable: readingList) {
    System.out.println(readable.read());
}
```
- Note that although the Ebook class that inherits the Readable interface class is always of the interface's type, not all classes that implement the Readable interface are of type Ebook. You can assign an object created from the Ebook class to a Readable-type variable, but it does not work the other way without a separate type conversion.
```java
Readable readable = new TextMessage("ope", "TextMessage is Readable!"); // works
TextMessage message = readable; // doesn't work

TextMessage castMessage = (TextMessage) readable; // works if, and only if, readable is of text message type
```
- Type conversion succeeds if, and only if, the variable is of the type that it's being converted to. Type conversion is not considered good practice, and one of the few situation where it's use is appropriate is in the implementation of the `equals` method.

