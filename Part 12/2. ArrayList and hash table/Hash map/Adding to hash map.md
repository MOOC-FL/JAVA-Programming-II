#### Adding to hash map
- Let's implement the first version of the method `public void add(K key, V value)`, which is used to add `values` to the `hash map`.
- In this version we are not going to increase the size of the internal array when new values are added to the hash map.
- The method first calculates the hash value for the key, and uses it to determine the suitable index in the internal array.
- If there is no value in that index, we create a list into that index. After this the method goes through the list at the index, and looks for a key-value pair whose key matches the key of the key-value pair to be added.
-  If the matching key is found, the value related to it is updated to match the new value.
> Otherwise the method adds a new key-value pair in the list — in which case the number of stored values is also incremented by one.
```java
public void add(K key, V value) {
    int hashValue = Math.abs(key.hashCode() % values.length);
    if (values[hashValue] == null) {
        values[hashValue] = new List<>();
    }

    List<Pair<K, V>> valuesAtIndex = values[hashValue];

    int index = -1;
    for (int i = 0; i < valuesAtIndex.size(); i++) {
        if (valuesAtIndex.value(i).getKey().equals(key)) {
            index = i;
            break;
        }
    }

    if (index < 0) {
        valuesAtIndex.add(new Pair<>(key, value));
        this.firstFreeIndex++;
    } else {
        valuesAtIndex.value(index).setValue(value);
    }
}
```
- The method is quite complex, so let's divide it into smaller parts. The first part is responsible for finding the list related to the key, and the second part is responsible for finding the key on that list.
```java
private List<Pair<K, V>> getListBasedOnKey(K key) {
    int hashValue = Math.abs(key.hashCode() % values.length);
    if (values[hashValue] == null) {
        values[hashValue] = new List<>();
    }

    return values[hashValue];
}

private int getIndexOfKey(List<Pair<K, V>> myList, K key) {
    for (int i = 0; i < myList.size(); i++) {
        if (myList.value(i).getKey().equals(key)) {
            return i;
        }
    }

    return -1;
}
```
- Now we can write a somewhat clearer implementation of the method `public void add(K key, V value)`
```java
public void add(K key, V value) {
    List<Pair<K, V>> valuesAtIndex = getListBasedOnKey(key);
    int index = getIndexOfKey(valuesAtIndex, key);

    if (index < 0) {
        valuesAtIndex.add(new Pair<>(key, value));
        this.firstFreeIndex++;
    } else {
        valuesAtIndex.value(index).setValue(value);
    }
}
```
Here's the `add(K key, V value)` method broken down into tables:

#### Overall Method Flow

| Step | Code | Purpose |
|---|---|---|
| 1. Find the bucket | `List<Pair<K, V>> valuesAtIndex = getListBasedOnKey(key);` | Locates (or creates) the list at the key's hashed index |
| 2. Search for existing key | `int index = getIndexOfKey(valuesAtIndex, key);` | Checks whether this key already exists in that bucket |
| 3. Decide: add or update | `if (index < 0) { ... } else { ... }` | If key not found → add new pair. If found → update existing value |
| 4a. Add new pair | `valuesAtIndex.add(new Pair<>(key, value)); this.firstFreeIndex++;` | Inserts a brand-new key-value pair and increments the count of stored values |
| 4b. Update existing pair | `valuesAtIndex.value(index).setValue(value);` | Overwrites the value of the matching key — key itself stays the same |

#### Helper Method 1: `getListBasedOnKey`

| Step | Code | Purpose |
|---|---|---|
| Calculate hash index | `int hashValue = Math.abs(key.hashCode() % values.length);` | Same hashing logic as `get()` — determines which bucket the key belongs to |
| Check if bucket exists | `if (values[hashValue] == null)` | A `null` bucket means nothing has ever been stored there |
| Create bucket if missing | `values[hashValue] = new List<>();` | Lazily initializes the list only when needed (saves memory) |
| Return the bucket | `return values[hashValue];` | Hands back the list so the caller can search/insert into it |

#### Helper Method 2: `getIndexOfKey`

| Step | Code | Purpose |
|---|---|---|
| Loop through the bucket | `for (int i = 0; i < myList.size(); i++)` | Checks every pair currently stored in this bucket |
| Compare keys | `if (myList.value(i).getKey().equals(key))` | Uses `.equals()` for logical equality, not reference (`==`) |
| Return match position | `return i;` | Found it — return the index within the list |
| Return "not found" | `return -1;` | Signals to the caller that no pair with this key exists yet |

#### Decision Logic Summary

| Return value of `getIndexOfKey` | Meaning | What `add()` does |
|---|---|---|
| `-1` | Key doesn't exist in the bucket yet | Creates a new `Pair<>(key, value)` and adds it to the list; increments `firstFreeIndex` |
| `≥ 0` | Key already exists at that position | Calls `.setValue(value)` on the existing pair — key stays, only value changes |

#### Why splitting into helper methods is useful

| Benefit | Explanation |
|---|---|
| **Readability** | `add()` now reads almost like plain English: "find the bucket, find the index, add or update" |
| **Reusability** | `getListBasedOnKey()` and `getIndexOfKey()` encapsulate logic that's *also* needed by `get()` — avoids duplicating hash/search logic |
| **Single responsibility** | Each method does one clear job: one calculates *where*, the other calculates *if it's already there* |
| **Easier testing/debugging** | You can test hash-bucket resolution and key-searching independently of the add/update decision |

#### Important note flagged in the text

| Point | Detail |
|---|---|
| **No resizing yet** | This version does **not** grow the internal array as more items are added — meaning as `firstFreeIndex` grows, buckets will get more crowded and lookups will slow down over time |
| **Consequence** | This is explicitly called out as a limitation to be addressed later (resizing logic would typically trigger once load factor — e.g. 75% — is exceeded) |
#### Adding to hash table, part 2
- The way of adding to a hash table that was described above works partly.
- he greatest fault in the functionality is that the size of the internal array is not increased when the number of values grows too large.
> Let's add a growing functionality to the program that doubles the size of the internal array of the hash map.
- The growing operation should also place each value in the hash map into the newly created bigger array.
- Let's sketch the beginning of the growing functionality.
1. The responsible method should create a new array whose size is double that of the old array.
2.  After this it goes through the old array, index by index.
3.  The encountered key-value pairs are copied into the new array.
4.  Finally, the old array is replaced with the new one.
- Below there is a first version of how the method should work. We haven't implemented the copying yet.
```java
private void grow() {
    // crete a new array
    List<Pair<K, V>>[] newValues = new List[this.values.length * 2];

    for (int i = 0; i < this.values.length; i++) {
        // copy the values of the old array into the new one

    }

    // replace the old array with the new one
    this.values = newValues;
}
```
