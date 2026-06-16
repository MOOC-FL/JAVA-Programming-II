#### Part 12
- In the twelfth part of the course we will introduce type parameters and you will learn what `ArrayList<String>;` actually means.
-  You will learn to implement classes that use type parameters.
-  You will get to understand how ArrayList and HashMap are implemented
-  and you will implement your own versions as well.
-  . You will learn how to create random numbers, and practice using some Java tools to create random numbers.
-   You will learn how multidimensional data can be presented, and how to use multidimensional arrays.

| **Topic** | **Sub-Topics** | **Exercises/Quizzes** | **Key Concepts** |
|-----------|----------------|----------------------|-------------------|
| **1. Type Parameters** | - Generic classes<br>- Type safety<br>- `ArrayList<String>` meaning | - Quiz: Type parameters<br>- Programming: Hideout<br>- Programming: Pipe<br>- Quiz: Why doesn't the program work? | - `<T>` placeholder syntax<br>- Compile-time type checking<br>- Code reusability<br>- No casting needed |
| **2. ArrayList & Hash Table** | - ArrayList implementation<br>- HashMap implementation<br>- Internal data structures<br>- Dynamic resizing | - Quiz: Printing an array<br>- Programming: Sum these for me<br>- Programming: List (2 parts)<br>- Quiz: Copying hash map values<br>- Programming: Hash map (3 parts) | - ArrayList: O(1) get, O(1) amortized add<br>- HashMap: O(1) average put/get<br>- Hashing & collision handling<br>- Dynamic array growth |
| **3. Randomness** | - `Random` class<br>- `ThreadLocalRandom`<br>- Seeding<br>- Random number generation | - Programming: Numbers<br>- Programming: Die<br>- Programming: Lottery | - `nextInt(n)` method<br>- `nextDouble()`<br>- `nextBoolean()`<br>- Fixed seeds for reproducibility |
| **4. Multidimensional Data** | - 2D arrays<br>- Jagged arrays<br>- Magic squares<br>- Array traversal | - Programming: Array as a string<br>- Programming: Magic square (4 parts) | - Matrix representation<br>- Row/column indexing<br>- Nested loops<br>- Row-major order |
| **5. Summary** | - Learning outcomes review | - Quiz: Part twelve learning outcomes | - Recap of all topics<br>- Integration of concepts |


#### Exercise Details

| **Exercise** | **Type** | **Description** | **Key Skills** |
|--------------|----------|-----------------|----------------|
| **Hideout** | Programming | Implement a generic class that hides a value | Generics, encapsulation |
| **Pipe** | Programming | Create a generic pipe that transfers values | Generics, queue-like behavior |
| **Sum these for me** | Programming | Practice ArrayList operations | ArrayList, iteration |
| **List (2 parts)** | Programming | Implement a custom list with generics | ArrayList implementation, generics |
| **Hash map (3 parts)** | Programming | Build a hash map from scratch | Hashing, collisions, generics |
| **Numbers** | Programming | Generate random numbers | Random class, loops |
| **Die** | Programming | Simulate a dice with variable sides | Random, class design |
| **Lottery** | Programming | Draw unique random numbers | Random, collections |
| **Array as a string** | Programming | Convert 2D array to string format | 2D arrays, string conversion |
| **Magic square (4 parts)** | Programming | Generate and validate magic squares | 2D arrays, algorithms |


#### Time Requirements

| **Activity** | **Time Estimate** |
|--------------|-------------------|
| **Total for Part 12** | 10+ hours |
| **Type Parameters** | ~2 hours |
| **ArrayList & Hash Table** | ~3 hours |
| **Randomness** | ~1.5 hours |
| **Multidimensional Data** | ~2.5 hours |
| **Summary & Quizzes** | ~1 hour |


#### Common Errors & Solutions

| **Error** | **Cause** | **Solution** |
|-----------|-----------|--------------|
| `ClassCastException` | Using raw types without generics | Use type parameters `<T>` |
| `IndexOutOfBoundsException` | Accessing invalid array index | Check array bounds before access |
| `NullPointerException` | Uninitialized array elements | Initialize all elements before use |
| `ConcurrentModificationException` | Modifying collection while iterating | Use iterator or copy collection |
| No randomness variation | Using same seed or no seed | Use different seeds or no seed |


#### Tools & Technologies

| **Tool/Concept** | **Use Case** |
|------------------|--------------|
| `ArrayList<T>` | Dynamic array storage |
| `HashMap<K,V>` | Key-value pair storage |
| `Random` | Random number generation |
| `ThreadLocalRandom` | Concurrent random generation |
| 2D Arrays `[][]` | Matrix/grid data representation |
| Generics `<T>` | Type-safe reusable classes |


#### Learning Outcomes

| **Outcome** | **Assessment** |
|-------------|----------------|
| Understand generic classes and type parameters | Quizzes & Hideout/Pipe exercises |
| Implement custom ArrayList and HashMap | List & Hash map exercises |
| Generate random numbers for simulations | Numbers, Die, Lottery exercises |
| Work with multidimensional arrays | Array as string & Magic square exercises |
| Apply all concepts in integrated solutions | All programming exercises |
