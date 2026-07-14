#### Removing a value
- [ ] We can now add values to the List, and check if the List contains a value
- [ ] Now we will implement the functionality for removing a value from the List.
- [ ]  Let's implement the method `public void remove(Type value)`, which `removes` one value type `value`.
- Simple implementation would be as follows:
```java
public void remove(Type value) {
    for (int i = 0; i < this.firstFreeIndex; i++) {
        if (value == this.values[i] || this.values[i].equals(value)) {
            this.values[i] = null;
            this.firstFreeIndex--;
            return;
        }
    }
}
```
> The above implementation is however problematic, because it leaves "empty" slots to the List, which would lead to the contains method not working.
```java
public void remove(T value) {
    boolean found = false;
    for (int i = 0; i < this.firstFreeIndex; i++) {
        if (found) {
            this.values[i - 1] = this.values[i];
        } else if (value == this.values[i] || this.values[i].equals(value)) {
            this.firstFreeIndex--;
            found = true;
        }
    }
}
```
1. We are not really satisfied with the solution above, because it does too many things at the same time.
2. The method looks for an element and moves elements around.
> We will split the functionality into two methods: `private int indexOfValue(Type value)`, which searches for the index of the value given to it as a parameter, and `private void moveToTheLeft(int fromIndex)`, which moves the elements above the given index to the left.
- [ ] First let's implement the method `private int indexOfValue(Type value)`, which searches for the index of the given value.
- [ ] The method returns -1 if the value is not found.
```java
private int indexOfValue(Type value) {
    for (int i = 0; i < this.firstFreeIndex; i++) {
        if (this.values[i].equals(value)) {
            return i;
        }
    }

    return -1;
}
```
- [ ] Then we will implement the method `private void moveToTheLeft(int fromIndex)`, which moves values from the given index one place to the left.
```java
private void moveToTheLeft(int fromIndex) {
    for (int i = fromIndex; i < this.firstFreeIndex - 1; i++) {
        this.values[i] = this.values[i + 1];
    }
}
```
- [ ] Now we can implement the method `remove` using these two methods.
```java
public void remove(Type value) {
    int indexOfValue = indexOfValue(value);
    if (indexOfValue < 0) {
        return; // not found
    }

    moveToTheLeft(indexOfValue);
    this.firstFreeIndex--;
}
```
> **On the effectiveness of the method**

>  The method describes above copies each element after the removed element one place to the left. Think about the effectiveness of this method when the List is used as a queue.

> We will discuss the effectiveness of this method — and ways to make it more effective — in the courses Datastructures and algorithms and Design and analysis of algorithms.

- The class List now contains some repeated code. The method `contains` is very similiar to the method `indexOfValue`. Let's modify the method `contains` so that it uses the method `indexOfValue`.
```java
public boolean contains(Type value) {
    return indexOfValue(value) >= 0;
}
```
- Now we have a List, which has the methods `add`, `contains`, and `remove`. The List also grows in size when needed. The implementation of the List could of course be improved by for example adding functionality for decreasing the size of the List if the number of values in it decreases.
```java
List<String> myList = new List<>();
System.out.println(myList.contains("hello"));
myList.add("hello");
System.out.println(myList.contains("hello"));
myList.remove("hello");
System.out.println(myList.contains("hello"));
```
```text
Sample output
false
true
false
```
