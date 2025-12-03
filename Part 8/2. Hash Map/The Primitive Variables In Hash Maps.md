#### Primitive Variables In Hash Maps
- A hash map expects that only reference-variables are added to it (in the same way that ذArrayListذ does).
Java converts **primitive variables** to their corresponding **reference-types** when using any Java's built in data structures (such as `ArrayList` and `HashMap`).
Although the value `1` can be represented as a value of the primitive `int` variable, its type should be defined as `Integer` when using ArrayLists and HashMaps.
```java
HashMap<Integer, String> hashmap = new HashMap<>(); // works
hashmap.put(1, "Ole!");
HashMap<int, String> map2 = new HashMap<>(); // doesn't work
```
- A hash map's key and the object to be stored are always reference-type variables. If you want to use a primitive variable as a key or value, there exists a reference-type version for each one. A few have been introduced below.
- Java converts primitive variables to reference-types automatically as they are added to either a HashMap or an ArrayList. This automatic conversion to a reference-type variable is termed `auto-boxing` in Java, i.e. putting something in a box automatically. The automatic conversion is also possible in the other direction.
```java
int key = 2;
HashMap<Integer, Integer> hashmap = new HashMap<>();
hashmap.put(key, 10);
int value = hashmap.get(key);
System.out.println(value);
```
- The following examples describes a class used for counting the number of vehicle number-plate sightings. Automatic type conversion takes place in the `addSighting` and `timesSighted` methods.
```java
public class registerSightingCounter {
    private HashMap<String, Integer> allSightings;

    public registerSightingCounter() {
        this.allSightings = new HashMap<>();
    }

    public void addSighting(String sighted) {
        if (!this.allSightings.containsKey(sighted)) {
            this.allSightings.put(sighted, 0);
        }

        int timesSighted = this.allSightings.get(sighted);
        timesSighted++;
        this.allSightings.put(sighted, timesSighted);
    }

    public int timesSighted(String sighted) {
        return this.allSightings.get(sighted);
    }
}
```
- There is, however, some risk in type conversions
-  If we attempt to convert a `null` reference - a sighting not in HashMap, for instance - to an integer, we witness a `java.lang.reflect.InvocationTargetException error.`
-   Such an error may occur in the `timesSighted` method in the example above - if the `allSightings` hash map does not contain the value being searched, it returns a `null` reference and the conversion to an integer fails.
-   When performing automatic conversion, we should ensure that the value to be converted is not null. For example, the `timesSighted` method in the program program should be fixed in the following way. ->
```java
public int timesSighted(String sighted) {
    return this.allSightings.getOrDefault(sighted, 0);
}
```
> The `getOrDefault` method of the HashMap searches for the key passed to it as a parameter from the HashMap. If the key is not found, it returns the value of the second parameter passed to it. The one-liner shown above is equivalent in its function to the following.

 ```java
public int timesSighted(String sighted) {
    if (this.allSightings.containsKey(sighted)) {
        return this.allSightings.get(sighted);
    }

    return 0;
}
```
- Let's make the `addSighting` method a little bit neater. In the original version, 0 is set as the value of the sighting count in the hash map if the given key is not found. We then get retrieve the count of the sightings, increment it by one, and the previous value of the sightings is replaced with the new one by adding the incremented count back into the hash map. A part of this can also be replaced with the `getOrDefault` method.
```java
public class registerSightingCounter {
    private HashMap<String, Integer> allSightings;

    public registerSightingCounter() {
        this.allSightings = new HashMap<>();
    }

    public void addSighting(String sighted) {
        int timesSighted = this.allSightings.getOrDefault(sighted, 0);
        timesSighted++;
        this.allSightings.put(sighted, timesSighted);
    }

    public int timesSighted(String sighted) {
        return this.allSightings.getOrDefault(sighted, 0);
    }
```
