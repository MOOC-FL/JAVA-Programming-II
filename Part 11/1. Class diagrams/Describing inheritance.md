#### Describing inheritance
- In a class diagram inheritance is described by an arrow with a triangle head. The triangle points to the class being inherited from. In the below example the Engine inherits the class Part.
  
<img src="https://github.com/MOOC-FL/Media/blob/main/Java%20Programming%202/part11.1-classdiagram-engine-inherits-part.webp"></img>

- In the below example the class diagram describes the classes from the Product warehouse exercise. The ProductWarehouseWithHistory class inherits the ProductWarehouse class, which, in turn, inherits the Warehouse class. ChangeHistory is a separate class connected to the ProductWarehouse. ProductWarehouseWithHistory knows about the ChangeHistory but the ChangeHistory does not know about the ProductWarehouseWithHistory.

<img src="https://github.com/MOOC-FL/Media/blob/main/Java%20Programming%202/part11.1-classdiagram-productWarehouseWithHistory.webp" alt="" style="position: centre;"></img>

- **Inheritance** of abstract classes is described almost the same way as regular classes. However we add the description `<<abstract>>` above the name of the class.
> The name of the class and its abstract methods are also written in cursive.

<img src="https://github.com/MOOC-FL/Media/blob/main/Java%20Programming%202/part11.1-classdiagram-abstracts.webp" alt="" style="position: centre;"></img>
