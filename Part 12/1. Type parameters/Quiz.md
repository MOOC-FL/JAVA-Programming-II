#### Examine the following class
```java
public class Info<A, B> {
    A a;
    B b;

    public Info(A a, B b) {
        this.a = a;
        this.b = b;
    }

    public String toString() {
        return this.a.toString() + " " + this.b.toString();
    }
}
```
- What does the following program print?
```java
Info<String, Integer> i1 = new Info<>("Pow", 3);
Info<Object, Object> i2 = new Info<>("Hey", i1);
System.out.println(i1);
System.out.println(i2);
```
```text
Sample Output
Pow 3
Hey Pow 3
```
#### Tracing the Output
#### `i1` — `Info<String, Integer>`

```java
Info<String, Integer> i1 = new Info<>("Pow", 3);
```

- `a = "Pow"` (String)
- `b = 3` (Integer)

`toString()` returns:
```
"Pow" + " " + "3"  →  "Pow 3"
```
#### `i2` — `Info<Object, Object>`

```java
Info<Object, Object> i2 = new Info<>("Hey", i1);
```

- `a = "Hey"` (String, stored as Object)
- `b = i1` (the `Info<String,Integer>` object above, stored as Object)

`toString()` returns:
```
"Hey".toString() + " " + i1.toString()
      ↓                       ↓
    "Hey"               "Pow 3"    ← calls i1's toString()!
```

Result: `"Hey Pow 3"`

#### Final Output

```
Pow 3
Hey Pow 3
```
#### Key insight

When `i2.toString()` calls `this.b.toString()`, even though `b` is declared as `Object`, the **actual runtime type** is `Info<String, Integer>` — so Java's polymorphism kicks in and calls `Info`'s overridden `toString()`, which returns `"Pow 3"`.
