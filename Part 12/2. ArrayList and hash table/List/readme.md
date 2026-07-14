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
Here's the complete implementation of the `List` class presented in tables for better understanding:

## Class Structure Overview

| **Component** | **Description** |
|---------------|-----------------|
| **Class Name** | `List<Type>` |
| **Type Parameter** | `Type` - Generic type for elements |
| **Private Fields** | `Type[] values` - Internal array<br>`int firstFreeIndex` - Tracks next empty position |
| **Constructor** | Initializes array with size 10 and `firstFreeIndex` to 0 |

---

## Method Summary Table

| **Method** | **Access** | **Return Type** | **Parameters** | **Description** |
|------------|------------|-----------------|----------------|-----------------|
| `add` | `public` | `void` | `Type value` | Adds value to list, grows array if full |
| `grow` | `private` | `void` | None | Increases array size by 1.5x |
| `contains` | `public` | `boolean` | `Type value` | Checks if value exists in list |
| `indexOfValue` | `public` | `int` | `Type value` | Returns index of value or -1 if not found |
| `remove` | `public` | `void` | `Type value` | Removes first occurrence of value |
| `moveToTheLeft` | `private` | `void` | `int fromIndex` | Shifts elements left from given index |
| `value` | `public` | `Type` | `int index` | Returns element at specified index |
| `size` | `public` | `int` | None | Returns number of elements in list |

---

## Constructor Details

| **Step** | **Action** | **Code** |
|----------|------------|----------|
| 1 | Create array of size 10 | `(Type[]) new Object[10]` |
| 2 | Initialize `firstFreeIndex` | `this.firstFreeIndex = 0` |

---

## Add Method Flow

| **Step** | **Condition** | **Action** |
|----------|---------------|------------|
| 1 | Check if array is full | `if (this.firstFreeIndex == this.values.length)` |
| 2 | If full | Call `grow()` method |
| 3 | Add value | `this.values[this.firstFreeIndex] = value` |
| 4 | Increment free index | `this.firstFreeIndex++` |

---

## Grow Method Details

| **Step** | **Action** | **Calculation** |
|----------|------------|-----------------|
| 1 | Calculate new size | `int newSize = this.values.length + this.values.length / 2` |
| 2 | Create new array | `Type[] newValues = (Type[]) new Object[newSize]` |
| 3 | Copy old elements | Loop through `this.values.length` |
| 4 | Replace reference | `this.values = newValues` |

**Growth Example:**
| **Old Size** | **New Size** | **Formula** |
|--------------|--------------|-------------|
| 10 | 15 | 10 + 10/2 = 15 |
| 15 | 22 | 15 + 15/2 = 22 (integer division) |
| 22 | 33 | 22 + 22/2 = 33 |

---

## Contains & IndexOfValue Comparison

| **Method** | **Return** | **Algorithm** | **Key Difference** |
|------------|------------|---------------|-------------------|
| `contains` | `boolean` | Calls `indexOfValue()` and checks if `>= 0` | Simplified - reuses index finder |
| `indexOfValue` | `int` | Linear search from 0 to `firstFreeIndex-1` | Returns position or -1 |

---

## Remove Method Process

| **Step** | **Method Called** | **Action** |
|----------|-------------------|------------|
| 1 | `indexOfValue(value)` | Find index of value to remove |
| 2 | Check result | If `< 0`, exit (value not found) |
| 3 | `moveToTheLeft(indexOfValue)` | Shift elements left from found index |
| 4 | Decrement | `this.firstFreeIndex--` |

### MoveToTheLeft Example

| **Before Removal** | **After Removal** | **Operation** |
|--------------------|-------------------|---------------|
| [A, B, C, D, E] | [A, C, D, E] | Remove B at index 1, shift C→index1, D→index2, E→index3 |

---

## Value Method (Get by Index)

| **Condition** | **Action** |
|---------------|------------|
| `index < 0` | Throw `ArrayIndexOutOfBoundsException` |
| `index >= firstFreeIndex` | Throw `ArrayIndexOutOfBoundsException` |
| Valid index | Return `this.values[index]` |

---

## Usage Example Table

| **Operation** | **Code** | **Output** |
|---------------|----------|------------|
| Create list | `List<String> myList = new List<>();` | - |
| Check contains | `System.out.println(myList.contains("hello"));` | `false` |
| Add element | `myList.add("hello");` | - |
| Check contains | `System.out.println(myList.contains("hello"));` | `true` |
| Find index | `int index = myList.indexOfValue("hello");` | `0` |
| Get value | `System.out.println(myList.value(index));` | `hello` |
| Remove element | `myList.remove("hello");` | - |
| Check contains | `System.out.println(myList.contains("hello"));` | `false` |

