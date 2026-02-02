##### Handling collections as streams
- Let's get to know collections, such as lists, as streams of values. Stream is a way of going through a collection of data such that the programmer determines the operation to be performed on each value. No record is kept of the index or the variable being processed at any given time.
- With streams, the programmer defines a sequence of events that is executed for each value in a collection. An event chain may include dumping some of the values, converting values ​​from one form to another, or calculations. A stream does not change the values ​​in the original data collection, but merely processes them. If you want to retain the transformations, they need to be compiled into another data collection.
- Let's begin to understand the usage of streams through a concrete example. Consider the following problem:
> *Write a program that reads input from a user and prints statistics about those inputs. When the user enters the string "end", the reading is stopped. Other inputs are numbers in string format. When you stop reading inputs, the program prints the number of positive integers divisible by three, and the average of all values.*
```java
// We initialise the scanner and the list into which the input is read
Scanner scanner = new Scanner(System.in);
List<String> inputs = new ArrayList<>();

// reading inputs
while (true) {
    String row = scanner.nextLine();
    if (row.equals("end")) {
        break;
    }

    inputs.add(row);
}

// counting the number of values divisible by three
long numbersDivisibleByThree = inputs.stream()
    .mapToInt(s -> Integer.valueOf(s))
    .filter(number -> number % 3 == 0)
    .count();

// working out the average
double average = inputs.stream()
    .mapToInt(s -> Integer.valueOf(s))
    .average()
    .getAsDouble();

// printing out the statistics
System.out.println("Divisible by three " + numbersDivisibleByThree);
System.out.println("Average number: " + average);
```
- Let's take a closer look at the part of the program above where the inputs are dealt as **streams**.
```java
// counting the number of values divisible by three
long numbersDivisibleByThree = inputs.stream()
    .mapToInt(s -> Integer.valueOf(s))
    .filter(number -> number % 3 == 0)
    .count();
```
- [ ] A stream can be formed from any object that implements the Collection interface (e.g., ArrayList, HashSet, HashMap, ...) with the stream() method.
- [ ] The string values ​​are then converted ("mapped") to integer form using the stream's mapToInt(value -> conversion) method.
- [ ] The conversion is implemented by the `valueOf` method of the Integer class, which we've used in the past.
- [ ] We then use the `filter (value -> filter condition)` method to filter out only those numbers that are divisible by three for further processing.
- [ ] Finally, we call the stream's `count()` method, which counts the number of elements in the stream and returns it as a `long` type variable.
- Let's now look at the part of the program that calculates the average of the list elements.
```java
// working out the average
double average = inputs.stream()
    .mapToInt(s -> Integer.valueOf(s))
    .average()
    .getAsDouble();
```
- [ ] Calculating the average is possible from a stream that has the `mapToInt` method called on it.
- [ ]  A stream of integers has an `average` method that returns an OptionalDouble-type object.
- [ ]  The object has `getAsDouble()` method that returns the average of the list values as a `double` type variable.
Based on your notes, here is the information organized into a structured table. I've clarified the "Method/Purpose" column and separated the core "Assumption/Requirement" from additional context.


| Method / Purpose | Key Assumption / Requirement | Additional Context & Common Usage |
| :--- | :--- | :--- |
| **Stream Formation**<br>`stream()` | Called on an object that implements the `Collection` interface (e.g., `ArrayList`, `HashSet`). | This is the entry point for the Stream API. You typically call this on a collection and then chain subsequent operations. |
| **Mapping to Integer Stream**<br>`mapToInt(value -> ...)` | Transforms a stream of objects into an `IntStream` (a stream of primitive integers). | Used to perform specialized integer operations (like `sum()`, `average()`). For `String` to `int`, use `mapToInt(Integer::parseInt)` or `mapToInt(Integer::valueOf)`. |
| **Filtering Values**<br>`filter(value -> ...)` | The lambda expression (`->`) must return a `boolean` value. | Elements are passed through only if the condition evaluates to `true`. If `false`, the element is discarded from the downstream. |
| **Calculating Average**<br>`average()` | Must be called on a stream of primitive numbers, typically an `IntStream`, `LongStream`, or `DoubleStream`. | Returns an `OptionalDouble` (to handle empty streams safely). Use `getAsDouble()` to get the value, but check `isPresent()` first or use `orElse()`. |
| **Counting Elements**<br>`count()` | Can be called on any type of stream (`Stream<T>`, `IntStream`, etc.). | Returns the number of elements in the stream as a `long`. This is a **terminal operation** that consumes the stream. |

1.  **Source:** Get a `Stream` from a `Collection` via `.stream()`.
2.  **Intermediate Operations:** Transform (`mapToInt`) or filter (`filter`) the stream. These can be chained and are lazy.
3.  **Terminal Operation:** Produce a result via `average()`, `count()`, `sum()`, `collect()`, etc. This ends the stream processing.

