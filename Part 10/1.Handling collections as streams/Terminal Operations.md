#### Terminal Operations
- Let's take a look at four terminal operations: the `count` method for counting the number of values on a list, the `forEach` method for going a through list values, the `collect` method for gathering the list values ​​into a data structure, and the `reduce` method for combining the list items.
- The `count` method informs us of the number of values in the stream as a `long`-type variable.
```java
List<Integer> values = new ArrayList<>();
values.add(3);
values.add(2);
values.add(17);
values.add(6);
values.add(8);

System.out.println("Values: " + values.stream().count());
```
```text
Values: 5
```
- The `forEach` method defines what is done to each list value and terminates the stream processing. In the example below, we first create a list of numbers, after which we only print the numbers that are divisible by `two`.
```java
List<Integer> values = new ArrayList<>();
values.add(3);
values.add(2);
values.add(17);
values.add(6);
values.add(8);

values.stream()
    .filter(value -> value % 2 == 0)
    .forEach(value -> System.out.println(value));
```
```text
Sample output
2
6
8
```
- You can use the `collect` method to collect **stream** values into another collection.
- The example below creates a new list containing only positive values.
- The `collect` method is given as a parameter to the `Collectors` object to which the stream values ​​are collected - for example, calling `Collectors.toCollection(ArrayList::new)` creates a new ArrayList object that holds the collected values.
```java
List<Integer> values = new ArrayList<>();
values.add(3);
values.add(2);
values.add(-17);
values.add(-6);
values.add(8);

ArrayList<Integer> positives = values.stream()
    .filter(value -> value > 0)
    .collect(Collectors.toCollection(ArrayList::new));

positives.stream()
    .forEach(value -> System.out.println(value));
```
```text
Sample output
3
2
8
```