---

## Time Complexity Table

| **Method** | **Time Complexity** | **Explanation** |
|------------|-------------------|-----------------|
| `add` | O(1) amortized | Usually O(1), occasionally O(n) when growing |
| `grow` | O(n) | Copies all n elements to new array |
| `contains` | O(n) | Linear search until found |
| `indexOfValue` | O(n) | Linear search of all elements |
| `remove` | O(n) | Find (O(n)) + shift (O(n)) = O(n) |
| `moveToTheLeft` | O(n) | Shifts elements left from index |
| `value` | O(1) | Direct array access |
| `size` | O(1) | Returns stored count |

---

## Edge Cases & Assumptions

| **Scenario** | **Behavior** |
|--------------|--------------|
| Adding to full list | Auto-grows by 1.5x |
| Finding value not in list | Returns `-1` (contains returns `false`) |
| Removing non-existent value | Does nothing (no change to list) |
| Accessing invalid index | Throws `ArrayIndexOutOfBoundsException` |
| Adding `null` value | Possible but may cause issues with `equals()` |
| Empty list operations | `firstFreeIndex` = 0, methods handle gracefully |

---

## Full Class Implementation (Table Format)

| **Section** | **Code** |
|-------------|----------|
| **Fields** | `private Type[] values;`<br>`private int firstFreeIndex;` |
| **Constructor** | `public List() {`<br>&nbsp;&nbsp;`this.values = (Type[]) new Object[10];`<br>&nbsp;&nbsp;`this.firstFreeIndex = 0;`<br>`}` |
| **Add** | `public void add(Type value) {`<br>&nbsp;&nbsp;`if(this.firstFreeIndex == this.values.length) {`<br>&nbsp;&nbsp;&nbsp;&nbsp;`grow();`<br>&nbsp;&nbsp;`}`<br>&nbsp;&nbsp;`this.values[this.firstFreeIndex] = value;`<br>&nbsp;&nbsp;`this.firstFreeIndex++;`<br>`}` |
| **Grow** | `private void grow() {`<br>&nbsp;&nbsp;`int newSize = this.values.length + this.values.length / 2;`<br>&nbsp;&nbsp;`Type[] newValues = (Type[]) new Object[newSize];`<br>&nbsp;&nbsp;`for (int i = 0; i < this.values.length; i++) {`<br>&nbsp;&nbsp;&nbsp;&nbsp;`newValues[i] = this.values[i];`<br>&nbsp;&nbsp;`}`<br>&nbsp;&nbsp;`this.values = newValues;`<br>`}` |
| **Contains** | `public boolean contains(Type value) {`<br>&nbsp;&nbsp;`return indexOfValue(value) >= 0;`<br>`}` |
| **IndexOfValue** | `public int indexOfValue(Type value) {`<br>&nbsp;&nbsp;`for (int i = 0; i < this.firstFreeIndex; i++) {`<br>&nbsp;&nbsp;&nbsp;&nbsp;`if (this.values[i].equals(value)) {`<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;`return i;`<br>&nbsp;&nbsp;&nbsp;&nbsp;`}`<br>&nbsp;&nbsp;`}`<br>&nbsp;&nbsp;`return -1;`<br>`}` |
| **Remove** | `public void remove(Type value) {`<br>&nbsp;&nbsp;`int indexOfValue = indexOfValue(value);`<br>&nbsp;&nbsp;`if (indexOfValue < 0) {`<br>&nbsp;&nbsp;&nbsp;&nbsp;`return;`<br>&nbsp;&nbsp;`}`<br>&nbsp;&nbsp;`moveToTheLeft(indexOfValue);`<br>&nbsp;&nbsp;`this.firstFreeIndex--;`<br>`}` |
| **MoveToTheLeft** | `private void moveToTheLeft(int fromIndex) {`<br>&nbsp;&nbsp;`for (int i = fromIndex; i < this.firstFreeIndex - 1; i++) {`<br>&nbsp;&nbsp;&nbsp;&nbsp;`this.values[i] = this.values[i + 1];`<br>&nbsp;&nbsp;`}`<br>`}` |
| **Value** | `public Type value(int index) {`<br>&nbsp;&nbsp;`if (index < 0 || index >= this.firstFreeIndex) {`<br>&nbsp;&nbsp;&nbsp;&nbsp;`throw new ArrayIndexOutOfBoundsException("Index " + index + " outside of [0, " + this.firstFreeIndex + "]");`<br>&nbsp;&nbsp;`}`<br>&nbsp;&nbsp;`return this.values[index];`<br>`}` |
| **Size** | `public int size() {`<br>&nbsp;&nbsp;`return this.firstFreeIndex;`<br>`}` |
