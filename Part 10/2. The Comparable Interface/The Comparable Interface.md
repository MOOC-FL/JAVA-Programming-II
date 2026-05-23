#### The Comparable Interface
- In the previous section, we looked at interfaces in more general terms - let's now familiarize ourselves with one of Java's ready-made interfaces. **The Comparable interface** defines the compareTo method used to `compare objects`.
- If a class implements **the Comparable interface**, objects created from that class can be sorted using `Java's sorting algorithms`.
- The `compareTo` method required by the Comparable interface receives as its parameter the object to which the "this" object is compared. If the "this" object comes before the object received as a parameter in terms of sorting order, the method should return a negative number. If, on the other hand, the "this" object comes after the object received as a parameter, the method should return a positive number. Otherwise, 0 is returned. The sorting resulting from the `compareTo` method is called **natural ordering**.
- Let's take a look at this with the help of a Member class that represents a child or youth who belongs to a club. Each club member has a name and height. The club members should go to eat in order of height, so the Member class should implement `the Comparable interface`. `The Comparable interface` takes as its type parameter the class that is the subject of the comparison. We'll use the same `Member` class as the type parameter.
```java
public class Member implements Comparable<Member> {
    private String name;
    private int height;

    public Member(String name, int height) {
        this.name = name;
        this.height = height;
    }

    public String getName() {
        return this.name;
    }

    public int getHeight() {
        return this.height;
    }

    @Override
    public String toString() {
        return this.getName() + " (" + this.getHeight() + ")";
    }

    @Override
    public int compareTo(Member member) {
        if (this.height == member.getHeight()) {
            return 0;
        } else if (this.height > member.getHeight()) {
            return 1;
        } else {
            return -1;
        }
    }
}
```
- The `compareTo` method required by the interface returns an integer that informs us of the order of comparison.
- As returning a negative number from `compareTo()` is enough if the `this` object is smaller than the parameter object, and returning zero is sufficient when the lengths are the same, the `compareTo` method described above can also be implemented as follows.
```java
@Override
public int compareTo(Member member) {
    return this.height - member.getHeight();
}
```
- Since the Member class implements the Comparable interface, it is possible to sort the list by using the `sorted` method. In fact, objects of any class that implement the `Comparable interface` can be sorted using the `sorted` method. Be aware, however, that a stream does not sort `the original list - only the items in the stream are sorted`.
- If a programmer wants to organize the original list, the `sort` method of the `Collections` class should be used. This, of course, assumes that the objects on the list implement the `Comparable interface`.
- Sorting club members is straightforward now.
```java
List<Member> member = new ArrayList<>();
member.add(new Member("mikael", 182));
member.add(new Member("matti", 187));
member.add(new Member("ada", 184));

member.stream().forEach(m -> System.out.println(m));
System.out.println();
// sorting the stream that is to be printed using the sorted method
member.stream().sorted().forEach(m -> System.out.println(m));
member.stream().forEach(m -> System.out.println(m));
// sorting a list with the sort-method of the Collections class
Collections.sort(member);
member.stream().forEach(m -> System.out.println(m));

```
```text
mikael (182)
matti (187)
ada (184)

mikael (182)
ada (184)
matti (187)

mikael (182)
matti (187)
ada (184)

mikael (182)
ada (184)
matti (187)

Quiz:
```




































