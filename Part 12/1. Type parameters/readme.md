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
