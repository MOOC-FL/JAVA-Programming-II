#### Creating a hash map
- A hash map contains an array of lists.
- Each value on the list is a pair (described in the previous section
> that contains a key and a value.
- A hash map also knows the number of the values.
- Here we have at our disposal the previously created class `List`.
```java
public class HashMap<K, V> {

    private List<Pair<K, V>>[] values;
    private int firstFreeIndex;

    public HashMap() {
        this.values = new List[32];
        this.firstFreeIndex = 0;
    }
}
```
#### Retrieving a value
- Let's implement a method called `public V get(K key)`. It can be used to search for a `value` based on a `key`.
- The method begins by calculating a hash value for the key, and using it to figure out which is the relevant index of the internal array of the hash map.
- The hash value is calculated with the `hashCode` method that each object has.
-  Then `modulo` (remainder of division operation) is used for ensuring that the index stays within the size boundaries of the internal array.
-  If there is no list in the calculated index, no key-value pairs have been added to that index.
-  This means that there are no key-value pairs with this key that have been stored.
-   In this case we'll return the `null` reference.
> Otherwise, the program goes through the list at the index, and we compare the parameter key to the key of every key-value pair on that list.

 >  If some of the keys matches the parameter key, the method returns the value of that key-value pair. Otherwise we cannot find a suitable key (and related value), so the method returns the value null.
```java
public V get(K key) {
    int hashValue = Math.abs(key.hashCode() % this.values.length);
    if (this.values[hashValue] == null) {
        return null;
    }

    List<Pair<K, V>> valuesAtIndex = this.values[hashValue];

    for (int i = 0; i < valuesAtIndex.size(); i++) {
        if (valuesAtIndex.value(i).getKey().equals(key)) {
            return valuesAtIndex.value(i).getValue();
        }
    }

    return null;
}
```
> **Why not implement hash map as a list?**

> The main principle of the `hash map` is that the `key-value` pairs are divided into small `sets` with the help of `hash values`.

> In this case a search based on `key` demands only going through a very small number of `key-value pairs` — assuming that the `hash` values are calculated in a sensible manner.

> If the `hash value` is always the same — e.g. 1 — the internal implementation of a `hash map` is similar to a list — all the values are on the same `list`. If the `hash values` are sufficiently random, the different values are as evenly distributed to the different `lists` as possible.

> Hash maps also grow the size of their internal array if the number of values becomes large enough (typically 75% of the size of the array). Typically a hash map that contains millions of key-value pairs only contains a few key-value pairs in each index. The practical consequence is that discovering if a key-value pair exists, we only need to calculate the hash value and examine a few objects — this is very significantly faster than going through a single list that contains the entirety of stored values.


| Step | Code | Purpose |
|---|---|---|
| 1. Calculate hash | `int hashValue = Math.abs(key.hashCode() % this.values.length);` | Gets the key's hash code, then uses modulo to fit it within the bounds of the internal array (`values.length`). `Math.abs` ensures a negative hash doesn't produce a negative index. |
| 2. Check if index is empty | `if (this.values[hashValue] == null)` | If no list exists at this index, no pairs were ever stored there. |
| 3. Return null (empty index) | `return null;` | Key can't exist if the bucket itself is empty. |
| 4. Get the list at index | `List<Pair<K, V>> valuesAtIndex = this.values[hashValue];` | Retrieves the bucket (list of key-value pairs) that may contain the key. |
| 5. Loop through the list | `for (int i = 0; i < valuesAtIndex.size(); i++)` | Since multiple keys can hash to the same index (collision), we must check each pair individually. |
| 6. Compare keys | `if (valuesAtIndex.value(i).getKey().equals(key))` | Uses `.equals()` (not `==`) to check logical equality of keys, not just reference equality. |
| 7. Return matching value | `return valuesAtIndex.value(i).getValue();` | If a match is found, return its associated value immediately. |
| 8. Return null (no match) | `return null;` | If the loop finishes without finding the key, it isn't in the map. |

#### Quick summary of the logic flow

| Scenario | Result |
|---|---|
| Bucket at hash index is empty | `null` |
| Bucket exists, key found in list | Corresponding value |
| Bucket exists, key not found in list | `null` |


#### Core Comparison

| Aspect | Plain List | Hash Map |
|---|---|---|
| **Storage structure** | One single list holding all key-value pairs | Internal array of "buckets," each holding a small list |
| **Search process** | Must check every element until found | Calculate hash → jump straight to the right bucket → check only that bucket's small list |
| **Search time (avg)** | O(n) — linear, grows with total data size | O(1) — constant, roughly independent of total data size |
| **Distribution of data** | N/A — everything is together | Spread across many buckets based on hash value |
| **Growth handling** | No structural change as it grows | Resizes internal array when it gets too full |

#### Why hashing helps: the "bad hash" scenario

| Situation | What happens | Result |
|---|---|---|
| Hash function always returns the same value (e.g. `1`) | Every key-value pair lands in the same bucket | Hash map degrades into **exactly a list** — no performance benefit |
| Hash function returns well-distributed values | Pairs are spread evenly across many buckets | Each bucket only holds a handful of items — fast lookups |

This is the key insight: **a hash map is only as good as its hash function.** With a poor hash function, you get all the overhead of a hash map with none of the speed benefits — it behaves identically to a list.

#### Why resizing matters (the load factor)

| Term | Meaning |
|---|---|
| **Load factor** | Ratio of (number of stored items) to (size of internal array) |
| **Typical threshold** | ~75% (0.75) |
| **What triggers resizing** | When stored items exceed 75% of array capacity |
| **What resizing does** | Creates a bigger array, recalculates positions, redistributes pairs |
| **Why it matters** | Keeps the *average number of items per bucket* small and roughly constant, even as total data grows |

#### Putting it together: millions of entries

| Data size | Items per bucket (with good hashing + resizing) | Search cost |
|---|---|---|
| 100 | ~1–2 | Check 1–2 items |
| 1,000,000 | ~1–2 | Check 1–2 items |

This is the practical payoff: **the cost of a lookup doesn't meaningfully grow even as the map grows into the millions** — because the number of buckets grows proportionally to the number of items. Compare that to a list, where searching 1,000,000 items in the worst case means checking all 1,000,000 of them.

#### Summary takeaway

| If you used... | You would get... |
|---|---|
| A list | Simple, but O(n) lookups — slow at scale |
| A hash map with a bad hash function | Same as a list — no real benefit |
| A hash map with a good hash function + resizing | O(1) average lookups — the whole point of using a hash map |

So the hash map's speed advantage isn't magic — it comes entirely from **(1)** a hash function that spreads keys out evenly, and **(2)** resizing to keep each bucket small as the map grows. Without both of those, you're just using a list with extra steps.
