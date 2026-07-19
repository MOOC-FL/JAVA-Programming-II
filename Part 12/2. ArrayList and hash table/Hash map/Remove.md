#### Remove
- Let's give the hash map the functionality to remove a `key-value` pair based on key.
- The removal functionality returns `null` if the value cannot be found, and otherwise it will remove the value that is paired with the key to be removed.
- We can take advantage of the method we've already implemented in the removing method. Explain to yourself (out loud) how the method described below concretely works.
```java
public V remove(K key) {
    List<Pair<K, V>> valuesAtIndex = getListBasedOnKey(key);
    if (valuesAtIndex.size() == 0) {
        return null;
    }

    int index = getIndexOfKey(valuesAtIndex, key);
    if (index < 0) {
        return null;
    }

    Pair<K, V> pair = valuesAtIndex.value(index);
    valuesAtIndex.remove(pair);
    return pair.getValue();
}
```


