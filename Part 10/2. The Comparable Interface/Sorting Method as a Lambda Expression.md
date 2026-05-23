#### Sorting Method as a Lambda Expression
- Let's assume that we have the following Person class for use.
```java
public class Person {

    private String name;
    private int birthYear;

    public Person(String name, int birthYear) {
        this.name = name;
        this.birthYear = birthYear;
    }

    public String getName() {
        return name;
    }

    public int getBirthYear() {
        return birthYear;
    }
}
```
- And person objects on a list.
```java
ArrayList<Person> persons = new ArrayList<>();
persons.add(new Person("Ada Lovelace", 1815));
persons.add(new Person("Irma Wyman", 1928));
persons.add(new Person("Grace Hopper", 1906));
persons.add(new Person("Mary Coombs", 1929));
```
- We want to sort the list without having to implement the `Comparable` interface.
- Both the sort method of the Collections class and the stream's sorted method accept a lambda expression as a parameter that defines the sorting criteria.
- More specifically, both methods can be provided with an object that implements the Comparator interface, which defines the desired order - the lambda expression is used to create this object.
```java
ArrayList<Person> persons = new ArrayList<>();
persons.add(new Person("Ada Lovelace", 1815));
persons.add(new Person("Irma Wyman", 1928));
persons.add(new Person("Grace Hopper", 1906));
persons.add(new Person("Mary Coombs", 1929));

persons.stream().sorted((p1, p2) -> {
    return p1.getBirthYear() - p2.getBirthYear();
}).forEach(p -> System.out.println(p.getName()));

System.out.println();

persons.stream().forEach(p -> System.out.println(p.getName()));

System.out.println();

Collections.sort(persons, (p1, p2) -> p1.getBirthYear() - p2.getBirthYear());

persons.stream().forEach(p -> System.out.println(p.getName()));
```  
```text
Ada Lovelace
Grace Hopper
Irma Wyman
Mary Coombs

Ada Lovelace
Irma Wyman
Grace Hopper
Mary Coombs

Ada Lovelace
Grace Hopper
Irma Wyman
Mary Coombs
```
- When comparing strings, we can use the `compareTo` method provided by the String class. The method returns an integer that describes the order of both the string given to it as a parameter and the string that's calling it.
```java
ArrayList<Person> persons = new ArrayList<>();
persons.add(new Person("Ada Lovelace", 1815));
persons.add(new Person("Irma Wyman", 1928));
persons.add(new Person("Grace Hopper", 1906));
persons.add(new Person("Mary Coombs", 1929));

persons.stream().sorted((p1, p2) -> {
    return p1.getName().compareTo(p2.getName());
}).forEach(p -> System.out.println(p.getName()));
```
```text
Sample output
Ada Lovelace
Grace Hopper
Irma Wyman
Mary Coombs
```

