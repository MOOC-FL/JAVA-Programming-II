> `ArrayList` and `HashMap` are commonly used **data structures** in programming. We are now going to take a look at their actual implementation. First we'll remind ourselves of how to use an `array`, after which we're going to build a **data structure** called `List`, imitating `ArrayList`. Then we'll make use of the `List` to implement the **data structure HashTable**.
#### A brief recap of arrays
- [ ]  An array is an object that contains a limited number of places for values.
- [ ]  The length (or size) of an array is the number of places in it;
> in other words,how many values can be stored in the array.
- [ ] **The size of an array is always predetermined:**
- [ ] it is chosen when the array is created, and cannot be changed later.
- [ ] The array type is defined with square brackets preceded by the type of the elements in **the array (typeOfElements[])**.
- [ ] An array is created with the `new` call, followed by the type of the elements in that array, square brackets, and the number of elements in the array places inside the square brackets.
```java
int[] numbers = new int[3];
String[] strings = new String[5];
```
- [ ] The elements of the array are referred to by the indexes. Below we create an integer array of size three, after which we set values to indexes 0 and 2. Then we print those values.
```java
int[] numbers = new int[3];
numbers[0] = 2;
numbers[2] = 5;

System.out.println(numbers[0]);
System.out.println(numbers[2]);
```
```text
Sample output
2
5
```
- Setting a single value to a certain position is done similarly to setting a value to a regular variable, just that when placing the value in an array, you use the index to indicate the position.
- To discover the size of an array you can use the public object variable `length` that arrays have. Examining the elements one by one can be accomplished with a for loop, for instance.
```java
int[] numbers = new int[4];
numbers[0] = 42;
numbers[1] = 13;
numbers[2] = 12;
numbers[3] = 7;

System.out.println("There are " + numbers.length + " elements in the array.");

for (int i = 0; i < numbers.length; i++) {
    System.out.println(numbers[i]);
}
```
```text
There are 4 elements in the array.
42
13
12
7
```
> What does the following program print?
```java
int[] numbers = new int[4];
numbers[0] = 4;
numbers[1] = 3;
numbers[2] = 2;
numbers[3] = 1;
numbers[1] = numbers[2] * numbers[3];
numbers[2] = 4;

for (int i = 0; i < numbers.length; i++) {
    System.out.print(numbers[i] + " ");
}
// Select the correct answer
```
> **The Answer is : 4 2 4 1**
