> Below there is an algorithm that copies the values of a hash map to a new one. The algorithm is this.
```java
private void copy(List<Pair<K, V>>[] newOne, int fromIdx) {
    for (int i = 0; i < this.values[fromIdx].size(); i++) {
        Pair<K, V> value = this.values[fromIdx].value(i);

        int hashValue = Math.abs(value.getKey().hashCode() % newOne.length);
        if(newOne[hashValue] == null) {
            newOne[hashValue] = new List<>();
        }

        newOne[hashValue].add(value);
    }
}
```
 - Explain the functionality of this method line by line. For each row, describe why it exists (i.e. what is its concrete role), and what happens on the row.
Let's go through this method line by line:

---

#### Method signature
```java
private void copy(List<Pair<K, V>>[] newOne, int fromIdx)
```
- **Why it exists**: This is a helper method to copy all key-value pairs from one bucket (at index `fromIdx`) of the current hash map's internal array into a new array `newOne`.  
- **What happens**: The method is `private`, so it's only used inside this class. It takes a destination array of lists (`newOne`) and an index (`fromIdx`) indicating which bucket of the current map to copy from.

---

#### Line 1: `for (int i = 0; i < this.values[fromIdx].size(); i++) {`
- **Why it exists**: To iterate over every element (key-value pair) stored in the linked list (or `List`) at position `fromIdx` in the current map's internal array `this.values`.  
- **What happens**: The loop runs from `i = 0` up to (but not including) the size of that list. Each iteration processes one `Pair`.

---

#### Line 2: `Pair<K, V> value = this.values[fromIdx].value(i);`
- **Why it exists**: To retrieve the i‑th element from the list in the source bucket, so it can be re‑hashed and placed into the new array.  
- **What happens**: It fetches the `Pair` object at index `i` from the list stored at `this.values[fromIdx]`.

---

#### Line 3: `int hashValue = Math.abs(value.getKey().hashCode() % newOne.length);`
- **Why it exists**: To compute the new bucket index for this key in the destination array (`newOne`), based on the key's hash code and the new array's length. This is necessary because the array size may have changed (e.g., during resizing), so the old index is no longer valid.  
- **What happens**:  
  - `value.getKey().hashCode()` gets the key's hash code.  
  - `% newOne.length` reduces it to a valid array index.  
  - `Math.abs()` ensures the result is non‑negative (since `%` can be negative in Java for negative numbers).  
  - The result is stored in `hashValue`.

---

#### Line 4: `if(newOne[hashValue] == null) {`
- **Why it exists**: To check whether the bucket at the computed index already has a list; if not, we must create one before adding the pair. This avoids `NullPointerException`.  
- **What happens**: It tests if the entry at `newOne[hashValue]` is `null`.

---

#### Line 5: `newOne[hashValue] = new List<>();`
- **Why it exists**: To instantiate a new list for that bucket so that pairs can be added.  
- **What happens**: A new `List` (likely `ArrayList` or `LinkedList`, depending on the implementation) is created and assigned to `newOne[hashValue]`.

---

#### Line 6: `newOne[hashValue].add(value);`
- **Why it exists**: To actually store the key‑value pair in the new array at the correct bucket, preserving it for future lookups.  
- **What happens**: The `add` method appends the `Pair` object to the list at that bucket.

---

#### Closing brace `}`
- Ends the loop and the method.

---

#### Overall summary
This method re‑hashes all entries from one bucket of the old internal array and inserts them into the new array, handling collisions by creating lists as needed. It is typically used during **resize** operations (e.g., when the load factor is exceeded) to redistribute entries into a larger array.
