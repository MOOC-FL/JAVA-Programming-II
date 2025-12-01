#### Going Through A Hash Map's Keys
- We may sometimes want to search for a book by a part of its title. The getmethod in the hash map is not applicable in this case as it is used to search by a specific key. Searching by a part of a book title is not possible with it.
- We can go through the values ​​of a hash map by using a for-each loop on the set returned by the `keySet()`method of the hash map.
- Below, a search is performed for all the books whose names contain the given string.
```java
public ArrayList<Book> getBookByPart(String titlePart) {
    titlePart = sanitizedString(titlePart);

    ArrayList<Book> books = new ArrayList<>();

    for(String bookTitle : this.directory.keySet()) {
        if(!bookTitle.contains(titlePart)) {
            continue;
        }

        // if the key contains the given string
        // we retrieve the value related to it
        // and add it tot the set of books to be returned

        books.add(this.directory.get(bookTitle));
    }

    return books;
}
```
> This way, however, we lose the speed advantage that comes with the hash map. The hash map is implemented in such a way that searching by a single key is extremely fast. The example above goes through all the book titles when looking for the existence of a single book using a particular key.


