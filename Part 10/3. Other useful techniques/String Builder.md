#### String Builder
- let`s look at the  following program
```java
String numbers = "";
for (int i = 1; i < 5; i++) {
    numbers = numbers + i;
}
System.out.println(numbers);
```
```text
Sample output
1234
```
- The program structure is straightforward. A string containing the number 1234 is created, and the string is then outputted.
- The program works, but there is a small problem invisible to the user. Calling `numbers + i ` creates a new string. Let's inspect the program line-by-line with the repetition block unpacked.
```java
String numbers = ""; // creating a new string: ""
int i = 1;
numbers = numbers + i; // creating a new string: "1"
i++;
numbers = numbers + i; // creating a new string: "12"
i++;
numbers = numbers + i; // creating a new string: "123"
i++;
numbers = numbers + i; // creating a new string: "1234"
i++;

System.out.println(numbers); // printing the string
```
- In the previous example, five strings are created in total.
- Let's look at the same program where a new line is added after each number.
```java
String numbers = "";
for (int i = 1; i < 5; i++) {
    numbers = numbers + i + "\n";
}
System.out.println(numbers);
```
```text
Sample output
1
2
3
4
```
- Each `+` -operation forms a new string. On the line `numbers + i + "\n";` a **string** is first created, after which another **string** is created joining a new line onto the previous **string**. Let's write this out as well.
```java
String numbers = ""; // creating a new string: ""
int i = 1;
// first creating the string "1" and then the string "1\n"
numbers = numbers + i + "\n";
i++;
// first creating the string "1\n2" and then the string "1\n2\n"
numbers = numbers + i + "\n"
i++;
// first creating the string "1\n2\n3" and then the string "1\n2\n3\n"
numbers = numbers + i + "\n"
i++;
// and so on
numbers = numbers + i + "\n"
i++;

System.out.println(numbers); // outputting the string
```
- In the previous example, a total of nine strings is created.
- String creation - although unnoticeable at a small scale - is not a quick operation. Space is allocated in memory for each string where the string is then placed. If the string is only needed as part of creating a larger string, performance should be improved.
> **Java's ready-made StringBuilder class** provides a way to concatenate strings without the need to create them. A `new StringBuilder object` is created with a `new StringBuilder()` call, and content is added to the object using the overloaded append method, i.e., there are variations of it for different types of variables. Finally, the **StringBuilder object** provides a string using the `toString method`.
- In the example below, only one string is created.
```java
StringBuilder numbers = new StringBuilder();
for (int i = 1; i < 5; i++) {
    numbers.append(i);
}
System.out.println(numbers.toString());
```
- Using StringBuilder is more efficient than creating strings with the `+ `operator.





