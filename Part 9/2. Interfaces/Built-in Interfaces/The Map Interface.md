#### The Map Interface
- The Map interface defines the basic behavior associated with hash tables. Because the HashMap class implements the Mapinterface, it can also be accessed through the `Map` interface.
```java
Map<String, String> maps = new HashMap<>();
maps.put("ganbatte", "good luck");
maps.put("hai", "yes");
```
> The keys to the hash table are obtained using the `keySet` method.
```java
Map<String, String> maps = new HashMap<>();
maps.put("ganbatte", "good luck");
maps.put("hai", "yes");

for (String key : maps.keySet()) {
    System.out.println(key + ": " + maps.get(key));
}
```
```text
ganbatte: good luck hai: yes
```
- The `keySet` method Returns a set of elements that implement the `Set` interface.You can use a for-each statement to go through a `set` that implements the Setinterface. The `hash` values ​​can be obtained from the `hash` table using the values method.
- The `values` method Returns a set of elements that implement the` Collection` interface. Let's take a quick look at the `Set` and `Collection` interfaces.
