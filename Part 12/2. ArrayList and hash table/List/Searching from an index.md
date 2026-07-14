#### Searching from an index
- Let's add method `public Type value(int index)`, which returns the value in the given index of the List. If the user searches for a value in an index outside of the Array, `IndexOutOfBoundsException` is thrown.
```java
public Type value(int index) {
    if (index < 0 || index >= this.firstFreeIndex) {
        throw new ArrayIndexOutOfBoundsException("Index " + index + " outside of [0, " + this.firstFreeIndex + "]");
    }

    return this.values[index];
}
```
- This method would be easier to use, if the user had information about the indexes of the values. Let's modify the method `indexOfValue(Type value) `so it can be used by everyone, so it is `public` instead of `private`.
```java
public int indexOfValue(Type value) {
    for (int i = 0; i < this.firstFreeIndex; i++) {
        if (this.values[i].equals(value)) {
            return i;
        }
    }

    return -1;
}
```
```java
List<String> myList = new List<>();
System.out.println(myList.contains("hello"));
myList.add("hello");
System.out.println(myList.contains("hello"));
int index = myList.indexOfValue("hello");
System.out.println(index);
System.out.println(myList.value(index));
myList.remove("hello");
System.out.println(myList.contains("hello"));
```
```text
Sample output
false
true
0
hello
false
```
