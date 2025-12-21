#### Interfaces 
- We can use interfaces to define behavior that's required from a class, i.e., its methods. They're defined the same way that regular Java classes are, but "`public interface ...`" is used instead of "`public class ...` " at the beginning of the class.
-  Interfaces define behavior through method names and their return values. However, they don't always include the actual implementations of the methods. A visibility attribute on interfaces is not marked explicitly as they're always `public`.
>  Let's examine a Readable interface that describes readability.
```java
public interface Readable {
    String read();
}
```
