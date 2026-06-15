#### Class diagrams
- A class diagram is a diagram used in designing and modeling software to describe classes and their relationships. Class diagrams enable us to model software in a high level of abstraction and without having to look at the source code.
- Classes in a class diagram correspond with classes in the source code. The diagram shows the names and attributes of the classes, connections between the classes, and sometimes also the methods of the classes.
<img src="https://github.com/MOOC-FL/Media/blob/main/Java%20Programming%202/java_class_diagram.svg" ></img>
> Next we will get familiar with creating and reading class diagrams using UML. Using a unified modeling language ensures that class diagrams drawn by different people can be read and understood by everyone familiar with the language.


<img src="https://github.com/MOOC-FL/Media/blob/main/Java%20Programming%202/composition_vs_inheritance.svg" ></img>

In OOP, `extends` and `implements` serve different purposes:

#### `extends` — Inheritance
Used when a class **inherits from another class** (or an interface extends another interface). It means "is a type of."

- Inherits all fields and methods from the parent
- Can override parent methods
- A class can only extend **one** class (single inheritance in most languages)

```java
class Animal {
    void eat() { System.out.println("eating..."); }
}

class Dog extends Animal {
    void bark() { System.out.println("barking..."); }
}
// Dog inherits eat() AND adds bark()
```

#### `implements` — Contract/Interface
Used when a class **fulfills the contract of an interface**. It means "can do these things."

- The class **must** provide implementations for all interface methods
- A class can implement **multiple** interfaces
- No inherited code — just a promise to define the methods

```java
interface Swimmable {
    void swim();
}
interface Flyable {
    void fly();
}

class Duck implements Swimmable, Flyable {
    public void swim() { System.out.println("swimming..."); }
    public void fly()  { System.out.println("flying..."); }
}
```

#### Key Differences

| | `extends` | `implements` |
|---|---|---|
| Used with | Classes (or interfaces) | Interfaces only |
| Inherits code? | Yes | No (just the contract) |
| Multiple allowed? | No (one parent) | Yes (many interfaces) |
| Must override? | No (optional) | Yes (all methods) |
| Purpose | Reuse & specialize behavior | Guarantee a capability |

#### When to use which?

- Use **`extends`** when there's a true "is-a" relationship and you want to reuse/specialize existing behavior (e.g., `Cat extends Animal`).
- Use **`implements`** when you want to guarantee a class has certain capabilities, regardless of its hierarchy (e.g., `Duck implements Flyable, Swimmable`).

> **Note:** In languages like Python and C++, there's no `implements` keyword — interfaces are replaced by abstract classes, and multiple inheritance handles both cases. The `extends`/`implements` distinction is most explicit in Java, TypeScript, and C#.
