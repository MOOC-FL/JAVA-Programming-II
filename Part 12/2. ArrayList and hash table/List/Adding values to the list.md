#### Adding values to the list
- Let's add the method `public void add(A value)`, which enables adding values to the list. We have to add an int variable to keep track of the first empty index in the array.
```java
public class List<Type> {
    private Type[] values;
    private int firstFreeIndex;
    public List() {
        this.values = (Type[]) new Object[10];
        this.firstFreeIndex = 0;
    }
    public void add(Type value) {
        this.values[this.firstFreeIndex] = value;
        this.firstFreeIndex++; // same as this.firstFreeIndex = this.firstFreeIndex + 1;
    }
}
```
- Now we can add values to the list — or at least we can create a list and call the add method. We cannot test if the values are actually saved to the list yet.
```java
List<String> myList = new List<>();
myList.add("hello");
myList.add("world");
```
#### Adding values to a list part 2
- There is a small problem with the `add` method. The problem occurs when the following code is run:
```java
List<String> myList = new List<>();
for (int i = 0; i < 11; i++) {
    myList.add("hello");
}
```
```text
Sample output
Exception in thread "main" java.lang.ArrayIndexOutOfBoundsException: 10
    at dataStructures.List.add(List.java:14)
    at dataStructures.Program.main(Program.java:8)
```
- The size of the list does not grow. One of the benefits of the `ArrayList` class is, that it grows as needed — programmers do not have to worry about the list getting full.
- Let's add the functionality for increasing the size of the List.
- [ ] The size of the List increases if user tries to add a value to a full list.
- [ ] The size of the List is increased by creating a new, larger, array to which the values from the old array are copied to.
- [ ] After this the old array is abandoned and the List starts to use the new array.
- [ ] The size of the array is determined in Java with the formula `oldSize + oldSize / 2`. 
> Let's use the same formula in our implementation. We'll create a new method `grow` for increasing the size of the array. The method is available only for other methods in the class (it is `private`).
```java
private void grow() {
    int newSize = this.values.length + this.values.length / 2;
    Type[] newValues = (Type[]) new Object[newSize];
    for (int i = 0; i < this.values.length; i++) {
        newValues[i] = this.values[i];
    }

    this.values = newValues;
}
```
1. The implementation creates a new array whose size is 1.5 times the size of the old array.
2. After this all the elements of the old array are copied into the new one, and finally the value of the object variable `values` is set to the new array.
3.  The **automatic Java garbage collector** removes the old array at some point, now that there are no longer any references to it.
> ***Let's modify the `add` method so that the size of the array grows when needed.***
```java
public void add(Type value) {
    if(this.firstFreeIndex == this.values.length) {
        grow();
    }
    this.values[this.firstFreeIndex] = value;
    this.firstFreeIndex++;
}
```
Now we can add almost unlimited amount of elements to the List.

