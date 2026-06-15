#### Describing class constructor
- Below we have the source code for a constructor for our Person class. The constructor gets the name of the person as a parameter.
```java
public class Person {
    private String name;
    private int age;

    public Person(String initialName) {
        this.name = initialName;
        this.age = 0;
    }
}
```
- [ ] In a class diagram, we list the constructor
- [ ] (and all other methods) below the attributes.
- [ ] A line below the attributes list separates it from the method list. Methods are written with +/- (depending on the visibility of the method),
- [ ] method name, parameters, and their types.
> The constructor above is written `+Person(initialName:String)`
> The parameters are written the same way class attributes are — `"parameterName: parameterType"`.

<img src="https://github.com/MOOC-FL/Media/blob/main/Java%20Programming%202/part4.1-classdiagram-person-name-age-constructor.webp"></img>

