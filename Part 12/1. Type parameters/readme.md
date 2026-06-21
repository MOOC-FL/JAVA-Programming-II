#### Type parameters
- Since we began using lists, we have given data structures the type of the values that we want them to store. For example, a list that stores strings has been defined as `ArrayList<String>`, and a `hash map` that stores **keys** and **values** as `Strings` has been defined as `HashMap<String, String>`.
> **How on Earth can you implement a class that can contain objects of a given type?**
- **Generics** relates to how classes that store objects can store objects of a freely chosen type. The choice is based on the generic type parameter in the definition of the classes,
- which makes it possible to choose the type(s) at the moment of the object's creation.
#####  Using generics is done in the following manner: 
   1.  after the name of the class,
   2.  follow it with a chosen number of type parameters.
   3.   Each of them is placed between the 'smaller than' and 'greater than' signs.
   4.    like this: `public class Class<TypeParameter1, TypeParameter2, ...>.`
   5. The type parameters are usually defined with a single character.
```java
public class Locker<T> {
    private T element;
    public void setValue(T element) {
        this.element = element;
    }
    public T getValue() {
        return element;
    }
}
```
- [ ] The definition `public class Locker<T>` indicates that the `Locker` class must be given a **type parameter** in its constructor.
- [ ]  After the constructor call is executed, all the variables stored in that object are going to be of the type that was given with the constructor
> Let's create a locker for storing strings.
```java
Locker<String> string = new Locker<>();
string.setValue(":)");
System.out.println(string.getValue());
 ```
```text
Sample output
:)
```
- In the program above, the **runtime implementation** of the `Locker` object named `string` looks like the following.
```java
public class Locker<String> {
    private String element;
    public void setValue(String element) {
        this.element = element;
    }
    public String getValue() {
        return element;
    }
}
```
> **Changing the type parameter allows for creating lockers that store objects of other types. You could store an integer in the following manner.**
```java
Locker<Integer> integer = new Locker<>();
integer.setValue(5);
System.out.println(integer.getValue());
```
```text
Sample output
5
```
- Similarly, here is how to create a locker for storing a **Random object**.
```java
Locker<Random> random = new Locker<>();
random.setValue(new Random());
System.out.println(random.getValue().nextDouble());
```
- [ ] There is no maximum on the number of type parameters, it's all dependent on the implementation.
- [ ]  The programmer could implement the following `Pair` class that is able to store two objects of specified types.
```java
public class Pair<T, K> {
    private T first;
    private K second;
    public void setValues(T first, K second) {
        this.first = first;
        this.second = second;
    }
    public T getFirst() {
        return this.first;
    }
    public K getSecond() {
        return this.second;
    }
}
```
- A significant portion of the Java data structures use type parameters, which enables them to handle different types of variables. `ArrayList`, for instance, receives a single `type parameter`, while `HashMap` receives two.
```java
List<String> strings = new ArrayList<>();
Map<String, String> keyValuePairs = new HashMap<>();
```
> From here on out when you see the type `ArrayList<String>`, you know that its **internal implementation** uses a `generic` type parameter. The same principle holds true for the interface Comparable, for example.
- Creating generic interfaces is very similar to creating generic classes.
> 
```java
public interface List<T> {
    void add(T value);
    T get(int index);
    T remove(int index);
}
```
- [ ] There are two ways for a class to implement a generic interface.
- [ ] One is to decide the type parameter in the definition of the class
- [ ] and the other is to define the implementing class with a type parameter as well.
> Below, the class `MovieList` defines the type parameter when it implements List. The MovieList is meant only for handling movies.
```java
public class MovieList implements List<Movie> {
    // object variables
    @Override
    public void add(Movie value) {
        // implementation
    }
    @Override
    public Movie get(int index) {
        // implementation
    }
    @Override
    public Movie remove(int index) {
        // implementation
    }
}
```
> The alternative is to use a type parameter in the class defintion, in which case the parameter is passed along to the interface. Now this concrete implementation of the interface remains generic.
```java
public class GeneralList<T> implements List<T> {
    // object variables
    @Override
    public void add(T value) {
        // implementation
    }
    @Override
    public T get(int index) {
        // implementation
    }
    @Override
    public T remove(int index) {
        // implementation
    }
}
```
> If you wanted, you could also use the existing ArrayList class to implement the GeneralList. The implementation would look roughly like this.
```java
import java.util.ArrayList;
public class GeneralList<T> implements List<T> {
    ArrayList<T> values;
    public GeneralList() {
        this.values = new ArrayList<>();
    }
    @Override
    public void add(T value) {
        this.values.add(value);
    }
    @Override
    public T get(int index) {
        retun this.values.get(index);
    }
    @Override
    public T remove(int index) {
        T value = this.values.get(index);
        this.values.remove(index);
        return value;
    }
}
```
| | **Way 1 — Fixed Type** | **Way 2 — Pass-Through Type** |
|---|---|---|
| **Declaration** | `class MovieList implements List<Movie>` | `class MyList<T> implements List<T>` |
| **Type decided** | At class definition | At instantiation |
| **Type parameter on class** | ❌ | ✅ `<T>` |
| **Reusable for other types** | ❌ | ✅ |
| **Instantiation** | `new MovieList()` | `new MyList<Movie>()` |
| **Add method signature** | `add(Movie movie)` | `add(T element)` |
| **Get method signature** | `Movie get(int index)` | `T get(int index)` |
| **Use case** | Domain-specific (Movies only) | General-purpose (any type) |
| **Java stdlib example** | `String implements Comparable<String>` | `ArrayList<E> implements List<E>` |
