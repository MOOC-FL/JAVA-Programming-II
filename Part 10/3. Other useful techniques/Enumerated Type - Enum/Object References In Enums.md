#### Object References In Enums
- Enumerated types may contain object reference variables. The values ​​​​of the reference variables should be set in an internal constructor of the class Defining the enumerated type, ie, within a constructor having an `private` access modifier.
-  Enum type classes cannot have a public constructor.
```java
public enum Color {
    // constructor parameters are defined as
    // the constants are enumerated
    RED("#FF0000"),
    GREEN("#00FF00"),
    BLUE("#0000FF");

    private String code;        // object reference variable

    private Color(String code) { // constructor
        this.code = code;
    }

    public String getCode() {
        return this.code;
    }
}
```
> In the following example, we have an enum `Color` that contains the constants RED, GREEN and BLUE. The constants have been declared with object reference variables referring to their color codes :
- The enum `Color` can be used like this:
```java
System.out.println(Color.GREEN.getCode());
```
```text
#00FF00

```
