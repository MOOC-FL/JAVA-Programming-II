#### The actual type of an object dictates which method is executed
- An object's type decides what the methods provided by the object are.
- For instance, we implemented the class `Student` earlier. If a reference to a `Student` type object is stored in a `Person `type variable,
only the methods defined in the `Person` class (and its superclass and interfaces) are available:
```java
Person ollie = new Student("Ollie", "6381 Hollywood Blvd. Los Angeles 90028");
ollie.credits();        // DOESN'T WORK!
ollie.study();              // DOESN'T WORK!
System.out.println(ollie);   // ollie.toString() WORKS
```
- So an object has at its disposal the methods that relate to its type, and also to its superclasses and interfaces. The Student object above offers the methods defined in the classes Person and Object.
- In the last exercise we wrote a new toString implementation for Student to override the method that it inherits from Person.
- The class Person had already overriden the toString method it inherited from the class Object.
> If we handle an object by some other type than its actual type, which version of the object's method is called?

> In the following example, we'll have two students that we refer to by variables of different types. Which version of the toString method will be executed: the one defined in **Object**, **Person**, or
**Student**?

```java
Student ollie = new Student("Ollie", "6381 Hollywood Blvd. Los Angeles 90028");
System.out.println(ollie);
Person olliePerson = new Student("Ollie", "6381 Hollywood Blvd. Los Angeles 90028");
System.out.println(olliePerson);
Object ollieObject = new Student("Ollie", "6381 Hollywood Blvd. Los Angeles 90028");
System.out.println(ollieObject);

Object alice = new Student("Alice", "177 Stewart Ave. Farmington, ME 04938");
System.out.println(alice);
```
```text
Ollie
  6381 Hollywood Blvd. Los Angeles 90028
  credits 0
Ollie
  6381 Hollywood Blvd. Los Angeles 90028
  credits 0
Ollie
  6381 Hollywood Blvd. Los Angeles 90028
  credits 0
Alice
  177 Stewart Ave. Farmington, ME 04938
  credits 0
```
- The method to be executed is chosen based on the actual type of the object, which means the class whose constructor is called when the object is created. If the method has no definition in that class, the version of the method is chosen from the class that is closest to the actual type in the inheritance hierarchy.
