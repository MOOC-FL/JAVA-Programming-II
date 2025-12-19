#### Class inheritance
- Classes are used to clarify the concepts of the problem domain in object-oriented programming. Every class we create adds functionality to the programming language. This functionality is needed to solve the problems that we encounter. An essential idea behind object-oriented programming is that **solutions rise from the interactions between objects which are created from classes**.
- An object in object-oriented programming is an independent unit that has a state, which can be modified by using the methods that the object provides. Objects are used in cooperation; each has its own area of responsibility. For instance, our user interface classes have so far made use of `Scanner` objects.
- Every Java class extends the class Object, which means that every class we create has at its disposal all the methods defined in the Object class. If we want to change how these methods are defined in Object function, they must be overriden by defining a new implementation for them in the newly created class. The objects we create receive the methods `equals` and `hashCode`, among others, from the Object class.
  > Every class derives from `Object`, but it's also possible to derive from other classes. When we examine the API (Application Programming Interface) of Java's `ArrayList`, we notice that `ArrayList` has the **superclass** `AbstractList`. `AbstractList`, in turn, has the class Object as its superclass.
 ``` 
  java.lang.Object
 |__
  java.util.AbstractCollection<E>
  |__
    java.util.AbstractList<E>
    |__
       java.util.ArrayList<E>
  ```
- Each class can directly extend only one class. However, a class indirectly inherits all the properties of the classes it extends. 
> So the `ArrayList` class derives from the class `AbstractList`, and indirectly derives from the classes `AbstractCollection` and `Object`.
- So `ArrayList` has at its disposal all the variables and methods of the classes `AbstractList`, `AbstractCollection`, and `Object`.
- You use the keyword `extends` to inherit the properties of a class. The class that receives the properties is called the subclass, and the class whose properties are inherited is called the superclass.
  - Let's take a look at a car manufacturing system that manages car parts. A basic component of part management is the class `Part`, which defines the identifier, the manufacturer, and the description.
```java
public class Part {

    private String identifier;
    private String manufacturer;
    private String description;

    public Part(String identifier, String manufacturer, String description) {
        this.identifier = identifier;
        this.manufacturer = manufacturer;
        this.description = description;
    }

    public String getIdentifier() {
        return identifier;
    }

    public String getDescription() {
        return description;
    }

    public String getManufacturer() {
        return manufacturer;
    }
}
```
  - One part of the car is the engine. As is the case with all parts, the engine, too, has a manufacturer, an identifier, and a description. In addition, each engine has a type: for instance, an internal combustion engine, an electric motor, or a hybrid engine.
    > The traditional way to implement the class `Engine`, ***without using inheritance***, would be this.
```java
public class Engine {

    private String engineType;
    private String identifier;
    private String manufacturer;
    private String description;

    public Engine(String engineType, String identifier, String manufacturer, String description) {
        this.engineType = engineType;
        this.identifier = identifier;
        this.manufacturer = manufacturer;
        this.description = description;
    }

    public String getEngineType() {
        return engineType;
    }

    public String getIdentifier() {
        return identifier;
    }

    public String getDescription() {
        return description;
    }

    public String getManufacturer() {
        return manufacturer;
    }
}
```
> We notice a significant amount of overlap between the contents of `Engine` and `Part`. It can confidently be said the `Engine` is a special case of `Part`. **The Engine is a Part**, but it also has properties that a `Part` does not have, which in this case means the engine type.
- Let's recreate the class `Engine` and, this time, use ***inheritance*** in our implementation. We'll create the class `Engine` which **inherits** the class `Part`: **an engine is a special case of a part**.
```java
public class Engine extends Part {

    private String engineType;

    public Engine(String engineType, String identifier, String manufacturer, String description) {
        super(identifier, manufacturer, description);
        this.engineType = engineType;
    }

    public String getEngineType() {
        return engineType;
    }
}
```
- The class definition `public class Engine extends Part` indicates that the class `Engine` inherits the functionality of the class `Part`. We also define an object variable `engineType` in the class `Engine`.
- The constructor of the Engine class is worth some consideration. On its first line we use the keyword `super` to call the constructor of the superclass. The call `super(identifier, manufacturer, description)` calls the constructor `public Part(String identifier, String manufacturer, String description)` which is defined in the class Part. Through this process the object variables defined in the superclass are initiated with their initial values. After calling the superclass constructor, we also set the proper value for the object variable `engineType`.

> ***The `super` call bears some resemblance to the `this` call in a constructor; `this` is used to call a constructor of `this` class, while `super` is used to call a constructor of the superclass. If a constructor uses the constructor of the superclass by calling `super` in it, the super call must be on the first line of the constructor. `This` is similar to the case with calling this (must also be the first line of the constructor).***
- Since the class `Engine` extends the class `Part`, it has at its disposal all the methods that the class `Part` offers. You can create instances of the class `Engine` the same way you can of any other class.
```java
Engine engine = new Engine("combustion", "hz", "volkswagen", "VW GOLF 1L 86-91");
System.out.println(engine.getEngineType());
System.out.println(engine.getManufacturer());
```
> As you can see, the class `Engine` has all the methods that are defined in the class `Part`.



`
