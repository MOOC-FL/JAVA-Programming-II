#### Polymorphism
- Regardless of the type of the variable, the method that is executed is always chosen based on the actual type of the object.
- Objects are polymorphic, which means that they can be used via many different variable types.
-  The executed method always relates to the actual type of the object.
> ***This phenomenon is called polymorphism.***
- Let's examine polymorphism with another example.
- You could represent a point in two-dimensional coordinate system with the following class:
```java
public class Point {

    private int x;
    private int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int manhattanDistanceFromOrigin() {
        return Math.abs(x) + Math.abs(y);
    }

    protected String location(){
        return x + ", " + y;
    }

    @Override
    public String toString() {
        return "(" + this.location() + ") distance " + this.manhattanDistanceFromOrigin();
    }
}
```


