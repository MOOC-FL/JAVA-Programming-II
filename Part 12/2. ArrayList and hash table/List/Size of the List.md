#### Size of the List
- Lastly we will add a method for checking the size of the List. The size of the list can be determined by the variable `firstFreeIndex`.
```java
public int size() {
    return this.firstFreeIndex;
}
```
Now we can use a for-loop to go through the elements of the list.
```java
List<String> myList = new List<>();
myList.add("hello");
myList.add("world");

for(int i = 0; i < myList.size(); i++) {
    System.out.println(myList.value(i));
}
```
```text
Sample output
hello
world
```
