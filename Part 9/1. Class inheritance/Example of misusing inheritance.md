#### Example of misusing inheritance
- Let's consider a postal service and some related classes. `Customer` includes the information related to a customer, and the class` Order` that inherits from the `Customer `class and includes the information about the ordered item. The class `Order` also has a method called postalAddress which represents the `postal address` that the order is shipped to.
```java
public class Customer {

    private String name;
    private String address;

    public Customer(String name, String address) {
        this.name = name;
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
``` 
```java
public class Order extends Customer {

    private String product;
    private String count;

    public Order(String product, String count, String name, String address) {
        super(name, address);
        this.product = product;
        this.count = count;
    }

    public String getProduct() {
        return product;
    }

    public String getCount() {
        return count;
    }

    public String postalAddress() {
        return this.getName() + "\n" + this.getAddress();
    }
}
```
- Above inheritance is not used correctly. When inheriting, the subclass must be a special case of the superclass; an order is definitely not a special case of a customer.
- The misuse shows itself in how the code breaks the single responsibility principle: the Order class is responsible both for maintaining the customer information and the order information.
> The problem becomes very clear when we think of what a change in a customer's address would cause.
- In the case that an address changes, we would have to change every `order` object that relates to that customer. This is hardly ideal. A better solution would be to encapsulate the customer as an object variable of the `Order` class. Thinking more closely on the semantics of an order, this seems intuitive. An order has a customer.
-  Let's modify the `Order` class so that it includes a reference to a `Customer` object.
```java
public class Order {

    private Customer customer;
    private String product;
    private String count;

    public Order(Customer customer, String product, String count) {
        this.customer = customer;
        this.product = product;
        this.count = count;
    }

    public String getProduct() {
        return product;
    }

    public String getCount() {
        return count;
    }

    public String postalAddress() {
        return this.customer.getName() + "\n" + this.customer.getAddress();
    }
}
```
- This version of the `Order` class is better. The method `postalAddress` uses the customer reference to obtain the postal address instead of inheriting the class `Customer`. This helps both the maintenance of the program
and its concrete functionality.
- Now, when a customer changes, all you need to do is change the customer information; there is no need to change the orders.





