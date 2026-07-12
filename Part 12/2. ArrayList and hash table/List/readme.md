#### Lists
- [ ] Let's examine one way to implement the Java ArrayList data structure.
- [ ] Java ArrayList uses an array.
- [ ] The type of the elements in the array is defined by the type parameter given to the ArrayList.
- [ ]  Due to this we can add nearly any type of data to a list.
- [ ]  Java List offers multiple methods, but right now `add`, `contains`, `remove` and `get` are most relevant for us.
```java
ArrayList<String> strings = new ArrayList<>();
System.out.println(strings.contains("Hello!"));
strings.add("Hello!");
System.out.println(strings.contains("Hello!"));
strings.remove("Hello!");
System.out.println(strings.contains("Hello!"));
```
```text
Sample output
false
true
false
```
