#### Approximate Comparison With HashMap
- In addition to `equals`, the `hashCode` method can also be used for approximate comparison of objects .
- The method creates from the object a "hash code", ie, a number, that tells a bit about the object's content
- If two objects have the same hash value, they may be equal.
- On the other hand, if two objects have different hash values, they are certainly unequal.
> **Hash Codes**  : Hash codes are used in HashMaps, for instance. HashMap's internal behavior is based on the fact that key-value pairs are stored in an array of lists based on the key's hash value. Each array index points to a list. The hash value identifies the array index, whereby the list located at the array index is traversed. The value associated with the key will be returned if, and only if, the exact same value is found in the list (equality comparison is done using the equals method). This way, the search only needs to consider a fraction of the keys stored in the hash map.
- So far, we've only used String and Integer-type objects as HashMap keys, which have conveniently had ready-made hashCodemethods implemented. Let's create an example where this is not the case: we'll continue with the books and keep track of the books that are on loan. We'll implement the book keeping with a HashMap. The key is the book and the value attached to the book is a string that tells the borrower's name:
```java
HashMap<Book, String> borrowers = new HashMap<>();

Book bookObject = new Book("Book Object", 2000, "...");
borrowers.put(bookObject, "Pekka");
borrowers.put(new Book("Test Driven Development", 1999, "..."), "Arto");

System.out.println(borrowers.get(bookObject));
System.out.println(borrowers.get(new Book("Book Object", 2000, "...")));
System.out.println(borrowers.get(new Book("Test Driven Development", 1999, "...")));
```
- We find the borrower when searching for the same object that was given as a key to the hash map's `put`method.
-  However, when searching by the exact same book but with a different object, a borrower isn't found, and we get the null reference instead. The reason lies in the default implementation of the`hashCode` method in the Objectclass.
- The default implementation creates a `hashCodevalue` based on the object's reference, which means that books having the same content that are nevertheless different objects get different results from the hashCode method. As such, the object is not being searched for in the right place.

- For the HashMap to work in the way we want it to, that is, to return the borrower when given an object with the correct content (not necessarily the same object as the original key),
- the class that the key belongs to must overwrite the hashCodemethod in addition to the equalsmethod.
-  The method must be overwritten so that it gives the same numerical result for all objects with the same content. Also,
-   some objects with different contents may get the same result from the hashCode method. However, with the HashMap's performance in mind,
-    it is essential that objects with different contents get the same hash value as rarely as possible.
> We've previously used `String`objects as `HashMap` keys, so we can deduce that the `String`class has a well-functioning `hashCode`implementation of its own. We'll delegate , ie, transfer the **Computational responsibility** to the `String`object.
```java
public int hashCode() {
    return this.name.hashCode();
}

```
> The above solution is quite good. However, if `name` is `null` , we see an `NullPointerExceptionerror`. Let's fix this by Defining a condition: if the value of the namevariable is null , we'll return the year of publication as the hash value.
```java
public int hashCode() {
    if (this.name == null) {
        return this.published;
    }

    return this.name.hashCode();
}
```
- Now, all of the books that share a name are bundled into one group. Let's improve it further so that the year of publication is also taken into account in the hash value calculation that's based on the book title.
```java
public int hashCode() {
    if (this.name == null) {
        return this.published;
    }

    return this.published + this.name.hashCode();
}
```
- It's now possible to use the book as the hash map's key.
- This way the problem we faced earlier gets solved and the book borrowers are found:
```java
HashMap<Book, String> borrowers = new HashMap<>();

Book bookObject = new Book("Book Object", 2000, "...");
borrowers.put(bookObject, "Pekka");
borrowers.put(new Book("Test Driven Development",1999, "..."), "Arto");

System.out.println(borrowers.get(bookObject));
System.out.println(borrowers.get(new Book("Book Object", 2000, "...")));
System.out.println(borrowers.get(new Book("Test Driven Development", 1999)));
```
>> **Let's review the ideas once more:** for a class to be used as a HashMap's key, we need to define for it:
1. the `equals`method, so that all equal or approximately equal objects cause the comparison to return true and all false for all the rest
2. the `hashCodemethod`, so that as few objects as possible end up with the same hash value




