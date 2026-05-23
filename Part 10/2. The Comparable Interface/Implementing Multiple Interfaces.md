#### Implementing Multiple Interfaces
- A class can implement multiple interfaces.
- Multiple interfaces are implemented by separating the implemented interfaces with commas `(public class ... implements *FirstInterface*, *SecondInterface* ...)`.
- Implementing multiple interfaces requires us to implement all of the methods for which implementations are required by the interfaces.
- Say we have the following `Identifiable` interface.
```java
public interface Identifiable {
    String getId();
}
```
- We want to create a Person who is both identifiable and sortable. This can be achieved by implementing both of the interfaces. An example is provided below.
```java
public class Person implements Identifiable, Comparable<Person> {
    private String name;
    private String socialSecurityNumber;

    public Person(String name, String socialSecurityNumber) {
        this.name = name;
        this.socialSecurityNumber = socialSecurityNumber;
    }

    public String getName() {
        return this.name;
    }

    public String getSocialSecurityNumber() {
        return this.socialSecurityNumber;
    }

    @Override
    public String getId() {
        return getSocialSecurityNumber();
    }

    @Override
    public int compareTo(Person another) {
        return this.getId().compareTo(another.getId());
    }
}
```
