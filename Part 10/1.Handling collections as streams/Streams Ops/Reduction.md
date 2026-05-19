#### What it does
This calculates the **sum** of all elements in the stream.

#### How it works

The `reduce` operation takes two parameters:
1. **Identity value** (`0`) - the starting value and default if stream is empty
2. **Accumulator function** - `(previousSum, value) -> previousSum + value`

**Execution process:**
- Starts with `previousSum = 0`
- For each element `value` in the stream:
  - New `previousSum` = old `previousSum + value`
- Returns final `previousSum`

#### Example

```java
List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);

int sum = numbers.stream()
    .reduce(0, (previousSum, value) -> previousSum + value);

System.out.println(sum); // Output: 15
```

**Step-by-step execution:**
| Step | previousSum | value | new previousSum |
|------|-------------|-------|-----------------|
| Start | 0 | - | - |
| 1 | 0 | 1 | 1 |
| 2 | 1 | 2 | 3 |
| 3 | 3 | 3 | 6 |
| 4 | 6 | 4 | 10 |
| 5 | 10 | 5 | 15 |

#### Shorter versions

```java
// Method reference
.reduce(0, Integer::sum)

// For non-empty streams (no identity)
.reduce((a, b) -> a + b) // returns Optional<Integer>
```

#### Other common reductions

```java
// Product
.reduce(1, (a, b) -> a * b)

// Maximum
.reduce(Integer.MIN_VALUE, (a, b) -> a > b ? a : b)

// String concatenation
.reduce("", (a, b) -> a + b)
```

