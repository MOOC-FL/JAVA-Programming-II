#### Checking the existence of a value
> Next we'll create the method `public boolean contains(Type value)`, which we use to check whether the List contains a value or not.
- [ ]  We will make use of the fact that each Java object — no matter its type — inherits the Object class (or is type Object).
- [ ]  Due to this, each object has the method `public boolean equals(Object object)`, which we can use to check equality.
- [ ]  The variable `firstFreeIndex` **contains** the number of elements in the array. We can implement the `contains` method so, that it only checks the indexes in the array which contain a value.
```java
public boolean contains(Type value) {
    for (int i = 0; i < this.firstFreeIndex; i++) {
        if (this.values[i].equals(value)) {
            return true;
        }
    }

    return false;
}
```
We can now inspect the elements in the List.
```java
List<String> myList = new List<>();
System.out.println(myList.contains("hello"));
myList.add("hello");
System.out.println(myList.contains("hello"));
```
```text
Sample output
false
true
```
- The method above assumes, that the user will not add a `null` reference to the list, and that the equals method checks that the value given to it as a parameter is not `null`.

