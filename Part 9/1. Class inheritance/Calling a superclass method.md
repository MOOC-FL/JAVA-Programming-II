#### Calling a superclass method
- You can call the methods defined in the superclass by prefixing the call with `super`, just as you can call the methods defined in this class by **prefixing the call with this**.
- For example, when overriding the toString method, you can call the superclass's definition of that method in the following manner:
```java
@Override
public String toString() {
    return super.toString() + "\n  And let's add my own message to it!";
}
```


