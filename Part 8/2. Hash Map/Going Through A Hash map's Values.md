#### Going Through A Hash map's Values
- The preceding functionality could also be implemented by going through the hash map's values. The set of values ​​can be retrieved with the hash map's values​​()method. This set of values ​​can also be iterated over with a for-each loop.
```java
public ArrayList<Book> getBookByPart(String titlePart) {
    titlePart = sanitizedString(titlePart);

    ArrayList<Book> books = new ArrayList<>();

    for(Book book : this.directory.values()) {
        if(!book.getName().contains(titlePart)) {
            continue;
        }

        books.add(book);
    }

    return books;
}
```
