#### The List Interface
- The List interface defines the basic functionality related to lists. Because the ArrayList class implements the Listinterface, one can also use it through the Listinterface.
```java
List<String> strings = new ArrayList<>();
strings.add("string objects inside an arraylist object!");
```
- As we can see fom the `Java API` of `List`, there are many classes that implement the `List` interface. One list that is familiar to computer scientists is a linked list .
- A linked list can be used through the `List` interface exactly the same way as an object created from `ArrayList`.
```java
List<String> strings = new LinkedList<>();
strings.add("string objects inside a linkedlist object!");
```
- From the perspective of the user, both implementations of the `List` interface work the same way. The interface abstracts their inner functionality. The internal structures of `ArrayList` and `LinkedList` differ quite a bit. `ArrayList` saves objects to an array where fetching an object with a specific index is very fast. On the other hand `LinkedList` constructs a `list` where each element contains a reference to the next element in the `list`. When one searches for an object by index in a `linked list`, one has to go though the list from the beginning until the index.
- One can see noticeable performance differences between list implementations if the lists are big enough. The strength of a linked list is that adding to it is always fast. ArrayList, on the other hand, is backed by an array, which needs to be resized each time it gets full. Resizing the array requires creating a new array and copying the values ​​from the old array to the new one. On the other hand, searching objects by index is much faster in an array list compared to a linked list.
>  For the problems that you Encounter during this course you should almost always choose ArrayList. However, "interface programming" is beneficial: implement your programs so that you'll use the data structures through the interfaces.



 
