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
- Functions that handle stream elements ​​cannot change values ​​of variables outside of the function. This has to do with how static methods behave - during a method call, there is no access to any variables outside of the method. With functions, the values ​​of variables outside the function can be read, assuming that those values of those variables do not change in the program.
- The program below demonstrates the situation in which a function attempts to make use of a variable outside the function. It doesn't work.
```java
// initializing a scanner and a list to which values are read
Scanner scanner = new Scanner(System.in);
List<String> inputs = new ArrayList<>()

// reading inputs
while (true) {
    String row = scanner.nextLine();
    if (row.equals("end")) {
        break;
    }

    inputs.add(row);
}

int numberOfMappedValues = 0;

// determining the number of values divisible by three
long numbersDivisibleByThree = inputs.stream()
    .mapToInt(s -> {
        // variables declared outside of an anonymous function cannot be used, so this won't work
        numberOfMappedValues++;
        return Integer.valueOf(s);
    }).filter(value -> value % 3 == 0)
    .count();
```





























