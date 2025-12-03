#### Method to Test For Equality 
- The ***equals*** method checks by default whether the object given as a parameter has the same reference as the object it is being compared to.
 In other words, the default behaviour checks whether the two objects are the same. If the reference is the same, the method returns `true`, and `false` otherwise.
  - This can be illustrated with the following example. The Book class does not have its own implementation of the equals method, so it falls back on the default implementation provided by Java.
```java
Book bookObject = new Book("Book object", 2000, "...");
Book anotherBookObject = bookObject;

if (bookObject.equals(anotherBookObject)) {
    System.out.println("The books are the same");
} else {
    System.out.println("The books aren't the same");
}

// we now create an object with the same content that's nonetheless its own object
anotherBookObject = new Book("Book object", 2000, "...");

if (bookObject.equals(anotherBookObject)) {
    System.out.println("The books are the same");
} else {
    System.out.println("The books aren't the same");
```
- The internal structure of the book objects (i.e., the values of their instance variables ) in the previous example is the same,
  but only the first comparison prints `"The books are the same"`. This is because the references are the same in the first case,
i.e., the object is compared to itself. The second comparison is about two different entities,
even though the variables have the same values.
- For strings, equals works as expected in that it declares two strings identical in content to be 'equal' even if they are two
 separate objects. The String class has replaced the default equals with its own implementation.
- If we want to compare our own classes using the `equals` method, then it must be defined inside the class.
  The method created accepts an `Object-type reference` as a **parameter**, which can be any object.
  The comparison first looks at the `references`. This is followed by checking the parameter object's type with the `instanceof` operation
   if the object type does not match the type of our class, the object cannot be the same. We then create a version of the object that is of the same type as our class, after which the object variables are compared against each other.
```java
public boolean equals(Object comparedObject) {
    // if the variables are located in the same place, they're the same
    if (this == comparedObject) {
        return true;
    }

   // if comparedObject is not of type Book, the objects aren't the same
   if (!(comparedObject instanceof Book)) {
        return false;
    }

    // let's convert the object to a Book-olioksi
    Book comparedBook = (Book) comparedObject;

    // if the instance variables of the objects are the same, so are the objects
    if (this.name.equals(comparedBook.name) &&
        this.published == comparedBook.published &&
        this.content.equals(comparedBook.content)) {
        return true;
    }

    // otherwise, the objects aren't the same
    return false;
}
```
- The `Book` class in its entirety.
```java
public class Book {
    private String name;
    private String content;
    private int published;

    public Book(String name, int published, String content) {
        this.name = name;
        this.published = published;
        this.content = content;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPublished() {
        return this.published;
    }

    public void setPublished(int published) {
        this.published = published;
    }

    public String getContent() {
        return this.content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String toString() {
        return "Name: " + this.name + " (" + this.published +   ")\n"
            + "Content: " + this.content;
    }

    @Override
    public boolean equals(Object comparedObject) {
        // if the variables are located in the same place, they're the same
        if (this == comparedObject) {
            return true;
        }

        // if comparedObject is not of type Book, the objects aren't the same
        if (!(comparedObject instanceof Book)) {
            return false;
        }

        // let's convert the object to a Book-object
        Book comparedBook = (Book) comparedObject;

        // if the instance variables of the objects are the same, so are the objects
        if (this.name.equals(comparedBook.name) &&
            this.published == comparedBook.published &&
            this.content.equals(comparedBook.content)) {
            return true;
        }

        // otherwise, the objects aren't the same
        return false;
    }
}

```



