#### Sorting By Multiple Criteria
- We sometimes want to sort items based on a number of things.
- Let's look at an example in which films are listed in order of their name and year of release.
- We'll make use of Java's Comparator class here, which offers us the functionality required for sorting. Let's assume that we have the class Film at our disposal.
```java
public class Film {
    private String name;
    private int releaseYear;

    public Film(String name, int releaseYear) {
        this.name = name;
        this.releaseYear = releaseYear;
    }

    public String getName() {
        return this.name;
    }

    public int getReleaseYear() {
        return this.releaseYear;
    }

    public String toString() {
        return this.name + " (" + this.releaseYear + ")";
    }
}

```
- The **Comparator class** provides two essential methods for sorting: `comparing` and `thenComparing`.
- The **comparing method** is passed the value to be `compared first`, and the `thenComparing` method is the next value to be compared.
> The **thenComparing method** can be used many times by chaining methods, which allows virtually unlimited values ​​to be used for comparison.
- When we sort objects, the `comparing` and `thenComparing` methods are given a `reference` to the `object's type` - the method is called in order and the values ​​returned by the method are compared.
-  The method reference is given as `Class::method.` In the example below, we print movies by year and title in order.
```java
List<Film> films = new ArrayList<>();
films.add(new Film("A", 2000));
films.add(new Film("B", 1999));
films.add(new Film("C", 2001));
films.add(new Film("D", 2000));

for (Film e: films) {
    System.out.println(e);
}

Comparator<Film> comparator = Comparator
              .comparing(Film::getReleaseYear)
              .thenComparing(Film::getName);

Collections.sort(films, comparator);

for (Film e: films) {
    System.out.println(e);
}
```
```text
Sample output
A (2000)
B (1999)
C (2001)
D (2000)

B (1999)
A (2000)
D (2000)
C (2001)

```
