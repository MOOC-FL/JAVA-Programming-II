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
> The `location` method is not meant for external use, which is why it is defined as protected. Subclasses will still be able to access the method. Manhattan distance means the distance between two points if you can only travel in the direction of the coordinate axes. It is used in many navigation algorithms, for example.
- A colored point is otherwise identical to a point, but it contains also a color that is expressed as a string. Due to the similarity, we can create a new class by extending the class Point.
```java
public class ColorPoint extends Point {

    private String color;

    public ColorPoint(int x, int y, String color) {
        super(x, y);
        this.color = color;
    }

    @Override
    public String toString() {
        return super.toString() + " color: " + color;
    }
}
```
- The class defines an object variable in which we store the color.
- The coordinates are already defined in the superclass.
- We want the string representation to be the same as the Point class, but to also include information about the color.
- The overriden `toString` method calls the `toString` method of the superclass and adds to it the color of the point.
- Next, we'll add a few points to a list. Some of them are "normal" while others are color points.
- At the end of the example, we'll print the points on the list
- For each point, the toString to be executed is determined by the actual type of the point
- even though the list knows all the points by the `Point` type.
```java
public class Main {
    public static void main(String[] args) {
        ArrayList<Point> points = new ArrayList<>();
        points.add(new Point(4, 8));
        points.add(new ColorPoint(1, 1, "green"));
        points.add(new ColorPoint(2, 5, "blue"));
        points.add(new Point(0, 0));

        for (Point p: points) {
            System.out.println(p);
        }
    }
}
```
```text
(4, 8) distance 12
(1, 1) distance 2 color: green
(2, 5) distance 7 color: blue
(0, 0) distance 0
```
- We also want to include a three-dimensional point in our program. Since it has no color information, let's derive it from the class `Point`.
```java
public class Point3D extends Point {

    private int z;

    public Point3D(int x, int y, int z) {
        super(x, y);
        this.z = z;
    }

    @Override
    protected String location() {
        return super.location() + ", " + z;    // the resulting string has the form "x, y, z"
    }

    @Override
    public int manhattanDistanceFromOrigin() {
        // first ask the superclass for the distance based on x and y
        // and add the effect of the z coordinate to that result
        return super.manhattanDistanceFromOrigin() + Math.abs(z);
    }

    @Override
    public String toString() {
        return "(" + this.location() + ") distance " + this.manhattanDistanceFromOrigin();
    }
}
```
- So a three-dimensional point defines an object variable that represents the third dimension, and overrides the methods `location`, `manhattanDistanceFromOrigin`, and `toString` so that they also account for the third dimension. Let's now expand the previous example and add also three-dimensional points to the list.
```java
public class Main {

    public static void main(String[] args) {
        ArrayList<Point> points = new ArrayList<>();
        points.add(new Point(4, 8));
        points.add(new ColorPoint(1, 1, "green"));
        points.add(new ColorPoint(2, 5, "blue"));
        points.add(new Point3D(5, 2, 8));
        points.add(new Point(0, 0));


        for (Point p: points) {
            System.out.println(p);
        }
    }
}
```
```text
(4, 8) distance 12
(1, 1) distance 2 color: green
(2, 5) distance 7 color: blue
(5, 2, 8) distance 15
(0, 0) distance 0
```
- We notice that the toString method in Point3D is exactly the same as the toString of Point. Could we save some effort and not override toString? The answer happens to be yes! The Point3D class is refined into this:
```java
public class Point3D extends Point {

    private int z;

    public Point3D(int x, int y, int z) {
        super(x, y);
        this.z = z;
    }

    @Override
    protected String location() {
        return super.location() + ", " + z;
    }

    @Override
    public int manhattanDistanceFromOrigin() {
        return super.manhattanDistanceFromOrigin() + Math.abs(z);
    }
}
```
- What happens in detail when we call the toString method of a three-dimensional point? The execution advances in the following manner.
  1. Look for a definition of toString in the class Point3D. It does not exist, so the superclass is next to be examined.
  2. Look for a definition of toString in the superclass point. It can be found, so the code inside the implementation of the method is executed
     1. so the exact code to be executed is return `"("+this.location()+") distance "+this.manhattanDistanceFromOrigin();`
     2. the method `location` is executed first
     3. look for a definition of `location` in the class `Point3D`. It can be found, so its code is executed.
     4. This `location` calls the `location` of the `superclass` to calculate the result
     5. next we look for a definition of `manhattanDistanceFromOrigin` in the `Point3D` class. It's found and its code is then executed
     6. Again, the method calls the similarly named method of the superclass during its execution
> As we can see, the sequence of events caused by the method call has multiple steps. The principle, however, is clear: The definition for the method is first searched for in the class definition of the actual type of the object. If it is not found, we next examine the superclass. If the definition cannot be found there, either, we move on to the superclass of this superclass, etc...


















