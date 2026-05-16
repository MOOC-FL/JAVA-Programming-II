#### Lambda Expressions
- Stream values ​​are handled by methods related to streams.Methods that handle values ​​get a function as a parameter that determines what is done with each element. What the function does is specific to the method in question. 
- For instance, the `filter` method used for filtering elements is provided a function which returns a **boolean** `true` or `false`, depending on whether to keep the value in the `stream` or not.
- The `mapToInt` method used for transformation is, on the other hand, provided a function which converts the value to an integer, and so on.
> Why are the functions written in the form `value -> value > 5?`
- The expression above, i.e., a `lambda expression`, is shorthand provided by Java for anonymous methods that do not have an "owner", i.e., they are not part of a class or an interface.
The function contains both the parameter definition and the function body. The same function can be written in several different ways. See below.
```java
// original
*stream*.filter(value -> value > 5).*furtherAction*

// is the same as
*stream*.filter((Integer value) -> {
    if (value > 5) {
        return true;
    }

    return false;
}).*furtherAction*
```
- The same can be written explicitly so that a static method is defined in the program, which gets used within the function passed to the stream as a parameter.
```java
public class Screeners {
    public static boolean greaterThanFive(int value) {
        return value > 5;
    }
}
```
```java
// original
*stream*.filter(value -> value > 5).*furtherAction*

// is the same as
*stream*.filter(value -> Screeners.greaterThanFive(value)).*furtherAction*
```
- The function can also be passed directly as a parameter. The syntax found below `Screeners::greaterThanFive` is saying:
> "use the static `greaterThanFive` method that's in the Screeners class".
```java
 // is the same as
*stream*.filter(Screeners::greaterThanFive).*furtherAction*
  ```




























