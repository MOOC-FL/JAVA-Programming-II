
#### What is a Stream Operation?

A **stream operation** is an action performed on a Java stream to process, transform, filter, or collect data from the stream. Stream operations are divided into two main categories:

#### 1. **Intermediate Operations** (Lazy)
These operations **transform** a stream into another stream. They don't produce a result immediately - they just set up the pipeline.

**Characteristics:**
- Return a new stream
- Can be chained together
- Don't execute until a terminal operation is called

**Examples:**
```java
.filter()    // Filters elements
.map()       // Transforms elements
.limit()     // Limits size
.sorted()    // Sorts elements
.distinct()  // Removes duplicates
.peek()      // Debugging
```

#### 2. **Terminal Operations** (Eager)

These operations **produce a result** or **side effect** and close the stream. After a terminal operation, the stream can no longer be used.

**Characteristics:**
- Produce a final result
- Trigger execution of all intermediate operations
- Close the stream

**Examples:**
```java
.collect()   // Collects into collection
.forEach()   // Performs action for each
.count()     // Returns number of elements
.reduce()    // Reduces to single value
.findFirst() // Returns first element
.anyMatch()  // Checks if any element matches
.toArray()   // Converts to array
```

#### Complete Example

```java
List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6);

numbers.stream()           // Create stream
    .filter(n -> n > 2)    // Intermediate operation
    .map(n -> n * 2)       // Intermediate operation
    .limit(3)              // Intermediate operation
    .forEach(System.out::println); // Terminal operation

// Output: 6, 8, 10
```

**Execution flow:**
1. Intermediate ops prepare the pipeline
2. Terminal op `forEach()` triggers execution
3. Each element flows through: `3→6`, `4→8`, `5→10`

#### Summary Table

| Type | Purpose | Returns | Examples |
|------|---------|---------|----------|
| **Intermediate** | Transform/pipeline | New Stream | `filter()`, `map()`, `sorted()` |
| **Terminal** | Produce result | Non-Stream (value, collection, void) | `collect()`, `count()`, `forEach()` |

**Key rule:** Streams are **lazy** - nothing happens until you call a **terminal operation**!


#### 1. Operation Types Overview

| Type | Characteristics | Execution | Return Type | Can Chain |
|------|----------------|-----------|-------------|------------|
| **Intermediate** | Lazy, transforms stream | When terminal op is called | Stream | Yes |
| **Terminal** | Eager, produces result | Immediately | Non-Stream (value/void) | No |


#### 2. Common Intermediate Operations

| Operation | Purpose | Example |
|-----------|---------|---------|
| `filter(Predicate)` | Keeps elements matching condition | `.filter(n -> n > 0)` |
| `map(Function)` | Transforms each element | `.map(n -> n * 2)` |
| `flatMap(Function)` | Flattens nested structures | `.flatMap(list -> list.stream())` |
| `sorted()` | Sorts elements | `.sorted()` |
| `distinct()` | Removes duplicates | `.distinct()` |
| `limit(long n)` | Limits to first n elements | `.limit(5)` |
| `skip(long n)` | Skips first n elements | `.skip(2)` |
| `peek(Consumer)` | Debugging (side effect) | `.peek(System.out::println)` |


#### 3. Common Terminal Operations

| Operation | Purpose | Return Type | Example |
|-----------|---------|-------------|---------|
| `collect(Collector)` | Collects into collection | Collection/Map | `.collect(Collectors.toList())` |
| `count()` | Returns number of elements | `long` | `.count()` |
| `forEach(Consumer)` | Performs action on each | `void` | `.forEach(System.out::println)` |
| `reduce(BinaryOperator)` | Reduces to single value | `Optional<T>` | `.reduce((a,b) -> a + b)` |
| `findFirst()` | Returns first element | `Optional<T>` | `.findFirst()` |
| `anyMatch(Predicate)` | Checks if any element matches | `boolean` | `.anyMatch(n -> n > 5)` |
| `allMatch(Predicate)` | Checks if all elements match | `boolean` | `.allMatch(n -> n > 0)` |
| `noneMatch(Predicate)` | Checks if no elements match | `boolean` | `.noneMatch(n -> n < 0)` |
| `toArray()` | Converts to array | `T[]` | `.toArray()` |


#### 4. Example Pipeline Structure

```java
list.stream()          // Create stream
    .filter(...)       // ⬅ Intermediate
    .map(...)          // ⬅ Intermediate
    .sorted()          // ⬅ Intermediate
    .limit(10)         // ⬅ Intermediate
    .collect(...);     // ⬅ Terminal (triggers execution)
```


Here is a **table summarizing Stream Operations** in Java:

#### 1. Operation Types Overview

| Type | Characteristics | Execution | Return Type | Can Chain |
|------|----------------|-----------|-------------|------------|
| **Intermediate** | Lazy, transforms stream | When terminal op is called | Stream | Yes |
| **Terminal** | Eager, produces result | Immediately | Non-Stream (value/void) | No |


#### 2. Common Intermediate Operations

| Operation | Purpose | Example |
|-----------|---------|---------|
| `filter(Predicate)` | Keeps elements matching condition | `.filter(n -> n > 0)` |
| `map(Function)` | Transforms each element | `.map(n -> n * 2)` |
| `flatMap(Function)` | Flattens nested structures | `.flatMap(list -> list.stream())` |
| `sorted()` | Sorts elements | `.sorted()` |
| `distinct()` | Removes duplicates | `.distinct()` |
| `limit(long n)` | Limits to first n elements | `.limit(5)` |
| `skip(long n)` | Skips first n elements | `.skip(2)` |
| `peek(Consumer)` | Debugging (side effect) | `.peek(System.out::println)` |


#### 3. Common Terminal Operations

| Operation | Purpose | Return Type | Example |
|-----------|---------|-------------|---------|
| `collect(Collector)` | Collects into collection | Collection/Map | `.collect(Collectors.toList())` |
| `count()` | Returns number of elements | `long` | `.count()` |
| `forEach(Consumer)` | Performs action on each | `void` | `.forEach(System.out::println)` |
| `reduce(BinaryOperator)` | Reduces to single value | `Optional<T>` | `.reduce((a,b) -> a + b)` |
| `findFirst()` | Returns first element | `Optional<T>` | `.findFirst()` |
| `anyMatch(Predicate)` | Checks if any element matches | `boolean` | `.anyMatch(n -> n > 5)` |
| `allMatch(Predicate)` | Checks if all elements match | `boolean` | `.allMatch(n -> n > 0)` |
| `noneMatch(Predicate)` | Checks if no elements match | `boolean` | `.noneMatch(n -> n < 0)` |
| `toArray()` | Converts to array | `T[]` | `.toArray()` |


#### 4. Example Pipeline Structure

```java
list.stream()          // Create stream
    .filter(...)       // ⬅ Intermediate
    .map(...)          // ⬅ Intermediate
    .sorted()          // ⬅ Intermediate
    .limit(10)         // ⬅ Intermediate
    .collect(...);     // ⬅ Terminal (triggers execution)
```


#### Key Rules

| Rule | Explanation |
|------|-------------|
| **Lazy evaluation** | Intermediate ops don't run until terminal op is called |
| **Single use** | Stream cannot be reused after terminal operation |
| **No storage** | Streams don't store data, they process from source | Key Rules

| Rule | Explanation |
|------|-------------|
| **Lazy evaluation** | Intermediate ops don't run until terminal op is called |
| **Single use** | Stream cannot be reused after terminal operation |
| **No storage** | Streams don't store data, they process from source |
