#### Abstract classes
- Sometimes, when planning a hierarchy of inheritance, there are cases when there exists a clear concept, but that concept is not a good candidate for an object in itself.
- The concept would be beneficial from the point of view of inheritance, since it includes variables and functionality that are shared by all the classes that would inherit it. On the other hand, you should not be able to create instances of the concept itself.
> An abstract class combines interfaces and inheritance.
- You cannot create instances of them — you can only create instances of subclasses of an abstract class.
- They can include normal methods which have a method body, but it's also possible to define abstract methods that only contain the method definition.
- Implementing the abstract methods is the responsibility of subclasses. Generally, abstract classes are used in situations where the concept that the class represents is not a clear independent concept. In such a case you shouldn't be able to create instances of it.
- To define an abstract class or an abstract method the keyword `abstract` is used.
-  An abstract class is defined with the phrase public abstract class *NameOfClass*; an abstract method is defined by public abstract returnType nameOfMethod. Let's take a look at the following abstract class called Operation, which offers a structure for operations and executing them.
