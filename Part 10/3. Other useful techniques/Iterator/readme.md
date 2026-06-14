#### Iterator
- Let's look at the following `Hand` class that represents the set of cards that a player is holding:
```java
public class Hand {
    private List<Card> cards;

    public Hand() {
        this.cards = new ArrayList<>();
    }

    public void add(Card card) {
        this.cards.add(card);
    }

    public void print() {
        this.cards.stream().forEach(card -> {
            System.out.println(card);
        });
    }
}
```
- The `print` method of the class prints each card in the current hand.
- ArrayList and other `"object containers"` that implement the **Collection interface** implement the Iterable interface, and they can also be iterated over with the help of an iterator - an object specifically designed to go through a particular type of object collection. The following is a version of printing the cards that uses an iterator:
```java
public void print() {
    Iterator<Card> iterator = cards.iterator();

    while (iterator.hasNext()) {
        System.out.println(iterator.next());
    }
}
```
- [ ] The iterator is requested from the `cards` list containing cards.
- [ ] **The iterator can be thought of as a "finger" that always points to a particular object inside the list.**
- [ ] Initially it points to the first item, then to the next, and so on... until all the objects have been gone through with the help of the "finger".
#### The iterator offers a few methods.
- [ ] The `hasNext()` method is used to ask if there are any objects still to be **iterated** over.
- [ ]  If there are, the next object in line can be requested from the iterator using the `next()` method.
- [ ]  This method returns the next object in line to be processed and moves the iterator, or "finger", to point to the following object in the collection.
> The object reference returned by the iterator's next method can of course also be stored in a variable. As such, the print method could also be written in the following way.
```java
public void print(){
    Iterator<Card> iterator = cards.iterator();

    while (iterator.hasNext()) {
        Card nextInLine = iterator.next();
        System.out.println(nextInLine);
    }
}
```
- Let's now consider a use case for an iterator. We'll first approach the issue problematically to provide motivation for the coming solution. We attempt to create a method that removes cards from a given stream with a value lower than the given value.
```java
public class Hand {
    // ...

    public void removeWorst(int value) {
        this.cards.stream().forEach(card -> {
            if (card.getValue() < value) {
                cards.remove(card);
            }
        });
    }
}
```
Executing the method results in an error.
```text
Sample output
Exception in thread "main" java.util.ConcurrentModificationException
at ...
Java Result: 1
```
- The reason for this error lies in the fact that when a list is iterated over using the forEach method, it's assumed that the list is not modified during the traversal. Modifying the list (in this case deleting elements) causes an error - we can think of the forEach method as getting "confused" here.
- If you want to remove some of the objects from the list during a traversal, you can do so using an iterator. Calling the `remove` method of the iterator object neatly removes form the list the item returned by the iterator with the previous `next` call. Here's a working example of the version of the method:
```JAVA
public class Hand {
    // ...

    public void removeWorst(int value) {
        Iterator<Card> iterator = cards.iterator();

        while (iterator.hasNext()) {
            if (iterator.next().getValue() < value) {
                // removing from the list the element returned by the previous next-method call
                iterator.remove();
            }
        }
    }
}
```


