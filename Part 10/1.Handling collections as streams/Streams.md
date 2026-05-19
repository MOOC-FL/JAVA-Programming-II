#### Streams Methods
Stream methods can be roughly divided into two categories:
- [ ] intermediate operations intended for processing elements ​​
- [ ] terminal operations that end the processing of elements.
>  Both of the `filter` and `mapToInt` methods shown in the previous example are intermediate operations.
- Intermediate operations return a value that can be further processed - you could, in practice, have an infinite number of intermediate operations chained sequentially `(& separated by a dot)`.
- On the other hand, the `average` method seen in the previous example is a terminal operation. A terminal operation returns a value to be processed, which is formed from, for instance, stream elements.
<img src="https://github.com/MOOC-FL/Media/blob/main/Java%20Programming%202/part10.1-stream.webp"></img>

| Step | Component / Method | Description |
|------|--------------------|-------------|
| (1) | Starting point | A **list with values** (source collection) |
| (2) | `stream()` method | Creates a **stream** of the list’s valuesThe values ​​are then dealt with individually. |
| (3) | `filter()` method | Removes values that **fail a condition** from the stream |
| (4) | `map()` method | **Transforms** each value from one form to another |
| (5) | `collect()` method | Gathers the processed stream values into a **collection** (e.g., a list) |
> Underneath is a program of the example depicted in the image above. In this example stream, a new ArrayList list is created to which values ​​are added. This is done in the last line `.collect(Collectors.toCollection(ArrayList::new));`.
```java
List<Integer> list = new ArrayList<>();
list.add(3);
list.add(7);
list.add(4);
list.add(2);
list.add(6);

ArrayList<Integer> values = list.stream()
    .filter(value -> value > 5)
    .map(value -> value * 2)
    .collect(Collectors.toCollection(ArrayList::new));
```
