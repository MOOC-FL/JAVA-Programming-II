#### Object polymorphism
- You are familiar with the concept of inheritance hierarchy.
- You understand that an object can be represented through all of its actual types.
- We've encountered situations where `reference-type` variables have other types besides their own one. For example, all objects are of type `Object`, i.e., any given `object` can be represented as a `Object-type` variable in addition to its own type.
```java
String text = "text";
Object textString = "another string";
```
```java
String text = "text";
Object textString = text;
```
- In the examples above, a string variable is represented as both a String type and an Object type. Also, a String-type variable is assigned to an Object-type variable. However, assignment in the other direction, i.e., setting an Object-type variable to a String type, will not work. This is because `Object-type` variables are not of type `String`.
```java
Object textString = "another string";
String text = textString; // WON'T WORK!
```
> What is this all about?

> In addition to each variable's original type, each variable can also be represented by the types of interfaces it implements and classes that it inherits. The String class inherits the Object class and, as such, String objects are always of type Object. The Object class does not inherit a String class, so Object-type variables are not automatically of type String. Take a closer look at the String API documentation, in particular at the top of the HTML page.

- The API documentation for the String class begins with a generic header followed by the class' package (`java.lang`). After the package details, the name of the class (`Class String`) is followed by the **inheritance hierarchy** of the class.
```text
  java.lang.Object
   java.lang.String
```
>**The inheritance hierarchy lists all the classes that the given class has inherited.**
-  Inherited classes are listed in the order of inheritance, with class being inspected always at the bottom. In the inheritance hierarchy of the `String` class, we see that the String class inherits the `Object` class. In Java, each class can inherit one class at most. On the other hand, the inherited class may have inherited another class. As such, a class may indirectly inherit more than a single class.
- The inheritance hierarchy can also be thought of as a list of the different types that the class implements.
- Knowledge of the fact that objects can be of many different types — of type Object, for instance — makes programming simpler. If we only need methods defined in the Object class, such as `toString`, `equals` and `hashCode` in a method, we can simply use `Object` as the type of the method parameter.
>  In that case, you can pass the method for any object as a parameter. Let's take a look at this with the `printManyTimes` method. The method gets an `Object-type` variable and the number of print operations as its parameters.
```java
public class Printer {

    public void printManyTimes(Object object, int times) {
        int i = 0;
        while (i < times) {
            System.out.println(object.toString());
            // or System.out.println(object);

            i = i + 1;
        }
    }
}
```
- The method can be given any type of object as a parameter. Within the `printManyTimes` method, the object only has access to the methods defined in the Object class because the `object` is known in the method to be of type Object. The `object` may, in fact, be of another type.
```java
Printer printer = new Printer();

String string = " o ";
List<String> words = new ArrayList<>();
words.add("polymorphism");
words.add("inheritance");
words.add("encapsulation");
words.add("abstraction");

printer.printManyTimes(string, 2);
printer.printManyTimes(words, 3);
```
```text
Sample output
o
o
[polymorphism, inheritance, encapsulation, abstraction]
[polymorphism, inheritance, encapsulation, abstraction]
[polymorphism, inheritance, encapsulation, abstraction]
```
- Let's continue to look at the API description of the `String` class. The inheritance hierarchy in the description is followed by a list of interfaces implemented by the class.
```java
  All Implemented Interfaces:
  Serializable, CharSequence, Comparable<String>
```
- The `String` class implements the `Serializable`,` CharSequence`, and `Comparable <String>` interfaces. An interface is also a type. According to the class' API description, the following interfaces can be set as the type of a String object.
```java
Serializable serializableString = "string";
CharSequence charSequenceString = "string";
Comparable<String> comparableString = "string";
```
- Since we're able to define the type of a method's parameter, we can declare methods that receive an object that ***implements a specific interface***. When a method's parameter is an interface, any object that implements that interface can be passed to it as an argument.

- We'll extend the `Printer` class so that it has a method for printing the characters of objects that implement the `CharSequence` interface. The `CharSequence` interface provides, among other things, methods `int length()` for getting a string's length and `char charAt(int index)`, which retrieves a character from a given index.
```java
public class Printer {

    public void printManyTimes(Object object, int times) {
        int i = 0;
        while (i < times) {
            System.out.println(object);
            i = i + 1;
        }
    }

    public void printCharacters(CharSequence charSequence) {
        int i = 0;
        while (i < charSequence.length()) {
            System.out.println(charSequence.charAt(i));
            i = i + 1;
        }
    }
}
```
- The `printCharacters` method can be passed any object that implements the `CharSequence` interface. These include `String` as well as `StringBuilder`, which is often more functional for building `strings` than `String`. The printCharacters method prints each character of a given `object` on its own line.
```java
Printer printer = new Printer();

String string = "works";

printer.printCharacters(string);
```
```text
w
o
r
k
s
```
