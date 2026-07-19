#### Hash map
- [ ] Hash map is implemented as an array,
- [ ] in which every element includes a list.
- [ ] The lists contain `(key, value)` pairs.
- [ ] The user can search from the hash map based on the key,
- [ ]  and they can also add new key-value pairs into it.
- [ ]  Each key can appear at most once in the hash map.
> The functioning of the hash map is based on the hash value of the key.
- When a new `(key, value)` pair is stored in a hash map, we calculate a hash value based on the key to be stored
- The `hash value` decides the index of the internal array that will be used for storing.
- The `(key, value)` pair is stored in the list that can be found at that index.
> Let's sketch out how a hash map functions.
#### Key-value pair
1. Let's start by creating the class `Pair` that represents a `key-value pair`.
2. We want to make the `hash map` as general as possible, so the types of the key and the value are determined at **run-time**.
3. The `Pair` class contains a key and a value, as well as the related get methods.
4. The **generic** types K and V are named so after the words 'key' and 'value'.
```java
public class Pair<K, V> {

    private K key;
    private V value;

    public Pair(K key, V value) {
        this.key = key;
        this.value = value;
    }

    public K getKey() {
        return key;
    }

    public V getValue() {
        return value;
    }

    public void setValue(V value) {
        this.value = value;
    }
}
```
Creating key-value pairs is straightforward.
```java
Pair<String, Integer> pair = new Pair<>("one", 1);
System.out.println(pair.getKey() + " -> " + pair.getValue());
```
```text
Sample output
one -> 1

```
