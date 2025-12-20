#### When is inheritance worth using?
- **Inheritance** is a tool for building and specializing hierarchies of concepts; a subclass is always a special case of the superclass. If the class to be created is a special case of an existing class, this new class could be created by extending the existing class.
- For example, in the previously discussed car part scenario an engine is a part, but an engine has extra functionality that not all parts have.
-  When inheriting, the subclass receives the functionality of the superclass.
-  If the subclass doesn't need or use some of the inherited functionality, inheritance is not justifiable.
-   Classes that inherit will inherit all the methods and interfaces from the superclass, so the subclass can be used in place of the superclass wherever the superclass is used. It's a good idea to keep the inheritance hierarchy shallow, since maintaining and further developing the hierarchy becomes more difficult as it grows larger.
-   Generally speaking, if your inheritance hierarchy is more than 2 or 3 levels deep, the structure of the program could probably be improved.
-   Inheritance is not useful in every scenario. For instance, extending the class Car with the class Part (or Engine) would be incorrect.
-    A car includes an engine and parts, but an engine or a part is not a car.
> More generally, ***if an object owns or is composed of other objects, inheritance should not be used.***

> When using inheritance, you should take care to ensure that the Single Responsibility Principle holds true. There should only be one reason for each class to change. If you notice that inheriting adds more responsibilities to a class, you should form multiple classes of the class.
