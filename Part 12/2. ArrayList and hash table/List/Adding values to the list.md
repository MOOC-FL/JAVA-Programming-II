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
- 
